//$Id: RDLOCQueryRequestHelper.java,v 1.3 2011/05/07 01:50:35 rs278j Exp $
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
 * Contains the logic for building the RDLOC Query Request
 * 
 * @author js7440
 */
public class RDLOCQueryRequestHelper implements TIRKSJXConstants
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	protected JXAPIImpl aRequest;
	
	/**
	 * Constructor for class RDLOCQueryRequestHelper
	 * 
	 * @param Utility aUtility
	 * @param Hashtable aProperties
	 */
	public RDLOCQueryRequestHelper (
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
		aRequest = new JXAPIImpl();
	}

	/**
	 * build the RDLOC Query request
	 * 
	 * @param tirksJxProperties
	 * @param sessionID
	 * @param location
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
	public JXAPIImpl buildRDLOCQueryRequest(
			Properties tirksJxProperties, 
			String sessionID, 
			String location,
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
		String myMethodName = "RDLOCQueryRequestHelper::buildRDLOCQueryRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		HeaderType header = null;
		ProcessingActionType processingAction = null;
		SetFieldsType setFields = null;
		FieldType locationField = null;
		String actionOnScreen = actionIndicator;;
		
		try 
		{
			header = new ObjectFactory().createJXAPITypeHeaderType();
			header.setName(HEADER_NAME);
			header.setVersion((tirksJxProperties.getProperty("TIRKSJX.HEADER.VERSION")));
			header.setSender(HEADER_SENDER);
			header.setSenderRole(HEADER_SENDER_ROLE);
			setFields = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsType();
			processingAction = new ObjectFactory().createJXAPITypeProcessingActionType();
			
			if(actionOnScreen.equalsIgnoreCase(ACTION_F1))
			{			
				if(location != null && !location.equalsIgnoreCase(""))
				{
					locationField = new ObjectFactory().createJXAPITypeProcessingActionTypeSetFieldsTypeFieldType();
					locationField.setName(FIELD_LOCATION_CODE);
					locationField.setInstance(new BigInteger(INSTANCE_0));
					locationField.setValue(location);	
				}				
				if(locationField != null)
				{
					setFields.getField().add(locationField);
				}
				
				processingAction.setScreen(SCREEN_RDLOC);
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
					"Error in building TIRKSJX RDLOC Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return aRequest;
	}
}
