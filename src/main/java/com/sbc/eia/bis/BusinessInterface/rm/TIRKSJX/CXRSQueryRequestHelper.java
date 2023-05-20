//$Id: CXRSQueryRequestHelper.java,v 1.3 2011/05/07 01:47:01 rs278j Exp $
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

import javax.xml.bind.JAXBException;

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
 * Contains the logic for building the CXRS Quesry Reqest
 * @author Julius Sembrano
 **/ 
public class CXRSQueryRequestHelper implements TIRKSJXConstants
{	
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	protected JXAPIImpl aRequest;
	
	/**
	 * Constructor for class CXRSQueryRequestHelper
	 * 
	 * @param aUtility aUtility
	 * @param aProperties aProperties
	 */
	public CXRSQueryRequestHelper (
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
		aRequest = new JXAPIImpl();
	}

	/**
	 * builds the CXRS quesry request
	 * 
	 * @param Properties property
	 * @param String sessionID
	 * @param String term_A
	 * @param String term_Z
	 * @param String fac_des
	 * @param String fac_type
	 * @param boolean isPageDownRequest
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public JXAPIImpl buildCXRSQueryRequest(
			Properties property, 
			String sessionID, 
			String term_A, 
			String term_Z, 
			String fac_des, 
			String fac_type,
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
		String myMethodName = "CXRSQueryRequestHelper::buildCXRSQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		HeaderType header = null;
		ProcessingActionType processingAction = null;
		SetFieldsType setFields = null;
		FieldType termAField = null;
		FieldType termZField = null;
		FieldType facDesField= null;
		FieldType facTypeField = null;
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
				if(term_A != null && !term_A.equalsIgnoreCase(""))
				{
					termAField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					termAField.setName(FIELD_TERM_A);
					termAField.setInstance(new BigInteger(INSTANCE_0));
					termAField.setValue(term_A);
				}
				
				if(term_Z != null && !term_Z.equalsIgnoreCase(""))
				{
					termZField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					termZField.setName(FIELD_TERM_Z);
					termZField.setInstance(new BigInteger(INSTANCE_0));
					termZField.setValue(term_Z);
				}
				
				if(fac_des != null && !fac_des.equalsIgnoreCase(""))
				{
					facDesField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					facDesField.setName(FIELD_FAC_DES);
					facDesField.setInstance(new BigInteger(INSTANCE_0));
					facDesField.setValue(fac_des);
				}
				
				if(fac_type != null && !fac_type.equalsIgnoreCase(""))
				{
					facTypeField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					facTypeField.setName(FIELD_FAC_TYPE);
					facTypeField.setInstance(new BigInteger(INSTANCE_0));
					facTypeField.setValue(fac_type);
				}
				
				if(termAField != null)
				{
					setFields.getField().add(termAField);
				}
				if(termZField != null)
				{
					setFields.getField().add(termZField);
				}
				if(facDesField != null)
				{
					setFields.getField().add(facDesField);
				}
				if(facTypeField != null)
				{
					setFields.getField().add(facTypeField);
				}
				
				processingAction.setScreen(SCREEN_CXRS);
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
					"Error in buildng TIRKSJX CXRS Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return aRequest;
	}
}
