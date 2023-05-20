// $Id: NetProvisionService.java,v 1.16 2005/02/11 23:47:51 ml2917 Exp $

//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2005.  All Rights Reserved.
//#
//# History :
//# Date      | Author        			| Notes
//# ----------------------------------------------------------------------------
//# 5/6/2004	Mark Liljequist			Creation.
//# 2/11/2005	Mark Liljequist 		Change transaction message.
//#
 
package com.sbc.eia.bis.embus.service.netprovision;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.netprovision.access.NetProvisionHelper;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.RequestTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.ExceptionTypeImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpRequestListImpl;
import com.sbc.eia.bis.embus.service.network.npconnector.rmbisrequests.interfaces.impl.NpResponseSeqTypeImpl;
import com.sbc.eia.bis.embus.service.npconnector.access.NpConnectorHelper;
import com.sbc.eia.bis.embus.service.npconnector.access.RmBisResponsesEvaluateException;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * @author ML2917
 *
 * NetProvisionService
 * This layer is used to provide access to the net provision resource.
 * Net Provision is accessed via NPConnector.
 * 
 * 
 */

public class NetProvisionService
{

	NpConnectorHelper theNpConnectorHelper;

	NetProvisionHelper theNetProvisionHelper;

	Properties theProperties;
	Logger theLogger;

	/**
	 * Method NetProvisionService.
	 *
	 */

	public NetProvisionService() throws ServiceException
	{

		theNpConnectorHelper = null;
		theNetProvisionHelper = null;

	}

	/**
	 * Method NetProvisionService.
	 * @param hashtable
	 * @param logger
	 */

	public NetProvisionService(Hashtable hash, Logger logger)
		throws ServiceException
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
	 * Method NetProvisionService.
	 * @param properties
	 * @param logger
	 */

	public NetProvisionService(Properties properties, Logger logger)
		throws ServiceException
	{

		this();

		theLogger = logger;
		theProperties = properties;

	}

	/**
	 * Method getServiceName.
	 * @param request
	 * @return String
	 */
	
	public String getServiceName(RequestTypeImpl request)
	{

		return "Net Provision";

	}
	
	/**
	 * Method getServiceName.
	 * @param request
	 * @return String
	 */
	
	public String getServiceName(NpRequestListImpl request)
	{

		return "NP Connector";

	}


	/**
	 * Method getSchenaVersion.
	 * @param request
	 * @return String
	 */
	
	public String getSchenaVersion(NpRequestListImpl request)
	{

		return "2.1.0";

	}

	/**
	 * Method getSchenaVersion.
	 * @param request
	 * @return String
	 */
	
	public String getSchenaVersion(RequestTypeImpl request)
	{

		return "20.0.5.000";

	}
	
	/**
	 * Method rmBisRequests.
	 * Send a request using the NpRequestListImpl object.
	 * @param NpRequestListImpl
	 * @return NpResponseSeqTypeImpl
	 * @throws ServiceException
	 */

	public NpResponseSeqTypeImpl rmBisRequests(NpRequestListImpl request)
		throws ServiceException
	{

		if (theNpConnectorHelper == null)
		{
			theNpConnectorHelper =
				new NpConnectorHelper(theProperties, theLogger);
		}

		NpResponseSeqTypeImpl response =
			theNpConnectorHelper.rmBisRequests(request);

		if (this.rmBisResponsesEvaluate(response))
		{

			theLogger.log(
				LogEventId.INFO_LEVEL_2,
				"Transaction status; " + response.getTransactionStatus());
			theLogger.log(
				LogEventId.INFO_LEVEL_2,
				"Transaction id; " + response.getTransactionId());

		}

		return response;

	}

	private boolean rmBisResponsesEvaluate(NpResponseSeqTypeImpl response)
		throws NetProvisionException
	{

		if (response.getNpResponse().iterator().hasNext())
		{

			return true;
		}

		Iterator iter = response.getException().iterator();

		if (iter.hasNext())
		{

			RmBisResponsesEvaluateException.evaluate(
				(ExceptionTypeImpl) iter.next());
		}

		return false;

	}

	/**
	 * Method rmBisRequests.
	 * Send a request using the RequestImpl object.
	 * @param RequestImpl
	 * @return ResponseTypeImpl
	 * @throws ServiceException
	 */

	public Object rmBisRequests(RequestTypeImpl request)
		throws ServiceException
	{

		if (theNetProvisionHelper == null)
		{
			theNetProvisionHelper =
				new NetProvisionHelper(theProperties, theLogger);
		}

		return theNetProvisionHelper.rmBisRequests(request);

	}

}
