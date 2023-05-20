//$Id: ValidateFacility.java,v 1.6 2007/11/13 17:08:37 rd2842 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 07/23/2007  Rene Duka             Creation.
//# 11/13/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.

package com.sbc.eia.bis.facades.rm.transactions.ValidateFacility;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utility.soaphelpers.SoapParserHelper;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.client.ClientService;
import com.sbc.eia.bis.embus.service.oms.OMSService;
import com.sbc.eia.bis.embus.service.oms.access.OMSHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.bis.validator.Validator;
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
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.ValidateFacilityReturn;
import com.sbc.eia.idl.rm.RmFacadePackage._sendTNAssignmentOrderRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequest;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequestBISMsg;
import com.sbc.eia.idl.rm.RmFacadePackage._validateFacilityRequestMsg;
import com.sbc.eia.idl.rm.bishelpers.ValidateFacilityReturnBisHelper;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.ShortOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.vicunalite.api.MMarshalObject;
import com.sbc.vicunalite.vaxb.VAXBDocumentWriter;
import com.sbc.vicunalite.vaxb.VAXBWriter;

/**
 * Class      : ValidateFacility
 * Extends    : TranBase
 * Description: Facade for validateFacility.
 *              - used by vF.
 */
public class ValidateFacility extends TranBase 
{

    private Utility aUtility = null;
    private Hashtable aProperties = null;
    private Validator validator = null;
    
    /**
     * Constructor: ValidateFacility
     * 
     * @author Rene Duka
     */
    public ValidateFacility() 
    {
        super();
    }

    /**
     * Constructor: ValidateFacility
     * 
     * @param Hashtable properties
     * 
     * @author Rene Duka
     */
    public ValidateFacility(Hashtable param) 
    {
        super(param);
        aUtility = this;
        aProperties = getPROPERTIES();
    }

    /**
     * Method: execute => validateFacility
     *
     *  Interface between OMS/AMSS/FIRST/MobilityCSI and RM to retrieve and analyze facility 
     *      availability information applicable to LS ordering.
     *
     * @param BisContext            aContext
     * @param Location              aServiceLocation
     * @param StringOpt             aRelatedCircuitID
     * @param StringOpt             aWorkingTelephoneNumber        
     * @param ShortOpt              aMaxPairsToAnalyze
     * @param StringOpt             aSOACServiceOrderNumber
     * @param StringOpt             aSOACServiceOrderCorrectionSuffix
     * @param EiaDateOpt            aUverseOrderDueDate
     * @param ObjectPropertySeqOpt  aObjectProperties
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * @return ValidateFacilityReturn
     */
    public ValidateFacilityReturn execute(
        BisContext aContext,
        Location aServiceLocation,
        StringOpt aRelatedCircuitID,
        StringOpt aWorkingTelephoneNumber,
        ShortOpt aMaxPairsToAnalyze,
        StringOpt aSOACServiceOrderNumber,
        StringOpt aSOACServiceOrderCorrectionSuffix,
        EiaDateOpt aUverseOrderDueDate,
        ObjectPropertySeqOpt aObjectProperties,
        Logger aLogger)
        throws 
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented 
    {
        String aMethodName = "ValidateFacility: execute()";
        myLogger = aLogger;
        callerContext = aContext;
      
        log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

        ObjectPropertyManager aContextOPM = new ObjectPropertyManager(aContext.aProperties);

        // log BisContext
        try 
        {
            log(LOG_INPUT_DATA, "aContext=<" + (new BisContextBisHelper(aContext)).toString() + ">");
        }
        catch (Exception e) 
        {
            log(LOG_INPUT_DATA, "aContext<null>");
        }

        ValidateFacilityReturn aValidateFacilityReturn = null;
        try 
        {
            // validate BisContext
            ValidateHelper.validateBisContext(aUtility,
                                              aContextOPM,
                                              aProperties.get("BIS_NAME").toString());

            // is client authorized
            validateClient(aContextOPM,
                           null, // group_id
                           ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
                           ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);
                         
            // validate input         
            if (validator == null) 
            {
                validator = new Validator(aUtility,
                                          aProperties,
                                          (String) aProperties.get("VF_VALIDATOR_VARIABLE_MAP_FILE"));
            }                           
            
            validator.validate(aContext, new ValidateFacilityValidation(aContext,
                                                                        aServiceLocation,
                                                                        aRelatedCircuitID,
                                                                        aWorkingTelephoneNumber,
                                                                        aMaxPairsToAnalyze,
                                                                        aSOACServiceOrderNumber,
                                                                        aSOACServiceOrderCorrectionSuffix,
                                                                        aUverseOrderDueDate,
                                                                        aObjectProperties), true);

            // build request XML
            String aRequestXML = buildRequestXML(aContext,
                                                 aServiceLocation,
                                                 aRelatedCircuitID,
                                                 aWorkingTelephoneNumber,
                                                 aMaxPairsToAnalyze,
                                                 aSOACServiceOrderNumber,
                                                 aSOACServiceOrderCorrectionSuffix,
                                                 aUverseOrderDueDate,
                                                 aObjectProperties);

            
            // send request XML to pVFN
            sendRequest(aContext,
                        aRequestXML);

            // build acknowledgement response object
            aValidateFacilityReturn = new ValidateFacilityReturn(aContext);
          
            // log output
            logOutput(aValidateFacilityReturn);
        }
        finally  
        {
            log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
        }
        return aValidateFacilityReturn;
    }

    /**
     * Builds the request XML.
     *
     * @param BisContext aContext
     * @param Location   aServiceLocation
     * @param StringOpt  aRelatedCircuitID
     * @param StringOpt  aWorkingTelephoneNumber
     * @param ShortOpt   aMaxPairsToAnalyze
     * @param StringOpt  aSOACServiceOrderNumber
     * @param StringOpt  aSOACServiceOrderNumberSuffix
     * @param EiaDateOpt aUverseOrderDueDate
     * @param ObjectPropertySeqOpt aObjectProperties
     * @return String
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * 
     * @author Rene Duka
     */
    private String buildRequestXML(
        BisContext aContext,
        Location aServiceLocation,
        StringOpt aRelatedCircuitID,
        StringOpt aWorkingTelephoneNumber,
        ShortOpt aMaxPairsToAnalyze,
        StringOpt aSOACServiceOrderNumber,
        StringOpt aSOACServiceOrderCorrectionSuffix,
        EiaDateOpt aUverseOrderDueDate,
        ObjectPropertySeqOpt aObjectProperties)
        throws 
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented 
    {
        String aMethodName = "ValidateFacility: buildRequestXML()";
        log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

        // send request to MDB
        String aRequestXML = null;
        try 
        {
            // build the request message to be sent to pVFN transaction
            _validateFacilityRequest aRequest = new _validateFacilityRequest(aContext,
                                                                             aServiceLocation,
                                                                             aRelatedCircuitID,
                                                                             aWorkingTelephoneNumber,
                                                                             aMaxPairsToAnalyze,
                                                                             aSOACServiceOrderNumber,
                                                                             aSOACServiceOrderCorrectionSuffix,
                                                                             aUverseOrderDueDate,
                                                                             aObjectProperties);

            // format request XML file for vF
            MMarshalObject aMarshalObject = new _validateFacilityRequestMsg(aRequest);
            aRequestXML = ValidateFacilityConstants.BEGIN_VAXB_TAG
                            + VAXBWriter.encode(aMarshalObject).trim()
                            + ValidateFacilityConstants.END_VAXB_TAG;

            log(LogEventId.INFO_LEVEL_1, "XML Message: [" + aRequestXML + "]");            
        }
        catch (IOException ioe) 
        {
            aUtility.log(LogEventId.ERROR, "Conversion to XML from ValidateFacility object failure: " + ioe.getMessage());
            aUtility.throwException(ExceptionCode.ERR_RM_IO_EXCEPTION,
                                    "XML conversion produced an IOException " + ioe.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.ERROR, ">" + "Found error in buildRequestXML: [" + e.getMessage() + "]");
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    "Exception message: " + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
        return aRequestXML;
    }
    
    /**
     * Sends the request XML.
     *
     * @param BisContext aContext
     * @param String     aRequestXML
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * 
     * @author Rene Duka
     */
    private void sendRequest(
        BisContext aContext,
        String aRequestXML)
        throws 
            BusinessViolation,
            ObjectNotFound,
            InvalidData,
            AccessDenied,
            SystemFailure,
            DataNotFound,
            NotImplemented 
    {
        String aMethodName = "ValidateFacility: sendRequest()";
        log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

        ClientService aService = null; 
        try 
        {
            if (aService == null) 
            {
                aService = new ClientService(aProperties, aUtility);
            }
            //aService.setClient(aContext);
            aService.setClient("RM");
            // log remote call
            aService.logREMOTE_CALL();
            // add the JMS property idetifier for vF
            Properties aMessageTags = new Properties();
            aMessageTags.put(ValidateFacilityConstants.JMS_propertyName, 
                             ValidateFacilityConstants.JMS_propertyValue);
            // send message
            aService.publishMessage(aRequestXML, aMessageTags);
            // log remote return
            aService.logREMOTE_RETURN();            
        }
        catch (ServiceException se) 
        {
            aUtility.log(LogEventId.ERROR, ">" + "ServiceException " + se.getMessage());
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    "Exception message: " + se.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        catch (Exception e) 
        {
            aUtility.log(LogEventId.ERROR, ">" + "Found error in sendRequest: [" + e.getMessage() + "]");
            aUtility.throwException(ExceptionCode.ERR_RM_SYSTEM_FAILURE,
                                    "Exception message: " + e.getMessage(),
                                    (String) aProperties.get("BIS_NAME").toString(),
                                    Severity.UnRecoverable);
        }
        finally 
        {
            log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
        }
    }

    /**
     * Logs the output.
     *
     * @param ValidateFacilityReturn aReturnObject
     * @exception InvalidData      : An input parameter contained invalid data.
     * @exception AccessDenied     : Access to the specified domain object or information is not allowed.
     * @exception BusinessViolation: The attempted action violates a business rule.
     * @exception SystemFailure    : The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity).
     * @exception NotImplemented   : The method has not been implemented.
     * @exception ObjectNotFound   : The desired domain object could not be found.
     * @exception DataNotFound     : No data found.
     * 
     * @author Rene Duka
     */
    private void logOutput(ValidateFacilityReturn aReturnObject)
        throws
            InvalidData,
            AccessDenied,
            BusinessViolation,
            SystemFailure,
            NotImplemented,
            ObjectNotFound,
            DataNotFound 
    {
        String aMethodName = "ValidateFacility: logOutput()";
        log(LOG_DEBUG_LEVEL_1, ">" + aMethodName);

        // aReturnObject
        try 
        {
            log(LOG_OUTPUT_DATA, "validateFacilityReturn=<" + (new ValidateFacilityReturnBisHelper(aReturnObject)).toString() + ">");
        }
        catch (Exception e) 
        {
            log(LOG_OUTPUT_DATA, "aReturnObject<null>");
        }
        finally 
        {
            log(LOG_DEBUG_LEVEL_1, "<" + aMethodName);
        }
    }
}
