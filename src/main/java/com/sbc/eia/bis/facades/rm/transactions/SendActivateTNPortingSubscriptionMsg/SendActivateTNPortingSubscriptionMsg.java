// $Id: SendActivateTNPortingSubscriptionMsg.java,v 1.7 2006/07/01 00:04:07 jp2854 Exp $
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
//# 06/02/2006  Jyothi Jasti         Updated for LS3.
//# 06/22/06    Jyothi Jasti         Code WT Changes.

package com.sbc.eia.bis.facades.rm.transactions.SendActivateTNPortingSubscriptionMsg;

import java.util.Hashtable;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.BusinessInterface.rm.soa.SOA;
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
import com.sbc.eia.idl.rm.SendActivateTNPortingSubscriptionMsgReturn;
import com.sbc.eia.idl.rm.bishelpers.SendActivateTNPortingSubscriptionMsgReturnBisHelper;
import com.sbc.eia.idl.types.bishelpers.StringSeqBisHelper;

public class SendActivateTNPortingSubscriptionMsg extends TranBase {

	private Utility utility = null;
	private Hashtable properties = null;
	private Validator validator = null;
	private SOA service = null;

	/**
	 * Constructor
	 */
	public SendActivateTNPortingSubscriptionMsg() {
		super();
	}

	/**
	 * Constructor
	 * @param param
	 */
	public SendActivateTNPortingSubscriptionMsg(Hashtable param) {
		super(param);
		utility = this;
		properties = getPROPERTIES();
	}

	/**
	 * Method executes the business logic. 
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aLocalServiceProviderId
	 * @param aTelephoneNumbers
	 * @param aLogger
	 * @return the sendActivateTNPortingSubscriptionMsgReturn
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public SendActivateTNPortingSubscriptionMsgReturn execute(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aLocalServiceProviderId,
		String[] aTelephoneNumbers,
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

		String myMethodName = "SendActivateTNPortingSubscriptionMsg:execute()";
		log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

		SendActivateTNPortingSubscriptionMsgReturn aReturn = null;

		// log BisContext
		try {
			log(
				LOG_INPUT_DATA,
				"aContext=<"
					+ (new BisContextBisHelper(aContext)).toString()
					+ ">");
		} catch (Exception e) {
			log(LOG_DEBUG_LEVEL_2, "aContext<null>");
		}

		try {
			ObjectPropertyManager aContextOPM =
				new ObjectPropertyManager(aContext.aProperties);

			// validate BisContext
			ValidateHelper.validateBisContext(
				utility,
				aContextOPM,
				getEnv("BIS_NAME"));

			// check client authorization
			validateClient(aContextOPM, null, // group_id
			ExceptionCode.ERR_RM_UNAUTHORIZED_USER,
				ExceptionCode.ERR_RM_CLIENT_AUTHORIZATION_EXCEPTION);

			// log input
			logInput(
				aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix,
				aLocalServiceProviderId,
				aTelephoneNumbers);
			
			validateInput(aContext,
				aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix,
				aLocalServiceProviderId,
				aTelephoneNumbers);

			if (service == null) {
				service = new SOA(utility, properties);
				service.loadSOAToBISStatusMappings();
			}

			aReturn =
				service.sendActivateTNPortingSubscriptionMsg(
					aContext,
					aSOACServiceOrderNumber,
					aSOACServiceOrderCorrectionSuffix,
					aLocalServiceProviderId,
					aTelephoneNumbers,
					aLogger);
					
			logOutput( aReturn ); 

		} finally {
			log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
		}
		
		return aReturn;
	}
	
	/**
	 * validateInput method validates the input data.
	 * @param aContext
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aLocalServiceProviderId
	 * @param aTelephoneNumbers
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public void validateInput(
		BisContext aContext,
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aLocalServiceProviderId,
		String[] aTelephoneNumbers)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound 
	{
		String myMethodName = "SendActivateTNPortingSubscriptionMsg:validateInput()";
		log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);
		
		//Validate Input using Validator framework
		if (validator == null) {

			String mapFileName =
				(String) properties.get(
					"SATNPSM_VALIDATIOR_VARIABLE_MAP_FILE");

			validator = new Validator(utility, properties, mapFileName);
		}

		SendActivateTNPortingSubscriptionMsgValidation validation =
			new SendActivateTNPortingSubscriptionMsgValidation(
				aContext,
				aSOACServiceOrderNumber,
				aSOACServiceOrderCorrectionSuffix,
				aLocalServiceProviderId,
				aTelephoneNumbers);

		validator.validate(aContext, validation);
			
		log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * Method logs the input data.
	 * @param aSOACServiceOrderNumber
	 * @param aSOACServiceOrderCorrectionSuffix
	 * @param aLocalServiceProviderId
	 * @param aTelephoneNumbers
	 */
	public void logInput(
		String aSOACServiceOrderNumber,
		String aSOACServiceOrderCorrectionSuffix,
		String aLocalServiceProviderId,
		String[] aTelephoneNumbers) {

		String myMethodName = "SendActivateTNPortingSubscriptionMsg:logInput()";
		log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

		log(LOG_INPUT_DATA,"SOACServiceOrderNumber=<" + aSOACServiceOrderNumber + ">");
		log(LOG_INPUT_DATA, "SOACServiceOrderCorrectionSuffix=<" + aSOACServiceOrderCorrectionSuffix + ">");
		log(LOG_INPUT_DATA,"LocalServiceProviderId=<" + aLocalServiceProviderId + ">");

		try {
			log(LOG_INPUT_DATA,"TelephoneNumbers=<" + (new StringSeqBisHelper(aTelephoneNumbers)).toString() + ">");
		} catch (Exception e) {
			log(LOG_DEBUG_LEVEL_2, "TelephoneNumbers=<null>");
		}
		log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
	}
	
	/**
	 * Method logs the return object using BisHelper.
	 * @param aReturn
	 */
	public void logOutput(SendActivateTNPortingSubscriptionMsgReturn aReturn) {
		String myMethodName = "SendActivateTNPortingSubscriptionMsg:logOutput()";
		log(LOG_DEBUG_LEVEL_1, ">" + myMethodName);

		try {
			log(LOG_OUTPUT_DATA, "SendActivateTNPortingSubscriptionMsgReturn=<" + (new SendActivateTNPortingSubscriptionMsgReturnBisHelper(aReturn)).toString() + ">");
		}
		catch (Exception e) {
			log(LOG_DEBUG_LEVEL_2, "SendActivateTNPortingSubscriptionMsgReturn=<null>");
		}            

		log(LOG_DEBUG_LEVEL_1, "<" + myMethodName);
	}

}
