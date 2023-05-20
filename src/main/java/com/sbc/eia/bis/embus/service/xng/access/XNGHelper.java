//$Id: XNGHelper.java,v 1.33.2.1 2009/06/08 17:38:30 mr0226 Exp $

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
//# 4/14/2005 | Jinmin Ni	         | create
//# 5/19/2005 | Jinmin Ni			 | added sendRequest for publishAutoDiscovery method
//# 5/26/2005 | Jinmin Ni            | Passed jms properties to Embus Service wrapper for CreateFacility Request.                                
//# 6/01/2005 | Jinmin Ni            | Changed to use new copyright notice
//# 6/20/2005 | Jinmin Ni            | Editted to accept encrypted password
//# 6/23/2005 | Jinmin Ni            | Added sendRequest() for ModifyNetworkInventory, QueryNetworkInventory
//#                                  | RetrieveCustomerTransportInfo
//# 7/20/2005 | Jinmin Ni            | Addes sendRequest() for DisconnectService, ModifyService and ModifyFacilityInfo
//#                                  | Changed version number to 1.4.0 from 1.4.1
//# 9/13/2005 | Jinmin Ni            | Change to expect ask instead of response for MS, DS, and MFI
//#10/14/2005 | Mark Liljequist      | Change for version 1.5 XNG RC.
//#12/14/2005 | Mark Liljequist      | Change for version 2.0 XNG RC.
//#  1/7/2006 | Rachel               | Added ModifyNetworkInventory.
//# 2/14/2007 | Mark Liljequist      | Remove ModifyNetworkInventory and changes for version 5.0.
//# 8/01/2008 | Mark Liljequist      | Add asynch request for rCTI and changes for version 6.0.
//# 1/14/2008 | Mark Liljequist      | Add asynch request for qNI.

package com.sbc.eia.bis.embus.service.xng.access;

import java.util.Hashtable;
import java.util.Properties;

import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.Receipt;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.CreateFacilityRequest;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CreateFacilityRequestImpl;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityResponse.CreateFacilityResponse;
import com.sbc.eia.bis.embus.service.xng.DisconnectServiceRequest.DisconnectServiceRequest;
import com.sbc.eia.bis.embus.service.xng.DisconnectServiceRequest.impl.DisconnectServiceRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoRequest.ModifyFacilityInfoRequest;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoRequest.impl.ModifyFacilityInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoResponse.ModifyFacilityInfoResponse;
import com.sbc.eia.bis.embus.service.xng.ModifyPathStatusRequest.ModifyPathStatusRequest;
import com.sbc.eia.bis.embus.service.xng.ModifyPathStatusRequest.impl.ModifyPathStatusRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyServiceRequest.ModifyServiceRequest;
import com.sbc.eia.bis.embus.service.xng.ModifyServiceRequest.impl.ModifyServiceRequestImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.PublishAutoDiscoveryRequest;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.PublishAutoDiscoveryRequestImpl;
import com.sbc.eia.bis.embus.service.xng.QueryNetworkInventoryRequest.QueryNetworkInventoryRequest;
import com.sbc.eia.bis.embus.service.xng.QueryNetworkInventoryRequest.impl.QueryNetworkInventoryRequestImpl;
import com.sbc.eia.bis.embus.service.xng.QueryNetworkInventoryResponse.QueryNetworkInventoryResponse;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.RetrieveCustomerTransportInfoRequest;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.RetrieveCustomerTransportInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoResponse.RetrieveCustomerTransportInfoResponse;


/**
 * @author jn1936
 *
 */

public class XNGHelper extends DecryptionServiceHelper
{
	public final static String XNG_SERVICE_NAME = "XNG_RC";
	public static final String XNG_RC_VERSION = "11.0";
	public final static String XNG_REQUEST_FOR_CREATE_FACILITY =
		"XNGCreateFacilityRequest";
	public final static String XNG_REQUEST_FOR_MODIFY_PATH_STATUS =
		"XNGModifyPathStatusRequest";
	public final static String XNG_REQUEST_FOR_PUBLISH_AUTO_DISCOVERY =
		"XNGPublishAutoDiscoveryRequest";
	public final static String XNG_REQUEST_FOR_QUERY_NETWORK_INVENTORY =
		"XNGQueryNetworkInventoryRequest";
	public final static String XNG_RESPONSE_FOR_QUERY_NETWORK_INVENTORY =
		"XNGQueryNetworkInventoryResponse";
	public final static String XNG_REQUEST_FOR_RETRIEVE_CUSTOMER_TRANSPORT_INFO =
		"XNGRetrieveCustomerTransportInfoRequest";
	public final static String XNG_REQUEST_FOR_DISCONNECT_SERVICE =
		"XNGDisconnectServiceRequest";
	public final static String XNG_REQUEST_FOR_MODIFY_SERVICE =
		"XNGModifyServiceRequest";
	public final static String XNG_REQUEST_FOR_MODIFY_FACILITY_INFO =
		"XNGModifyFacilityInfoRequest";
	
	
	private Receipt retrieveCustomerTransportInfoReceipt = null;
	private Receipt queryNetworkInventoryReceipt = null;
	private Properties marshalUnmarshalOptions;

	/**
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public XNGHelper(Hashtable properties, Logger logger)
		throws ServiceException
	{
		super(properties, logger, XNG_SERVICE_NAME);
		//m_selfTestFunctionKey = "XNGCreateFacilityRequest";
		
		// Set marshalUnmarshalOptions properties.
		marshalUnmarshalOptions = new Properties();
		marshalUnmarshalOptions.setProperty(
			Marshaller.JAXB_FORMATTED_OUTPUT,
			"true");
	}

	/**
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	//requet for createFacility()
	public Object rmBisRequests(
			CreateFacilityRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.

		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				CreateFacilityRequest.class.getPackage().getName(),
				marshalUnmarshalOptions));

		// Set the decoder for the response class.

		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
					CreateFacilityResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));

		return getServiceAccess().sendAndReceive(
			XNG_REQUEST_FOR_CREATE_FACILITY,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties)[0];

	}

	/**
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	//requet for modifyPathStatus()
	public String rmBisRequests(
		ModifyPathStatusRequestImpl request, 
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.

		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				ModifyPathStatusRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));

		return getServiceAccess().sendAndAcknowledge(
			XNG_REQUEST_FOR_MODIFY_PATH_STATUS,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties);

	}

	/**
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	//requet for publishAutoDiscory()
	public String rmBisRequests(
		PublishAutoDiscoveryRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.

		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				PublishAutoDiscoveryRequest.class.getPackage().getName(),
				marshalUnmarshalOptions));


		return getServiceAccess().sendAndAcknowledge(
			XNG_REQUEST_FOR_PUBLISH_AUTO_DISCOVERY,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties);

	}
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	//requet for QueryNetworkInventory()
	public Object rmBisRequests(
			QueryNetworkInventoryRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				QueryNetworkInventoryRequest.class.getPackage().getName(),
				marshalUnmarshalOptions));
				
		// Set the decoder for the response class.
		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
				QueryNetworkInventoryResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));

		return getServiceAccess().sendAndReceive(
			XNG_REQUEST_FOR_QUERY_NETWORK_INVENTORY,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties)[0];

	}

	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	// Asynch requet for RetrieveCustomerTransportInfo()
	public void rmBisAsynchRequests(
			QueryNetworkInventoryRequestImpl request,
			Properties jmsRequstProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
					QueryNetworkInventoryRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		// Set the decoder for the response class.
		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
					QueryNetworkInventoryResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		// Call the method and get back a receipt.
		// The receipt will be needed for the pickup call.
		
		queryNetworkInventoryReceipt = getServiceAccess().sendAndGetReceipt(
			XNG_REQUEST_FOR_QUERY_NETWORK_INVENTORY,
			new Object[] { request }, 
			jmsRequstProperties);
		
		return;
		
	}
	
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	
	// Asynch requet for RetrieveCustomerTransportInfo()
	public void rmBisAsynchRequests(
			RetrieveCustomerTransportInfoRequestImpl request,
			Properties jmsRequstProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				RetrieveCustomerTransportInfoRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		// Set the decoder for the response class.
		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
				RetrieveCustomerTransportInfoResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		// Call the method and get back a receipt.
		// The receipt will be needed for the pickup call.
		
		retrieveCustomerTransportInfoReceipt = getServiceAccess().sendAndGetReceipt(
			XNG_REQUEST_FOR_RETRIEVE_CUSTOMER_TRANSPORT_INFO,
			new Object[] { request }, 
			jmsRequstProperties);
		
		return;
		
	}
		
	/**
	 * @return
	 * @throws ServiceException
	 */
	
	//Asynch response for RetrieveCustomerTransportInfo()
	public Object rmBisRetrieveCustomerTransportInfoAsynchResponses(Properties jmsResponseProperties)
		throws ServiceException
	{
	
		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				RetrieveCustomerTransportInfoRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		// Set the decoder for the response class.
		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
				RetrieveCustomerTransportInfoResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		return getServiceAccess().pickup(retrieveCustomerTransportInfoReceipt, jmsResponseProperties) [0];

	}
	/**
	 * @return
	 * @throws ServiceException
	 */
	
	//Asynch response for QueryNetworkInventory()
	public Object rmBisQueryNetworkInventoryAsynchResponses(Properties jmsResponseProperties)
		throws ServiceException
	{
	
		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
					QueryNetworkInventoryRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		// Set the decoder for the response class.
		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
					QueryNetworkInventoryResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		return getServiceAccess().pickup(queryNetworkInventoryReceipt, jmsResponseProperties) [0];

	}
		

	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	//requet for RetrieveCustomerTransportInfo()
	public Object rmBisRequests(
			RetrieveCustomerTransportInfoRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				RetrieveCustomerTransportInfoRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		// Set the decoder for the response class.
		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
				RetrieveCustomerTransportInfoResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		return getServiceAccess().sendAndReceive(
			XNG_REQUEST_FOR_RETRIEVE_CUSTOMER_TRANSPORT_INFO,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties)[0];


	}
	

	/**
	 * Method rmBisRequests.
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return String
	 * @throws ServiceException
	 */
	//request for DisconnectService
	public String rmBisRequests(
			DisconnectServiceRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				DisconnectServiceRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		return getServiceAccess().sendAndAcknowledge(
			XNG_REQUEST_FOR_DISCONNECT_SERVICE,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties);


	}
	

	/**
	 * Method rmBisRequests.
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return String
	 * @throws ServiceException
	 */
	//request for ModifyService
	public String rmBisRequests(
			ModifyServiceRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				ModifyServiceRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		return getServiceAccess().sendAndAcknowledge(
			XNG_REQUEST_FOR_MODIFY_SERVICE,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties);


	}

	/**
	 * Method rmBisRequests.
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return String
	 * @throws ServiceException
	 */
	//request for ModifyFaclityInfo request
	public Object rmBisRequests(
			ModifyFacilityInfoRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		// Set the encoder for the request class.
		getServiceAccess().setEncoder(
			new RmBisXngEncoderDecoder(
				ModifyFacilityInfoRequest
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		getServiceAccess().setDecoder(
			new RmBisXngEncoderDecoder(
				ModifyFacilityInfoResponse
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));
		
		return getServiceAccess().sendAndReceive(
			XNG_REQUEST_FOR_MODIFY_FACILITY_INFO,
			new Object[] { request }, 
			jmsRequstProperties,
			jmsResponseProperties)[0];

	}
	
}
