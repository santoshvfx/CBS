// $Id: NpConnectorHelper.java,v 1.10 2008/03/14 14:03:17 biscvsid Exp $

/* Copyright Notice
 * RESTRICTED - PROPRIETARY INFORMATION
 * The information herein is for use only by authorized employees
 * of SBC Services Inc. and authorized Affiliates of SBC Services,
 * Inc., and is not for general distribution within or outside the
 * the respective companies.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2004 SBC Services, Inc.
 * All rights reserved.
 */

package com.sbc.eia.bis.embus.service.npconnector.access;

import java.util.Hashtable;
import java.util.Properties;

import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.access.ServiceHelper;
import com.sbc.eia.bis.embus.service.access.ServiceHelperSelfTestException;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.NpRequestSeqType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.NpResponseSeqType;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestSeqTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpResponseSeqTypeImpl;

/** 
 *  Used for NPConnector requests.
 *  
 */

public class NpConnectorHelper extends ServiceHelper
{

	private final static String NP_SERVICE_NAME = "NP_CONNECTOR";
	private final static String NP_REQUEST_LIST = "NpRequestList";

	/**
	 * Constructor for NPConnectorHelper.
	 * @param Hashtable
	 * @param Logger
	 */

	public NpConnectorHelper(Hashtable properties, Logger logger)
		throws ServiceException
	{
		super(properties, logger, NP_SERVICE_NAME);
		//m_selfTestFunctionKey = "NpRequestList";
		
		setServiceAccess(
			new NpConnectorAccess(
				getEnvironmentName(),
				getClientConfigFileName(),
				logger));

	}

	/**
	 * Method rmBismRequest.
	 * Set the encoder and decoder and do a send and receive.
	 * The encoder and decoder are set here so multiple request types can be done.
	 * @param NpRequestSeqTypeImpl
	 * @return NpResponseSeqTypeImpl
	 * 
	 * @throws ServiceException
	 */

	public NpResponseSeqTypeImpl rmBisRequests(NpRequestSeqTypeImpl request)
		throws ServiceException
	{

		// Set the properties.

		Properties options = new Properties();

		options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");

		// Set the encoder for the request class.

		getServiceAccess().setEncoder(
			new RmBisRequestsEncoderDecoder(
				NpRequestSeqType.class.getPackage().getName(),
				options));

		// Set the decoder for the response class.

		getServiceAccess().setDecoder(
			new RmBisRequestsEncoderDecoder(
				NpResponseSeqType.class.getPackage().getName(),
				options));

		return (NpResponseSeqTypeImpl) getServiceAccess().sendAndReceive(NP_REQUEST_LIST,
			new Object[] { request })[0];

	}

	/**
	 * Send message to the broker
	 */
	protected void sendAndReceiveSelfTestMessage() throws ServiceHelperSelfTestException
	{
//		defaultSendAndReceiveSelfTestMessage();
	}
}
