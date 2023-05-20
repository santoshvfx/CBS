//$Id: ModifyInventory.java,v 1.34 2006/08/31 22:08:47 ds4987 Exp $
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
//# Date                | Author            | Notes
//# ----------------------------------------------------------------------------
//# 08/24/2006           Sriram Chevuturu    CR 9315 changes for MI SWITCH FTTN : No RCTI and No IOM BIS Calls
//# 08/31/2006           Doris Sunga         PREP LS4: Replaced import files com.~.BusinessInterface.rm.SOAC.SoacWireCenterTable and com.~.BusinessInterface.rm.SOAC.SoacWireCenterRow
//#                                          with com.sbc.eia.bis.rm.database.SoacWireCenterRow and com.sbc.eia.bis.rm.database.SoacWireCenterTable
//# 12/11/2006           Rene Duka           PR 18942097 and 19042150 (R3)/DR 172866: Fixed business logic in LFACS FTTN scenario.
//# 12/12/2006           Rene Duka           DR 171995 (R3): Fixed business logic of SWITCH FTTN scenario.
//# 02/20/2007			 Deepti				 DR 170206:Added retry logic to database connection.
//# 11/11/2007           Changchuan Yin      LS6, changed to for new client, BBNMS.
//# 11/14/2007           Changchuan Yin      update BisContext property:Application before accessing IOM BIS.
//# 11/14/2007           Changchuan Yin      Changed to call setClient(String).
//# 12/14/2007           Changchuan Yin      Changed to fix DR 80170- Email sent to OMS twice for pre-migration (FTTP). 
//# 01/07/2007           Changchuan Yin      Changed to fix MQC # 81486- Save wirecenter CLLI8 to avoid another db call.

package com.sbc.eia.bis.facades.rm.transactions.ModifyInventory;

import com.sbc.eia.idl.types.Severity;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.xml.bind.Marshaller;
import com.ibm.websphere.ce.cm.StaleConnectionException;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utility.database.DBConnection;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.EmailHelper;
import com.sbc.eia.bis.BusinessInterface.rm.ModifyInventory.Migration;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACGIServiceOrderResponseParser;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.RmNam.utilities.IomClient.IomClient;
import com.sbc.eia.bis.embus.service.access.DecoderException;
import com.sbc.eia.bis.embus.service.access.DefaultJAXBEncoderDecoder;
import com.sbc.eia.bis.embus.service.access.SBCLoggingIDProvider;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.facades.rm.transactions.ModifyFacilityInfo.ModifyFacilityInfo;
import com.sbc.eia.bis.facades.rm.transactions.ModifyFacilityInfo.ModifyFacilityInfoReturn;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.database.SoacWireCenterRow;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.bis.rm.database.WireCenterMigrationTable;
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
import com.sbc.eia.idl.iomip.UpdateIpProductDataReturn;
import com.sbc.eia.idl.iomip_types.Products;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponseBISMsg;
import com.sbc.eia.idl.rm_ls_types.CopperSegment;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransport;
import com.sbc.eia.idl.rm_ls_types.FiberSegment;
import com.sbc.eia.idl.rm_ls_types.Fttn;
import com.sbc.eia.idl.rm_ls_types.Fttp;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.OpticalLineTerminal;
import com.sbc.eia.idl.rm_ls_types.OpticalNetworkTerminal;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignment;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignmentOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectProperty;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.iomip.sessionbeans.IomipBIS;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.embus.service.client.*;


import java.io.*;

public class ModifyInventory extends TranBase {

	private static String LFACS_FTTN = "CUFTTNL";
	private static String LFACS_FTTP = "CUFTTPL";
	private static String SWITCH_FTTN = "CUFTTNS";
	
	private static String FTTN = NetworkTypeValues.FTTN;
	private static String FTTP = NetworkTypeValues.FTTP;
	
	private Migration myMigration = null;
	private Logger myLogger = null;
	private Hashtable myProperties = null;
	private Utility aUtility = null;
	
	public String transactionName = "ModifyInventory";
	private BisContext aContext = null;
	
	private String NETWORK_TYPE = null;
	private String circuitNumber = null;
	
	private ModifyFacilityInfo aMfi = null;
	private boolean mailSent = false;
	private boolean mailSentBBNMS = false;
	private ClientService aService = null;

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
	
	private static String PRE_MIGRATION ="OMS";
	private static String DURING_MIGRATION ="OMS:BBNMS";
	private static String POST_MIGRATION ="BBNMS";

	protected final String SOAP_ENVELOP_HEADER =
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "\n<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\""
			+ " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\""
			+ " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
	        + " xsi:schemaLocation=\"http://schemas.xmlsoap.org/soap/envelope/ rm.xsd urn:RmFacadePackage.rm.idl.eia.sbc.com Body.xsd urn:soap.embus.sbc.com Header.xsd \">";
	protected final String SOAP_ENVELOP_FOOTER = "</soap:Body>\n</soap:Envelope>";

	

	public ModifyInventory(
			Hashtable properties,
			Utility utility,
			Logger aLogger) {
		PROPERTIES = properties;
		myProperties = properties;
		myLogger = aLogger;
		aUtility = utility;
		aContext = null;
		myMigration = new Migration("","");
	}

   private void doPreMigrationFttn(
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
        .EnvelopeTypeImpl envelope)
    throws
        InvalidData,
        AccessDenied,
        BusinessViolation,
        SystemFailure,
        NotImplemented,
        ObjectNotFound,
        DataNotFound,
        Throwable {

	    String myMethodName = "ModifyInventory:doPreMigrationFttn()";
	    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
	
        ModifyFacilityInfoReturn aReturn = null;
	
        String wireCenterCLLI8 = myMigration.getWireCenterCLLI8();
        
        myLogger.log(LOG_INPUT_DATA, "Wire Center CLLI8 =<" + wireCenterCLLI8 + ">");
        
	    SOACGIServiceOrderResponseParser soacGIParser = new SOACGIServiceOrderResponseParser(myProperties, aUtility,envelope, wireCenterCLLI8,true);
	    circuitNumber = soacGIParser.getCircuitNumber();
	    NETWORK_TYPE = FTTN;
	    String primaryNPANXX = soacGIParser.getPrimaryNPANXX();
	    
	    String aOrderNum = soacGIParser.getAOrderNum();
	    String aOrderID= soacGIParser.getAOrderID();
	    Fttn aFttn = soacGIParser.getAFttn();
	    boolean isFTTnSAI = soacGIParser.isFTTnSAI();
	    	
	    myLogger.log(LogEventId.INPUT_DATA, "|NETWORK_TYPE =" + NETWORK_TYPE +
	    		                            "|CircuitNumber=" + circuitNumber +
	    		                            "|PrimaryNPANXX=" + primaryNPANXX +
											"|WireCenterCLLI8=" + wireCenterCLLI8 +
											"|OrderNum=" + aOrderNum + 
											"|OrderID=" + aOrderNum +
											"|IsFTTnSAI=" + isFTTnSAI +
											"|Fttn (object)=" + aFttn.toString() + "|");
	    
	    try {
	    	aReturn =
	    		aMfi.compareAndUpdateGranite(
	    				circuitNumber,
						null,
						wireCenterCLLI8,
						primaryNPANXX,
						null,
						aFttn,
						null,
						null,
						null,
						transactionName,
						null);//no old TRE for FTTN LFACS
	    } catch (NotImplemented e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE.");
	    	aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (SystemFailure e) {
	    	aMfi.setSendEmailStatus(true);
	    	   myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE: SystemFailure.");
	    	aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (InvalidData e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE.");
	    	aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (ObjectNotFound e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE.");
	    	aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (AccessDenied e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE.");
	    	aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (BusinessViolation e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE.");
	    	aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (DataNotFound e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE.");
	    	aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (Exception e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE: System Exception");
	    	myLogger.log(
	    			LogEventId.ERROR,
					"Error Message [" + e.getMessage() + "]");
	    	myLogger.log(
	    			LogEventId.ERROR,
					"Error String [" + e.toString() + "]");
	    }
   
	    // if FTTN - SAI scenario, determine if it's VLAN affecting
	    if (isFTTnSAI) {
	    	
	    	aMfi.setVLANAffecting(aReturn);
	    	
	    	// call IOM BIS
	    	if (aMfi.getVlanAffecting() == true) {
	    		try {
	    			updateIpProductData(
	    					aMfi.getVlanAffecting(),
							circuitNumber,
							NETWORK_TYPE,
							aOrderNum,
							aOrderID);
	    		} catch (Throwable e) {
	    			myLogger.log(
	    					LogEventId.ERROR,
							"Error message [" + e.getMessage() + "]");
	    			myLogger.log(
	    					LogEventId.ERROR,
							"Error String [" + e.toString() + "]");
	    			
	    			throw e;
	    		}
	    	}
	    }	
	    
	    try {
	    	prepareAndSendEmail();
	    } 
	    catch (Exception ex) {
	    	myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO OMS.");
	    	myLogger.log(
	    			LogEventId.ERROR,
					"Error message [" + ex.getMessage() + "]");
	    	myLogger.log(
	    			LogEventId.ERROR,
					"Error String [" + ex.toString() + "]");
	    	
	    	throw ex;
	    }
	    
	    myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
}

	private void doLfacsFttn(
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
	        .EnvelopeTypeImpl envelope)
	    throws
	        InvalidData,
	        AccessDenied,
	        BusinessViolation,
	        SystemFailure,
	        NotImplemented,
	        ObjectNotFound,
	        DataNotFound,
	        Throwable {
	
	    String myMethodName = "ModifyInventory:doLfacsFttn()";
	    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
	    //do FTTNL
	    NETWORK_TYPE = FTTN;
	    CopperSegment[] aCopperSegmentList = new CopperSegment[6];
	    int numSegs = 0;
	
	    com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.ACLType aAcl = null;
	
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
	        //circuitNumber =   aAcl.getCTID();
	    }catch(Exception e){}
	
	    myLogger.log(LOG_INPUT_DATA, "CircuitID=<" + circuitNumber + ">");
	
	    String primaryNPANXX =
	        envelope.getHeader().getAdditionalInfo().getTtWireCenter();
	    myLogger.log(LOG_INPUT_DATA, "primaryNPANXX=<" + primaryNPANXX + ">");
	
	    if (primaryNPANXX == null || primaryNPANXX.trim().length() != 6)
	        throwException(
	            ExceptionCode.ERR_RM_MISSING_NPANXX,
	            "Missing or invalid required data: NPA NXX",
	            this.getEnv("BIS_NAME"),
	            Severity.UnRecoverable);
	
	    String wireCenterCLLI8 = myMigration.getWireCenterCLLI8();
	   
	    myLogger.log(LOG_INPUT_DATA, "Wire Center CLLI8 =<" + wireCenterCLLI8 + ">");
		
	    String aOrderNum =
	        envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
	    String aOrderID = null;
	    // envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
	    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderNum + ">");
	    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderID + ">");
	
	    aMfi =
	        new ModifyFacilityInfo(
	            aUtility,
	            myProperties,
	            aContext.aProperties[3].aValue);
	
	    aMfi.setMIObjectForEmailCallback(this);
	
	    ModifyFacilityInfoReturn aReturn = null;
	
	   
	    CopperSegment aCopperSegment = null;
	
	    String aBP = null;
	    String emptyString = "";
	
	    boolean isFTTnSAI = false;
	    boolean pigtailCreated = false; 
	    CopperSegment[] pigtailCopperSegment = new CopperSegment[2];  
	
	    // determine if FTTN - SAI scenario
	    if ((aAcl.getF1() != null) 
	        && (aAcl.getF1().getPGST() != null) 
	        && (aAcl.getF1().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF1().getPGST().equalsIgnoreCase("73RMD")))
	        isFTTnSAI = true;
	        
	    if ((aAcl.getF2() != null) 
	        && (aAcl.getF2().getPGST() != null) 
	        && (aAcl.getF2().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF2().getPGST().equalsIgnoreCase("73RMD")))
	        isFTTnSAI = true;
	        
	    if ((aAcl.getF3() != null) 
	        && (aAcl.getF3().getPGST() != null) 
	        && (aAcl.getF3().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF3().getPGST().equalsIgnoreCase("73RMD")))
	        isFTTnSAI = true;
	        
	    if ((aAcl.getF4() != null) 
	        && (aAcl.getF4().getPGST() != null) 
	        && (aAcl.getF4().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF4().getPGST().equalsIgnoreCase("73RMD")))
	        isFTTnSAI = true;
	
	    if ((aAcl.getF5() != null) 
	        && (aAcl.getF5().getPGST() != null) 
	        && (aAcl.getF5().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF5().getPGST().equalsIgnoreCase("73RMD")))
	        isFTTnSAI = true;
	
	    if ((aAcl.getF6() != null) 
	        && (aAcl.getF6().getPGST() != null) 
	        && (aAcl.getF6().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF6().getPGST().equalsIgnoreCase("73RMD")))
	        isFTTnSAI = true;
	        
	    // log
	    if (isFTTnSAI) {
	        myLogger.log(LogEventId.INFO_LEVEL_1,"FTTN - SAI Scenario.");
	    }
	    else {
	        myLogger.log(LogEventId.INFO_LEVEL_1,"FTTN - CO DSLAM Scenario.");
	    }    
	    
	
	    // process F1 segment
	    if (aAcl.getF1() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F1 Segment Data");
	
	        // log F1 segment input
	        
	        myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + aAcl.getF1().getTERM() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + aAcl.getF1().getOBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + aAcl.getF1().getBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + aAcl.getF1().getOPC() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + aAcl.getF1().getCA() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + aAcl.getF1().getPR() + ">");
	
	        
	        // validate cable/pair info
	        validateCablePairInfo(aAcl.getF1().getCA(), 
	                              aAcl.getF1().getPR(),
	                              1);
	
	        // create segment
	        if (isFTTnSAI) {
	            // FTTN - SAI
	            if (aAcl.getF1().getPGST() != null) {
	                if (aAcl.getF1().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF1().getPGST().equalsIgnoreCase("73RMD")) {
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
	        else {
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
	    if (aAcl.getF2() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F2 Segment Data");
	
	        // log F2 segment input
	        myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + aAcl.getF2().getTERM() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + aAcl.getF2().getOBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + aAcl.getF2().getBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + aAcl.getF2().getOPC() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + aAcl.getF2().getCA() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + aAcl.getF2().getPR() + ">");
	
	        // validate cable/pair info
	        validateCablePairInfo(aAcl.getF2().getCA(),
	                              aAcl.getF2().getPR(),
	                              2);
	
	        // create segment
	        if (isFTTnSAI) {
	            // FTTN - SAI
	            if (!pigtailCreated) {
	                if (aAcl.getF2().getPGST() != null) {
	                    if (aAcl.getF2().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF2().getPGST().equalsIgnoreCase("73RMD")) {
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
	        else {
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
	    if (aAcl.getF3() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F3 Segment Data");
	
	        // log F3 segment input
	        myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + aAcl.getF3().getTERM() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + aAcl.getF3().getOBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + aAcl.getF3().getBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + aAcl.getF3().getOPC() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + aAcl.getF3().getCA() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + aAcl.getF3().getPR() + ">");
	
	        // validate cable/pair info
	        validateCablePairInfo(aAcl.getF3().getCA(),
	                              aAcl.getF3().getPR(),
	                              3);
	
	        // create segment
	        if (isFTTnSAI) {
	            // FTTN - SAI
	            if (!pigtailCreated) {
	                if (aAcl.getF3().getPGST() != null) {
	                    if (aAcl.getF3().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF3().getPGST().equalsIgnoreCase("73RMD")) {
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
	        else {
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
	    if (aAcl.getF4() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F4 Segment Data");
	
	        // log F4 segment input
	        myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + aAcl.getF4().getTERM() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + aAcl.getF4().getOBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + aAcl.getF4().getBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + aAcl.getF4().getOPC() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + aAcl.getF4().getCA() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + aAcl.getF4().getPR() + ">");
	
	        // validate cable/pair info
	        validateCablePairInfo(aAcl.getF4().getCA(),
	                              aAcl.getF4().getPR(),
	                              4);
	
	        // create segment
	        if (isFTTnSAI) {
	            // FTTN - SAI
	            if (!pigtailCreated) {
	                if (aAcl.getF4().getPGST() != null) {
	                    if (aAcl.getF4().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF4().getPGST().equalsIgnoreCase("73RMD")) {
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
	        else {
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
	    if (aAcl.getF5() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F5 Segment Data");
	
	        // log F5 segment input
	        myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + aAcl.getF5().getTERM() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + aAcl.getF5().getOBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + aAcl.getF5().getBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + aAcl.getF5().getOPC() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + aAcl.getF5().getCA() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + aAcl.getF5().getPR() + ">");
	
	        // validate cable/pair info
	        validateCablePairInfo(aAcl.getF5().getCA(),
	                              aAcl.getF5().getPR(),
	                              5);
	
	        // create segment
	        if (isFTTnSAI) {
	            // FTTN - SAI
	            if (!pigtailCreated) {
	                if (aAcl.getF5().getPGST() != null) {
	                    if (aAcl.getF5().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF5().getPGST().equalsIgnoreCase("73RMD")) {
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
	        else {
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
	    if (aAcl.getF6() != null) {
	        myLogger.log(LogEventId.INFO_LEVEL_1, "Parsing F6 Segment Data");
	
	        // log F6 segment input
	        myLogger.log(LOG_INPUT_DATA, "Terminal ID=<" + aAcl.getF6().getTERM() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutBindingPost=<" + aAcl.getF6().getOBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "InBindingPost=<" + aAcl.getF6().getBP() + ">");
	        myLogger.log(LOG_INPUT_DATA, "OutPairColor=<" + aAcl.getF6().getOPC() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Cable=<" + aAcl.getF6().getCA() + ">");
	        myLogger.log(LOG_INPUT_DATA, "Pair=<" + aAcl.getF6().getPR() + ">");
	
	        // validate cable/pair info
	        validateCablePairInfo(aAcl.getF6().getCA(),
	                              aAcl.getF6().getPR(),
	                              6);
	
	        // create segment
	        if (isFTTnSAI) {
	            // FTTN - SAI
	            if (!pigtailCreated) {
	                if (aAcl.getF6().getPGST() != null) {
	                    if (aAcl.getF6().getPGST().equalsIgnoreCase("73RMB") || aAcl.getF6().getPGST().equalsIgnoreCase("73RMD")) {
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
	        else {
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
	
	    Fttn aFttn = null;
	
	    //now make a copper segment with the exact number
	    CopperSegment[] aNewCopperSegmentList = new CopperSegment[numSegs];
	    for (int i = 0; i < numSegs; i++)
	        aNewCopperSegmentList[i] = aCopperSegmentList[i];
	
	    aFttn = NetworkTypeHelper.createFttn(null, aNewCopperSegmentList);
	
	    try {
	        aReturn =
	            aMfi.compareAndUpdateGranite(
	                circuitNumber,
	                null,
	                wireCenterCLLI8,
	                primaryNPANXX,
	                null,
	                aFttn,
	                null,
	                null,
	                null,
	                transactionName,
	                null);//no old TRE for FTTN LFACS
	    } catch (NotImplemented e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (SystemFailure e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (InvalidData e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (ObjectNotFound e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (AccessDenied e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (BusinessViolation e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (DataNotFound e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (Exception e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        myLogger.log(
	            LogEventId.ERROR,
	            "Error Message [" + e.getMessage() + "]");
	        myLogger.log(
	            LogEventId.ERROR,
	            "Error String [" + e.toString() + "]");
	    }
	
	    // if FTTN - SAI scenario, determine if it's VLAN affecting
	    if (isFTTnSAI) {
	
	        aMfi.setVLANAffecting(aReturn);
	
	        // call IOM BIS
	        if (aMfi.getVlanAffecting() == true) {
	            try {
	                updateIpProductData(
	                    aMfi.getVlanAffecting(),
	                    circuitNumber,
	                    NETWORK_TYPE,
	                    aOrderNum,
	                    aOrderID);
	            } catch (Throwable e) {
	                try {
	                    prepareAndSendEmail();
	                } catch (Exception ex) {
	                    myLogger.log(
	                        LogEventId.ERROR,
	                        "FAILED TO SEND EMAIL TO OMS.");
	                    myLogger.log(
	                        LogEventId.ERROR,
	                        "Error message [" + ex.getMessage() + "]");
	                    myLogger.log(
	                        LogEventId.ERROR,
	                        "Error String [" + ex.toString() + "]");
	
	                    throw e;
	                }
	            }
	        } 
	        else {
	            try {
	                prepareAndSendEmail();
	            } 
	            catch (Exception ex) {
	                myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO OMS.");
	                myLogger.log(
	                    LogEventId.ERROR,
	                    "Error message [" + ex.getMessage() + "]");
	                myLogger.log(
	                    LogEventId.ERROR,
	                    "Error String [" + ex.toString() + "]");
	
	                throw ex;
	            }
	        }
	    }
	    
	    
	    myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
	}
  
	
	private ExceptionDataOpt validateCircuitID(SOACGIServiceOrderResponseParser soacGIParser)
  {
		ExceptionData aStatus = new ExceptionData();
		ExceptionDataOpt aStatusOpt = null;
		String aErrDesc = null;
		String circuitID = soacGIParser.getCircuitNumber();
		
		if (circuitID == null || circuitID.trim().length() == 0)
		{
			aStatus.aCode = ExceptionCode.ERR_RM_MISSING_CIRCUIT_ID;
			aErrDesc = "Missing Circuit ID in SOACGI message.";
			aStatus.aDescription =  aErrDesc;
			aStatus.aOrigination = (StringOpt) IDLUtil.toOpt((String) myProperties.get("BIS_NAME").toString());
			aStatus.aSeverity = (SeverityOpt) IDLUtil.toOpt((SeverityOpt.class), Severity.UnRecoverable);
			aStatusOpt = (ExceptionDataOpt) IDLUtil.toOpt(ExceptionDataOpt.class, aStatus);
			
			myLogger.log(
					LogEventId.INFO_LEVEL_1,
			"RM-InvalidData-02054: Missing Circuit ID in SOACGI message. ");
			
			
		}
		else
		{
			aStatusOpt = (ExceptionDataOpt) IDLUtil.toOpt(ExceptionDataOpt.class, null);	
			
		}
		
		return aStatusOpt;
		
  }
	private String buildPostMigrationMsg(
	   Object envelopObj)
	    throws
	        InvalidData,
	        AccessDenied,
	        BusinessViolation,
	        SystemFailure,
	        NotImplemented,
	        ObjectNotFound,
	        DataNotFound,
	        Throwable 
	 {
	
	        String myMethodName = "ModifyInventory:buildPostMigrationMsg()";
	        myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	        
	        String xmlResp = null;
			SOACGIServiceOrderResponseParser soacGIParser = null;

		    String wireCenterCLLI8 = myMigration.getWireCenterCLLI8();
		    
		    myLogger.log(LOG_INPUT_DATA, "Wire Center CLLI8 =<" + wireCenterCLLI8 + ">");
			
	        
	        if (envelopObj instanceof com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.impl.EnvelopeTypeImpl)
	        {      
	        	   myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + "parse LFACS FTTN");
	               com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.impl.EnvelopeTypeImpl envelope = (com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.impl.EnvelopeTypeImpl)envelopObj;
	               soacGIParser = new SOACGIServiceOrderResponseParser(myProperties, aUtility, envelope, wireCenterCLLI8, false); //Do not need to validate cablePairInformation in BBNMS
	        }
	        else if (envelopObj instanceof com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces.impl.EnvelopeTypeImpl)
		    {      
	        	   myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + "parse LFACS FTTP");
		           com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces.impl.EnvelopeTypeImpl envelope = (com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces.impl.EnvelopeTypeImpl)envelopObj;
		           soacGIParser = new SOACGIServiceOrderResponseParser(myProperties, aUtility,envelope, wireCenterCLLI8);
	        }
	        else if (envelopObj instanceof com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.impl.EnvelopeTypeImpl)
		    {  
	        	   myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + "parse SWITCH FTTN");
		           com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.impl.EnvelopeTypeImpl envelope = (com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.impl.EnvelopeTypeImpl)envelopObj;
		           soacGIParser = new SOACGIServiceOrderResponseParser(myProperties, aUtility,envelope, wireCenterCLLI8);
	        }
	       
	        ServiceOrderAssignment soaObj = soacGIParser.getSoaObj();
	        ServiceOrderAssignmentOpt aServiceOrderAssignment = new ServiceOrderAssignmentOpt();
			aServiceOrderAssignment.theValue(soaObj);
			
            // BisContext
			ObjectPropertySeqOpt aProperties = new ObjectPropertySeqOpt();
			aProperties.__default();
	        ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);
			
	        // Validation/Exceptions
			ExceptionDataOpt aStatusOpt = null;
			
			if (soacGIParser != null)
			  aStatusOpt = validateCircuitID(soacGIParser);
			
            try
			{
				Properties messageTags = new Properties();
				
				String application = aContext.aProperties[0].aValue;
			    String customerName = aContext.aProperties[1].aValue;
			    String businessUnit = aContext.aProperties[2].aValue;
			    String loggingInformation = aContext.aProperties[3].aValue;
			    String jmsMessageID = aContext.aProperties[4].aValue;
			   
			    messageTags.setProperty("embus:MessageID", jmsMessageID);
				messageTags.setProperty("embus:LoggingKey", loggingInformation);
				messageTags.setProperty("embus:ApplicationID",application);
				messageTags.setProperty("embus:MessageTag", "ModifyInventory");
		        messageTags.setProperty("embus:ConversationKey", "ConversationKey");
		        messageTags.setProperty("embus:ResponseMessageExpiration", "0");
				
				com.sbc.eia.idl.rm.ModifyFacilityInfoReturn mfiReturn = new com.sbc.eia.idl.rm.ModifyFacilityInfoReturn (aContext, aServiceOrderAssignment, aStatusOpt, aProperties);
	        	
				_modifyFacilityInfoResponse mfiResponse = new _modifyFacilityInfoResponse();
			    
				mfiResponse.aModifyFacilityInfoReturn(mfiReturn);
				
				_modifyFacilityInfoResponseBISMsg mfiResponseBISMsg = new _modifyFacilityInfoResponseBISMsg(mfiResponse);
				
				MMarshalObject msgs = mfiResponseBISMsg;
				
				xmlResp = VAXBDocumentWriter.encode(mfiResponseBISMsg);
			   	
				xmlResp =
					SoapParserHelper.appendEMBUSSoapEnvelope(
						SoapParserHelper.removeTagsFromXML(xmlResp, "<vaxb:VAXB", "</vaxb:VAXB>"),
						messageTags);
				System.out.println("JMS Message = " + xmlResp);
				
				myLogger.log(LogEventId.INFO_LEVEL_1, "ModifyInventory Notification XML Resp: [" + xmlResp + "]");
				
			}
			catch (IOException ioe)
			{
				myLogger.log(
					LogEventId.AUDIT_TRAIL,
					"Conversion to XML from ModifyInventory notification object failed: " + ioe.getMessage());
				ioe.printStackTrace();
				throwException(
					ExceptionCode.ERR_RM_IO_EXCEPTION,
					"XML conversion produced an IOException " + ioe.getMessage(),
					(String) myProperties.get("BIS_NAME").toString(),
					Severity.UnRecoverable);
			}
	     
		 myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
		
	     return xmlResp;
	}

private void doLfacsFttp(
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
        .EnvelopeTypeImpl envelope)
    throws
        InvalidData,
        AccessDenied,
        BusinessViolation,
        SystemFailure,
        NotImplemented,
        ObjectNotFound,
        DataNotFound,
        Throwable {

    String myMethodName = "ModifyInventory:doLfacsFttp()";
    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    int numSegs = 0;
    NETWORK_TYPE = FTTP;
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

    myLogger.log(LOG_INPUT_DATA, "CircuitID=<" + circuitNumber + ">");

    FiberSegment aFiberSegment = null;

    String primaryNPANXX =
        envelope.getHeader().getAdditionalInfo().getTtWireCenter();
    myLogger.log(LOG_INPUT_DATA, "primaryNPANXX=<" + primaryNPANXX + ">");

    if (primaryNPANXX == null || primaryNPANXX.length() != 6)
        throwException(
            ExceptionCode.ERR_RM_MISSING_NPANXX,
            "Missing or invalid required data: NPA NXX",
            this.getEnv("BIS_NAME"),
            Severity.UnRecoverable);

    String wireCenterCLLI8 = myMigration.getWireCenterCLLI8();

    myLogger.log(LOG_INPUT_DATA, "Wire Center CLLI8 =<" + wireCenterCLLI8 + ">");
	
    String aOrderNum =
        envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
    String aOrderID = null;
    // envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderNum + ">");
    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderID + ">");

    aMfi =
        new ModifyFacilityInfo(
            aUtility,
            myProperties,
            aContext.aProperties[3].aValue);

    aMfi.setMIObjectForEmailCallback(this);

    ModifyFacilityInfoReturn aReturn = null;

   
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
    Fttp aFttp = null;

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
    //===
    try {
        aReturn =
            aMfi.compareAndUpdateGranite(
                circuitNumber,
                null,
                wireCenterCLLI8,
                primaryNPANXX,
                null,
                null,
                aFttp,
                null,
                null,
                transactionName,
                null);//no old TRE for FTTP LFACS;
    } catch (NotImplemented e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (SystemFailure e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (InvalidData e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (ObjectNotFound e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (AccessDenied e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (BusinessViolation e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (DataNotFound e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (Exception e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        myLogger.log(
            LogEventId.ERROR,
            "Error Message [" + e.getMessage() + "]");
        myLogger.log(
            LogEventId.ERROR,
            "Error String [" + e.toString() + "]");
    }

    aMfi.setVLANAffecting(aReturn);
    // call IOM BIS
    if (aMfi.getVlanAffecting() == true) 
    {
        try {
            updateIpProductData(
                aMfi.getVlanAffecting(),
                circuitNumber,
                NETWORK_TYPE,
                aOrderNum,
                aOrderID);
        } 
        catch (Throwable e) 
		{    aMfi.setSendEmailStatus(true);
		
		}
    }
    /*
     try 
	 {
        prepareAndSendEmail();
     } 
     catch (Exception ex) 
	 {
     	myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO OMS.");
     	myLogger.log(
     			LogEventId.ERROR,
				"Error Message [" + ex.getMessage() + "]");
     	myLogger.log(
     			LogEventId.ERROR,
				"Error String [" + ex.toString() + "]");
     	throw ex;
      }
      */
     myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
}

	private void doPreMigrationFttp(
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
	        .EnvelopeTypeImpl envelope)
	    throws
	        InvalidData,
	        AccessDenied,
	        BusinessViolation,
	        SystemFailure,
	        NotImplemented,
	        ObjectNotFound,
	        DataNotFound,
	        Throwable {
	
	    String myMethodName = "ModifyInventory:doPreMigrationFttp()";
	    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
	
	    ModifyFacilityInfoReturn aReturn = null;
	    
	    String wireCenterCLLI8 = myMigration.getWireCenterCLLI8();
	    myLogger.log(LOG_INPUT_DATA, "Wire Center CLLI8 =<" + wireCenterCLLI8 + ">");
	    
	    SOACGIServiceOrderResponseParser soacGIParser = new SOACGIServiceOrderResponseParser(myProperties, aUtility,envelope, wireCenterCLLI8);
	    NETWORK_TYPE = FTTP;
	    circuitNumber = soacGIParser.getCircuitNumber();
	    String primaryNPANXX = soacGIParser.getPrimaryNPANXX();
	    
	    String aOrderNum = soacGIParser.getAOrderNum();
	    String aOrderID= soacGIParser.getAOrderID();
	    Fttp aFttp = soacGIParser.getAFttp();
	    boolean isFTTnSAI = soacGIParser.isFTTnSAI();
	    	
	    myLogger.log(LogEventId.INPUT_DATA, "|NETWORK_TYPE =" + NETWORK_TYPE +
	    		"|CircuitNumber=" + circuitNumber +
                "|PrimaryNPANXX=" + primaryNPANXX +
				"|WireCenterCLLI8=" + wireCenterCLLI8 +
				"|OrderNum=" + aOrderNum + 
				"|OrderID=" + aOrderNum +
				"|IsFTTnSAI=" + isFTTnSAI +
				"|Fttp (object)=" + aFttp.toString()+ "|");
	   
	    try {
	        aReturn =
	            aMfi.compareAndUpdateGranite(
	                circuitNumber,
	                null,
	                wireCenterCLLI8,
	                primaryNPANXX,
	                null,
	                null,
	                aFttp,
	                null,
	                null,
	                transactionName,
	                null);//no old TRE for FTTP LFACS;
	    } catch (NotImplemented e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (SystemFailure e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE: SystemFailure.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (InvalidData e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (ObjectNotFound e) {
	        aMfi.setSendEmailStatus(true);
	       myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (AccessDenied e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (BusinessViolation e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (DataNotFound e) {
	        aMfi.setSendEmailStatus(true);
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        aMfi.logException(e.aExceptionData, e.aContext);
	    } catch (Exception e) {
	    	aMfi.setSendEmailStatus(true);
	    	myLogger.log(
	    			LogEventId.ERROR,
	    	"FAILED TO COMPARE AND UPDATE GRANITE: System Exception.");
	        myLogger.log(
	            LogEventId.ERROR,
	            "FAILED TO COMPARE AND UPDATE GRANITE.");
	        myLogger.log(
	            LogEventId.ERROR,
	            "Error Message [" + e.getMessage() + "]");
	        myLogger.log(
	            LogEventId.ERROR,
	            "Error String [" + e.toString() + "]");
	    }
	
	    aMfi.setVLANAffecting(aReturn);
	
	    // call IOM BIS
	    if (aMfi.getVlanAffecting() == true) {
	    	try {
	    		updateIpProductData(
	    				aMfi.getVlanAffecting(),
						circuitNumber,
						NETWORK_TYPE,
						aOrderNum,
						aOrderID);
	    	} 
	    	catch (Throwable e) {
	    		myLogger.log(
	    				LogEventId.ERROR,
						"Error Message [" + e.getMessage() + "]");
	    		myLogger.log(
	    				LogEventId.ERROR,
						"Error String [" + e.toString() + "]");
	    		
	    		throw e;
	    	}
	    }
	    
	    try {
	    	prepareAndSendEmail();
	    } 
	    catch (Exception ex) {
	    	myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO OMS.");
	    	myLogger.log(
	    			LogEventId.ERROR,
					"Error Message [" + ex.getMessage() + "]");
	    	myLogger.log(
	    			LogEventId.ERROR,
					"Error String [" + ex.toString() + "]");
	    	throw ex;
	    }
	    myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
	}

	private void doSwitchFttn(
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
        .EnvelopeTypeImpl envelope)
    throws
        InvalidData,
        AccessDenied,
        BusinessViolation,
        SystemFailure,
        NotImplemented,
        ObjectNotFound,
        DataNotFound,
        Throwable {

    String myMethodName = "ModifyInventory:doSwitchFttn()";
    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    NETWORK_TYPE = FTTN;

    CopperSegment[] aCopperSegmentList = new CopperSegment[1];

    com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.ACLType aAcl = null;

    if(envelope.getHeader().getObject().equals("MCT"))
            aAcl = envelope.getGIOUTBMCT().getBODY().getGRANITE().getACL();
    else if(envelope.getHeader().getObject().equals("FTR"))
            aAcl = envelope.getGIOUTBFTR().getBODY().getGRANITE().getACL();


    try
    {
        circuitNumber = aAcl.getCTID().replace('.','/');
        //circuitNumber =   aAcl.getCTID();
    }catch(Exception e){}

    if (circuitNumber == null)
	  {
    	myLogger.log(LOG_INPUT_DATA, "Circuit ID=<" + circuitNumber + ", and set as an empty string in xml response.>");
    	circuitNumber = ""; // To prevent XML conversion error
	  }
    myLogger.log(LOG_INPUT_DATA, "Circuit ID=<" + circuitNumber + ">");

    String aOrderNum =
        envelope.getHeader().getAdditionalInfo().getTtOrderNumber();
    String aOrderID = null;
   
    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderNum + ">");
    myLogger.log(LOG_INPUT_DATA, "OrderNumber=<" + aOrderID + ">");

    String dslamID = aAcl.getNEWTRE();
    myLogger.log(LOG_INPUT_DATA, "DSLAM ID=<" + dslamID + ">");

    String oldDslamID = aAcl.getOLDTRE();
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


    aMfi =
        new ModifyFacilityInfo(
            aUtility,
            myProperties,
            aContext.aProperties[3].aValue);

    aMfi.setMIObjectForEmailCallback(this);
    ModifyFacilityInfoReturn aReturn = null;

    String primaryNPANXX =
        envelope.getHeader().getAdditionalInfo().getTtWireCenter();
    myLogger.log(LOG_INPUT_DATA, "primaryNPANXX=<" + primaryNPANXX + ">");

    if (primaryNPANXX == null || primaryNPANXX.length() != 6)
        throwException(
            ExceptionCode.ERR_RM_MISSING_NPANXX,
            "Missing or invalid required data: NPA NXX",
            this.getEnv("BIS_NAME"),
            Severity.UnRecoverable);

    String wireCenterCLLI8 = myMigration.getWireCenterCLLI8();

    myLogger.log(LOG_INPUT_DATA, "Wire Center CLLI8 =<" + wireCenterCLLI8 + ">");
	
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
    
    Fttn aFttn = NetworkTypeHelper.createFttn(aDSLAMTransport, aCopperSegmentList);

    Fttn tFttn = new Fttn(aFttn.aDSLAM, null);
    
    com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.ModifyFacilityInfo aMFI_busInterface =
        new com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.ModifyFacilityInfo(aUtility, getPROPERTIES());

    try {
        aReturn = aMFI_busInterface.sendRequest(aContext,
                                                primaryNPANXX,
                                                circuitNumber,
                                                null,             // taperCode,
                                                tFttn,
                                                null,             // aFttp
                                                true,
                                                null,             // SoacDefect[] aDefectList
                                                oldDslamID);      // String OldTRE

        myLogger.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");

    } 
    catch (Exception e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE GRANITE.");
        myLogger.log(LogEventId.ERROR, "Error Message [" + e.getMessage() + "]");
        myLogger.log(LogEventId.ERROR, "Error String [" + e.toString() + "]");
    }
   
    // call IOM BIS
    if (!(dslamID).equalsIgnoreCase(oldDslamID)) 
    {
        try {
            updateIpProductData(
                true,                  // aMfi.getVlanAffecting(),
                circuitNumber,
                NETWORK_TYPE,
                aOrderNum,
                aOrderID);
        } catch (Throwable e) {
        	if (aMfi.getSendEmailStatus() == true)
                try {
                    prepareAndSendEmail();
                } catch (Exception ex) {
                    myLogger.log(
                        LogEventId.ERROR,
                        "FAILED TO SEND EMAIL TO OMS.");
                    myLogger.log(
                        LogEventId.ERROR,
                        "Error Message [" + e.getMessage() + "]");
                    myLogger.log(
                        LogEventId.ERROR,
                        "Error String [" + e.toString() + "]");
                    throw e;
                }
        }
    } 
    else
        try {
            prepareAndSendEmail();
        } catch (Exception ex) {
            myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO OMS.");
            myLogger.log(
                LogEventId.ERROR,
                "Error Message [" + ex.getMessage() + "]");
            myLogger.log(
                LogEventId.ERROR,
                "Error String [" + ex.toString() + "]");
            throw ex;
   }

}

	private void doPreMigrationSwitch(
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
	            .EnvelopeTypeImpl envelope)
	        throws
	            InvalidData,
	            AccessDenied,
	            BusinessViolation,
	            SystemFailure,
	            NotImplemented,
	            ObjectNotFound,
	            DataNotFound,
	            Throwable {
	
		String myMethodName = "ModifyInventory:doPreMigrationSwitch()";
		myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
		
		ModifyFacilityInfoReturn aReturn = null;
		  
	    String wireCenterCLLI8 = myMigration.getWireCenterCLLI8();
	    myLogger.log(LOG_INPUT_DATA, "Wire Center CLLI8 =<" + wireCenterCLLI8 + ">");
	    
		SOACGIServiceOrderResponseParser soacGIParser = new SOACGIServiceOrderResponseParser(myProperties, aUtility,envelope,wireCenterCLLI8 );
		NETWORK_TYPE = FTTN;
		circuitNumber = soacGIParser.getCircuitNumber();
		String primaryNPANXX = soacGIParser.getPrimaryNPANXX();
		
		String aOrderNum = soacGIParser.getAOrderNum();
		String aOrderID= soacGIParser.getAOrderID();
		Fttn aFttn = soacGIParser.getAFttn();
		String oldDslamID = soacGIParser.getOldDslamID();
		String dslamID = soacGIParser.getDslamID();
		boolean isFTTnSAI = soacGIParser.isFTTnSAI();
		
		myLogger.log(LogEventId.INPUT_DATA, "|NETWORK_TYPE =" + NETWORK_TYPE +
				"|CircuitNumber=" + circuitNumber +
				"|PrimaryNPANXX=" + primaryNPANXX +
				"|WireCenterCLLI8=" + wireCenterCLLI8 +
				"|OrderNum=" + aOrderNum + 
				"|OrderID=" + aOrderNum +
				"|IsFTTnSAI=" + isFTTnSAI +
				"|Fttn (object)=" + aFttn.toString() + "|");
		
		com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.ModifyFacilityInfo aMFIBusInterface =
			new com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.ModifyFacilityInfo(aUtility, getPROPERTIES());
		
		try {
			aReturn = aMFIBusInterface.sendRequest(aContext,
					primaryNPANXX,
					circuitNumber,
					null,             // taperCode,
					aFttn,
					null,             // aFttp
					true,
					null,             // SoacDefect[] aDefectList
					oldDslamID);      // String OldTRE
			
			myLogger.log(LogEventId.INFO_LEVEL_1,"SUCCESSFULLY UPDATED GRANITE.");
			
		} 
		catch (Exception e) {
			aMfi.setSendEmailStatus(true);
			myLogger.log(
					LogEventId.ERROR,
			"FAILED TO COMPARE AND UPDATE GRANITE: SystemFailure.");
			
			myLogger.log(LogEventId.ERROR, "Error Message [" + e.getMessage() + "]");
			myLogger.log(LogEventId.ERROR, "Error String [" + e.toString() + "]");
		}
		
		// call IOM BIS
		if (!(dslamID).equalsIgnoreCase(oldDslamID)) 
		{
			try {
				updateIpProductData(
						true,                  // aMfi.getVlanAffecting(),
						circuitNumber,
						NETWORK_TYPE,
						aOrderNum,
						aOrderID);
			} 
			catch (Throwable e) 
			{
				myLogger.log(
						LogEventId.ERROR,
						"Error Message [" + e.getMessage() + "]");
				myLogger.log(
						LogEventId.ERROR,
						"Error String [" + e.toString() + "]");
				throw e;
			}
		}
		
		
		try {
			prepareAndSendEmail();
	    } 
		catch (Exception ex) {
			myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO OMS.");
			myLogger.log(
					LogEventId.ERROR,
					"Error Message [" + ex.getMessage() + "]");
			myLogger.log(
					LogEventId.ERROR,
					"Error String [" + ex.toString() + "]");
			throw ex;
		}
		
		myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
		
	}
	
	public void execute(BisContext callerContext, String xmlMessage) throws Throwable{

		String myMethodName = "ModifyInventory:execute()";
		myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
		
		this.aContext = callerContext;
		
		aMfi =
			new ModifyFacilityInfo(
					aUtility,
					myProperties,
					aContext.aProperties[3].aValue);
		
		// Set marshalUnmarshalOptions properties.
		Properties marshalUnmarshalOptions = new Properties();
		marshalUnmarshalOptions.setProperty(
				Marshaller.JAXB_FORMATTED_OUTPUT,
		"true");
		
		DefaultJAXBEncoderDecoder decoder = null;

        try {

         if (xmlMessage.indexOf(LFACS_FTTN.toUpperCase()) != -1
            || xmlMessage.indexOf(LFACS_FTTN.toLowerCase()) != -1) {

            myLogger.log(
                LogEventId.INFO_LEVEL_1,
                "Processing Request for LFACS FTTN");
            decoder =
                new DefaultJAXBEncoderDecoder(
                    "com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces",
                    marshalUnmarshalOptions);
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
                .EnvelopeTypeImpl fttnlenvelope =
                null;

            try {
                fttnlenvelope =
                    (
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
                            .EnvelopeTypeImpl) decoder
                            .decode(
                        xmlMessage)[0];
            } catch (DecoderException e) {
                myLogger.log(
                    LogEventId.ERROR,
                    "Decoder Exception: [" + e.getMessage() + "]");
                myLogger.log(
                    LogEventId.ERROR,
                    "Decoder Exception: [" + e.toString() + "]");

                throw e;
            } catch (ServiceException e) {
                myLogger.log(
                    LogEventId.ERROR,
                    "Service Exception: [" + e.getMessage() + "]");
                myLogger.log(
                    LogEventId.ERROR,
                    "Service Exception: [" + e.toString() + "]");

                throw e;
            }
        
           setClient(fttnlenvelope);
           
           if (myMigration.getMigrationClient().equals(PRE_MIGRATION))
           { 
           	 myLogger.log(
                      LogEventId.INFO_LEVEL_1,
                     "Processing pre migration: LFACS FTTN "); 
              doLfacsFttn(fttnlenvelope);
           	  //doPreMigrationFttn(fttnlenvelope);
           }
           else if (myMigration.getMigrationClient().equals(DURING_MIGRATION) ) 
           { 	 
           	 
               try 
				 {
           	   myLogger.log(
  	                    LogEventId.INFO_LEVEL_1,
  	                  "Start Processing BBNMS in During migration: LFACS FTTN "); 	
           	   doPostMigration(fttnlenvelope);
				   myLogger.log(
	                    LogEventId.INFO_LEVEL_1,
	                  "End Processing BBNMS in During migration: LFACS FTTN "); 
           	 }
           	 finally
               {
           	 	 myLogger.log(
  	                    LogEventId.INFO_LEVEL_1,
  	                  "Start Processing OMS During migration: LFACS FTTN "); 
           	 	 
                  doLfacsFttn(fttnlenvelope);
                  myLogger.log(
  	                    LogEventId.INFO_LEVEL_1,
  	                  "End Processing OMS During migration: LFACS FTTN "); 	
               }
           }
           else
           {	 myLogger.log(
                  LogEventId.INFO_LEVEL_1,
                 "Processing post migration: LFACS FTTN "); 
           	 doPostMigration(fttnlenvelope);
           	
           }
           
      
        } else if (
            xmlMessage.indexOf(LFACS_FTTP.toUpperCase()) != -1
                || xmlMessage.indexOf(LFACS_FTTP.toLowerCase()) != -1) {

            myLogger.log(
                LogEventId.INFO_LEVEL_1,
                "Processing Request for LFACS FTTP ");
           
            decoder =
                new DefaultJAXBEncoderDecoder(
                    "com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces",
                    marshalUnmarshalOptions);
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
                .EnvelopeTypeImpl fttplenvelope =
                null;

            try {
                fttplenvelope =
                    (
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
                            .EnvelopeTypeImpl) decoder
                            .decode(
                        xmlMessage)[0];
            } catch (DecoderException e) {
                myLogger.log(
                    LogEventId.ERROR,
                    "Decoder Exception: [" + e.getMessage() + "]");
                myLogger.log(
                    LogEventId.ERROR,
                    "Decoder Exception: [" + e.toString() + "]");

                throw e;

            } catch (ServiceException e) {
                myLogger.log(
                    LogEventId.ERROR,
                    "Service Exception: [" + e.getMessage() + "]");
                myLogger.log(
                    LogEventId.ERROR,
                    "Service Exception: [" + e.toString() + "]");

                throw e;

            }
           
			  setClient(fttplenvelope);
			 
			  if (myMigration.getMigrationClient().equals(PRE_MIGRATION))
            {     
                  myLogger.log(
                  LogEventId.INFO_LEVEL_1,
                  "Processing Pre migration: LFACS FTTP"); 
                  
                  doLfacsFttp(fttplenvelope);
            	 
                  
            }
            else if (myMigration.getMigrationClient().equals(DURING_MIGRATION) ) 
            { 	 
                  
                  try
                   {
                    myLogger.log(
      	                    LogEventId.INFO_LEVEL_1,
      	                  "Start Processing BBNMS During migration: LFACS FTTP "); 	
                    
                    doPostMigration(fttplenvelope);
                    
                    myLogger.log(
  	                    LogEventId.INFO_LEVEL_1,
  	                  "End Processing BBNMS During migration: LFACS FTTP "); 	
                   
                   }
                   finally
                   {
                      myLogger.log(
      	                    LogEventId.INFO_LEVEL_1,
      	                  "Start Processing OMS During migration: LFACS FTTP "); 
                     
                     doLfacsFttp(fttplenvelope);
                    
                     myLogger.log(
  	                    LogEventId.INFO_LEVEL_1,
  	                  "End Processing OMS During migration: LFACS FTTP "); 
                    }
            }
            else
            {	 myLogger.log(
                  LogEventId.INFO_LEVEL_1,
                  "Processing Post migration: LFACS FTTP "); 
            	     
              doPostMigration(fttplenvelope);
            }

        } else if (
            xmlMessage.indexOf(SWITCH_FTTN.toUpperCase()) != -1
                || xmlMessage.indexOf(SWITCH_FTTN.toLowerCase()) != -1) {

            myLogger.log(
                LogEventId.INFO_LEVEL_1,
                "Processing Request for SWITCH FTTN");
            
            decoder =
                new DefaultJAXBEncoderDecoder(
                    "com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces",
                    marshalUnmarshalOptions);
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
                .EnvelopeTypeImpl fttnsenvelope =
                null;

            try {
                fttnsenvelope =
                    (
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
                            .EnvelopeTypeImpl) decoder
                            .decode(
                        xmlMessage)[0];
            } catch (DecoderException e) {
                myLogger.log(
                    LogEventId.ERROR,
                    "Decoder Exception: [" + e.getMessage() + "]");
                myLogger.log(
                    LogEventId.ERROR,
                    "Decoder Exception: [" + e.toString() + "]");

                throw e;

            } catch (ServiceException e) {
                myLogger.log(
                    LogEventId.ERROR,
                    "Service Exception: [" + e.getMessage() + "]");
                myLogger.log(
                    LogEventId.ERROR,
                    "Service Exception: [" + e.toString() + "]");

                throw e;

            }
          
            setClient(fttnsenvelope);
            if (myMigration.getMigrationClient().equals(PRE_MIGRATION))
            { 
               myLogger.log(
                 LogEventId.INFO_LEVEL_1,
                  "Processing pre migration: SWITCH FTTN LS6 "); 	
                doSwitchFttn(fttnsenvelope);
                
            }
            else if (myMigration.getMigrationClient().equals(DURING_MIGRATION) ) 
            {  
                 
                 try
                 {  
                 	 myLogger.log(
	                    LogEventId.INFO_LEVEL_1,
	                  "Start Processing BBNMS During migration: SWITCH FTTN "); 
                   
                 	 doPostMigration(fttnsenvelope);
                   
                 	 myLogger.log(
  	                    LogEventId.INFO_LEVEL_1,
  	                  "End Processing BBNMS During migration: SWITCH FTTN "); 
                 }
                 finally
                 { 
                 	 myLogger.log(
	                    LogEventId.INFO_LEVEL_1,
	                  "Start Processing OMS During migration: SWITCH FTTN "); 
                 	 
                 	 doSwitchFttn(fttnsenvelope);
                  
                   
                     myLogger.log(
  	                    LogEventId.INFO_LEVEL_1,
  	                  "End Processing OMS During migration: SWITCH FTTN "); 
                 }
            }
            else
            { 
               myLogger.log(
                 LogEventId.INFO_LEVEL_1,
                  "Processing post migration: SWITCH FTTN "); 	
               doPostMigration(fttnsenvelope);
            }
           
        } else {
            myLogger.log(
                LogEventId.ERROR,
                "The XML Request Received From SOACGI is None of the Following."
                    + "\n1. LFACS FTTN\n2. LFACS FTTP\n3. SWITCH FTTN\n");

        }
    } catch (NotImplemented e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (SystemFailure e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (InvalidData e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (ObjectNotFound e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (AccessDenied e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (BusinessViolation e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (DataNotFound e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (Exception e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        myLogger.log(
            LogEventId.ERROR,
            "Error Message [" + e.getMessage() + "]");
        myLogger.log(
            LogEventId.ERROR,
            "Error String [" + e.toString() + "]");
    } catch (Throwable e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(
            LogEventId.ERROR,
            "FAILED TO COMPARE AND UPDATE GRANITE.");
        myLogger.log(
            LogEventId.ERROR,
            "Error Message [" + e.getMessage() + "]");
        myLogger.log(
            LogEventId.ERROR,
            "Error String [" + e.toString() + "]");
    }  
      try 
    	{
    	prepareAndSendEmail();
    	} catch (Exception ex) {
    		myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO OMS.");
    		myLogger.log(
    				LogEventId.ERROR,
					"Error Message [" + ex.getMessage() + "]");
    		myLogger.log(
    				LogEventId.ERROR,
					"Error String [" + ex.toString() + "]");
    	}
    

    myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
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

    String myMethodName = "ModifyInventory:validateCablePairInfo()";
    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
    
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
    
    myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);

}

public void prepareAndSendEmail() throws MessagingException, IOException {
    EmailHelper anEmailHelper =
        new EmailHelper(aUtility, myProperties);

    String aMailSubject = "Lightspeed Assignment Problem";
    mailSent = false;
    if (aMfi.getSendEmailStatus() == true && mailSent == false) {
        String aEmailBody = buildEmailMsgText();
        anEmailHelper.prepareAndSendEmail(aMailSubject, aEmailBody);
        mailSent = true;
    }

}

public void prepareAndSendEmail(String aEmailBody) throws MessagingException, IOException {
  EmailHelper anEmailHelper =
      new EmailHelper(aUtility, myProperties);

  String aMailSubject = "Lightspeed Assignment Problem";
  mailSent = false;

  String client = myMigration.getMigrationClient();
  
  if (aMfi.getSendEmailStatus() == true && mailSent == false) {
      
  	if (client.equals(PRE_MIGRATION))
      { 
           aEmailBody = buildEmailMsgText();
           anEmailHelper.prepareAndSendEmail(aMailSubject, aEmailBody);
           mailSent = true;
  	}
  	else if (client.equals(DURING_MIGRATION))
		{  
  		//BBNMS
  		anEmailHelper.prepareAndSendEmail(aMailSubject, aEmailBody);
  		//OMS  
  		aEmailBody = buildEmailMsgText();
          anEmailHelper.prepareAndSendEmail(aMailSubject, aEmailBody);
          mailSent = true;
            
            
		}
  	else //POST
  	{
  		anEmailHelper.prepareAndSendEmail(aMailSubject, aEmailBody);
  		mailSent = true;
      }
      
  }

}

public void prepareAndSendEmailBBNMS(String aEmailBody, String recipient) throws MessagingException, IOException {
   EmailHelper anEmailHelper =
      new EmailHelper(aUtility, myProperties);

   String aMailSubject = "Lightspeed Assignment Problem - BBNMS";
   
   mailSentBBNMS = false;
   
   if (aMfi.getSendEmailStatusBBNMS() == true && mailSentBBNMS == false) {
      anEmailHelper.prepareAndSendEmail(aMailSubject, aEmailBody,recipient);
      mailSentBBNMS = true;
  }


}

public String buildEmailMsgText() {
    String myMethodName = "ModifyInventory::buildEmailMsgText()";
    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

    //Some other Info
    String aEmailSt1 =
        "A pending service order, or an existing service, may require your attention.\n\n\n"
            + "LFACS reports that assignments may have changed on:\n";

    //Email Property Names
    String aCircuitIdString = "LS Circuit ID            :   [";
    String aNetworkTypeString = "NetworkType                :   [";
    String aVLANAffectingString = "VLAN Affecting           :   [";

    String aEmailSt2 =
        "\n\nPlease contact the appropriate assignment center and if necessary \n"
            + "issue a change order, or a supplement on the pending order,\n"
            + "to resolve this problem."
            + "If there is no pending order, the change order you issue should be today.";

   
    String aEmailBody = new String();

    aEmailBody = aEmailSt1;
    aEmailBody += ModifyFacilityInfo.NEW_LINE
        + aCircuitIdString
        + circuitNumber
        + "]";
    aEmailBody += ModifyFacilityInfo.NEW_LINE
        + aNetworkTypeString
        + NETWORK_TYPE
        + "]";
        
    String temp = aMfi != null ? (aMfi.getVlanAffecting() == true ? "Yes" : "No") : "NA";   
    aEmailBody += ModifyFacilityInfo.NEW_LINE
        + aVLANAffectingString
        + temp
        + "]";

    aEmailBody += aEmailSt2;

    myLogger.log(
        LOG_DEBUG_LEVEL_1,
        "EMail Body :\n\n" + aEmailBody + "\n\n");
    myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);

    return aEmailBody;

}
public String retrieveCLLI8(String primaryNPANXX) throws SQLException {

    String myMethodName = "ModifyInventory:retrieveCLLI8()";
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
        myLogger.log( LogEventId.REMOTE_CALL, (String)myProperties.get( "jdbcURL" ),
                                           (String)myProperties.get( "jdbcUSERID" ),
                                           (String)myProperties.get( "jdbcUSERID" ),
                                           "ModifyInventory::retrieveCLLI8()" ) ;
          
        aDBConn =
            SoacWireCenterTable.getDBConnection(myProperties, myLogger);
        ps = aDBConn.getConnection().prepareStatement(SQLstatement);
        ps.setString(1, primaryNPANXX);

         try {
            rs = ps.executeQuery();
         }
         finally {
         	
            myLogger.log( LogEventId.REMOTE_RETURN, (String)myProperties.get( "jdbcURL" ),
                                         (String)myProperties.get( "jdbcUSERID" ),
                                         (String)myProperties.get( "jdbcUSERID" ),
                                         "ModifyInventory::retrieveCLLI8()" ) ;
            
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
			//retry only once
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
        myLogger.log(LOG_DEBUG_LEVEL_1, "Something is wrong in DB connection???>");
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
  } 
}
// This methos is suggested to be kept for unit testing
public static void mainTest(java.lang.String[] args) 
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound, Throwable {
	
	Hashtable mProperties = new Hashtable();
	Logger mLogger = null;
	Utility mUtility = null;
	
	//XML message from MDB, here to use a file to mimic the message.
	File testFile1 = new File("C:\\testData\\MI_FTTNL_LS3.xml");
	File testFile2 = new File("C:\\testData\\MI_FTTPL_LS3.xml");
	File testFile3 = new File("C:\\testData\\MI_FTTNS_LS3.xml");
	
	String xmlMessage1 = getContents(testFile1);
	String xmlMessage2 = getContents(testFile2);
	String xmlMessage3 = getContents(testFile3);
	
	Properties marshalUnmarshalOptions = new Properties();
	marshalUnmarshalOptions.setProperty(
			Marshaller.JAXB_FORMATTED_OUTPUT,
	"true");
	
	DefaultJAXBEncoderDecoder decoder = null;
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
	.EnvelopeTypeImpl envelope1 =
		null;
	try {
		decoder =
			new DefaultJAXBEncoderDecoder(
					"com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces",
					marshalUnmarshalOptions);
		envelope1 =
			(
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
					.EnvelopeTypeImpl) decoder
					.decode(
							xmlMessage1)[0];
	} catch (DecoderException e) {
		
		
		throw e;
	} catch (ServiceException e) {
		throw e;
	}
	
	
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
	.EnvelopeTypeImpl envelope2 =
		null;
	try {
		decoder =
			new DefaultJAXBEncoderDecoder(
					"com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces",
					marshalUnmarshalOptions);
		envelope2 =
			(
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
					.EnvelopeTypeImpl) decoder
					.decode(
							xmlMessage2)[0];
	} catch (DecoderException e) {
		
		
		throw e;
	} catch (ServiceException e) {
		throw e;
	}
	
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
	.EnvelopeTypeImpl envelope3 =
		null;
	try {
		decoder =
			new DefaultJAXBEncoderDecoder(
					"com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces",
					marshalUnmarshalOptions);
		envelope3 =
			(
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
					.EnvelopeTypeImpl) decoder
					.decode(
							xmlMessage3)[0];
	} catch (DecoderException e) {
		
		
		throw e;
	} catch (ServiceException e) {
		throw e;
	}
	
	Properties rmProperties = null;
	try {
		rmProperties = PropertiesFileLoader.read("rm.properties",null);
	} 
	catch (IOException ioe) {
		System.out.println(
				"Read RM BIS propeties file failed" + ioe.getMessage());
	}
	
	//(Utility) this
	ModifyInventory aMI = new ModifyInventory(rmProperties,null,mLogger);
	aMI.setClient(envelope1);
	String client = aMI.myMigration.getMigrationClient();
	
    ObjectProperty[] temp = new ObjectProperty[7];
    temp[0] = new ObjectProperty("Application", "RMBIS");
	temp[1] = new ObjectProperty("CustomerName", "SOACGI");
	temp[2] = new ObjectProperty("BusinessUnit", "RMBIS");
	temp[3] = new ObjectProperty("LoggingInformation", "000000");
	temp[4] = new ObjectProperty("JMSCorrelationId", "12345");
	temp[5] = new ObjectProperty("JMSReplyToQueue", "cy4727@att.com");
	temp[6] = new ObjectProperty("EMSMessageTag", "SOACGI");
	BisContext rmBisContext = new BisContext(temp);
	
	ModifyFacilityInfo aMfi =
		new ModifyFacilityInfo(
				null,
				rmProperties,
				rmBisContext.aProperties[3].aValue);
	
    aMI.doPostMigration(envelope1);
	aMI.doPostMigration(envelope2);
	aMI.doPostMigration(envelope3);
	aMI.doPreMigrationFttn(envelope1);

}


static public String getContents(File aFile) {
    //...checks on aFile are elided
    StringBuffer contents = new StringBuffer();

    //declared here only to make visible to finally clause
    BufferedReader input = null;
    try {
      //use buffering, reading one line at a time
      //FileReader always assumes default encoding is OK!
      input = new BufferedReader( new FileReader(aFile) );
      String line = null; //not declared within while loop
      /*
      * readLine is a bit quirky :
      * it returns the content of a line MINUS the newline.
      * it returns null only for the END of the stream.
      * it returns an empty String if two newlines appear in a row.
      */
      while (( line = input.readLine()) != null){
        contents.append(line);
        contents.append(System.getProperty("line.separator"));
      }
    }
    catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    catch (IOException ex){
      ex.printStackTrace();
    }
    finally {
      try {
        if (input!= null) {
          //flush and close both "input" and its underlying FileReader
          input.close();
        }
      }
      catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return contents.toString();
  }

private void setClient(
   Object envelopObj)
throws
        InvalidData,
        AccessDenied,
        BusinessViolation,
        SystemFailure,
        NotImplemented,
        ObjectNotFound,
        DataNotFound,
        Throwable
{
        String myMethodName = "ModifyInventory : setClient()";
		myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

		String anMIException = null;
		String anMIExceptionMsg = null;

		String clientName = null;
		String primaryNPANXX = null;
		String sCLLI8 = null;

		java.sql.Date startDate = null;
		java.sql.Date endDate = null;
		java.util.Date today = new java.util.Date();
		java.sql.Date sqlToday = new java.sql.Date(today.getTime());

		ResultSet rs = null;
		DBConnection aDBConn = null;
		PreparedStatement ps = null;
		String SQLstatement = null;
 
		if (envelopObj instanceof com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.impl.EnvelopeTypeImpl)
		{  
			com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.impl.EnvelopeTypeImpl envelope = (com.sbc.eia.bis.embus.service.soacgi.LFACS.fttn.interfaces.impl.EnvelopeTypeImpl)envelopObj;
			primaryNPANXX =
				envelope.getHeader().getAdditionalInfo().getTtWireCenter();
			
		}
		else if (envelopObj instanceof com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces.impl.EnvelopeTypeImpl)
		{  
			com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces.impl.EnvelopeTypeImpl envelope = (com.sbc.eia.bis.embus.service.soacgi.LFACS.fttp.interfaces.impl.EnvelopeTypeImpl)envelopObj;
			primaryNPANXX =
				envelope.getHeader().getAdditionalInfo().getTtWireCenter();
			
		}
		else if (envelopObj instanceof com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.impl.EnvelopeTypeImpl)
		{  
			com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.impl.EnvelopeTypeImpl envelope = (com.sbc.eia.bis.embus.service.soacgi.SWITCH.fttn.interfaces.impl.EnvelopeTypeImpl)envelopObj;
			primaryNPANXX =
				envelope.getHeader().getAdditionalInfo().getTtWireCenter();
			
		}

		myLogger.log(LOG_INPUT_DATA, "primaryNPANXX = <" + primaryNPANXX + ">");
		
		boolean isMigrationComplete = false;
		String migrationIndicator = (String)myProperties.get("MIGRATION_COMPLETE"); 
		
		if (migrationIndicator.equalsIgnoreCase("Y"))
		{   
			isMigrationComplete = true;
			clientName = (String)myProperties.get("POST_MIGRATION"); 
			myLogger.log(LOG_INPUT_DATA, "Migration phase is complete, the client = " + clientName );
		}
		else
		{	
			isMigrationComplete = false;
			myLogger.log(LOG_INPUT_DATA, "Migration phase is not complete, clients choice will be based on the query to WIRE_CENTER_MIGRATION table. ");
		}
		
		if (primaryNPANXX == null || primaryNPANXX.trim().length() != 6)
			throwException(
					ExceptionCode.ERR_RM_MISSING_NPANXX,
					"Missing or invalid required data: NPA NXX",
					this.getEnv("BIS_NAME"),
					Severity.UnRecoverable);
		try {
			sCLLI8 = retrieveCLLI8(primaryNPANXX).toUpperCase();
			
		} catch (SQLException e) {
			myLogger.log(
					LogEventId.ERROR,
			"FAILED TO RETRIEVE WIRE CENTER CLLI8 INFO FROM THE DATABASE.");
			myLogger.log(
					LogEventId.ERROR,
					"Error Message [" + e.getMessage() + "]");
			myLogger.log(
					LogEventId.ERROR,
					"Error String [" + e.toString() + "]");
		} 
		
		myLogger.log(LOG_INPUT_DATA, "The retrieved CLLI8 = <" + sCLLI8 + ">");
		
		if (!isMigrationComplete)
		{
			
			if ( sCLLI8 == null || sCLLI8.trim().length() == 0)
			{  
				myLogger.log(LOG_INPUT_DATA, "Wire center CLLI 8 does not exist in the routing table. Client = OMS");
				clientName = (String)myProperties.get("PRE_MIGRATION");
			}
			else
			{
				SQLstatement =
					"select EFFSTARTDATE,EFFENDDATE from WIRE_CENTER_MIGRATION where WIRECENTER = ? ";
				
				try {
					
					myLogger.log( LogEventId.REMOTE_CALL, (String)myProperties.get( "jdbcURL" ),
							(String)myProperties.get( "jdbcUSERID" ),
							(String)myProperties.get( "jdbcUSERID" ),
					"ModifyInventory : setMigration()" ) ;
					aDBConn = WireCenterMigrationTable.getDBConnection(myProperties, myLogger);
					
					ps = aDBConn.getConnection().prepareStatement(SQLstatement);
					ps.setString(1, sCLLI8);
					
					rs = ps.executeQuery();
					
					if (rs.next()) {
						myLogger.log(
								LogEventId.DEBUG_LEVEL_1,
								"Migration: |Start date = " + rs.getString(1) + " End date = " + rs.getString(2) +"|");
						
						startDate = rs.getDate(1);
						endDate = rs.getDate(2);
						
						if (sqlToday.before(startDate)) 
						{	   clientName = (String)myProperties.get("PRE_MIGRATION");
						myLogger.log(LOG_DEBUG_LEVEL_1, "Pre migration. The client = "+ clientName);
						}
						else if ( (sqlToday.compareTo(startDate) == 0) || ( sqlToday.after(startDate) && sqlToday.before(endDate))  ) 
						{     clientName = (String)myProperties.get("DURING_MIGRATION");
						myLogger.log(LOG_DEBUG_LEVEL_1, "During migration. The client = "+ clientName);
						}
						else if ((sqlToday.compareTo(endDate) == 0)||sqlToday.after(endDate))
						{	   clientName = (String)myProperties.get("POST_MIGRATION");
						myLogger.log(LOG_DEBUG_LEVEL_1, "Post migration. The client = "+ clientName);
						
						}
						
					}
					else
					{
						clientName = (String)myProperties.get("PRE_MIGRATION"); 
						myLogger.log(LOG_DEBUG_LEVEL_1, "No record found in migration table based on the given CLLI8 value. The client = "+ clientName);
						
					}
					
					
				}
				catch (StaleConnectionException e) 
				{  // If in error situations that this routing table is not accessible, clients = OMS and BBNMS
					clientName = (String)myProperties.get("DURING_MIGRATION");
					myLogger.log(LOG_DEBUG_LEVEL_1, "Databse error occurred. The client = "+ clientName);
					throw e;
					
				} 
				catch (Exception e) 
				{
					clientName = (String)myProperties.get("DURING_MIGRATION");
					
					myLogger.log(LOG_DEBUG_LEVEL_1, "Databse error occurred. Client = "+ clientName);
					myLogger.log(
							LogEventId.ERROR,
							"Error Message [" + e.getMessage() + "]");
					myLogger.log(
							LogEventId.ERROR,
							"Error String [" + e.toString() + "]");
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
					
					myLogger.log( LogEventId.REMOTE_RETURN, (String)myProperties.get( "jdbcURL" ),
							(String)myProperties.get( "jdbcUSERID" ),
							(String)myProperties.get( "jdbcUSERID" ),
					"ModifyInventory : setMigration()" ) ;
					
				}
			}//end the else block
		} //End of the if (isMigrationComplete == false)
		
		myMigration.setMigrationClient(clientName);
		myLogger.log(LOG_INPUT_DATA, "The destination client(s) = <" + clientName + ">");
		
		myMigration.setWireCenterCLLI8(sCLLI8);
		myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
		
}

public void updateIpProductData(
    boolean aVlanIdAffecting,
    String CircuitId,
    String networktype,
    String orderNum,
    String orderID)
    throws Throwable {

    String myMethodName = "modifyInventory:updateIpProductData()";
    myLogger.log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
    IomClient aIomClient = null;

    UpdateIpProductDataReturn result;

    try {
        IomipBIS iomBean = aMfi.getIOMBean();
        Products[] aProducts = new Products[1];
        aProducts[0] = new Products();
        aProducts[0].aName = "FacilitiesData";
        aProducts[0].aValue = new String[1];
        aProducts[0].aValue[0] = new String("");
        
        myLogger.log(LOG_DEBUG_LEVEL_1, "Input Data to IOM is as follows.");
        myLogger.log(
            LOG_INPUT_DATA,
            "Customer Transport ID=<" + CircuitId + ">");
        myLogger.log(LOG_INPUT_DATA, "Network Type=<" + networktype + ">");
        myLogger.log(
            LOG_INPUT_DATA,
            "VLAN ID Affecting=<" + aVlanIdAffecting + ">");
        myLogger.log(LOG_INPUT_DATA, "Order Number=<" + orderNum + ">");
        myLogger.log(LOG_INPUT_DATA, "Order ID=<" + orderID + ">");

        BooleanOpt vlanBoolOpt = new BooleanOpt();
        vlanBoolOpt.theValue(aVlanIdAffecting);

       
        
        aUtility.log(
            LogEventId.REMOTE_CALL,
            (String) getEnv("IOM_IIOP_HOST"),
            (String) getEnv("IOM_BIS_NAME"),
            (String) getEnv("IOM_PROVIDER_URL"),
            "ModifyInventory::updateIpProductData ==> Executing Remote Call");

        result =
            iomBean.updateIpProductData(
                aContext,
                (StringOpt) IDLUtil.toOpt(orderNum),
                (StringOpt) IDLUtil.toOpt(orderID),
                (StringOpt) IDLUtil.toOpt(networktype),
                vlanBoolOpt,
                (StringOpt) IDLUtil.toOpt(CircuitId),
                aProducts,
                (EiaDateOpt) IDLUtil.toOpt(EiaDateOpt.class, null));

        myLogger.log(LOG_DEBUG_LEVEL_1, "SUCCESSFULLY UPDATED IOM-BIS.");
    } catch (NotImplemented e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (SystemFailure e) {
        aMfi.setSendEmailStatus(true);
   	    myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS: SystemFailure.");
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (InvalidData e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (ObjectNotFound e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (AccessDenied e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (BusinessViolation e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (DataNotFound e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        aMfi.logException(e.aExceptionData, e.aContext);
    } catch (Exception e) {
    	aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS: System Exception.");
        myLogger.log(
            LogEventId.ERROR,
            "Error message [" + e.getMessage() + "]");
        myLogger.log(
            LogEventId.ERROR,
            "Error String [" + e.toString() + "]");
    } catch (Throwable e) {
        aMfi.setSendEmailStatus(true);
        myLogger.log(LogEventId.ERROR, "FAILED TO UPDATE IOM-BIS.");
        myLogger.log(
            LogEventId.ERROR,
            "Error message [" + e.getMessage() + "]");
        myLogger.log(
            LogEventId.ERROR,
            "Error String [" + e.toString() + "]");
    }

    finally {
        aUtility.log(
            LogEventId.REMOTE_RETURN,
            (String) getEnv("IOM_IIOP_HOST"),
            (String) getEnv("IOM_BIS_NAME"),
            (String) getEnv("IOM_PROVIDER_URL"),
            "ModifyInventory::updateIpProductData ==> Executed Remote Call");           
    }
    
    myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
}

public String appendONTSlotAndPortToONTAID(String ONTaID) {
    String myMethodName =
        "ModifyInventory::appendONTSlotAndPortToONTAID()";
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
    String myMethodName = "ModifyInventory::getFTTP_OLT_PORT()";
    myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

    String retVal = null;

        try {
            //if ONTaID is ONT-A-B-C-D-E, then OLT Port is D.
            //for LS3 the OLT Port is A-B-C-D. not just D.
            retVal =
                getDataTillNumberOfSpecialCharacters(ONTaId, '-', 4);

         //   myLogger.log(
           //     LogEventId.DEBUG_LEVEL_1,
             //   "FTTP_OLT_PORT =<" + retVal + ">");
        } catch (Exception e) {

    }

    myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
    return retVal;

}

public String getFTTP_ONT_INDEX(String ONTaId) {
    String myMethodName = "ModifyInventory::getFTTP_ONT_INDEX()";
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
        "ModifyInventory::getNumberOfSpecialChars()";
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
        "ModifyInventory::getDataAfterNumberOfSpecifiedCharacters()";
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
        "ModifyInventory::getDataTillNumberOfSpecialCharacters()";
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


/**
 * Sets the circuitNumber.
 * @param circuitNumber The circuitNumber to set
 */
public void setCircuitNumber(String circuitNumber) {
    this.circuitNumber = circuitNumber;
}

	protected String addSOAPTagstoXML(String xmlResponse, String soapMsgHeader) {
		int index = xmlResponse.indexOf("?>", xmlResponse.indexOf("<?") + 1);
		xmlResponse =
			SOAP_ENVELOP_HEADER
				+ soapMsgHeader
				+ xmlResponse.substring(index + 2)
				+ SOAP_ENVELOP_FOOTER;
		return xmlResponse;
	}
	
	protected String getSoapMsgHeader(String messageTag,String applicationID, String messageID,
			 String correlationID, String conversationKey, String loggingKey, String expiration)
	
	{
			return "\n<soap:Header> \n <embus:MessageHeader>"
			+ "\n  <embus:MessageTag>" + messageTag + "</embus:MessageTag>"
			+ "\n  <embus:MessageID>" + messageID + "</embus:MessageID>"
			+ "\n  <embus:ApplicationID>" + applicationID + "generated</embus:ApplicationID>"
			+ "\n  <embus:CorrelationID>" + correlationID + "</embus:CorrelationID>"
		    + "\n  <embus:ConversationKey>" + conversationKey + "</embus:ConversationKey>"
			+ "\n  <embus:LoggingKey>" + loggingKey + "</embus:LoggingKey>"
			+ "\n  <embus:ResponseMessageExpiration>" + expiration + "</embus:ResponseMessageExpiration>"
			+ "\n </embus:MessageHeader>"
			+ "\n</soap:Header>"
			+ "\n<soap:Body>";
}
	public void sendPostMigrationMsg(
		String xmlResp)
		throws
			DataNotFound,
			ObjectNotFound,
			NotImplemented,
			SystemFailure,
			BusinessViolation,
			AccessDenied,
			InvalidData
		{
		  String myMethodName =
	          "ModifyInventory::sendPostMigrationMsg()";
	      myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	      
	      String jmsMessageID = aContext.aProperties[4].aValue;
	      Properties jmsPros = new Properties();
			
	   
		  jmsPros.put("embus:MessageID",jmsMessageID);
		  jmsPros.put("embus:ApplicationID",(String) myProperties.get("BIS_NAME").toString());
		  jmsPros.put("embus:MessageTag", "ModifyInventory");
		 
		  ClientService aService = null;
	      if (aService == null)                
	  	  {
	  	  	 try 
			 {
          		aService = new ClientService(myProperties, aUtility);
          		aService.setClient("BBNMS");
          		aService.logREMOTE_CALL();
          		aService.publishMessage(xmlResp, jmsPros);
          		aService.logREMOTE_RETURN();
          		myLogger.log(LogEventId.INFO_LEVEL_1,"Successfully Sent the XML Response Message to BBNMS.");
		     }
	         catch (Exception e ) 
		     {
	      	   myLogger.log(LogEventId.INFO_LEVEL_2,"MISSED_TARGET_XML:[" + xmlResp+"]");
	      	   myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + e.getMessage());
               aMfi.setSendEmailStatusBBNMS(true);
              }
	     
	  	}
      
	  	  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	private ServiceOrderAssignment setServiceOrderAssignment()
	{
		String myMethodNameName = "ModifyInventory.setServiceOrderAssignment()";
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

	private void doPostMigration(Object envelopObj)
	{
	   String myMethodName = "ModifyInventory:doPostMigration()";
       myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	 
	   String xmlResp = null; 
	   try {
			xmlResp = buildPostMigrationMsg(envelopObj);
			sendPostMigrationMsg(xmlResp);
	   } 
       catch (SystemFailure e) {
			aMfi.setSendEmailStatusBBNMS(true);
			myLogger.log(LogEventId.ERROR,
					"Failed to send response XML message to BBNMS due to System Failure");
			aMfi.logException(e.aExceptionData, e.aContext);
		}
		catch (Exception e) {
			aMfi.setSendEmailStatusBBNMS(true);
			myLogger.log(LogEventId.ERROR,
					"Failed to send response XML message to BBNMS.");
			myLogger.log(LogEventId.ERROR, "Error Message -  [" + e.getMessage()
					+ "]");
			myLogger.log(LogEventId.ERROR, "Error String - [" + e.toString()
					+ "]");
		} catch (Throwable e) {
			aMfi.setSendEmailStatusBBNMS(true);
			myLogger.log(LogEventId.ERROR,
					"Failed to send response XML message to BBNMS.");
			myLogger.log(LogEventId.ERROR, "Error Message: [" + e.getMessage()
					+ "]");
			myLogger.log(LogEventId.ERROR, "Error String: [" + e.toString()
					+ "]");
		}
       
		try {
			prepareAndSendEmailBBNMS(xmlResp,"BBNMS");
		} catch (Exception ex) {
			myLogger.log(LogEventId.ERROR, "FAILED TO SEND EMAIL TO BBNMS.");
			myLogger.log(LogEventId.ERROR, "Error Message [" + ex.getMessage()
					+ "]");
			myLogger.log(LogEventId.ERROR, "Error String [" + ex.toString()
					+ "]");

		}

      myLogger.log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
  }
	

}