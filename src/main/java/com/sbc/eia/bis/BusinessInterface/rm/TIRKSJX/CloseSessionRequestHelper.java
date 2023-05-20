//$Id: CloseSessionRequestHelper.java,v 1.2 2011/04/07 02:56:21 rs278j Exp $
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

import java.util.Properties;

import javax.xml.bind.JAXBException;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.ObjectFactory;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.JXAPIType.HeaderType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.JXAPIType.SessionType;
import com.sbc.eia.bis.tcp.service.tirksjx.TIRKSJxCloseSessionRequest.impl.JXAPIImpl;
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
 * Contains the logic for building the Close Session Request
 * 
 * @author js7440
 *
 */
public class CloseSessionRequestHelper 
	implements TIRKSJXConstants
{
	private com.sbc.bccs.utilities.Utility utility = null;
	private java.util.Hashtable properties = null;
	protected JXAPIImpl aRequest;
	
	/**
	 * Constructor for class CloseSessionRequestHelper
	 * 
	 * @param aUtility
	 * @param aProperties
	 */
	public CloseSessionRequestHelper (
			com.sbc.bccs.utilities.Utility aUtility, 
			java.util.Hashtable aProperties)
	{
		utility = aUtility;
		properties = aProperties;
		aRequest = new JXAPIImpl();
	}
	
	/**
	 * Build the close session request
	 * 
	 * @param Properties property
	 * @param String sessionID
	 * @return JXAPIImpl
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws InvalidData
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws SystemFailure
	 * @throws DataNotFound
	 */
	public JXAPIImpl buildCloseSessionRequest(
			Properties property, 
			String sessionID)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound
	{
		String myMethodName = "CloseSessionRequestHelper::buildCloseSessionRequest()";
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		HeaderType header;
		SessionType session;
		
		try 
		{
			header = new ObjectFactory().createJXAPITypeHeaderType();
			header.setName(HEADER_NAME);
			header.setVersion((property.getProperty("TIRKSJX.HEADER.VERSION")));
			header.setSender(HEADER_SENDER);
			header.setSenderRole(HEADER_SENDER_ROLE);
		
			session = new ObjectFactory().createJXAPITypeSessionType();
			session.setAction(ACTION_CLOSE);
			session.setSessionID(sessionID);
			
			aRequest.setHeader(header);
			aRequest.setSession(session);
		
		} 
		catch (Exception e) 
		{
			utility.throwException(
					ExceptionCode.ERR_RM_TIRKS_CONNECT_FAILURE_ALL_HELPERS,
					"Error in building TIRKSJX Close Session Request " + e.getMessage(),
					myMethodName,
					Severity.UnRecoverable);
		}
		utility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
		
		return aRequest;
	}
}
