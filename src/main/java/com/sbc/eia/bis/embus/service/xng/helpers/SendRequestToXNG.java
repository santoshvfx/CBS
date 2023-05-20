//$Id: SendRequestToXNG.java,v 1.20 2007/02/15 19:20:38 ml2917 Exp $

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
//# 4/19/2005 | Jinmin Ni	         | create
//# 5/10/2005 | Jinmin Ni		     | change the logic to call RmBIStoXnGRCMapping
//#                                    due to extra properties DESTINATION XnG RC expect
//# 5/11/2005 | Jinmin Ni            | change request method signature to take properites 
//#	                                   as hashtable
//# 5/19/2005 | Jinmin Ni			 | added sendRequest for publishAutoDiscovery method
//# 6/01/2005 | Jinmin Ni            | Changed to use new copyright notice &
//#                                    modified exception parser per code review
//# 6/03/2005 | Jinmin Ni            | Log remote return before handling exception
//# 6/08/2005 | Jinmin Ni            | corrected the acknowledgement check for publishAutoDiscovery emthod
//# 6/24/2005 | Jinmin Ni            | Added rmBisRequests() for ModifyNetworkInventory, QueryNetworkInventory
//#                                    and RetrieveCustomerTransportInfo. 
//#								       Change the all methods from static to none
//#                                    Extract the common code to new methods sendAndReceive() and sendAndAck()
//#                                    Pass exception to exception builder. 
//# 7/20/2005 | Jinmin Ni            | Addes sendRequest() for DisconnectService, ModifyService and ModifyFacilityInfo
//#                                  | Handle EncoderException and java.lang.IllegalArguement exception specially to extract
//#                                  | nested meaningful error messag
//# 8/07/2005 | Jinmin Ni            | Passing error message from XnG as aText instead of msg to exceptionBuilder
//#12/14/2005 | Mark Liljequist      | Change for version 2.0 XNG RC.
//#  1/7/2006 | Rachel				 | Added ModifyNetworkInventory.
//#12/14/2005 | Mark Liljequist      | Remove ModifyNetworkInventory and changes for version 5.0.

package com.sbc.eia.bis.embus.service.xng.helpers;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.access.EmbusServiceException;
import com.sbc.eia.bis.embus.service.access.EncoderException;
import com.sbc.eia.bis.embus.service.access.ResourceConnectorServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceTimeOutException;
import com.sbc.eia.bis.embus.service.utilities.ExceptionHelper;
import com.sbc.eia.bis.embus.service.xng.XNGService;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CreateFacilityRequestImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityResponse.impl.CreateFacilityResponseImpl;
import com.sbc.eia.bis.embus.service.xng.DisconnectServiceRequest.impl.DisconnectServiceRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoRequest.impl.ModifyFacilityInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoResponse.impl.ModifyFacilityInfoResponseImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyPathStatusRequest.impl.ModifyPathStatusRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyServiceRequest.impl.ModifyServiceRequestImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.PublishAutoDiscoveryRequestImpl;
import com.sbc.eia.bis.embus.service.xng.QueryNetworkInventoryRequest.impl.QueryNetworkInventoryRequestImpl;
import com.sbc.eia.bis.embus.service.xng.QueryNetworkInventoryResponse.impl.QueryNetworkInventoryResponseImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.RetrieveCustomerTransportInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.impl.RetrieveCustomerTransportInfoResponseImpl;
import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.rm_ls_types.OrderAction;

public class SendRequestToXNG {

	public static final String XNG_EXCEPTION_RULE_FILE_TAG =
		"EXCEPTION_BUILDER_XNG_RULE_FILE";

	private Utility aUtility;
	private BisContext aContext;
	private Hashtable aProp;
	private XNGService service;
	private Properties jmsResponseProperties;

	/**
	 * @param aUtility
	 * @param aContext
	 * @param aProp
	 * @param service
	 */
	public SendRequestToXNG(
		Utility aUtility,
		BisContext aContext,
		Hashtable aProp,
		XNGService service) {

		this.aUtility = aUtility;
		this.aProp = aProp;
		this.service = service;
		this.aContext = aContext;
		jmsResponseProperties = new Properties();

	}

	/**
	 * @param request
	 * @param aOrderAction
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public CreateFacilityResponseImpl sendRequest(
		CreateFacilityRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_CREATE_FACILITY);

		CreateFacilityResponseImpl response = null;

		try {

			response =
				(CreateFacilityResponseImpl) service.rmBisRequests(
					(CreateFacilityRequestImpl) request,
					createJMSProperties(aOrderAction),
					jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(XNGHelper.XNG_REQUEST_FOR_CREATE_FACILITY);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_CREATE_FACILITY);

		return response;

	}

	/**
	 * @param request
	 * @param aOrderAction
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public QueryNetworkInventoryResponseImpl sendRequest(
		QueryNetworkInventoryRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_QUERY_NETWORK_INVENTORY);

		QueryNetworkInventoryResponseImpl response = null;

		try {

			response =
				(QueryNetworkInventoryResponseImpl) service.rmBisRequests(
					(QueryNetworkInventoryRequestImpl) request,
					createJMSProperties(aOrderAction),
					jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(XNGHelper.XNG_REQUEST_FOR_QUERY_NETWORK_INVENTORY);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_QUERY_NETWORK_INVENTORY);

		return response;

	}

	/**
	 * @param request
	 * @param aOrderAction
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public RetrieveCustomerTransportInfoResponseImpl sendRequest(
		RetrieveCustomerTransportInfoRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_RETRIEVE_CUSTOMER_TRANSPORT_INFO);

		RetrieveCustomerTransportInfoResponseImpl response = null;

		try {

			response =
				(
					RetrieveCustomerTransportInfoResponseImpl) service
						.rmBisRequests(
					(RetrieveCustomerTransportInfoRequestImpl) request,
					createJMSProperties(aOrderAction),
					jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(
				XNGHelper.XNG_REQUEST_FOR_RETRIEVE_CUSTOMER_TRANSPORT_INFO);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_RETRIEVE_CUSTOMER_TRANSPORT_INFO);

		return response;

	}

	/**
	 * Method sendRequest.
	 * @param request
	 * @param aOrderAction
	 * @return boolean
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public boolean sendRequest(
		ModifyServiceRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_MODIFY_SERVICE);

		try {

			service.rmBisRequests(
				(ModifyServiceRequestImpl) request,
				createJMSProperties(aOrderAction),
				jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(XNGHelper.XNG_REQUEST_FOR_MODIFY_SERVICE);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_MODIFY_SERVICE);

		return parseACK(jmsResponseProperties);

	}

	/**
	 * Method sendRequest.
	 * @param request
	 * @param aOrderAction
	 * @return boolean
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public boolean sendRequest(
		DisconnectServiceRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_DISCONNECT_SERVICE);

		try {

			service.rmBisRequests(
				(DisconnectServiceRequestImpl) request,
				createJMSProperties(aOrderAction),
				jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(XNGHelper.XNG_REQUEST_FOR_DISCONNECT_SERVICE);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_DISCONNECT_SERVICE);

		return parseACK(jmsResponseProperties);

	}

	/**
	 * Method sendRequest.
	 * @param request
	 * @param aOrderAction
	 * @return boolean
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public ModifyFacilityInfoResponseImpl sendRequest(
		ModifyFacilityInfoRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_MODIFY_FACILITY_INFO);

		ModifyFacilityInfoResponseImpl response = null;

		try {

			response =
				(ModifyFacilityInfoResponseImpl) service.rmBisRequests(
					(ModifyFacilityInfoRequestImpl) request,
					createJMSProperties(aOrderAction),
					jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(XNGHelper.XNG_REQUEST_FOR_MODIFY_FACILITY_INFO);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_MODIFY_FACILITY_INFO);

		return response;

	}


	/**
	 * @param request
	 * @param aOrderAction
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public boolean sendRequest(
		ModifyPathStatusRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_MODIFY_PATH_STATUS);

		try {

			service.rmBisRequests(
				(ModifyPathStatusRequestImpl) request,
				createJMSProperties(aOrderAction),
				jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(XNGHelper.XNG_REQUEST_FOR_MODIFY_PATH_STATUS);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_MODIFY_PATH_STATUS);

		return parseACK(jmsResponseProperties);

	}

	/**
	 * @param request
	 * @param aOrderAction
	 * @return
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public boolean sendRequest(
		PublishAutoDiscoveryRequestImpl request,
		OrderAction aOrderAction)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		logCall(XNGHelper.XNG_REQUEST_FOR_PUBLISH_AUTO_DISCOVERY);

		try {

			service.rmBisRequests(
				(PublishAutoDiscoveryRequestImpl) request,
				createJMSProperties(aOrderAction),
				jmsResponseProperties);

		} catch (Exception e) // call exception include ServiceException
			{
			logReturn(XNGHelper.XNG_REQUEST_FOR_PUBLISH_AUTO_DISCOVERY);

			ParseXnGRCException(request.getClass().getPackage().getName(), e);
		}

		logReturn(XNGHelper.XNG_REQUEST_FOR_PUBLISH_AUTO_DISCOVERY);

		return parseACK(jmsResponseProperties);

	}

	/**
	 * Method ParseXnGRCException.
	 * @param e
	 * @param aUtility
	 * @param aContext
	 * @param ruleFile
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	private void ParseXnGRCException(String objectClass, Exception e)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String errorCode = null;
		String errorText = null;
		String ruleFile = (String) aProp.get(XNG_EXCEPTION_RULE_FILE_TAG);
		int defaultRuleInd = ExceptionBuilderRule.NO_DEFAULT;

		if (e instanceof ResourceConnectorServiceException) {
			//xng exception
			ResourceConnectorServiceException aRCException =
				(ResourceConnectorServiceException) e;
			errorText = aRCException.getErrorDescription();
			errorCode = aRCException.getErrorCode();
		} else if (e instanceof EmbusServiceException) {
			//xng exception
			EmbusServiceException aEmbusException = (EmbusServiceException) e;
			errorText = aEmbusException.getErrorDescription();
			errorCode = aEmbusException.getErrorCode();
		} else if (e instanceof EncoderException) {
			//rm exception
			ExceptionHelper.throwException(
				(EncoderException) e,
				objectClass,
				this.aUtility,
				ruleFile,
				aContext);
		} else if (e instanceof IllegalArgumentException) {
			//rm exception
			ExceptionHelper.throwException(
				(IllegalArgumentException) e,
				objectClass,
				this.aUtility,
				ruleFile,
				aContext);
		} else if (e instanceof ServiceTimeOutException) {
			//xng  time out exception
			//errorText = "Error in XnG RC Service access: "+e.getMessage();
			errorText = e.getMessage();
		} else {
			//xng exception
			errorText = e.getMessage();
			defaultRuleInd = 1;
		}

		ExceptionBuilder.parseException(
			aContext,
			ruleFile,
			null,
			errorCode,
			errorText,
			true,
			defaultRuleInd,
			null,
			e,
			aUtility,
			null,
			null,
			null);
	}

	/**
	 * @param jmsResponseProperties
	 * @return
	 */

	// Parse the properties for the ACKNOWLEDGEMENT response.

	private boolean parseACK(Properties jmsResponseProperties) {

		String ack =
			jmsResponseProperties.getProperty(
				RmBISToXnGRCMapping.EMBUS_MESSAGE_TAG);

		if (ack != null
			&& ack.trim().endsWith(RmBISToXnGRCMapping.ACKNOWLEDGEMENT)) {
			return true;
		}

		return false;
	}

	/**
	 * @param aOrderAction
	 * @return
	 */
	// Create the JMS properties from the prop map.

	private Properties createJMSProperties(OrderAction aOrderAction) {

		return (new RmBISToXnGRCMapping(aProp, aOrderAction)).mapProperties();

	}

	/**
	 * @param type
	 */
	// Log the remote call.

	private void logCall(String type) {

		aUtility.log(
			LogEventId.REMOTE_CALL,
			XNGHelper.XNG_SERVICE_NAME,
			XNGHelper.XNG_SERVICE_NAME + XNGHelper.XNG_RC_VERSION,
			XNGHelper.XNG_SERVICE_NAME + XNGHelper.XNG_RC_VERSION,
			type);

	}

	/**
	 * @param type
	 */

	// Log the remote return.

	private void logReturn(String type) {

		aUtility.log(
			LogEventId.REMOTE_RETURN,
			XNGHelper.XNG_SERVICE_NAME,
			XNGHelper.XNG_SERVICE_NAME + XNGHelper.XNG_RC_VERSION,
			XNGHelper.XNG_SERVICE_NAME + XNGHelper.XNG_RC_VERSION,
			type);

	}
}
