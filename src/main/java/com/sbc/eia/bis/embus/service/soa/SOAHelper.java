//$Id: SOAHelper.java,v 1.2 2006/06/14 19:20:19 jp2854 Exp $
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

import javax.xml.bind.Marshaller;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;
import com.sbc.eia.bis.embus.service.soa.interfaces.OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResult;
import com.sbc.eia.bis.embus.service.soa.interfaces.OpIdl33ServerOrderPathSubscriptionRequestsActivateSv;

public class SOAHelper extends DecryptionServiceHelper {
	
	public static final String SOA_SERVICE_NAME = "SOA";
	public static final String SOA_VERSION = "1.0";
	public static final String REQUEST_FOR_SEND_ACTIVATE_TN_PORTING_SUBSCRIPTION_MSG = "SendActivateTNPortingSubscriptionMsgRequest";
	private Properties marshalUnmarshalOptions;

	/**
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public SOAHelper(Hashtable properties, Logger logger)throws ServiceException 
	{
		super(properties, logger, SOA_SERVICE_NAME);

		// Set marshalUnmarshalOptions properties.
		marshalUnmarshalOptions = new Properties();
		marshalUnmarshalOptions.setProperty(
			Marshaller.JAXB_FORMATTED_OUTPUT,
			"true");
	}

	/**
	 * Method sets the Encoder and Decoder, sends and receives messages from SOA using ServiceAccess class.
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return the response object
	 * @throws ServiceException
	 */
	public Object sendActivateTNPortingSubscriptionMsgRequest(
		OpIdl33ServerOrderPathSubscriptionRequestsActivateSv request,
		Properties jmsRequstProperties,
		Properties jmsResponseProperties)
		throws ServiceException {

		// Set encoder for the request 
		getServiceAccess().setEncoder(
			new RmBisSoaEncoderDecoder(
				OpIdl33ServerOrderPathSubscriptionRequestsActivateSv
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));

		// Set decoder for the request 
		getServiceAccess().setDecoder(
			new RmBisSoaEncoderDecoder(
				OpIdl33ServerOrderPathSubscriptionRequestsActivateSvResult
					.class
					.getPackage()
					.getName(),
				marshalUnmarshalOptions));

		return getServiceAccess().sendAndReceive(
			REQUEST_FOR_SEND_ACTIVATE_TN_PORTING_SUBSCRIPTION_MSG,
			new Object[] { request },
			jmsRequstProperties,
			jmsResponseProperties)[0];

	}

}
