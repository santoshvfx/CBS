//$Id: SOAC.java,v 1.113 2009/02/03 21:47:34 ra0331 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007-2010 AT&T Intellectual Property All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 05/2005	   Jon Costa			  Creation
//# 08/2005	   Jon Costa			  DR143549,143556 Circuit ID, Dflt NetworkType
//# 09/2005	   Jon Costa			  FCIF to have SOP originating host in properties
//# 10/07/2005 Sriram Chevuturu		  Fixed the issue with Exception Data being incorporated into the Bis Response
//#									  Instead of sending the Exception Data response itself.
//# 12/2005	   Jon Costa			  LS R2 enhancements.
//# 05/2006	   Doris Sunga			  LS R3 enhancements. rename SendF1F2Order to SendFacilityAssignmentOrder,
//# 								  add new network type 'RGPON',
//# 								  add new ErrorStatusCodes, ExceptionCodes and ExceptionDesc,
//#									  renaming receiveF1F2Response to publishFacilityAssignmentOrderNotification
//# 06/2005    Rene Duka              LS R3 enhancements. Added the following methods:
//#                                     - publishTNAssignmentOrderNotification
//#                                     - sendEmail
//#                                     - setStatus
//# 06/20/2006 Rene Duka              LS R3 enhancements. pTAO - Code WT changes.
//# 07/05/2006 Rene Duka              pTAO: Changed method name from forwardXmlToOms to publishMesage.
//# 07/06/2006 Rene Duka              pTAO: Integration Test changes.
//# 07/11/2006 Doris Sunga			  PFAO: update for isCombinationResponse()
//# 07/13/2006 Doris Sunga			  SFAO: Remove ParserSvcException under throws
//# 08/25/2006 Jon Costa			  PR18355774 Update to SOAC IA for ERR, TOPListener cache
//# 08/31/2006 Doris Sunga			  Prep LS4: fixed error by adding import file com.sbc.eia.bis.rm.database.CvoipOrderRow,
//# 								  CvoipRulesRow, SoacWireCenterRow, SoacWireCenterTable
//# 10/02/2006 Doris Sunga			  DR169001 Update setStatus() - populate the return object as a normal response but also
//								  complete the status structure with the exception code/description from the helper
//# 10/09/2006 Doris Sunga			  LS R4:  add GPON network type on PublishFacilityAssignmentOrderNotification()
//# 12/04/2006 Doris Sunga			  DR #170893 - correlation id issue in PFAO log and xml output,  need to pass corr id in setBisContext()
//# 12/04/2006 Doris Sunga			  DR #170893 - correlation id issue in PTAO log and xml output,  need to pass corr id in setBisContext()
//# 03/27/2007 Rene Duka              PR 19520247: Fixed circuitID format from "." to "/".
//# 05/10/2007 Jon Costa			  CR13804: Change for OTN==TN and actionCode == M
//# 05/10/2007 Doris Sunga			  DR 13440 - added aTelephoneNumber on the response.
//# 05/23/2007 Doris Sunga			  DR 67019 - pFAON - TN from RTID FID, converted to 10 digit numeric TDM Telco TN
//# 05/30/2007 Doris Sunga			  DR 67467 - pFAON - TDM TELCO TN is not being returned in the pFAON response when null/empty
//# 06/05/2007 Doris Sunga			  CR13804 - pFAON - enhancement of code as per code review
//# 08/10/2007 Doris Sunga			  LS5SE 70002226 - modified getEntityPlatform() to add 'JT'/'SE' for SouthEast region
//# 09/25/2007 Ott Phannavong		  LS6 - modified sendFacilityAssignment() to add application indicator
//# 10/20/2007 Mrinalini Peddi        DR 75491 PTNAON logging the xml when failed to send to target system.
//# 10/31/2007 Doris Sunga			  LS6:MQC 77469: added myLogger to fix correlationID 
//# 11/08/2007 Doris Sunga			  DR 77978 - isErrorStatusCode() use retVal as return value instead of 'true'.
//# 11/09/2007 Doris Sunga			  LS6:CR 14149 MFI codes
//# 11/19/2007 Ott Phannavong		  LS6: changed the logging PFAO
//# 02/11/2008 Doris Sunga			  LS7: Update forwardXMLToClient() for MFI
//# 02/19/2008 Doris Sunga			  LS7: Update for PFAO
//# 02/22/2008 Doris Sunga			  LS7: Remove handleUnknownClient() 
//############################################################################

package com.sbc.eia.bis.BusinessInterface.rm.SOAC;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.BusinessInterface.rm.ModifyFacilityInfo.OMSEmailHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderRequestGenerator;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SOACServiceOrderResponseParser;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder.SvcOrderConstants;
import com.sbc.eia.bis.embus.service.client.ClientService;
import com.sbc.eia.bis.embus.service.oms.OMSService;
import com.sbc.eia.bis.embus.service.oms.access.OMSHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.nam.database.CVOIPReferenceRow;
import com.sbc.eia.bis.nam.database.CVOIPReferenceTable;
import com.sbc.eia.bis.rm.database.CvoipOrderRow;
import com.sbc.eia.bis.rm.database.CvoipRulesRow;
import com.sbc.eia.bis.rm.database.SoacWireCenterRow;
import com.sbc.eia.bis.rm.database.SoacWireCenterTable;
import com.sbc.eia.bis.rm.utilities.ApplicationMapper;
import com.sbc.eia.bis.rm.utilities.BisContextHelper;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.exceptions.ParserSvcException;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.bis_types.bishelpers.BisContextBisHelper;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm.PublishFacilityAssignmentOrderNotificationReturn;
import com.sbc.eia.idl.rm.PublishTNAssignmentOrderNotificationReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishFacilityAssignmentOrderNotificationResponseBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNAssignmentOrderNotificationResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._publishTNAssignmentOrderNotificationResponseBISMsg;
import com.sbc.eia.idl.rm_ls_types.ClassOfServiceValues;
import com.sbc.eia.idl.rm_ls_types.NetworkType;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeOpt;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignment;
import com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignmentOpt;
import com.sbc.eia.idl.rm_ls_types.TelephoneNumberOrderPairSeqOpt;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.StringSeqOpt;
import com.sbc.eia.idl.types.bishelpers.ExceptionDataOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringSeqOptBisHelper;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.service.toplistener.TOPLISTENERAccess;
import com.sbc.gwsvcs.service.toplistener.TOPLISTENERHelper;
import com.sbc.gwsvcs.service.toplistener.interfaces.Header_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t;
import com.sbc.gwsvcs.service.toplistener.interfaces.TrnsptType_e;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;

import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponse;
import com.sbc.eia.idl.rm.RmFacadePackage._modifyFacilityInfoResponseBISMsg;
import com.sbc.eia.idl.rm.ModifyFacilityInfoReturn;
/**
* @author jc1421
*
* To change this generated comment edit the template variable "typecomment":
* Window>Preferences>Java>Templates.
* To enable and disable the creation of type comments go to
* Window>Preferences>Java>Code Generation.
*/
public class SOAC
{
public static final String SERVICE_FUNCTION_KEY = "PublishFacilityAssignmentOrderNotification";
public static final String SERVICE_FUNCTION_KEY_ptao = "PublishTNAssignmentOrderNotification";
public String AdditionalErrorDesc = null;

// SOAC specifics:
protected static final String RMBIS_DTN_NAME = "YICDMP";
protected static final String SOAC_RES_CLS_OF_SVC = "XR7FA";
protected static final String SOAC_BUS_CLS_OF_SVC = "XB7FA";
protected static final String DEFAULT_SOAC_SOP_ORIGHOST = "OMS";
protected static final String COMPLETION_SUB_ACTION_TYPE = "PCN";
protected static final String CANCELLATION_SUB_ACTION_TYPE = "CAN";
protected static final String CVOIP_SOAC_REGION_IND = " ";
protected static final String CVOIP_SOAC_TEST_ENV_FIELD = "TC";
protected static final String CVOIP_SOAC_PRODUCTION_ENV_FIELD = "PC";

protected Logger myLogger = null;
protected Utility myUtility = null;
private OMSService omsService = null;
protected Hashtable myProperties = null;
private TOPLISTENERHelper cacheTShelper = null;
private BooleanOpt LSTIndicator = new BooleanOpt();

// The array elements for SOACErrorCodes[], RMExceptionCodes[] & RMExceptionDesc[] align:
// SOACErrorCodes[0] goes with RMExceptionCodes[0] goes with RMExceptionDesc[0]
private final static String[] SOACErrorCodes =
{"FMIS", "FPLK", "FNAS", "FNIA", "FNIW", "FANR", "ENPO", "ELKT", "EHDR",
     "ESOS", "EDUP", "ESOI", "FMIU", "FPLU"};

private final static String[] RMExceptionCodes =
{ExceptionCode.ERR_RM_FACS_MANUAL_ASSIS_SOLICITED,
     ExceptionCode.ERR_RM_FACS_PARTIAL_LOCKOUT_SOLICITED,
     ExceptionCode.ERR_RM_FACS_NO_ASSIGNMENT,
     ExceptionCode.ERR_RM_FACS_ASSIGNMENT_NOT_REQUIRED,
     ExceptionCode.ERR_RM_NOT_FACS_WIRE_CENTER,
     ExceptionCode.ERR_RM_FACS_ASSIGNMENT_NO_LONGER_REQUIRED,
     ExceptionCode.ERR_RM_NO_PENDING_SERVICE_ORDER,
     ExceptionCode.ERR_RM_LOCKOUT_VIOLATION,
     ExceptionCode.ERR_RM_MESSAGE_CONTROL_HEADER,
     ExceptionCode.ERR_RM_SERVICE_ORDER_SEQUENCE,
     ExceptionCode.ERR_RM_SERVICE_ORDER_DUPLICATED,
     ExceptionCode.ERR_RM_SERVICE_ORDER_IMAGE,
     ExceptionCode.ERR_RM_FACS_MANUAL_ASSIS_UNSOLICITED,
     ExceptionCode.ERR_RM_FACS_PARTIAL_LOCKOUT_UNSOLICITED};
private final static String[] RMExceptionDesc =
{"FACS Manual Assistance Solicited", "FACS Partial Lockout Solicited",
     "NO assignment section produced", "assignment not required",
     "Not a FACS Wire Center", "FACS assignments no longer required",
     "No Pending SO", "Lockout Violation", "Message Control Header Error",
     "SO sequence Error", "Duplicate SO Detected", "SO Image Error",
     "FACS Manual Assistance Unsolicited",
     "FACS Partial Lockout Unsolicited"};

// The next two arrays are associated, SOACStatusCodes[0] goes with SOACStatusDesc[0]
private final static String[] SOACStatusCodes =
{"FANK", "FACH", "FANC", "FDND", "FUAU", "FUND", "FANU"};
private final static String[] SOACStatusDesc =
{"FACS assignment - no prior knowledge", "FACS assignments changed",
     "FACS assignment unchanged", "FACS assignment - do not distribute",
     "FACS Unsolicited Assignment Update", "FACS Unsolicited Assignment Change - do not distribute",
     "FACS assignment unchanged Unsolicited"};

private final static int ORDERNUMBER_SIZE = 10;
private final static int ORDERACTIONID_SIZE = 10;
private static String[] applicationID = null;

/**
* Constructor
* @param aProperties
* @param aLogger
*/
public SOAC(Hashtable aProperties, Utility aUtility)
{
  myProperties = aProperties;
  myUtility = aUtility;
  //LS6    
  LSTIndicator.theValue(false);
}
public SOAC(Hashtable aProperties, Utility aUtility, Logger aLogger)
{
  myProperties = aProperties;
  myUtility = aUtility;
  myLogger = aLogger;
  //LS6
  LSTIndicator.theValue(false);
}   
/**
* Method sendFacilityAssignmentorder.
* @param aContext
* @param aSOACServiceOrderNumber
* @param aSOACServiceOrderCorrectionSuffix
* @param aNetworkType
* @param aOrderActionId
* @param aOrderNumber
* @param aOrderActionType
* @param aCompletionIndicator
* @param aSubActionType
* @param aCircuitId
* @param aServiceLocation
* @param aOriginalDueDate
* @param aSubsequentDueDate
* @param aApplicationDate
* @param aTDMTelphoneNumber
* @param aRelatedServiceOrderNumber
* @param aLineShareDisconnectFlag
* @param aClassOfService
* @param aResendFlag
* @param aProperties
* @throws TOPLISTENERException
* @throws ServiceException
* @throws ParserSvcException
* @throws SystemFailure
*/
public void sendFacilityAssignmentOrder(BisContext aContext,
     String aSOACServiceOrderNumber,
     String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
     String aOrderActionId, String aOrderNumber, String aOrderActionType,
     BooleanOpt aCompletionIndicator, StringOpt aSubActionType,
     String aCircuitId, Location aServiceLocation,
     EiaDate aOriginalDueDate, EiaDateOpt aSubsequentDueDate,
     EiaDate aApplicationDate, StringOpt aTDMTelphoneNumber,
     StringOpt aRelatedServiceOrderNumber,
     BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
     BooleanOpt aResendFlag, ObjectPropertySeqOpt aProperties)
     throws InvalidData, SystemFailure, BusinessViolation, AccessDenied,
     NotImplemented, ObjectNotFound, DataNotFound
{
  String myMethodNameName = "SOAC:sendFacilityAssignmentOrder()";
  myUtility.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);
  SoacWireCenterRow soacWC_row = null;
  try
  {
     // Obtain SOAC wire center data to get host_name and entity
     soacWC_row = getSOACWireCenterData(
           aServiceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx,
           aServiceLocation.aProviderLocationProperties[0].aSbcServingOfficeWirecenter);
  }
  catch(DataNotFound e)
  {
     String ErrTxt = e.getMessage() == null ? "Data not found" : e
           .getMessage();
     myUtility.log(LogEventId.ERROR,
           "Database lookup for SOACWireCenterData failed: " + ErrTxt);
     myUtility
           .log(
                 LogEventId.ERROR,
                 "Values used in lookup: aPrimaryNpaNxx["
                       + aServiceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx
                             .theValue()
                       + "] aSbcServingOfficeWirecenter["
                       + aServiceLocation.aProviderLocationProperties[0].aSbcServingOfficeWirecenter
                             .theValue() + "]");
     myUtility.throwException(ExceptionCode.ERR_RM_INVALID_REQUEST_TYPE,
           "Unable to determine SOAC wire center from input: " + ErrTxt,
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }
  TopListenerSoacLinkRow aTopListenerSoacLinkRow = null;
  String applData = null;
  try
  {
     // Use SOAC wire center data(host_name, entity) to get applData for
     // TOPListener
     aTopListenerSoacLinkRow = getTopListenerSoacLinkData(soacWC_row
           .getHOST_NAME().trim(), soacWC_row.getENTITY().trim());
     applData = aTopListenerSoacLinkRow.getTELCO_APPLDATA().trim()
           .toUpperCase();
  }
  catch(DataNotFound e)
  {
     myUtility.log(LogEventId.ERROR,
           "Unable to determine ApplData to TOPListener");
     myUtility.log(LogEventId.ERROR,
           "Database failure for TOPListenerSoacLink: " + e.getMessage());
     myUtility.log(LogEventId.ERROR, "Values used in lookup: aHostName["
           + soacWC_row.getHOST_NAME().trim().toUpperCase() + "] aEntity["
           + soacWC_row.getENTITY().trim().toUpperCase() + "]");
     myUtility.throwException(ExceptionCode.ERR_RM_INVALID_REQUEST_TYPE,
           "Unable to determine TOPListener ApplData from input: "
                 + e.getMessage(), (String)myProperties.get("BIS_NAME")
                 .toString(), Severity.UnRecoverable);
  }
  String SOP_origHost = myProperties.get("SOAC_SOP_ORIGHOST").toString()
        .toUpperCase();
  if(SOP_origHost == null || SOP_origHost.trim().equalsIgnoreCase(""))
     SOP_origHost = DEFAULT_SOAC_SOP_ORIGHOST;

  //LS6
  ObjectPropertyManager aContextOPM = new ObjectPropertyManager(
        aContext.aProperties);
  String applicationID = aContextOPM.getValue(
        BisContextProperty.APPLICATION).toString().toUpperCase().trim();
  ApplicationMapper applicationMapper = ApplicationMapper
        .getInstanceOfApplicationMapper(myUtility, myProperties);
  String applicationIndicator = applicationMapper
        .retrieveApplicationIndicator(applicationID);
  //LS6 end
  // Construct FCIF service order:
  String SoacFcifTxt = null;
  try
  {
     // Construct FCIF service order:
     SoacFcifTxt = makeSOACServiceOrderRequestGenerator().getFCIFRequestStringforFTTX(aSOACServiceOrderNumber,
                 aSOACServiceOrderCorrectionSuffix, aNetworkType,
                 padTrailingSpaces(aOrderActionId, ORDERACTIONID_SIZE),
                 padTrailingSpaces(aOrderNumber, ORDERNUMBER_SIZE),
                 aOrderActionType, aCompletionIndicator, aSubActionType,
                 aCircuitId, aServiceLocation, aOriginalDueDate,
                 aSubsequentDueDate, aApplicationDate, FttpFilter(
                       aNetworkType, aTDMTelphoneNumber), FttpFilter(
                       aNetworkType, aRelatedServiceOrderNumber),
                 aLineShareDisconnectFlag,
                 getClassOfService(aClassOfService), aResendFlag,
                 soacWC_row.getENTITY(), getEntityPlatform(
                       aTopListenerSoacLinkRow.getREGION(), myProperties
                             .get("SOAC_ENTITY_PLATFORM").toString()),
                 aProperties, SOP_origHost, aTopListenerSoacLinkRow
                       .getREGION(), getSpecialCondValue(aContext),
                 applicationIndicator);
  }
  catch(ParserSvcException pse)
  {
     // SRM FCIF formatter exception
     String errDesc = "Failure to format Telco SOAC service order: "
           + pse.getMessage();
     myUtility.log(LogEventId.ERROR, errDesc);
     myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE, errDesc,
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }
  myUtility.log(LogEventId.INFO_LEVEL_2, "SOAC FCIF Order: [" + SoacFcifTxt
        + "]");
  sendToTOPListener(aContext, SoacFcifTxt, applData);
  myUtility.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
}
/**
* Method SendTNAssignmentOrder.
* @param aContext
* @param aSOACServiceOrderNumber
* @param aSOACServiceOrderCorrectionSuffix
* @param aOrderNumber
* @param aOrderActionId
* @param aOrderActionType
* @param aSubActionType
* @param aCompletionIndicator
* @param aServiceLocation
* @param aOriginalDueDate
* @param aSubsequentDueDate
* @param aApplicationDate
* @param aResendFlag
* @param aWireCenter
* @param aRateCenter
* @param aTelephoneNumberOrderPairs
* @param aProperties
* @param aCvoipOrderRows
* @param aCvoipRulesRows
* @throws InvalidData
* @throws SystemFailure
* @throws BusinessViolation
* @throws AccessDenied
* @throws NotImplemented
* @throws ObjectNotFound
* @throws DataNotFound
*/
public void SendTNAssignmentOrder(BisContext aContext,
     String aSOACServiceOrderNumber,
     String aSOACServiceOrderCorrectionSuffix, String aOrderNumber,
     String aOrderActionId, String aOrderActionType,
     StringOpt aSubActionType, BooleanOpt aCompletionIndicator,
     Location aServiceLocation, EiaDate aOriginalDueDate,
     EiaDateOpt aSubsequentDueDate, EiaDate aApplicationDate,
     BooleanOpt aResendFlag, StringOpt aWireCenter, StringOpt aRateCenter,
     TelephoneNumberOrderPairSeqOpt aTelephoneNumberOrderPairs,
     ObjectPropertySeqOpt aProperties, CvoipOrderRow[] aCvoipOrderRows,
     CvoipRulesRow[] aCvoipRulesRows) throws InvalidData, SystemFailure,
     BusinessViolation, AccessDenied, NotImplemented, ObjectNotFound,
     DataNotFound
{
  String myMethodNameName = "SOAC:SendTNAssignmentOrder()";
  myUtility.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

  CVOIPReferenceRow aRefRow = null;
  try
  {
     aRefRow = new CVOIPReferenceRow();
     aRefRow.setRateCenter(aRateCenter.theValue());
     aRefRow.setStateCd(aServiceLocation.aProviderLocationProperties[0].aServiceAddress.theValue().aFieldedAddress().aState.theValue());

     myUtility.log(LogEventId.INFO_LEVEL_2,
           "CVOIPReferenceTable.getWireCenterAndTnListId("
                 + aRefRow.getRateCenter() + ", " + aRefRow.getStateCd()
                 + ")");
     new CVOIPReferenceTable(myUtility, myProperties).getWireCenterAndTnListId(aRefRow);
     
     myUtility.log(LogEventId.INFO_LEVEL_2, "SQLResult: "
           + aRefRow.getMultiEsrnIndicator() + "|" + aRefRow.getEsrn()
           + "|" + aRefRow.getNpa() + "|" + aRefRow.getRateCenter() + "|"
           + aRefRow.getStateCd() + "|" + aRefRow.getTnListId() + "|"
           + aRefRow.getLightspeedLrn() + "|"
           + aRefRow.getCvoipSoacSwitchWc() + "|"
           + aRefRow.getCvoipSoacEntity() + "|"
           + aRefRow.getCvoipSwitchEntity() + "|");
  }
  catch(SQLException sqle)
  {
     String errDesc = "Unable to determine CVOIP SOAC entity from: "
           + aRefRow.getRateCenter() + " and " + aRefRow.getStateCd()
           + ". SQLException[" + sqle.getMessage() + "]";
     myUtility.log(LogEventId.ERROR, errDesc);

     myUtility.throwException(ExceptionCode.ERR_RM_INVALID_DATA, errDesc,
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }

  String aApplData = getTOPListenerCvoipApplData(aContext, aRefRow);
  myUtility.log(LogEventId.DEBUG_LEVEL_1, "aApplData: <" + aApplData + ">");

  String SOP_origHost = myProperties.get("SOAC_SOP_ORIGHOST").toString()
        .toUpperCase();
  if(SOP_origHost == null || SOP_origHost.trim().equals(""))
     SOP_origHost = DEFAULT_SOAC_SOP_ORIGHOST;

  String SoacFcifTxt = null;
  try
  {
     // Construct FCIF service order:
     SoacFcifTxt = (new SOACServiceOrderRequestGenerator(myProperties,
           (com.sbc.bccs.utilities.Logger)myUtility))
           .getFCIFRequestStringForVOIP(
                 aSOACServiceOrderNumber,
                 aSOACServiceOrderCorrectionSuffix,
                 SvcOrderConstants.VOIP_NETWORK,
                 padTrailingSpaces(aOrderActionId, ORDERACTIONID_SIZE),
                 padTrailingSpaces(aOrderNumber, ORDERNUMBER_SIZE),
                 aOrderActionType,
                 aCompletionIndicator,
                 aSubActionType,
                 aServiceLocation,
                 aOriginalDueDate,
                 aSubsequentDueDate,
                 aApplicationDate,
                 aRefRow.getCvoipSoacEntity(),
                 myProperties.get("SOAC_ENTITY_PLATFORM").toString().trim()
                       .equalsIgnoreCase("test") ? CVOIP_SOAC_TEST_ENV_FIELD
                       : CVOIP_SOAC_PRODUCTION_ENV_FIELD, aProperties,
                 SOP_origHost, CVOIP_SOAC_REGION_IND,
                 aTelephoneNumberOrderPairs,
                 aRefRow.getCvoipSoacSwitchWc(),
                 getSpecialCondValue(aContext));
  }
  catch(ParserSvcException pse)
  {
     // SRM FCIF formatter exception
     String errDesc = "Failure to format CVOIP SOAC service order: "
           + pse.getMessage();
     myUtility.log(LogEventId.ERROR, errDesc);

     myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE, errDesc,
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }
  catch(DataNotFound dnf)
  {
     myUtility.log(LogEventId.INFO_LEVEL_1,
           "DataNotFound from formatter, nonOp request ignored");
     return;
  }

  myUtility.log(LogEventId.INFO_LEVEL_2, "SOAC FCIF Order: [" + SoacFcifTxt
        + "]");

  sendToTOPListener(aContext, SoacFcifTxt, aApplData);

  myUtility.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
}

/**
* Method getSpecialCondValue.
* @param aContext
* @return String
*/
private String getSpecialCondValue(BisContext aContext)
{
  // This method is here to facilitate connectivity testing, the client should never
  // pass a tag/value in the BisContext with this. The correct value is "EV".
  ObjectPropertyManager aContextOPM = new ObjectPropertyManager(
        aContext.aProperties);
  return aContextOPM.getValue("SOAC_SPECIAL_CONDITIONS_DATA");
}

/**
* Method getTOPListenerCvoipApplData.
* @param aRefRow
* @return String
*/
private String getTOPListenerCvoipApplData(BisContext aContext,
     CVOIPReferenceRow aRefRow) throws InvalidData, SystemFailure,
     BusinessViolation, AccessDenied, NotImplemented, ObjectNotFound,
     DataNotFound
{
  String aApplData = null;
  try
  {
     SoacWireCenterRow soacWC_row = null;

     // Obtain SOAC wire center data to get host_name and entity
     soacWC_row = (new SoacWireCenterTable()).retrieveRow(aRefRow
           .getCvoipSoacSwitchWc(), myProperties,
           (com.sbc.bccs.utilities.Logger)myUtility);

     TopListenerSoacLinkRow aTopListenerSoacLinkRow = null;

     // Use SOAC wire center data(host_name, entity) to get applData for TOPListener
     aTopListenerSoacLinkRow = getTopListenerSoacLinkData(soacWC_row
           .getHOST_NAME().trim(), soacWC_row.getENTITY().trim());

     aApplData = aTopListenerSoacLinkRow.getCVOIP_APPLDATA();
  }
  catch(Exception e)
  {
     //If all else fails, we'll attempt to use the property value...
     aApplData = myProperties.get("TOPLISTENER_APPLDATA").toString();
  }

  // ApplData must be populated by now, else we have invalid input data and no backup in the property file.
  if(aApplData == null || aApplData.trim().length() < 1)
  {
     myUtility.throwException(ExceptionCode.ERR_RM_INVALID_DATA,
           "Unable to determine TOPListener ApplData from input.",
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }
  return aApplData.toUpperCase().trim();
}

/**
* Method sendToTOPListener.
* @param aContext
* @param aFcifTxt
* @param aApplData
* @throws InvalidData
* @throws SystemFailure
* @throws BusinessViolation
* @throws AccessDenied
* @throws NotImplemented
* @throws ObjectNotFound
* @throws DataNotFound
*/
protected void sendToTOPListener(BisContext aContext, String aFcifTxt,
     String aApplData) throws InvalidData, SystemFailure,
     BusinessViolation, AccessDenied, NotImplemented, ObjectNotFound,
     DataNotFound
{
  String myMethodNameName = "SOAC:sendToTOPListener()";
  myUtility.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

  com.sbc.gwsvcs.access.vicuna.EventResultPair response = null;
  try
  {
     if(cacheTShelper == null)
        cacheTShelper = new TOPLISTENERHelper(myProperties,
              (com.sbc.bccs.utilities.Logger)myUtility);

     Header_t hdr = new Header_t("BIS", "BIS", "", "",
           TrnsptType_e.RPC_TRNSPT, "");
     TOPSendToHostReq_t request = new TOPSendToHostReq_t(hdr, aFcifTxt,
           RMBIS_DTN_NAME);

     myUtility.log(LogEventId.REMOTE_CALL, "SOAC Call begin");

     myUtility.log(LogEventId.REMOTE_CALL, TOPLISTENERAccess.name,
           TOPLISTENERAccess.name + "-" + TOPLISTENERAccess.version,
           TOPLISTENERAccess.name + "-" + TOPLISTENERAccess.version,
           "topListenerReq()");

     response = cacheTShelper.topListenerReq(aApplData, null, 0, request);

     myUtility.log(LogEventId.INFO_LEVEL_1, "Received event: "
           + response.getEventNbr());
  }
  catch(ServiceTimeoutException e)
  {
     myUtility.log(LogEventId.INFO_LEVEL_1,
           "Timeout error from TOPListener ignored: " + e.getMessage());
  }
  catch(ServiceException se)
  {
     // ServiceException/TOPLISTENERException from TOPListener
     String errDesc = "ServiceException from TOPListener: "
           + se.getMessage();
     myUtility.log(LogEventId.ERROR, errDesc);
     ExceptionBuilder.parseException(aContext, (String)myProperties
           .get("EXCEPTION_BUILDER_TOPLISTENER_RULE_FILE"), null, null,
           errDesc, true, ExceptionBuilderRule.NO_DEFAULT, null, se,
           (com.sbc.bccs.utilities.Logger)myUtility, null, null, null);
  }
  finally
  {
     myUtility.log(LogEventId.REMOTE_RETURN, TOPLISTENERAccess.name,
           TOPLISTENERAccess.name + "-" + TOPLISTENERAccess.version,
           TOPLISTENERAccess.name + "-" + TOPLISTENERAccess.version,
           "topListenerReq()");
  }
}
/**
* Prepares and sends the XML and uses the Email helper to send where appropriate.
* @param aParsedFCIF
* @param aApplicationID
* @param emailSender
* @param aContext
* @throws Exception
*/
protected void sendXML(SOACServiceOrderResponseParser aParsedFCIF,
     String aApplicationID, SOACEmailSender emailSender, BisContext aContext)
     throws Exception
{
  String myMethodNameName = "SOAC: sendXML()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

  boolean emailReportSent = false;
  // Return to OMS via Email or XML on queue.
  // NOTE: A try/catch block will need surround those FCIF values that are not fatal
  // to the process if they do not exist. The *C2 header values (ie FunctionType,
  // SOACServiceOrderNum, WireCenter) MUST exist or the process will fail so the
  // larger try/catch will handle those.

  if(((aParsedFCIF.getFunctionType().equalsIgnoreCase(
        COMPLETION_SUB_ACTION_TYPE) || aParsedFCIF.getFunctionType()
        .equalsIgnoreCase(CANCELLATION_SUB_ACTION_TYPE)) && (isErrorStatusCode(aParsedFCIF
        .getStatusCode()) == true))
        || aParsedFCIF.isCombinationResponse() == true)
  {

     emailSender.send();
     emailReportSent = true;
  }
  //LS6 end
  if(emailReportSent == false
        || aParsedFCIF.isCombinationResponse() == true)
  {

     myLogger.log(LogEventId.DEBUG_LEVEL_2, "aContext=<"
           + (new BisContextBisHelper(aContext)).toString() + ">");
     /* Build response object from FCIF attributes...
      * SendF1F2OrderReturn:
      *	com.sbc.eia.idl.bis_types.BisContext aContext
      *	com.sbc.eia.idl.rm_ls_types.ServiceOrderAssignmentOpt aServiceOrderAssignment
      *	com.sbc.eia.idl.types.ExceptionDataOpt
      *	com.sbc.eia.idl.types.ObjectPropertySeqOpt aProperties
      */
     ServiceOrderAssignment soaObj = setServiceOrderAssignment();

     soaObj.aWireCenter.theValue(aParsedFCIF.getWireCenter());
     soaObj.aServiceOrderNumber.theValue(aParsedFCIF
           .getSOACServiceOrderNum());
     soaObj.aServiceOrderCorrectionSuffix.theValue(aParsedFCIF
           .getCorrectionSuffix());

     // Common FTTP, FTTN, RGPON and GPON response attributes:
     try
     {
        if(aParsedFCIF.getLSCircuitID() != null)
           soaObj.aLightspeedCircuitId.theValue(aParsedFCIF
                 .getLSCircuitID());
     }
     catch(Exception e)
     {
     }

     try
     {
        if(aParsedFCIF.getTaperCode() != null)
           soaObj.aTaperCode.theValue(aParsedFCIF.getTaperCode());
     }
     catch(Exception e)
     {
     }

     // Network type required to determine response type (FTTP, FTTN, RGPON or GPON).
     String aNetworkType = aParsedFCIF.getNetworkType().trim();

     if(aNetworkType == null)
     {
        // This error has no place to go but the log file, most likely
        // it is an invalid FCIF message in the queue.
        throw new Exception(
              "NetworkType not found or invalid in SOAC output, response invalid.");
     }

     if(aNetworkType.equalsIgnoreCase(SvcOrderConstants.RGPN_NETWORK))
        aNetworkType = NetworkTypeValues.RGPON;
     

     /* LS 10: Added IPCO, IPRT*/
     if((!aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTP)
           && !aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTN)
           && !aNetworkType.equalsIgnoreCase(NetworkTypeValues.RGPON) 
           && !aNetworkType.equalsIgnoreCase(NetworkTypeValues.GPON)
           && !aNetworkType.equalsIgnoreCase(SvcOrderConstants.FTTNBP_NETWORK)
           && !aNetworkType.equalsIgnoreCase(SvcOrderConstants.FTTPIP_NETWORK)
           && !aNetworkType.equalsIgnoreCase(SvcOrderConstants.IPCO_NETWORK)
           && !aNetworkType.equalsIgnoreCase(SvcOrderConstants.IPRT_NETWORK)))
     {
        // This error has no place to go but the log file, most likely
        // it is an invalid FCIF message in the queue.
        throw new Exception(
              "NetworkType not found or invalid in SOAC output, response invalid.");
     }

     myLogger.log(LogEventId.INFO_LEVEL_1, "Processing a [" + aNetworkType
           + "] response.");

     NetworkType aNetworkTypeChoice = new NetworkType();

     /* LS R7: Added FTTPIP_NETWORK*/
     if(aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTP)
           || aNetworkType.equalsIgnoreCase(NetworkTypeValues.RGPON)
           || aNetworkType.equalsIgnoreCase(NetworkTypeValues.GPON)
           || aNetworkType.equalsIgnoreCase(SvcOrderConstants.FTTPIP_NETWORK))
     {
        if(aParsedFCIF.getFTTP() != null)
        {
           aNetworkTypeChoice.aFttp(aParsedFCIF.getFTTP()); // FTTP Response
           soaObj.aNetworkTypeChoice.theValue(aNetworkTypeChoice);
        }
     }
     else  // FTTN Response
     {
        if(aParsedFCIF.getFTTN() != null)
        {
           aNetworkTypeChoice.aFttn(aParsedFCIF.getFTTN()); 
           soaObj.aNetworkTypeChoice.theValue(aNetworkTypeChoice);
        }
     }

     try
     {
        if(aParsedFCIF.getAssgnSectionString() != null)
           soaObj.aAssignmentLines.theValue(aParsedFCIF
                 .getAssgnSectionString());
     }
     catch(Exception e)
     {
     }

     /* CR 13440 - when TN is populated '999 999-9999', reformat to 10 numeric digits
      * the aTelephoneNumber.  Otherwise send null
      */
     String aTelephoneNum = "";
     String aTelNum = "";
     try
     {
        if(aParsedFCIF.getTelephoneNum() != null
              || aParsedFCIF.getTelephoneNum().trim().length() > 0)
        {
           aTelNum = aParsedFCIF.getTelephoneNum().trim();

           for(int i = 0; i < aTelNum.length(); i++)
           {
              if(Character.isLetterOrDigit(aTelNum.charAt(i)))
                 aTelephoneNum = aTelephoneNum + aTelNum.charAt(i);
           }
        }
     }
     catch(Exception e)
     {
     }

     soaObj.aTelephoneNumber.theValue(aTelephoneNum);

     //LS6
     boolean aIsLST = ApplicationMapper.getInstanceOfApplicationMapper(
           myUtility, myProperties).isLST(aApplicationID);

     if(aIsLST)
     {
        if(aParsedFCIF.getLSTIndicator().theValue())
        {
           LSTIndicator.theValue(true);
        }
        soaObj.aLSTIndicator.theValue(LSTIndicator.theValue());
     }
     //LS6 end

     ServiceOrderAssignmentOpt aServiceOrderAssignment = new ServiceOrderAssignmentOpt();
     aServiceOrderAssignment.theValue(soaObj);
     ExceptionDataOpt aStatus = (ExceptionDataOpt)IDLUtil.toOpt(
           ExceptionDataOpt.class, setStatus(aParsedFCIF));
     myUtility.log(LogEventId.DEBUG_LEVEL_2, "aStatus=<"
           + (new ExceptionDataOptBisHelper(aStatus)).toString() + ">");

     ObjectPropertySeqOpt aProperties = new ObjectPropertySeqOpt();
     aProperties.__default();

     prepareAndForwardXml(aContext, aServiceOrderAssignment, aStatus, aProperties,
           aParsedFCIF);
  }
  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
}



/**
* This method handles send the response to a known client base on application ID.
* @param aUtility
* @param aParsedFCIF
* @param correlationId
* @param aApplicationID
* @throws Exception
*/
private void handleKnownClient(Utility aUtility,
     SOACServiceOrderResponseParser aParsedFCIF, String correlationId,
     String aApplicationID) throws Exception
{
  String myMethodNameName = "SOAC: handleKnownClient()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

  BisContext aContext = null;
  aContext = BisContextHelper.setBisContext(aApplicationID, null, null,
        correlationId, myProperties);

  SOACEmailSender emailSender = SOACEmailSenderFactory.getInstance(
        aContext, aUtility, aParsedFCIF, myProperties, aApplicationID,myLogger);
  emailSender.setTransactionName(SvcOrderConstants.PFAO_TRANSACTION);
  try
  {
     sendXML(aParsedFCIF, aApplicationID, emailSender, aContext);
  }
  catch(SystemFailure sfe)
  {
     myLogger.log(LogEventId.ERROR, "Sending email because of SystemFailure.");
     emailSender.sendWhenReponseFailed();

     throw sfe;
  }
  catch(Exception e)
  {
     myLogger.log(LogEventId.ERROR, "Sending email because of Exception.");
     emailSender.sendWhenReponseFailed();
     if(aContext == null)
        throw e;
     else
     {
        myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
			e.getClass().getName() + " occured in: " + "Class=" + e.getStackTrace()[0].getFileName() + ", Method=" + e.getStackTrace()[0].getMethodName() + ", Line="+ e.getStackTrace()[0].getLineNumber(), (String) myProperties
					.get("BIS_NAME").toString(), Severity.UnRecoverable);
     }
  }
  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
}
/**
* Method publishFacilityAssignmentOrderNotification (used to be receiveF1F2Response).
* @param aUtility
* @param parsedFCIF
* @throws SystemFailure
* @throws Exception
*/
public void publishFacilityAssignmentOrderNotification(Utility aUtility,
     SOACServiceOrderResponseParser aParsedFCIF, String correlationId,
     String aApplicationID) throws SystemFailure, Exception
{
  String myMethodNameName = "SOAC: publishFacilityAssignmentOrderNotification()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

  handleKnownClient(aUtility, aParsedFCIF, correlationId, aApplicationID);


  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
}

/**
* Method isErrorStatusCodes.
* @param string
* @return boolean
*/
protected boolean isErrorStatusCode(String aStatusCode)
{
  String myMethodNameName = "SOAC.isErrorStatusCode()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
  boolean retVal = false;

  if(aStatusCode != null && aStatusCode.length() > 0)
  {
     // Status code begins with "E"?
     if(aStatusCode.substring(0, 1).toUpperCase().equalsIgnoreCase("E"))
        retVal = true;
     else
     {
        // Status code is known error response?
        for(int i = 0; i < SOACErrorCodes.length; i++)
        {
           if(aStatusCode.substring(0, 4).equalsIgnoreCase(
                 SOACErrorCodes[i]))
              retVal = true;
        }
     }
  }
  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
  return retVal;
}

/**
* Method getClassOfService.
* @param iBusResFlag
* @return String
*/
private String getClassOfService(String iBusResFlag)
{
  if(iBusResFlag.equalsIgnoreCase(ClassOfServiceValues.BUSINESS))
     return SOAC_BUS_CLS_OF_SVC;

  return SOAC_RES_CLS_OF_SVC;
}

/**
* Method getExceptionCode.
* @param aStatusCode
* @return String
*/
private String getExceptionCode(String aStatusCode,
     SOACServiceOrderResponseParser parsedFCIF)
{
  String retVal = ExceptionCode.ERR_RM_SOAC_SYSTEM_FAILURE;

  if(parsedFCIF.isCombinationResponse() == true)
     retVal = parsedFCIF.getCombinationResponseExceptionCode();
  else
  {
     for(int i = 0; i < SOACErrorCodes.length; i++)
     {
        if(aStatusCode.substring(0, 4).equalsIgnoreCase(SOACErrorCodes[i]))
           retVal = RMExceptionCodes[i];
     }
  }

  return retVal;
}

/**
* Method getExceptionDesc.
* @param aStatusCode
* @return String
*/
private String getExceptionDesc(String aStatusCode,
     SOACServiceOrderResponseParser parsedFCIF)
{
  String retVal = "SOAC System failure. ";

  if(parsedFCIF.isCombinationResponse() == true)
     retVal = "RM Invalid Data. "
           + parsedFCIF.getCombinationResponseExceptionMessage();
  else
  {
     for(int i = 0; i < SOACErrorCodes.length; i++)
     {
        if(aStatusCode.substring(0, 4).equalsIgnoreCase(SOACErrorCodes[i]))
           retVal = RMExceptionDesc[i];
     }
  }

  return retVal;
}

/**
* Method getStatusDesc.
* @param aStatusCode
* @return String
*/
private String getStatusDesc(String aStatusCode,
     SOACServiceOrderResponseParser parsedFCIF)
{
  String retVal = "";

  for(int i = 0; i < SOACStatusCodes.length; i++)
  {
     if(aStatusCode.substring(0, 4).equalsIgnoreCase(SOACStatusCodes[i]))
        retVal = SOACStatusDesc[i];
  }
  return retVal;
}

/**
* Method getEntityPlatform.
* Entity Platform is a combination of region and env
* @param aRegion
* @param aEnv
* @return String
*/
private String getEntityPlatform(String aRegion, String aEnv)
{
  String retVal = "";

  if(aEnv.trim().equalsIgnoreCase("test"))
  {
     // For SouthEast, return "JT"; other regions have region+'T'
     retVal = (aRegion.trim().equalsIgnoreCase("B") ? "J" : aRegion
           .toUpperCase())
           + "T";
  }
  else
  {
     if(aRegion.trim().equalsIgnoreCase("S"))
     {
        retVal = aRegion.toUpperCase() + "W";
     }
     else if(aRegion.trim().equalsIgnoreCase("W"))
     {
        retVal = aRegion.toUpperCase() + "S";
     }
     else if(aRegion.trim().equalsIgnoreCase("B"))
     {
        retVal = "SE";
     }
  }
  return retVal;
}

/**
* Method FttpFilter.
* @param aNetworkType
* @param aConditionalStrOpt
* @return StringOpt
*/
private StringOpt FttpFilter(String aNetworkType,
     StringOpt aConditionalStrOpt)
{
  StringOpt aDefaultStrOpt = new StringOpt();
  aDefaultStrOpt.__default();

  if(aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTP))
  {
     // In case of FTTP, the input value is not to be used.
     return aDefaultStrOpt;
  }
  return aConditionalStrOpt;
}

/**
* Method padTrailingSpaces.
* @param inString
* @param newLength
* @return String
*/
private String padTrailingSpaces(String inString, int newLength)
{
  StringBuffer temp = new StringBuffer(inString);

  while(temp.length() < newLength)
     temp.append(" ");

  return temp.toString();
}

/**
* Method setSvcOrdAsgn.
* @return ServiceOrderAssignment
*/
public ServiceOrderAssignment setServiceOrderAssignment()
{
  String myMethodNameName = "SOAC: setServiceOrderAssignment()";
  myLogger.log(LogEventId.DEBUG_LEVEL_2, ">" + myMethodNameName);

  // IMPORTANT:	These are preset values to accomodate the
  //				conversion to XML(Opt: Min=0). If the response value
  //				does NOT exist, leave at this preset value.
  StringOpt aWireCenter = IDLUtil.toOpt((String)null, false);
  StringOpt aServiceOrderNumber = IDLUtil.toOpt((String)null, false);
  StringOpt aServiceOrderCorrectionSuffix = IDLUtil.toOpt((String)null,
        false);
  StringOpt aTelephoneNumber = IDLUtil.toOpt((String)null, false);
  StringOpt aLightspeedCircuitId = IDLUtil.toOpt((String)null, false);
  StringOpt aTaperCode = IDLUtil.toOpt((String)null, false);
  //LS6
  BooleanOpt aLSTIndicator = new BooleanOpt();

  aLSTIndicator.theValue(false); // default

  // Network type data
  NetworkTypeOpt aNetworkTypeChoice = new NetworkTypeOpt();
  aNetworkTypeChoice.__default();

  // Assignment section
  StringSeqOpt aAssignmentLines = new StringSeqOpt();
  aAssignmentLines.__default();

  myLogger.log(LogEventId.DEBUG_LEVEL_2, "<" + myMethodNameName);
  return new ServiceOrderAssignment(aWireCenter, aServiceOrderNumber,
        aServiceOrderCorrectionSuffix, aTelephoneNumber,
        aLightspeedCircuitId, aTaperCode, aNetworkTypeChoice,
        aAssignmentLines, aLSTIndicator);
}

/**
* Method setSvcOrdAsgn.
* @return ServiceOrderAssignment
*/
public ServiceOrderAssignment setServiceOrderAssignmentValue()
{
  String myMethodNameName = "SOAC: setServiceOrderAssignmentValue()";
  myUtility.log(LogEventId.DEBUG_LEVEL_2, ">" + myMethodNameName);

  // IMPORTANT:	These are preset values to accomodate the
  //				conversion to XML(Opt: Min=0). If the response value
  //				does NOT exist, leave at this preset value.
  StringOpt aWireCenter = IDLUtil.toOpt((String)null, false);
  StringOpt aServiceOrderNumber = IDLUtil.toOpt((String)null, false);
  StringOpt aServiceOrderCorrectionSuffix = IDLUtil.toOpt((String)null,
        false);
  StringOpt aTelephoneNumber = IDLUtil.toOpt((String)null, false);
  StringOpt aLightspeedCircuitId = IDLUtil.toOpt((String)null, false);
  StringOpt aTaperCode = IDLUtil.toOpt((String)null, false);
  //LS6
  BooleanOpt aLSTIndicator = new BooleanOpt();

  aLSTIndicator.theValue(false); // default

  // Network type data
  NetworkTypeOpt aNetworkTypeChoice = new NetworkTypeOpt();
  aNetworkTypeChoice.__default();

  // Assignment section
  StringSeqOpt aAssignmentLines = new StringSeqOpt();
  aAssignmentLines.__default();

  myUtility.log(LogEventId.DEBUG_LEVEL_2, "<" + myMethodNameName);
  return new ServiceOrderAssignment(aWireCenter, aServiceOrderNumber,
        aServiceOrderCorrectionSuffix, aTelephoneNumber,
        aLightspeedCircuitId, aTaperCode, aNetworkTypeChoice,
        aAssignmentLines, aLSTIndicator);
}

/**
* Method prepareAndForwardXml
* @param aContext
* @param aServiceOrderAssignment
* @param aStatus
* @param aProperties
* @param aParsedFCIF
* @throws Exception
*/
protected void prepareAndForwardXml(BisContext aContext,
     ServiceOrderAssignmentOpt aServiceOrderAssignment,
     ExceptionDataOpt aStatus, ObjectPropertySeqOpt aProperties,
     SOACServiceOrderResponseParser aParsedFCIF) throws Exception
{
  String myMethodNameName = "SOAC: prepareAndForwardXml()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

  ObjectPropertyManager aContextOPM = new ObjectPropertyManager(
        aContext.aProperties);
  String xmlResp = null;
  Properties messageTags = null;

  try
  {
     messageTags = new Properties();
     messageTags.setProperty("embus:MessageTag", SERVICE_FUNCTION_KEY);
     messageTags.setProperty("embus:ApplicationID", "ApplicationID");
     messageTags.setProperty("embus:MessageID", "MessageID");
     messageTags.setProperty("embus:ConversationKey", "ConversationKey");
     messageTags.setProperty("embus:LoggingKey", "LoggingKey");
     messageTags.setProperty("embus:ResponseMessageExpiration", "0");

     PublishFacilityAssignmentOrderNotificationReturn publishFacilityAssignmentRet = new PublishFacilityAssignmentOrderNotificationReturn(
           aContext, aServiceOrderAssignment, aStatus, aProperties);

     _publishFacilityAssignmentOrderNotificationResponse publishFacilityAssignmentResp = new _publishFacilityAssignmentOrderNotificationResponse();

     publishFacilityAssignmentResp
           .aPublishFacilityAssignmentOrderNotificationReturn(publishFacilityAssignmentRet);

     _publishFacilityAssignmentOrderNotificationResponseBISMsg respBisMsg = new _publishFacilityAssignmentOrderNotificationResponseBISMsg(
           publishFacilityAssignmentResp);

     xmlResp = VAXBDocumentWriter.encode(respBisMsg);

     xmlResp = SoapParserHelper.appendEMBUSSoapEnvelope(SoapParserHelper
           .removeTagsFromXML(xmlResp, "<vaxb:VAXB", "</vaxb:VAXB>"),
           messageTags);

     myLogger.log(LogEventId.INFO_LEVEL_2, "XML Resp: [" + xmlResp + "]");
  }
  catch(IOException ioe)
  {
     myLogger
           .log(
                 LogEventId.AUDIT_TRAIL,
                 "Conversion to XML from PublishFacilityAssignmentOrderNotification object failure: "
                       + ioe.getMessage());
     myUtility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
           "XML conversion produced an IOException " + ioe.getMessage(),
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }
  //LS6
  forwardXMLToClient(aContext, xmlResp, messageTags);

  myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
}
/**
* The ClientService determines who the client is and sends along the reponse that was prepared.
* @param aContext
* @param aApplicationID
* @param aXmlResp
* @param aMessageTags
* @throws BusinessViolation
* @throws Exception
*/
protected void forwardXMLToClient(BisContext aContext, String aXmlResp,
     Properties aMessageTags)
     throws Exception
{
  String myMethodNameName = "SOAC: forwardXMLToClient()";
  myLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
  ClientService clientService = null;
  try
  {
     clientService = new ClientService(myProperties, myLogger);

     Properties props = new Properties();
     Properties override = new Properties();

     //LS6
     clientService.setClient(aContext);
     clientService.logREMOTE_CALL();
     clientService.publishMessage(aXmlResp, aMessageTags);

  }
  catch(com.sbc.eia.bis.embus.service.access.ServiceException se)
  {
     myLogger.log(LogEventId.INFO_LEVEL_2, "MISSED_TARGET_XML: ["
           + aXmlResp + "]");
     myLogger.log(LogEventId.INFO_LEVEL_1, ">" + "ServiceException "
           + se.getMessage());
     throw se;
  }
  catch(Exception e)
  {
     myLogger.log(LogEventId.INFO_LEVEL_2, "MISSED_TARGET_XML: ["
           + aXmlResp + "]");     
     throw e;
  }
  finally
  {
     clientService.logREMOTE_RETURN();
     myLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);       
  }
}
/**
* Method getSOACWireCenterData.
* @param aPrimaryNpaNxx
* @param aSbcServingOfficeWireCenter
* @return SoacWireCenterRow
* @throws DataNotFound
*/
public SoacWireCenterRow getSOACWireCenterData(StringOpt aPrimaryNpaNxx,
     StringOpt aSbcServingOfficeWireCenter) throws DataNotFound
{
  String myMethodNameName = "SOAC.getSOACWireCenterData()";
  myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
  SoacWireCenterRow resultRow = null;
  try
  {
     resultRow = (new SoacWireCenterTable()).retrieveRow(aPrimaryNpaNxx
           .theValue(), aSbcServingOfficeWireCenter.theValue(),
           myProperties, (com.sbc.bccs.utilities.Logger)myUtility);
  }
  catch(Exception e)
  {
     myUtility.log(LogEventId.ERROR, "getSOACRegion() error: ["
           + e.getMessage() + "]");
     throw new DataNotFound();
  }

  if(resultRow == null)
     throw new DataNotFound();

  myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
  return resultRow;
}

/**
* Method getTopListenerSoacLinkData.
* @param aHostName
* @param aEntity
* @return TopListenerSoacLinkRow
* @throws DataNotFound
*/
public TopListenerSoacLinkRow getTopListenerSoacLinkData(String aHostName,
     String aEntity) throws DataNotFound
{
  String myMethodNameName = "SOAC.getTopListenerSoacLinkData()";
  myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
  TopListenerSoacLinkRow resultRow = null;
  try
  {
     resultRow = (new TopListenerSoacLinkTable()).retrieveRow(aHostName,
           aEntity, myProperties, (com.sbc.bccs.utilities.Logger)myUtility);
  }
  catch(Exception e)
  {
     myUtility.log(LogEventId.ERROR,
           "getTopListenerSoacLinkData() error: [" + e.getMessage() + "]");
     throw new DataNotFound();
  }

  if(resultRow == null)
     throw new DataNotFound();

  myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
  return resultRow;
}

/**
* Method publishFacilityAssignmentOrderNotification
* @param aUtility
* @param parsedFCIF
* @throws SystemFailure
* @throws Exception
*/
public void publishTNAssignmentOrderNotification(Utility aUtility,
     SOACServiceOrderResponseParser aParsedFCIF, String correlationId)
     throws SystemFailure, Exception
{

  String myMethodNameName = "SOAC:publishTNAssignmentOrderNotification()";
  myUtility.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);

  BisContext aContext = null;
  StringOpt aSOACServiceOrderNumber = null;
  StringOpt aSOACServiceOrderCorrectionSuffix = null;
  StringSeqOpt aAssignmentLines = null;
  ExceptionDataOpt aStatus = null;
  ObjectPropertySeqOpt aObjectProperties = null;

  // instantiate SoacFCIF
  SoacFCIF aSoacFCIF = new SoacFCIF(myProperties, aUtility);

  try
  {
     // Return to OMS via Email or XML on queue.
     // NOTE: A try/catch block will need surround those FCIF values that are not fatal
     // to the process if they do not exist. The *C2 header values (ie FunctionType,
     // SOACServiceOrderNum, WireCenter) MUST exist or the process will fail so the
     // larger try/catch will handle those.

     if((aParsedFCIF.getFunctionType().equalsIgnoreCase(
           COMPLETION_SUB_ACTION_TYPE) || aParsedFCIF.getFunctionType()
           .equalsIgnoreCase(CANCELLATION_SUB_ACTION_TYPE))
           && (isErrorStatusCode(aParsedFCIF.getStatusCode()) == true))
     {

        sendEmail(aContext, aUtility, aParsedFCIF);

     }
     else if(aSoacFCIF.isUnsolicitedMsg(aParsedFCIF.getStatusCode()) == true)
     {

        sendEmail(aContext, aUtility, aParsedFCIF);

     }
     else
     {
        // aBisContext
        aContext = BisContextHelper.setBisContext(null, null, null,
              correlationId, myProperties);
        aUtility.log(LogEventId.DEBUG_LEVEL_2, "aContext=<"
              + (new BisContextBisHelper(aContext)).toString() + ">");

        // aSoacServiceOrderNumber
        aSOACServiceOrderNumber = (StringOpt)IDLUtil.toOpt(aParsedFCIF
              .getSOACServiceOrderNum());
        aUtility.log(LogEventId.DEBUG_LEVEL_2, "aSOACServiceOrderNumber=<"
              + (new StringOptBisHelper(aSOACServiceOrderNumber))
                    .toString() + ">");

        // aSOACServiceOrderCorrectionSuffix
        aSOACServiceOrderCorrectionSuffix = (StringOpt)IDLUtil
              .toOpt(aParsedFCIF.getCorrectionSuffix());
        aUtility.log(LogEventId.DEBUG_LEVEL_2,
              "aSOACServiceOrderCorrectionSuffix=<"
                    + (new StringOptBisHelper(
                          aSOACServiceOrderCorrectionSuffix)).toString()
                    + ">");

        // aAssignmentLines
        aAssignmentLines = (StringSeqOpt)IDLUtil.toOpt(aParsedFCIF
              .getAssgnSectionString());
        aUtility.log(LogEventId.DEBUG_LEVEL_2, "aAssignmentLines=<"
              + (new StringSeqOptBisHelper(aAssignmentLines)).toString()
              + ">");

        // aStatus
        aStatus = (ExceptionDataOpt)IDLUtil.toOpt(ExceptionDataOpt.class,
              setStatus(aParsedFCIF));
        aUtility.log(LogEventId.DEBUG_LEVEL_2, "aStatus=<"
              + (new ExceptionDataOptBisHelper(aStatus)).toString() + ">");

        // aObjectProperties
        aObjectProperties = new ObjectPropertySeqOpt();
        aObjectProperties.__default();

        aUtility.log(LogEventId.DEBUG_LEVEL_2, "aObjectProperties=<"
              + (new ExceptionDataOptBisHelper(aStatus)).toString() + ">");

        // build the return object
        PublishTNAssignmentOrderNotificationReturn aReturnObject = new PublishTNAssignmentOrderNotificationReturn(
              aContext, aSOACServiceOrderNumber,
              aSOACServiceOrderCorrectionSuffix, aAssignmentLines, aStatus,
              aObjectProperties);
        // send message to OMS
        publishMessage(aContext, aReturnObject);
     }
  }
  catch(SystemFailure sfe)
  {
     throw sfe;
  }
  catch(Exception e)
  {
     myUtility.log(LogEventId.ERROR,
           "Found error in PublishTNAssignmentOrderNotification: ["
                 + e.getMessage() + "]");

     if(aContext == null)
        throw e;
     else
     {
        myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
              "Exception message: " + e.getMessage(), (String)myProperties
                    .get("BIS_NAME").toString(), Severity.UnRecoverable);
     }
  }
  myUtility.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);
}

/**
* Method publishMessage.
* @param aContext
* @param aReturnObject
* @throws DataNotFound
* @throws ObjectNotFound
* @throws NotImplemented
* @throws SystemFailure
* @throws BusinessViolation
* @throws AccessDenied
* @throws InvalidData
* @throws ServiceException
*/
private void publishMessage(BisContext aContext,
     PublishTNAssignmentOrderNotificationReturn aReturnObject)
     throws DataNotFound, ObjectNotFound, NotImplemented, SystemFailure,
     BusinessViolation, AccessDenied, InvalidData,
     com.sbc.eia.bis.embus.service.access.ServiceException, Exception
{

  String myMethodNameName = "SOAC.publishMessage()";
  myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

  ObjectPropertyManager aContextOPM = new ObjectPropertyManager(
        aContext.aProperties);
  String aBisMessage = null;
  String aResponseXML = null;

  try
  {
     Properties messageTags = new Properties();
     messageTags.setProperty("embus:MessageTag", SERVICE_FUNCTION_KEY_ptao);
     messageTags.setProperty("embus:ApplicationID", "ApplicationID");
     messageTags.setProperty("embus:MessageID", "MessageID");
     messageTags.setProperty("embus:ConversationKey", "ConversationKey");
     messageTags.setProperty("embus:LoggingKey", "LoggingKey");
     messageTags.setProperty("embus:ResponseMessageExpiration", "0");

     _publishTNAssignmentOrderNotificationResponse aMessage = new _publishTNAssignmentOrderNotificationResponse();

     aMessage.aPublishTNAssignmentOrderNotificationReturn(aReturnObject);

     _publishTNAssignmentOrderNotificationResponseBISMsg aPTAO_BisMessage = new _publishTNAssignmentOrderNotificationResponseBISMsg(
           aMessage);

     aBisMessage = VAXBDocumentWriter.encode(aPTAO_BisMessage);

     aResponseXML = SoapParserHelper.appendEMBUSSoapEnvelope(
           SoapParserHelper.removeTagsFromXML(aBisMessage, "<vaxb:VAXB",
                 "</vaxb:VAXB>"), messageTags);

     myUtility.log(LogEventId.INFO_LEVEL_1, "XML Resp: [" + aResponseXML
           + "]");

  }
  catch(IOException ioe)
  {
  	myUtility.log(LogEventId.INFO_LEVEL_1,
  			"MISSED_TARGET_XML: " + aResponseXML);
  	
     myUtility.log(LogEventId.AUDIT_TRAIL,
           "Conversion to XML from PublishTNAssignmentOrderNotification object failure: "
                 + ioe.getMessage());

     //ioe.printStackTrace();
     myUtility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
           "XML conversion produced an IOException " + ioe.getMessage(),
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }

  myUtility.log(LogEventId.REMOTE_CALL, OMSHelper.OMS_SERVICE_NAME,
        OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
        OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
        OMSHelper.OMS_REQUEST);

  try
  {
     if(omsService == null)
     {
        omsService = new OMSService(myProperties,
              (com.sbc.bccs.utilities.Logger)myUtility);
     }

     Properties aProps = new Properties();
     Properties aOverride = new Properties();
     omsService.rmBisRequests(aResponseXML, aProps, aOverride);

  }
  catch(com.sbc.eia.bis.embus.service.access.ServiceException se)
  {
     myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + "ServiceException "
           + se.getMessage());
     throw se;
  }
  catch(Exception e)
  {
     myUtility.log(LogEventId.ERROR, ">"
           + "Found error in publishMessage: [" + e.getMessage() + "]");
     if(aContext == null)
        throw e;
     else
     {
        myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
              "Exception message: " + e.getMessage(), (String)myProperties
                    .get("BIS_NAME").toString(), Severity.UnRecoverable);
     }
  }
  finally
  {
     myUtility.log(LogEventId.REMOTE_RETURN, OMSHelper.OMS_SERVICE_NAME,
           OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
           OMSHelper.OMS_SERVICE_NAME + OMSHelper.OMS_VERSION,
           OMSHelper.OMS_REQUEST);
  }
  myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
}

/**
* Method setStatus.
* @param aParsedFCIF
* @return ExceptionData
*/
public ExceptionData setStatus(SOACServiceOrderResponseParser aParsedFCIF)
{
  String myMethodNameName = "SOAC.setStatus()";
  myLogger.log(LogEventId.DEBUG_LEVEL_2, ">" + myMethodNameName);

  String aSoacStatusCode = aParsedFCIF.getStatusCode().substring(0, 4);
  String aErrCode = aSoacStatusCode;
  String aErrDesc = getStatusDesc(aSoacStatusCode, aParsedFCIF);
  StringOpt aOrigination = new StringOpt();
  aOrigination.__default();
  SeverityOpt aSeverity = new SeverityOpt();
  aSeverity.__default();

  if(isErrorStatusCode(aSoacStatusCode) == true
        || aParsedFCIF.getExceptionCode() != null)
  {
     // The status code from SOAC is a known error.
     // NOTE: This exception can NOT be thrown, but will be converted to XML.
     //       ExceptionBuilder was not used since we need the ExceptionData object.
     //
     aErrCode = getExceptionCode(aSoacStatusCode, aParsedFCIF);

     aErrDesc = aSoacStatusCode
           + ": "
           + (getStatusDesc(aSoacStatusCode, aParsedFCIF) == "" ? getExceptionDesc(
                 aSoacStatusCode, aParsedFCIF)
                 : getStatusDesc(aSoacStatusCode, aParsedFCIF))
           + ". "
           + (aParsedFCIF.getExceptionCode() != null ? aParsedFCIF
                 .getExceptionCode() : "")
           + " "
           + (aParsedFCIF.getExceptionDescription() != null ? aParsedFCIF
                 .getExceptionDescription() : "");

     // the origination of this error is SOAC
     //aOrigination = IDLUtil.toOpt((String) myProperties.get("BIS_NAME").toString());
     aOrigination = IDLUtil.toOpt((String)"SOAC");
     aSeverity.theValue(Severity.UnRecoverable);
  }

  myLogger.log(LogEventId.DEBUG_LEVEL_2, "<" + myMethodNameName);

  return new ExceptionData(aErrCode, aErrDesc, aOrigination, aSeverity);
}

/**
* Method setStatusValue.
* @param aParsedFCIF
* @return ExceptionData
*/
public ExceptionData setStatusValue(SOACServiceOrderResponseParser aParsedFCIF)
{
  String myMethodNameName = "SOAC.setStatusValue()";
  myUtility.log(LogEventId.DEBUG_LEVEL_2, ">" + myMethodNameName);

  String aSoacStatusCode = aParsedFCIF.getStatusCode().substring(0, 4);
  String aErrCode = aSoacStatusCode;
  String aErrDesc = getStatusDesc(aSoacStatusCode, aParsedFCIF);
  StringOpt aOrigination = new StringOpt();
  aOrigination.__default();
  SeverityOpt aSeverity = new SeverityOpt();
  aSeverity.__default();

  if(isErrorStatusCode(aSoacStatusCode) == true
        || aParsedFCIF.getExceptionCode() != null)
  {
     // The status code from SOAC is a known error.
     // NOTE: This exception can NOT be thrown, but will be converted to XML.
     //       ExceptionBuilder was not used since we need the ExceptionData object.
     //
     aErrCode = getExceptionCode(aSoacStatusCode, aParsedFCIF);

     aErrDesc = aSoacStatusCode
           + ": "
           + (getStatusDesc(aSoacStatusCode, aParsedFCIF) == "" ? getExceptionDesc(
                 aSoacStatusCode, aParsedFCIF)
                 : getStatusDesc(aSoacStatusCode, aParsedFCIF))
           + ". "
           + (aParsedFCIF.getExceptionCode() != null ? aParsedFCIF
                 .getExceptionCode() : "")
           + " "
           + (aParsedFCIF.getExceptionDescription() != null ? aParsedFCIF
                 .getExceptionDescription() : "");
     
     aOrigination = IDLUtil.toOpt((String)"SOAC");
     aSeverity.theValue(Severity.UnRecoverable);
  } 

  if(AdditionalErrorDesc != null && AdditionalErrorDesc.trim().length()>0)
      aErrDesc += " [" + AdditionalErrorDesc + "]";
  
  myUtility.log(LogEventId.DEBUG_LEVEL_2, "<" + myMethodNameName);

  return new ExceptionData(aErrCode, aErrDesc, aOrigination, aSeverity);
}

/**
* Method sendEmail
* @param aContext
* @param aUtility
* @param aParsedFCIF
* @return void
*/
public void sendEmail(BisContext aContext, Utility aUtility,
     SOACServiceOrderResponseParser aParsedFCIF) throws SystemFailure,
     Exception
{

  String myMethodNameName = "SOAC.sendEmail()";
  myUtility.log(LogEventId.DEBUG_LEVEL_2, ">" + myMethodNameName);

  try
  {
     //  Standard eMail Format:
     //                      OMS Order Number
     //                      Due Date
     //                      LS Circuit ID
     //                      TN
     //                      SOAC Status Type
     //                      SOAC Status Description
     //                      PASS Type
     //                      SOAC Service Order Number
     //                      Error Section
     //                      Current Assignment Section
     //
     // (NOTE: Not all values are available in sendF1F2Order output.
     //
     String eSubject = "Lightspeed Assignment Problem";

     String OMSOrderNum = "NA";
     String DueDate = "NA";
     String OMSOrderActionNum = "NA";
     String LSCircuitID = "NA";
     String TelephoneNum = "NA";
     String StatusCode = "NA";
     String SOACServiceOrderNum = "NA";
     String SOACErrorMsg = "NA";
     StringBuffer AssgnSectionStrBuf = new StringBuffer("");
     try
     {
        OMSOrderNum = aParsedFCIF.getOMSOrderNum().trim();
     }
     catch(Exception e)
     {
     }

     try
     {
        DueDate = aParsedFCIF.getDueDate();
     }
     catch(Exception e)
     {
     }

     try
     {
        OMSOrderActionNum = aParsedFCIF.getOMSOrderActionNum().trim();
     }
     catch(Exception e)
     {
     }

     try
     {
        LSCircuitID = aParsedFCIF.getLSCircuitID();
     }
     catch(Exception e)
     {
     }

     try
     {
        TelephoneNum = aParsedFCIF.getTelephoneNum();
     }
     catch(Exception e)
     {
     }

     try
     {
        StatusCode = aParsedFCIF.getStatusCode().substring(0, 4).trim();
     }
     catch(Exception e)
     {
     }

     try
     {
        SOACServiceOrderNum = aParsedFCIF.getSOACServiceOrderNum();
     }
     catch(Exception e)
     {
     }

     try
     {
        SOACErrorMsg = aParsedFCIF.getExceptionMessage();
     }
     catch(Exception e)
     {
     }

     try
     {
        String[] AsgnmntArray = aParsedFCIF.getAssgnSectionString();
        for(int i = 0; i < AsgnmntArray.length; i++)
           AssgnSectionStrBuf.append(AsgnmntArray[i] + "\n");
     }
     catch(Exception e)
     {
        AssgnSectionStrBuf.append("NA");
     }

     String eBody = "A pending service order, or an existing service, may require your attention."
           + "\nLFACS reports that assignments may have changed on:"
           + "\nOMS Order Number:          "
           + OMSOrderNum
           + "\nDue date:                  "
           + DueDate
           + "\nOMS Order Action Num:      "
           + OMSOrderActionNum
           + "\nLS Circuit ID:             "
           + LSCircuitID
           + "\nTelephone Number:          "
           + TelephoneNum
           + "\nSOAC Status Type:          "
           + StatusCode
           + "\nSOAC Status Description:   "
           + (getExceptionDesc(StatusCode, aParsedFCIF) != "" ? getExceptionDesc(
                 StatusCode, aParsedFCIF)
                 : getStatusDesc(StatusCode, aParsedFCIF))
           + "\nNetwork Type:              "
           + aParsedFCIF.getNetworkType()
           + "\nPASS Type:                  NA"
           + "\nSOAC Service Order Number: "
           + SOACServiceOrderNum
           + "\nError Section:             "
           + SOACErrorMsg
           + "\nCurrent Assignment Section:\n"
           + AssgnSectionStrBuf.toString()
           + "\n\nPlease contact the appropriate assignment center and if necessary issue a change order, or a"
           + "\nsupplement on the pending order, to resolve this problem.  If there is no pending order, the change"
           + "\norder you issue should be today.";

     myUtility.log(LogEventId.INFO_LEVEL_1, "Sending email: [" + eBody
           + "]");

     (new OMSEmailHelper(aUtility, myProperties)).prepareAndSendEmail(
           eSubject, eBody);
  }
  catch(Exception e)
  {
     myUtility.log(LogEventId.ERROR, "Found error in SOAC:sendEmail: ["
           + e.getMessage() + "]");

     if(aContext == null)
        throw e;
     else
     {
        myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
              "Exception message: " + e.getMessage(), (String)myProperties
                    .get("BIS_NAME").toString(), Severity.UnRecoverable);
     }
  }
  myUtility.log(LogEventId.DEBUG_LEVEL_2, "<" + myMethodNameName);
}

private boolean isNumeric(String inString)
{
  for(int i = 0; i < inString.length(); i++)
  {
     if(!Character.isDigit(inString.toUpperCase().charAt(i)))
        return false;
  }
  return true;
}

private boolean isAlphNum(String inString)
{
  for(int i = 0; i < inString.length(); i++)
  {
     if(!Character.isLetterOrDigit(inString.toUpperCase().charAt(i)))
        return false;
  }
  return true;
}

private boolean isAlph(String inString)
{
  for(int i = 0; i < inString.length(); i++)
  {
     if(!Character.isLetter(inString.toUpperCase().charAt(i)))
        return false;
  }
  return true;
}
//LS6: MFI
public void buildSoaObj(BisContext aContext, ServiceOrderAssignment soaObj,
       SOACServiceOrderResponseParser parsedFCIF,
       String aTransactionName,
       String aApplicationID)
  throws SystemFailure, Exception
{
String myMethodNameName = "SOAC:buildSOAObj()";
myUtility.log(LogEventId.INFO_LEVEL_1, ">" + myMethodNameName);     
try
{
   AdditionalErrorDesc = null;
   soaObj.aWireCenter.theValue(parsedFCIF.getWireCenter());
   soaObj.aServiceOrderNumber.theValue(parsedFCIF
         .getSOACServiceOrderNum());
   soaObj.aServiceOrderCorrectionSuffix.theValue(parsedFCIF
         .getCorrectionSuffix());    
   
   try
   {
      if(parsedFCIF.getLSCircuitID() != null)
         soaObj.aLightspeedCircuitId.theValue(parsedFCIF
               .getLSCircuitID());
   }
   catch(Exception e) {}
   
   String aNetworkType = parsedFCIF.getNetworkType().trim();
   
   if(aNetworkType == null || aNetworkType.trim().length() < 1)
   {
       myUtility.log(LogEventId.INFO_LEVEL_2, "Missing required data: NETWORK TYPE");
       AdditionalErrorDesc = "Missing required data: NETWORK TYPE";
   }

   if(aNetworkType.equalsIgnoreCase(SvcOrderConstants.RGPN_NETWORK))
      aNetworkType = NetworkTypeValues.RGPON;

   NetworkType aNetworkTypeChoice = new NetworkType();
   
   if((!aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTP)
         && !aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTN)
         && !aNetworkType.equalsIgnoreCase(NetworkTypeValues.RGPON) 
         && !aNetworkType.equalsIgnoreCase(NetworkTypeValues.GPON)))
   {
       AdditionalErrorDesc = "Invalid NETWORK TYPE: " + aNetworkType + ".";
       myUtility.log(LogEventId.INFO_LEVEL_2, AdditionalErrorDesc);         
   }
   else
   {    
     myUtility.log(LogEventId.INFO_LEVEL_1, "Processing a ["
         + aNetworkType + "] response.");

     if(aNetworkType.equalsIgnoreCase(NetworkTypeValues.FTTP)
           || aNetworkType.equalsIgnoreCase(NetworkTypeValues.RGPON)
           || aNetworkType.equalsIgnoreCase(NetworkTypeValues.GPON))
     {
        if(parsedFCIF.getFTTP() != null)
        {
           aNetworkTypeChoice.aFttp(parsedFCIF.getFTTP()); // FTTP Response
           soaObj.aNetworkTypeChoice.theValue(aNetworkTypeChoice);
        }
     }
     else
     {
        if(parsedFCIF.getFTTN() != null)
        {
           aNetworkTypeChoice.aFttn(parsedFCIF.getFTTN()); // FTTN Response
           soaObj.aNetworkTypeChoice.theValue(aNetworkTypeChoice);
        }
     }
   }
   
   try
   {
      if(parsedFCIF.getAssgnSectionString() != null)
         soaObj.aAssignmentLines.theValue(parsedFCIF
               .getAssgnSectionString());
   }
   catch(Exception e) {}

   String aTelephoneNum = "";
   String aTelNum = "";
   try
   {
      if(parsedFCIF.getTelephoneNum() != null
            || parsedFCIF.getTelephoneNum().trim().length() > 0)
      {
         aTelNum = parsedFCIF.getTelephoneNum().trim();

         for(int i = 0; i < aTelNum.length(); i++)
         {
            if(Character.isLetterOrDigit(aTelNum.charAt(i)))
               aTelephoneNum = aTelephoneNum + aTelNum.charAt(i);
         }
      }
   }
   catch(Exception e) {}

   soaObj.aTelephoneNumber.theValue(aTelephoneNum);   

   if(parsedFCIF.getLSTIndicator().theValue())
   {
      LSTIndicator.theValue(true);
   }
   soaObj.aLSTIndicator.theValue(LSTIndicator.theValue());  
     
}
catch(Exception e)
{
   myUtility.log(LogEventId.ERROR,
         "Found error in " + aTransactionName + ": ["
               + e.getMessage() + "]");

   if(aContext == null)
      throw e;
   else
   {
      myUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
            "Exception message: " + e.getMessage(), (String) myProperties
                  .get("BIS_NAME").toString(), Severity.UnRecoverable);
   }
}
myUtility.log(LogEventId.INFO_LEVEL_1, "<" + myMethodNameName);  
}      

public void prepareAndForwardXml(BisContext aContext,
     ServiceOrderAssignmentOpt aServiceOrderAssignment,
     ExceptionDataOpt aStatus, ObjectPropertySeqOpt aProperties,
     SOACServiceOrderResponseParser aParsedFCIF, 
     String aTransactionName) 
throws Exception
{
  String myMethodNameName = "SOAC: prepareAndForwardXml()";
  myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);

  ObjectPropertyManager aContextOPM = new ObjectPropertyManager(
        aContext.aProperties);
  String xmlResp = null;
  Properties messageTags = null;

  try
  {
     messageTags = new Properties();
     messageTags.setProperty("embus:MessageTag", aTransactionName);
     messageTags.setProperty("embus:ApplicationID", "ApplicationID");
     messageTags.setProperty("embus:MessageID", "MessageID");
     messageTags.setProperty("embus:ConversationKey", "ConversationKey");
     messageTags.setProperty("embus:LoggingKey", "LoggingKey");
     messageTags.setProperty("embus:ResponseMessageExpiration", "0");

     xmlResp = VAXBDocumentWriter.encode(createRespBisMsgMFI(aContext, aServiceOrderAssignment, aStatus, aProperties));

     xmlResp = SoapParserHelper.appendEMBUSSoapEnvelope(SoapParserHelper
           .removeTagsFromXML(xmlResp, "<vaxb:VAXB", "</vaxb:VAXB>"),
           messageTags);

     myUtility.log(LogEventId.INFO_LEVEL_2, "XML Resp: [" + xmlResp + "]");
  }
  catch(IOException ioe)
  {
     myUtility
           .log(
                 LogEventId.AUDIT_TRAIL,
                 "Conversion to XML from " + aTransactionName + " object failure: "
                       + ioe.getMessage());
     myUtility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
           "XML conversion produced an IOException " + ioe.getMessage(),
           (String)myProperties.get("BIS_NAME").toString(),
           Severity.UnRecoverable);
  }

  forwardXMLToClient(aContext, xmlResp, messageTags);

  myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
} 

private _modifyFacilityInfoResponseBISMsg createRespBisMsgMFI(BisContext aContext,
       ServiceOrderAssignmentOpt aServiceOrderAssignment,
       ExceptionDataOpt aStatus, ObjectPropertySeqOpt aProperties)
 {
    String myMethodNameName = "SOAC.prepareXml()";
    myUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodNameName);
    
    ModifyFacilityInfoReturn modifyFacilityInfoRet = new ModifyFacilityInfoReturn(
          aContext, aServiceOrderAssignment, aStatus, aProperties);

    _modifyFacilityInfoResponse modifyFacilityInfoResp = new _modifyFacilityInfoResponse();

    modifyFacilityInfoResp
  	.aModifyFacilityInfoReturn(modifyFacilityInfoRet);

    _modifyFacilityInfoResponseBISMsg respBisMsg = new _modifyFacilityInfoResponseBISMsg(
          modifyFacilityInfoResp);        
    
    myUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodNameName);
    
    return respBisMsg;
 }  

/**
* LS7
* @return an instance of SOACServiceOrderRequestGenerator
*/
protected SOACServiceOrderRequestGenerator makeSOACServiceOrderRequestGenerator()
{
  return new SOACServiceOrderRequestGenerator(myProperties, (Logger)myUtility); // later try taking out the cast to Logger see if it still work
}
}