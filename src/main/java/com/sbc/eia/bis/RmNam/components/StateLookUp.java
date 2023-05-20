package com.sbc.eia.bis.RmNam.components;

/**
	 * Determines a State Code, i.e. CA, MO, IL, etc. from a telephone number
	 * @return java.lang.String
	 * @param telephoneNumber java.lang.String
	 *
	 * Send a telphone number to HOSTLOOKUP and get back a state code.
	 */
import java.util.Hashtable;
import com.sbc.bccs.utilities.Logger;
import com.sbc.bccs.idl.helpers.*;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.exception_types.*;
import com.sbc.eia.idl.nam.*;
import com.sbc.bccs.idl.helpers.ObjectPropertyManager;

import com.sbc.gwsvcs.access.vicuna.*;
import com.sbc.gwsvcs.access.vicuna.exceptions.*;
import com.sbc.bccs.utilities.exceptionbuilder.*;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.gwsvcs.service.hostlookup.*;
import com.sbc.gwsvcs.service.hostlookup.exceptions.*;
import com.sbc.gwsvcs.service.hostlookup.interfaces.*;


public class StateLookUp 
{
		
	public StateLookUp() {
	super();
	
	}
		
	private HOSTLOOKUPHelper hostHelper = null;
	
	public String getStateCode(BisContext aContext, Logger aLogger, String telephoneNumber, Hashtable aProperties)
	
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		String methodName = "StateLookUp:getStateCode ";
		
		String hostLookupName =
			HOSTLOOKUPAccess.name + "-" + HOSTLOOKUPAccess.version;

		String exceptionString = null;
		String divCode = null;
		EventResultPair hostResponse = null;

		aLogger.log(LogEventId.DEBUG_LEVEL_1, methodName);
		aLogger.log(
			LogEventId.INFO_LEVEL_2,
			methodName + "Using Telephhonenumber <" + telephoneNumber + ">");

		// *********************************
		// Create HostLookup Helper
		// *********************************
		if (hostHelper == null) {
			try {
				hostHelper = new HOSTLOOKUPHelper(aProperties, aLogger);
			} catch (ServiceException e) {
				ExceptionBuilder.parseException(
				aContext,
				(String)aProperties.get(
					"EXCEPTION_BUILDER_HOSTLKUP_RULE_FILE"),
				null,
				e.getOriginalExceptionCode(),
				e.getMessage(),
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aLogger,
				null,
				null,
				null);
			}
		}

		HostLookupST hostLookupst = new HostLookupST(telephoneNumber);

		// *********************************
		// Call HostLookup
		// *********************************
		// --------------------------			
		// Performance Logger - Start
		// --------------------------			
		aLogger.log(
			LogEventId.REMOTE_CALL,
			HOSTLOOKUPAccess.name,
			hostLookupName,
			hostLookupName,
			"hlLookupState");

		java.util.Properties tagValues = new java.util.Properties();
		tagValues.setProperty("TN", telephoneNumber);
		// --------------------------			
		// Make the call
		// --------------------------			
		try {
			hostResponse =
				hostHelper.hlLookupState(null, null, 0, hostLookupst);

		} catch (HOSTLOOKUPException e) {
		
			ExceptionBuilder.parseException(
				aContext,
				(String) aProperties.get(
					"EXCEPTION_BUILDER_HOSTLKUP_RULE_FILE"),
				null,
				e.getMessage(),
				e.getMessage(),
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aLogger,
				null,
				null,
				tagValues);

		} catch (ServiceTimeoutException e) {
			
				ExceptionBuilder.parseException(
				aContext,
				(String) aProperties.get(
					"EXCEPTION_BUILDER_HOSTLKUP_RULE_FILE"),
				null,
				e.getMessage(),
				"timed out",
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aLogger,
				null,
				null,
				null);

		} catch (ServiceException e) {
			ExceptionBuilder.parseException(
				aContext,
				(String) aProperties.get(
					"EXCEPTION_BUILDER_HOSTLKUP_RULE_FILE"),
				null,
				e.getOriginalExceptionCode(),
				e.getMessage(),
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aLogger,
				null,
				null,
				null);
			
		}

		// --------------------------			
		// Performance Logger - End
		// --------------------------			
		aLogger.log(
			LogEventId.REMOTE_RETURN,
			HOSTLOOKUPAccess.name,
			hostLookupName,
			hostLookupName,
			"hlLookupState");

		// *********************************
		// Process the result
		// *********************************
		aLogger.log(
			LogEventId.INFO_LEVEL_2,
			methodName + "Received event: " + hostResponse.getEventNbr());

		switch (hostResponse.getEventNbr()) {
			// --------------------------
			// Event 310 - Successful Return
			// --------------------------
			case HOSTLOOKUPAccess.HL_LOOKUP_STATE_R_NBR :

				HostLookupST_R hostReturn =
					(HostLookupST_R) hostResponse.getTheObject();

				aLogger.log(
					LogEventId.DEBUG_LEVEL_2,
					"Advisory message: "
						+ methodName
						+ "retrieve state code was successful");
				aLogger.log(
					LogEventId.INFO_LEVEL_2,
					methodName + "Received state <" + hostReturn.state + ">");

				return hostReturn.state;

				// --------------------------			
				// Event 110 error in access
				// --------------------------			
			case HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR :
				HostLookup_Error errorResp =
					(HostLookup_Error) hostResponse.getTheObject();
			ExceptionBuilder.parseException(
				aContext,
				(String) aProperties.get(
					"EXCEPTION_BUILDER_HOSTLKUP_RULE_FILE"),
				null,
				HOSTLOOKUPAccess.HL_OSS_LOOKUP_ERROR_NBR + "",
				"Received a HL_OSS_LOOKUP_ERROR_NBR exception: " + errorResp.ErrorMsg,
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aLogger,
				null,
				null,
				tagValues);

				break;

				// --------------------------			
				// Received an unknown event
				// --------------------------			
			default :
			
			ExceptionBuilder.parseException(
				aContext,
				(String) aProperties.get(
					"EXCEPTION_BUILDER_HOSTLKUP_RULE_FILE"),
				null,
				"Received an unknown Event: ",
				hostResponse.getEventNbr() + "" ,
				true,
				ExceptionBuilderRule.NO_DEFAULT,
				null,
				null,
				aLogger,
				null,
				null,
				tagValues);
				
				break;
		}

		return null;
	}

}
