//$Id: OMSHelper.java,v 1.7 2006/06/20 17:15:52 jp2854 Exp $

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
//# 6/20/2005 | Jinmin Ni           | Editted to accept encrypted password
//# 7/25/2005 | Jinmin Ni           | Add overload the one way method to use previously
//#                                 | persisted jms properties for asyn. transaction
//# 06/19/2005| Jyothi Jasti        | Added publishMessage method.

package com.sbc.eia.bis.embus.service.oms.access;

import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;

import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceAccess;
import com.sbc.eia.bis.embus.service.access.ServiceException;
import com.sbc.eia.bis.embus.service.decryption.DecryptionServiceHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.embus.common.EMBusNamingException;



/**
 * @author jn1936
 *
 */

public class OMSHelper extends DecryptionServiceHelper
{
	public final static String OMS_SERVICE_NAME = "OMS";
	public final static String OMS_REQUEST = "OmsRequest";
	public static final String OMS_VERSION = "1.0";


	//pre-defined valid JMS/EMBUS vs. bisContext tag mapping  
	
	//EMBUS_MESSAGE_TAG in bisContext
	public static final String EMBUS_MESSAGE_TAG = "embusMessageTag";

	//JMS_CORRELATION_ID in bisContext
	public static final String JMS_CORRELATION_ID = "JMSCorrelationID";

	// JMS_REPLY_TO_QUEUE in bisContext x500 format
	public static final String JMS_DESTINATION_NAME = "destinationName";
	
	
	Logger aLogger;
		
	/**
	 * Method OMSHelper.
	 * @param properties
	 * @param logger
	 * @throws ServiceException
	 */
	public OMSHelper(Hashtable properties, Logger logger)
		throws ServiceException
	{
		super(properties, logger, OMS_SERVICE_NAME);
		aLogger = logger;
	}

	/**
	 * Method rmBisRequests.
	 * @param inputXMLMsg
	 * @throws ServiceException
	 */
	public void rmBisRequests(String inputXMLMsg) throws ServiceException
	{
		getServiceAccess().send(OMS_REQUEST, inputXMLMsg);
	}

	/**
	 * @param inputXMLMsg
	 * @param jmsRequestPropertiesList
	 * @throws ServiceException
	 */
	public void rmBisRequests(
		String inputXMLMsg,
		Properties jmsRequestPropertiesList)
		throws ServiceException
	{
		getServiceAccess().send(OMS_REQUEST,inputXMLMsg, jmsRequestPropertiesList);
	}
	
	/**
	 * @param publishMessage
	 * @param jmsRequestPropertiesList
	 * @throws ServiceException
	 */
	public void publishMessage(
		String inputXMLMsg,
		Properties jmsRequestPropertiesList)
		throws ServiceException
	{
		getServiceAccess().send(OMS_REQUEST, inputXMLMsg, jmsRequestPropertiesList);
	}
	
	/**
	 * @param inputXMLMsg
	 * @param jmsRequestPropertiesList
	 * @param override
	 * @throws ServiceException
	 */
	public void rmBisRequests(
		String inputXMLMsg,
		Properties jmsRequestPropertiesList,
		Properties override)
		throws ServiceException
	{
		//reset the jms/embus tag with given values

		ServiceAccess access = getServiceAccess();
		boolean retry = true;
		
		if (override == null)
		{
			rmBisRequests(inputXMLMsg, jmsRequestPropertiesList);
		}
		else
		{
			try
			{
				//remove whitespace for each token and check if the destinationName 
				//include the "t=jms,c=us". if does, take it out
				String destinationName = override.getProperty(JMS_DESTINATION_NAME);
				
				if (destinationName != null)
				{
					destinationName = removeTokenWhiteSpace(destinationName);
					if (destinationName.endsWith(",t=jms,c=us"))
					{
						String trimDestination =
							destinationName.trim().substring(
								0,
								destinationName.indexOf(",t=jms,c=us"));
						override.put(JMS_DESTINATION_NAME, trimDestination);
						try
						{
							aLogger.log(
								LogEventId.INFO_LEVEL_1,
								"Send to [" + trimDestination + "]");
							access.send(
								OMS_REQUEST,
								inputXMLMsg,
								jmsRequestPropertiesList,
								override);
						}
						catch (ServiceException e)
						{
							//if exception is look up failure, try with ",t=jms,c=us"
							if (isLDAPQueueLookupException(e))
							{
								override.put(JMS_DESTINATION_NAME, destinationName);
								//if look up falied again, retry with default queue
								aLogger.log(
									LogEventId.INFO_LEVEL_1,
									e.getMessage()+" | try [" + destinationName+"]");
								access.send(
									OMS_REQUEST,
									inputXMLMsg,
									jmsRequestPropertiesList,
									override);
							}
							else
							{
								//some other service exceptioin. do not retry with default queue
								retry = false;
								throw e;
							}
						}
					}
					else
					{
						//retry with default queue only if look up failed
						aLogger.log(
							LogEventId.INFO_LEVEL_1,
							"Send to [" + destinationName+"]");
						access.send(
							OMS_REQUEST,
							inputXMLMsg,
							jmsRequestPropertiesList,
							override);
					}
				}
				else
				{
					//no retry with default queue since it is using default queue already
					retry = false;
					aLogger.log(
						LogEventId.INFO_LEVEL_1,
						"No destinationName in overrride properties. Send to default queue on config xml file ");
					access.send(
						OMS_REQUEST,
						inputXMLMsg,
						jmsRequestPropertiesList,
						override);
				}

			}
			catch (ServiceException e)
			{
				if(retry == false || isLDAPQueueLookupException(e) == false)
				{
					throw e;	
				}
				
				//use default queue if look up failed
				aLogger.log(
					LogEventId.INFO_LEVEL_1,
					e.getMessage() + " | try default queue...");
				override.remove(JMS_DESTINATION_NAME);
				access.send(
						OMS_REQUEST,
						inputXMLMsg,
						jmsRequestPropertiesList,
						override);
			}
		}
	}
	
	private boolean isLDAPQueueLookupException(ServiceException e) 
	{
		boolean isQLookUpFailed = false;
		//check if nested exception is caused by LDAP Queue look up failed with ldap error code 32
		String errorMsg = e.getMessage();
		if (e.getOriginalException() instanceof EMBusNamingException
			&& errorMsg != null
			&& (errorMsg.indexOf("LDAP: error code 34") != -1 || errorMsg.indexOf("LDAP: error code 32") != -1 ))
		{
			isQLookUpFailed = true;
		}
		return isQLookUpFailed;
	}

	private String removeTokenWhiteSpace(String input)
	{
		StringBuffer sf = new StringBuffer();
		StringTokenizer tokenizer = new StringTokenizer(input, ",");
		boolean isFirst = true;
		while (tokenizer.hasMoreTokens())
		{
			if (isFirst == false)
			{
				sf.append(",");
			}
			else
			{
				isFirst = false;	
			}
			sf.append(tokenizer.nextToken().trim());

		}
		return sf.toString();
	}
}
