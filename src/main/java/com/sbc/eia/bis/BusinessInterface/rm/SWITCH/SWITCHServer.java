//$Id: SWITCHServer.java,v 1.12 2009/09/21 00:03:00 js7440 Exp $
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
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 06/07/2006  Rene Duka             Creation.
//# 06/23/2006  Rene Duka             LS R3 enhancements. Code WT changes.
//# 07/10/2006  Rene Duka             LS R3 enhancements. Integration Test changes.
//# 02/06/2007  Rene Duka             DR 174665: Convert the Requester ID to Requester Category mappings into property file.
//# 02/23/2007  Rene Duka             60665: Format SWITCH DueDate to YYYYMMDD.
//# 05/21/2007  Jon Costa			  DR66698: check for null value in aTNLimitationValue.
//# 07/20/2007  Doris sunga			  CR 19539: Added code to handle system failure
//# 03/12/2008  Jon Costa             CR21918: Remove WSINTU order nbr/due date check.
//# 03/31/2009 	Sheilla Lirio		  DR125501: need to update log so that it logs the response

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.rm.database.CvoipOrderRow;
import com.sbc.eia.bis.rm.database.CvoipOrderTable;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.SeverityOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.gwsvcs.access.vicuna.EventResultPair;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceException;
import com.sbc.gwsvcs.access.vicuna.exceptions.ServiceTimeoutException;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerAccess;
import com.sbc.gwsvcs.service.switchserver.SWITCHServerHelper;
import com.sbc.gwsvcs.service.switchserver.exceptions.SWITCHServerException;
import com.sbc.gwsvcs.service.switchserver.interfaces.ExceptionResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SWITCHSERVER_Const;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuResp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.TrnsptType_e;

/**
 * Provides SWITCH Update process for sendTNAssignmentOrder (sTAO) transaction
 * @author: Rene Duka
 */

public class SWITCHServer
{
    private Utility aUtility = null;
    private Hashtable aProperties = null;
    private SWITCHServerHelper aSWITCHServerHelper = null;
    private String aSwitchName = null;
    private String aRuleFile = null;
    private String aOriginator = null;
    private String aTNList = null;
    private SWITCHServerReturn aReturnObject = null;
    private static Properties aRequesterIdToRequesterCategory = new Properties();
    
    /**
     * Constructor for SWITCHServer
     * @param Utility utility
     * @param Hashtable properties
     */
    public SWITCHServer(Utility utility, Hashtable properties) {
        aProperties = properties;
        aUtility = utility;
        String aRuleFile = (String) properties.get("EXCEPTION_BUILDER_SWITCH_RULE_FILE");
    }
    
    /**
     * Method: updateSWITCH
     * @param BisContext            aContext
     * @param String                aSOACServiceOrderNumber
     * @param EiaDate               aOriginalDueDate
     * @param String                aSWITCHActionType
     * @param String                aTN
     * @param String                aRequesterId
     * @return SWITCHServerReturn
     */
    public SWITCHServerReturn updateSWITCH(
        BisContext aContext, 
        String aSOACServiceOrderNumber,
        EiaDate aOriginalDueDate,
        String aSWITCHActionType,
        String aTN,
        String aRequesterId)
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {

        String myMethodName = "SWITCHServer: updateSWITCH()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        String aWireCenter = (String) aProperties.get("CVOIP_SWITCH_HIPCS_WC");
        aRuleFile = (String) aProperties.get("EXCEPTION_BUILDER_SWITCH_RULE_FILE");
        aOriginator = (String) aProperties.get("BIS_NAME");
        aSwitchName = SWITCHServerAccess.name + "-" + SWITCHServerAccess.version;

        // load the Requester ID to Requester Category mappings
        if (aRequesterIdToRequesterCategory.size() < 1)
            loadRequesterIdToRequesterCategory();

        aReturnObject = new SWITCHServerReturn();
    
        // build SwitchServerHelper
        if (aSWITCHServerHelper == null) {
            try {
                aSWITCHServerHelper = new SWITCHServerHelper(aProperties, aUtility);
            } 
            catch (ServiceException e) {
                String aErrDesc = "SwtichServerHelper Failure: "
                                        + e.getExceptionCode()
                                        + " "
                                        + e.getMessage();
                                        
                aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                aUtility.throwException(ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
                                        aErrDesc,
                                        aOriginator,
                                        Severity.Recoverable);
            }
        }

        // send WSINTU request
        // If WSINTU request is successful, send UPDTRM request
        // Update the cVOIP Order table with SOAC Order Number and TN. 
        // If any of the TNs returns an error, RM-BIS will not send the order to cVOIP SOAC and will not update the cVOIP Order table.

        // format header
        Header_t aHeader_t = new Header_t("RMBIS", "", "", "", TrnsptType_e.RPC_TRNSPT, "");
        aTNList = null; // null attribute for WSINTU to reset
        
        // process SwitchWsiNtuReq_t request
        boolean aWSINTUOk = sendWSINTURequest(aContext, 
                                               aHeader_t, 
                                               aSOACServiceOrderNumber, 
                                               aWireCenter, 
                                               aSWITCHActionType, 
                                               aTN, 
                                               aRequesterId);

        if (aWSINTUOk) {
            // log the succesful SwitchWsiNtuReq_t request for this TN
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "TN: " + aTN + " SUCCESSFUL SwitchWsiNtuReq_t request!");
            
            // process SwitchTnUpdReq_t request
            boolean aUPDTRMOk = sendUPDTRMRequest(aContext,
                                                   aSWITCHActionType,         
                                                   aHeader_t,
                                                   aSOACServiceOrderNumber,
                                                   aWireCenter,
                                                   aOriginalDueDate,
                                                   aTN);
            if (aUPDTRMOk) {
                // log the succesful SwitchTnUpdReq_t request for this TN
                aUtility.log(LogEventId.DEBUG_LEVEL_1, "TN: " + aTN + " SUCCESSFUL SwitchTnUpdReq_t request!");                
                // update CVOIP Order table
                updateCVOIPOrderTable(aContext,
                                      aSWITCHActionType,
                                      aSOACServiceOrderNumber,
                                      aTN);
            }                                          
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aReturnObject;
    }
    
    /**
     * Method: sendWSINTURequest
     * @param BisContext            aContext
     * @param Header_t              aHeader
     * @param String                aSOACServiceOrderNumber
     * @param String                aWireCenter
     * @param String                aSWITCHActionType
     * @param String                aTN
     * @param String                aRequesterId
     * @return int
     */
    private boolean sendWSINTURequest(
        BisContext aContext, 
        Header_t aHeader,
        String aSOACServiceOrderNumber,
        String aWireCenter,
        String aSWITCHActionType,
        String aTN,
        String aRequesterId)        
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {

        String myMethodName = "SWITCHServer: sendWSINTURequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        boolean aWSINTUOk = true;
        String aEvent = "5170 - SwitchWsiNtuReq_t";

        // initilize request
        SwitchWsiNtuReq_t aRequest = null;
        
        try {
            // log REMOTE CALL to SWITCH
            logCALLtoSWITCH(LogEventId.REMOTE_CALL, aEvent);

            // format request
            WsiNtuRequestHelper aRequestHelper = new WsiNtuRequestHelper(aUtility, 
                                                                         aProperties,
                                                                         aHeader,
                                                                         aWireCenter,
                                                                         aTN);
            aRequest = aRequestHelper.getRequest();                                                                     
            
            // log request
            aUtility.log(LogEventId.INFO_LEVEL_2,
                         aEvent + " Input Request-->["
                                + " TN <"+ aTN + ">" 
                                + " WireCenter <" + aWireCenter + "> ]");

            // send and evaluate request
            EventResultPair aResult = null;

            try {
                aResult = aSWITCHServerHelper.switchWsiNtuReq(null, null, aSWITCHServerHelper.USE_DEFAULT_TIMEOUT, aRequest);
            } 
            catch (SWITCHServerException e) {
                String aErrDesc = "SwtichServer Failure ==> SWITCHServerException: "
                                        + e.getExceptionCode()
                                        + " "
                                        + e.getMessage();
                aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                aUtility.throwException(ExceptionCode.ERR_RM_SWITCH_SERVER,
                                        aErrDesc,
                                        aOriginator,
                                        Severity.UnRecoverable);
            } 
            catch (ServiceTimeoutException e) {
                String aErrDesc = "SwtichServer Timeout ==> ServiceTimeoutException: "
                                        + e.getExceptionCode()
                                        + " "
                                        + e.getMessage();
                aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                aUtility.throwException(ExceptionCode.ERR_RM_TIMEOUT,
                                        aErrDesc,
                                        aSwitchName,
                                        Severity.UnRecoverable);
            } 
            catch (ServiceException e) {
                String aErrDesc = "SwtichServerHelper ==> ServiceException: "
                                        + e.getExceptionCode()
                                        + " "
                                        + e.getMessage();
                aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                ExceptionBuilder.parseException(aContext,
                                                aRuleFile,
                                                "",
                                                e.getOriginalExceptionCode(),
                                                e.getMessage(),
                                                true,
                                                ExceptionBuilderRule.NO_DEFAULT,
                                                null,
                                                null,
                                                aUtility,
                                                null,
                                                null,
                                                null);
            }
            finally {
                // log REMOTE RETURN from SWITCH
                logCALLtoSWITCH(LogEventId.REMOTE_RETURN, aEvent);
            }

            // log event received
            aUtility.log(LogEventId.INFO_LEVEL_2,
                         "Received Event: " + aResult.getEventNbr());
            
            // initialize response
            SwitchWsiNtuResp_t aResponse = null;        

            // evaluate event number
            switch (aResult.getEventNbr()) {
                // Received event 5171. Success.
                case SWITCHServerAccess.SWITCH_WSI_NTU_RESP_NBR: 
                {
                    aResponse = (SwitchWsiNtuResp_t) aResult.getTheObject();

                    // parse the response
                    WsiNtuResponseHelper aResponseHelper = new WsiNtuResponseHelper(aUtility, aProperties);
                    aResponseHelper.parseResponse(aResponse);
                    
                    // check if there is an error from SWITCH
                    if (aResponseHelper.getIsError()) {
                        aReturnObject.setTNSourcePoolUpdateNotifier(false);
                        aReturnObject.setError(buildExceptionData(aResponseHelper.getUmsg_MSG_NBR(), 
                                                                  aResponseHelper.getUmsg_MSG_TX()));
                        aReturnObject.setNextTN(true);
            
                        aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN 
                                                             + " Error Code: " + aResponseHelper.getUmsg_MSG_NBR()
                                                             + " Error Message: " + aResponseHelper.getUmsg_MSG_TX());
                        aWSINTUOk = false;
                    }
                    else {
                        // if SWITCH Action Type is I - port TN in SWITCH
                        if (aSWITCHActionType.equalsIgnoreCase("I")) {
    
                            aWSINTUOk = portHIPCSTn(aContext,
                                                    aSWITCHActionType,
                                                    aSOACServiceOrderNumber,
                                                    aTN,
                                                    aRequesterId,
                                                    aResponseHelper);
    
                        }
                        // if SWITCH Action Type is O - remove/cancel status in SWITCH
                        else if (aSWITCHActionType.equalsIgnoreCase("O")) {
    
                            aWSINTUOk = cancelHIPCSTn(aContext,
                                                      aSWITCHActionType,
                                                      aSOACServiceOrderNumber,
                                                      aTN,
                                                      aResponseHelper);
    
    
                        }
                        // if SWITCH Action Type is NEITHER I nor O
                        else {
                            String aErrDesc = "SWITCH Action Type in CVOIP Rules Table is neither I nor O." + " - "
                                                        + aResult.getEventNbr() + " - "
                                                        + aTN;
                            aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                            aUtility.throwException(ExceptionCode.ERR_RM_INVALID_DATA,
                                                    aErrDesc,
                                                    aOriginator,
                                                    Severity.UnRecoverable);
                        }
                    }
                    break;
                }

                // Received event 9999. Exception event.
                case SWITCHServerAccess.EXCEPTION_NBR:   
                {
                    ExceptionResp_t aErrorResponse = (ExceptionResp_t) aResult.getTheObject();

                    // if error is a SWITCH timeout error, process next TN
                    if (aErrorResponse.OutExcp.ERR_CD == 9015) {
                        aReturnObject.setTNSourcePoolUpdateNotifier(false);
                        aReturnObject.setError(buildExceptionData(Integer.toString(aErrorResponse.OutExcp.ERR_CD), 
                                                                  aErrorResponse.OutExcp.ERR_TX));
                        aReturnObject.setNextTN(true);
            
                        aUtility.log(LogEventId.DEBUG_LEVEL_1, "TN: " + aTN 
                                                             + " Error Code: " + Integer.toString(aErrorResponse.OutExcp.ERR_CD)
                                                             + " Error Message: " + aErrorResponse.OutExcp.ERR_TX);
                        aWSINTUOk = false;
                    }
                    else  {
                        String aErrDesc = "SwtichServerAccess ==> Exception: "
                                                    + aErrorResponse.OutExcp.ERR_TX;
                        aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                        aUtility.throwException(Integer.toString(aErrorResponse.OutExcp.ERR_CD),
                                                aErrDesc,
                                                aOriginator,
                                                Severity.UnRecoverable);
                    }
                    break;
                }
                // Unknown event.
                default: 
                {
                    String aErrDesc = "SWITCHServerHelper failure: SWITCHServerAccess: Unexpected event returned from SWITCHServerHelper:SwitchWsiNtutResp_t: " 
                                                + aResult.getEventNbr();
                    aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                    aUtility.throwException(ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
                                            aErrDesc,
                                            aOriginator,
                                            Severity.UnRecoverable);

                    break;
                }
            }
        } 
        finally {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        }

        return aWSINTUOk;
    }
    
    /**
     * Method: sendUPDTRMRequest
     * @param BisContext            aContext
     * @param String                aSWITCHActionType
     * @param Header_t              aHeader
     * @param String                aSOACServiceOrderNumber
     * @param String                aWireCenter
     * @param EiaDate               aDueDate
     * @param String                aTN
     * @return boolean
     */
    private boolean sendUPDTRMRequest(
        BisContext aContext, 
        String aSWITCHActionType,         
        Header_t aHeader,
        String aSOACServiceOrderNumber,
        String aWireCenter,
        EiaDate aDueDate,
        String aTN)
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {

        String myMethodName = "SWITCHServer: sendUPDTRMRequest()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        boolean aUPDTRMOk = true;          
        String aEvent = "5010 - SwitchTnUpdReq_t";

        // initilize request
        SwitchTnUpdReq_t aRequest = null;
        
        try {
            // log REMOTE CALL to SWITCH
            logCALLtoSWITCH(LogEventId.REMOTE_CALL, aEvent);

            // format request
            TnUpdRequestHelper aRequestHelper = new TnUpdRequestHelper(aUtility, 
                                                                       aProperties,
                                                                       aSWITCHActionType,
                                                                       aHeader,
                                                                       aSOACServiceOrderNumber,
                                                                       aWireCenter,
                                                                       aDueDate,
                                                                       aTN,
                                                                       aTNList);

            aRequest = aRequestHelper.getRequest();                                                                     

            // log request
            aUtility.log(LogEventId.INFO_LEVEL_2,
                         aEvent + " Input Request-->["
                                + " TN <"+ aTN + ">" 
                                + " WireCenter <" + aWireCenter + ">"
                                + " Due Date <" + aRequest.TnUpdReq[0].Ntu.Uattr.DUE_DT.YR
                                                + aRequest.TnUpdReq[0].Ntu.Uattr.DUE_DT.MO
                                                + aRequest.TnUpdReq[0].Ntu.Uattr.DUE_DT.DY + "> " 
                                + " TN_RLS_DT_TX <" + aRequest.TnUpdReq[0].Ntu.Uattr.TN_RLS_DT_TX + "> ]");

            // send and evaluate request
            EventResultPair aResult = null;

            try {
                aResult = aSWITCHServerHelper.switchTnUpdReq(null, null, aSWITCHServerHelper.USE_DEFAULT_TIMEOUT, aRequest);
            } 
            catch (SWITCHServerException e) {
                String aErrDesc = "SwtichServer Failure ==> SWITCHServerException: "
                                        + e.getExceptionCode()
                                        + " "
                                        + e.getMessage();
                aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                aUtility.throwException(ExceptionCode.ERR_RM_SWITCH_SERVER,
                                        aErrDesc,
                                        aOriginator,
                                        Severity.UnRecoverable);
            } 
            catch (ServiceTimeoutException e) {
                String aErrDesc = "SwtichServer Timeout ==> ServiceTimeoutException: "
                                        + e.getExceptionCode()
                                        + " "
                                        + e.getMessage();
                aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                aUtility.throwException(ExceptionCode.ERR_RM_TIMEOUT,
                                        aErrDesc,
                                        aSwitchName,
                                        Severity.UnRecoverable);
            } 
            catch (ServiceException e) {
                String aErrDesc = "SwtichServerHelper ==> ServiceException: "
                                        + e.getExceptionCode()
                                        + " "
                                        + e.getMessage();
                aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                ExceptionBuilder.parseException(aContext,
                                                aRuleFile,
                                                "",
                                                e.getOriginalExceptionCode(),
                                                e.getMessage(),
                                                true,
                                                ExceptionBuilderRule.NO_DEFAULT,
                                                null,
                                                null,
                                                aUtility,
                                                null,
                                                null,
                                                null);
            }
            finally {
                // log REMOTE RETURN from SWITCH
                logCALLtoSWITCH(LogEventId.REMOTE_RETURN, aEvent);
            }

            // log event received
            aUtility.log(LogEventId.INFO_LEVEL_2,
                         "Received Event: " + aResult.getEventNbr());
            
            // initialize response
            SwitchTnUpdResp_t aResponse = null;        

            // evaluate event number
            switch (aResult.getEventNbr()) {
                // Received event 5011. Success.
                case SWITCHServerAccess.SWITCH_TN_UPD_RESP_NBR: 
                {
                    aResponse = (SwitchTnUpdResp_t) aResult.getTheObject();

                    // parse the response
                    TnUpdResponseHelper aResponseHelper = new TnUpdResponseHelper(aUtility, aProperties);
                    aResponseHelper.parseResponse(aResponse);
                    
                    // evaluate MSG_TYPE
                    if (aResponseHelper.getMSG_TYPE().equalsIgnoreCase("A")) {
                        // Accepted / Success
                        aUPDTRMOk = true;
                    }
                    else if (aResponseHelper.getMSG_TYPE().equalsIgnoreCase("E")) {
                        aReturnObject.setTNSourcePoolUpdateNotifier(false);
                        aReturnObject.setError(buildExceptionData(aResponseHelper.getMSG_NBR(), 
                                                                  aResponseHelper.getMSG_TEXT()));
                        aReturnObject.setNextTN(true);
        
                        aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN 
                                                             + " Error Code: " + aResponseHelper.getMSG_NBR()
                                                             + " Error Message: " + aResponseHelper.getMSG_TEXT());

                        aUPDTRMOk = false;
                    }
                    else {
                        aReturnObject.setTNSourcePoolUpdateNotifier(false);
                        aReturnObject.setError(buildExceptionData(ExceptionCode.ERR_RM_TN_NOT_PORTED, 
                                                                  "The MSG_TYPE returned by SWITCH is neither A or E"));
                        aReturnObject.setNextTN(true);
        
                        aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN 
                                                             + " Error Code: " + ExceptionCode.ERR_RM_TN_NOT_PORTED
                                                             + " Error Message: " + "The MSG_TYPE returned by SWITCH is neither A or E");

                        aUPDTRMOk = false;
                    }
                    break;
                }
                // Received event 9999. Exception event.
                case SWITCHServerAccess.EXCEPTION_NBR:   
                {
                    ExceptionResp_t aErrorResponse = (ExceptionResp_t) aResult.getTheObject();

                    // if error is a SWITCH timeout error, process next TN
                    if (aErrorResponse.OutExcp.ERR_CD == 9015) {
                        aReturnObject.setTNSourcePoolUpdateNotifier(false);
                        aReturnObject.setError(buildExceptionData(Integer.toString(aErrorResponse.OutExcp.ERR_CD), 
                                                                  aErrorResponse.OutExcp.ERR_TX));
                        aReturnObject.setNextTN(true);
            
                        aUtility.log(LogEventId.DEBUG_LEVEL_1, "TN: " + aTN 
                                                             + " Error Code: " + Integer.toString(aErrorResponse.OutExcp.ERR_CD)
                                                             + " Error Message: " + aErrorResponse.OutExcp.ERR_TX);
                        aUPDTRMOk = false;
                    }
                    else  {
                        String aErrDesc = "SwtichServerAccess ==> Exception: "
                                                    + aErrorResponse.OutExcp.ERR_TX;
                        aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                        aUtility.throwException(Integer.toString(aErrorResponse.OutExcp.ERR_CD),
                                                aErrDesc,
                                                aOriginator,
                                                Severity.UnRecoverable);
                    }
                    break;
                }
                // Unknown event.
                default: 
                {
                    String aErrDesc = "SWITCHServerHelper failure: SWITCHServerAccess: Unexpected event returned from SWITCHServerHelper:SwitchTnUpdResp_t: " 
                                                + aResult.getEventNbr();
                    aUtility.log(LogEventId.INFO_LEVEL_1, ">" + aErrDesc);

                    aUtility.throwException(ExceptionCode.ERR_RM_SWITCH_SERVER_HELPER,
                                            aErrDesc,
                                            aOriginator,
                                            Severity.UnRecoverable);

                    break;
                }
            }
        } 
        finally {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        }

        return aUPDTRMOk;
    }

    /**
     * Method: updateCVOIPOrderTable
     * @param BisContext            aContext
     * @param String                aSWITCHActionType
     * @param String                aSOACServiceOrderNumber
     * @param String                aTN
     */
    public int updateCVOIPOrderTable(
        BisContext aContext,
        String aSWITCHActionType,
        String aSOACServiceOrderNumber,
        String aTN)
        throws 
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound{

    	String aBisName = aProperties.get("BIS_NAME").toString();
    	String myMethodName = "SWITCHServer: updateCVOIPOrderTable()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        
        int aReturnValue = 0;
        try {
            CvoipOrderTable aCvoipOrderTable = new CvoipOrderTable();
            if (aSWITCHActionType.equalsIgnoreCase("I")) {
                // insert record on the table if it doesn't exist
                CvoipOrderRow[] aCvoipOrderRows = aCvoipOrderTable.retrieveByOrderTN(aSOACServiceOrderNumber, aTN, aProperties, aUtility);
                if (aCvoipOrderRows == null) {
                    aReturnValue = aCvoipOrderTable.insertByOrderTN(aSOACServiceOrderNumber, aTN, aProperties, aUtility);
                    if (aReturnValue ==  1) {
                        aUtility.log(LogEventId.INFO_LEVEL_2, "Order Number <" + aSOACServiceOrderNumber + ">"
                                                                + " TN <" + aTN + "> ADDED SUCCESSFULLY in CVOIP ORDER table.");
                    }
                }
                else {
                    aReturnValue = aCvoipOrderTable.updateByOrderTN(aSOACServiceOrderNumber, aTN, aProperties, aUtility);
                    if (aReturnValue ==  1) {
                        aUtility.log(LogEventId.INFO_LEVEL_2, "Order Number <" + aSOACServiceOrderNumber + ">"
                                                                + " TN <" + aTN + "> already ADDED in CVOIP ORDER table."
                                                                + "Updated RECORD SUCCESSFULLY.");
                    }
                }
            }
            else if (aSWITCHActionType.equalsIgnoreCase("O")) {
                // delete record on the table if it does exist
                CvoipOrderRow[] aCvoipOrderRows = aCvoipOrderTable.retrieveByOrderTN(aSOACServiceOrderNumber, aTN, aProperties, aUtility);
                if (aCvoipOrderRows != null) {
                    aReturnValue = aCvoipOrderTable.deleteByOrderTN(aSOACServiceOrderNumber, aTN, aProperties, aUtility);
                    if (aReturnValue ==  1) {
                        aUtility.log(LogEventId.INFO_LEVEL_2, "Order Number <" + aSOACServiceOrderNumber + ">"
                                                                + " TN <" + aTN + "> DELETED SUCCESSFULLY in CVOIP ORDER table.");
                    }
                }
                else {
                    aUtility.log(LogEventId.INFO_LEVEL_2, "Order Number <" + aSOACServiceOrderNumber + ">"
                                                            + " TN <" + aTN + "> already DELETED from CVOIP ORDER table.");
                }
            }
            else {
                String aErrorText = "SWITCH Action Type in CVOIP Rules Table is neither I nor O." + " - " + aSWITCHActionType;
                                            
                aUtility.throwException(ExceptionCode.ERR_RM_INVALID_DATA,
                                        aErrorText,
                                        (String) aProperties.get("BIS_NAME"),
                                        Severity.UnRecoverable);
            }
        }
        catch (SQLException e) 
        {
			// throw System Failure for missing table, field, or permission 
			aUtility.throwException(
					ExceptionCode.ERR_RM_INEXS_SQL_EXCEPTION,
					e.getMessage(),
					aBisName,
					Severity.UnRecoverable);				        	
        }
        

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aReturnValue;
        
    }

    /**
     * Method: logCALLtoSWITCH
     * @param String                aLogEventId
     * @param String                aEventName
     */
    private void logCALLtoSWITCH(
        String aLogEventId, 
        String aEventName) {

        // log remote call
        aUtility.log(aLogEventId,
                     SWITCHServerAccess.name,
                     aSwitchName,
                     aSwitchName,
                     aEventName);
    }

    /**
     * Method: disconnect
     * @param String                aEvent
     */
    private void disconnect(String aEvent)
    {
        try {
            aSWITCHServerHelper.disconnect();
        } 
        catch (ServiceException e) {
            aUtility.log(LogEventId.ERROR,
                         aEvent + "-" + "SWITCHServerHelper DISCONNECT failure: ServiceException: "
                            + e.getExceptionCode()
                            + ": "
                            + e.getMessage());
        }
    }

    /**
     * Method: connect
     * @param String                aEvent
     */
    private void connect(String aEvent)
    {
        try {
            // applData = ?
            // aService = ? 
            aSWITCHServerHelper.connect(null, null);
        } 
        catch (ServiceException e) {
            aUtility.log(LogEventId.ERROR,
                         aEvent + "-" + "SWITCHServerHelper CONNECT failure: ServiceException: "
                            + e.getExceptionCode()
                            + ": "
                            + e.getMessage());
        }
    }

    /**
     * buildExceptionData
     * @param e Exception
     * @return ExceptionData
     */
    private ExceptionData buildExceptionData(Exception e) {

        String myMethodName = "SWITCHServer: buildExceptionData()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        ExceptionData aExceptionData = null;

        if (e instanceof InvalidData) {
            aExceptionData = ((InvalidData) e).aExceptionData;
        } else if (e instanceof SystemFailure) {
            aExceptionData = ((SystemFailure) e).aExceptionData;
        } else if (e instanceof AccessDenied) {
            aExceptionData = ((AccessDenied) e).aExceptionData;
        } else if (e instanceof BusinessViolation) {
            aExceptionData = ((BusinessViolation) e).aExceptionData;
        } else if (e instanceof NotImplemented) {
            aExceptionData = ((NotImplemented) e).aExceptionData;
        } else if (e instanceof ObjectNotFound) {
            aExceptionData = ((ObjectNotFound) e).aExceptionData;
        } else if (e instanceof DataNotFound) {
            aExceptionData = ((DataNotFound) e).aExceptionData;
        } else {
            aExceptionData = new ExceptionData();

            aExceptionData.aOrigination = (StringOpt) IDLUtil.toOpt(SWITCHSERVER_Const.SWITCHServerName + SWITCHSERVER_Const.SWITCHServerVersion);
            aExceptionData.aSeverity = (SeverityOpt) IDLUtil.toOpt((SeverityOpt.class), Severity.Recoverable);

            if (e instanceof ServiceException) {
                aExceptionData.aDescription = e.getMessage();
                aExceptionData.aCode = ExceptionCode.ERR_RM_SWITCH_SERVER_DOWN;
            }
            else if (e instanceof ServiceTimeoutException) {
                aExceptionData.aDescription = e.getMessage();
                aExceptionData.aCode = ExceptionCode.ERR_RM_SWITCH_TIME_OUT;
            }
            else if (e instanceof SWITCHServerException) {
                aExceptionData.aDescription = e.getMessage();
                aExceptionData.aCode = ExceptionCode.ERR_RM_SWITCH_SERVER;
            }
            else {
                aExceptionData.aDescription = "";
                aExceptionData.aCode = "";
            }
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aExceptionData;
    }

    /**
     * buildExceptionData
     * @param e Exception
     * @return ExceptionData
     */
    private ExceptionData buildExceptionData(
        String aCode,
        String aDescription) {
        
        String myMethodName = "SWITCHServer: buildExceptionData()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        ExceptionData aExceptionData = new ExceptionData();

        aExceptionData.aOrigination = (StringOpt) IDLUtil.toOpt(SWITCHSERVER_Const.SWITCHServerName + SWITCHSERVER_Const.SWITCHServerVersion);
        aExceptionData.aSeverity = (SeverityOpt) IDLUtil.toOpt((SeverityOpt.class), Severity.Recoverable);
        aExceptionData.aCode = aCode;
        aExceptionData.aDescription = aDescription;

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return aExceptionData;
    }

    /**
     * portHIPCSTn
     * @param BisContext            aContext
     * @param String                aSWITCHActionType
     * @param String                aSOACServiceOrderNumber
     * @param String                aTN
     * @param String                aRequesterId
     * @param WsiNtuResponseHelper  aResponseHelper
     * @return boolean
     */
    private boolean portHIPCSTn(BisContext aContext,
                                String aSWITCHActionType,
                                String aSOACServiceOrderNumber,
                                String aTN,
                                String aRequesterId,
                                WsiNtuResponseHelper aResponseHelper) throws InvalidData,
                                                                     AccessDenied,
                                                                     BusinessViolation,
                                                                     SystemFailure,
                                                                     NotImplemented,
                                                                     ObjectNotFound,
                                                                     DataNotFound
    {
        String myMethodName = "SWITCHServer: portHIPCSTn()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        String aErrorCode = null;
        String aErrorMessage = null;
        String LogTNErrorMsg = "TN: " + aTN + " not PORTED in SWITCH.";

        try
        {
            String aRequesterCategory = aResponseHelper.getREQCATG();
            String aNegotiationStatus = aResponseHelper.getNEGSTATUS();
            String aSWITCHOrderNumber = aResponseHelper.getORD_3_NBR();
            String aSWITCHDueDate = aResponseHelper.getDUE_DT();

            aUtility.log(LogEventId.INFO_LEVEL_2, "Requester ID <" + aRequesterId 
                    + ">. Requester Category <" + aRequesterCategory
                    + ">.");
            
            aUtility.log(LogEventId.INFO_LEVEL_2, "SWITCH Negotiation Status <" + aNegotiationStatus 
                    + ">.");
            
            aUtility.log(LogEventId.INFO_LEVEL_2, "SWITCH Due Date <" + aSWITCHDueDate 
                    + ">. SWITCH Order Number <" + aSWITCHOrderNumber
                    + ">.");


            if ((aRequesterCategory != null && !aRequesterCategory.equalsIgnoreCase("")) 
            		&& (aNegotiationStatus != null && !aNegotiationStatus.equalsIgnoreCase("")))
            {
            	try
            	{
            		String aRequesterCategoryList = (String) aRequesterIdToRequesterCategory.get(aRequesterId);
                    StringTokenizer aStringTokens = new StringTokenizer(aRequesterCategoryList, "|");
                    while (aStringTokens.hasMoreElements())
                    {
                        String aToken = aStringTokens.nextToken().trim();
                        if (aToken.equalsIgnoreCase(aRequesterCategory))
                        {
                            // Found match, verify negotiation status
                            if (aNegotiationStatus.equalsIgnoreCase("S"))
                            {
                                // set TNL from WSINTU response, this will be used on the UPDTRM
                                aTNList = aResponseHelper.getMemb_EXID();
                                return true; // return and do UPDTRM
                            }
                            else
                            {
                                aErrorCode = ExceptionCode.ERR_RM_TN_NOT_SELECTED;
                                aErrorMessage = "The SWITCH Negotiation Status <" + aNegotiationStatus
                                                + "> is not S for Selected.";
                                aReturnObject.setTNSourcePoolUpdateNotifier(false);
                                aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                                aReturnObject.setNextTN(true);

                                aUtility.log(LogEventId.INFO_LEVEL_2, LogTNErrorMsg + " Error Code: " + aErrorCode
                                                                      + " Error Message: " + aErrorMessage);
                                return false; // return, do not perform UPDTRM
                            }
                        }
                    }
            	}
            	catch (Exception e) { 
                    aUtility.log(LogEventId.EXCEPTION, e.getMessage());
                }

                // We looped through all aRequesterCategoryList and did not find a match
                aErrorCode = ExceptionCode.ERR_RM_REQUESTER_ID_MISMATCH_SWITCH_REQUESTER_CATAGORY;
                aErrorMessage = "The Requester ID <" + aRequesterId
                                + "> does not match the SWITCH Requester Category <" + aRequesterCategory + ">.";
                aReturnObject.setTNSourcePoolUpdateNotifier(false);
                aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                aReturnObject.setNextTN(true);

                aUtility.log(LogEventId.INFO_LEVEL_2, LogTNErrorMsg + " Error Code: " + aErrorCode + " Error Message: "
                                                      + aErrorMessage);
            }
            else
            {
                // Check LIMVAL
                String aTNLimitationValue = aResponseHelper.getTN_LIM_VALU_CD();
                
                aUtility.log(LogEventId.INFO_LEVEL_2, "LIM VALUE <" + aTNLimitationValue 
                        + ">.");
                
                if (aTNLimitationValue != null && aTNLimitationValue.equalsIgnoreCase("EXP"))
                {
                    // No error recorded, tn already ported, ok to continue
                    aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN + " already PORTED in SWITCH.");
                }
                else
                {
                    aErrorCode = ExceptionCode.ERR_RM_TN_NOT_PORTED;
                    aErrorMessage = "The SWITCH TN Limitation Value <" + aTNLimitationValue + "> is not EXP.";
                    aReturnObject.setTNSourcePoolUpdateNotifier(false);
                    aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                    aReturnObject.setNextTN(true);

                    aUtility.log(LogEventId.INFO_LEVEL_2, LogTNErrorMsg + " Error Code: " + aErrorCode
                                                          + " Error Message: " + aErrorMessage);
                }
            }
        }
        catch (Exception e)
        {
            aUtility.log(LogEventId.EXCEPTION, e.getMessage());
        }
        finally
        {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        }
        return false; // return, do not perform UPDTRM
    }

    /**
     * cancelHIPCSTn
     * 
     * @param BisContext
     *            aContext
     * @param String
     *            aSWITCHActionType
     * @param String
     *            aSOACServiceOrderNumber
     * @param String
     *            aTN
     * @param WsiNtuResponseHelper
     *            aResponseHelper
     * @return boolean
     */
    private boolean cancelHIPCSTn(
        BisContext aContext,
        String aSWITCHActionType,
        String aSOACServiceOrderNumber,
        String aTN,
        WsiNtuResponseHelper aResponseHelper)
        throws
            InvalidData, 
            AccessDenied, 
            BusinessViolation, 
            SystemFailure, 
            NotImplemented, 
            ObjectNotFound, 
            DataNotFound {
        
        String myMethodName = "SWITCHServer: cancelHIPCSTn()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        String aErrorCode = null;
        String aErrorMessage = null;
        boolean isTNCancelled = true;

        // evaluate TN Limitation Value Code
        if (isTNCancelled) {
            String aTNLimitationValue = aResponseHelper.getTN_LIM_VALU_CD();
            if (aTNLimitationValue == null || !aTNLimitationValue.equalsIgnoreCase("EXP")) {
                aErrorCode = ExceptionCode.ERR_RM_TN_NOT_PORTED;
                aErrorMessage = "The SWITCH TN Limitation Value <" + aTNLimitationValue
                                    + "> is not EXP.";
                
                aReturnObject.setTNSourcePoolUpdateNotifier(false);
                aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                aReturnObject.setNextTN(true);

                aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN + " PORTING STATUS not CANCELLED in SWITCH."
                                                     + " Error Code: " + aErrorCode
                                                     + " Error Message: " + aErrorMessage);
                isTNCancelled = false;
            }
            aUtility.log(LogEventId.INFO_LEVEL_2, "SWITCH TN Limitation Value <" + aTNLimitationValue 
                                                    + ">.");
        }

        // evaluate TN Limitation Type Code
        if (isTNCancelled) {
            String aTNLimitationType = aResponseHelper.getTN_LIM_TYPE_CD();
            if (!aTNLimitationType.equalsIgnoreCase("RST")) {
                aErrorCode = ExceptionCode.ERR_RM_TN_NOT_PORTED;
                aErrorMessage = "The SWITCH TN Limitation Type <" + aTNLimitationType
                                    + "> is not RST.";

                aReturnObject.setTNSourcePoolUpdateNotifier(false);
                aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                aReturnObject.setNextTN(true);

                aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN + " PORTING STATUS not CANCELLED in SWITCH."
                                                     + " Error Code: " + aErrorCode
                                                     + " Error Message: " + aErrorMessage);
                isTNCancelled = false;
            }
            aUtility.log(LogEventId.INFO_LEVEL_2, "SWITCH TN Limitation Type <" + aTNLimitationType 
                                                    + ">.");
        }

        // evaluate TN Selectability Indicator
        if (isTNCancelled) {
            String aTNSelectIndicator = aResponseHelper.getTN_SELT_IND();
            if (!aTNSelectIndicator.equalsIgnoreCase("N")) {
                aErrorCode = ExceptionCode.ERR_RM_TN_NOT_PORTED;
                aErrorMessage = "The SWITCH TN Selectability Indicator <" + aTNSelectIndicator
                                    + "> is not N.";
                
                aReturnObject.setTNSourcePoolUpdateNotifier(false);
                aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                aReturnObject.setNextTN(true);

                aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN + " PORTING STATUS not CANCELLED in SWITCH."
                                                     + " Error Code: " + aErrorCode
                                                     + " Error Message: " + aErrorMessage);
                isTNCancelled = false;
            }
            aUtility.log(LogEventId.INFO_LEVEL_2, "SWITCH TN Selectability Indicator <" + aTNSelectIndicator 
                                                    + ">.");
        }       
    
        // evaluate Order Number and Due Date
        // if Order Number and Due Date are NOT populated, and Order Numbers matched, TN was already removed in SWITCH, no need to call UPDTRM
        // if Order Number and Due Date are populated, TN not yet removed in SWITCH, call UPDTRM
        if (isTNCancelled) {
            String aSWITCHOrderNumber = aResponseHelper.getORD_3_NBR();
            String aSWITCHDueDate = aResponseHelper.getDUE_DT();
            if (aSWITCHOrderNumber.equalsIgnoreCase("") && aSWITCHDueDate.equalsIgnoreCase("")) {
                // OK - Delete the HIPCS TN in the CVOIP order table
                updateCVOIPOrderTable(aContext,
                                      aSWITCHActionType,
                                      aSOACServiceOrderNumber,
                                      aTN);
            
                aReturnObject.setTNSourcePoolUpdateNotifier(true);
                aReturnObject.setNextTN(true);

                aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN + " PORTING STATUS ALREADY CANCELLED in SWITCH."
                                                     + " SWITCH Due Date <" + aSWITCHDueDate
                                                     + ">. SWITCH Order Number <" + aSWITCHOrderNumber
                                                     + ">. SOAC Order Number <" + aSOACServiceOrderNumber 
                                                     + ">.");


            }
            else if (!aSWITCHOrderNumber.equalsIgnoreCase("") && !aSWITCHDueDate.equalsIgnoreCase("")) {
                // compare SOAC Order Number with SWITCH Order Number
                if (aSOACServiceOrderNumber.equalsIgnoreCase(aSWITCHOrderNumber)) {
                    // OK
                    aTNList = aResponseHelper.getMemb_EXID();
                }
                else {
                    aErrorCode = ExceptionCode.ERR_RM_TN_NOT_PORTED;
                    aErrorMessage = "The SWITCH Order Number <" + aSWITCHOrderNumber 
                                    + "> does not match with the SOAC Order Number <" + aSOACServiceOrderNumber 
                                    + ">.";

                    
                    aReturnObject.setTNSourcePoolUpdateNotifier(false);
                    aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                    aReturnObject.setNextTN(true);
    
                    aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN + " PORTING STATUS not CANCELLED in SWITCH."
                                                         + " Error Code: " + aErrorCode
                                                         + " Error Message: " + aErrorMessage);
                    isTNCancelled = false;
                }
            }
            else {
                aErrorCode = ExceptionCode.ERR_RM_TN_NOT_PORTED;
                aErrorMessage = "Either the SWITCH Due Date <" + aSWITCHDueDate 
                                + "> or SWITCH Order Number <" + aSWITCHOrderNumber 
                                + "> is NULL.";

                aReturnObject.setTNSourcePoolUpdateNotifier(false);
                aReturnObject.setError(buildExceptionData(aErrorCode, aErrorMessage));
                aReturnObject.setNextTN(true);

                aUtility.log(LogEventId.INFO_LEVEL_2, "TN: " + aTN + " PORTING STATUS not CANCELLED in SWITCH."
                                                     + " Error Code: " + aErrorCode
                                                     + " Error Message: " + aErrorMessage);
                isTNCancelled = false;
            }
            aUtility.log(LogEventId.INFO_LEVEL_2, "SWITCH Due Date <" + aSWITCHDueDate 
                                                    + ">. SWITCH Order Number <" + aSWITCHOrderNumber
                                                    + ">.");

        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return isTNCancelled;
    }

    /**
     * loadRequesterIdToRequesterCategory
     */
    private void loadRequesterIdToRequesterCategory()
        throws
            AccessDenied,
            BusinessViolation,
            InvalidData,
            NotImplemented,
            ObjectNotFound,
            SystemFailure,
            DataNotFound {

        String myMethodName = "SWITCHServer: loadRequesterIdToRequesterCategory()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        // The file contains data in the form: RequesterID=RequesterCategory
        // For example: OMS=OMSCVOIP
        String aRequesterIdToRequesterCategoryFile = (String) aProperties.get("SWITCH_REQUESTER_ID_TO_REQUESTER_CATEGORY_MAP_FILE");

        if (aRequesterIdToRequesterCategoryFile == null || aRequesterIdToRequesterCategoryFile.length() < 1)
            aUtility.throwException(ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
                                    "SWITCH_REQUESTER_ID_TO_REQUESTER_CATEGORY_MAP_FILE property not found/not set",
                                    (String) aProperties.get("BIS_NAME"),
                                    Severity.UnRecoverable);

        aUtility.log(LogEventId.DEBUG_LEVEL_2, "Loading Requester ID to Requester Category mappings from: <"
                                                + aRequesterIdToRequesterCategoryFile
                                                + ">");

        try {
            PropertiesFileLoader.read(aRequesterIdToRequesterCategory,
                                      aRequesterIdToRequesterCategoryFile,
                                      aUtility);
                                      
            aUtility.log(LogEventId.DEBUG_LEVEL_2, "Loaded " 
                                                   + aRequesterIdToRequesterCategory.size()
                                                   + " Requester ID to Requester Category mappings.");
        } 
        catch (FileNotFoundException e) {
            aUtility.throwException(ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
                                    "FileNotFoundException: " + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME"),
                                    Severity.UnRecoverable);
        } 
        catch (IOException e) {
            aUtility.throwException(ExceptionCode.ERR_RM_PROPERTIES_FILE_NOT_FOUND,
                                    "IOException: " + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME"),
                                    Severity.UnRecoverable,
                                    e);
        }

        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
    }
}
