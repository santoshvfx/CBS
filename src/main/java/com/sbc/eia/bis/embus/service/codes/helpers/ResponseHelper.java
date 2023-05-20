//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of AT&T Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of AT&T Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) AT&T Services, Inc, 2006-2007.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# 12/15/2006  Changchuan Yin  Created.
//# 02/11/2007  Changchuan Yin  Fixed a bug.

package com.sbc.eia.bis.embus.service.codes.helpers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderResult;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.ErrorCode;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.Errorlist;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.Messageslist;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.ResponseMessage;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.StatusInfolist;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.impl.ErrorlistImpl;
import com.sbc.eia.bis.embus.service.codes.RetrieveServiceProvidersForResourceResponse.impl.StatusInfolistImpl;
import com.sbc.eia.bis.embus.service.codes.interfaces.impl.MessageslistImpl;
import com.sbc.eia.bis.embus.service.codes.interfaces.impl.ResponseMessageImpl;
import com.sbc.eia.bis.embus.service.netprovision.helpers.NullResourcesForServiceException;
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
import com.sbc.eia.idl.types.StringOpt;

/**
 * @author cy4727
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ResponseHelper
{

	String myMethodName = "ResponseHelper";
	ArrayList serviceItemList = null;
	Object response = null;
    String ruleFile = null;
	Iterator serviceItemIterator = null;
	String bis_name = null;
    
	

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
	 * Method hasNext.
	 * @return boolean
	 */

	public boolean hasNext()
	{
		return serviceItemIterator.hasNext();
	}

	/**
	 * @param aContext
	 * @param aUtility
	 * @throws NullResourcesForServiceException
	 */
	public void checkErrorMsg(BisContext aContext, Utility aUtility)
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
		//ServiceItemHelper serviceItemHelper = new ServiceItemHelper();
		StringBuffer responseCodeList = new StringBuffer();
		String code = null;
		boolean isFirst = true;
        
		ResponseMessage aResponse = (ResponseMessage)this.response;//checkResponse(aContext,aUtility);
		StatusInfolist statusInfo = aResponse.getStatusInfo();
		
        //Errors section in StatusInfo
        List errList = statusInfo.getErrors();
	    String ObjName = errList.get(0).getClass().getName();
		
		ErrorlistImpl errDetail = (ErrorlistImpl)errList.get(0);
	    String errCode = errDetail.getErrorCode();
	    
	    //System.out.println("Error Code=" + errCode);
		
		String errMsg = errDetail.getErrorDescription();
		//System.out.println("Error Description e=" + errMsg);
		
		//Tested above code
		
		if(errCode != null && errCode.trim().length()>1)
			{
				aUtility.log(
					LogEventId.DEBUG_LEVEL_2,
					"Error was received from CODESRC in response message and the error code = "+errCode.trim());
					
				aUtility.log(LogEventId.DEBUG_LEVEL_2,
									"Error description: "+errMsg);
				
				ExceptionBuilder.parseException(
																	aContext,
																	ruleFile,
																	null,
																	errCode,
																	errMsg,
																	true,
																	ExceptionBuilderRule.NO_DEFAULT,
																	null,
																	null,
																	aUtility,
																	null,
																	null,
																	null);
					
					
		}
		else
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_2, "Response message from CODESRC was received successfully.");
		}
		
			
		//CODESRC will not use message Code
        /*
			  Messageslist statusList = statusInfo.getMessages();
			  String msgCode = statusList.getMessageCode();
			  String msgDes = statusList.getMessageDescription();
			  
		if(msgCode == null || msgCode.trim().length()< 1)
		{
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"No response message code was sent with the CODESRC response.");
		
			aUtility.throwException(
							ExceptionCode.ERR_RM_RESPONSE_CODE_NOT_FOUND,
							"Response messsage code was not receied from CODESRC.",
							 this.bis_name,
							Severity.UnRecoverable);
		}
		else
		{
			aUtility.log(LogEventId.DEBUG_LEVEL_2, "Message Code =" + msgCode.trim());
		}
		*/
		
		
	}
	
}
