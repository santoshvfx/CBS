//# $Id: SOACGIServiceOrderResponseParser.java,v 1.6 2011/03/03 02:23:38 rs278j Exp $
//###############################################################################
//#
//#       Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       AT&T Services, Inc.
//#
//#       © 2007 AT&T Intellectual Property. All rights reserved.
//#
//# History :
//# Date         | Author                 | Notes
//# --------------------------------------------------------------------------
//# 10/08/2007	 Changchuan Yin			  Creation
//# 01/07/2007   Changchuan Yin           Changed to fix MQC # 81486- Save wirecenter CLLI8 to avoid another db call.
//# 09/20/2009   Julius Sembrano          AOTS 116662371 - RM-SystemFailure-00909 - Error in creating XML response if the Optional field
//#										  OrderNumber is missing in the SOACGI Response

package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import com.sbc.eia.idl.types.Severity;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.SoacWireCenterRow;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.bis.rm.utilities.NetworkTypeHelper;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponseBISMsg;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminal;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignment;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignmentOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;

public class SOACGIServiceOrderResponseParser extends TranBase 
{
private String circuitNumber = null;
//private String wireCenterCLLI8 = null;
private String primaryNPANXX = null;
private String aOrderNum = null;
private String aOrderID = null;
private String oldDslamID = null;
private String dslamID = null;
private boolean isFTTnSAI = false;
private boolean isIPDSLAMSAI = false;
private Fttn aFttn = null;
private Fttp aFttp = null;
private ServiceOrderAssignment soaObj = null;

private static String LFACS_FTTN = "CUFTTNL";
private static String LFACS_FTTP = "CUFTTPL";
private static String SWITCH_FTTN = "CUFTTNS";
private static String FTTN = NetworkTypeValues.FTTN;
private static String FTTP = NetworkTypeValues.FTTP;

private Logger myLogger = null;
private Hashtable myProperties = null;
private Utility aUtility = null;

public String transactionName = "SOACGIServiceOrderResponseParser";
public static final String SERVICE_FUNCTION_KEY = "ModifyInventoryNotification";
protected static final String RMBIS_DTN_NAME = "YICDMP";
protected static final String SOAC_RES_CLS_OF_SVC = "XR7FA";
	protected static final String SOAC_BUS_CLS_OF_SVC = "XB7FA";
	protected static final String DEFAULT_SOAC_SOP_ORIGHOST = "OMS";
	protected static final String COMPLETION_SUB_ACTION_TYPE = "PCN";
	protected static final String CANCELLATION_SUB_ACTION_TYPE = "CAN";
	protected static final String CVOIP_SOAC_REGION_IND = " ";
	protected static final String CVOIP_SOAC_TEST_ENV_FIELD = "TC";
	protected static final String CVOIP_SOAC_PRODUCTION_ENV_FIELD = "PC";
	private static String PRE_MIGRATION = "OMS";
	private static String DURING_MIGRATION ="OMS:BBNMS";
	private static String POST_MIGRATION = "BBNMS";

public SOACGIServiceOrderResponseParser(
  Hashtable properties,
  Utility utility,
  Logger aLogger) {
  PROPERTIES = properties;
  myProperties = properties;
  myLogger = aLogger;
  aUtility = utility;

}

public SOACGIServiceOrderResponseParser(Hashtable properties, Logger aLogger,
	    com
	        .sbc
	        .eia
	        .bis
	        .embus
	        .service
	        .soacgi
	        .LFACS
	        .fttp
	        .interfaces
	        .impl
	        .EnvelopeTypeImpl envelope, String wireCenterCLLI8)
	    throws
	        InvalidData,
	        AccessDenied,
	        BusinessViolation,
	        SystemFailure,
	        NotImplemented,
	        ObjectNotFound,
	        DataNotFound,
	        Throwable {
	
	    String myMethodName = "SOACGIServiceOrderResponseParser:SOACGIServiceOrderResponseParser()";
	    
	    PROPERTIES = properties;
	    myProperties = properties;
	    myLogger = aLogger;
	    
	    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
	   
	    soaObj = setServiceOrderAssignment();
	    
	    int numSegs = 0;
	   
	    FiberSegment[] aFiberSegmentList = new FiberSegment[9];
	
	    com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces.ACLType aAcl = null;
	
	    if(envelope.getHeader().getObject().equals("MCT"))
	            aAcl = envelope.getGIOUTBMCT().getBODY().getGRANITE().getACL();
	    else if(envelope.getHeader().getObject().equals("IR"))
	            aAcl = envelope.getGIOUTBIR().getBODY().getGRANITE().getACL();
	
	    try
	    {
	        circuitNumber = aAcl.getCTID().replace('.','/');
	    }catch(Exception e){}
 
	    if (circuitNumber == null)
		{
	      	myLogger.log(LOG_INPUT_DATA, "Circuit ID=<" + circuitNumber + ", and set as an empty string in xml response.>");
	      	circuitNumber = ""; // To prevent XML conversion error
		}
	    
	    myLogger.log(LOG_INPUT_DATA, "CircuitID=<" + circuitNumber + ">");
    soaObj.aLightspeedCircuitId.theValue(circuitNumber);
	  
	    FiberSegment aFiberSegment = null;
	
	    primaryNPANXX =
	        envelope.getHeader().getAdditionalInfo().getTtWireCenter();
	    myLogger.log(LOG_INPUT_DATA, "primaryNPANXX=<" + primaryNPANXX + ">");
	
	    if (primaryNPANXX == null || primaryNPANXX.length() != 6)
	        throwException(
	            ExceptionCode.ERR_RM_MISSING_NPANXX,
	            "Missing or invalid required data: NPA NXX",
	            this.getEnv("BIS_NAME"),
	            Severity.UnRecoverable);
	
	    String aOrderNum =
	        envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
	    String aOrderID = null;
	    
	    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderNum + ">");
	    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderID + ">");
	
	    soaObj.aWireCenter.theValue(wireCenterCLLI8);
	    
	    if (aAcl.getF2().getTF1() != null) {
	       myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF1 Segment Data");
	
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "1",
	                aAcl.getF2().getTF1().getTERM(),
	                null,
	                aAcl.getF2().getTF1().getNETPRT(),
	                aAcl.getF2().getTF1().getACCPRT(),
	                aAcl.getF2().getTF1().getCA(),
	                aAcl.getF2().getTF1().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF1().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF1().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF1().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF1().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF1().getACCPRT() + ">");
	
	        aFiberSegmentList[0] = aFiberSegment;
	        numSegs++;
	    }
	
	    if (aAcl.getF2().getTF2() != null) {
	
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF2 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "2",
	                aAcl.getF2().getTF2().getTERM(),
	                null,
	                aAcl.getF2().getTF2().getNETPRT(),
	                aAcl.getF2().getTF2().getACCPRT(),
	                aAcl.getF2().getTF2().getCA(),
	                aAcl.getF2().getTF2().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF2().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF2().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF2().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF2().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF2().getACCPRT() + ">");
	
	        aFiberSegmentList[1] = aFiberSegment;
	        numSegs++;          
	    }
	
	    if (aAcl.getF2().getTF3() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF3 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "3",
	                aAcl.getF2().getTF3().getTERM(),
	                null,
	                aAcl.getF2().getTF3().getNETPRT(),
	                aAcl.getF2().getTF3().getACCPRT(),
	                aAcl.getF2().getTF3().getCA(),
	                aAcl.getF2().getTF3().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF3().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF3().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF3().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF3().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF3().getACCPRT() + ">");
	
	        aFiberSegmentList[2] = aFiberSegment;
	        numSegs++;          
	    }
	
	    if (aAcl.getF2().getTF4() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF4 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "4",
	                aAcl.getF2().getTF4().getTERM(),
	                null,
	                aAcl.getF2().getTF4().getNETPRT(),
	                aAcl.getF2().getTF4().getACCPRT(),
	                aAcl.getF2().getTF4().getCA(),
	                aAcl.getF2().getTF4().getPR(),
	                wireCenterCLLI8);
	
	         myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF4().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF4().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF4().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF4().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF4().getACCPRT() + ">");
	
	        aFiberSegmentList[3] = aFiberSegment;
	        numSegs++;          
	    }
	
	    if (aAcl.getF2().getTF5() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF5 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "5",
	                aAcl.getF2().getTF5().getTERM(),
	                aAcl.getF2().getTF5().getNETPRT(),
	                aAcl.getF2().getTF5().getACCPRT(),
	                aAcl.getF2().getTF5().getCA(),
	                aAcl.getF2().getTF5().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF5().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF5().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF5().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF5().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF5().getACCPRT() + ">");
	
	        aFiberSegmentList[4] = aFiberSegment;
	        numSegs++;          
	    }
	
	    if (aAcl.getF2().getTF6() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF6 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "6",
	                aAcl.getF2().getTF6().getTERM(),
	                null,
	                aAcl.getF2().getTF6().getNETPRT(),
	                aAcl.getF2().getTF6().getACCPRT(),
	                aAcl.getF2().getTF6().getCA(),
	                aAcl.getF2().getTF6().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF6().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF6().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF6().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF6().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF6().getACCPRT() + ">");
	
	        aFiberSegmentList[5] = aFiberSegment;
	        numSegs++;
	    }
	
	    if (aAcl.getF2().getTF7() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF7 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "7",
	                aAcl.getF2().getTF7().getTERM(),
	                null,
	                aAcl.getF2().getTF7().getNETPRT(),
	                aAcl.getF2().getTF7().getACCPRT(),
	                aAcl.getF2().getTF7().getCA(),
	                aAcl.getF2().getTF7().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF7().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF7().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF7().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF7().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF7().getACCPRT() + ">");
	
	        aFiberSegmentList[6] = aFiberSegment;
	        numSegs++;          
	    }
	
	    if (aAcl.getF2().getTF8() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF8 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "8",
	                aAcl.getF2().getTF8().getTERM(),
	                null,
	                aAcl.getF2().getTF8().getNETPRT(),
	                aAcl.getF2().getTF8().getACCPRT(),
	                aAcl.getF2().getTF8().getCA(),
	                aAcl.getF2().getTF8().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF8().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF8().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF8().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF8().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF8().getACCPRT() + ">");
	
	        aFiberSegmentList[7] = aFiberSegment;
	        numSegs++;          
	    }
	
	    if (aAcl.getF2().getTF9() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing TF9 Segment Data");
	        aFiberSegment =
	            NetworkTypeHelper.createFiberSegment(
	                "9",
	                aAcl.getF2().getTF9().getTERM(),
	                null,
	                aAcl.getF2().getTF9().getNETPRT(),
	                aAcl.getF2().getTF9().getACCPRT(),
	                aAcl.getF2().getTF9().getCA(),
	                aAcl.getF2().getTF9().getPR(),
	                wireCenterCLLI8);
	
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Terminal ID=<" + aAcl.getF2().getTF9().getTERM() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Network Port=<" + aAcl.getF2().getTF9().getNETPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "Access Port=<" + aAcl.getF2().getTF9().getACCPRT() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Cable=<" + aAcl.getF2().getTF9().getCA() + ">");
	        myLogger.log(
	            LOG_INPUT_DATA,
	            "InFiber Pair=<" + aAcl.getF2().getTF9().getACCPRT() + ">");
	
	        aFiberSegmentList[8] = aFiberSegment;
	        numSegs++;          
	    }
	   
    FiberSegment[] aNewFiberSegmentList = new FiberSegment[numSegs];
	    for(int i = 0; i < numSegs; i++)
	    {
	        aNewFiberSegmentList[i] = aFiberSegmentList[i];
	    }
	
	    //F1 is OpticalLineTerminal
	    String f1Model = null;
	    try {
	        f1Model = aAcl.getF1().getMODEL();
	    } catch (Exception e) {
	    }
	    String f1Term = null;
	    try {
	        f1Term = aAcl.getF1().getTERM();
	    } catch (Exception e) {
	    }
	    String f1Tidc = null;
	    try {
	        f1Tidc = aAcl.getF1().getTIDC();
	    } catch (Exception e) {
	    }
	
	    myLogger.log(LOG_INPUT_DATA, "OLT Model=<" + f1Model + ">");
	    myLogger.log(LOG_INPUT_DATA, "OLT ID=<" + f1Term + ">");
	    myLogger.log(LOG_INPUT_DATA, "OLT TID=<" + f1Tidc + ">");
	
	    //F2 is OpticalNetworkTerminal
	    String f2Term = null;
	    try {
	        f2Term = aAcl.getF2().getTERM();
	    } catch (Exception e) {
	    }
	    String f2Aid = null;
	    try {
	        f2Aid = aAcl.getF2().getAID();
	    } catch (Exception e) {
	    }
	    String f2Model = null;
	    try {
	        f2Model = aAcl.getF2().getMODEL();
	    } catch (Exception e) {
	    }
	    String f2Port = null;
	    try {
	        f2Port = aAcl.getF2().getPORT();
	    } catch (Exception e) {
	    }
	
	    myLogger.log(LOG_INPUT_DATA, "ONT ID=<" + f2Term + ">");
	    myLogger.log(LOG_INPUT_DATA, "ONT AID=<" + f2Aid + ">");
	    myLogger.log(LOG_INPUT_DATA, "ONT Model=<" + f2Model + ">");
	    myLogger.log(LOG_INPUT_DATA, "ONT PORT=<" + f2Port + ">");
	
	    OpticalNetworkTerminal aOpticalNetworkTerminal =
	        NetworkTypeHelper.createOpticalNetworkTerminal(
	            f2Term,
	            f2Model,
	            appendONTSlotAndPortToONTAID(f2Aid),
	            f2Port,
	            getFTTP_ONT_INDEX(f2Aid),
	            null);
	
	    OpticalLineTerminal aOpticalLineTerminal =
	        NetworkTypeHelper.createOpticalLineTerminal(
	            null,
	            f1Term,
	            null,
	            null,
	            null,
	            null,
	            null,
	            null,
	            null,
	            null,
	            null,
	            null,
	            null,
	            getFTTP_OLT_PORT(f2Aid),
	            null,
	            null,
	            null,
	            null,
	            f1Model,
	            f1Tidc,
	            null,
	            null,
	            null,
	            null,
	            null);
	
	    aFttp =
	        NetworkTypeHelper.createFttp(
	            aOpticalNetworkTerminal,
	            aOpticalLineTerminal,
	            aNewFiberSegmentList);
	
	    NetworkType aNetworkTypeChoice = new NetworkType();
	    aNetworkTypeChoice.aFttp(aFttp); 
	   
	    aNetworkTypeChoice.aFttp().aSegments.theValue(aNewFiberSegmentList);
		soaObj.aNetworkTypeChoice.theValue(aNetworkTypeChoice);
			
	   myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
	}


public SOACGIServiceOrderResponseParser(Hashtable properties, Logger aLogger,
	        com
	            .sbc
	            .eia
	            .bis
	            .embus
	            .service
	            .soacgi
	            .SWITCH
	            .fttn
	            .interfaces
	            .impl
	            .EnvelopeTypeImpl envelope, String wireCenterCLLI8)
	        throws
	            InvalidData,
	            AccessDenied,
	            BusinessViolation,
	            SystemFailure,
	            NotImplemented,
	            ObjectNotFound,
	            DataNotFound,
	            Throwable {
	
	        String myMethodName = "ModifyInventory:SOACGIServiceOrderResponseParser()";
	    	String anMIException = null;
        String anMIExceptionMsg = null;
        
        PROPERTIES = properties;
	    myProperties = properties;
	    myLogger = aLogger;
      
	        myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
	        
	        soaObj = setServiceOrderAssignment();
	        
	        CopperSegment[] aCopperSegmentList = new CopperSegment[1];
	
	        com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.ACLType aAcl = null;
	
	        if(envelope.getHeader().getObject().equals("MCT"))
	                aAcl = envelope.getGIOUTBMCT().getBODY().getGRANITE().getACL();
	        else if(envelope.getHeader().getObject().equals("FTR"))
	                aAcl = envelope.getGIOUTBFTR().getBODY().getGRANITE().getACL();
	
	
	        try
	        {
	            circuitNumber = aAcl.getCTID().replace('.','/');
	            
	        }catch(Exception e){}
	        
	        if (circuitNumber == null)
	        {
	        	myLogger.log(LOG_INPUT_DATA, "Circuit ID=<" + circuitNumber + ", and set as an empty string in xml response.>");
	        	circuitNumber = ""; // To prevent XML conversion error
	  	    }
	        
	        myLogger.log(LOG_INPUT_DATA, "Circuit ID=<" + circuitNumber + ">");
		    
	       
	        soaObj.aLightspeedCircuitId.theValue(circuitNumber);
	         
	        String aOrderNum =
	            envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
	        String aOrderID = null;
			
	        myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderNum + ">");
	        myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderID + ">");
	        
	        if(aOrderNum == null)
	        {
	        	aOrderNum = "";// To prevent XML conversion error
	        }
		    soaObj.aServiceOrderNumber.theValue(aOrderNum);
	
	        dslamID = aAcl.getNEWTRE();
	        myLogger.log(LOG_INPUT_DATA, "DSLAM ID=<" + dslamID + ">");
	
	        oldDslamID = aAcl.getOLDTRE();
	        myLogger.log(LOG_INPUT_DATA, "OLD DSLAM ID=<" + oldDslamID + ">");
	        
	        DSLAMTransport aDSLAMTransport =
	            NetworkTypeHelper.createDSLAMTransport(
	                dslamID,
	                null,
	                null,
	                null,
	                null,
	                null,
	                null,
	                false,
	                null);
	
	
	        primaryNPANXX =
	            envelope.getHeader().getAdditionalInfo().getTtWireCenter();
	        myLogger.log(LOG_INPUT_DATA, "primaryNPANXX=<" + primaryNPANXX + ">");
	
	        if (primaryNPANXX == null || primaryNPANXX.length() != 6)
	            throwException(
	                ExceptionCode.ERR_RM_MISSING_NPANXX,
	                "Missing or invalid required data: NPA NXX",
	                this.getEnv("BIS_NAME"),
	                Severity.UnRecoverable);
	                soaObj.aWireCenter.theValue(wireCenterCLLI8);
			
	        if (aAcl.getF1L() != null) {
	
	            myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F1 Segment Data");
	            myLogger.log(
	                LOG_INPUT_DATA,
	                "Cable=<" + aAcl.getF1L().getCA() + ">");
	            myLogger.log(
	                LOG_INPUT_DATA,
	                "Pair=<" + aAcl.getF1L().getPR() + ">");
	
	            validateCablePairInfo(
	                aAcl.getF1L().getCA(),
	                aAcl.getF1L().getPR(),
	                1);
	            CopperSegment aCopperSegment =
	                NetworkTypeHelper.createCopperSegment(
	                    "1",
	                    null,
	                    null,
	                    null,
	                    null,
	                    null,
	                    null,
	                    null,
	                    null,
	                    null);
	
	            aCopperSegmentList[0] = aCopperSegment;
	        }
	     
	      
	    	Fttn sFttn = NetworkTypeHelper.createFttn(aDSLAMTransport, aCopperSegmentList);
	        aFttn = new Fttn(sFttn.aDSLAM, null);
	        NetworkType aNetworkTypeChoice = new NetworkType();
		    aNetworkTypeChoice.aFttn(sFttn); 
		 
		    aNetworkTypeChoice.aFttn().aSegments.theValue(aCopperSegmentList);
		    
			soaObj.aNetworkTypeChoice.theValue(aNetworkTypeChoice);
			
			myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);		
	}

public void validateCablePairInfo(
  String aCable,
  String aPair,
  int aCablePairIndex)
  throws
      InvalidData,
      SystemFailure,
      BusinessViolation,
      AccessDenied,
      NotImplemented,
      ObjectNotFound,
      DataNotFound {
  String aCAException = null;
  String aPRException = null;

  String aCAExceptionMsg = null;
  String aPRExceptionMsg = null;

  if (aCablePairIndex == 1) {
      aCAException = ExceptionCode.ERR_RM_MISSING_F1_CABLE;
      aCAExceptionMsg = "Missing required data: F1 Cable";

      aPRException = ExceptionCode.ERR_RM_MISSING_F1_PAIR;
      aPRExceptionMsg = "Missing required data: F1 Pair";

  } else if (aCablePairIndex == 2) {
      aCAException = ExceptionCode.ERR_RM_MISSING_F2_CABLE;
      aCAExceptionMsg = "Missing required data: F2 Cable";

      aPRException = ExceptionCode.ERR_RM_MISSING_F2_PAIR;
      aPRExceptionMsg = "Missing required data: F2 Pair";

  } else {
      aCAException = ExceptionCode.ERR_RM_MISSING_IN_CABLE;
      aCAExceptionMsg = "Missing required data: Cable for Segment:"+aCablePairIndex;

      aPRException = ExceptionCode.ERR_RM_MISSING_IN_PAIR;
      aPRExceptionMsg = "Missing required data: Pair for Segment:"+aCablePairIndex;
  }

  if (aCable == null || aCable.trim().length() == 0)
      throwException(
          aCAException,
          aCAExceptionMsg,
          this.getEnv("BIS_NAME"),
          Severity.UnRecoverable);

  if (aPair == null || aPair.trim().length() == 0)
      throwException(
          aPRException,
          aPRExceptionMsg,
          this.getEnv("BIS_NAME"),
          Severity.UnRecoverable);

}

public String retrieveCLLI8(String primaryNPANXX) throws SQLException {

  String myMethodName = "SOACGIServiceOrderResponseParser:retrieveCLLI8()";
  myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

  String retVal = null;
  ResultSet rs = null;
  DBConnection aDBConn = null;
  PreparedStatement ps = null;
  SoacWireCenterRow resultRow = null;

  String SQLstatement =
      "select CLLI8 from SOAC_WIRE_CENTER where NPANXX = ? ";
	    int reTryCnt = 0;
		while (true){
    try {
      aDBConn =
          SoacWireCenterTable.getDBConnection(myProperties, myLogger);
      ps = aDBConn.getConnection().prepareStatement(SQLstatement);
      ps.setString(1, primaryNPANXX);

      myLogger.log( LogEventId.REMOTE_CALL, (String)myProperties.get( "jdbcURL" ),
                                       (String)myProperties.get( "jdbcUSERID" ),
                                       (String)myProperties.get( "jdbcUSERID" ),
                                       "SOACServiceOrderResponseParser::retrieveCLLI8()" ) ;
      
       try {
          rs = ps.executeQuery();
       }
       finally {
       	;
          myLogger.log( LogEventId.REMOTE_RETURN, (String)myProperties.get( "jdbcURL" ),
                                       (String)myProperties.get( "jdbcUSERID" ),
                                       (String)myProperties.get( "jdbcUSERID" ),
                                       "SOACServiceOrderResponseParser::retrieveCLLI8()" ) ;
          
       }


       if (rs.next()) {
          myLogger.log(
              LogEventId.DEBUG_LEVEL_1,
              "Result: |" + rs.getString(1) + "|");
          retVal = rs.getString(1);

       }
   }
   catch (StaleConnectionException e) 
		 {
			
			if (reTryCnt == 0) {
					reTryCnt++;
					aDBConn = null;
					myLogger.log(
						LogEventId.INFO_LEVEL_2,
						"*****Getting Connection second time*****");
					continue;
			}
			throw e;

   } 
   catch (Exception e) 
		 {
     myLogger.log(
          LogEventId.ERROR,
          "Error Message [" + e.getMessage() + "]");
      myLogger.log(
          LogEventId.ERROR,
          "Error String [" + e.toString() + "]");
      throw new SQLException(
          "No CLLI8 found for the NPANXX=" + primaryNPANXX);
   } 
   finally 
		 {
      try {
          if (rs != null)
              rs.close();
          if (ps != null)
              ps.close();
      } 
      catch (Exception e) {
      }
      try {
          aDBConn.disconnect();
      } 
      catch (Exception e) {
      }
      aDBConn = null;
  }
 myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
  return retVal;
} //end of while loop
}

public String appendONTSlotAndPortToONTAID(String ONTaID) {
  String myMethodName =
      "SOACGIServiceOrderResponseParser::appendONTSlotAndPortToONTAID()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

  String appendData = "-1";

  String retVal = ONTaID;
  
  if(retVal != null)
  {
      int numPos = getNumberOfSpecialChars(ONTaID, '-');          

      if (numPos == 5)
              retVal = ONTaID + appendData + appendData;
      else if (numPos == 6)
          retVal = ONTaID + appendData;
  }
  
  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
  return retVal;
}

public String getFTTP_OLT_PORT(String ONTaId) {
  String myMethodName = "SOACGIServiceOrderResponseParser::getFTTP_OLT_PORT()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

  String retVal = null;

      try {
          //if ONTaID is ONT-A-B-C-D-E, then OLT Port is D.
          //for LS3 the OLT Port is A-B-C-D. not just D.
          retVal =
              getDataTillNumberOfSpecialCharacters(ONTaId, '-', 4);

          myLogger.log(
             LogEventId.DEBUG_LEVEL_1,
              "FTTP_OLT_PORT =<" + retVal + ">");
      } catch (Exception e) {

  }

  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
  return retVal;

}

public String getFTTP_ONT_INDEX(String ONTaId) {
  String myMethodName = "SOACGIServiceOrderResponseParser::getFTTP_ONT_INDEX()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

  String retVal = null;

      try {
          //if ONTaID is ONT-A-B-C-D-E, then ONT Index is E.  
          retVal =
              getDataAfterNumberOfSpecifiedCharacters(ONTaId, '-', 5);

          myLogger.log(
              LogEventId.DEBUG_LEVEL_1,
              "FTTP_ONT_INDEX =<" + retVal + ">");
      } catch (Exception e) {

  }

  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
  return retVal;

}

public int getNumberOfSpecialChars(String aString, int aChar)
{
  String myMethodName =
      "SOACGIServiceOrderResponseParser::getNumberOfSpecialChars()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

  int index = 0;
  int previousIndex = 0;
  int numTimes = 0;
  
  while(index != -1)
  {
      previousIndex = index;
      index   = aString.indexOf(aChar,previousIndex + 1);
      numTimes++;
  }

  if(index == -1)
      numTimes--;

  myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);        
  
  return numTimes;
}

public String getDataAfterNumberOfSpecifiedCharacters(String aString, int aChar, int numberOfInstancesAfter)
{
  String myMethodName =
      "SOACGIServiceOrderResponseParser::getDataAfterNumberOfSpecifiedCharacters()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

  
  int index = 0;
  int previousIndex = 0;
  int numTimes = 0;
  
  String retVal= null;
  for(; numTimes <= numberOfInstancesAfter ; numTimes++)
  {
      previousIndex = index;
      index   = aString.indexOf(aChar,previousIndex + 1);
      
      if( numTimes == numberOfInstancesAfter)
          if(index != -1)
              retVal= aString.substring(previousIndex+1,index);
          else
              retVal= aString.substring(previousIndex+1);
      
  }
  
  myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
  
  return retVal;
}

public String getDataTillNumberOfSpecialCharacters(String aString, int aChar, int numberOfInstancesAfter)
{

  String myMethodName =
      "SOACGIServiceOrderResponseParser::getDataTillNumberOfSpecialCharacters()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
  
  int index = 0;
  int previousIndex = 0;
  int numTimes = 0;
  
  int firstIndex = -1;
  
  String retVal= null;
  for(; numTimes <= numberOfInstancesAfter ; numTimes++)
  {
      previousIndex = index;
      index   = aString.indexOf(aChar,previousIndex + 1);
      if(numTimes == 0 && index != -1)
          firstIndex = index;
      
      if( numTimes == numberOfInstancesAfter)
          if(index != -1)
              retVal= aString.substring(firstIndex+1,index);
          else
              retVal= aString.substring(firstIndex);
      
  }

  myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
          
  return retVal;
}


private ServiceOrderAssignment setServiceOrderAssignment()
	{
		String myMethodNameName = "SOACGIServiceOrderResponseParser.setServiceOrderAssignment()";
		myLogger.log(LogEventId.DEBUG_LEVEL_2, ">" + myMethodNameName);

		// IMPORTANT:	These are preset values to accomodate the 
		//				conversion to XML(Opt: Min=0). If the response value 
		//				does NOT exist, leave at this preset value.
		StringOpt aWireCenter = IDLUtil.toOpt((String) null, false);
		StringOpt aServiceOrderNumber = IDLUtil.toOpt((String) null, false);
		StringOpt aServiceOrderCorrectionSuffix = IDLUtil.toOpt((String) null, false);
		StringOpt aTelephoneNumber = IDLUtil.toOpt((String) null, false);
		StringOpt aLightspeedCircuitId = IDLUtil.toOpt((String) null, false);
		StringOpt aTaperCode = IDLUtil.toOpt((String) null, false);
		BooleanOpt aLSTIndicator = new BooleanOpt();
		
		aLSTIndicator.theValue(false);  // default

		// Network type data
		NetworkTypeOpt aNetworkTypeChoice = new NetworkTypeOpt();
		aNetworkTypeChoice.__default();

		// Assignment section
		StringSeqOpt aAssignmentLines = new StringSeqOpt();
		aAssignmentLines.__default();

		myLogger.log(LogEventId.DEBUG_LEVEL_2, "<" + myMethodNameName);
		return new ServiceOrderAssignment(
			aWireCenter,
			aServiceOrderNumber,
			aServiceOrderCorrectionSuffix,
			aTelephoneNumber,
			aLightspeedCircuitId,
			aTaperCode,
			aNetworkTypeChoice,
			aAssignmentLines,
			aLSTIndicator);
	}

public SOACGIServiceOrderResponseParser(Hashtable properties, Logger aLogger,
	    com
	        .sbc
	        .eia
	        .bis
	        .embus
	        .service
	        .soacgi
	        .LFACS
	        .fttn
	        .interfaces
	        .impl
	        .EnvelopeTypeImpl envelope, String wireCenterCLLI8, boolean validateCablePair)
	    throws
	        InvalidData,
	        AccessDenied,
	        BusinessViolation,
	        SystemFailure,
	        NotImplemented,
	        ObjectNotFound,
	        DataNotFound,
	        Throwable {
	
	    String myMethodName = "SOACGIServiceOrderResponseParser:SOACGIServiceOrderResponseParser()";
	    PROPERTIES = properties;
	    myProperties = properties;
	    myLogger = aLogger;
	     
	    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
	   
	    soaObj = setServiceOrderAssignment();
	    String terminalId = null;
	    String outBindingPost = null;
	    String inBindingPost = null;
	    String outPairColor = null;
	    String inCableName = null;
	    String inCablePair = null;
	    
	    CopperSegment[] aCopperSegmentList = new CopperSegment[7];
	    int numSegs = 0;
	  
	    com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.ACLType aAcl = null;
	    // (1)LS Circuit ID
	    if(envelope.getHeader().getObject().equals("MCT"))
	            aAcl = envelope.getGIOUTBMCT().getBODY().getGRANITE().getACL();
	    else if(envelope.getHeader().getObject().equals("IR"))
	            aAcl = envelope.getGIOUTBIR().getBODY().getGRANITE().getACL();
	    else if(envelope.getHeader().getObject().equals("LST"))
	            aAcl = envelope.getGIOUTBLST().getBODY().getGRANITE().getACL();
	    else if(envelope.getHeader().getObject().equals("CPT"))
	            aAcl = envelope.getGIOUTBCPT().getBODY().getGRANITE().getACL();
	    else if(envelope.getHeader().getObject().equals("RCN"))
	            aAcl = envelope.getGIOUTBRCN().getBODY().getGRANITE().getACL();
	    
	    
	    try
	    {
	        circuitNumber = aAcl.getCTID().replace('.','/');
	       
	    }catch(Exception e){}
	    
    myLogger.log(LOG_INPUT_DATA, "CircuitID=<" + circuitNumber + ">");
       
	    soaObj.aLightspeedCircuitId.theValue(circuitNumber);
	   
	    
	    primaryNPANXX =
	        envelope.getHeader().getAdditionalInfo().getTtWireCenter();
	    myLogger.log(LOG_INPUT_DATA, "primaryNPANXX=<" + primaryNPANXX + ">");
	
	    if (primaryNPANXX == null || primaryNPANXX.trim().length() != 6)
	        throwException(
	            ExceptionCode.ERR_RM_MISSING_NPANXX,
	            "Missing or invalid required data: NPA NXX",
	            this.getEnv("BIS_NAME"),
	            Severity.UnRecoverable);
	
	    aOrderNum =
	        envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
	    aOrderID = null;
	 
	    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderNum + ">");
	   
	    if(aOrderNum == null)
	    {
	    	aOrderNum = "";// To prevent XML conversion error
	    }
	    
	    soaObj.aServiceOrderNumber.theValue(aOrderNum);
		
	    soaObj.aWireCenter.theValue(wireCenterCLLI8);
		CopperSegment aCopperSegment = null;
	
	    String aBP = null;
	    String emptyString = "";
	
	    isFTTnSAI = false;
	    isIPDSLAMSAI = false;
	    boolean pigtailCreated = false;
	    CopperSegment[] pigtailCopperSegment = new CopperSegment[2];  
	
	    // determine if FTTN / IPDSLAM - SAI scenario	    		    
		if ((aAcl.getF1() != null)
		    && (aAcl.getF1().getPGST() != null) 
		    && ((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF1().getPGST().toUpperCase() + ":") >= 0)
		    isFTTnSAI = true;   
		
		else if ((aAcl.getF1() != null) 
	        && (aAcl.getF1().getPGST() != null)
	        && ((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF1().getPGST().toUpperCase() + ":") >= 0)
	    	isIPDSLAMSAI = true;
		
		else if ((aAcl.getF2() != null) 
		    && (aAcl.getF2().getPGST() != null) 
		    && ((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF2().getPGST().toUpperCase() + ":") >= 0)
		    isFTTnSAI = true;
		
		else if ((aAcl.getF2() != null) 
	        && (aAcl.getF2().getPGST() != null) 
	        && ((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF2().getPGST().toUpperCase() + ":") >= 0)
	    	isIPDSLAMSAI = true;
		    
		else if ((aAcl.getF3() != null) 
		    && (aAcl.getF3().getPGST() != null) 
		    && ((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF3().getPGST().toUpperCase() + ":") >= 0)
		    isFTTnSAI = true;
		
		else if ((aAcl.getF3() != null) 
	        && (aAcl.getF3().getPGST() != null) 
	        && ((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF3().getPGST().toUpperCase() + ":") >= 0)
	    	isIPDSLAMSAI = true;
		
		else if ((aAcl.getF4() != null) 
		    && (aAcl.getF4().getPGST() != null) 
		    && ((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF4().getPGST().toUpperCase() + ":") >= 0)
		    isFTTnSAI = true;
		
		else if ((aAcl.getF4() != null) 
	        && (aAcl.getF4().getPGST() != null) 
	        && ((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF4().getPGST().toUpperCase() + ":") >= 0)
	    	isIPDSLAMSAI = true;	
		
		else if ((aAcl.getF5() != null) 
		    && (aAcl.getF5().getPGST() != null) 
		    && ((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF5().getPGST().toUpperCase() + ":") >= 0)
		    isFTTnSAI = true;
		
		else if ((aAcl.getF5() != null) 
	        && (aAcl.getF5().getPGST() != null) 
	        && ((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF5().getPGST().toUpperCase() + ":") >= 0)
	    	isIPDSLAMSAI = true;	
		
		else if((aAcl.getF6() != null) 
		    && (aAcl.getF6().getPGST() != null) 
			&& ((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF6().getPGST().toUpperCase() + ":") >= 0)
		    isFTTnSAI = true;
		
		else if ((aAcl.getF6() != null) 
	        && (aAcl.getF6().getPGST() != null) 
	    	&& ((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF6().getPGST().toUpperCase() + ":") >= 0)
	    	isIPDSLAMSAI = true;
		
	    // log
	    if (isFTTnSAI)
	    {
	       myLogger.log(LogEventId.INFO_LEVEL_1,"FTTN - SAI Scenario.");
	    }
	    else if (isIPDSLAMSAI) 
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1,"IPDSLAM - SAI Scenario.");
	    }
	    else
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1,"FTTN - CO DSLAM Scenario.");
	    }    
	    
    // process F1 segment
	    if (aAcl.getF1() != null) 
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F1 Segment Data");
	
	        // log F1 segment input
	        terminalId = aAcl.getF1().getTERM();
		    outBindingPost = aAcl.getF1().getOBP();
		    inBindingPost = aAcl.getF1().getBP();
		    outPairColor = aAcl.getF1().getOPC();
		    inCableName = aAcl.getF1().getCA();
		    inCablePair =  aAcl.getF1().getPR();
		  
		    myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + terminalId + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + outBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + inBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + outPairColor + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + inCableName + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + inCablePair + ">");

	      
	       if (validateCablePair)
	         validateCablePairInfo(aAcl.getF1().getCA(), 
	                              aAcl.getF1().getPR(),
	                              1);
	                              
	       
	        // create segment
	        if (isFTTnSAI)
	        {
	            // FTTN - SAI
	            if (aAcl.getF1().getPGST() != null)
	            {
	            	if (((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF1().getPGST().toUpperCase() + ":") >= 0)
	            	{
	                    pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegment(aAcl.getF1().getTERM(),
	                                                                                         null,
	                                                                                         null,
	                                                                                         aAcl.getF1().getBP(), 
	                                                                                         aAcl.getF1().getOBP(),
	                                                                                         aAcl.getF1().getOPC(),
	                                                                                         aAcl.getF1().getCA(),
	                                                                                         aAcl.getF1().getPR(),
	                                                                                         wireCenterCLLI8);
	                    aCopperSegmentList[0] = pigtailCopperSegment[0];
	                    aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                    numSegs = 2;
	                    pigtailCreated = true;
	                }
	            }
	        }
	        //IPDSLAM - SAI
	        else if (isIPDSLAMSAI)
	        {
	        	if (aAcl.getF1().getPGST() != null) 
	        	{
	                if (((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF1().getPGST().toUpperCase() + ":") >= 0)
	                {
	                    pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegmentIPDSLAM(aAcl.getF1().getTERM(),
	                                                                                         null,
	                                                                                         null,
	                                                                                         aAcl.getF1().getBP(), 
	                                                                                         aAcl.getF1().getOBP(),
	                                                                                         aAcl.getF1().getOPC(),
	                                                                                         aAcl.getF1().getCA(),
	                                                                                         aAcl.getF1().getPR(),
	                                                                                         wireCenterCLLI8);
	                    aCopperSegmentList[0] = pigtailCopperSegment[0];
	                    aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                    numSegs = 2;
	                    pigtailCreated = true;
	                }
	            }
	        }        
	        else
	        {
	            // FTTN - CO DSLAM
	            aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                   aAcl.getF1().getTERM(),
	                                                                   null,
	                                                                   null,
	                                                                   aAcl.getF1().getBP(),
	                                                                   aAcl.getF1().getOBP(),
	                                                                   aAcl.getF1().getOPC(),
	                                                                   aAcl.getF1().getCA(),
	                                                                   aAcl.getF1().getPR(),
	                                                                   wireCenterCLLI8);
	            
	            aCopperSegmentList[numSegs] = aCopperSegment;
	            numSegs++;
	        }
	    }
	
	    // process F2 segment
	    if (aAcl.getF2() != null) 
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F2 Segment Data");
	
	        // log F2 segment input
	        terminalId = aAcl.getF2().getTERM();
		    outBindingPost = aAcl.getF2().getOBP();
		    inBindingPost = aAcl.getF2().getBP();
		    outPairColor = aAcl.getF2().getOPC();
		    inCableName = aAcl.getF2().getCA();
		    inCablePair =  aAcl.getF2().getPR();
		  
		    myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + terminalId + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + outBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + inBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + outPairColor + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + inCableName + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + inCablePair + ">");

	        // validate cable/pair info
		    if (validateCablePair)
		     validateCablePairInfo(aAcl.getF2().getCA(),
	                              aAcl.getF2().getPR(),
	                              2);
	                              
	        // create segment
	        if (isFTTnSAI)
	        {
	            // FTTN - SAI
	            if (!pigtailCreated) 
	            {
	                if (aAcl.getF2().getPGST() != null) 
	                {
	                	if (((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF2().getPGST().toUpperCase() + ":") >= 0)
	                	{
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegment(aAcl.getF2().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF2().getBP(), 
	                                                                                             aAcl.getF1().getOBP(),
	                                                                                             aAcl.getF2().getOPC(),
	                                                                                             aAcl.getF2().getCA(),
	                                                                                             aAcl.getF2().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }            
	            else {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                       aAcl.getF2().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF2().getBP(),
	                                                                       aAcl.getF1().getOBP(),
	                                                                       aAcl.getF2().getOPC(),
	                                                                       aAcl.getF2().getCA(),
	                                                                       aAcl.getF2().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        //IPDSLAM - SAI
	        else if (isIPDSLAMSAI)
	        {
	            if (!pigtailCreated)
	            {
	                if (aAcl.getF2().getPGST() != null)
	                {	                	
	                    if (((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF2().getPGST().toUpperCase() + ":") >= 0)
	                    {
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegmentIPDSLAM(aAcl.getF2().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF2().getBP(), 
	                                                                                             aAcl.getF1().getOBP(),
	                                                                                             aAcl.getF2().getOPC(),
	                                                                                             aAcl.getF2().getCA(),
	                                                                                             aAcl.getF2().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            } 
	            else
	            {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                       aAcl.getF2().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF2().getBP(),
	                                                                       aAcl.getF1().getOBP(),
	                                                                       aAcl.getF2().getOPC(),
	                                                                       aAcl.getF2().getCA(),
	                                                                       aAcl.getF2().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        else 
	        {
	            // FTTN - CO DSLAM                
	            aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                   aAcl.getF2().getTERM(),
	                                                                   null,
	                                                                   null,
	                                                                   aAcl.getF2().getBP(),
	                                                                   aAcl.getF2().getOBP(),
	                                                                   aAcl.getF2().getOPC(),
	                                                                   aAcl.getF2().getCA(),
	                                                                   aAcl.getF2().getPR(),
	                                                                   wireCenterCLLI8);
	            
	            aCopperSegmentList[numSegs] = aCopperSegment;
	            numSegs++;
	        }
	    }
	
	    // process F3 segment
	    if (aAcl.getF3() != null)
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F3 Segment Data");
	
	        // log F3 segment input
	        terminalId = aAcl.getF3().getTERM();
		    outBindingPost = aAcl.getF3().getOBP();
		    inBindingPost = aAcl.getF3().getBP();
		    outPairColor = aAcl.getF3().getOPC();
		    inCableName = aAcl.getF3().getCA();
		    inCablePair =  aAcl.getF3().getPR();
		  
		    myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + terminalId + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + outBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + inBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + outPairColor + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + inCableName + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + inCablePair + ">");
		   
		    if (validateCablePair)
	          validateCablePairInfo(aAcl.getF3().getCA(),
	                              aAcl.getF3().getPR(),
	                              3);
	                              
	      
	        // create segment
	        if (isFTTnSAI) 
	        {
	            // FTTN - SAI
	            if (!pigtailCreated) 
	            {
	                if (aAcl.getF3().getPGST() != null) 
	                {
	                	if (((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF3().getPGST().toUpperCase() + ":") >= 0)
	                	{
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegment(aAcl.getF3().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF3().getBP(), 
	                                                                                             aAcl.getF2().getOBP(),
	                                                                                             aAcl.getF3().getOPC(),
	                                                                                             aAcl.getF3().getCA(),
	                                                                                             aAcl.getF3().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF3().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF3().getBP(),
	                                                                       aAcl.getF2().getOBP(),
	                                                                       aAcl.getF3().getOPC(),
	                                                                       aAcl.getF3().getCA(),
	                                                                       aAcl.getF3().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        //IPDSLAM - SAI
	        else if (isIPDSLAMSAI)
	        {	
	            if (!pigtailCreated) 
	            {
	                if (aAcl.getF3().getPGST() != null) 
	                {
	                    if (((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF3().getPGST().toUpperCase() + ":") >= 0) 
	                    {
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegmentIPDSLAM(aAcl.getF3().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF3().getBP(), 
	                                                                                             aAcl.getF2().getOBP(),
	                                                                                             aAcl.getF3().getOPC(),
	                                                                                             aAcl.getF3().getCA(),
	                                                                                             aAcl.getF3().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF3().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF3().getBP(),
	                                                                       aAcl.getF2().getOBP(),
	                                                                       aAcl.getF3().getOPC(),
	                                                                       aAcl.getF3().getCA(),
	                                                                       aAcl.getF3().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        else 
	        {
	            // FTTN - CO DSLAM                                
	            aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                   aAcl.getF3().getTERM(),
	                                                                   null,
	                                                                   null,
	                                                                   aAcl.getF3().getBP(),
	                                                                   aAcl.getF3().getOBP(),
	                                                                   aAcl.getF3().getOPC(),
	                                                                   aAcl.getF3().getCA(),
	                                                                   aAcl.getF3().getPR(),
	                                                                   wireCenterCLLI8);
	            
	            aCopperSegmentList[numSegs] = aCopperSegment;
	            numSegs++;
	        }           
	    }
	
	    // process F4 segment
	    if (aAcl.getF4() != null)
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F4 Segment Data");
	
	        // log F4 segment input
	        
	        terminalId = aAcl.getF4().getTERM();
		    outBindingPost = aAcl.getF4().getOBP();
		    inBindingPost = aAcl.getF4().getBP();
		    outPairColor = aAcl.getF4().getOPC();
		    inCableName = aAcl.getF4().getCA();
		    inCablePair =  aAcl.getF4().getPR();
		  
		    myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + terminalId + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + outBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + inBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + outPairColor + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + inCableName + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + inCablePair + ">");
	
	        // validate cable/pair info
		    if (validateCablePair)
		      validateCablePairInfo(aAcl.getF4().getCA(),
	                              aAcl.getF4().getPR(),
	                              4);
	                              
	
	        // create segment
	        if (isFTTnSAI) 
	        {
	            // FTTN - SAI
	            if (!pigtailCreated) 
	            {
	                if (aAcl.getF4().getPGST() != null) 
	                {
	                	if (((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF4().getPGST().toUpperCase() + ":") >= 0) 
	                    {
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegment(aAcl.getF4().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF4().getBP(), 
	                                                                                             aAcl.getF3().getOBP(),
	                                                                                             aAcl.getF4().getOPC(),
	                                                                                             aAcl.getF4().getCA(),
	                                                                                             aAcl.getF4().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF4().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF4().getBP(),
	                                                                       aAcl.getF3().getOBP(),
	                                                                       aAcl.getF4().getOPC(),
	                                                                       aAcl.getF4().getCA(),
	                                                                       aAcl.getF4().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        //IPDSLAM - SAI
	        else if (isIPDSLAMSAI)	        
	        {

	            if (!pigtailCreated)
	            {
	                if (aAcl.getF4().getPGST() != null)
	                {
	                    if (((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF4().getPGST().toUpperCase() + ":") >= 0)
	                    {
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegmentIPDSLAM(aAcl.getF4().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF4().getBP(), 
	                                                                                             aAcl.getF3().getOBP(),
	                                                                                             aAcl.getF4().getOPC(),
	                                                                                             aAcl.getF4().getCA(),
	                                                                                             aAcl.getF4().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else
	            {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF4().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF4().getBP(),
	                                                                       aAcl.getF3().getOBP(),
	                                                                       aAcl.getF4().getOPC(),
	                                                                       aAcl.getF4().getCA(),
	                                                                       aAcl.getF4().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        else 
	        {
	            // FTTN - CO DSLAM                                
	            aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                   aAcl.getF4().getTERM(),
	                                                                   null,
	                                                                   null,
	                                                                   aAcl.getF4().getBP(),
	                                                                   aAcl.getF4().getOBP(),
	                                                                   aAcl.getF4().getOPC(),
	                                                                   aAcl.getF4().getCA(),
	                                                                   aAcl.getF4().getPR(),
	                                                                   wireCenterCLLI8);
	            
	            aCopperSegmentList[numSegs] = aCopperSegment;
	            numSegs++;
	        }           
	    }
	
	    // process F5 segment
	    if (aAcl.getF5() != null)
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F5 Segment Data");
	
	        // log F5 segment input
	        terminalId = aAcl.getF5().getTERM();
		    outBindingPost = aAcl.getF5().getOBP();
		    inBindingPost = aAcl.getF5().getBP();
		    outPairColor = aAcl.getF5().getOPC();
		    inCableName = aAcl.getF5().getCA();
		    inCablePair =  aAcl.getF5().getPR();
		  
		   myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + terminalId + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + outBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + inBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + outPairColor + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + inCableName + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + inCablePair + ">");
	        
		    if (validateCablePair)
	         validateCablePairInfo(aAcl.getF5().getCA(),
	                              aAcl.getF5().getPR(),
	                              5);
	                              
	                              
	
	        // create segment
	        if (isFTTnSAI) 
	        {
	            // FTTN - SAI
	            if (!pigtailCreated) 
	            {
	                if (aAcl.getF5().getPGST() != null) 
	                {
	                	if (((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF5().getPGST().toUpperCase() + ":") >= 0)
	                	{
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegment(aAcl.getF5().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF5().getBP(), 
	                                                                                             aAcl.getF4().getOBP(),
	                                                                                             aAcl.getF5().getOPC(),
	                                                                                             aAcl.getF5().getCA(),
	                                                                                             aAcl.getF5().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF5().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF5().getBP(),
	                                                                       aAcl.getF4().getOBP(),
	                                                                       aAcl.getF5().getOPC(),
	                                                                       aAcl.getF5().getCA(),
	                                                                       aAcl.getF5().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        //IPDSLAM - SAI
	        else if (isIPDSLAMSAI)
	        {
	            if (!pigtailCreated)
	            {
	                if (aAcl.getF5().getPGST() != null)
	                {
	                    if (((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF5().getPGST().toUpperCase() + ":") >= 0)
	                    {
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegmentIPDSLAM(aAcl.getF5().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF5().getBP(), 
	                                                                                             aAcl.getF4().getOBP(),
	                                                                                             aAcl.getF5().getOPC(),
	                                                                                             aAcl.getF5().getCA(),
	                                                                                             aAcl.getF5().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else 
	            {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF5().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF5().getBP(),
	                                                                       aAcl.getF4().getOBP(),
	                                                                       aAcl.getF5().getOPC(),
	                                                                       aAcl.getF5().getCA(),
	                                                                       aAcl.getF5().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        else 
	        {
	            // FTTN - CO DSLAM                                
	            aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                   aAcl.getF5().getTERM(),
	                                                                   null,
	                                                                   null,
	                                                                   aAcl.getF5().getBP(),
	                                                                   aAcl.getF5().getOBP(),
	                                                                   aAcl.getF5().getOPC(),
	                                                                   aAcl.getF5().getCA(),
	                                                                   aAcl.getF5().getPR(),
	                                                                   wireCenterCLLI8);
	            
	            aCopperSegmentList[numSegs] = aCopperSegment;
	            numSegs++;
	        }           
	    }
	
	    // process F6 segment
	    if (aAcl.getF6() != null) 
	    {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F6 Segment Data");
	
	        // log F6 segment input
	        
	        terminalId = aAcl.getF6().getTERM();
		    outBindingPost = aAcl.getF6().getOBP();
		    inBindingPost = aAcl.getF6().getBP();
		    outPairColor = aAcl.getF6().getOPC();
		    inCableName = aAcl.getF6().getCA();
		    inCablePair =  aAcl.getF6().getPR();
		  
		    myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + terminalId + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + outBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + inBindingPost + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + outPairColor + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + inCableName + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + inCablePair + ">");
	        // validate cable/pair info
		    if (validateCablePair)
	          validateCablePairInfo(aAcl.getF6().getCA(),
	                              aAcl.getF6().getPR(),
	                              6);
	                              
	
	        // create segment
	        if (isFTTnSAI) 
	        {
	            // FTTN - SAI
	            if (!pigtailCreated) 
	            {
	                if (aAcl.getF6().getPGST() != null) 
	                {
	                	if (((String) myProperties.get("FTTN_PGST")).indexOf(":" + aAcl.getF6().getPGST().toUpperCase() + ":") >= 0)
	                	{
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegment(aAcl.getF6().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF6().getBP(), 
	                                                                                             aAcl.getF5().getOBP(),
	                                                                                             aAcl.getF6().getOPC(),
	                                                                                             aAcl.getF6().getCA(),
	                                                                                             aAcl.getF6().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF6().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF6().getBP(),
	                                                                       aAcl.getF5().getOBP(),
	                                                                       aAcl.getF6().getOPC(),
	                                                                       aAcl.getF6().getCA(),
	                                                                       aAcl.getF6().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        //IPDSLAM - SAI
	        else if (isIPDSLAMSAI)
	        {
	            if (!pigtailCreated) 
	            {
	                if (aAcl.getF6().getPGST() != null) 
	                {
	                    if (((String) myProperties.get("IPDSLAM_PGST")).indexOf(":" + aAcl.getF6().getPGST().toUpperCase() + ":") >= 0) 
	                    {
	                        pigtailCopperSegment  = NetworkTypeHelper.createPigtailCopperSegmentIPDSLAM(aAcl.getF6().getTERM(),
	                                                                                             null,
	                                                                                             null,
	                                                                                             aAcl.getF6().getBP(), 
	                                                                                             aAcl.getF5().getOBP(),
	                                                                                             aAcl.getF6().getOPC(),
	                                                                                             aAcl.getF6().getCA(),
	                                                                                             aAcl.getF6().getPR(),
	                                                                                             wireCenterCLLI8);
	                        aCopperSegmentList[0] = pigtailCopperSegment[0];
	                        aCopperSegmentList[1] = pigtailCopperSegment[1];  
	                        numSegs = 2;
	                        pigtailCreated = true;
	                    }
	                }
	            }
	            else 
	            {
	                aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1), 
	                                                                       aAcl.getF6().getTERM(),
	                                                                       null,
	                                                                       null,
	                                                                       aAcl.getF6().getBP(),
	                                                                       aAcl.getF5().getOBP(),
	                                                                       aAcl.getF6().getOPC(),
	                                                                       aAcl.getF6().getCA(),
	                                                                       aAcl.getF6().getPR(),
	                                                                       wireCenterCLLI8);
	
	                aCopperSegmentList[numSegs] = aCopperSegment;
	                numSegs++;
	            }
	        }
	        else
	        {
	            // FTTN - CO DSLAM                                
	            aCopperSegment = NetworkTypeHelper.createCopperSegment(String.valueOf(numSegs + 1),
	                                                                   aAcl.getF6().getTERM(),
	                                                                   null,
	                                                                   null,
	                                                                   aAcl.getF6().getBP(),
	                                                                   aAcl.getF6().getOBP(),
	                                                                   aAcl.getF6().getOPC(),
	                                                                   aAcl.getF6().getCA(),
	                                                                   aAcl.getF6().getPR(),
	                                                                   wireCenterCLLI8);
	            
	            aCopperSegmentList[numSegs] = aCopperSegment;
	            numSegs++;
	           
	          }           
	    }
	
	   //Make a copper segment with the exact number
	    CopperSegment[] aNewCopperSegmentList = new CopperSegment[numSegs];
	    for (int i = 0; i < numSegs; i++)
	        aNewCopperSegmentList[i] = aCopperSegmentList[i];
	
	    aFttn = NetworkTypeHelper.createFttn(null, aNewCopperSegmentList);
	
	    NetworkType aNetworkTypeChoice = new NetworkType();
	    aNetworkTypeChoice.aFttn(aFttn); 
	    
	    aNetworkTypeChoice.aFttn().aSegments.theValue(aNewCopperSegmentList);
		soaObj.aNetworkTypeChoice.theValue(aNetworkTypeChoice);
		
		myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
		
	}

private void displayBBNMSResponse(
		BisContext aContext
)
		throws
			DataNotFound,
			ObjectNotFound,
			NotImplemented,
			SystemFailure,
			BusinessViolation,
			AccessDenied,
			InvalidData,
			com.sbc.eia.bis.embus.service.access.ServiceException
	{
		String myMethodNameName = "ModifyInventory.forwardXmlToBBNMS()";
		myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
		

		ServiceOrderAssignmentOpt aServiceOrderAssignment = new ServiceOrderAssignmentOpt();
		aServiceOrderAssignment.theValue(soaObj);
		ExceptionDataOpt aStatus = (ExceptionDataOpt) IDLUtil.toOpt(ExceptionDataOpt.class, null);
		
		ObjectPropertySeqOpt aProperties = new ObjectPropertySeqOpt();
		aProperties.__default();

	
		ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);
		String xmlResp = null;
		
		try
		{
			Properties messageTags = new Properties();
			messageTags.setProperty("embus:MessageTag", SERVICE_FUNCTION_KEY);
			messageTags.setProperty("embus:ApplicationID", "ApplicationID");
			messageTags.setProperty("embus:MessageID", "MessageID");
			messageTags.setProperty("embus:ConversationKey", "ConversationKey");
			messageTags.setProperty("embus:LoggingKey", "LoggingKey");
			messageTags.setProperty("embus:ResponseMessageExpiration", "0");
	
			com.sbc.eia.idl.rm.ModifyFacilityInfoReturn mfiReturn = new com.sbc.eia.idl.rm.ModifyFacilityInfoReturn (aContext, aServiceOrderAssignment, aStatus,aProperties);
		    _modifyFacilityInfoResponse mfiResponse = new _modifyFacilityInfoResponse();
		    mfiResponse.aModifyFacilityInfoReturn(mfiReturn);
			
			_modifyFacilityInfoResponseBISMsg mfiResponseBISMsg = new _modifyFacilityInfoResponseBISMsg(mfiResponse);
			
			MMarshalObject msgs = mfiResponseBISMsg;
			
			xmlResp = VAXBDocumentWriter.encode(mfiResponseBISMsg);
			
		    xmlResp =
				SoapParserHelper.appendEMBUSSoapEnvelope(
					SoapParserHelper.removeTagsFromXML(xmlResp, "<vaxb:VAXB", "</vaxb:VAXB>"),
					messageTags);
			
			myLogger.log(LogEventId.INFO_LEVEL_1, "XML Resp: [" + xmlResp + "]");
			
		}
		catch (IOException ioe)
		{
			myLogger.log(
				LogEventId.AUDIT_TRAIL,
				"Conversion to XML from ModifyFacilityInfo Notification object failure: " + ioe.getMessage());
			ioe.printStackTrace();
			throwException(
				ExceptionCode.ERR_RM_IO_EXCEPTION,
				"XML conversion produced an IOException " + ioe.getMessage(),
				myProperties.get("BIS_NAME").toString(),
				Severity.UnRecoverable);
		}
	
		myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
	}

	
	public Fttn getAFttn() {
		return aFttn;
	}
	
	public String getAOrderID() {
		return aOrderID;
	}
	
	public String getAOrderNum() {
		return aOrderNum;
	}
	
	public ServiceOrderAssignment getSoaObj() {
		return soaObj;
	}
	
	
	public String getPrimaryNPANXX() {
		return primaryNPANXX;
	}
	
	public String getCircuitNumber() {
		return circuitNumber;
	}
	
	public boolean isFTTnSAI() {
		return isFTTnSAI;
	}
	
	public Fttp getAFttp() {
		return aFttp;
	}
	
	public String getDslamID() {
		return dslamID;
	}
	
	public String getOldDslamID() {
		return oldDslamID;
	}
}