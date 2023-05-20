//$Id: ErrorOnScreenResponseHelper.java,v 1.3 2011/05/07 01:49:52 rs278j Exp $
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

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl.ProcessingActionTypeImpl.GetFieldsTypeImpl.FieldTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxErrorOnScreenResponse.impl.JXAPITypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxErrorOnScreenResponse.impl.JXAPITypeImpl.HeaderTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxErrorOnScreenResponse.impl.JXAPITypeImpl.ErrorReturnTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxErrorOnScreenResponse.impl.JXAPITypeImpl.ErrorReturnTypeImpl.ErrorTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.ObjectFactory;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.JXAPIType.HeaderType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.JXAPIType.SessionType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxNewSessionRequest.impl.JXAPIImpl;
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
 * Contains the logic for handling the Error on Screen response
 * 
 * @author js7440
 */
public class ErrorOnScreenResponseHelper 
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	private String headerName = null;
	private String headerVersion = null;
	private String headerSender = null;
	private String headerSenderRole = null;
	private String conversationTypeID = null;
	private String conversationID = null;
	private String transactionID = null;
	private String security = null;
	private String priority = null;
	private String language = null;
	private String country = null;
	private String traceLevel = null;
	private String traceName = null;
	private String performance = null;
	private String endOfReply = null;
	private String sessionID = null;
	private String dataCenter = null;
	private String screen = null;
	private String responseDate = null;
	private String responseTime = null;
	private TIRKSJXError [] tirksErrors = null;
	
	/**
	 * Constructor for class ErrorOnScreenResponseHelper
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public ErrorOnScreenResponseHelper (
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
	}
	
	/**
	 * parse the Error On Screen response
	 * 
	 * @param JXAPITypeImpl errorOnScreenResponse
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public void parseResponse(
			JXAPITypeImpl errorOnScreenResponse) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "ErrorOnScreenResponseHelper::parseResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try
		{
			HeaderTypeImpl headerResponse = (HeaderTypeImpl)errorOnScreenResponse.getHeader();
			ErrorReturnTypeImpl errorReturn = (ErrorReturnTypeImpl) errorOnScreenResponse.getErrorReturn();
			String responseDate = errorOnScreenResponse.getDate();
			String responseTime = errorOnScreenResponse.getTime();
			
			if (headerResponse != null)
			{
				if(headerResponse.getName() != null)
				{
					this.headerName = headerResponse.getName();
				}				
				if(headerResponse.getConversationID() != null)
				{
					this.conversationID = headerResponse.getConversationID();
				}
				if(headerResponse.getConversationTypeID() != null)
				{
					this.conversationTypeID = headerResponse.getConversationTypeID();
				}
				if(headerResponse.getCountry() != null)
				{
					this.country = headerResponse.getCountry();
				}
				if(headerResponse.getEndOfReply() != null)
				{
					this.endOfReply = headerResponse.getEndOfReply();
				}
				if(headerResponse.getLanguage() != null)
				{
					this.language = headerResponse.getLanguage();
				}
				if(headerResponse.getPerformance() != null)
				{
					this.performance = headerResponse.getPerformance();
				}
				if(headerResponse.getPriority() != null)
				{
					this.priority = headerResponse.getPriority();
				}
				if(headerResponse.getSecurity() != null)
				{
					this.security = headerResponse.getSecurity();
				}
				if(headerResponse.getSender() != null)
				{
					this.headerSender = headerResponse.getSender();
				}
				if(headerResponse.getSenderRole() != null)
				{
					this.headerSenderRole = headerResponse.getSenderRole();
				}
				if(headerResponse.getTraceLevel() != null)
				{
					this.traceLevel = headerResponse.getTraceLevel();
				}
				if(headerResponse.getTraceName() != null)
				{
					this.traceName = headerResponse.getTraceName();
				}
				if(headerResponse.getTransactionID() != null)
				{
					this.transactionID = headerResponse.getTransactionID();
				}
				if(headerResponse.getVersion() != null)
				{
					this.headerVersion = headerResponse.getVersion();
				}
			}
			
			if (errorReturn != null)
			{
				if(errorReturn.getSessionID() != null)
				{
					this.sessionID = errorReturn.getSessionID();
				}
				if(errorReturn.getDataCenter() != null)				
				{
					this.dataCenter = errorReturn.getDataCenter();
				}
				if(errorReturn.getScreen() != null)				
				{
					this.screen = errorReturn.getScreen();
				}
				if(errorReturn.getError() != null)				
				{
					ArrayList aErrorArray = new ArrayList();
					int errorTypeCount = errorReturn.getError().size();
					if(errorTypeCount > 0);
					{
						for (int i=0; i < errorTypeCount; i++ )
						{
							ErrorTypeImpl aErrorTypeImpl = (ErrorTypeImpl) errorReturn.getError().get(i);
							TIRKSJXError error = new TIRKSJXError(utility, properties);
							error.setErrorType(aErrorTypeImpl.getType());
							error.setErrorMessage(aErrorTypeImpl.getMessage());
							aErrorArray.add(error);
						}
						if(aErrorArray.size() > 0)
						{
							tirksErrors = (TIRKSJXError[]) aErrorArray.toArray(new TIRKSJXError[aErrorArray.size()]);
						}
					}
				}
			}
			if(responseDate != null)
			{
				this.setResponseDate(responseDate);
			}
			if(responseTime != null)
			{
				this.setResponseTime(responseTime);
			}
			utility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
		}
		catch(Exception e)
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
	
	}
	
	/**
	 * get the conversation ID
	 * 
	 * @return String
	 */
	public String getConversationID() 
	{
		return conversationID;
	}
	
	/**
	 * Set the conversation ID
	 * 
	 * @param conversationID
	 */
	public void setConversationID(String conversationID) 
	{
		this.conversationID = conversationID;
	}
	
	/**
	 * get the conversation ID Type
	 * 
	 * @return String
	 */
	public String getConversationTypeID() 
	{
		return conversationTypeID;
	}
	
	/**
	 * set the Conversation ID Type
	 * 
	 * @param String conversationTypeID
	 */
	public void setConversationTypeID(String conversationTypeID) 
	{
		this.conversationTypeID = conversationTypeID;
	}
	
	/**
	 * get the Country
	 * 
	 * @return String
	 */
	public String getCountry() 
	{
		return country;
	}
	
	/**
	 * set the Country
	 * 
	 * @param String country
	 */
	public void setCountry(String country) 
	{
		this.country = country;
	}
	
	/**
	 * get the Data Center
	 * 
	 * @return String
	 */
	public String getDataCenter() 
	{
		return dataCenter;
	}
	
	/**
	 * set the Data Center
	 * 
	 * @param String dataCenter
	 */
	public void setDataCenter(String dataCenter) 
	{
		this.dataCenter = dataCenter;
	}
	
	/**
	 * get the End Of Reply
	 * 
	 * @return String
	 */
	public String getEndOfReply() 
	{
		return endOfReply;
	}
	
	/**
	 * set the End Of Reply
	 * 
	 * @param String endOfReply
	 */
	public void setEndOfReply(String endOfReply) 
	{
		this.endOfReply = endOfReply;
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
	 * get the Sender
	 * 
	 * @return String
	 */
	public String getHeaderSender() 
	{
		return headerSender;
	}
	
	/**
	 * Set the Sender
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
	 * set the Sender Role
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
	 * get the Language
	 * 
	 * @return String
	 */
	public String getLanguage() 
	{
		return language;
	}
	
	/**
	 * set the Language
	 * 
	 * @param lString anguage
	 */
	public void setLanguage(String language) 
	{
		this.language = language;
	}
	
	/**
	 * get the Performance
	 * 
	 * @return String 
	 */
	public String getPerformance() 
	{
		return performance;
	}
	
	/**
	 * set the Performance
	 * 
	 * @param String performance
	 */
	public void setPerformance(String performance) 
	{
		this.performance = performance;
	}
	
	/**
	 * get the Priority
	 * 
	 * @return String
	 */
	public String getPriority() 
	{
		return priority;
	}
	/**
	 * set the Priority
	 * 
	 * @param String priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	/**
	 * get the Screen
	 * 
	 * @return String
	 */
	public String getScreen() 
	{
		return screen;
	}
	
	/**
	 * set the Screen
	 * 
	 * @param String screen
	 */
	public void setScreen(String screen) 
	{
		this.screen = screen;
	}
	
	/**
	 * get the Security
	 * 
	 * @return String
	 */
	public String getSecurity() 
	{
		return security;
	}
	
	/**
	 * set the Security
	 * 
	 * @param String security
	 */
	public void setSecurity(String security) 
	{
		this.security = security;
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
	 * get the TIRKS Errors
	 * 
	 * @return TIRKSJXError[]
	 */
	public TIRKSJXError[] getTirksErrors() 
	{
		return tirksErrors;
	}
	
	/**
	 * set the TIRKS Errors
	 * 
	 * @param TIRKSJXError[] tirksErrors
	 */
	public void setTirksErrors(TIRKSJXError[] tirksErrors) 
	{
		this.tirksErrors = tirksErrors;
	}
	
	/**
	 * get the Trace Level
	 * 
	 * @return String
	 */
	public String getTraceLevel() 
	{
		return traceLevel;
	}
	
	/**
	 * set the Trace Level
	 * 
	 * @param String traceLevel
	 */
	public void setTraceLevel(String traceLevel) 
	{
		this.traceLevel = traceLevel;
	}
	
	/**
	 * get the Trace Name
	 * 
	 * @return String
	 */
	public String getTraceName() 
	{
		return traceName;
	}
	
	/**
	 * set the Trace Name
	 * 
	 * @param String traceName
	 */
	public void setTraceName(String traceName) 
	{
		this.traceName = traceName;
	}
	
	/**
	 * get the Transaction ID
	 * 
	 * @return String
	 */
	public String getTransactionID() 
	{
		return transactionID;
	}
	
	/**
	 * set the Transaction ID
	 * 
	 * @param String transactionID
	 */
	public void setTransactionID(String transactionID) 
	{
		this.transactionID = transactionID;
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