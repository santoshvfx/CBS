//$Id: OpenSessionResponseHelper.java,v 1.2 2011/04/07 03:07:32 rs278j Exp $
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

package com.sbc.eia.bis.BusinessInterface.rm.TIRKSJX;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionResponse.impl.JXAPITypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionResponse.impl.JXAPITypeImpl.HeaderTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionResponse.impl.JXAPITypeImpl.SessionTypeImpl;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

/**
 * Contains the logic for handling the Open Session response
 * 
 * @author js7440
 */
public class OpenSessionResponseHelper 
{	
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private String headerName = null;
	private String headerVersion = null;
	private String headerSender = null;
	private String headerSenderRole = null;
	private String sessionID = null;
	private String sessionAPIMessage = null;
	private String sessionRelease = null;
	private String responseDate = null;
	private String responseTime = null;

	/**
	 * Constructor for class OpenSessionResponseHelper
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public OpenSessionResponseHelper(
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
	}
	
	/**
	 * parse the Open Session response
	 * 
	 * @param JXAPITypeImpl openSessionResponse
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public void parseResponse(
			JXAPITypeImpl openSessionResponse) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "OpenSessionResponseHelper::parseResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		HeaderTypeImpl headerResponse = (HeaderTypeImpl)openSessionResponse.getHeader();
		SessionTypeImpl sessionResponse = (SessionTypeImpl)openSessionResponse.getSession();
		String responseDate = openSessionResponse.getDate();
		String responseTime = openSessionResponse.getTime(); 
		
		try
		{
			if(headerResponse != null)
			{
				if(headerResponse.getName() != null)
				{
					setHeaderName(headerResponse.getName());
				}
				
				if(headerResponse.getVersion() != null)
				{
					setHeaderName(headerResponse.getVersion());
				}
				
				if(headerResponse.getSender() != null)
				{
					setHeaderName(headerResponse.getSender());
				}
				
				if(headerResponse.getSenderRole() != null)
				{
					setHeaderName(headerResponse.getSenderRole());
				}
			}
			if (sessionResponse != null)
			{
				if(sessionResponse.getSessionID() != null)
				{
					setSessionID(sessionResponse.getSessionID());
				}
				
				if(sessionResponse.getAPIMessage() != null)
				{
					setSessionAPIMessage(sessionResponse.getAPIMessage());
				}
	
				if(sessionResponse.getRelease()!= null)
				{
					setRelease(sessionResponse.getRelease());
				}
			}
			if(responseDate != null)
			{
				setResponseDate(responseDate);
			}
			
			if(responseTime != null)
			{
				setResponseTime(responseTime);
			}
			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in parsing TIRKSJX Open Session Response " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
	}

	/**
	 * get the Session ID
	 * 
	 * @return String
	 */
	public String getSessionID() 
	{
		return sessionID;
	}

	/**
	 * set the Session ID
	 * 
	 * @param String sessionID
	 */
	public void setSessionID(String sessionID) 
	{
		this.sessionID = sessionID;
	}

	/**
	 * get the API Message
	 * 
	 * @return String
	 */
	public String getAPIMessage() 
	{
		return sessionAPIMessage;
	}

	/**
	 * set the API Message
	 * 
	 * @param String message
	 */
	public void setAPIMessage(String message) 
	{
		this.sessionAPIMessage = message;
	}

	/**
	 * get the Release
	 * 
	 * @return String
	 */
	public String getRelease() 
	{
		return sessionRelease;
	}

	/**
	 * set the Release
	 * 
	 * @param String release
	 */
	public void setRelease(String release) 
	{
		this.sessionRelease = release;
	}

	/**
	 * get the Header Name
	 * 
	 * @return String
	 */
	public String getHeaderName() 
	{
		return headerName;
	}

	/**
	 * set the Header Name
	 * 
	 * @param String headerName
	 */
	public void setHeaderName(String headerName) 
	{
		this.headerName = headerName;
	}

	/**
	 * set the Sender
	 * 
	 * @return String
	 */
	public String getHeaderSender() 
	{
		return headerSender;
	}

	/**
	 * set the Sender
	 * 
	 * @param String headerSender
	 */
	public void setHeaderSender(String headerSender) 
	{
		this.headerSender = headerSender;
	}

	/**
	 * get the Sender Role
	 * 
	 * @return String
	 */
	public String getHeaderSenderRole() 
	{
		return headerSenderRole;
	}

	/**
	 * set the Header Sender Role
	 * 
	 * @param String headerSenderRole
	 */
	public void setHeaderSenderRole(String headerSenderRole) 
	{
		this.headerSenderRole = headerSenderRole;
	}

	/**
	 * get the Header Version
	 * 
	 * @return String
	 */
	public String getHeaderVersion() 
	{
		return headerVersion;
	}

	/**
	 * set the Header Version
	 * 
	 * @param String headerVersion
	 */
	public void setHeaderVersion(String headerVersion) 
	{
		this.headerVersion = headerVersion;
	}

	/**
	 * get the Session API Message
	 * 
	 * @return String
	 */
	public String getSessionAPIMessage() 
	{
		return sessionAPIMessage;
	}

	/**
	 * set teh Session aPI Message
	 * 
	 * @param String sessionAPIMessage
	 */
	public void setSessionAPIMessage(String sessionAPIMessage) 
	{
		this.sessionAPIMessage = sessionAPIMessage;
	}

	/**
	 * get the Session Release
	 * 
	 * @return String
	 */
	public String getSessionRelease() 
	{
		return sessionRelease;
	}

	/**
	 * set the Session Release
	 * 
	 * @param String sessionRelease
	 */
	public void setSessionRelease(String sessionRelease) 
	{
		this.sessionRelease = sessionRelease;
	}

	/**
	 * get the Response Date
	 * 
	 * @return String
	 */
	public String getResponseDate() 
	{
		return responseDate;
	}

	/**
	 * set the Response Date
	 * 
	 * @param String responseDate
	 */
	public void setResponseDate(String responseDate) 
	{
		this.responseDate = responseDate;
	}

	/**
	 * get the Response Time
	 * 
	 * @return String
	 */
	public String getResponseTime() 
	{
		return responseTime;
	}

	/**
	 * set the Response Time
	 * 
	 * @param String responseTime
	 */
	public void setResponseTime(String responseTime) 
	{
		this.responseTime = responseTime;
	}
}
