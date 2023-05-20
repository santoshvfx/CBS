//$Id: OMSService.java,v 1.5 2006/06/20 17:15:52 jp2854 Exp $

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
//# 5/11/2005 | Jinmin Ni	        | create
//# 6/01/2005 | Jinmin Ni           | Changed to use new copyright notice
//# 6/03/2005 | Jinmin Ni           | Changed to one way method call.
//# 7/25/2005 | Jinmin Ni           | Add overload the one way method to get previously
//#                                 | persisted jms properties for asyn. transaction
//# 06/19/2005| Jyothi Jasti        | Added publishMessage method.

package com.sbc.eia.bis.embus.service.oms;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.oms.access.OMSHelper;

/**
 * @author jn1936
 *
 */
public class OMSService
{
	
	OMSHelper theOMSHelper;

	Properties theProperties;
	Logger theLogger;

	/**
	 * @see java.lang.Object#Object()
	 */
	public OMSService() throws ServiceException
	{
		theOMSHelper = null;
	}


	/**
	 * Method OMSService.
	 * @param hash
	 * @param logger
	 * @throws ServiceException
	 */
	public OMSService(Hashtable hash, Logger logger) throws ServiceException
	{

		this();

		theLogger = logger;
		theProperties = new Properties();

		// Convert the hash to a properties.

		Set set = hash.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext())
		{
			Map.Entry entry = (Map.Entry) it.next();
			theProperties.setProperty(
				(String) entry.getKey(),
				(String) entry.getValue());
		}

	}


	/**
	 * Method OMSService.
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public OMSService(Properties properties, Logger logger)
		throws ServiceException
	{

		this();

		theLogger = logger;
		theProperties = properties;

	}



	/**
	 * Method rmBisRequests.
	 * @param inputXMLMsg
	 * @throws ServiceException
	 */
	public void rmBisRequests(
			String inputXMLMsg)
		throws ServiceException
	{

		if (theOMSHelper == null)
		{
			theOMSHelper = new OMSHelper(theProperties, theLogger);
		}

		theOMSHelper.rmBisRequests(inputXMLMsg);
	}
	
	public void rmBisRequests(
			String inputXMLMsg, 
			Properties jmsRequestPropertiesList, Properties override)
		throws ServiceException
	{

		if (theOMSHelper == null)
		{
			theOMSHelper = new OMSHelper(theProperties, theLogger);
		}

		theOMSHelper.rmBisRequests(inputXMLMsg,jmsRequestPropertiesList, override);
	}
	
	public void rmBisRequests(
			String inputXMLMsg, 
			Properties jmsRequestPropertiesList)
		throws ServiceException
	{

		if (theOMSHelper == null)
		{
			theOMSHelper = new OMSHelper(theProperties, theLogger);
		}

		theOMSHelper.rmBisRequests(inputXMLMsg,jmsRequestPropertiesList);
	}
	
	public void publishMessage(
			String inputXMLMsg, 
			Properties jmsRequestPropertiesList)
		throws ServiceException
	{

		if (theOMSHelper == null)
		{
			theOMSHelper = new OMSHelper(theProperties, theLogger);
		}

		theOMSHelper.publishMessage(inputXMLMsg,jmsRequestPropertiesList);
	}
}
