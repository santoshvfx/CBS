//$Id: ModifyFacilityInfo.java,v 1.54 2007/12/20 17:22:15 op1664 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date                | Author            | Notes
//# ----------------------------------------------------------------------------
//# 03/30/2005          jp2854              Creation.
//# 6/1/2005            Chaitanya           Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1
//# 6/24/2205           Sriram Chevuturu    Added excecute Method.
//# 08/07/2005          Sriram Chevuturu    Added all other remaining Methods for parsing the FCIF Request.
//# 08/09/2005          Sriram Chevuturu    Added Some more Code.
//# 08/12/2005          Sriram Chevuturu    Added Some more Code.
//# 08/23/2005          Sriram Chevuturu    Some Minor Code Changes.
//# 10/09/2005          Sriram Chevuturu    Updated so as not to send request to XNG-RC for LS release1.
//# 12/15/2005          Hongmei parkin      Changed for Lightspeed Release 2
//# 04/02/2006          Sriram Chevuturu    Changed for Lightspeed Release 2
//# 11/14/2006          Rene Duka           CR 12110: Changed comparison algorithm of FTTP segments.
//# 12/11/2006          Rene Duka           PR 18942097 and 19042150 (R3)/DR 172866: Fixed business logic in LFACS FTTN scenario.
//# 01/18/2007          Rene Duka           DR 173998: Correct logging information for FTTp VLAN ID affecting scenario.
//# 03/27/2007          Rene Duka           PR 19520247: Fixed circuitID format from "." to "/".
//# 03/28/2007          Rene Duka           Defect # 62803: Set correlation ID correctly on the IOM BIS call.
//# 11/07/2007          Changchuan Yin      Updated for LS6:BBNMS client.
//# 11/10/2007		    Doris Sunga			LS6:CR14149: BBNMS client
//# 11/13/2007		    Doris Sunga			LS6:CR14149: BBNMS client update
//# 11/15/2007		    Doris Sunga			LS6:CR14149: add setUtility() method to take latest utility value
//# 11/15/2007		    Doris Sunga			LS6:DR79712, DR79715: reset variables 
//# 11/15/2007		    Doris Sunga			LS6:DR 79957: convert RGPN to RGPON when sending network type to IOM BIS

package com.sbc.eia.bis.facades.rm.transactions.ModifyFacilityInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import javax.mail.MessagingException;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.OMSEmailHelper;
import com.sbc.eia.bis.BusinessInterface.rm.RetrieveCustomerTransportInfo.RetrieveCustomerTransportInfo;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOACEmailSender;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOACEmailSenderFactory;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SoacDefect;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.RmNam.utilities.IomClient.IomClient;
import com.sbc.eia.bis.embus.service.access.SBCLoggingIDProvider;
import com.sbc.eia.bis.facades.rm.transactions.ModifyInventory.ModifyInventory;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.ApplicationMapper;
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.valueobject.accessor.DataAccessorException;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.iomip.UpdateIpProductDataReturn;
import com.sbc.eia.idl.iomip_types.Products;
import com.sbc.eia.bis.BusinessInterface.rm.RetrieveCustomerTransportInfo.RetrieveCustomerTransportInfo;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.CopperSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.FiberSegmentSeqOpt;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignment;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignmentOpt;
import com.sbc.eia.idl.rmim.RetrieveCustomerTransportInfoReturn;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.ExceptionDataOptBisHelper;
import com.sbc.iomip.sessionbeans.IomipBIS;
/**
* @author jp2854
*
*/
public class ModifyFacilityInfo extends TranBase {

private Utility aUtility = null;
private Hashtable aProperties = null;
com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.ModifyFacilityInfo aModifyFacilityInfo = null;

//Status for both resend and unsolicited response
//
private final static String[] assignmentStatus = { "FANK", "FACH", "FANC", "FDND", "FUAU", "FUND" };

private Fttp sFttp;
private Fttn sFttn;

private Fttp gFttp;
private Fttn gFttn;
//
public static String NA_STRING = "NA";
public static String NEW_LINE = "\n";

public String transactionName = "ModifyFacilityInfo";

//this is the ModifyInventoryObject for Email Callback for MI.
private ModifyInventory aMI = null;

//i need to create my own BisContext
BisContext aContext = null;
  
boolean vlanIdAffecting = false;
//there are number of conditions in which to sent email..
//but we want to send only once..once sent, this will set this to true..
//then we will never send again if there arises a situation to send email.
private boolean emailSent;

private boolean sendEmail;
private boolean sendEmailBBNMS = false;

/**
 * Constructor for ModifyFacilityInfo.
 */
public ModifyFacilityInfo() {
    super();
}

/**
 * Constructor for ModifyFacilityInfo.
 * @param param
 */
public ModifyFacilityInfo(Utility utility, Hashtable properties) {
    super(properties);
    aUtility = utility;
    aProperties = getPROPERTIES();
    aContext = setBisContext();
    
}

public ModifyFacilityInfo(Utility utility, Hashtable properties, String aCorrID) {
    super(properties);
    aUtility = utility;
    aProperties = getPROPERTIES();
    aContext = setBisContext(aCorrID);
    
}


public ModifyFacilityInfo(Hashtable properties) {
    super(properties);
    aUtility = this;
    aProperties = getPROPERTIES();
    aContext = setBisContext();
}

public BisContext setBisContext() {
    //ObjectProperty[] of 4 pairs of tag/value...
    ObjectProperty[] temp = new ObjectProperty[4];
    
    //IOM uses Application and BusinessUnit . Value is "RMBIS"
    //create aBisContext what else properties ..
    temp[0] = new ObjectProperty("Application","RMBIS");
    temp[1] = new ObjectProperty("CustomerName","SOAC");
    temp[2] = new ObjectProperty("BusinessUnit","RMBIS");
    SBCLoggingIDProvider loggingIDProvider = new SBCLoggingIDProvider(
                                                    (String) PROPERTIES.get("BIS_NAME"),
                                                    (String) PROPERTIES.get("BIS_VERSION"));
    temp[3] = new ObjectProperty("LoggingInformation",loggingIDProvider.getCorrelationId());                                                

    return new BisContext(temp);
}

public BisContext setBisContext(String aCorrID) {
    //ObjectProperty[] of 4 pairs of tag/value...
    ObjectProperty[] temp = new ObjectProperty[4];
    
    //IOM uses Application and BusinessUnit . Value is "RMBIS"
    //create aBisContext what else properties ..
    temp[0] = new ObjectProperty("Application","RMBIS");
    temp[1] = new ObjectProperty("CustomerName","SOAC");
    temp[2] = new ObjectProperty("BusinessUnit","RMBIS");
    temp[3] = new ObjectProperty("LoggingInformation",aCorrID);                                             

    return new BisContext(temp);
}
public void setUtility(Utility utility)
{
    aUtility = utility;
}
public void modifyFacilityInfoRequest(SOACServiceOrderResponseParser parsedFCIF,
        String correlationId,
        String aApplicationID,
        com.sbc.bccs.utilities.Logger myLogger)
    throws ParserSvcException, DataAccessorException, IOException, MessagingException, Exception 
{      
    String myMethodNameName = "RM::ModifyFacilityInfo:modifyFacilityInfoRequest";               
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodNameName);        
    
    boolean aIsLST = false;
    
    sFttp = null;
    sFttn = null;
    gFttp = null;
    gFttn = null;

    vlanIdAffecting = false;
    emailSent = false;
    sendEmail = false; 
           
    aIsLST = ApplicationMapper.getInstanceOfApplicationMapper(
          aUtility, aProperties).isLST(aApplicationID);
    
    try
    {
       if(!aIsLST)
          processModifyFacilityInfoRequest(parsedFCIF);
       else          
          processModifyFacilityInfoRequestLST(parsedFCIF, correlationId, aApplicationID, myLogger);
    }
    catch(Exception e)
    {}
    finally
    {                            
      aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodNameName);
    }  
}
	
public void processModifyFacilityInfoRequestLST(SOACServiceOrderResponseParser parsedFCIF, 
	          String correlationId,
	          String aApplicationID,
	          com.sbc.bccs.utilities.Logger myLogger)
	     throws ParserSvcException, DataAccessorException, IOException, MessagingException, Exception 
	{
	      String myMethodNameName = "RM::ModifyFacilityInfo:processModifyFacilityInfoRequestLST()";
	      aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodNameName);
	      
	      String xmlResp = null;
	      Properties messageTags = null;

	      SOACEmailSender emailSender = SOACEmailSenderFactory.getInstanceValue(
	              aContext, aUtility, parsedFCIF, aProperties, myLogger);      
	      try
	      {
	         BisContext aContext = null;
	         aContext = BisContextHelper.setBisContext(aApplicationID, null, null,
	              correlationId, aProperties);
	      
	         aUtility.log(LogEventId.DEBUG_LEVEL_2, "aContext=<"
	              + (new BisContextBisHelper(aContext)).toString() + ">");
	        
	         SOAC soac = new SOAC(aProperties, aUtility, myLogger);
	         ServiceOrderAssignment soaObj = soac.setServiceOrderAssignment(); 
	         
	         soac.buildSoaObj(aContext, soaObj, parsedFCIF, SvcOrderConstants.MFI_TRANSACTION, aApplicationID);
	         
	         ServiceOrderAssignmentOpt aServiceOrderAssignment = new ServiceOrderAssignmentOpt();
	         aServiceOrderAssignment.theValue(soaObj);
	         ExceptionDataOpt aStatus = (ExceptionDataOpt) IDLUtil.toOpt(
	               ExceptionDataOpt.class, soac.setStatusValue(parsedFCIF));
	         aUtility.log(LogEventId.DEBUG_LEVEL_2, "aStatus=<"
	               + (new ExceptionDataOptBisHelper(aStatus)).toString() + ">");

	         ObjectPropertySeqOpt aProperties = new ObjectPropertySeqOpt();
	         aProperties.__default();      

	         soac.prepareAndForwardXml(aContext, aServiceOrderAssignment, aStatus, aProperties,
	                 parsedFCIF, SvcOrderConstants.MFI_TRANSACTION);
	         
	         aUtility.log(LogEventId.INFO_LEVEL_1, "Successful send of response.");
	      }     
	      catch(Exception e)              
	      {
	          // send email                   
	          aUtility.log(LogEventId.INFO_LEVEL_1,
						"Failed to send message. So sending email.");
	          emailSender.sendWhenReponseFailed(parsedFCIF);
	          
	      }
	      finally
	      {
	         aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodNameName);
	      }
	}    
	
/**
 * Method processModifyFacilityInfoRequest.
 * @param aContext
 * @param aService
 * @param aFCIFRequestString
 * @param aObjectProperties
 * @param aLogger
 * @throws InvalidData
 * @throws AccessDenied
 * @throws BusinessViolation
 * @throws SystemFailure
 * @throws NotImplemented
 * @throws ObjectNotFound
 * @throws DataNotFound
 */
public void processModifyFacilityInfoRequest(SOACServiceOrderResponseParser parsedFCIF)
    throws ParserSvcException, DataAccessorException, IOException, MessagingException, Exception {

    aUtility.log(LOG_DEBUG_LEVEL_1, ">RM::ModifyFacilityInfo:processModifyFacilityInfoRequest");

    try
    {
            execute(parsedFCIF);
    }
    catch(Exception ex)             
    {
        try
        {   
            sendEmail = true;
            prepareAndSendEmail(parsedFCIF);
        }
        catch(Exception e)              
        {
            aUtility.log(LogEventId.ERROR,"FAILED TO SEND EMAIL TO OMS.");
            aUtility.log(LogEventId.ERROR,"Error message [" + e.getMessage() + "]");
            aUtility.log(LogEventId.ERROR,"Error message [" + e.toString() + "]");                                  
            throw e;
        }   
        throw ex;
    }
    aUtility.log(LOG_DEBUG_LEVEL_1, "<RM::ModifyFacilityInfo:processModifyFacilityInfoRequest");
}

public void execute(SOACServiceOrderResponseParser parsedFCIF) throws  Exception {
    String myMethodName = "modifyFacilityInfo:execute()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
    String lsCircuitId = parsedFCIF.getLSCircuitID();
    
    String wireCenterCLLI8  = null;
    
    aUtility.log(LOG_INPUT_DATA, "primaryNPANXX=<" + parsedFCIF.getWireCenter() + ">");

    String primaryNPANXX = parsedFCIF.getWireCenter().toUpperCase();

    if(primaryNPANXX == null || primaryNPANXX.length() != 6 )
        throwException(
            ExceptionCode.ERR_RM_MISSING_NPANXX,
            "Missing or invalid required data: NPA NXX",
            this.getEnv("BIS_NAME"),                
            Severity.UnRecoverable);
            
    try
    {
        wireCenterCLLI8 = parsedFCIF.retrieveCLLI8().toUpperCase();
    
    }
    catch(SQLException e)
    {
        aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE WIRE CENTER CLLI8 INFO FROM THE DATABASE.");          
        aUtility.log(LogEventId.ERROR,"Error message [" + e.getMessage() + "]");
        aUtility.log(LogEventId.ERROR,"Error message [" + e.toString() + "]");          
    }
    catch(NullPointerException e)
    {
        aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE WIRE CENTER CLLI8 INFO FROM THE DATABASE.");          
        aUtility.log(LogEventId.ERROR,"Error message [" + e.getMessage() + "]");
        aUtility.log(LogEventId.ERROR,"Error message [" + e.toString() + "]");          
    }

    
    String taperCode = parsedFCIF.getTaperCode();
    
    sFttp = parsedFCIF.getFTTP();
    sFttn = parsedFCIF.getFTTN();
    
    ObjectKey[] aObjectKey = new ObjectKey[2];

    logInput(parsedFCIF);
        
        //if validation fails, send email.
    try
    {                           
        validateInput(parsedFCIF);
    }
    catch(Exception e)
    {
        try
        {   
            sendEmail = true;
            prepareAndSendEmail(parsedFCIF);
        }
        catch(Exception ex)             
        {
            aUtility.log(LogEventId.ERROR,"FAILED TO SEND EMAIL TO OMS.");
            aUtility.log(LogEventId.ERROR,"Error message [" + ex.getMessage() + "]");
            aUtility.log(LogEventId.ERROR,"Error message [" + ex.toString() + "]");                                 
        }
        throw e;
    }       
    
    SoacDefect[] aDefectList = parsedFCIF.getDefectiveCablePairList();
    boolean isUpdateGranite = false;

    try 
    {
        //update Granite and IOM BIs only if the Status Codes are FUAU or FUND or else Email.
        if(parsedFCIF.getStatusCode().equalsIgnoreCase(assignmentStatus[4])
        || parsedFCIF.getStatusCode().equalsIgnoreCase(assignmentStatus[5]))
                    isUpdateGranite = true;

        if (isUpdateGranite == true) {
            
            aUtility.log(LOG_DEBUG_LEVEL_1, "TRYING TO COMPARE AND UPDATE GRANITE.");
            
            ModifyFacilityInfoReturn aReturn = null;
            
            //2. Compare and update Granite 
            try {       
                aReturn =
                    compareAndUpdateGranite(lsCircuitId, null, wireCenterCLLI8,primaryNPANXX, taperCode, sFttn, sFttp, aDefectList,parsedFCIF,transactionName,null);
                    
            } catch (NotImplemented e) {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");
                    logException(e.aExceptionData,e.aContext);
            } catch (SystemFailure e) {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");
                    logException(e.aExceptionData,e.aContext);
            } catch (InvalidData e) {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");
                    logException(e.aExceptionData,e.aContext);
            } catch (ObjectNotFound e) {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");
                    logException(e.aExceptionData,e.aContext);
            } catch (AccessDenied e) {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");
                    logException(e.aExceptionData,e.aContext);
            } catch (BusinessViolation e) {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");
                    logException(e.aExceptionData,e.aContext);
            } catch (DataNotFound e) {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");
                    logException(e.aExceptionData,e.aContext);
            }
            catch(Exception e)
            {
                    sendEmail = true;
                    aUtility.log(LogEventId.ERROR,"FAILED TO COMPARE AND UPDATE GRANITE.");                     
                    aUtility.log(LogEventId.ERROR,"Error message [" + e.getMessage() + "]");
                    aUtility.log(LogEventId.ERROR,"Error message [" + e.toString() + "]");                              
            }

            setVLANAffecting(aReturn);      
                    
            //3. Update IOM BIS             
            if(vlanIdAffecting == true)
                    updateIpProductData(vlanIdAffecting, parsedFCIF);
            //now check if there where any errors...using sendEmail status..
            //if(sendEmail == true)
                try
                {   
                    prepareAndSendEmail(parsedFCIF);
                }
                catch(Exception ex)             
                {
                    aUtility.log(LogEventId.ERROR,"FAILED TO SEND EMAIL TO OMS.");
                    aUtility.log(LogEventId.ERROR,"Error message [" + ex.getMessage() + "]");
                    aUtility.log(LogEventId.ERROR,"Error message [" + ex.toString() + "]");                                 
                }   

        } else {
            //1.Send e-mail
                try
                {   
                    sendEmail = true;
                    prepareAndSendEmail(parsedFCIF);
                }
                catch(Exception ex)             
                {
                    aUtility.log(LogEventId.ERROR,"FAILED TO SEND EMAIL TO OMS.");
                    aUtility.log(LogEventId.ERROR,"Error message [" + ex.getMessage() + "]");
                    aUtility.log(LogEventId.ERROR,"Error message [" + ex.toString() + "]");                                 
                }

        }
    } catch (Exception e) {

        try
        {   
            sendEmail = true;
            prepareAndSendEmail(parsedFCIF);
        }
        catch(Exception ex)             
        {
            aUtility.log(LogEventId.ERROR,"FAILED TO SEND EMAIL TO OMS.");
            aUtility.log(LogEventId.ERROR,"Error message [" + ex.getMessage() + "]");
            aUtility.log(LogEventId.ERROR,"Error message [" + ex.toString() + "]");                                 
        }
        aUtility.log(LOG_DEBUG_LEVEL_2, e.getMessage());
        aUtility.log(LOG_DEBUG_LEVEL_2, e.toString());
        throw e;
    }
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
}

public ModifyFacilityInfoReturn compareAndUpdateGranite(
    String lsCircuitId,
    CompositeObjectKey aBAN,
    String wireCenter,
    String primaryNPANXX,       
    String taperCode,
    Fttn sFttn,
    Fttp sFttp,
    SoacDefect[] aDefectList,
    SOACServiceOrderResponseParser parsedFCIF,
    String transactionName,
    String oldTRE)
    throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound {

    String myMethodName = "modifyFacilityInfo:compareAndUpdateGranite()";       
            aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);

    //2. get value from Granite

    RetrieveCustomerTransportInfo aRcti = new RetrieveCustomerTransportInfo(aUtility, getPROPERTIES());     

    RetrieveCustomerTransportInfoReturn aRctiReturn = new RetrieveCustomerTransportInfoReturn();
    
    ModifyFacilityInfoReturn aReturn = null;

    //try to retrieve info from Grante using RCTI, if exception then set sendEmail to true and return null.
    try {

        aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");           
        if(lsCircuitId != null  && lsCircuitId.trim().length() != 0) //with circuit ID
        {
            aRctiReturn =
                aRcti.retrieveNoLFACS(
                    aContext,
                    (StringOpt) IDLUtil.toOpt(StringOpt.class, lsCircuitId),
                    aBAN,
                    (StringOpt) IDLUtil.toOpt(StringOpt.class, wireCenter),
                    (StringOpt) IDLUtil.toOpt(StringOpt.class, primaryNPANXX),
                    null,
                    null,
                    null,
                    null);
        }
        else //with TRE and CLLI8
        {
            aRctiReturn =
                aRcti.retrieveNoLFACS(
                    aContext,
                    (StringOpt) IDLUtil.toOpt(StringOpt.class, lsCircuitId),
                    aBAN,
                    (StringOpt) IDLUtil.toOpt(StringOpt.class, wireCenter),
                    (StringOpt) IDLUtil.toOpt(StringOpt.class, primaryNPANXX),
                    null,
                    null,
                    null,
                    oldTRE);
            
            lsCircuitId =   aRctiReturn.aCustomerTransportId;
            if(aMI != null)
                aMI.setCircuitNumber(lsCircuitId);
            
        }
    } catch (NotImplemented e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            logException(e.aExceptionData,e.aContext);
            return null;    
    } catch (SystemFailure e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            logException(e.aExceptionData,e.aContext);
            return null;    
    } catch (InvalidData e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            logException(e.aExceptionData,e.aContext);
            return null;    
    } catch (ObjectNotFound e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            logException(e.aExceptionData,e.aContext);
            return null;    
    } catch (AccessDenied e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            logException(e.aExceptionData,e.aContext);
            return null;    
    } catch (BusinessViolation e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            logException(e.aExceptionData,e.aContext);
            return null;    
    } catch (DataNotFound e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            logException(e.aExceptionData,e.aContext);
            return null;    
    }
    catch(Exception e)
    {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO RETRIEVE CUSTOMER TRANSPORT INFO FROM GRANITE.");
            aUtility.log(LogEventId.ERROR,"Error message [" + e.getMessage() + "]");
            aUtility.log(LogEventId.ERROR,"Error message [" + e.toString() + "]");  
            return null;                                
    }
            

    aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY RETRIEVED CUSTOMER TRANSPORT INFO FROM GRANITE.");

    String gTaperCode = null;
    try {
        gTaperCode = getStringOptObjectValueWithOutException(aRctiReturn.aTaperCode);
    } catch (org.omg.CORBA.BAD_OPERATION e) {
        //Null value in Opt.
        //Optional field, don't need to set.
    }


    boolean isFttn = false;
    boolean isFttp = false;     
    
    Fttp tempFttp = null;
    Fttn tempFttn = null;
    
    try
    {   
        tempFttp = aRctiReturn.aNetworkTypeChoice.aFttp();

    }
    catch(Exception e){}
    try
    {
        tempFttn = aRctiReturn.aNetworkTypeChoice.aFttn();          
    }
    catch(Exception e){}    

    if (sFttn != null) 
            this.sFttn = sFttn;
    if (sFttp != null) 
            this.sFttp = sFttp;

            
    if (tempFttp != null) 
            gFttp = aRctiReturn.aNetworkTypeChoice.aFttp();
    if (tempFttn != null) 
            gFttn = aRctiReturn.aNetworkTypeChoice.aFttn();


    com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.ModifyFacilityInfo aModifyFacilityInfo =
        new com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.ModifyFacilityInfo(aUtility, getPROPERTIES());

    boolean isUpdate = false;

    if (sFttn != null) {

    //For Switfh FTTN
    
        if(oldTRE != null  && oldTRE.trim().length() != 0 )
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH OLD AND NEW TRE INFO.");
                    
            //Try setting the lsCircuitId to empty string if it's null, other wise jaxb error.
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;
            //try to update granite
            //if we fail to update Granite, Exception or return null.
            Fttn tFttn =new Fttn(sFttn.aDSLAM,null);
                aReturn =
                    aModifyFacilityInfo.sendRequest(
                        aContext,
                        primaryNPANXX,
                        lsCircuitId,
                        null,
                        tFttn,
                        null,
                        true,
                        aDefectList,
                        oldTRE);
            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");                              
            return aReturn;             
        }
            
        
        isFttn = true;
        String gIBP,sIBP,gICN,sICN,gICP,sICP,sOBP,gOBP,gOPC,sOPC,gTId,sTId,sTty,gTty,sTloc,gTloc;

        CopperSegment[] gCSeg = getCopperSegmentArrayValueWithOutException(gFttn);          
        int gFttnNumSegs = gCSeg != null ? gCSeg.length : 0;
        
        CopperSegment[] sCSeg = getCopperSegmentArrayValueWithOutException(sFttn);
        int sFttnNumSegs = sCSeg != null ? sCSeg.length : 0;


        // if number of GRANITE segments = 0 OR
        // if number of SOAC segments < number of GRANITE segments
        // send an e-mail, GRANITE will not be updated
        if (gFttnNumSegs == 0 || sFttnNumSegs < gFttnNumSegs) {
            sendEmail = true;
            aUtility.log(LogEventId.INFO_LEVEL_1,"Did not receive any or match the number of segments from Granite.");
            aUtility.log(LogEventId.INFO_LEVEL_1,"NO UPDATES SENT TO GRANITE.");                        
            return null;    
        }
/*
        //if num segmetns from SOAC is more then update granite. 
        //if equal then try to see if anything is different, if so update granite.
        if(sFttnNumSegs > gFttnNumSegs || sFttnNumSegs != gFttnNumSegs )
            isUpdate = true;
*/              
        // if number of SOAC segments = number of GRANITE segments OR
        // if number of SOAC segments > number of GRANITE segments
        // compare the segment information, if different, GRANITE will be updated with SOAC segments
        for (int i = 0; i < gFttnNumSegs && isUpdate == false; i++) {
            
                CopperSegment sCS = getCopperSegmentArrayObjectValueWithOutException(sFttn, (sFttnNumSegs - gFttnNumSegs) + i);
                CopperSegment gCS = getCopperSegmentArrayObjectValueWithOutException(gFttn,i);

                sIBP = getStringOptObjectValueWithOutException(sCS != null ? sCS.aInBindingPost : null);
                gIBP = getStringOptObjectValueWithOutException(gCS != null ? gCS.aInBindingPost : null);
        
                sICN = getStringOptObjectValueWithOutException(sCS != null ? sCS.aInCableName : null);
                gICN = getStringOptObjectValueWithOutException(gCS != null ? gCS.aInCableName : null);          
                    
                sICP = getStringOptObjectValueWithOutException(sCS != null ? sCS.aInCablePair : null);
                gICP = getStringOptObjectValueWithOutException(gCS != null ? gCS.aInCablePair : null);                      
                    
                sOBP = getStringOptObjectValueWithOutException(sCS != null ? sCS.aOutBindingPost : null);
                gOBP = getStringOptObjectValueWithOutException(gCS != null ? gCS.aOutBindingPost : null);
                    
                sOPC = getStringOptObjectValueWithOutException(sCS != null ? sCS.aOutPairColor : null);
                gOPC = getStringOptObjectValueWithOutException(gCS != null ? gCS.aOutPairColor : null);
                    
                sTId = getStringOptObjectValueWithOutException(sCS != null ? sCS.aTerminalId : null);
                gTId = getStringOptObjectValueWithOutException(gCS != null ? gCS.aTerminalId : null);

                if((sIBP != null && !sIBP.equals(gIBP)) 
                ||(sICN != null && !sICN.equals(gICN))
                ||(sICP != null && !sICP.equals(gICP))
                ||(sOBP != null && !sOBP.equals(gOBP))
                ||(sOPC != null && !sOPC.equals(gOPC))
                ||(sTId != null && !sTId.equals(gTId))) 
                {
                    //call mfi with both segment and nonsegment info
                    isUpdate = true;
                }
                gIBP = null;sIBP = null;gICN = null;sICN = null;gICP = null;sICP = null;
                sOBP = null;gOBP = null;gOPC = null;sOPC = null;gTId = null;sTId = null;
        }
        //the above is only true when the num segs from Granite is less or equals to num segs from SOAC.
        if (isUpdate == true) {     

            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH SEGMENT INFO.");
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;                                           
            //try to update granite
            //if we fail to update Granite, Exception or return null.

                aReturn =
                    aModifyFacilityInfo.sendRequest(
                        aContext,
                        primaryNPANXX,
                        lsCircuitId,
                        taperCode,
                        sFttn,
                        null,
                        true,
                        aDefectList,
                        oldTRE);
            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");                              
            return aReturn;
        }

        //now check to see if non segmented info...         

        //i have to do this so that i don't raise null pointer exception        
        
        boolean taperCodeDifferent = isTaperCodeDifferent(taperCode,gTaperCode);
        
        boolean dslamIdDifferent   = isDslamIdDifferentForFTTN(sFttn,gFttn);

        //because of the above code...there wont be any nullpointer exceptions below
        //If segments are equal then check taper and dslam for FTTN 

        //update with taper code
        if(taperCodeDifferent && !dslamIdDifferent)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH TAPER CODE.");
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;           
            //call mfi to update tapercode 
            Fttn tFttn =new Fttn(null,null);                
            //if we fail to update Granite, Exception or return null.                                       
            aReturn =
                aModifyFacilityInfo.sendRequest(
                    aContext,
                    primaryNPANXX,
                    lsCircuitId,
                    taperCode,
                    tFttn,
                    null,
                    false,
                    aDefectList,
                    oldTRE);

            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");                              
            return aReturn;                     
        }
        //call mfi to update DSLAM 
        //if we fail to update Granite, Exception or return null.       
        else if(!taperCodeDifferent && dslamIdDifferent)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH DSLAM ID INFO.");       
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;   
            //change the DSLAM ID
            Fttn tFttn =new Fttn(sFttn.aDSLAM,null);
                aReturn =
                aModifyFacilityInfo.sendRequest(
                    aContext,
                    primaryNPANXX,
                    lsCircuitId,
                    null,
                    tFttn,
                    null,
                    false,
                    aDefectList,
                    oldTRE);

            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");                              
            return aReturn;
        }
        else if(taperCodeDifferent && dslamIdDifferent)// both Taper Code and DSLAM ID will be updated.
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH TAPER CODE AND DSLAM ID INFO.");                
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;
            //change the DSLAM ID
            Fttn tFttn =new Fttn(sFttn.aDSLAM,null);
                aReturn =
                aModifyFacilityInfo.sendRequest(
                    aContext,
                    primaryNPANXX,
                    lsCircuitId,
                    taperCode,
                    tFttn,
                    null,
                    false,
                    aDefectList,
                    oldTRE);

            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");
            return aReturn;
        }
        else //only compared and did not update Granite.
        {
            sendEmail = true;
            aUtility.log(LogEventId.INFO_LEVEL_1,"NO UPDATES SENT TO GRANITE.");                                        
        return aReturn;
        }
        
    } // if isFttn

    else if (sFttp != null) {
        isFttp = true;
        String gTid,gTtype,gNport,gAport,gIFcable,gIFstrand,sTid,sTtype,sNport,sAport,sIFcable,sIFstrand ;

        FiberSegment[] gFSeg =  getFiberSegmentArrayValueWithOutException(gFttp);           
        int gFttpSegNums = gFSeg != null ? gFSeg.length : 0;
        
        FiberSegment[] sFSeg =  getFiberSegmentArrayValueWithOutException(sFttp);
        int sFttpNumSegs = sFSeg != null ? sFSeg.length : 0;


        //did not receive any segments or more segments from granite..

/*          if(gFttpSegNums == 0 ||sFttpNumSegs < gFttpSegNums  )
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"Did not receive any or received more number of segments from Granite.");                                          
        }
*/

        // if number of GRANITE segments = 0 OR
        // if number of SOAC segments < number of GRANITE segments
        // send an e-mail, GRANITE will not be updated
        if (gFttpSegNums == 0 || sFttpNumSegs < gFttpSegNums) {
            sendEmail = true;
            aUtility.log(LogEventId.INFO_LEVEL_1,"Did not receive any or received more number of segments from Granite.");
            aUtility.log(LogEventId.INFO_LEVEL_1,"NO UPDATES SENT TO GRANITE.");                                        
            return null;    
        }

        // if number of SOAC segments = number of GRANITE segments OR
        // if number of SOAC segments > number of GRANITE segments
        // compare the segment information, if different, GRANITE will be updated with SOAC segments
        for (int i = 0; i < gFttpSegNums; i++) {                        
            FiberSegment gFS = getFiberSegmentArrayObjectValueWithOutException(gFttp, i);
            FiberSegment sFS = getFiberSegmentArrayObjectValueWithOutException(sFttp, (sFttpNumSegs - gFttpSegNums) + i);

            gTid = getStringOptObjectValueWithOutException(gFS != null ? gFS.aTerminalId : null);
            sTid = getStringOptObjectValueWithOutException(sFS != null ? sFS.aTerminalId : null);
        
            gTtype = getStringOptObjectValueWithOutException(gFS != null ? gFS.aTerminalType : null);
            sTtype = getStringOptObjectValueWithOutException(sFS != null ? sFS.aTerminalType : null);           
                    
            gNport = getStringOptObjectValueWithOutException(gFS != null ? gFS.aNetworkPort : null);
            sNport = getStringOptObjectValueWithOutException(sFS != null ? sFS.aNetworkPort : null);                        
                    
            gAport = getStringOptObjectValueWithOutException(gFS != null ? gFS.aAccessPort : null);
            sAport = getStringOptObjectValueWithOutException(sFS != null ? sFS.aAccessPort : null);
                    
            gIFcable = getStringOptObjectValueWithOutException(gFS != null ? gFS.aInFiberCable : null);
            sIFcable = getStringOptObjectValueWithOutException(sFS != null ? sFS.aInFiberCable : null);
                    
            gIFstrand = getStringOptObjectValueWithOutException(gFS != null ? gFS.aInFiberStrand : null);
            sIFstrand = getStringOptObjectValueWithOutException(sFS != null ? sFS.aInFiberStrand : null);

                        
            if ((sTid != null && !sTid.equalsIgnoreCase(gTid))
                || (sTtype != null && !sTtype.equalsIgnoreCase(gTtype))
                || (sNport != null && !sNport.equalsIgnoreCase(gNport))
                || (sAport != null && !sAport.equalsIgnoreCase(gAport))
                || (sIFcable != null && !sIFcable.equalsIgnoreCase(gIFcable))
                || (sIFstrand != null && !sIFstrand.equalsIgnoreCase(gIFstrand))) {
                //call mfi with both segment and nonsegment info
                isUpdate = true;
            }
            gTid = null; gTtype = null;gNport = null;gAport = null;gIFcable = null;gIFstrand = null;
            sTid = null; sTtype = null;sNport = null;sAport = null;sIFcable = null;sIFstrand = null;
        }

        if (isUpdate == true) {
            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH SEGMENT INFO.");
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;

            aReturn =
                aModifyFacilityInfo.sendRequest(
                    aContext,
                    primaryNPANXX,
                    lsCircuitId,
                    taperCode,
                    null,
                    sFttp,
                    true,
                    aDefectList,
                    oldTRE);


            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");                      

            return aReturn;
        }

        //now check to see if non segmented info...     
        
        boolean taperCodeDifferent = isTaperCodeDifferent(taperCode,gTaperCode);
        boolean nonSegmentInfoDifferent = isNonSegmentInfoDifferentForFTTP(sFttp,gFttp);

        // if segments equal then check nonsegment info
        if (taperCodeDifferent && !nonSegmentInfoDifferent) 
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH ONLY TAPER CODE.");
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;               
            Fttp tFttp =new Fttp(null,null,null);
            //call mfi to update tapercode no other info
            aReturn =
                aModifyFacilityInfo.sendRequest(
                    aContext,
                    primaryNPANXX,
                    lsCircuitId,
                    taperCode,
                    null,
                    tFttp,
                    false,
                    aDefectList,
                    oldTRE);


            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");
            return aReturn;         

        } else if (!taperCodeDifferent && nonSegmentInfoDifferent) {
            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH ONLY OLT and ONT INFO.");               
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;               
            //call mfi with all nonsegment info no segment info need to send
            Fttp tFttp =new Fttp(sFttp.aOpticalNetworkTerminal,sFttp.aOpticalLineTerminal,null);
            
            aReturn =
                aModifyFacilityInfo.sendRequest(
                    aContext,
                    primaryNPANXX,
                    lsCircuitId,
                    null,
                    null,
                    tFttp,
                    false,
                    aDefectList,
                    oldTRE);


            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");                      
            return aReturn;                         

        } else if (taperCodeDifferent && nonSegmentInfoDifferent){

            aUtility.log(LogEventId.INFO_LEVEL_1,"TRYING TO UPDATE GRANITE WITH TAPER CODE AND OLT and ONT INFO.");             
            lsCircuitId = lsCircuitId == null ? "" : lsCircuitId;
            //call mfi with all nonsegment info no segment info need to send
            Fttp tFttp =new Fttp(sFttp.aOpticalNetworkTerminal,sFttp.aOpticalLineTerminal,null);
            
            aReturn =
                aModifyFacilityInfo.sendRequest(
                    aContext,
                    primaryNPANXX,
                    lsCircuitId,
                    taperCode,
                    null,
                    tFttp,
                    false,
                    aDefectList,
                    oldTRE);

                    
            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");
            return aReturn; 
        } else {
            sendEmail = true;
            aUtility.log(LogEventId.INFO_LEVEL_1,"NO UPDATES SENT TO GRANITE.");                    
            return aReturn;             
            
        }
        

    } //if isFttp
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
    return aReturn;

}



public void setVLANAffecting(ModifyFacilityInfoReturn aReturn)
{
    String myMethodName = "modifyFacilityInfo:setVLANAffecting()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);        
    
    
    String networkType = null;
    if(gFttn != null)
        networkType = NetworkTypeValues.FTTN;
    else if (gFttp != null)
        networkType = NetworkTypeValues.FTTP;
                
    String tAID = null;
    String tVlan = null;
    
    String t1AID = null;
    String t1Vlan = null;
    
    
    String tOntAid = null;
    String tOltTid = null;
    
    String t1OntAid = null;
    String t1OltTid = null;

    if(networkType != null && networkType.equalsIgnoreCase(NetworkTypeValues.FTTN))
    {
        try 
        {
                tAID = getStringOptObjectValueWithOutException(gFttn.aDSLAM.theValue().aId);
                aUtility.log(LogEventId.INFO_LEVEL_1,"DSLAM aId from Granite (RCTI Return):"+tAID);             
        }catch(Exception e)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"DSLAM aId from Granite (RCTI Return):"+tAID);
        }
        try
        {
            tVlan = getStringOptObjectValueWithOutException(gFttn.aDSLAM.theValue().aVLANId);
            aUtility.log(LogEventId.INFO_LEVEL_1,"VLAN ID from Granite (RCTI Return):"+tVlan);                      
        }catch(Exception e)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"VLAN ID from Granite (RCTI Return):"+tVlan);          
        }
        
        try 
        {
                t1AID = aReturn.getFttnDslamName().trim();
                aUtility.log(LogEventId.INFO_LEVEL_1,"DSLAM aId from Granite (MFI Return):"+t1AID);                             
        }catch(Exception e)
        {
                aUtility.log(LogEventId.INFO_LEVEL_1,"DSLAM aId from Granite (MFI Return):"+t1AID);             
        }
        try
        {
            t1Vlan = aReturn.getFttnVlanId().trim();
            aUtility.log(LogEventId.INFO_LEVEL_1,"VLAN ID from Granite (MFI Return):"+t1Vlan);                          
        }catch(Exception e)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"VLAN ID from Granite (MFI Return):"+t1Vlan);                      
        }
    }


    if(networkType != null && networkType.equalsIgnoreCase(NetworkTypeValues.FTTP))
    {
        try 
        {
                tOntAid = getStringOptObjectValueWithOutException(gFttp.aOpticalNetworkTerminal.theValue().aAccessId);
                aUtility.log(LogEventId.INFO_LEVEL_1,"ONT AID from Granite (RCTI Return):"+tOntAid);                
        }catch(Exception e)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"ONT AID from Granite (RCTI Return):"+tOntAid);
        }
        try
        {
            tOltTid = getStringOptObjectValueWithOutException(gFttp.aOpticalLineTerminal.theValue().aEquipmentTargetId);
            aUtility.log(LogEventId.INFO_LEVEL_1,"OLT TID from Granite (RCTI Return):"+tOltTid);                        
        }catch(Exception e)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"OLT TID from Granite (RCTI Return):"+tOltTid);            
        }
        
        try 
        {
                t1OntAid = getStringOptObjectValueWithOutException(sFttp.aOpticalNetworkTerminal.theValue().aAccessId);
                aUtility.log(LogEventId.INFO_LEVEL_1,"ONT AID from SOAC (SOAC Request):"+t1OntAid);                             
        }catch(Exception e)
        {
                aUtility.log(LogEventId.INFO_LEVEL_1,"ONT AID from SOAC (SOAC Request):"+t1OntAid);             
        }
        try
        {
            t1OltTid = getStringOptObjectValueWithOutException(sFttp.aOpticalLineTerminal.theValue().aEquipmentTargetId);
            aUtility.log(LogEventId.INFO_LEVEL_1,"OLT TID from SOAC (SOAC Request):"+t1OltTid);                         
        }catch(Exception e)
        {
            aUtility.log(LogEventId.INFO_LEVEL_1,"OLT TID from SOAC (SOAC Request):"+t1OltTid);                     
        }

    }
                
        
            if (aReturn != null && networkType != null && networkType.equalsIgnoreCase(NetworkTypeValues.FTTN)) //Fttn case
            {
                try {
                    if(tAID != null && tVlan != null) // required for FTTN case.
                        if ((!tAID.equalsIgnoreCase(t1AID))|| (!tVlan.equalsIgnoreCase(t1Vlan))) 
                        {
                            vlanIdAffecting = true;
                            aUtility.log(LogEventId.INFO_LEVEL_1,"VLAN Affecting ID is set to TRUE.");
                        }
                } catch (org.omg.CORBA.BAD_OPERATION e) {
                    //Null Opt value
                    //vlanIdAffecting = true;
                } catch (NullPointerException ne) {
                    //null value
                    //vlanIdAffecting = true;
                }
            } 
            else if (networkType != null && networkType.equalsIgnoreCase(NetworkTypeValues.FTTP))
            {
                try {
                    if(t1OntAid != null && t1OltTid != null) // required for FTTP case.
                        if ((!t1OntAid.equalsIgnoreCase(tOntAid))|| (!t1OltTid.equalsIgnoreCase(tOltTid))) 
                        {
                            vlanIdAffecting = true;
                            aUtility.log(LogEventId.INFO_LEVEL_1,"VLAN Affecting ID is set to TRUE.");
                        }
                } catch (org.omg.CORBA.BAD_OPERATION e) {
                    //Null Opt value
                    //vlanIdAffecting = true;
                } catch (NullPointerException ne) {
                    //null value
                    //vlanIdAffecting = true;
                }
            }
            
        if(!vlanIdAffecting)
        {   
            sendEmail = true;
            aUtility.log(LogEventId.INFO_LEVEL_1,"VLAN Affecting ID is not set to TRUE.");
        }

    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);            
}

public boolean getVlanAffecting()
{
    return vlanIdAffecting; 
}

public boolean getSendEmailStatus()
{
    return sendEmail;   
}
public boolean getSendEmailStatusBBNMS()
{
    return sendEmailBBNMS;   
}

public void setSendEmailStatus(boolean status)
{
        sendEmail = status;
}

public void setSendEmailStatusBBNMS(boolean status)
{
        sendEmailBBNMS = status;
}

public void updateIpProductData(boolean aVlanIdAffecting, SOACServiceOrderResponseParser parsedFCIF) {

    String myMethodName = "modifyFacilityInfo:updateIpProductData()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);        
    IomClient aIomClient = null;
    
    UpdateIpProductDataReturn result ;

    try {
        IomipBIS iomBean = getIOMBean();        

        Products[] aProducts = new Products[1];
        
        //convert network type RGPN to RGPON
        String aNetworkType = parsedFCIF.getNetworkType().trim();
        
        if(aNetworkType.equalsIgnoreCase(SvcOrderConstants.RGPN_NETWORK))
            aNetworkType = NetworkTypeValues.RGPON;
        
        //Lightspeed Circuit ID
        String aCustomerTransportId = parsedFCIF.getLSCircuitID();
        aProducts[0] = new Products();
        aProducts[0].aName = "FacilitiesData";
        aProducts[0].aValue = parsedFCIF.getAssgnSectionString();

        EiaDate date = new EiaDate();
        String d = parsedFCIF.getDueDate();
        date.aYear =(short) Integer.parseInt("20"+ d.substring(0,2));
        date.aMonth =(short) Integer.parseInt(d.substring(2,4));
        date.aDay =(short)Integer.parseInt( d.substring(4));                        

        aUtility.log(LOG_DEBUG_LEVEL_1, "Input Data to IOM is as follows.");
        aUtility.log(LOG_INPUT_DATA, "Customer Transport ID=<" + aCustomerTransportId + ">");
        aUtility.log(LOG_INPUT_DATA, "Order Number=<" + parsedFCIF.getOMSOrderNum().trim() + ">");
        aUtility.log(LOG_INPUT_DATA, "Order Action ID=<" + parsedFCIF.getOMSOrderActionID().trim() + ">");
        aUtility.log(LOG_INPUT_DATA, "Network Type=<" + aNetworkType + ">");
        aUtility.log(LOG_INPUT_DATA, "VLAN ID Affecting=<" + aVlanIdAffecting + ">");

        BooleanOpt vlanBoolOpt = new BooleanOpt();
        vlanBoolOpt.theValue(aVlanIdAffecting);

        aUtility.log(
            LogEventId.REMOTE_CALL,
            (String) getEnv("IOM_IIOP_HOST"),
            (String) getEnv("IOM_BIS_NAME"),
            (String) getEnv("IOM_PROVIDER_URL"),
            "ModifyFacilityInfo::updateIpProductData ==> Executing Remote Call");


        result =
            iomBean.updateIpProductData(
                aContext,
                (StringOpt) IDLUtil.toOpt(StringOpt.class, parsedFCIF.getOMSOrderNum().trim(),true),
                (StringOpt) IDLUtil.toOpt(StringOpt.class, parsedFCIF.getOMSOrderActionID().trim(),true),
                (StringOpt) IDLUtil.toOpt(aNetworkType,true),
                vlanBoolOpt,
                (StringOpt) IDLUtil.toOpt(parsedFCIF.getLSCircuitID().trim(),true),
                aProducts,
                (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, date,true));

            aUtility.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED IOM-BIS.");                  

    } catch (NotImplemented e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");
            logException(e.aExceptionData,e.aContext);
    } catch (SystemFailure e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");
            logException(e.aExceptionData,e.aContext);
    } catch (InvalidData e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");
            logException(e.aExceptionData,e.aContext);
    } catch (ObjectNotFound e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");
            logException(e.aExceptionData,e.aContext);
    } catch (AccessDenied e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");
            logException(e.aExceptionData,e.aContext);
    } catch (BusinessViolation e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");
            logException(e.aExceptionData,e.aContext);
    } catch (DataNotFound e) {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");
            logException(e.aExceptionData,e.aContext);
    }
    catch(Exception e)
    {
            sendEmail = true;
            aUtility.log(LogEventId.ERROR,"FAILED TO UPDATE IOM-BIS.");     
            aUtility.log(LogEventId.ERROR,"Error message [" + e.getMessage() + "]");
            aUtility.log(LogEventId.ERROR,"Error message [" + e.toString() + "]");                              
    }

    finally {
        aUtility.log(
            LogEventId.REMOTE_RETURN,
            (String) getEnv("IOM_IIOP_HOST"),
            (String) getEnv("IOM_BIS_NAME"),
            (String) getEnv("IOM_PROVIDER_URL"),
            "ModifyFacilityInfo::updateIpProductData ==> Executed Remote Call");            
    }
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);                    
}


public IomipBIS getIOMBean() throws DataNotFound, ObjectNotFound, NotImplemented, SystemFailure, BusinessViolation, AccessDenied, InvalidData, Exception
{

        IomClient aIomClient = null;
        IomipBIS iomBean = null;

    String initialContextFile = (String) this.getPROPERTIES().get("INITIAL_CONTEXT_PROPERTIES_FILE");

        if (aIomClient == null) {
            aIomClient =
                new IomClient(
                    getEnv("IOM_PROVIDER_URL"),
                    getEnv("IOM_BIS_NAME"),
                    getEnv("BIS_NAME"),
                    initialContextFile);
        }

        aUtility.log(
            LogEventId.REMOTE_CALL,
            (String) getEnv("IOM_IIOP_HOST"),
            (String) getEnv("IOM_BIS_NAME"),
            (String) getEnv("IOM_PROVIDER_URL"),
            "ModifyFacilityInfo::getIOMBean");
        
        try {
            iomBean = aIomClient.getIomConnection(aContext, aUtility, "IOMException","Iom Connection failed");          }
        catch(Exception e){
            //failed to get the reference now..try with the secondary provider url for ls2...
            aUtility.log(LOG_DEBUG_LEVEL_1, "Failed to Retrieved IOM Bean Reference.");
            
            throw e;    
        }
        finally {

            aUtility.log(
                LogEventId.REMOTE_RETURN,
                (String) getEnv("IOM_IIOP_HOST"),
                (String) getEnv("IOM_BIS_NAME"),
                (String) getPROPERTIES().get("IOM_PROVIDER_URL"),
                "ModifyFacilityInfo::getIOMBean");
            
        }
        aUtility.log(LOG_DEBUG_LEVEL_1, "Retrieved IOM Bean Reference.");
        
    
return iomBean;

}

/**
* Method prepareAndSendEmail.
* @throws MessagingException
* @throws IOException
*/
private void prepareAndSendEmail(SOACServiceOrderResponseParser parsedFCIF) throws MessagingException, IOException {
    String myMethodName = "ModifyFacilityInfo::prepareAndSendEmail()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    //Email Subject ...
    String aMailSubject = "Lightspeed Assignment Problem";

    OMSEmailHelper aOMSEmailHelper = new OMSEmailHelper(aUtility, getPROPERTIES());
    
    if(sendEmail == true && emailSent == false)
    {
        String aEmailBody = buildEmailMsgText(parsedFCIF);

        aOMSEmailHelper.prepareAndSendEmail(aMailSubject, aEmailBody);
        emailSent = true;           
    }
    //by this time the email is sent. 

    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
}

/**
 * Method buildEmailMsgText.
 * @return String
 */
private String buildEmailMsgText(SOACServiceOrderResponseParser parsedFCIF) {
    String myMethodName = "ModifyFacilityInfo::buildEmailMsgText()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    //Some other Info
    String aEmailSt1 =
        "A pending service order, or an existing service, may require your attention.\n\n\n"
            + "LFACS reports that assignments may have changed on:\n";

    //Email Property Names
    String aOMSOrdNumString =       "OMS Order Number           :   [";
    String aDueDateString =         "Due Date               :   [";
    String aOMSOrdActionIDString =  "OMS Order Action           :   [";
    String aCircuitIdString =       "LS Circuit ID          :   [";
    String aTNString =              "TN                 :   [";
    String aSOACStatusTypeString =  "SOAC Status Type           :   [";
    String aSOACStatusDescString =  "SOAC Status Description        :   [";
    //Function Type or passType
    String aOrderActionTypeString = "Pass Type              :   [";
    String aSOACSvsOrdNumString =   "SOAC Service Order Number  :   [";
    String aAsgnLinesString =       "Current Assignment Section :   \n[";
    
    String aNetworkTypeString =     "NetworkType                :   [";
    String aVLANAffectingString=    "VLAN Affecting         :   [";
    String aResendIndicatorString       =   "Resend Indicator           :   [";

    String aErrorSectionString =    "Error Section          :   [";

    String aEmailSt2 =
        "\n\nPlease contact the appropriate assignment center and if necessary \n"
            + "issue a change order, or a supplement on the pending order,\n"
            + "to resolve this problem."
            + "If there is no pending order, the change order you issue should be today.";

    String omsOrderId = null;
    String dueDate = null;
    String omsOrderActionId = null;
    String lsCircuitId = null;
    String tn = null;
    String soacStatusType = null;
    String soacStatusDesc = null;
    String soacSvcOrderNum = null;
    String asignmentLine = null;
    String orderActionType = null;
    String aErrorSection = null;
    
    //LS2
    String aNetworkType = null;
    String aVlanAffecting = null;
    String aResendIndicator = null;

    String temp = null;
    String[] tempArray = new String[100];

    temp = parsedFCIF.getOMSOrderNum();
    if ((temp != null) && (!temp.trim().equals("")))
        omsOrderId = temp;
    else
        omsOrderId = NA_STRING;

    temp = parsedFCIF.getDueDate();
    if ((temp != null) && (!temp.trim().equals("")))
        dueDate = temp;
    else
        dueDate = NA_STRING;

    temp = parsedFCIF.getTelephoneNum();
    if ((temp != null) && (!temp.trim().equals("")))
        tn = temp;
    else
        tn = NA_STRING;

    temp = parsedFCIF.getStatusCode();
    if ((temp != null) && (!temp.trim().equals("")))
        soacStatusType = temp.substring(0, 4);
    else
        soacStatusType = NA_STRING;

    temp = parsedFCIF.getSOACStatusDecr();
    if ((temp != null) && (!temp.trim().equals("")))
        soacStatusDesc = temp;
    else
        soacStatusDesc = NA_STRING;

    temp = parsedFCIF.getSOACServiceOrderNum();
    if ((temp != null) && (!temp.trim().equals("")))
        soacSvcOrderNum = temp;
    else
        soacSvcOrderNum = NA_STRING;

    tempArray = parsedFCIF.getAssgnSectionString();

    if (tempArray != null) {
        int length = 0;

        try {
            for (; length < tempArray.length; length++) {
                if (!tempArray[length].trim().equals(""))
                    if(length == 0)
                        asignmentLine = tempArray[length] + NEW_LINE;
                    else
                        asignmentLine += tempArray[length] + NEW_LINE;
            }
        } catch (NullPointerException e) {
            if (length == 0)
                asignmentLine = NA_STRING;
        }
    } else
        asignmentLine = NA_STRING;

    //call this method after getting the assignment section..
    //this is going to go round about and find out 
    //the action type using the assignment string..hence call this later.
    //see SendF1F2OrderHelper for more.
    temp = parsedFCIF.getOrderActionType();
    if ((temp != null) && (!temp.trim().equals("")))
        orderActionType = temp;
    else
        orderActionType = NA_STRING;

    temp = parsedFCIF.getExceptionMessage();
    if ((temp != null) && (!temp.trim().equals("")))
        aErrorSection = temp;
    else
        aErrorSection = NA_STRING;

    lsCircuitId = parsedFCIF.getLSCircuitID();

    omsOrderActionId = parsedFCIF.getOMSOrderActionID();
    
    //LS2
    aNetworkType = parsedFCIF.getNetworkType();
    aVlanAffecting = this.vlanIdAffecting == true  ? "Yes" : "No";
    aResendIndicator = parsedFCIF.getResendIndicator().equals("R") ? "Resend" : "NA";



    logEmailInput(
        omsOrderId,
        dueDate,
        omsOrderActionId,
        lsCircuitId,
        tn,
        soacStatusType,
        soacStatusDesc,
        soacSvcOrderNum,
        asignmentLine,
        orderActionType);

    String aEmailBody = new String();

    aEmailBody = aEmailSt1;
    aEmailBody += NEW_LINE + aOMSOrdNumString + omsOrderId + "]";
    aEmailBody += NEW_LINE + aDueDateString + dueDate + "]";
    aEmailBody += NEW_LINE + aOMSOrdActionIDString + omsOrderActionId + "]";
    aEmailBody += NEW_LINE + aCircuitIdString + lsCircuitId + "]";
    aEmailBody += NEW_LINE + aTNString + tn + "]";
    aEmailBody += NEW_LINE + aSOACStatusTypeString + soacStatusType + "]";
    aEmailBody += NEW_LINE + aSOACStatusDescString + soacStatusDesc + "]";
    aEmailBody += NEW_LINE + aOrderActionTypeString + orderActionType + "]";
    aEmailBody += NEW_LINE + aSOACSvsOrdNumString + soacSvcOrderNum + "]";
    aEmailBody += NEW_LINE + aAsgnLinesString + asignmentLine + "]";
    aEmailBody += NEW_LINE + aNetworkTypeString + aNetworkType + "]";
    aEmailBody += NEW_LINE + aVLANAffectingString + aVlanAffecting + "]";
    aEmailBody += NEW_LINE + aResendIndicatorString + aResendIndicator + "]";
    aEmailBody += NEW_LINE + aErrorSectionString + aErrorSection + "]" + NEW_LINE;

    aEmailBody += aEmailSt2;

    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);

    return aEmailBody;
}

public void validateInput(SOACServiceOrderResponseParser parsedFCIF)
    throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound {

    String myMethodName = "modifyFacilityInfo:validateInput()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
    String aBisName = aProperties.get("BIS_NAME").toString();

    ValidateHelper.validateString(
        aUtility,
        parsedFCIF.getNetworkType(),
        ExceptionCode.ERR_RM_MISSING_NETWORK_TYPE,
        "Missing required data: NETWORK TYPE",
        aBisName);

    // aCircuitId
    ValidateHelper.validateString(
        aUtility,
        parsedFCIF.getLSCircuitID(),
        ExceptionCode.ERR_RM_MISSING_CIRCUIT_ID,
        "Missing required data: aCircuitId",
        aBisName);
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);            

}

private void logEmailInput(
    String omsOrderId,
    String dueDate,
    String omsOrderActionId,
    String lsCircuitId,
    String tn,
    String soacStatusType,
    String soacStatusDesc,
    String soacSvcOrderNum,
    String asignmentLine,
    String orderActionType) {

    String myMethodName = "ModifyFacilityInfo:logEmailInput()";

    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    aUtility.log(LOG_DEBUG_LEVEL_1, "Start Input log for ModifyFacilityInfo Email Going to OMS.>>");
    try {
        aUtility.log(LOG_INPUT_DATA, "omsOrderId=<" + omsOrderId + ">");
    } catch (Exception e) {
    }

    try {
        aUtility.log(LOG_INPUT_DATA, "dueDate=<" + dueDate + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "omsOrderActionId=<" + omsOrderActionId + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "lsCircuitId=<" + lsCircuitId + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "tn=<" + tn + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "soacStatusType=<" + soacStatusType + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "soacStatusDesc=<" + soacStatusDesc + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "soacSvcOrderNum=<" + soacSvcOrderNum + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "asignmentLine=<" + asignmentLine + ">");
    } catch (Exception e) {
    }
    try {
        aUtility.log(LOG_INPUT_DATA, "orderActionType=<" + orderActionType + ">");
    } catch (Exception e) {
    }
    aUtility.log(LOG_DEBUG_LEVEL_1, "Stop Input log for ModifyFacilityInfo Email Going to OMS.<<");

}

public void logInput(SOACServiceOrderResponseParser parsedFCIF) {

    //going to only validate the required fiels i.e.
    // wire center, ls circuit id,F1 CODSLAM Port, F1,F2 cable&pairs, F2 DSLAM binding post.
    //also aOrderActionID

    String myMethodName = "ModifyFacilityInfo:logInput()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    aUtility.log(LOG_DEBUG_LEVEL_1, ">>Start Input log for ModifyFacilityInfo Request Going to Granite.");
    // log input data

    try {
        aUtility.log(LOG_INPUT_DATA, "aOrderActionID=<" + parsedFCIF.getOMSOrderActionID() + ">");
    } catch (Exception e) {
    }

    try {
        aUtility.log(LOG_INPUT_DATA, "aWireCenter=<" + parsedFCIF.getWireCenter() + ">");
    } catch (Exception e) {
    }

    try {
        aUtility.log(LOG_INPUT_DATA, "aCircuitId=<" + parsedFCIF.getLSCircuitID() + ">");
    } catch (Exception e) {
    }

    aUtility.log(LOG_DEBUG_LEVEL_1, "Stop Input log for ModifyFacilityInfo Request Going to Granite.<<");

    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);

}

/**
 * Method getCopperSegmentArrayObjectValueWithOutException.
 * @param aOpt
 * @param index
 * @return CopperSegment
 */
public CopperSegment getCopperSegmentArrayObjectValueWithOutException(Fttn aFttn, int index)
{
    String myMethodName = "modifyFacilityInfo:getCopperSegmentArrayObjectValueWithOutException()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);        
    
    CopperSegmentSeqOpt aOpt = null;
    CopperSegment x = null;
    try
    {
        aOpt= aFttn.aSegments;
        x   = aOpt.theValue()[index];           
    }
    catch(Exception e){}
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);        
return x;   
}

public String getStringOptObjectValueWithOutException(StringOpt aOpt)
{
    String myMethodName = "modifyFacilityInfo:getOStringOptObjectValueWithOutException()";      
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);        
    String x = null;
    try
    {
        x = aOpt.theValue().trim();         
    }
    catch(Exception e){}
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);        
return x;   
}

public CopperSegment[] getCopperSegmentArrayValueWithOutException(Fttn aFttn)
{
    String myMethodName = "modifyFacilityInfo:getCopperSegmentArrayObjectValueWithOutException()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    CopperSegmentSeqOpt aOpt = null;        
    CopperSegment[] x = null;
    try
    {   aOpt= aFttn.aSegments;
        x   = aOpt.theValue();          
    }
    catch(Exception e){}
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);        
return x;   
}


public FiberSegment getFiberSegmentArrayObjectValueWithOutException(Fttp aFttp, int index)
{
    String myMethodName = "modifyFacilityInfo:getFiberSegmentArrayObjectValueWithOutException()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);    

    FiberSegmentSeqOpt aOpt = null;
    FiberSegment x = null;
    try
    {
        aOpt= aFttp.aSegments;
        x   = aOpt.theValue()[index];           
    }
    catch(Exception e){}
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);        
return x;   
}


public FiberSegment[] getFiberSegmentArrayValueWithOutException(Fttp aFttp)
{
    String myMethodName = "modifyFacilityInfo:getFiberSegmentArrayValueWithOutException()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
    
    FiberSegmentSeqOpt aOpt = null;     
    FiberSegment[] x = null;
    try
    {
        aOpt= aFttp.aSegments;          
        x   = aOpt.theValue();          
    }
    catch(Exception e){}
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);        
return x;   
}



public void setMIObjectForEmailCallback(ModifyInventory anMI)
{
    aMI = anMI;
}

public void logException(ExceptionData e,BisContext b)
{
        aUtility.log(LogEventId.ERROR,"ExceptionData Code [" + e.aCode+ "]");
        aUtility.log(LogEventId.ERROR,"ExceptionData Description [" + e.aDescription + "]");
        try{    
            aUtility.log(LogEventId.ERROR,"ExceptionData Severity Value ["+e.aSeverity.theValue().value()+ "]");
            aUtility.log(LogEventId.ERROR,"ExceptionData Origination ["+e.aOrigination.theValue()+ "]");
        }catch(Exception ex){}
}

/**
 * Returns the emailSent.
 * @return boolean
 */
public boolean isEmailSent() {
    return emailSent;
}

protected boolean isTaperCodeDifferent(String sTaperCode, String gTaperCode)
{
    String myMethodName = "modifyFacilityInfo:isTaperCodeDifferent()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);        
    
    String tempSTaperCode = null;
    String tempGTaperCode = null;
    
    boolean taperCodeDifferent = false;
        
    if(sTaperCode != null && !sTaperCode.equals("null") && !sTaperCode.equals(""))
        tempSTaperCode = sTaperCode.trim();
        
    if(gTaperCode != null && !gTaperCode.equals("null") && !gTaperCode.equals(""))
        tempGTaperCode = gTaperCode.trim();                     
        
    if(tempSTaperCode != null  && !tempSTaperCode.equalsIgnoreCase(tempGTaperCode))
            taperCodeDifferent = true;

    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);    
    return taperCodeDifferent;
}



protected boolean isDslamIdDifferentForFTTN(Fttn sFttn, Fttn gFttn)
{
    String myMethodName = "modifyFacilityInfo:isDslamIdDifferentForFTTN()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);        

    String sDslamaId = null;
    String gDslamaId = null;

    boolean dslamIdDifferent = false;           
    try {
        
        if( getStringOptObjectValueWithOutException(sFttn.aDSLAM.theValue().aId) != null)
            sDslamaId = getStringOptObjectValueWithOutException(sFttn.aDSLAM.theValue().aId);
    } catch (Exception e) {
    }

    try {
        if( getStringOptObjectValueWithOutException(gFttn.aDSLAM.theValue().aId) != null)
            gDslamaId = getStringOptObjectValueWithOutException(gFttn.aDSLAM.theValue().aId);
    } catch (Exception e) {
    }

    if(sDslamaId != null && !sDslamaId.equals("null") && !sDslamaId.equals(""))
        sDslamaId = sDslamaId.trim();
    
    if(gDslamaId != null && !gDslamaId.equals("null") && !gDslamaId.equals(""))
        gDslamaId = gDslamaId.trim();

    if(sDslamaId != null && !sDslamaId.equalsIgnoreCase(gDslamaId))
        dslamIdDifferent = true;

    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);            
    return dslamIdDifferent;
    
}
/**
 * Method isNonSegmentInfoDifferent.
 * @param sFttp
 * @param gFttp
 * @return boolean
 */
private boolean isNonSegmentInfoDifferentForFTTP(Fttp sFttp, Fttp gFttp) 
{
    String myMethodName = "modifyFacilityInfo:isNonSegmentInfoDifferentForFTTP()";
    aUtility.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);        
    
    String sOLTAid = null,sOLTTid = null,sOLTModel = null,sONTAid = null, sONTId = null,sONTModel = null,sONTPort = null;
    String gOLTAid = null,gOLTTid = null,gOLTModel = null,gONTAid = null, gONTId = null,gONTModel = null,gONTPort = null;
    
    boolean isDifferent = false;
    
    try {
        sOLTAid = getStringOptObjectValueWithOutException(sFttp.aOpticalLineTerminal.theValue().aId);
    } catch (Exception e) {
    }

    try {
        gOLTAid = getStringOptObjectValueWithOutException(gFttp.aOpticalLineTerminal.theValue().aId);
    } catch (Exception e) {
    }
    
    try {
        sOLTTid = getStringOptObjectValueWithOutException(sFttp.aOpticalLineTerminal.theValue().aEquipmentTargetId);
    } catch (Exception e) {
    }
    
    try {
        gOLTTid = getStringOptObjectValueWithOutException(gFttp.aOpticalLineTerminal.theValue().aEquipmentTargetId);
    } catch (Exception e) {
    }
    
    try {
        sOLTModel = getStringOptObjectValueWithOutException(sFttp.aOpticalLineTerminal.theValue().aModelNumber);
    } catch (Exception e) {
    }

    try {
        gOLTModel = getStringOptObjectValueWithOutException(gFttp.aOpticalLineTerminal.theValue().aModelNumber);
    } catch (Exception e) {
    }
    
    try {
        sONTAid = getStringOptObjectValueWithOutException(sFttp.aOpticalNetworkTerminal.theValue().aAccessId);
    } catch (Exception e) {
    }
    
    try {
        gONTAid = getStringOptObjectValueWithOutException(gFttp.aOpticalNetworkTerminal.theValue().aAccessId);
    } catch (Exception e) {
    }
    
    try {
        sONTId = getStringOptObjectValueWithOutException(sFttp.aOpticalNetworkTerminal.theValue().aId);
    } catch (Exception e) {
    }
    
    try {
        gONTId = getStringOptObjectValueWithOutException(gFttp.aOpticalNetworkTerminal.theValue().aId);
    } catch (Exception e) {
    }

    try {
        sONTModel = getStringOptObjectValueWithOutException(sFttp.aOpticalNetworkTerminal.theValue().aModelNumber);
    } catch (Exception e) {
    }
    
    try {
        gONTModel = getStringOptObjectValueWithOutException(gFttp.aOpticalNetworkTerminal.theValue().aModelNumber);
    } catch (Exception e) {
    }

    try {
        sONTPort = getStringOptObjectValueWithOutException(sFttp.aOpticalNetworkTerminal.theValue().aPort);
    } catch (Exception e) {
    }
    
    try {
        gONTPort = getStringOptObjectValueWithOutException(gFttp.aOpticalNetworkTerminal.theValue().aPort);
    } catch (Exception e) {
    }


    if((sOLTAid != null     && !sOLTAid.equalsIgnoreCase(gOLTAid))
    ||(sOLTTid != null  && !sOLTTid.equalsIgnoreCase(gOLTTid))
    ||(sOLTModel != null    && !sOLTModel.equalsIgnoreCase(gOLTModel))
    ||(sONTAid != null  && !sONTAid.equalsIgnoreCase(gONTAid))
    ||(sONTId != null       && !sONTId.equalsIgnoreCase(gONTId))
    ||(sONTModel != null    && !sONTModel.equalsIgnoreCase(gONTModel))
    ||(sONTPort != null     && !sONTPort.equalsIgnoreCase(gONTPort)))
    {
        isDifferent = true;
    }                                       
    aUtility.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);

    return isDifferent;
    
    
}



} //end of class ModifyFacilityInfo