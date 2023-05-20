//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of AT&T Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       AT&T Services, Inc.
//#
//#       (C) AT&T Services, Inc 2007  All Rights Reserved.
//#
//# History :
//# Date      | Author        			| Notes
//# ----------------------------------------------------------------------------
//# 2/1/2007     Changchuan Yin           Creation
//# 2/25/2007    Changchuan Yin           Added service name and version.
//# 4/05/2007    Changchuan Yin           Removed two unrelated import packages

 
package com.sbc.eia.bis.embus.service.codes;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import com.sbc.bccs.utilities.Logger;
import com.sbc.eia.bis.embus.service.access.ServiceException;
//import com.sbc.eia.bis.embus.service.npconnector.access.NpConnectorHelper;
//import com.sbc.eia.bis.embus.service.npconnector.access.RmBisResponsesEvaluateException;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.GetClliCICAvailRequest;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceRequest.impl.*;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.ResponseMessage;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.impl.*;
import com.sbc.eia.bis.embus.service.codes.access.CodesRCAccessHelper;
import com.sbc.eia.bis.framework.logging.LogEventId;

public class CodesRCAccessService
{

	CodesRCAccessHelper theCodesHelper;

    Properties theProperties;
	Logger theLogger;
	

	private String serviceName  = null;
	private String version = null;


    public CodesRCAccessService() throws ServiceException
	{

		theCodesHelper = null;

		serviceName  = "CODESRCAccess" ;
		version = "1.0" ;

		
   }

   public CodesRCAccessService(Hashtable hash, Logger logger)
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

	
	public CodesRCAccessService(Properties properties, Logger logger)
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
	
	public String getServiceName()
	{

		return serviceName;

	}
	
   
	public String getServiceVersion()
	{

		return version;

	}

	public ResponseMessage rmBisRequests(GetClliCICAvailRequest request)
		throws ServiceException
	{
		
		if (theCodesHelper == null)
		{
			theCodesHelper =
				new CodesRCAccessHelper(theProperties, theLogger);
		}

		theLogger.log(
						LogEventId.INFO_LEVEL_2,
						"Before sending request.");
						
		ResponseMessage response =
			theCodesHelper.rmBisRequests(request);
			
		theLogger.log(
						LogEventId.INFO_LEVEL_2,
						"After receiving response.");

		return response;

	}



}
