//$Id: WAQueryRequestHelper.java,v 1.3 2011/05/07 01:53:29 rs278j Exp $
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

import java.math.BigInteger;
import java.util.Properties;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.ObjectFactory;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.JXAPIType.HeaderType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.JXAPIType.ProcessingActionType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.JXAPIType.ProcessingActionType.SetFieldsType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.JXAPIType.ProcessingActionType.SetFieldsType.FieldType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxActionOnScreenRequest.impl.JXAPIImpl;
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
 * Class for building the WA Query Request
 * 
 * @author js7440
 */
public class WAQueryRequestHelper implements TIRKSJXConstants
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	protected JXAPIImpl aRequest;
	
	/**
	 * Constructor for class WA Query
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public WAQueryRequestHelper (
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
		aRequest = new JXAPIImpl();
	}
	
	/**
	 * build the WA Query Request
	 * 
	 * @param property
	 * @param sessionID
	 * @param circuitFormat
	 * @param circuitID
	 * @param circuitLayoutOrder
	 * @param circuitAccessCode
	 * @param isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public JXAPIImpl buildWAQueryRequest(
			Properties property, 
			String sessionID, 
			String circuitFormat, 
			String circuitID, 
			String circuitLayoutOrder, 
			String circuitAccessCode,
			String actionIndicator)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "WAQueryRequestHelper::buildWAQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		HeaderType header = null;
		ProcessingActionType processingAction = null;
		SetFieldsType setFields = null;
		FieldType circuitFormatField = null;
		FieldType circuitIDField = null;
		FieldType circuitLayoutOrderField = null;;
		FieldType circuitAccessCodeField = null;	
		String actionOnScreen = actionIndicator;
		
		try 
		{
			header = new ObjectFactory().createJXAPITypeHeaderType();
			header.setName(HEADER_NAME);
			header.setVersion((property.getProperty("TIRKSJX.HEADER.VERSION")));
			header.setSender(HEADER_SENDER);
			header.setSenderRole(HEADER_SENDER_ROLE);
			setFields = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsType();
			processingAction = new ObjectFactory().createJXAPITypeProcessingActionType();
			
			if(actionOnScreen.equalsIgnoreCase(ACTION_F1))
			{
				if(circuitFormat != null && !circuitFormat.equalsIgnoreCase(""))
				{
					circuitFormatField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					circuitFormatField.setName(FIELD_CKT);
					circuitFormatField.setInstance(new BigInteger(INSTANCE_0));
					circuitFormatField.setValue(circuitFormat);
				}
				
				if(circuitID != null && !circuitID.equalsIgnoreCase(""))
				{
					circuitIDField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					circuitIDField.setName(FIELD_CKT);
					circuitIDField.setInstance(new BigInteger(INSTANCE_1));
					circuitIDField.setValue(circuitID);
				}
				
				if(circuitLayoutOrder != null && !circuitLayoutOrder.equalsIgnoreCase(""))
				{
					circuitLayoutOrderField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					circuitLayoutOrderField.setName(FIELD_CLO);
					circuitLayoutOrderField.setInstance(new BigInteger(INSTANCE_0));
					circuitLayoutOrderField.setValue(circuitLayoutOrder);
				}
				
				if(circuitAccessCode != null && !circuitAccessCode.equalsIgnoreCase(""))
				{
					circuitAccessCodeField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					circuitAccessCodeField.setName(FIELD_CAC);
					circuitAccessCodeField.setInstance(new BigInteger(INSTANCE_0));
					circuitAccessCodeField.setValue(circuitAccessCode);
				}
				
				if(circuitFormatField != null)
				{
					setFields.getField().add(circuitFormatField);
				}
				if(circuitIDField != null)
				{
					setFields.getField().add(circuitIDField);
				}
				if(circuitLayoutOrderField != null)
				{
					setFields.getField().add(circuitLayoutOrderField);				
				}
				if(circuitAccessCodeField != null)
				{
					setFields.getField().add(circuitAccessCodeField);
				}
				
				processingAction.setScreen(SCREEN_WA);
			}
			
			processingAction.setSessionID(sessionID);
			processingAction.setAction(actionOnScreen);
			processingAction.setSetFields(setFields);
			
			aRequest.setHeader(header);
			aRequest.setProcessingAction(processingAction);
		
		} 
		catch (Exception e) 
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX WA Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return aRequest;
	}
}
