// $Id: PublishRGActivation.java,v 1.17 2007/11/07 11:28:37 ra0331 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2007 AT&T Intellectual Property, L.P. All rights reserved.
//#
//# History :
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------
//#	03/30/2005  jp2854		 Creation.
//# 04/08/2005  Jinmin Ni    Changed to accommodate the IDL interface change
//#	05/09/2005  kk8467		 Added code to validate input and called 
//#							 business interface for PublishRGActivation
//#	05/11/2005  kk8467		 Modified validateInput().
//#	05/16/2005  kk8467		 Modified execute().
//#	05/19/2005  kk8467		 Removed creation of new PublishRGActivationReturn object. 
//# 05/31/2005	Chaitanya	 Changed LOG_ENTRY, LOG_EXIT to LOG_DEBUG_LEVEL_1
//#	06/14/2005  kk8467		 Added code to validate BillingAccountNumber
//#	07/29/2005  kk8467		 Added try - catch to log statements in logInput.
//#							 Called logInput() before validateInput().
//#							 Added log statements after entering and before exiting the method.
//#	08/05/2005  kk8467		 Logged BisContext first, next validated BisContext, then validated 
//#							 Client, logged the remaining input fields and finally validated 
//#							 remaining required input fields.

package com.sbc.eia.bis.facades.rm.transactions.PublishRGActivation;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
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
import com.sbc.eia.idl.rm.PublishRGActivationReturn;
import com.sbc.eia.idl.rm.bishelpers.PublishRGActivationReturnBisHelper;
import com.sbc.eia.idl.rm_ls_types.DSLAMTransportOpt;
import com.sbc.eia.idl.rm_ls_types.OrderAction;
import com.sbc.eia.idl.rm_ls_types.ResidentialGateway;
import com.sbc.eia.idl.rm_ls_types.bishelpers.DSLAMTransportOptBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.OrderActionBisHelper;
import com.sbc.eia.idl.rm_ls_types.bishelpers.ResidentialGatewayBisHelper;
import com.sbc.eia.idl.types.CompositeObjectKey;
import com.sbc.eia.idl.types.ObjectPropertySeqOpt;
import com.sbc.eia.idl.types.Severity;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.Time;
import com.sbc.eia.idl.types.bishelpers.CompositeObjectKeyBisHelper;
import com.sbc.eia.idl.types.bishelpers.ObjectPropertySeqOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringOptBisHelper;
import com.sbc.eia.idl.types.bishelpers.TimeBisHelper;

/**
 * @author jp2854
 */
public class PublishRGActivation extends TranBase {
	private Utility aUtility = null;
	private Hashtable aProperties = null;
	private com
		.sbc
		.eia
		.bis
		.BusinessInterface
		.rm
		.PublishRGActivation
		.PublishRGActivation publishService =
		null;
	private Validator validator = null;

	/**
	 * Constructor for PublishRGActivation.
	 */
	public PublishRGActivation() {
		super();
	}

	/**
	 * Constructor for PublishRGActivation.
	 * @param param
	 */
	public PublishRGActivation(Hashtable param) {
		super(param);
		aUtility = this;
		aProperties = getPROPERTIES();
	}

	/**
	 * Method execute.
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aBillingAccountNumber
	 * @param aDSLAM
	 * @param aRG
	 * @param aActivationTime
	 * @param aOrderAction
	 * @param aObjectProperties
	 * @param aLogger
	 * @return PublishRGActivationReturn
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public PublishRGActivationReturn execute(
		BisContext aContext,
		StringOpt aCustomerTransportId,
		CompositeObjectKey aBillingAccountNumber,
		DSLAMTransportOpt aDSLAM,
		ResidentialGateway aRG,
		Time aActivationTime,
		OrderAction aOrderAction,
		ObjectPropertySeqOpt aObjectProperties,
		Logger aLogger)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		callerContext = aContext;
		myLogger = aLogger;

		log(LOG_DEBUG_LEVEL_1, ">PublishRGActivation::execute()");

		PublishRGActivationReturn publishRGActivationReturn = null;
		String aBisName = aProperties.get("BIS_NAME").toString();
	
		// log aContext
		try {
			log(
				LOG_INPUT_DATA,
				"aContext=<"
					+ (new BisContextBisHelper(aContext)).toString()
					+ ">");
		} catch (Exception e) {
		}

		try {
			ObjectPropertyManager aContextOPM =
				new ObjectPropertyManager(aContext.aProperties);

			// validate BisContext
			ValidateHelper.validateBisContext(aUtility, aContextOPM, aBisName);

			// validate client
			validateClient(aContextOPM, null, // group_id
			ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);
			
			// log remaining input fields
			logInput(
				aContext,
				aCustomerTransportId,
				aBillingAccountNumber,
				aDSLAM,
				aRG,
				aActivationTime,
				aOrderAction,
				aObjectProperties);
	
			//Validate input using validator
			if (validator ==  null) {
				String map_file_name = (String) aProperties.get("PRGA_VALIDATOR_VARIABLE_MAP_FILE");
			
				validator = new Validator(aUtility, aProperties, map_file_name);
			}
		
			validator.validate(aContext, 
					new PublishRGActivationValidation(
							aContext, 
							aCustomerTransportId, 
							aBillingAccountNumber, 
							aDSLAM, 
							aRG, 
							aActivationTime, 
							aOrderAction), 
					true);
			
			
			if (aActivationTime == null) {
				aUtility.throwException(
					ExceptionCode.ERR_RM_MISSING_ACTIVATION_TIME,
					"Missing required data: aActivationTime",
					aProperties.get("BIS_NAME").toString(),
					Severity.UnRecoverable);
			}
			
			if (publishService == null) {
				publishService =
					new com
						.sbc
						.eia
						.bis
						.BusinessInterface
						.rm
						.PublishRGActivation
						.PublishRGActivation(aUtility, getPROPERTIES());
			}



			// publishRGActivation
			publishRGActivationReturn =
				publishService.publishActivation(
					aContext,
					aCustomerTransportId,
					aBillingAccountNumber,
					aDSLAM,
					aRG,
					aActivationTime,
					aOrderAction,
					aObjectProperties);

			// log output
			logOutput(publishRGActivationReturn);

		} finally {
			log(LOG_DEBUG_LEVEL_1, "<PublishRGActivation::execute()");
		}
		return publishRGActivationReturn;
	}

	/**
	 * Method logInput.
	 * @param aContext
	 * @param aCustomerTransportId
	 * @param aBillingAccountNumber
	 * @param aDSLAM
	 * @param aRG
	 * @param aActivationTime
	 * @param aOrderAction
	 * @param aObjectProperties
	 */
	private void logInput(
			BisContext aContext,
			StringOpt aCustomerTransportId,
			CompositeObjectKey aBillingAccountNumber,
			DSLAMTransportOpt aDSLAM,
			ResidentialGateway aRG,
			Time aActivationTime,
			OrderAction aOrderAction,
			ObjectPropertySeqOpt aObjectProperties) {	
	
		log(LOG_DEBUG_LEVEL_1, ">PublishRGActivation::logInput()");

		//  Log the input data.
		try {
			log(
				LOG_INPUT_DATA,
				"aContext=<"
					+ (new BisContextBisHelper(aContext)).toString()
					+ ">");
		} catch (Exception e) {
		}
		
		// log aCustomerTransportId
		try {
			log(
				LOG_INPUT_DATA,
				"aCustomerTransportId=<"
					+ (new StringOptBisHelper(aCustomerTransportId)).toString()
					+ ">");
		} catch (Exception e) {
		}

		// log aBillingAccountNumber
		try {
			log(
				LOG_INPUT_DATA,
				"aBillingAccountNumber=<"
					+ (new CompositeObjectKeyBisHelper(aBillingAccountNumber))
						.toString()
					+ ">");
		} catch (Exception e) {
		}

		// log aDSLAM
		try {
			log(
				LOG_INPUT_DATA,
				"aDSLAM=<"
					+ (new DSLAMTransportOptBisHelper(aDSLAM)).toString()
					+ ">");
		} catch (Exception e) {
		}

		// log aRG
		try {
			log(
				LOG_INPUT_DATA,
				"aRG=<"
					+ (new ResidentialGatewayBisHelper(aRG)).toString()
					+ ">");
		} catch (Exception e) {
		}

		// log aActivationTime
		try {
			log(
				LOG_INPUT_DATA,
				"aActivationTime=<"
					+ (new TimeBisHelper(aActivationTime)).toString()
					+ ">");
		} catch (Exception e) {
		}

		// log aOrderAction
		try {
			log(
				LOG_INPUT_DATA,
				"aOrderAction=<"
					+ (new OrderActionBisHelper(aOrderAction)).toString()
					+ ">");
		} catch (Exception e) {
		}

		// log aObjectProperties
		try {
			log(
				LOG_INPUT_DATA,
				"aObjectProperties=<"
					+ (new ObjectPropertySeqOptBisHelper(aObjectProperties))
						.toString()
					+ ">");
		} catch (Exception e) {
		}

		log(LOG_DEBUG_LEVEL_1, "<PublishRGActivation::logInput()");
	}

	/**
	 * Method logOutput.
	 * @param aReturnObject
	 */
	private void logOutput(PublishRGActivationReturn aReturnObject) {

		log(LOG_DEBUG_LEVEL_1, ">PublishRGActivation::logOutput()");

		// log output data
		log(
			LOG_OUTPUT_DATA,
			"publishRGActivationReturn=<"
				+ (new PublishRGActivationReturnBisHelper(aReturnObject))
					.toString()
				+ ">");

		log(LOG_DEBUG_LEVEL_1, "<PublishRGActivation::logOutput()");
	}
}