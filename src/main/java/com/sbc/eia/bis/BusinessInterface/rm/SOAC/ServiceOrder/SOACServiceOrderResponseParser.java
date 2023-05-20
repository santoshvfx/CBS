//$Id: SOACServiceOrderResponseParser.java,v 1.19 2010/09/17 22:37:29 js7440 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/2006		Sriram Chevuturu      Creation
//# 08/31/2006  Doris Sunga			  Updated import line from com.sbc.eia.bis.BusinessInterface.rm.SOAC.SoacWireCenterTable
//#                                   to com.sbc.eia.bis.rm.database.SoacWireCenterTable	
//# 10/16/2006 	Doris Sunga			  LS R4: Adding GPON
//# 2/24/2008   Ott Phannavong	      LS7: Modified processByNetworkType() to take bonded network
//# 02/28/2006 	Doris Sunga			  LS7: Modified processByNetworkType() added BOND network type
//# 09/16/2010  Julius Sembrano		  Added IBCO and IBRT Network Types
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SoacDefect;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants.SoacServiceOrderConstants;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.DefectFidVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.SectionVO;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.DataAccessorException;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SOACServiceOrderResponseParser extends ServiceOrderResponseParser
{

    protected String NETWORK_TYPE = null;

    //Defective CableInfo
    SoacDefect[] defectCableList = null;

    //a boolean indicating  the database failure while retrieving the CLLI8
    //we call this combinationResponse because if the db failure occurs, we will
    //send Error Response to OMS queue and send an Email Report. Hence CombinationResponse.
    boolean isCombinationResponse = false;
    String comboResponseExceptionCode = null;
    String comboResponseExceptionMessage = null;

    protected boolean FTTP_REPONSE_PROCESSED = false;
    protected boolean FTTN_REPONSE_PROCESSED = false;

    protected String CLLI8 = null;

    //Network Specific Parser instance as an interface.
    protected ResponseParserI aResponseParser = null;

    //
    FttpResponseParser aFttpResponseParser = null;
    FttnResponseParser aFttnResponseParser = null;
    
    public SOACServiceOrderResponseParser(Hashtable aProperties, Logger aLogger)
    {
        super(aProperties, aLogger);
    }

    public void processFCIFResponseString(String responseFCIFString)
            throws ParserSvcException, DataAccessorException
    {
        super.processFCIFResponseString(responseFCIFString);

        determineNetworkType();

        try
        {
            // for LS3 not need to specific parsing VOIP Orders.
            if(NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.VOIP_NETWORK) != true)
                CLLI8 = retrieveCLLI8().trim();
        }
        catch(SQLException e)
        {
            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                    "Could Not Retrieve CLLI8 from DB.");
            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                    "Setting the CombinationResponse Indicator to true");
            isCombinationResponse = true;
            comboResponseExceptionCode = ExceptionCode.ERR_RM_CABLENAME_NO_CLLI;
            comboResponseExceptionMessage = " Multiple attempts to retrieve CLLI code from data base failed, "
                    + "unable to append CLLI in SOAC response. ";
        }
        catch(Exception e)
        {
            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                    "Could Not Retrieve CLLI8 from DB.");
            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                    "Setting the CombinationResponse Indicator to true");
            isCombinationResponse = true;
            comboResponseExceptionCode = ExceptionCode.ERR_RM_CABLENAME_NO_CLLI;
            comboResponseExceptionMessage = " Multiple attempts to retrieve CLLI code from data base failed, "
                    + "unable to append CLLI in SOAC response. ";

        }

        //if VOIP Network, then no need for extra parsing. we just get the Assignment section
        //else continue and do the extra parsing for FTTN / FTTP / RGPN.
        if(NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.VOIP_NETWORK) != true)
            processByNetworkType();
    }

    public void processByNetworkType()
    {

        String myMethodName = "SOACServiceOrderResponseParser::processByNetworkType()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        if(NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.RGPN_NETWORK))
            NETWORK_TYPE = NetworkTypeValues.RGPON; //NETWORK_TYPE = "RGPON";

        if(NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.FTTP_NETWORK)
                || NETWORK_TYPE
                        .equalsIgnoreCase(SvcOrderConstants.RGPON_NETWORK)
                || NETWORK_TYPE
                        .equalsIgnoreCase(SvcOrderConstants.GPON_NETWORK))
        {
            aFttpResponseParser = new FttpResponseParser(myProperties,
                    myLogger, this);
            aResponseParser = aFttpResponseParser;
        }
        else if(NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.FTTN_NETWORK) || 
                NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR) ||
                NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.IPCO) || 
                NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.IPRT) || 
                NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.IPCOBP_NETWORK)|| 
                NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.IPRTBP_NETWORK))
        {
            aFttnResponseParser = new FttnResponseParser(myProperties,
                    myLogger, this);
            aResponseParser = aFttnResponseParser;
        }      
        // VOIP orders no specific parsing for LS3. all the Control Header / Echo sections are already parsed.	
        else if(NETWORK_TYPE.equalsIgnoreCase(SvcOrderConstants.VOIP_NETWORK))
            return;

        if(getTotalSegments() > 0)
        {
            if(aResponseParser != null)
            {
                aResponseParser.processResponse();
                if(NETWORK_TYPE
                        .equalsIgnoreCase(SvcOrderConstants.FTTP_NETWORK)
                        || NETWORK_TYPE
                                .equalsIgnoreCase(SvcOrderConstants.RGPON_NETWORK)
                        || NETWORK_TYPE
                                .equalsIgnoreCase(SvcOrderConstants.GPON_NETWORK))
                    FTTP_REPONSE_PROCESSED = true;
                else if(NETWORK_TYPE
                        .equalsIgnoreCase(SvcOrderConstants.FTTN_NETWORK)
                        || NETWORK_TYPE
                                .equalsIgnoreCase(SvcOrderConstants.BOND_NETWORK_INDICATOR)
                        || NETWORK_TYPE
                        		.equalsIgnoreCase(SvcOrderConstants.IPCO)
                        || NETWORK_TYPE
                                .equalsIgnoreCase(SvcOrderConstants.IPRT)
                        || NETWORK_TYPE
                        		.equalsIgnoreCase(SvcOrderConstants.IPCOBP_NETWORK)
                        || NETWORK_TYPE
                        		.equalsIgnoreCase(SvcOrderConstants.IPRTBP_NETWORK))
                    FTTN_REPONSE_PROCESSED = true;
            }
            else
                myLogger.log(LogEventId.PARTIAL_FAILURE,
                        "Not a Known Network Type. Can not Process Further.");

        }
        //else do nothing...may be just C2 Header and nothing else...

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

    }

    public void determineNetworkType()
    {
        String myMethodName = "SOACServiceOrderResponseParser::determineNetworkType()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        if(FCIF_RESPONSE_PROSESSED == true)
        {
            try
            {
                NETWORK_TYPE = (String) aResponseSSOVO
                        .get(SoacServiceOrderConstants.NETWORK_TYPE);
                myLogger.log(LogEventId.DEBUG_LEVEL_1, "NetworkType =<"
                        + NETWORK_TYPE + ">");

            }
            catch(Exception e)
            {
            }
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
    }

    public String getResendIndicator()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getResendIndicator()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        String retVal = null;
        if(FCIF_RESPONSE_PROSESSED == true)
        {
            try
            {
                retVal = (String) aResponseSSOVO
                        .get(SoacServiceOrderConstants.RESEND_INDICATOR);
                ;
                if(retVal != null)
                    retVal = retVal.trim();
                myLogger.log(LogEventId.DEBUG_LEVEL_1, "FTTNInPair =<" + retVal
                        + ">");
            }
            catch(Exception e)
            {
            }
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return retVal;

    }

    public String getDefectCode()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getDefectCode()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        String retVal = null;
        if(FCIF_RESPONSE_PROSESSED == true)
        {
            try
            {
                //retVal = (String) aResponseSSOVO.get(SoacServiceOrderConstants);
                myLogger.log(LogEventId.DEBUG_LEVEL_1, "DefectCode =<" + retVal
                        + ">");
            }
            catch(Exception e)
            {
            }
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return retVal;

    }

    public String getDefectCable()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getDefectCable()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        String retVal = null;
        if(FCIF_RESPONSE_PROSESSED == true)
        {
            try
            {
                //retVal = (String) aResponseSSOVO.get(SoacServiceOrderConstants);
                myLogger.log(LogEventId.DEBUG_LEVEL_1, "DefectCable =<"
                        + retVal + ">");
            }
            catch(Exception e)
            {
            }
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return retVal;

    }

    public String getDefectPair()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getDefectPair()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        String retVal = null;
        if(FCIF_RESPONSE_PROSESSED == true)
        {
            try
            {
                //retVal = (String) aResponseSSOVO.get(SoacServiceOrderConstants);
                myLogger.log(LogEventId.DEBUG_LEVEL_1, "DefectPair =<" + retVal
                        + ">");
            }
            catch(Exception e)
            {
            }
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return retVal;

    }

    public Fttn getFTTN()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getFTTN()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        Fttn aFTTN = null;

        if(aResponseParser != null)
            if(FTTN_REPONSE_PROCESSED)
                aFTTN = (Fttn) aResponseParser.createNetworkStructure();

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

        return aFTTN;
    }

    public Fttp getFTTP()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getFTTP()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        Fttp aFTTP = null;

        if(aResponseParser != null)
            if(FTTP_REPONSE_PROCESSED)
                aFTTP = (Fttp) aResponseParser.createNetworkStructure();

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

        return aFTTP;

    }

    public SoacDefect[] getDefectiveCablePairList()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getDefectiveCablePairs()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return defectCableList;
    }

    protected SoacDefect[] parseDefectiveCablePairs()
    {

        String myMethodName = "SOACServiceOrderResponseParser::parseDefectiveCablePairs()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        if(FCIF_RESPONSE_PROSESSED == true)
        {
            try
            {

                SectionVO svo = (SectionVO) aResponseSSOVO.get("ASGM");
                DefectFidVO[] defList = svo.getAllDefectFids();
                SoacDefect[] aDefectList = null;
                int i = 0, numSoacDefect = 0;
                if(defList.length > 0)
                {

                    aDefectList = new SoacDefect[defList.length + 1];//if the defective cabl eis also splitting for SAI.	
                    while(numSoacDefect < defList.length)
                    {
                        aDefectList[i] = new SoacDefect();
                        String aCable = defList[numSoacDefect].getDefectCable()
                                .trim();
                        if(aFttnResponseParser != null
                                && aFttnResponseParser
                                        .getSAI_DSLAM_PIGTAIL_COPPER_CABLE()
                                        .equalsIgnoreCase(aCable))
                        {
                            aDefectList[i]
                                    .setDefectCable(processDefectiveCable(
                                            aCable, "_I"));
                            aDefectList[i].setDefectCode(defList[numSoacDefect]
                                    .getDefectCode());
                            aDefectList[i].setDefectPair(defList[numSoacDefect]
                                    .getDefectPair());

                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Number =<" + i + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Code   =<"
                                            + aDefectList[i].getDefectCode()
                                            + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Cable  =<"
                                            + aDefectList[i].getDefectCable()
                                            + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Pair  =<"
                                            + aDefectList[i].getDefectPair()
                                            + ">");

                            aDefectList[i + 1] = new SoacDefect();
                            aDefectList[i + 1]
                                    .setDefectCable(processDefectiveCable(
                                            aCable, "_O"));
                            aDefectList[i + 1]
                                    .setDefectCode(defList[numSoacDefect]
                                            .getDefectCode());
                            aDefectList[i + 1]
                                    .setDefectPair(defList[numSoacDefect]
                                            .getDefectPair());

                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Number =<" + i + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Code   =<"
                                            + aDefectList[i + 1]
                                                    .getDefectCode() + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Cable  =<"
                                            + aDefectList[i + 1]
                                                    .getDefectCable() + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Pair  =<"
                                            + aDefectList[i + 1]
                                                    .getDefectPair() + ">");

                            i = +2;
                        }
                        else
                        {
                            aDefectList[i]
                                    .setDefectCable(processDefectiveCable(
                                            aCable, null));
                            aDefectList[i].setDefectCode(defList[numSoacDefect]
                                    .getDefectCode());
                            aDefectList[i].setDefectPair(defList[numSoacDefect]
                                    .getDefectPair());

                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Number =<" + i + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Code   =<"
                                            + aDefectList[i].getDefectCode()
                                            + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Cable  =<"
                                            + aDefectList[i].getDefectCable()
                                            + ">");
                            myLogger.log(LogEventId.DEBUG_LEVEL_1,
                                    "Defective Cable Pair  =<"
                                            + aDefectList[i].getDefectPair()
                                            + ">");

                            i++;
                        }
                        numSoacDefect++;
                    }
                    myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
                }

                SoacDefect[] aNewDefectList = new SoacDefect[i];
                for(int ii = 0; ii < i; ii++)
                    aNewDefectList[ii] = aDefectList[ii];

                return aNewDefectList;
            }
            catch(Exception e)
            {
            }
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return null;
    }

    public String processDefectiveCable(String aCable, String aUnderScoreI_O)
    {
        String myMethodName = "SOACServiceOrderResponseParser::processDefectiveCable()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        String retVal = null;

        //just incase CLLI8 is null..which case...it's going to fail in Granite.
        try
        {
            if(aUnderScoreI_O != null)
                retVal = aCable + aUnderScoreI_O + "/" + CLLI8.toUpperCase();
            else
                retVal = aCable + "/" + CLLI8.toUpperCase();
        }
        catch(Exception e)
        {
            if(aUnderScoreI_O != null)
                retVal = aCable + aUnderScoreI_O + "/" + CLLI8;
            else
                retVal = aCable + "/" + CLLI8;

        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return retVal;
    }

    //the follwoing two methods will return call the super class methods
    //in case the database retrieval of CLLI8 failed for any reason.
    /**
     * @see com.sbc.eia.bis.BusinessInterface.rm.SOAC.SendF1F2OrderHelper#getExceptionDescription()
     */
    public String getExceptionDescription()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getExceptionDescription()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        String retVal = null;

        if(FTTP_REPONSE_PROCESSED || FTTN_REPONSE_PROCESSED
                || FCIF_RESPONSE_PROSESSED)
        {
            if(isCombinationResponse == true)
                retVal = comboResponseExceptionMessage;
            else
                retVal = super.getExceptionDescription();

            myLogger.log(LogEventId.DEBUG_LEVEL_1, "getExceptionMessage =<"
                    + retVal + ">");
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        return retVal;
    }

    public String getExceptionMessage()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getExceptionMessage()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        String retVal = null;

        if(FTTP_REPONSE_PROCESSED || FTTN_REPONSE_PROCESSED
                || FCIF_RESPONSE_PROSESSED)
        {

            if(isCombinationResponse == true)
                retVal = comboResponseExceptionMessage;
            else
                retVal = super.getExceptionMessage();

            myLogger.log(LogEventId.DEBUG_LEVEL_1, "getExceptionMessage =<"
                    + retVal + ">");
        }

        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        return retVal;
    }

    /**
     * Method aExceptionCode.
     * @return String
     */
    public String getExceptionCode()
    {
        String myMethodName = "SOACServiceOrderResponseParser::aExceptionCode()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        String retVal = null;

        if(FTTP_REPONSE_PROCESSED || FTTN_REPONSE_PROCESSED
                || FCIF_RESPONSE_PROSESSED)
        {
            if(isCombinationResponse == true)
                retVal = comboResponseExceptionCode;
            else
                retVal = super.getExceptionCode();

            myLogger.log(LogEventId.DEBUG_LEVEL_1, "ExceptionCode =<" + retVal
                    + ">");

        }
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        return retVal;
    }

    public String retrieveCLLI8() throws SQLException
    {
        String myMethodName = "SOACServiceOrderResponseParser::retrieveCLLI8()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        String retVal = null;
        ResultSet rs = null;
        DBConnection aDBConn = null;
        PreparedStatement ps = null;

        //first retry variables
        ResultSet rs1 = null;
        DBConnection aDBConn1 = null;
        PreparedStatement ps1 = null;

        //second retry variables
        ResultSet rs2 = null;
        DBConnection aDBConn2 = null;
        PreparedStatement ps2 = null;

        String SQLstatement = "select CLLI8 from SOAC_WIRE_CENTER where NPANXX = ? ";

        String aNpaNxx = getWireCenter();
        try
        {
            aDBConn = SoacWireCenterTable.getDBConnection(myProperties,
                    myLogger);
            ps = aDBConn.getConnection().prepareStatement(SQLstatement);
            ps.setString(1, aNpaNxx);

            myLogger.log(LogEventId.REMOTE_CALL, (String) myProperties
                    .get("jdbcURL"), (String) myProperties.get("jdbcUSERID"),
                    (String) myProperties.get("jdbcUSERID"),
                    "SOACServiceOrderResponseParser::retrieveCLLI8()");

            try
            {
                rs = ps.executeQuery();
            }
            finally
            {
                myLogger.log(LogEventId.REMOTE_RETURN, (String) myProperties
                        .get("jdbcURL"), (String) myProperties
                        .get("jdbcUSERID"), (String) myProperties
                        .get("jdbcUSERID"),
                        "SOACServiceOrderResponseParser::retrieveCLLI8()");

            }

            if(rs.next())
            {
                myLogger.log(LogEventId.DEBUG_LEVEL_1, "Result: |"
                        + rs.getString(1) + "|");
                retVal = rs.getString(1);

            }
            myLogger.log(LogEventId.INFO_LEVEL_1,
                    "Got Data from Database at First Hit.");
        }
        catch(Exception e)
        {
            myLogger.log(LogEventId.ERROR, "Error message [" + e.getMessage()
                    + "]");
            myLogger.log(LogEventId.INFO_LEVEL_1,
                    "Initial Database Connection & Querying Failed");
            myLogger.log(LogEventId.INFO_LEVEL_1,
                    "First retry to fetch CLLI8 from db.");

            try
            {
                aDBConn1 = SoacWireCenterTable.getDBConnection(myProperties,
                        myLogger);
                ps1 = aDBConn1.getConnection().prepareStatement(SQLstatement);
                ps1.setString(1, aNpaNxx);

                myLogger.log(LogEventId.REMOTE_CALL, (String) myProperties
                        .get("jdbcURL"), (String) myProperties
                        .get("jdbcUSERID"), (String) myProperties
                        .get("jdbcUSERID"),
                        "SOACServiceOrderResponseParser::retrieveCLLI8()");

                try
                {
                    rs1 = ps1.executeQuery();
                }
                finally
                {
                    myLogger.log(LogEventId.REMOTE_RETURN,
                            (String) myProperties.get("jdbcURL"),
                            (String) myProperties.get("jdbcUSERID"),
                            (String) myProperties.get("jdbcUSERID"),
                            "SOACServiceOrderResponseParser::retrieveCLLI8()");

                }

                if(rs1.next())
                {
                    myLogger.log(LogEventId.DEBUG_LEVEL_1, "Result: |"
                            + rs1.getString(1) + "|");
                    retVal = rs1.getString(1);

                }
                myLogger.log(LogEventId.INFO_LEVEL_1,
                        "Got Data from Database at First Retry.");
            }
            catch(Exception ex)
            {
                myLogger.log(LogEventId.ERROR, "Error message ["
                        + ex.getMessage() + "]");
                myLogger.log(LogEventId.INFO_LEVEL_1,
                        "First Retry Database Connection & Querying Failed");
                myLogger.log(LogEventId.INFO_LEVEL_1, "Wait for 10 secs.");

                try
                {
                    synchronized(this)
                    {
                        wait(10000);
                    }
                }
                catch(InterruptedException ex1)
                {
                    myLogger
                            .log(LogEventId.INFO_LEVEL_1,
                                    "Caught an InterruptedException while waiting before retry.");
                    myLogger.log(LogEventId.ERROR, "Error message ["
                            + ex1.getMessage() + "]");
                    myLogger.log(LogEventId.ERROR, "Error message ["
                            + ex1.toString() + "]");
                }
                catch(Throwable ex2)
                {
                    myLogger.log(LogEventId.INFO_LEVEL_1,
                            "Caught an Throwable while waiting before retry.");
                    myLogger.log(LogEventId.ERROR, "Error message ["
                            + ex2.getMessage() + "]");
                    myLogger.log(LogEventId.ERROR, "Error message ["
                            + ex2.toString() + "]");
                }

                myLogger.log(LogEventId.INFO_LEVEL_1,
                        "Second Retry to fetch CLLI8 from db again.");

                try
                {
                    aDBConn2 = SoacWireCenterTable.getDBConnection(
                            myProperties, myLogger);
                    ps2 = aDBConn2.getConnection().prepareStatement(
                            SQLstatement);
                    ps2.setString(1, aNpaNxx);

                    myLogger.log(LogEventId.REMOTE_CALL, (String) myProperties
                            .get("jdbcURL"), (String) myProperties
                            .get("jdbcUSERID"), (String) myProperties
                            .get("jdbcDATA_SOURCE_NAME"),
                            "SOACServiceOrderResponseParser::retrieveCLLI8()");

                    try
                    {
                        rs2 = ps2.executeQuery();
                    }
                    finally
                    {
                        myLogger
                                .log(
                                        LogEventId.REMOTE_RETURN,
                                        (String) myProperties.get("jdbcURL"),
                                        (String) myProperties.get("jdbcUSERID"),
                                        (String) myProperties
                                                .get("jdbcDATA_SOURCE_NAME"),
                                        "SOACServiceOrderResponseParser::retrieveCLLI8()");

                    }

                    if(rs2.next())
                    {
                        myLogger.log(LogEventId.DEBUG_LEVEL_1, "Result: |"
                                + rs2.getString(1) + "|");
                        retVal = rs2.getString(1);

                    }
                    myLogger.log(LogEventId.INFO_LEVEL_1,
                            "Got Data from Database at Second Retry.");
                }
                catch(Exception ex3)
                {
                    myLogger.log(LogEventId.ERROR, "Error message ["
                            + ex3.getMessage() + "]");
                    myLogger
                            .log(LogEventId.INFO_LEVEL_1,
                                    "Second Retry Database Connection & Querying Failed");
                    throw new SQLException("No data found");
                }

            }
        }
        finally
        {
            try
            {
                if(rs != null)
                    rs.close();
                if(ps != null)
                    ps.close();

                if(rs1 != null)
                    rs1.close();
                if(ps1 != null)
                    ps1.close();

                if(rs2 != null)
                    rs2.close();
                if(ps2 != null)
                    ps2.close();

            }
            catch(Exception e)
            {
            }
            try
            {
                if(aDBConn != null)
                    aDBConn.disconnect();
                if(aDBConn1 != null)
                    aDBConn1.disconnect();
                if(aDBConn2 != null)
                    aDBConn2.disconnect();
            }
            catch(Exception e)
            {
            }
            aDBConn = null;
            aDBConn1 = null;
            aDBConn2 = null;
        }

        if(retVal == null || retVal.trim().length() == 0)
            throw new SQLException("No data found");

        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return retVal;

    }

    protected int getTotalSegments()
    {
        int numSegs = (new FttpResponseParser(myProperties, myLogger, this))
                .getTotalSegments();

        return numSegs;
    }

    /**
     * Method isCombinationResponse.
     * @return boolean
     */
    public boolean isCombinationResponse()
    {
        return isCombinationResponse;
    }

    public void setIsCombinationResponse(boolean value)
    {
        isCombinationResponse = value;
    }

    public void setCombinationResponseExceptionCode(String aCode)
    {
        comboResponseExceptionCode = aCode;
    }

    public String getCombinationResponseExceptionCode()
    {
        return comboResponseExceptionCode;
    }

    public void setCombinationResponseExceptionMessage(String aMessage)
    {
        comboResponseExceptionMessage = aMessage;
    }

    public String getCombinationResponseExceptionMessage()
    {
        return comboResponseExceptionMessage;
    }

    public String getTaperCode()
    {
        String myMethodName = "SOACServiceOrderResponseParser::getTaperCode()";
        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        String aTaperCode = null;

        if(FTTN_REPONSE_PROCESSED)
        {
            if(aFttnResponseParser != null)
                aTaperCode = aFttnResponseParser.getTaperCode();
        }
        else if(FTTP_REPONSE_PROCESSED)
        {
            if(aFttpResponseParser != null)
                aTaperCode = aFttpResponseParser.getTaperCode();
        }
        myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);

        return aTaperCode;

    }

}