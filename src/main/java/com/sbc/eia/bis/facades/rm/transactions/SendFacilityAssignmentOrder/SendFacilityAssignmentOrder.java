// $Id: SendFacilityAssignmentOrder.java,v 1.26 2009/02/25 00:03:32 ra0331 Exp $
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
//# 5/24/2006	Doris Sunga			  The original program was copied from com.sbc.eia.bis.facades.rm.transactions.SenfF1F2Order.java	
//# 5/25/2006	Doris Sunga			  renamed class to SendFacilityAssignemntOrder and
//# 								  applied LS3 requirements
//# 08/25/2006 Jon Costa			  PR18355774 for SOAC IA change to ERR, TOPListener cache 	
//# 05/09/2007  Doris Sunga			  CR 13440: validate aProperties.aDSLDisconnectTN when populated
//# 05/21/2007	Doris Sunga			  CR 13440, DR 66791 - Discontinue process when failed in aDSLDisconnectTelephoneNumber
//#												DR 66789 - for blank aDSLDisconnectTN, no DSL Disconnect rmk on remark section
//# 05/24/2007	Doris Sunga			  DR 66789 - sFAO - getting "RM-SystemFailure-99999" when testing for missing DSL Disconnect TN
//# 02/04/2008  Ott Phannavong        LS7: Changes in in execute(..) to accomodate SFAO2
//###################################################################################

package com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.common.utilities.BisDateUtil;
import com.sbc.eia.bis.rm.utilities.OptHelper;
import com.sbc.eia.bis.rm.utilities.ValidateHelper;
import com.sbc.eia.bis.validator.Validator;
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
import com.sbc.eia.idl.lim_types.bishelpers.LocationBisHelper;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn;
import com.sbc.eia.idl.rm.bishelpers.SendFacilityAssignmentOrderReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.BooleanOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;

public class SendFacilityAssignmentOrder extends TranBase
{

   protected Utility aUtility = null;
   protected Hashtable myProperties = null;
   protected Validator validator = null;
   private SOAC cacheSOAC = null;

   /**
    * Constructor.
    */
   public SendFacilityAssignmentOrder()
   {
      super();
   }

   /**
    * for SendFacilityAssignmentOrder.
    * 
    * @param param
    */
   public SendFacilityAssignmentOrder(Hashtable param)
   {
      super(param);
      aUtility = this;
      myProperties = getPROPERTIES();
   }

   /**
    * Method execute.
    * 
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
    * @param aLogger
    * @return SendFacilityAssignmentOrderReturn
    * @throws BusinessViolation
    * @throws ObjectNotFound
    * @throws InvalidData
    * @throws AccessDenied
    * @throws SystemFailure
    * @throws DataNotFound
    * @throws NotImplemented
    */
   public SendFacilityAssignmentOrderReturn execute(BisContext aContext,
         String aSOACServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
         String aOrderActionId, String aOrderNumber, String aOrderActionType,
         BooleanOpt aCompletionIndicator, StringOpt aSubActionType,
         String aCircuitId, Location aServiceLocation,
         EiaDate aOriginalDueDate, EiaDateOpt aSubsequentDueDate,
         EiaDate aApplicationDate, StringOpt aTDMTelphoneNumber,
         StringOpt aRelatedServiceOrderNumber,
         BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
         BooleanOpt aResendFlag, ObjectPropertySeqOpt aProperties,
         com.sbc.eia.bis.RmNam.utilities.Logger aLogger) throws InvalidData,
         AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
         ObjectNotFound, DataNotFound
   {
      String myMethodNameName = "SendFacilityAssignmentOrder.execute()";
      myLogger = aLogger;
      log(LOG_DEBUG_LEVEL_1, ">" + myMethodNameName);
      callerContext = aContext;
      SendFacilityAssignmentOrderReturn sfaoReturn = null;
      try
      {
         String propertyValue = null;
         String aBisName = myProperties.get("BIS_NAME").toString();
         ObjectPropertyManager aContextOPM = new ObjectPropertyManager(
               aContext.aProperties);

         // Log BisContext
         try
         {
            log(LOG_INPUT_DATA, "aContext=<"
                  + (new BisContextBisHelper(aContext)).toString() + ">");
         }
         catch(Exception e)
         {
            log(LOG_INPUT_DATA, "aContext=<null>");
         }

         // Validate BisContext
         ValidateHelper.validateBisContext(aUtility, aContextOPM, aBisName);

         //validate JMS_CORRELATION_ID
         if((propertyValue = aContextOPM
               .getValue(BisContextProperty.JMS_CORRELATION_ID)) == null
               || propertyValue.trim().length() < 1)
            aUtility.throwException(
                  ExceptionCode.ERR_RM_MISSING_JMS_CORRELATION_ID,
                  formatInvalidData(BisContext.class, "aContext.aProperties["
                        + BisContextProperty.JMS_CORRELATION_ID + "]"),
                  aBisName, Severity.UnRecoverable);

         //validate EMBUS_MESSAGE_TAG
         if((propertyValue = aContextOPM
               .getValue(BisContextProperty.EMBUS_MESSAGE_TAG)) == null
               || propertyValue.trim().length() < 1)
            aUtility.throwException(
                  ExceptionCode.ERR_RM_MISSING_EMBUS_MESSAGE_TAG,
                  formatInvalidData(BisContext.class, "aContext.aProperties["
                        + BisContextProperty.EMBUS_MESSAGE_TAG + "]"),
                  aBisName, Severity.UnRecoverable);

         validateClient(aContextOPM, (String) null,
               ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
               ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);

         logInput(aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
               aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
               aCompletionIndicator, aSubActionType, aCircuitId,
               aServiceLocation, aOriginalDueDate, aSubsequentDueDate,
               aApplicationDate, aTDMTelphoneNumber,
               aRelatedServiceOrderNumber, aLineShareDisconnectFlag,
               aClassOfService, aResendFlag, aProperties);

         validateInputWithValidator(aContext, aSOACServiceOrderNumber,
               aSOACServiceOrderCorrectionSuffix, aNetworkType, aOrderActionId,
               aOrderNumber, aOrderActionType, aCompletionIndicator,
               aSubActionType, aCircuitId, aServiceLocation, aOriginalDueDate,
               aSubsequentDueDate, aApplicationDate, aTDMTelphoneNumber,
               aRelatedServiceOrderNumber, aLineShareDisconnectFlag,
               aClassOfService, aResendFlag, aProperties);

         /*
          * There is no return value since SOAC will not return a response, only exception handling:
          * Exceptions: Persistence (eliminated for LS R3) FCIF parser TOPListener ServiceException
          * SystemFailure NOTE: Asynchronous response to arrive on MDB.
          */
         //LS7
         cacheSOAC = makeSOAC(cacheSOAC);

         cacheSOAC.sendFacilityAssignmentOrder(aContext,
               aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
               aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
               aCompletionIndicator, aSubActionType, aCircuitId,
               aServiceLocation, aOriginalDueDate, aSubsequentDueDate,
               aApplicationDate, aTDMTelphoneNumber,
               aRelatedServiceOrderNumber, aLineShareDisconnectFlag,
               aClassOfService, aResendFlag, aProperties);

         StringOpt aSOACServiceOrderNumberOpt = IDLUtil
               .toOpt(aSOACServiceOrderNumber);
         StringOpt aSOACServiceOrderCorrectionSuffixOpt = IDLUtil
               .toOpt(aSOACServiceOrderCorrectionSuffix);
         sfaoReturn = new SendFacilityAssignmentOrderReturn(aContext,
               aSOACServiceOrderNumberOpt, aSOACServiceOrderCorrectionSuffixOpt);
         logOutput(sfaoReturn);
      }
      finally
      {
         log(LOG_INFO_LEVEL_1, "<" + myMethodNameName);
      }

      return sfaoReturn;
   }

   /**
    * Method logInput.
    * 
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
    */
   protected void logInput(String aSOACServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
         String aOrderActionId, String aOrderNumber, String aOrderActionType,
         BooleanOpt aCompletionIndicator, StringOpt aSubActionType,
         String aCircuitId, Location aServiceLocation,
         EiaDate aOriginalDueDate, EiaDateOpt aSubsequentDueDate,
         EiaDate aApplicationDate, StringOpt aTDMTelphoneNumber,
         StringOpt aRelatedServiceOrderNumber,
         BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
         BooleanOpt aResendFlag, ObjectPropertySeqOpt aProperties)
   {
      String myMethodNameName = "SendFacilityAssignmentOrder.logInput()";
      log(LOG_INFO_LEVEL_1, ">" + myMethodNameName);

      log(LOG_INPUT_DATA, "aSOACServiceOrderNumber<" + aSOACServiceOrderNumber
            + ">");
      log(LOG_INPUT_DATA, "aSOACServiceOrderCorrectionSuffix<"
            + aSOACServiceOrderCorrectionSuffix + ">");
      log(LOG_INPUT_DATA, "aNetworkType<" + aNetworkType + ">");
      log(LOG_INPUT_DATA, "aOrderActionId<" + aOrderActionId + ">");
      log(LOG_INPUT_DATA, "aOrderNumber<" + aOrderNumber + ">");
      log(LOG_INPUT_DATA, "aOrderActionType<" + aOrderActionType + ">");

      try
      {
         log(LOG_INPUT_DATA, "aCompletionIndicator<"
               + (new BooleanOptBisHelper(aCompletionIndicator)).toString()
               + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aCompletionIndicator<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aSubActionType<" + aSubActionType.theValue()
               + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aSubActionType<null>");
      }

      log(LOG_INPUT_DATA, "aCircuitId<" + aCircuitId + ">");

      try
      {
         log(LOG_INPUT_DATA, "aServiceLocation<"
               + (new LocationBisHelper(aServiceLocation)).toString() + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aServiceLocation<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aOriginalDueDate<"
               + (new EiaDateBisHelper(aOriginalDueDate)).toString() + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aOriginalDueDate<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aSubsequentDueDate<"
               + (new EiaDateOptBisHelper(aSubsequentDueDate)).toString() + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aSubsequentDueDate<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aApplicationDate<"
               + (new EiaDateBisHelper(aApplicationDate)).toString() + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aApplicationDate<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aTDMTelphoneNumber<"
               + aTDMTelphoneNumber.theValue() + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aTDMTelphoneNumber<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aRelatedServiceOrderNumber<"
               + aRelatedServiceOrderNumber.theValue() + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aRelatedServiceOrderNumber<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aLineShareDisconnectFlag<"
               + (new BooleanOptBisHelper(aLineShareDisconnectFlag)).toString()
               + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aLineShareDisconnectFlag<null>");
      }

      log(LOG_INPUT_DATA, "aClassOfService<" + aClassOfService + ">");

      try
      {
         log(LOG_INPUT_DATA, "aResendFlag<"
               + (new BooleanOptBisHelper(aResendFlag)).toString() + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aResendFlag<null>");
      }

      try
      {
         log(LOG_INPUT_DATA, "aProperties<"
               + (new ObjectPropertySeqOptBisHelper(aProperties)).toString()
               + ">");
      }
      catch(Exception e)
      {
         log(LOG_INPUT_DATA, "aProperties<null>");
      }

      log(LOG_INFO_LEVEL_1, "<" + myMethodNameName);
   }
   /**
    * Method validateInputWithValidator.
    * 
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
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */
   protected void validateInputWithValidator(BisContext aContext,
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
         throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
         NotImplemented, ObjectNotFound, DataNotFound
   {
      String myMethodNameName = "SendFacilityAssignmentOrder: validateInputWithValidator()";
      log(LOG_INFO_LEVEL_1, ">" + myMethodNameName);

      String aBisName = myProperties.get("BIS_NAME").toString();

      //Validate Input using Validator framework
      validator();

      SendFacilityAssignmentOrderValidation validation = makeSendFacilityAssignmentOrderValidation(
            aContext, aSOACServiceOrderNumber,
            aSOACServiceOrderCorrectionSuffix, aNetworkType, aOrderActionId,
            aOrderNumber, aOrderActionType, aCompletionIndicator,
            aSubActionType, aCircuitId, aServiceLocation, aOriginalDueDate,
            aSubsequentDueDate, aApplicationDate, aTDMTelphoneNumber,
            aRelatedServiceOrderNumber, aLineShareDisconnectFlag,
            aClassOfService, aResendFlag);

      validator.validate(aContext, validation);

      if(aSOACServiceOrderNumber.trim().length() != 9
            || !isAlphNum(aSOACServiceOrderNumber))
      {
         aUtility.throwException(
               ExceptionCode.ERR_RM_MISSING_SERVICE_ORDER_NUMBER,
               "Invalid required data: aSOACServiceOrderNumber", aBisName,
               Severity.UnRecoverable);
      }

      if(aSOACServiceOrderCorrectionSuffix.length() > 1
            || !Character.isLetter(aSOACServiceOrderCorrectionSuffix
                  .toUpperCase().charAt(0)))
      {
         aUtility
               .throwException(
                     ExceptionCode.ERR_RM_MISSING_SOAC_SERVICE_ORDER_CORRECTION_SUFFIX,
                     "Invalid required data: aSOACServiceOrderCorrectionSuffix, must be single alpha character",
                     aBisName, Severity.UnRecoverable);
      }

      if(aOrderActionId.length() > 10)
      {
         aUtility
               .throwException(
                     ExceptionCode.ERR_RM_MISSING_ORDER_ACTION_ID,
                     "Invalid required data: aOrderActionId, must be 10 characters or less",
                     aBisName, Severity.UnRecoverable);
      }

      if(aOrderNumber.length() > 10)
      {
         aUtility
               .throwException(
                     ExceptionCode.ERR_RM_MISSING_ORDER_NUMBER,
                     "Invalid required data: aOrderNumber, must be 10 characters or less",
                     aBisName, Severity.UnRecoverable);
      }

      // Empty or null is a valid value since it is optional, but verify if
      // populated.
      if(OptHelper.isStringOptEmpty(aSubActionType) == false)
      {
         if(!aSubActionType.theValue().equalsIgnoreCase(
               SubActionTypeValues.SUB_ACTION_AMEND)
               && !aSubActionType.theValue().equalsIgnoreCase(
                     SubActionTypeValues.SUB_ACTION_CANCEL))
         {
            aUtility.throwException(
                  ExceptionCode.ERR_RM_INVALID_ORDER_SUB_ACTION_TYPE,
                  "INVALID " + "aSubActionType, valid values are: "
                        + SubActionTypeValues.SUB_ACTION_AMEND + " or "
                        + SubActionTypeValues.SUB_ACTION_CANCEL + " or 'null'",
                  aBisName, Severity.UnRecoverable);
         }
      }

      ValidateHelper.validateLocation(aUtility, aServiceLocation, aBisName);

      try
      {
         // Check for House Number
         if(aServiceLocation.aProviderLocationProperties[0].aServiceAddress
               .theValue().aFieldedAddress().aHouseNumber.theValue() == null
               || aServiceLocation.aProviderLocationProperties[0].aServiceAddress
                     .theValue().aFieldedAddress().aHouseNumber.theValue()
                     .trim().equalsIgnoreCase(""))
            throw new Exception();
      }
      catch(Exception e)
      {
         aUtility.throwException(
               ExceptionCode.ERR_RM_MISSING_SERVICE_LOCATION_HOUSE_NUMBER,
               "Missing required data: Service Location House Number.",
               aBisName, Severity.UnRecoverable);
      }

      try
      {
         // Check for Street Name
         if(aServiceLocation.aProviderLocationProperties[0].aServiceAddress
               .theValue().aFieldedAddress().aStreetName.theValue() == null
               || aServiceLocation.aProviderLocationProperties[0].aServiceAddress
                     .theValue().aFieldedAddress().aStreetName.theValue()
                     .trim().equalsIgnoreCase(""))
            throw new Exception();
      }
      catch(Exception e)
      {
         aUtility.throwException(
               ExceptionCode.ERR_RM_MISSING_SERVICE_LOCATION_STREET_NAME,
               "Missing required data: Service Location Street Name.",
               aBisName, Severity.UnRecoverable);
      }

      try
      {
         if(aServiceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx
               .theValue() == null
               || aServiceLocation.aProviderLocationProperties[0].aPrimaryNpaNxx
                     .theValue().trim().equalsIgnoreCase(""))
            throw new Exception();
      }
      catch(Exception e)
      {
         aUtility.throwException(ExceptionCode.ERR_RM_MISSING_NPANXX,
               "Missing required data: NPANXX", aBisName,
               Severity.UnRecoverable);
      }

      ValidateHelper
            .validateSbcServingOfficeWireCenter(
                  aUtility,
                  aServiceLocation.aProviderLocationProperties[0].aSbcServingOfficeWirecenter,
                  aBisName);

      // Convert to standard date format
      try
      {
         aOriginalDueDate = BisDateUtil.toEiaDate(aOriginalDueDate);
         verifyDate(aOriginalDueDate, "aOriginalDueDate", aBisName);
      }
      catch(InvalidData id)
      {
         throw id;
      }
      catch(Exception e)
      {
      } // Ignore, validateDueDate() will throw proper exception.

      try
      {
         EiaDate aDate = aSubsequentDueDate.theValue();
         aDate = BisDateUtil.toEiaDate(aDate);
         aSubsequentDueDate.theValue(aDate);
         verifyDate(aSubsequentDueDate.theValue(), "aSubsequentDueDate",
               aBisName);
      }
      catch(InvalidData id)
      {
         throw id;
      }
      catch(Exception e)
      {
      } // Ignore, not a required field, exception only if populated and date
      // is invalid.

      // Convert to standard date format
      try
      {
         aApplicationDate = BisDateUtil.toEiaDate(aApplicationDate);
         verifyDate(aApplicationDate, "aApplicationDate", aBisName);
      }
      catch(InvalidData id)
      {
         throw id;
      }
      catch(Exception e)
      {
      } // Ignore, Validator() will throw proper exception.

      // CR 13440 Empty or null is a valid value since it is optional, but
      // verify if populated.
      // must be 10 numeric digits when populated
      String aDSLDisconnectTN = null;
      try
      {
         if(aProperties != null && aProperties.theValue().length > 0)
         {
            aDSLDisconnectTN = new ObjectPropertyManager(aProperties.theValue())
                  .getValue("aDSLDisconnectTelephoneNumber").trim();
         }

      }
      catch(Exception ex)
      {
      } // do nothing

      if(aDSLDisconnectTN != null && aDSLDisconnectTN.trim().length() > 0)
      {
         if((aDSLDisconnectTN.trim().length() != 10)
               || !isNumeric(aDSLDisconnectTN))
         {
            aUtility.throwException(ExceptionCode.ERR_RM_NUMERIC_TN,
                  "Invalid data: aDSLDisconnectTN [" + aDSLDisconnectTN
                        + "], must be 10 numeric digits.", aBisName,
                  Severity.UnRecoverable);
         }
      }

      log(LOG_INFO_LEVEL_1, "<" + myMethodNameName);
   }

   /**
    * Method validateApplicationDate
    * 
    * @param aUtility
    * @param inDate
    * @param aBisName
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */

   private void validateApplicationDate(Utility aUtility, EiaDate inDate,
         String aBisName) throws BusinessViolation, ObjectNotFound,
         InvalidData, AccessDenied, SystemFailure, DataNotFound, NotImplemented
   {

      if(inDate == null)
      {
         aUtility.throwException(ExceptionCode.ERR_RM_MISSING_APPLICATION_DATE,
               "Missing required data: aApplicationDate", aBisName,
               Severity.UnRecoverable);
      }
   }

   /**
    * Method verifyDate.
    * 
    * @param inDate
    * @param dateParm
    * @param aBisName
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */
   private void verifyDate(EiaDate inDate, String dateParm, String aBisName)
         throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
         NotImplemented, ObjectNotFound, DataNotFound
   {
      if(validDayMonth(inDate.aDay, inDate.aMonth))
      {
         if(inDate.aYear > 0 && inDate.aYear <= 2100)
         {
            return;
         }
      }

      aUtility.throwException(ExceptionCode.ERR_RM_INVALID_DATA,
            "Invalid date: " + dateParm, aBisName, Severity.UnRecoverable);
   }

   /**
    * Method validDayMonth.
    * 
    * @param aDay
    * @param aMonth
    * @return boolean
    */
   private boolean validDayMonth(short aDay, short aMonth)
   {
      if(aMonth == 1 || aMonth == 3 || aMonth == 5 || aMonth == 7
            || aMonth == 8 || aMonth == 10 || aMonth == 12)
      {
         if(aDay >= 1 && aDay <= 31)
         {
            return true;
         }
      }
      if(aMonth == 4 || aMonth == 6 || aMonth == 9 || aMonth == 11)
      {
         if(aDay >= 1 && aDay <= 30)
         {
            return true;
         }
      }
      if(aMonth == 2)
      {
         if(aDay >= 1 && aDay <= 29)
         {
            return true;
         }
      }
      return false;
   }

   /**
    * Method isAlphNum.
    * 
    * @param inString
    * @return boolean
    */
   private boolean isAlphNum(String inString)
   {
      for(int i = 0; i < inString.length(); i++)
      {
         if(!Character.isLetterOrDigit(inString.toUpperCase().charAt(i)))
            return false;
      }
      return true;
   }

   /**
    * Method isNumeric.
    * 
    * @param inString
    * @return boolean
    */
   private boolean isNumeric(String inString)
   {
      for(int i = 0; i < inString.length(); i++)
      {
         if(!Character.isDigit(inString.toUpperCase().charAt(i)))
            return false;
      }
      return true;
   }
   /**
    * @return an instance of SOAC
    */
   protected SOAC makeSOAC(SOAC oldSoac)
   {
      if(oldSoac != null)
      {
         return oldSoac;
      }
      return new SOAC(myProperties, aUtility);
   }
   /**
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
    * @return
    */
   protected SendFacilityAssignmentOrderValidation makeSendFacilityAssignmentOrderValidation(
         BisContext aContext, String aSOACServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
         String aOrderActionId, String aOrderNumber, String aOrderActionType,
         BooleanOpt aCompletionIndicator, StringOpt aSubActionType,
         String aCircuitId, Location aServiceLocation,
         EiaDate aOriginalDueDate, EiaDateOpt aSubsequentDueDate,
         EiaDate aApplicationDate, StringOpt aTDMTelphoneNumber,
         StringOpt aRelatedServiceOrderNumber,
         BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
         BooleanOpt aResendFlag)
   {
      return new SendFacilityAssignmentOrderValidation(aContext,
            aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
            aCompletionIndicator, aSubActionType, aCircuitId, aServiceLocation,
            aOriginalDueDate, aSubsequentDueDate, aApplicationDate,
            aTDMTelphoneNumber, aRelatedServiceOrderNumber,
            aLineShareDisconnectFlag, aClassOfService, aResendFlag);
   }
   /**
    * Use validator/SendFacilityAssignmentOrderValidate.xml file
    */
   protected void validator()
   {
      String methodName = "SendFacilityAssignmentOrder: validator()";
      aUtility.log(LOG_DEBUG_LEVEL_1, ">" + methodName);

      if(validator == null)
      {

         String mapFileName = (String) myProperties
               .get("SFAO_VALIDATOR_VARIABLE_MAP_FILE");

         validator = new Validator(aUtility, myProperties, mapFileName);
      }
      aUtility.log(LOG_DEBUG_LEVEL_1, "<" + methodName);
   }
   /**
    * @param aReturnObject
    * @throws InvalidData
    * @throws AccessDenied
    * @throws BusinessViolation
    * @throws SystemFailure
    * @throws NotImplemented
    * @throws ObjectNotFound
    * @throws DataNotFound
    */
   private void logOutput(SendFacilityAssignmentOrderReturn aReturnObject)
         throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
         NotImplemented, ObjectNotFound, DataNotFound
   {
      String methodName = "SendFacilityAssignmentOrder: logOutput()";
      log(LOG_DEBUG_LEVEL_1, ">" + methodName);

      // aReturnObject
      try
      {
         log(LOG_OUTPUT_DATA,
               "SendFacilityAssignmentOrderReturn=<"
                     + (new SendFacilityAssignmentOrderReturnBisHelper(
                           aReturnObject)).toString() + ">");
      }
      catch(Exception e)
      {
         log(LOG_OUTPUT_DATA, "aReturnObject<null>");
      }
      finally
      {
         log(LOG_DEBUG_LEVEL_1, "<" + methodName);
      }
   }
}