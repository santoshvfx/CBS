//$Id: XNGService.java,v 1.10 2007/02/15 19:20:23 ml2917 Exp $

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
//# 4/14/2005 | Jinmin Ni	        | create
//# 5/19/2005 | Jinmin Ni			| added sendRequest for publishAutoDiscovery method
//# 6/01/2005 | Jinmin Ni           | changed to use new copyright notice
//# 6/23/2005 | Jinmin Ni           | Added rmBisRequests() for ModifyNetworkInventory and QueryNetworkInventory
//#                                 | and RetrieveCustomerTransportInfo
//# 7/20/2005 | Jinmin Ni           | Addes sendRequest() for DisconnectService, ModifyService and ModifyFacilityInfo
//# 1/7/2006  | Rachel              | Added ModifyNetworkInventory.
//# 2/14/2007 |Mark Liljequist      | Remove ModifyNetworkInventory and changes for version 5.0.
                              
package com.sbc.eia.bis.embus.service.xng;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.xng.CreateFacilityRequest.impl.CreateFacilityRequestImpl;
import com.sbc.eia.bis.embus.service.xng.DisconnectServiceRequest.impl.DisconnectServiceRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyFacilityInfoRequest.impl.ModifyFacilityInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyPathStatusRequest.impl.ModifyPathStatusRequestImpl;
import com.sbc.eia.bis.embus.service.xng.ModifyServiceRequest.impl.ModifyServiceRequestImpl;
import com.sbc.eia.bis.embus.service.xng.PublishAutoDiscoveryRequest.impl.PublishAutoDiscoveryRequestImpl;
import com.sbc.eia.bis.embus.service.xng.QueryNetworkInventoryRequest.impl.QueryNetworkInventoryRequestImpl;
import com.sbc.eia.bis.embus.service.xng.RetrieveCustomerTransportInfoRequest.impl.RetrieveCustomerTransportInfoRequestImpl;
import com.sbc.eia.bis.embus.service.xng.access.XNGHelper;
/**
 * @author jn1936
 *
 */
public class XNGService
{
	
	XNGHelper theXNGHelper;

	Properties theProperties;
	Logger theLogger;

	/**
	 * @throws ServiceException
	 */
	/**
	 * Method NetProvisionService.
	 *
	 */

	public XNGService() throws ServiceException
	{
		theXNGHelper = null;
	}

	/**
	 * @param hash
	 * @param logger
	 * @throws ServiceException
	 */
	public XNGService(Hashtable hash, Logger logger) throws ServiceException
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
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public XNGService(Properties properties, Logger logger)
		throws ServiceException
	{

		this();

		theLogger = logger;
		theProperties = properties;

	}

	/**
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public Object rmBisRequests(
		CreateFacilityRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request,jmsRequstProperties,jmsResponseProperties);

	}
	/**
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public String rmBisRequests(
		ModifyPathStatusRequestImpl request, 
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request, jmsRequstProperties,jmsResponseProperties);

	}

	/**
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	public String rmBisRequests(
		PublishAutoDiscoveryRequestImpl request,
		Properties jmsRequstProperties,
		Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request,jmsRequstProperties,jmsResponseProperties);
	}
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object rmBisRequests(
			RetrieveCustomerTransportInfoRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request,jmsRequstProperties,jmsResponseProperties);

	}
	
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object rmBisRequests(
			QueryNetworkInventoryRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request,jmsRequstProperties,jmsResponseProperties);

	}
	
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object rmBisRequests(
			DisconnectServiceRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request,jmsRequstProperties,jmsResponseProperties);

	}
	
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object rmBisRequests(
			ModifyServiceRequestImpl request,
			Properties jmsRequstProperties,
			Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request,jmsRequstProperties,jmsResponseProperties);

	}
	/**
	 * @param request
	 * @param jmsRequstProperties
	 * @param jmsResponseProperties
	 * @return
	 * @throws ServiceException
	 */
	public Object rmBisRequests(
	ModifyFacilityInfoRequestImpl request,
	Properties jmsRequstProperties,
	Properties jmsResponseProperties)
		throws ServiceException
	{

		if (theXNGHelper == null)
		{
			theXNGHelper = new XNGHelper(theProperties, theLogger);
		}

		return theXNGHelper.rmBisRequests(request,jmsRequstProperties,jmsResponseProperties);

	}
}
