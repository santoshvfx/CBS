//$Id: CBLSQueryRequestHelper.java,v 1.3 2011/05/07 01:45:37 rs278j Exp $
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
 * Contains the logic for building the CBLSQuery Request
 * 
 * @author js7440
 *
 */
public class CBLSQueryRequestHelper 
	implements TIRKSJXConstants
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	protected JXAPIImpl aRequest;
	
	/**
	 * Constructor for class CBLSQueryRequestHelper
	 * 
	 * @param aUtility
	 * @param aProperties
	 */
	public CBLSQueryRequestHelper (
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
		aRequest = new JXAPIImpl();
	}
	
	/**
	 * Builds the CBLS Query Request
	 * 
	 * @param property
	 * @param sessionID
	 * @param term_A
	 * @param term_Z
	 * @param cable
	 * @param fromUnit
	 * @param lastUnit
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
	public JXAPIImpl buildCBLSQueryRequest(
			Properties property, 
			String sessionID, 
			String term_A, 
			String term_Z, 
			String cable, 
			String fromUnit, 
			String lastUnit,
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
		String myMethodName = "CBLSQueryRequestHelper::buildCBLSQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		HeaderType header = null;
		ProcessingActionType processingAction = null;
		SetFieldsType setFields = null;
		FieldType termAField = null;
		FieldType termZField = null;
		FieldType cableField = null;
		FieldType fromUnitField = null;
		FieldType lastUnitField = null;
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
				
				if(cable != null && !cable.equalsIgnoreCase(""))
				{
					cableField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					cableField.setName(FIELD_CABLE);
					cableField.setInstance(new BigInteger(INSTANCE_0));
					cableField.setValue(cable);
				}
				
				if(fromUnit != null && !fromUnit.equalsIgnoreCase(""))
				{
					fromUnitField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					fromUnitField.setName(FIELD_FROM_UNIT);
					fromUnitField.setInstance(new BigInteger(INSTANCE_0));
					fromUnitField.setValue(fromUnit);
				}
				
				if(lastUnit != null && !lastUnit.equalsIgnoreCase(""))
				{
					lastUnitField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					lastUnitField.setName(FIELD_LAST_UNIT);
					lastUnitField.setInstance(new BigInteger(INSTANCE_0));
					lastUnitField.setValue(lastUnit);
				}
				
				if(termAField != null)
				{
					setFields.getField().add(termAField);
				}
				if(termZField != null)
				{
					setFields.getField().add(termZField);
				}
				if(cableField != null)
				{
					setFields.getField().add(cableField);
				}
				if(fromUnitField != null)
				{
					setFields.getField().add(fromUnitField);
				}
				if(lastUnitField != null)
				{
					setFields.getField().add(lastUnitField);
				}
				
				processingAction.setScreen(SCREEN_CBLS);
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
					"Error in building TIRKSJX CBLS Query Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return aRequest;
	}
}
