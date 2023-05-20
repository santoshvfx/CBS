//$Id: SendRequestToSOA.java,v 1.1 2006/06/02 21:19:16 jp2854 Exp $
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
//# Date      | Author               | Notes
//# --------------------------------------------------------------------
//# 06/02/2006   Jyothi Jasti         Creation for SOA EMBus service wrapper.

package com.sbc.eia.bis.embus.service.soa;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.EmbusServiceException;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ResourceConnectorServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl;
import com.sbc.eia.bis.embus.service.soa.interfaces.impl.OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl;
import com.sbc.eia.bis.embus.service.utilities.ExceptionHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

public class SendRequestToSOA {

	public static final String SOA_EXCEPTION_RULE_FILE_TAG =
		"EXCEPTION_BUILDER_SOA_RULE_FILE";

	private Utility utility;
	private BisContext bisContext;
	private Hashtable properties;
	private SOAService service;
	private Properties jmsResponseProperties;

	/**
	 * Constructor
	 * @param utility
	 * @param context
	 * @param properties
	 * @param service
	 */
	public SendRequestToSOA( Utility utility, BisContext context, Hashtable properties, SOAService service) {
		this.utility = utility;
		this.bisContext = context;
		this.properties = properties;
		this.service = service;
		jmsResponseProperties = new Properties();

	}

	/**
	 * @param request
	 * @return the response
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl sendRequest(OpIdl33ServerOrderPathSubscriptionRequestsActivateSvImpl request)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(SOAHelper.REQUEST_FOR_SEND_ACTIVATE_TN_PORTING_SUBSCRIPTION_MSG);

		OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl response = null;

		try 
		{
			response =
				(OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResultImpl) service
						.sendActivateTNPortingSubscriptionMsgRequest(
					request,
					null,
					jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
		{
			logReturn(SOAHelper.REQUEST_FOR_SEND_ACTIVATE_TN_PORTING_SUBSCRIPTION_MSG);
			parseException(request.getClass().getPackage().getName(), e);
		}

		logReturn(SOAHelper.REQUEST_FOR_SEND_ACTIVATE_TN_PORTING_SUBSCRIPTION_MSG);

		return response;
	}

	/**
	 * @param className
	 * @param e
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private void parseException(String className, Exception e)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String errorCode = null;
		String errorDescription = null;
		String ruleFile = (String) properties.get(SOA_EXCEPTION_RULE_FILE_TAG);
		int defaultRuleInd = ExceptionBuilderRule.NO_DEFAULT;

		if (e instanceof ResourceConnectorServiceException) {
			errorDescription =
				((ResourceConnectorServiceException) e).getErrorDescription();
			errorCode = ((ResourceConnectorServiceException) e).getErrorCode();
		} else if (e instanceof EmbusServiceException) {
			errorDescription =
				((EmbusServiceException) e).getErrorDescription();
			errorCode = ((EmbusServiceException) e).getErrorCode();
		} else if (e instanceof EncoderException) {
			//rm exception
			ExceptionHelper.throwException(
				(EncoderException) e,
				className,
				utility,
				ruleFile,
				bisContext);
		} else if (e instanceof IllegalArgumentException) {
			//rm exception
			ExceptionHelper.throwException(
				(IllegalArgumentException) e,
				className,
				utility,
				ruleFile,
				bisContext);
		} else if (e instanceof ServiceTimeOutException) {
			errorDescription = e.getMessage();
		} else {
			errorDescription = e.getMessage();
			defaultRuleInd = 1;
		}

		ExceptionBuilder.parseException(
			bisContext,
			ruleFile,
			null,
			errorCode,
			errorDescription,
			true,
			defaultRuleInd,
			null,
			e,
			utility,
			null,
			null,
			null);
	}

	private void logCall(String type) {
		utility.log(
			LogEventId.REMOTE_CALL,
			SOAHelper.SOA_SERVICE_NAME,
			SOAHelper.SOA_SERVICE_NAME + SOAHelper.SOA_VERSION,
			SOAHelper.SOA_SERVICE_NAME + SOAHelper.SOA_VERSION,
			type);
	}

	private void logReturn(String type) {
		utility.log(
			LogEventId.REMOTE_RETURN,
			SOAHelper.SOA_SERVICE_NAME,
			SOAHelper.SOA_SERVICE_NAME + SOAHelper.SOA_VERSION,
			SOAHelper.SOA_SERVICE_NAME + SOAHelper.SOA_VERSION,
			type);
	}
}