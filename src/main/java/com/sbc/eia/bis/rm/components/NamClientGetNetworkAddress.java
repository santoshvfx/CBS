// $Id: NamClientGetNetworkAddress.java,v 1.1 2006/08/15 20:33:14 jo8461 Exp $

package com.sbc.eia.bis.rm.components;

import java.rmi.RemoteException;
import java.util.Properties;

import com.sbc.bccs.idl.helpers.IDLUtil;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.bccs.utilities.Utility;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilder;
import com.sbc.bccs.utilities.exceptionbuilder.ExceptionBuilderRule;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.RmNam.utilities.NAMClient.NAMClient;
//import com.sbc.eia.bis.facades.nam.ejb.Nam;
import com.sbc.eia.bis.framework.BisContextManager;
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
import com.sbc.eia.idl.nam.GetNetworkAddressReturn;
import com.sbc.eia.idl.nam.NamFacade;
import com.sbc.eia.idl.nam_types.NetworkAddressProperty;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.types.ObjectKeyOpt;
import com.sbc.eia.idl.types.Severity;

/**
 * Does a lot of the Switch wire center logic.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author:Mark Liljequist
 
#   History :
#   Date       | Author        |    Version	 Notes
#   ----------------------------------------------------------------------------
#	08/12/2002	Mark Liljequist		6.0		 Creation.
#   03/26/03	Tanuja Singh		6.1		 Added BisContext.Application=RM_BIS 
#											 for NAM BIS (DR 68672)
#   01/30/2005  Stevan Dunkin       11.0     Corrected error becasue of a change to 
#                                            GetNetworkAddressReturn.aProperties containing 
#                                            an array of ObjectProperty objects.
#	09/08/2005  Mark Liljequist     11.0     Remove context as a instance variable.								
*/

/**
 * Insert the type's description here.
 * Creation date: (8/14/2002 10:09:51 AM)
 * @author: Mark Liljequist
 */
public class NamClientGetNetworkAddress {

	Utility theUtility;
	java.util.Hashtable theProperties;

	// The NAM Client for calls to NAM

	private NAMClient theNAMClient = null;

	private GetNetworkAddressReturn theGetNAReturn;
	private String theNetworkAddressHandle;

	private String theTN;
	/**
	 * NamClientGetNetworkAddress constructor.
	 */
	public NamClientGetNetworkAddress(
		Utility aUtility,
		java.util.Hashtable aProperties) {
		super();

		theUtility = aUtility;
		theProperties = aProperties;
	}
	public void createClient()
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String myMethodName = "NamClientGetNetworkAddress.createClient()";

		theUtility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// Create NAM client.

		if (theNAMClient == null)
			theNAMClient =
				new NAMClient();
//					(String) theProperties.get("NAM_PROVIDER_URL"),
//					(String) theProperties.get("NAM_BIS_NAME"),
//					(String) theProperties.get("BIS_NAME"),
//					(String) theProperties.get(
//						"INITIAL_CONTEXT_PROPERTIES_FILE"));

	}
	public String getAddressHandleValue(String aProperty)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String myMethodName = "NamClientGetNetworkAddress.getAddressHandleValue()";
	theUtility.log(
			LogEventId.DEBUG_LEVEL_1, myMethodName);

		// Analyse the result.

		String aValue = null;
		int i = 0;
		try {
			for (i = 0;
				i < theGetNAReturn.aNetworkAddress.aProperties.theValue().length;
				i++) {
				if (theGetNAReturn
					.aNetworkAddress
					.aProperties.theValue()[i]
					.aTag
					.equalsIgnoreCase(aProperty)) {
					aValue =
						theGetNAReturn.aNetworkAddress.aProperties.theValue()[i].aValue;
					break;
				}
			}
		} catch (org.omg.CORBA.BAD_OPERATION e) {
			theUtility.throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_VALUE_FROM_NAM,
				"Unexpected value received from NAM: aGetNAReturn.aNetworkAddress.aProperties["
					+ i
					+ "].aTag/aValue = <null>",
				(String) theProperties.get("BIS_NAME"),
				Severity.UnRecoverable);
		} catch (NullPointerException e) {
			theUtility.throwException(
				ExceptionCode.ERR_RM_UNEXPECTED_VALUE_FROM_NAM,
				"Unexpected value received from NAM: aGetNAReturn.aNetworkAddress.aProperties["
					+ i
					+ "].aTag/aValue = <null>",
				(String) theProperties.get("BIS_NAME"),
				Severity.UnRecoverable);
		}

		return aValue;

	}
	public void getClientNetworkAddress(BisContext aContext)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		theUtility.log(
			LogEventId.DEBUG_LEVEL_1,
			"NamClientGetNetworkAddress::getNetworkAddress()");

		try {
			theUtility.log(
				LogEventId.REMOTE_CALL,
				(String) theProperties.get("NAM_PROVIDER_URL"),
				(String) theProperties.get("NAM_BIS_NAME"),
				(String) theProperties.get("NAM_BIS_NAME"),
				"getNetworkAddress()");

			NamFacade namBean =
				theNAMClient.getNamConnection(
					aContext,
					theUtility,
					ExceptionCode.ERR_NAMCL_REMOTE_EXCEPTION,
					"NAM Connection Failed");
			theGetNAReturn =
				namBean.getNetworkAddress(
					TranBase.setBisContextAppl(
						aContext,
						(String) theProperties.get("BIS_CONTEXT_APPLICATION")),
					theNetworkAddressHandle);

		} /*catch (RemoteException e) {
			theUtility.throwException(
				ExceptionCode.ERR_NAMCL_REMOTE_EXCEPTION,
				"RemoteException: <" + e.getMessage() + ">",
				(String) theProperties.get("BIS_NAME"),
				Severity.UnRecoverable);

		}*/ catch (NotImplemented e) {
			throwNamException(
				aContext,
				e.aExceptionData.aCode,
				e.aExceptionData.aDescription,
				e.aExceptionData.aOrigination.theValue(),
				e.aExceptionData.aSeverity.theValue());
		} catch (SystemFailure e) {
			throwNamException(
				aContext,
				e.aExceptionData.aCode,
				e.aExceptionData.aDescription,
				e.aExceptionData.aOrigination.theValue(),
				e.aExceptionData.aSeverity.theValue());
		} catch (InvalidData e) {
			throwNamException(
				aContext,
				e.aExceptionData.aCode,
				e.aExceptionData.aDescription,
				e.aExceptionData.aOrigination.theValue(),
				e.aExceptionData.aSeverity.theValue());
		} catch (ObjectNotFound e) {
			throwNamException(
				aContext,
				e.aExceptionData.aCode,
				e.aExceptionData.aDescription,
				e.aExceptionData.aOrigination.theValue(),
				e.aExceptionData.aSeverity.theValue());
		} catch (DataNotFound e) {
			throwNamException(
				aContext,
				e.aExceptionData.aCode,
				e.aExceptionData.aDescription,
				e.aExceptionData.aOrigination.theValue(),
				e.aExceptionData.aSeverity.theValue());
		} catch (AccessDenied e) {
			throwNamException(
				aContext,
				e.aExceptionData.aCode,
				e.aExceptionData.aDescription,
				e.aExceptionData.aOrigination.theValue(),
				e.aExceptionData.aSeverity.theValue());
		} catch (BusinessViolation e) {
			throwNamException(
				aContext,
				e.aExceptionData.aCode,
				e.aExceptionData.aDescription,
				e.aExceptionData.aOrigination.theValue(),
				e.aExceptionData.aSeverity.theValue());
		} finally {
			theUtility.log(
				LogEventId.REMOTE_RETURN,
				(String) theProperties.get("NAM_PROVIDER_URL"),
				(String) theProperties.get("NAM_BIS_NAME"),
				(String) theProperties.get("NAM_BIS_NAME"),
				"getNetworkAddress()");
		}
	}

	public void throwNamException(
		BisContext aContext,
		String aCode,
		String aDescription,
		String aOrigination,
		Severity aSeverity)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		// Set the context into the logger

		String myMethodName = "NamClientGetNetworkAddress::throwNamException";
		theUtility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		String namRuleFile =
			(String) theProperties.get("EXCEPTION_BUILDER_NAM_RULE_FILE");

		Properties tagValues = new Properties();
		BisContextManager bcm = new BisContextManager(aContext);
		String aCenter = bcm.getServiceCenter();
		tagValues.setProperty("TN", theTN);
		tagValues.setProperty("SC", aCenter);
		ExceptionBuilder.parseException(
			aContext,
			namRuleFile,
			"",
			aCode,
			aDescription,
			true,
			ExceptionBuilderRule.NO_DEFAULT,
			null,
			null,
			theUtility,
			aOrigination,
			aSeverity,
			tagValues);
	}

	public String retrievePortedTN(String aTN, BisContext aContext)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String myMethodName = "NamClientGetNetworkAddress.retrievePortedTN()";

		theUtility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		this.setAddressHandleValue(aTN);
		this.setTN(aTN);

		theUtility.log(
			LogEventId.INFO_LEVEL_2,
			"getNetworkAddress() Request["
				+ "TN<"
				+ theNetworkAddressHandle
				+ ">]");

		this.createClient();

		//	this.process();
		getClientNetworkAddress(aContext);

		String aLRN =
			this.getAddressHandleValue(
				NetworkAddressProperty.LOCALROUTINGNUMBER);

		theUtility.log(
			LogEventId.INFO_LEVEL_2,
			"getNetworkAddress() Response[lrn<" + aLRN + ">]");

		return aLRN;

	}
	public void setAddressHandleValue(String aHandle) {

		String myMethodName = "NamClientGetNetworkAddress.setAddressHandle()";

		theUtility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		// Set the address handle,

		theNetworkAddressHandle = aHandle;

	}
	public void setTN(String aTN) {

		theTN = aTN;

	}
	public TN validatePortedTN(TN aTN, BisContext aContext)
		throws
			AccessDenied,
			BusinessViolation,
			InvalidData,
			NotImplemented,
			ObjectNotFound,
			SystemFailure,
			DataNotFound {

		String myMethodName = "NamClientGetNetworkAddress.validatePortedTN()";

		theUtility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);

		String aLRN = this.retrievePortedTN(aTN.toString(), aContext);

		if (aLRN == null)
			return aTN;

		return new TN(aLRN);

	}
}
