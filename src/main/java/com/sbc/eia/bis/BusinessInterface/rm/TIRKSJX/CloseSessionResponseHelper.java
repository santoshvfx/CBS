//$Id: CloseSessionResponseHelper.java,v 1.2 2011/04/07 02:57:34 rs278j Exp $
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
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionResponse.impl.JXAPITypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionResponse.impl.JXAPITypeImpl.HeaderTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionResponse.impl.JXAPITypeImpl.SessionTypeImpl;
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
 * Contains the logic for handling the Close Session Response
 * 
 * @author js7440
 *
 */
public class CloseSessionResponseHelper 
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private String sessionID = null;
	private String sessionAPIMessage = null;
	private String headerName = null;
	private String headerVersion = null;
	private String headerSender = null;
	private String headerSenderRole = null;
	private String responseDate = null;
	private String responseTime = null;
	
	/**
	 * Constructor for class CloseSessionResponseHelper
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public CloseSessionResponseHelper(
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
	}

	/**
	 * parse the close session response
	 * 
	 * @param closeSessionResponse
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public void parseResponse(
			JXAPITypeImpl closeSessionResponse) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "CloseSessionResponseHelper::parseResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		HeaderTypeImpl headerResponse = (HeaderTypeImpl)closeSessionResponse.getHeader();
		SessionTypeImpl sessionResponse = (SessionTypeImpl)closeSessionResponse.getSession();
		
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
			}
			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in parsing TIRKSJX Close Session Response " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
	}

	/**
	 * get the header name
	 * 
	 * @return	String 
	 */
	public String getHeaderName() 
	{
		return headerName;
	}

	/**
	 * set the header name
	 * 
	 * @param String headerName
	 */
	public void setHeaderName(String headerName) 
	{
		this.headerName = headerName;
	}

	/**
	 * get the header sender
	 * 
	 * @return String
	 */
	public String getHeaderSender() 
	{
		return headerSender;
	}

	/**
	 * set the header sender
	 * 
	 * @param String headerSender
	 */
	public void setHeaderSender(String headerSender) 
	{
		this.headerSender = headerSender;
	}

	/**
	 * get the header sender role
	 * 
	 * @return String
	 */
	public String getHeaderSenderRole() 
	{
		return headerSenderRole;
	}

	/**
	 * set the header sender role
	 * 
	 * @param String headerSenderRole
	 */
	public void setHeaderSenderRole(String headerSenderRole) 
	{
		this.headerSenderRole = headerSenderRole;
	}

	/**
	 * get the header version
	 * 
	 * @return String
	 */
	public String getHeaderVersion() 
	{
		return headerVersion;
	}

	/**
	 * set the header version
	 * 
	 * @param String headerVersion
	 */
	public void setHeaderVersion(String headerVersion) 
	{
		this.headerVersion = headerVersion;
	}

	/**
	 * get the response date
	 * 
	 * @return String
	 */
	public String getResponseDate() 
	{
		return responseDate;
	}

	/**
	 * 
	 * 
	 * @param String responseDate
	 */
	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	public String getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}

	public String getSessionAPIMessage() {
		return sessionAPIMessage;
	}

	public void setSessionAPIMessage(String sessionAPIMessage) {
		this.sessionAPIMessage = sessionAPIMessage;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
}
