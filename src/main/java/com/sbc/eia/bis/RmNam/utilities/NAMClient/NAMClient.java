// $Id: NAMClient.java,v 1.5 2004/01/14 21:12:27 ts8181 Exp $

package com.sbc.eia.bis.RmNam.utilities.NAMClient;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.att.lms.bis.common.config.StaticContextAccessor;
import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
//import com.sbc.eia.bis.facades.nam.ejb.Nam;
//import com.sbc.eia.bis.facades.nam.ejb.NamHome;
import com.sbc.eia.bis.RmNam.utilities.InitialContextFactory;
import com.sbc.eia.bis.RmNam.utilities.InitialContextFactoryException;
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
import com.sbc.eia.idl.nam.NamFacade;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;

/**
 * Allows client access to the NAM facade.
 * Creation date: (4/26/01 2:57:56 PM)
 * @author: Creighton Malet
 */
public final class NAMClient {
//	private NamHome namHome = null;

	String aProviderURL = null;
	String namHomeName = null;
	String origination = null;
	String initialContextPropertiesFile = null;
	/**
	 * Class constructor.
	 */
	public NAMClient() {
	}
	/**
	 * Class constructor with network parameters.
	 * @param newProviderURL
	 * @param aNamHomeName java.lang.String
	 * @param anOrigination java.lang.String
	 * @param anInitialContextPropertiesFile java.lang.String
	 */
	/*public NAMClient(
		String newProviderURL,
		String aNamHomeName,
		String anOrigination,
		String anInitialContextPropertiesFile) {
		aProviderURL = newProviderURL;
		namHomeName = aNamHomeName;
		origination = anOrigination;
		initialContextPropertiesFile = anInitialContextPropertiesFile;
	}*/
	/**
	 * Get a NAM bean.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @return com.sbc.eia.bis.facades.nam.ejb.Nam
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	/*private final Nam getNamBean(BisContext aContext, Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "NamClient::getNamBean()");

		Nam namBean = null;

		try {
			namBean = getNamHome(aContext, aUtility).create();
		} catch (RemoteException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_2,
				"Caught RemoteException on NamHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				namHome = null;
				namBean = getNamHome(aContext, aUtility).create();
			} catch (RemoteException x) {
				namHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_NAMCL_REMOTE_EXCEPTION,
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ namHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				namHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_NAMCL_CREATE_EXCEPTION,
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ namHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		} catch (CreateException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_2,
				"Caught CreateException on NamHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				namHome = null;
				namBean = getNamHome(aContext, aUtility).create();

			} catch (RemoteException x) {
				namHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_NAMCL_REMOTE_EXCEPTION,
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ namHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				namHome = null;
				aUtility.throwException(
					ExceptionCode.ERR_NAMCL_CREATE_EXCEPTION,
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ namHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}

		return namBean;
	}*/

	public final NamFacade getNamConnection(
			BisContext aContext, Utility aUtility, String aCode, String aDescription)
			throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented,
			ObjectNotFound, DataNotFound {

		return StaticContextAccessor.getBean(NamFacade.class);
	}

	/**
	 * Calls LIM Bean.
	 * Creation date: (4/25/01 9:53:57 AM)
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @param aCode java.lang.String
	 * @param aDescription java.lang.String
	 * @return com.sbc.eia.bis.facades.lim.ejb.Lim
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	/*public final Nam getNamConnection(
		BisContext aContext,
		Utility aUtility,
		String aCode,
		String aDescription)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {

		aUtility.log(LogEventId.DEBUG_LEVEL_1, "NAMClient::getNamConnection()");

		Nam namBean = null;

		try {
			namBean = getNamBean(aContext, aUtility);
		} catch (NotImplemented e) {
			throwNamException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (SystemFailure e) {
			throwNamException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (InvalidData e) {
			throwNamException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (ObjectNotFound e) {
			throwNamException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (AccessDenied e) {
			throwNamException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (BusinessViolation e) {
			throwNamException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (DataNotFound e) {
			throwNamException(e.aExceptionData, aUtility, aCode, aDescription);
		}
		return namBean;
	}*/
	/**
	 * Get a NAM home.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext com.sbc.eia.idl.bis_types.BisContext
	 * @param aUtility com.sbc.bccs.utilities.Utility
	 * @return com.sbc.eia.bis.facades.nam.ejb.Nam
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	/*private final NamHome getNamHome(BisContext aContext, Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_1, "NamClient::getNamHome()");

		if (namHome == null) {
			InitialContext initialContext = null;
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"Get NAM Bean: "
					+ "[ provider url<"
					+ aProviderURL
					+ "> home<"
					+ namHomeName
					+ ">");

			// Look for any properties for the Context factory
			Properties initialContextProperties = null;
			try {
				initialContextProperties =
					PropertiesFileLoader.read(
						initialContextPropertiesFile,
						aUtility);
			} catch (Exception e) {
				initialContextProperties = new Properties();
			}
			InitialContextFactory aContextFactory =
				new InitialContextFactory(aProviderURL, initialContextProperties);

			try {
				aUtility.log(
					LogEventId.DEBUG_LEVEL_2,
					"NamClient::initialContextProperties: "
						+ initialContextProperties.toString());
				initialContext =
					aContextFactory.getInitialContext(initialContextProperties);
			} catch (InitialContextFactoryException e) {
				aUtility.throwException(
					ExceptionCode.ERR_NAMCL_INITIAL_CONTEXT_FACTORY_EXCEPTION,
					"InitialContextFactoryException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ namHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}

			try {
				Object o = initialContext.lookup(namHomeName);
				namHome =
					(NamHome) javax.rmi.PortableRemoteObject.narrow(
						o,
						NamHome.class);
			} catch (NamingException e) {
				aUtility.throwException(
					ExceptionCode.ERR_NAMCL_NAMING_EXCEPTION,
					"NamingException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ namHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}
		return namHome;
	}*/
	/**
	 * Wraps a NAM exception with the specified exception.
	 * Creation date: (5/7/01 12:43:31 PM)
	 * @param anExceptionData com.sbc.eia.idl.types.ExceptionData
	 * @param aUtility com.sbc.bccs.utilities
	 * @param anExceptionCode java.lang.String
	 * @param anExceptionMessage java.lang.String
	 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
	 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
	 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
	 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
	 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
	 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
	 */
	/*public static void throwNamException(
		ExceptionData anExceptionData,
		Utility aUtility,
		String anExceptionCode,
		String anExceptionMessage)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		String aOrigination = null;
		Severity aSeverity = null;

		try {
			aOrigination = anExceptionData.aOrigination.theValue();
		} catch (Exception e) {
		}
		try {
			aSeverity = anExceptionData.aSeverity.theValue();
		} catch (Exception e) {
		}

		aUtility.throwException(
			anExceptionCode,
			anExceptionMessage
				+ " ["
				+ anExceptionData.aCode
				+ " "
				+ anExceptionData.aDescription
				+ " ]",
			aOrigination,
			aSeverity);
	}*/
}
