// $Id: SendFacilityAssignmentOrder2.java,v 1.4 2008/03/07 18:09:55 op1664 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//# RESTRICTED - PROPRIETARY INFORMATION
//# The information herein is for use only by authorized employees
//# of SBC Services Inc. and authorized Affiliates of SBC Services,
//# Inc., and is not for general distribution within or outside the
//# respective companies.
//# Copying or reproduction without prior written approval is prohibited.
//#
//# © 2008-2015 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date | Author | Notes
//# 12/20/2007 Hongmei Parkin Created initial file
//# 01/29/2008 Ott Phannavong Add makeSOAC() and
// makeSendFacilityAssignmentOrderValidation(..)
//# --------------------------------------------------------------------
//############################################################################

package com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder2;

import java.util.Hashtable;

import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC;
import com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder.SendFacilityAssignmentOrder;
import com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder.SendFacilityAssignmentOrderValidation;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.validator.Validator;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.lim_types.Location;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder2Return;
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrderReturn;
import com.sbc.eia.idl.rm.bishelpers.SendFacilityAssignmentOrder2ReturnBisHelper;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC2;

public class SendFacilityAssignmentOrder2 extends SendFacilityAssignmentOrder
{

   protected StringOpt oldNetworkType = null;
   protected StringOpt secondaryCircuitID = null;
   protected StringOpt secondaryRelatedCircuitID = null;
   protected CompositeObjectKey billingAccountNumber = null;

   /**
    * Constructor.
    */
   public SendFacilityAssignmentOrder2(Hashtable aProperties)
   {
      super(aProperties);
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
    * @return SendFacilityAssignmentOrder2Return
    * @throws BusinessViolation
    * @throws ObjectNotFound
    * @throws InvalidData
    * @throws AccessDenied
    * @throws SystemFailure
    * @throws DataNotFound
    * @throws NotImplemented
    */
   public SendFacilityAssignmentOrder2Return execute(BisContext aContext,
         String aSOACServiceOrderNumber,
         String aSOACServiceOrderCorrectionSuffix, String aNetworkType,
         StringOpt aOldNetworkType, String aOrderActionId, String aOrderNumber,
         String aOrderActionType, BooleanOpt aCompletionIndicator,
         StringOpt aSubActionType, String aCircuitId,
         StringOpt aSecondaryCircuitId, Location aServiceLocation,
         EiaDate aOriginalDueDate, EiaDateOpt aSubsequentDueDate,
         EiaDate aApplicationDate, StringOpt aRelatedCircuitID,
         StringOpt aSecondaryRelatedCircuitID,
         StringOpt aRelatedServiceOrderNumber,
         BooleanOpt aLineShareDisconnectFlag, String aClassOfService,
         BooleanOpt aResendFlag, CompositeObjectKey aBillingAccountNumber,
         ObjectPropertySeqOpt aProperties,
         com.sbc.eia.bis.RmNam.utilities.Logger aLogger) throws InvalidData,
         AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
         ObjectNotFound, DataNotFound
   {
      String methodName = "SendFacilityAssignmentOrder2: execute()";
      aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      oldNetworkType = aOldNetworkType;
      secondaryCircuitID = aSecondaryCircuitId;
      secondaryRelatedCircuitID = aSecondaryRelatedCircuitID;
      billingAccountNumber = aBillingAccountNumber;
      SendFacilityAssignmentOrderReturn sfaoReturn = super.execute(aContext,
            aSOACServiceOrderNumber, aSOACServiceOrderCorrectionSuffix,
            aNetworkType, aOrderActionId, aOrderNumber, aOrderActionType,
            aCompletionIndicator, aSubActionType, aCircuitId, aServiceLocation,
            aOriginalDueDate, aSubsequentDueDate, aApplicationDate,
            aRelatedCircuitID, aRelatedServiceOrderNumber,
            aLineShareDisconnectFlag, aClassOfService, aResendFlag,
            aProperties, aLogger);

      // build acknowledgement response object

      SendFacilityAssignmentOrder2Return sendFacilityAssignmentOrder2Return = new SendFacilityAssignmentOrder2Return(
            sfaoReturn.aContext, sfaoReturn.aSoacServiceOrderNumber,
            sfaoReturn.aSOACServiceOrderCorrectionSuffix);

      // log output
      logOutput(sendFacilityAssignmentOrder2Return);

      log(LOG_DEBUG_LEVEL_1, "<" + methodName);
      return sendFacilityAssignmentOrder2Return;
   }
   /*
    * Return and instance of SOAC
    */
   protected SOAC makeSOAC(SOAC oldSoac)
   {
      String methodName = "SendFacilityAssignmentOrder2: makeSOAC()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      SOAC2 soac2;
      if(oldSoac == null)
      {
         soac2 = new SOAC2(myProperties, aUtility);
      }
      else
      {
         soac2 = (SOAC2) oldSoac;
      }
      soac2.setBillingAccountNumber(billingAccountNumber);
      soac2.setOldNetworkType(oldNetworkType);
      soac2.setSecondaryCircuitID(secondaryCircuitID);
      soac2.setSecondaryRelatedCircuitID(secondaryRelatedCircuitID);

      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return (SOAC) soac2;
   }
   /*
    * Return an instance of SendFacilityAssignmentOrder2Validation
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
      String methodName = "SendFacilityAssignmentOrder2: makeSendFacilityAssignmentOrderValidation()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      SendFacilityAssignmentOrder2Validation SFAO2V = new SendFacilityAssignmentOrder2Validation(
            aContext, aSOACServiceOrderNumber,
            aSOACServiceOrderCorrectionSuffix, aNetworkType, aOrderActionId,
            aOrderNumber, aOrderActionType, aCompletionIndicator,
            aSubActionType, aCircuitId, aServiceLocation, aOriginalDueDate,
            aSubsequentDueDate, aApplicationDate, aTDMTelphoneNumber,
            aRelatedServiceOrderNumber, aLineShareDisconnectFlag,
            aClassOfService, aResendFlag, oldNetworkType, secondaryCircuitID,
            secondaryRelatedCircuitID, billingAccountNumber);

      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return (SendFacilityAssignmentOrderValidation) SFAO2V;
   }
   /*
    * (non-Javadoc)
    * 
    * @see com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder.SendFacilityAssignmentOrder#validator()
    */
   protected void validator()
   {
      String methodName = "SendFacilityAssignmentOrder2: validator()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      if(validator == null)
      {

         String mapFileName = (String) myProperties
               .get("SFAO2_VALIDATOR_VARIABLE_MAP_FILE");

         validator = new Validator(aUtility, myProperties, mapFileName);
      }

      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
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
   private void logOutput(SendFacilityAssignmentOrder2Return aReturnObject)
         throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
         NotImplemented, ObjectNotFound, DataNotFound
   {
      String methodName = "SendFacilityAssignmentOrder2: logOutput()";
      log(LOG_DEBUG_LEVEL_1, ">" + methodName);

      // aReturnObject
      try
      {
         log(LOG_OUTPUT_DATA,
               "SendFacilityAssignmentOrder2Return=<"
                     + (new SendFacilityAssignmentOrder2ReturnBisHelper(
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