//$Id: SelfTestURLConnection.java,v 1.2 2011/04/08 18:21:44 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date       | Author              | Notes
//# --------------------------------------------------------------------
//# 05/28/2009   Julius Sembrano       Creation. DR 132786 - Added CPSOS and Granite SelfTest

package com.sbc.eia.bis.facades.rm.transactions.SelfTest;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

import com.att.it.granite.GetSITE_t;
import com.att.it.granite.QNIPortTypeProxy;
import com.att.it.granite.QueryNetworkInventoryRequest;
import com.att.it.granite.QueryNetworkInventoryResponse;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sun.java.util.collections.ArrayList;

public class SelfTestURLConnection 
{
	private String GRANITE = "Granite";
	private String CPSOS = "CPSOS";
	
	public SelfTestURLConnection(
			String fileName, 
			BisContext aContext,
			Hashtable aProperties, 
			BisLogger bisLogger, 
			ArrayList aResultList)
	{
		String myMethodName = "SelfTestURLConnection::SelfTestURLConnection()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
			
		String GraniteURL = null;
		String CPSOSURL = null;
		int CPSOSURLResponse;
		
		try
		{
			GraniteURL = (String)aProperties.get("GRANITE_SOAP_URL");
			
			if(testGraniteConnection(GraniteURL, aProperties, bisLogger))
			{
				bisLogger.log(LogEventId.INFO_LEVEL_1, 
						GRANITE + " Access tested successfully using address " + GraniteURL);
				SelfTestResult.addResultToList(
						"WEBSERVICES", 
						GRANITE + " Access tested successfully using address " + GraniteURL,
						true,
						bisLogger,
						aResultList);
			}//else Log Failure
			else
			{
				bisLogger.log(LogEventId.INFO_LEVEL_1, 
						GRANITE + " Access failed using addresss " + GraniteURL);
				SelfTestResult.addResultToList(
						"WEBSERVICES", 
						GRANITE + " Access failed using addresss " + GraniteURL,
						false,
						bisLogger,
						aResultList);
			}
		}
		catch(Exception e)
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, 
					GRANITE + " Access failed using addresss " + GraniteURL + e.getMessage());
			SelfTestResult.addResultToList(
					"WEBSERVICES", 
					GRANITE + " Access failed using addresss " + GraniteURL + e.getMessage(),
					false,
					bisLogger,
					aResultList);
		}

		try
		{
			CPSOSURL = (String)aProperties.get("CPSOS_URL");
			
			CPSOSURLResponse = connectToAddress(CPSOSURL, bisLogger);
			
			if(CPSOSURLResponse == HttpURLConnection.HTTP_OK)
			{
				bisLogger.log(LogEventId.INFO_LEVEL_1, 
						CPSOS + " Access tested successfully using address " + CPSOSURL + " HTTPResponse is: " + CPSOSURLResponse);
				SelfTestResult.addResultToList(
						"URL", 
						CPSOS + " Access tested successfully using address " + CPSOSURL + " HTTPResponse is: " + CPSOSURLResponse,
						true,
						bisLogger,
						aResultList);
			}//else Log Failure
			else
			{
				bisLogger.log(LogEventId.INFO_LEVEL_1, 
						CPSOS + " Access failed using addresss " + CPSOSURL + " HTTPResponse is: " + CPSOSURLResponse);
				SelfTestResult.addResultToList(
						"URL", 
						CPSOS + " Access failed using addresss " + CPSOSURL + " HTTPResponse is: " + CPSOSURLResponse,
						false,
						bisLogger,
						aResultList);
			}
		}
		catch(Exception e)
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, 
					CPSOS + " Access failed using addresss " + CPSOSURL + e.getMessage());
			SelfTestResult.addResultToList(
					"URL", 
					CPSOS + " Access failed using addresss " + CPSOSURL + e.getMessage(),
					false,
					bisLogger,
					aResultList);
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
	}

	/**
	 * @param address
	 * @param bisLogger
	 * @return int response
	 * @throws Exception
	 */
	private int connectToAddress(
			String address,  
			BisLogger bisLogger)
	throws Exception
	{

		String myMethodName = "SelfTestURLConnection::connectToAddress()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		URL url = null;
		URLConnection connection = null;
		HttpURLConnection httpConnection = null;
		int response = 0;
		
		try
		{
			url = new URL(address);
			
			connection = url.openConnection();
			
			httpConnection = (HttpURLConnection) connection;
			
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "Trying to connect...");
			
			httpConnection.connect();
			
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "Getting response from: " + address);
			
			response = httpConnection.getResponseCode();
			
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "Response: " + response);			
		}
		catch(Exception e)
		{
			throw new Exception(e);
		}
		finally
		{
			httpConnection.disconnect();
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		
		return response;		
	}
	
	private boolean testGraniteConnection(
			String GraniteURL, 
			Hashtable aProperties,
			BisLogger bisLogger)  throws Exception
	{
		String myMethodName = "SelfTestURLConnection::testGraniteConnection()";
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "Building dummy data for Granite...");
		boolean isConnnnectionSuccesful = false;
		QNIPortTypeProxy aQNIPortTypeProxy = new QNIPortTypeProxy();
		aQNIPortTypeProxy.setEndpoint(GraniteURL);
		QueryNetworkInventoryResponse aGraniteResp = null;
		GetSITE_t aGetSITE_t = new GetSITE_t();
		aGetSITE_t.setSiteType((String) aProperties.get("GRANITE_SITE_TYPE_VALUE"));
		aGetSITE_t.setQueryType((String) aProperties.get("GRANITE_QUERY_TYPE_VALUE"));
		QueryNetworkInventoryRequest QNIRequestObj = new QueryNetworkInventoryRequest();
		QNIRequestObj.setSite(aGetSITE_t);
		
		try
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "Sending dummy data to Granite...");
			aGraniteResp = aQNIPortTypeProxy.queryNetworkInventory(QNIRequestObj);
			isConnnnectionSuccesful = true;
		}
		catch(com.att.cio.WSException wse)
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "Expected Exception received from Granite...");
			isConnnnectionSuccesful = true;
		}
		catch(Exception e)
		{
			bisLogger.log(LogEventId.DEBUG_LEVEL_1, "Unexpected Exception received from Granite...");
			throw new Exception(e);
		}
		bisLogger.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		return isConnnnectionSuccesful;
	}
}
