// $Id: SendFacilityAssignmentOrder3.java,v 1.8 2009/03/17 04:58:35 dl8121 Exp $
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
//# Date 		| 	Author 				|	Notes
//# 12/20/2007 		Hongmei Parkin 			Created initial file
//# 01/29/2008 		Ott Phannavong 			Add makeSOAC() and makeSendFacilityAssignmentOrderValidation(..)
//# 01/09/2009 		Rommel Baldivas 		Add added LS10 changes
//# --------------------------------------------------------------------
//############################################################################

package com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder3;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.eia.bis.BusinessInterface.rm.SOAC.SOAC3;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.common.utilities.BisDateUtil;
import com.sbc.eia.bis.framework.logging.LogEventId;
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
import com.sbc.eia.idl.rm.SendFacilityAssignmentOrder3Return;
import com.sbc.eia.idl.rm.bishelpers.SendFacilityAssignmentOrder3ReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.SubActionTypeValues;
import com.sbc.eia.idl.types.BooleanOpt;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.eia.idl.types.EiaDateOpt;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.bishelpers.BooleanOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateBisHelper;
import com.sbc.eia.idl.types.bishelpers.EiaDateOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;

public class SendFacilityAssignmentOrder3 extends TranBase
{
	protected Utility aUtility = null;
	protected Hashtable myProperties = null;
	protected Validator validator = null;
	
	protected StringOpt oldNetworkType = null;
    protected StringOpt secondaryCircuitID = null;
    protected StringOpt secondaryRelatedCircuitID = null;
    protected CompositeObjectKey billingAccountNumber = null;

    //ra0331: added LS10 changes
	protected BooleanOpt interceptRedirectIndicator = null;
	protected StringOpt dryloopRelatedCircuitId = null;
	protected StringOpt dSLDisconnectTelephoneNumber = null;
	protected StringOpt exceptionRoutingIndicator = null;
	private SOAC3 cacheSOAC = null;

	//SFAO exception rule
	private String aSFAORuleFile = null;
	public static final String SFAO3_RULE_FILE_TAG =
		"EXCEPTION_BUILDER_SFAO_RULE_FILE";
	
	/**
	 * Constructor.
	 */
	public SendFacilityAssignmentOrder3()
	{
		super();
	}

	/**
	 * for SendFacilityAssignmentOrder3.
	 * 
	 * @param sfaoparam
	 */
	public SendFacilityAssignmentOrder3(Hashtable sfao3param)
	{
	   super(sfao3param);
	   aUtility = this;
	   myProperties = getPROPERTIES();
	   aSFAORuleFile =
		(String) myProperties.get(SFAO3_RULE_FILE_TAG);
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
    * @return SendFacilityAssignmentOrder3Return
    * @throws BusinessViolation
    * @throws ObjectNotFound
    * @throws InvalidData
    * @throws AccessDenied
    * @throws SystemFailure
    * @throws DataNotFound
    * @throws NotImplemented
    */
   public SendFacilityAssignmentOrder3Return execute(BisContext aContext,
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
         BooleanOpt aInterceptRedirectIndicator,
		 StringOpt aDryloopRelatedCircuitId,
		 StringOpt aDSLDisconnectTelephoneNumber,
		 StringOpt aExceptionRoutingIndicator,
         ObjectPropertySeqOpt aProperties,
         com.sbc.eia.bis.RmNam.utilities.Logger aLogger) throws InvalidData,
         AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
         ObjectNotFound, DataNotFound
   {
      String methodName = "SendFacilityAssignmentOrder3: execute()";
      myLogger = aLogger;
      aLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);
      callerContext = aContext;
      
      SendFacilityAssignmentOrder3Return sendFacilityAssignmentOrder3Return = null;

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
      
	      //LS9
	      oldNetworkType = aOldNetworkType;
	      secondaryCircuitID = aSecondaryCircuitId;
	      secondaryRelatedCircuitID = aSecondaryRelatedCircuitID;
	      billingAccountNumber = aBillingAccountNumber;
	      
	      //ra0331: added changes to LS10
	      interceptRedirectIndicator = aInterceptRedirectIndicator;
	      dryloopRelatedCircuitId = aDryloopRelatedCircuitId;
	      dSLDisconnectTelephoneNumber  = aDSLDisconnectTelephoneNumber;
	      exceptionRoutingIndicator = aExceptionRoutingIndicator;

	      //logInput
	      logInput(aSOACServiceOrderNumber,
			   aSOACServiceOrderCorrectionSuffix, aNetworkType,
			   aOldNetworkType, aOrderActionId, aOrderNumber,
		       aOrderActionType, aCompletionIndicator,
		       aSubActionType, aCircuitId,
		       aSecondaryCircuitId, aServiceLocation,
		       aOriginalDueDate, aSubsequentDueDate,
		       aApplicationDate, aRelatedCircuitID,
		       aSecondaryRelatedCircuitID,
		       aRelatedServiceOrderNumber,
		       aLineShareDisconnectFlag, aClassOfService,
		       aResendFlag, aBillingAccountNumber,
		       aInterceptRedirectIndicator,
		       aDryloopRelatedCircuitId,
		       aDSLDisconnectTelephoneNumber,
		       aExceptionRoutingIndicator,
		       aProperties);

	      validateInputWithValidator(aContext,
	 	         aSOACServiceOrderNumber,
	 	         aSOACServiceOrderCorrectionSuffix, aNetworkType,
	 	         aOldNetworkType, aOrderActionId, aOrderNumber,
	 	         aOrderActionType, aCompletionIndicator,
	 	         aSubActionType, aCircuitId,
	 	         aSecondaryCircuitId, aServiceLocation,
	 	         aOriginalDueDate, aSubsequentDueDate,
	 	         aApplicationDate, aRelatedCircuitID,
	 	         aSecondaryRelatedCircuitID,
	 	         aRelatedServiceOrderNumber,
	 	         aLineShareDisconnectFlag, aClassOfService,
	 	         aResendFlag, aBillingAccountNumber,
	 	         aInterceptRedirectIndicator,
	 			 aDryloopRelatedCircuitId,
	 			 aDSLDisconnectTelephoneNumber,
	 			 aExceptionRoutingIndicator,
	 	         aProperties);
 
      cacheSOAC = makeSOAC3(cacheSOAC);

      cacheSOAC.sendFacilityAssignmentOrder(aContext,
    		     aSOACServiceOrderNumber,
    		     aSOACServiceOrderCorrectionSuffix, aNetworkType,
    		     aOrderActionId, aOrderNumber, aOrderActionType,
    		     aCompletionIndicator, aSubActionType,
    		     aCircuitId, aServiceLocation,
    		     aOriginalDueDate, aSubsequentDueDate,
    		     aApplicationDate, aRelatedCircuitID,
    		     aRelatedServiceOrderNumber,
    		     aLineShareDisconnectFlag, aClassOfService,
    		     aResendFlag, aProperties);
 
      StringOpt aSOACServiceOrderNumberOpt = IDLUtil.toOpt(aSOACServiceOrderNumber);
      StringOpt aSOACServiceOrderCorrectionSuffixOpt = IDLUtil.toOpt(aSOACServiceOrderCorrectionSuffix);
      
      sendFacilityAssignmentOrder3Return = new SendFacilityAssignmentOrder3Return(
              aContext, aSOACServiceOrderNumberOpt, aSOACServiceOrderCorrectionSuffixOpt);

      // log output
      logOutput(sendFacilityAssignmentOrder3Return);
 
      }
      finally
      {
         log(LOG_INFO_LEVEL_1, "<" + methodName);
      }     
      return sendFacilityAssignmentOrder3Return;
   }

   /*
    * Return and instance of SOAC
    */
   protected SOAC3 makeSOAC3(SOAC3 oldSoac)
   {
      String methodName = "SendFacilityAssignmentOrder3: makeSOAC3()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);

      SOAC3 soac3;
      if(oldSoac == null)
      {
         soac3 = new SOAC3(myProperties, aUtility);
      }
      else
      {
         soac3 = (SOAC3) oldSoac;
      }
      soac3.setBillingAccountNumber(billingAccountNumber);
      soac3.setOldNetworkType(oldNetworkType);
      soac3.setSecondaryCircuitID(secondaryCircuitID);
      soac3.setSecondaryRelatedCircuitID(secondaryRelatedCircuitID);
      
      //ra0331: added changes to LS10
      soac3.setDryloopRelatedCircuitId(dryloopRelatedCircuitId);
      soac3.setExceptionRoutingIndicator(exceptionRoutingIndicator);
      if (OptHelper.isBooleanOptEmpty(interceptRedirectIndicator))
      {
    	  interceptRedirectIndicator.theValue(false);
          soac3.setInterceptRedirectIndicator(interceptRedirectIndicator);
      }
      else 
      {  
    	  soac3.setInterceptRedirectIndicator(interceptRedirectIndicator);
      }
      soac3.setDSLDisconnectTelephoneNumber(dSLDisconnectTelephoneNumber);      
                 
      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
      return (SOAC3) soac3;
   }
   /*
    * Return an instance of SendFacilityAssignmentOrder3Validation
    */
   protected SendFacilityAssignmentOrder3Validation makeSendFacilityAssignmentOrder3Validation(
		   BisContext context,
			String sOACServiceOrderNumber,
	        String sOACServiceOrderCorrectionSuffix, String networkType,
	        StringOpt oldNetworkType, String orderActionId, String orderNumber,
	        String orderActionType, BooleanOpt completionIndicator,
	        StringOpt subActionType, String circuitId,
	        StringOpt secondaryCircuitId, Location serviceLocation,
	        EiaDate originalDueDate, EiaDateOpt subsequentDueDate,
	        EiaDate applicationDate, StringOpt relatedCircuitID,
	        StringOpt secondaryRelatedCircuitID,
	        StringOpt relatedServiceOrderNumber,
	        BooleanOpt lineShareDisconnectFlag, String classOfService,
	        BooleanOpt resendFlag, CompositeObjectKey billingAccountNumber,
	        BooleanOpt interceptRedirectIndicator,
			StringOpt dryloopRelatedCircuitId,
			StringOpt dSLDisconnectTelephoneNumber,
			StringOpt exceptionRoutingIndicator)
   {
      String methodName = "SendFacilityAssignmentOrder3: makeSendFacilityAssignmentOrderValidation()";
      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);
     
      return new SendFacilityAssignmentOrder3Validation(context,
			sOACServiceOrderNumber,
	        sOACServiceOrderCorrectionSuffix, networkType,
	        oldNetworkType, orderActionId, orderNumber,
	        orderActionType, completionIndicator,
	        subActionType, circuitId,
	        secondaryCircuitId, serviceLocation,
	        originalDueDate, subsequentDueDate,
	        applicationDate, relatedCircuitID,
	        secondaryRelatedCircuitID,
	        relatedServiceOrderNumber,
	        lineShareDisconnectFlag, classOfService,
	        resendFlag, billingAccountNumber,
	        interceptRedirectIndicator,
			dryloopRelatedCircuitId,
			dSLDisconnectTelephoneNumber,
			exceptionRoutingIndicator);
   }
  
   // added for LS10 changes
   protected void validateInputWithValidator(BisContext aContext,
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
	         BooleanOpt aInterceptRedirectIndicator,
			 StringOpt aDryloopRelatedCircuitId,
			 StringOpt aDSLDisconnectTelephoneNumber,
			 StringOpt aExceptionRoutingIndicator,
	         ObjectPropertySeqOpt aProperties)
	         throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
	         NotImplemented, ObjectNotFound, DataNotFound
	   {
	      String myMethodNameName = "SendFacilityAssignmentOrder3: validateInputWithValidator()";
	      log(LOG_INFO_LEVEL_1, ">" + myMethodNameName);

	      String aBisName = myProperties.get("BIS_NAME").toString();

	      //Validate Input using Validator framework
	      validator();

	      SendFacilityAssignmentOrder3Validation sfao3Validation = makeSendFacilityAssignmentOrder3Validation(
	    		aContext,
	  			aSOACServiceOrderNumber,
	  	        aSOACServiceOrderCorrectionSuffix, aNetworkType,
	  	        aOldNetworkType, aOrderActionId, aOrderNumber,
	  	        aOrderActionType, aCompletionIndicator,
	  	        aSubActionType, aCircuitId,
	  	        aSecondaryCircuitId, aServiceLocation,
	  	        aOriginalDueDate, aSubsequentDueDate,
	  	        aApplicationDate, aRelatedCircuitID,
	  	        aSecondaryRelatedCircuitID,
	  	        aRelatedServiceOrderNumber,
	  	        aLineShareDisconnectFlag, aClassOfService,
	  	        aResendFlag, aBillingAccountNumber,
	  	        aInterceptRedirectIndicator,
	  			aDryloopRelatedCircuitId,
	  			aDSLDisconnectTelephoneNumber,
	  			aExceptionRoutingIndicator);
	      
	      validator.validate(aContext, sfao3Validation);
	      
	      //ra0331: added changes in LS10
	      if(!OptHelper.isStringOptEmpty(aExceptionRoutingIndicator))
	      {		
	    	  if((aExceptionRoutingIndicator.theValue().trim().length() > 10)
		               || aExceptionRoutingIndicator.theValue().matches("[a-zA-Z&&0-9&&-]{10}"))
	    	  {	    		
	    		  ExceptionBuilder.parseException(
					aContext, 		// BisContext
					aSFAORuleFile, 	// String ruleFile
					null, 			// String exceptionFile
					"185", 			// String aCode
					null, 			// String aText
					true,  			// throwException
					1, 				// aDefaultIndex
					null, 			// String[] aTextValues
					null, 			// anException
					aUtility, 		// utility logger
					null, 			// origin use file
					null, 			// severity use file
					null); 			//Property aDataSet         
	         }
	      }

	      // ra0331: add changes in LS10 [AD: Validation p.5]	      	      
	      if (!OptHelper.isBooleanOptEmpty(aInterceptRedirectIndicator) && aInterceptRedirectIndicator.theValue())
	      {
	    	  if(OptHelper.isStringOptEmpty(aDryloopRelatedCircuitId)
	    		  && OptHelper.isStringOptEmpty(aRelatedCircuitID)) 
	    	  {
	    		  ExceptionBuilder.parseException(
					aContext,
					aSFAORuleFile,
					null,
					"184",
					null,
					true,
					1,
					null,
					null,
					aUtility,
					null,
					null,
					null);              
	    	  } 
	  
		      // ra0331: add changes in LS10 [AD: Validation p.5]
		      if(!OptHelper.isStringOptEmpty(aDryloopRelatedCircuitId)
	    		  && !OptHelper.isStringOptEmpty(aRelatedCircuitID)) 
		      {
		    	  ExceptionBuilder.parseException(
  					aContext,
  					aSFAORuleFile,
  					null,
  					"183",
  					null,
  					true,
  					1,
  					null,
  					null,
  					aUtility,
  					null,
  					null,
  					null);              
		      }
	
		  } else {
		    	  log(LOG_INFO_LEVEL_1, "aInterceptRedirectIndicator> = " + aInterceptRedirectIndicator);
		  }
	      
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
    
	   /*
	    * (non-Javadoc)
	    * 
	    * @see com.sbc.eia.bis.facades.rm.transactions.SendFacilityAssignmentOrder.SendFacilityAssignmentOrder#validator()
	    */
	   protected void validator()
	   {
	      String methodName = "SendFacilityAssignmentOrder3: validator()";
	      aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + methodName);
	
	      if(validator == null)
	      {	
	         String mapFileName = (String) myProperties
	               .get("SFAO3_VALIDATOR_VARIABLE_MAP_FILE");
	
	         validator = new Validator(aUtility, myProperties, mapFileName);
	      }	
	      aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + methodName);
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
		       BooleanOpt aInterceptRedirectIndicator,
		       StringOpt aDryloopRelatedCircuitId,
		       StringOpt aDSLDisconnectTelephoneNumber,
		       StringOpt aExceptionRoutingIndicator,
		       ObjectPropertySeqOpt aProperties)
	   {
		   
		   String myMethodNameName = "SendFacilityAssignmentOrder3.logInput()";
	      log(LOG_INFO_LEVEL_1, ">" + myMethodNameName);
	
	      log(LOG_INPUT_DATA, "aSOACServiceOrderNumber<" + aSOACServiceOrderNumber
	            + ">");
	      log(LOG_INPUT_DATA, "aSOACServiceOrderCorrectionSuffix<"
	            + aSOACServiceOrderCorrectionSuffix + ">");
	      log(LOG_INPUT_DATA, "aNetworkType<" + aNetworkType + ">");
	      
	      try 
	      {
	         log(LOG_INPUT_DATA, "aOldNetworkType<" + aOldNetworkType.theValue()
	               + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aOldNetworkType<null>");
	      }
	      
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
	         log(LOG_INPUT_DATA, "aSecondaryCircuitId<" + aSubActionType.theValue()
	               + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aSecondaryCircuitId<null>");
	      }
	      
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
	         log(LOG_INPUT_DATA, "aRelatedCircuitID<"
	               + aRelatedCircuitID.theValue() + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aRelatedCircuitID<null>");
	      }
	
	      try
	      {
	         log(LOG_INPUT_DATA, "aSecondaryRelatedCircuitID<"
	               + aSecondaryRelatedCircuitID.theValue() + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aSecondaryRelatedCircuitID<null>");
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
	         log(LOG_INPUT_DATA, "aBillingAccountNumber<"
	               + (new CompositeObjectKeyBisHelper(aBillingAccountNumber)).toString() + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aBillingAccountNumber<null>");
	      }
	
	      try
	      {
	         log(LOG_INPUT_DATA, "aInterceptRedirectIndicator<"
	               + (new BooleanOptBisHelper(aInterceptRedirectIndicator)).toString() + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aInterceptRedirectIndicator<null>");
	      }
	
	      try
	      {
	         log(LOG_INPUT_DATA, "aDryloopRelatedCircuitId<"
	               + aDryloopRelatedCircuitId.theValue() + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aDryloopRelatedCircuitId<null>");
	      }
	      
	      try
	      {
	         log(LOG_INPUT_DATA, "aDSLDisconnectTelephoneNumber<"
	               + aDSLDisconnectTelephoneNumber.theValue() + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aDSLDisconnectTelephoneNumber<null>");
	      }      
	
	      try
	      {
	         log(LOG_INPUT_DATA, "aExceptionRoutingIndicator<"
	               + aExceptionRoutingIndicator.theValue() + ">");
	      }
	      catch(Exception e)
	      {
	         log(LOG_INPUT_DATA, "aExceptionRoutingIndicator<null>");
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
	    * @param aReturnObject
	    * @throws InvalidData
	    * @throws AccessDenied
	    * @throws BusinessViolation
	    * @throws SystemFailure
	    * @throws NotImplemented
	    * @throws ObjectNotFound
	    * @throws DataNotFound
	    */
	   private void logOutput(SendFacilityAssignmentOrder3Return aReturnObject)
	         throws InvalidData, AccessDenied, BusinessViolation, SystemFailure,
	         NotImplemented, ObjectNotFound, DataNotFound
	   {
	      String methodName = "SendFacilityAssignmentOrder3: logOutput()";
	      log(LOG_DEBUG_LEVEL_1, ">" + methodName);
	
	      // aReturnObject
	      try
	      {
	         log(LOG_OUTPUT_DATA,
	               "SendFacilityAssignmentOrder3Return=<"
	                     + (new SendFacilityAssignmentOrder3ReturnBisHelper(
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
	   
}