//$Id: SelfTestTIRKSJXConnection.java,v 1.1 2011/04/08 17:21:06 rs278j Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of ATT Services Inc. and authorized Affiliates of ATT Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 ATT Knowledge Ventures, L.P. All rights reserved.
//#
//############################################################################

package com.sbc.eia.bis.facades.rm.transactions.SelfTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.CloseSessionResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.OpenSessionResponseHelper;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.TIRKSJX;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX.TIRKSJXConstants;
import com.sbc.eia.bis.framework.logging.BisLogger;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;
import com.sun.java.util.collections.ArrayList;
import com.sbc.bccs.utilities.Utility;

/**
 * Class for testing the connection to TIRKSJX API
 * 
 * @author js7440
 */
public class SelfTestTIRKSJXConnection implements TIRKSJXConstants
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private Properties TIRKSJXProperties = null;
	public final String WestDataCenter = "TIRKSJX.NV";
	public final String SouthWestDataCenter = "TIRKSJX.MO";
	public final String MidWestDataCenter = "TIRKSJX.IL";
	public final String EastDataCenter = "TIRKSJX.CT";
	
	

	public SelfTestTIRKSJXConnection(
			BisContext context, 
			Hashtable aProperties, 
			Utility aUtility, 
			BisLogger bisLogger, 
			ArrayList aResultList)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
    {	
		utility = aUtility;
		properties = aProperties;
		
		String openSessionResponse = null;
		String sessionID = null;
		String selfTestMessage = null;
		String dataCenters[] ={WestDataCenter, SouthWestDataCenter, MidWestDataCenter, EastDataCenter};
		boolean isSelfTestSuccessful = false;
		TIRKSJX TIRKSJXHelper = new TIRKSJX(utility, properties);
		
	    try
	    {
	    	loadTIRKSJxProperties();
	    	
	    	while(isSelfTestSuccessful != true)	    	
	    	{
	    		for(int i=0; i<dataCenters.length; i++)
	    		{
	    			try
	    			{
				    	com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl openSessionRequest = 
				    		TIRKSJXHelper.buildTIRKSJxOpenSessionRequest((String)TIRKSJXProperties.get(dataCenters[i]));
				    	openSessionResponse = TIRKSJXHelper.sendOpenSessionRequest(openSessionRequest);
				    	
						OpenSessionResponseHelper openSessionResponseHelper =  TIRKSJXHelper.parseOpenSessionResponse(openSessionResponse);
						sessionID = openSessionResponseHelper.getSessionID();
						
						selfTestMessage = "TIRKSJX Access Tested Successfully." + " Session ID is " + sessionID;
						isSelfTestSuccessful = true;
						bisLogger.log(LogEventId.INFO_LEVEL_1, selfTestMessage);
						break;
	    			}
	    			catch(Exception e)
	    			{
	    				selfTestMessage = "TIRKSJX Access Failed";
	    				isSelfTestSuccessful = false;
	    				bisLogger.log(LogEventId.INFO_LEVEL_1, selfTestMessage);
	    			}
	    		}
	    	}		
	    }
	    finally
	    {
			SelfTestResult.addResultToList(
					"TIRSKJX",
					selfTestMessage,
					isSelfTestSuccessful,
					bisLogger,
					aResultList);	
			if(sessionID != null)
			{
				com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl closeSessionRequest = TIRKSJXHelper.buildTIRKSJxCloseSessionRequest(sessionID);
				String closeSessionResponse = TIRKSJXHelper.sendCloseSessionRequest(closeSessionRequest);
				// Parse the Close session response
				CloseSessionResponseHelper closeSessionResponseHelper = TIRKSJXHelper.parseCloseSessionResponse(closeSessionResponse);
				
				if(closeSessionResponseHelper != null)
				{
					if(closeSessionResponseHelper.getSessionAPIMessage() != null &&
							closeSessionResponseHelper.getSessionAPIMessage().equalsIgnoreCase(CLOSE_SESSION_SUCCESS))
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " " + CLOSE_SESSION_SUCCESS);
						
						sessionID = null;
					}
					else
					{
						utility.log(
								LogEventId.INFO_LEVEL_2,
								closeSessionResponseHelper.getSessionID().trim() + " not closed successfully. " 
									+ closeSessionResponseHelper.getSessionAPIMessage());
					}
				}
			}	
	    }
    }
	
	/**
	 * Loads the TIRSKJX property file
	 * 
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	private void loadTIRKSJxProperties()
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound 
	{
		String myMethodName = "TIRKSJx::loadTIRKSJxProperties()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
	
		// The file contains the TIRKSJx Properties 
		String tirksJxFile = (String)properties.get("TIRKSJX_PROPERTY_FILE");
		
		if (tirksJxFile == null || tirksJxFile.length() < 1)
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE_PROPERTY,
				"TIRKSJX_PROPERTY_FILE property not found/not set",
				myMethodName,
				Severity.UnRecoverable);
		}
		
		utility.log(
			LogEventId.DEBUG_LEVEL_2,
			"Loaded TIRKSJx Properties from: <"
				+ tirksJxFile
				+ ">");
		
		try 
		{
			TIRKSJXProperties = new Properties();
			
			PropertiesFileLoader.read(
				TIRKSJXProperties,
				tirksJxFile,
				utility);
		
			utility.log(
				LogEventId.DEBUG_LEVEL_2,
				("Loaded "
					+ TIRKSJXProperties.size()
					+ " TIRKSJx properties"));
		
			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);			
		} 
		catch (Exception e) 
		{
			utility.throwException(
				ExceptionCode.ERR_RM_TIRKS_STATE_IMS_REGION_MAP_FILE,
				"FileNotFoundException: " + e.getMessage(),
				myMethodName,
				Severity.UnRecoverable);
		} 
	}	
}
