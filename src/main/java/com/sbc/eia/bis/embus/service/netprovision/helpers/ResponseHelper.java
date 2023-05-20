//$Id: ResponseHelper.java,v 1.5 2007/07/23 19:55:29 ml2917 Exp $
//###############################################################################
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
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//# 10/06/2004   Jinmin Ni      Creation.
//# 10/22/2004   Stevan Dunkin  Added static final variable FAILED_NOTSTARTED.
//# 10/22/2004	 Jinmin Ni		modify to handle expection opt when failed response 
//#								item come back from netp
//# 10/25/2004   Jinmin Ni		Correct the getter method name of aExceptionDataOpt
//# 10/26/2004   Jinmin Ni &    modify for exception handling 
//#    			 Stevan Dunkin		
//# 10/27/2004   Jinmin Ni		changed to fix the response code concat error 
//# 10/28/2004   Jinmin Ni		changed to fix retrieve multiple response item error
//# 11/3/2004    Jinmin Ni		added java doc and remove commented out coded. 
//# 11/03/2004	 Mark Liljequist Add MsgId as an instance.
//#								 Change break logic for comma add.
//#								 Add log for response code list.
//# 12/20/2004   Jinmin ni      Add logic to handle unexpected NotStarted response Code for
//#                             single request.   
//# 01/05/2004   Jinmin Ni &    Modified to handle exceptionResponse from NetP 
//#              Stevan Dunkin
//# 02/03/2005   Jinmin Ni      RM185978: Add validation for responseCode and response number
//# 04/15/2005   Jinmin Ni	    Copy form netprovision.utities 
//# 08/08/2005   Jinmin Ni		Get control back from exceptionBuilder by setting the throwException=false
//#                             instead of catching exception from it.  Passing error message from NetP as 
//#                             aText instead of msg to exceptionBuilder


package com.sbc.eia.bis.embus.service.netprovision.helpers;
import java.util.ArrayList;
import java.util.Iterator;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.ErrorMessage;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ExceptionResponseImpl;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseImpl;
import com.sbc.eia.bis.embus.service.netprovision.interfaces.impl.ResponseItemImpl;
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
import com.sbc.eia.idl.types.ExceptionDataOpt;
import com.sbc.eia.idl.types.Severity;

/**
 * @author jn1936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ResponseHelper
{
	public static final String SUCCEEDED = "Succeeded";
	public static final String FAILED = "Failed";
	public static final String PENDING = "Pending";
	public static final String NOTSTARTED = "NotStarted";
	public static final String SUCCEEDED_SUCCEEDED = "Succeeded,Succeeded";
	public static final String SUCCEEDED_FAILED = "Succeeded,Failed";
	public static final String SUCCEEDED_PENDING = "Succeeded,Pending";
	public static final String FAILED_NOTSTARTED = "Failed,NotStarted";
	public static final String SUCCEEDED_SUCCEEDED_SUCCEEDED =
		"Succeeded,Succeeded,Succeeded";
	public static final String SUCCEEDED_SUCCEEDED_PENDING =
		"Succeeded,Succeeded,Pending";
	public static final String SUCCEEDED_SUCCEEDED_FAILED =
		"Succeeded,Succeeded,Failed";
	public static final String SUCCEEDED_PENDING_NOTSTARTED =
		"Succeeded,Pending,NotStarted";
	public static final String SUCCEEDED_FAILED_NOTSTARTED =
		"Succeeded,Failed,NotStarted";
	public static final String PENDING_NOTSTARTED_NOTSTARTED =
		"Pending,NotStarted,NotStarted";
	public static final String FAILED_NOTSTARTED_NOTSTARTED =
		"Failed,NotStarted,NotStarted";

	String myMethodName = "ResponseHelper";
	ArrayList serviceItemList = null;
	Object response = null;
	String responseCode = null;
	String ruleFile = null;
	ExceptionDataOpt aExceptionDataOpt = null;
	Iterator serviceItemIterator = null;
	String bis_name = null;
    
	private String theMsgId;

	/**
	 * Class ResponseHelper.
	 * @param ruleFile
	 * @param response
	 */

	public ResponseHelper(String ruleFile, Object response, String bis_name)
	{
		this.bis_name = bis_name;
		this.response = response;
		serviceItemList = new ArrayList();
		this.ruleFile = ruleFile;
		aExceptionDataOpt =
			(ExceptionDataOpt) IDLUtil.toOpt(ExceptionDataOpt.class, null);
	}
	/**
	 * Method setResponse.
	 * @param response
	 */

	public void setResponse(Object response)
	{
		this.response = response;
	}
	
	public Object getResponse()
	{
		return response;
	}
	
	/**
	 * Method getServiceItemList.
	 * @return ArrayList
	 */

	public ArrayList getServiceItemList()
	{
		return serviceItemList;
	}
	/**
	 * Method getResponseCode.
	 * @return String
	 */

	public String getResponseCode()
	{
		return responseCode;
	}
	/**
	 * Method getNextServiceItem.
	 * @return ServiceItem
	 */

	public ServiceItem getNextServiceItem()
	{
		return (ServiceItem) serviceItemIterator.next();
	}
	/**
	 * Method hasNext.
	 * @return boolean
	 */

	public boolean hasNext()
	{
		return serviceItemIterator.hasNext();
	}

	/**
	* Method getMsgId.
	* @return String
	*/

	public String getMsgId()
	{
		return theMsgId;
	}

	private ResponseImpl checkResponse(BisContext aContext, Utility aUtility)
		throws
			NullResourcesForServiceException,
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		if (response.getClass() != ResponseImpl.class)
		{
			ExceptionResponseImpl exceptionResponse =
				(ExceptionResponseImpl)response;
			ErrorMessage aFailureReason = exceptionResponse.getFailureReason();
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"get ExceptionResponse Object from NetP");
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"Msg ID =" + aFailureReason.getMsgId());
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"Exception Type=" + aFailureReason.getExceptionType());
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"Message=" + aFailureReason.getMessage());
			theMsgId = exceptionResponse.getFailureReason().getMsgId();
			String errorText = aFailureReason.getMessage();
			//not first response item failed, set exception opt.
			ExceptionBuilder.parseException(
				aContext,
				ruleFile,
				null,
				theMsgId,
				errorText,
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aUtility,
				null,
				null,
				null);
		}
		return (ResponseImpl)response;
	}
	/**
	 * @param aContext
	 * @param aUtility
	 * @throws NullResourcesForServiceException
	 */
	public void processResponse(BisContext aContext, Utility aUtility)
		throws
			NullResourcesForServiceException,
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound
	{
		ResponseItemImpl responseItem = null;
		ServiceItemHelper serviceItemHelper = new ServiceItemHelper();
		StringBuffer responseCodeList = new StringBuffer();
		String code = null;
		boolean isFirst = true;
        
		ResponseImpl aResponseImpl = checkResponse(aContext,aUtility);
		Iterator it = aResponseImpl.getResponseItem().iterator();

		try
		{
			while (it.hasNext())
			{
                
				if (!isFirst)
					responseCodeList.append(",");
				isFirst = false;

				responseItem = (ResponseItemImpl) it.next();

				code = responseItem.getResponseCode();
               
				if(code == null || code.trim().length()< 1)
				{
					aUtility.log(
						LogEventId.DEBUG_LEVEL_2,
						"no responseCode for this responseItem");
					aUtility.throwException(
						ExceptionCode.ERR_RM_RESPONSE_CODE_NOT_FOUND,
						"Missing Data: ResponseCode",
						 this.bis_name,
						Severity.UnRecoverable);
				}
				code = code.trim();
                
				if (code.equalsIgnoreCase(SUCCEEDED))
				{

					responseCodeList.append(SUCCEEDED);
					serviceItemList.add(
						serviceItemHelper.createServiceItem(responseItem));
				} else if (
					code.equalsIgnoreCase(PENDING)
						|| code.equalsIgnoreCase(NOTSTARTED))
				{
                    
					responseCodeList.append(code);
				} else if (code.equalsIgnoreCase(FAILED))
				{
					ErrorMessage aFailureReason = responseItem.getFailureReason();
					
					theMsgId = aFailureReason.getMsgId();
					String errorText = aFailureReason.getMessage();

					aUtility.log(
						LogEventId.DEBUG_LEVEL_2,
						"get Failed Response Item from NetP");
					aUtility.log(LogEventId.DEBUG_LEVEL_2, "Msg ID =" + theMsgId);
					aUtility.log(
						LogEventId.DEBUG_LEVEL_2,
						"Exception Type=" + aFailureReason.getExceptionType());
					aUtility.log(LogEventId.DEBUG_LEVEL_2, "Message=" + errorText);
				
					responseCodeList.append(FAILED);
					
					//failed response item, set exception opt.
					ExceptionBuilderResult aExceptionResult = ExceptionBuilder.parseException(
							aContext,
							ruleFile,
							null,
							theMsgId,
							errorText,
							false,
							ExceptionBuilderRule.NO_DEFAULT,
							null,
							null,
							aUtility,
							null,
							null,
							null);
				
					Exception aException = aExceptionResult.getException();
					if(aException instanceof InvalidData)
					{
						aExceptionDataOpt.theValue(((InvalidData)aException).aExceptionData);
					}
					else if(aException instanceof AccessDenied)
					{
						aExceptionDataOpt.theValue(((AccessDenied)aException).aExceptionData);
					}
					else if(aException instanceof BusinessViolation)
					{
						aExceptionDataOpt.theValue(((BusinessViolation)aException).aExceptionData);
					}
					else if(aException instanceof NotImplemented)
					{
						aExceptionDataOpt.theValue(((NotImplemented)aException).aExceptionData);
					}
					else if(aException instanceof ObjectNotFound)
					{
						aExceptionDataOpt.theValue(((ObjectNotFound)aException).aExceptionData);
					}
					else if(aException instanceof DataNotFound)
					{
						aExceptionDataOpt.theValue(((DataNotFound)aException).aExceptionData);
					}
					else
					{
						aExceptionDataOpt.theValue(((SystemFailure)aException).aExceptionData);
					}
					
				} else
				{
					aUtility.log(
						LogEventId.DEBUG_LEVEL_2,
						"Received invalid status from NP, ignore this response Item");
				}
			}
		} finally
		{
			serviceItemIterator = serviceItemList.iterator();
			responseCode = responseCodeList.toString();
			if (aResponseImpl.isSetResponseNumber() == false)
			{
				aUtility.throwException(
					ExceptionCode.ERR_RM_RESPONSE_NUMBER_NOT_FOUND,
					"Missing Data: response number",
					this.bis_name,
					Severity.UnRecoverable);
			}

			aUtility.log(
							LogEventId.DEBUG_LEVEL_1,
							"Response code list<" + responseCode + ">");
    
			//check if "NotStarted" is returned for single request
			if(responseCode.equalsIgnoreCase(NOTSTARTED))
			{
				ExceptionBuilder.parseException(
											aContext,
											ruleFile,
											null,
											null,
											responseCode,
											true,
											ExceptionBuilderRule.NO_DEFAULT,
											null,
											null,
											aUtility,
											null,
											null,
											null);
			}
		}
	}
	
	/**
	 * Method getAExceptionDataOpt.
	 * @return ExceptionDataOpt
	 */
	
	public ExceptionDataOpt getAExceptionDataOpt()
	{
		return aExceptionDataOpt;
	}
}
