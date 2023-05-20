// $Id: NetProvisionHelper.java,v 1.14 2006/02/09 00:42:02 ml2917 Exp $

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

package com.sbc.eia.bis.embus.service.netprovision.access;

import java.util.Hashtable;
import java.util.Properties;

import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.SBCLoggingIDProvider;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.RequestType;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.ResponseType;
import com
	.sbc
	.eia
	.bis
	.embus
	.service
	.netprovision
	.interfaces
	.impl
	.RequestTypeImpl;
import com.sbc.logging.LogAssistantFactory;

/** 
 *  Used for Net Provision requests.
 *  
 */

public class NetProvisionHelper extends DecryptionServiceHelper {

	public final static String NET_PROVISION_SERVICE_NAME = "NET_PROVISION";
	public final static String NET_PROVISION_REQUEST = "NpRequestList";

	private SBCLoggingIDProvider loggingIDProvider = null;

	/**
	 * Constructor for NetProvisionHelper.
	 * @param Hashtable
	 * @param Logger
	 */

	public NetProvisionHelper(Hashtable properties, Logger logger)
		throws ServiceException {
		super(properties, logger, NET_PROVISION_SERVICE_NAME);

		loggingIDProvider =
			new SBCLoggingIDProvider(
				(String) properties.get("BIS_NAME"),
				(String) properties.get("BIS_VERSION"));

		getServiceAccess().setDefaultCorrelationIDProvider(loggingIDProvider);

	}

	/**
	 * Method rmBismRequest.
	 * Set the encoder and decoder and do a send and receive.
	 * The encoder and decoder are set here so multiple request types can be done.
	 * @param RequestTypeImpl
	 * @return ResponseTypeImpl
	 * @throws ServiceException
	 */

	public Object rmBisRequests(RequestTypeImpl request)
		throws ServiceException {

		// Set the properties.

		Properties options = new Properties();

		options.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, "true");

		// Set the encoder for the request class.

		getServiceAccess().setEncoder(
			new RmBisRequestsEncoderDecoder(
				RequestType.class.getPackage().getName(),
				options));

		// Set the decoder for the response class.

		getServiceAccess().setDecoder(
			new RmBisRequestsEncoderDecoder(
				ResponseType.class.getPackage().getName(),
				options));

		return getServiceAccess().sendAndReceive(
			NET_PROVISION_REQUEST,
			new Object[] { request })[0];

	}

}
