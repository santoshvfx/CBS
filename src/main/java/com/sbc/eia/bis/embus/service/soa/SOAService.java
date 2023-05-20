//$Id: SOAService.java,v 1.2 2006/06/12 19:38:29 jp2854 Exp $
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
//# 06/02/2006   Jyothi Jasti         Creation for SOA EMBus service wrapper.

package com.sbc.eia.bis.embus.service.soa;

import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.soa.interfaces.OpIdl33ServerOrderPathSubscriptionRequestsActivateSv;

public class SOAService {
	private SOAHelper aSOAHelper;
	private Properties properties;
	private Logger logger;

	/**
	 * Constructor
	 */
	public SOAService() {
		aSOAHelper = null;
	}

	/**
	 * Constructor
	 * @param properties
	 * @param logger
	 */
	public SOAService(Hashtable properties, Logger logger)
	{
		this();
		this.logger = logger;
		this.properties = (Properties) properties;
	}

	/**
	 * Sends the request to SOA and receives from SOA using SOAHelper class.
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return the sendActivateTNPortingSubscriptionMsg response 
	 * @throws ServiceException
	 */
	public Object sendActivateTNPortingSubscriptionMsgRequest(
		OpIdl33ServerOrderPathSubscriptionRequestsActivateSv request,
		Properties jmsRequstProperties,
		Properties jmsResponseProperties)
		throws ServiceException {

		if (aSOAHelper == null) {
			aSOAHelper = new SOAHelper(properties, logger);
		}

		return aSOAHelper.sendActivateTNPortingSubscriptionMsgRequest(
			request,
			jmsRequstProperties,
			jmsResponseProperties);
	}
}
