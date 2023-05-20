//$Id: ActionOnScreenResponseHelper.java,v 1.2 2011/04/07 02:48:19 rs278j Exp $
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

import java.util.ArrayList;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl.HeaderTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl.ProcessingActionTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl.ProcessingActionTypeImpl.GetFieldsTypeImpl;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenResponse.impl.JXAPITypeImpl.ProcessingActionTypeImpl.GetFieldsTypeImpl.FieldTypeImpl;
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
 * Contains the logic for handling response returned by the TIRKSJX API 
 * @author Julius Sembrano
 **/ 
public class ActionOnScreenResponseHelper 
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
	private String responseDate = null;
	private String responseTime = null;
	private String sessionID = null;
	private Field [] aFields = null;		

	/**
	 * Constructor for class ActionOnScreenResponseHelper
	 * 
	 * @param	com.sbc.bccs.utilities.Utility aUtility
	 * @param	java.util.Hashtable aProperties
	 */
	public ActionOnScreenResponseHelper(
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
	}
	
	/**
	 * Parses the TIRKSJX API response
	 * 
	 * @param	JXAPITypeImpl
	 * @param	java.util.Hashtable aProperties
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public void parseResponse(
			JXAPITypeImpl actionOnScreenResponse) 
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "ActionOnScreenResponseHelper::parseResponse()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		try
		{
			HeaderTypeImpl headerResponse = (HeaderTypeImpl)actionOnScreenResponse.getHeader();
			ProcessingActionTypeImpl processingAction = (ProcessingActionTypeImpl) actionOnScreenResponse.getProcessingAction();
			String responseDate = actionOnScreenResponse.getDate();
			String responseTime = actionOnScreenResponse.getTime();
			
			if (headerResponse != null)
			{
				if(headerResponse.getName() != null)
				{
					setHeaderName(headerResponse.getName());
				}
			}
			
			if (processingAction != null)
			{
				if(processingAction.getSessionID() != null)
				{
					setSessionID(processingAction.getSessionID());
				}
				if(processingAction.getGetFields() != null)				
				{
					GetFieldsTypeImpl getFields = (GetFieldsTypeImpl) processingAction.getGetFields();
					ArrayList aFieldsArray = new ArrayList();
					int getFieldsCount = getFields.getFieldCount().intValue();
					if(getFieldsCount > 0);
					{
						for (int i=0; i < getFieldsCount; i++ )
						{
							FieldTypeImpl aFieldTypeImpl = (FieldTypeImpl) getFields.getField().get(i);
							Field aField = new Field(utility, properties);
							aField.parseField(aFieldTypeImpl);	
							aFieldsArray.add(aField);
						}
						if(aFieldsArray.size() > 0)
						{
							aFields = (Field[]) aFieldsArray.toArray(new Field[aFieldsArray.size()]);
						}
					}
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
					"Error in parsing TIRKSJX Action On Screen Response " + e.getMessage(),
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
	 * get the conversationID
	 * 
	 * @return String
	 */
	public String getConversationID() 
	{
		return conversationID;
	}

	/**
	 * set the conversationID
	 * 
	 * @param String conversationID
	 */
	public void setConversationID(String conversationID) 
	{
		this.conversationID = conversationID;
	}

	/**
	 * get the conversation ID type
	 * 
	 * @return String
	 */
	public String getConversationTypeID() 
	{
		return conversationTypeID;
	}

	/**
	 * set the conversation ID type
	 * 
	 * @param String conversationTypeID
	 */
	public void setConversationTypeID(String conversationTypeID) 
	{
		this.conversationTypeID = conversationTypeID;
	}

	/**
	 * get the country
	 * 
	 * @return String
	 */
	public String getCountry() 
	{
		return country;
	}

	/**
	 * get the country
	 * 
	 * @param String country
	 */
	public void setCountry(String country) 
	{
		this.country = country;
	}

	/**
	 * get end of reply
	 * 
	 * @return String
	 */
	public String getEndOfReply() 
	{
		return endOfReply;
	}

	/**
	 * @param String endOfReply
	 */
	public void setEndOfReply(String endOfReply) 
	{
		this.endOfReply = endOfReply;
	}

	/**
	 * get the language
	 * 
	 * @return String
	 */
	public String getLanguage() 
	{
		return language;
	}

	/**
	 * set the language
	 * 
	 * @param String language
	 */
	public void setLanguage(String language) 
	{
		this.language = language;
	}

	/**
	 * get the performance
	 * 
	 * @return String
	 */
	public String getPerformance() 
	{
		return performance;
	}

	/**
	 * set the performance
	 * 
	 * @param String performance
	 */
	public void setPerformance(String performance) 
	{
		this.performance = performance;
	}

	/**
	 * get the priority
	 * 
	 * @return String
	 */
	public String getPriority() 
	{
		return priority;
	}

	/**
	 * set the priority
	 * 
	 * @param String priority
	 */
	public void setPriority(String priority) 
	{
		this.priority = priority;
	}

	/**
	 * get the security
	 * 
	 * @return String
	 */
	public String getSecurity() 
	{
		return security;
	}

	/**
	 * set the security
	 * 
	 * @param String security
	 */
	public void setSecurity(String security) 
	{
		this.security = security;
	}

	/**
	 * get the trace level
	 * 
	 * @return String
	 */
	public String getTraceLevel() 
	{
		return traceLevel;
	}

	/**
	 * set the trace level
	 * 
	 * @param String traceLevel
	 */
	public void setTraceLevel(String traceLevel) 
	{
		this.traceLevel = traceLevel;
	}

	/**
	 * get the trace name
	 * 
	 * @return String
	 */
	public String getTraceName() 
	{
		return traceName;
	}

	/**
	 * set the trace name
	 * 
	 * @param String traceName
	 */
	public void setTraceName(String traceName) 
	{
		this.traceName = traceName;
	}

	/**
	 * get the transaction ID
	 * 
	 * @return String
	 */
	public String getTransactionID() 
	{
		return transactionID;
	}

	/**
	 * set the transaction ID
	 * 
	 * @param String transactionID
	 */
	public void setTransactionID(String transactionID) 
	{
		this.transactionID = transactionID;
	}

	/**
	 * get the session ID
	 * 
	 * @return String
	 */
	public String getSessionID() 
	{
		return sessionID;
	}

	/**
	 * set the session ID
	 * 
	 * @param sessionID String
	 */
	public void setSessionID(String sessionID) 
	{
		this.sessionID = sessionID;
	}

	/**
	 * get the Fields
	 * 
	 * @return Field[]
	 */
	public Field[] getAFields() 
	{
		return aFields;
	}

	/**
	 * set the Fields
	 * 
	 * @param Field[] fields
	 */
	public void setAFields(Field[] fields) 
	{
		aFields = fields;
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
	 * set the response date
	 * 
	 * @param String responseDate
	 */
	public void setResponseDate(String responseDate) 
	{
		this.responseDate = responseDate;
	}
	
	/**
	 * get the response time
	 * 
	 * @return String
	 */
	public String getResponseTime() 
	{
		return responseTime;
	}
	
	/**
	 * set the response time
	 * 
	 * @param String responseTime
	 */
	public void setResponseTime(String responseTime) 
	{
		this.responseTime = responseTime;
	}
}
