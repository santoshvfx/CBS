// $Id: IomClient.java,v 1.5 2003/03/31 21:11:16 as5472 Exp $

package com.sbc.eia.bis.RmNam.utilities.IomClient;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sbc.bccs.utilities.PropertiesFileLoader;
import com.sbc.bccs.utilities.Utility;
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
//import com.sbc.eia.idl.iomip.IOMIPFacade;
import com.sbc.eia.idl.types.ExceptionData;
import com.sbc.eia.idl.types.Severity;
import com.sbc.iomip.sessionbeans.IomipBIS;
import com.sbc.iomip.sessionbeans.IomipBISBean;
import com.sbc.iomip.sessionbeans.IomipBISHome;

/**
 * Connects to Iom Bean.
 * Creation date: (4/26/01 2:57:56 PM)
 * @author: Hongmei Parkin
 */
public final class IomClient {
	private IomipBISHome IomHome = null;

	String aProviderURL = null;
	String IomHomeName = null;
	String origination = null;
	String initialContextPropertiesFile = null;
	/**
	 * IomClient constructor.
	 */
	public IomClient() {
		super();
	}
	/**
	 * IomClient constructor.
	 * Creation date: (9/17/01 3:57:07 PM)
	 * @param newProviderURL
	 * @param aIomHomeName
	 * @param anOrigination
	 * @param anInitialContextPropertiesFile
	 */
	public IomClient(
		String newProviderURL,
		String aIomHomeName,
		String anOrigination,
		String anInitialContextPropertiesFile) {
		aProviderURL = newProviderURL;
		IomHomeName = aIomHomeName;
		origination = anOrigination;
		initialContextPropertiesFile = anInitialContextPropertiesFile;

	}
	/**
	 * Establishes Iom Bean connection if it is not cached.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext
	 * @param aUtility
	 * @return IOMIPFacade
	 * @throws InvalidData: An input parameter contained invalid data.
	 * @throws AccessDenied: Access to the specified domain object or information is not allowed.
	 * @throws BusinessViolation: The attempted action violates a business rule.
	 * @throws SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @throws NotImplemented: The method has not been implemented.
	 * @throws ObjectNotFound: The desired domain object could not be found.
	 * @throws DataNotFound
	 */
	public final IomipBIS getIomBean(
		BisContext aContext,
		Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_2, "IomClient::getIomBean()");

		IomipBIS IomBean = null;
		
		try {
			IomBean = getIomHome(aContext, aUtility).create();
		} catch (RemoteException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_1,
				"Caught RemoteException on IomHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				IomHome = null;
				IomBean = getIomHome(aContext, aUtility).create();
			} catch (RemoteException x) {
				IomHome = null;
				aUtility.throwException(
					//ExceptionCode.ERR_IOMCL_REMOTE_EXCEPTION,
					"IOMIPException",
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ IomHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				IomHome = null;
				aUtility.throwException(
					"IOMIPException",
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ IomHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		} catch (CreateException e) {
			aUtility.log(
				LogEventId.INFO_LEVEL_1,
				"Caught CreateException on IomHome::create(): "
					+ e.getMessage());
			// The home may be stale - get a new home
			try {
				IomHome = null;
				IomBean = getIomHome(aContext, aUtility).create();

			} catch (RemoteException x) {
				IomHome = null;
				aUtility.throwException(
					"IOMIPException",
					"RemoteExeption: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ IomHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			} catch (CreateException x) {
				IomHome = null;
				aUtility.throwException(
					"IOMIPException",
					"CreateException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ IomHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}

		return IomBean;
	}
	/**
	 * Gets a Iom Bean.
	 * Creation date: (4/25/01 9:53:57 AM)
	 * @param aContext
	 * @param aUtility
	 * @param aCode
	 * @param aDescription
	 * @return IOMIPFacade
	 * @throws InvalidData: An input parameter contained invalid data.
	 * @throws AccessDenied: Access to the specified domain object or information is not allowed.
	 * @throws BusinessViolation: The attempted action violates a business rule.
	 * @throws SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @throws NotImplemented: The method has not been implemented.
	 * @throws ObjectNotFound: The desired domain object could not be found.
	 * @throws DataNotFound: No data found.
	 */
	public final IomipBIS getIomConnection(
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

		aUtility.log(LogEventId.DEBUG_LEVEL_2, "IomClient::getIomConnection()");

		IomipBIS IomBean = null;

		try {
			IomBean = getIomBean(aContext, aUtility);
		} catch (NotImplemented e) {
			throwIomException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (SystemFailure e) {
			throwIomException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (InvalidData e) {
			throwIomException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (ObjectNotFound e) {
			throwIomException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (AccessDenied e) {
			throwIomException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (BusinessViolation e) {
			throwIomException(e.aExceptionData, aUtility, aCode, aDescription);
		} catch (DataNotFound e) {
			throwIomException(e.aExceptionData, aUtility, aCode, aDescription);
		}
		return IomBean;
	}
	/**
	 * Get a Iom home.
	 * Creation date: (4/26/01 3:04:35 PM)
	 * @param aContext
	 * @param aUtility
	 * @return IOMIPFacadeHome
 	 * @throws InvalidData: An input parameter contained invalid data.
	 * @throws AccessDenied: Access to the specified domain object or information is not allowed.
	 * @throws BusinessViolation: The attempted action violates a business rule.
	 * @throws SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
	 * @throws NotImplemented: The method has not been implemented.
	 * @throws ObjectNotFound: The desired domain object could not be found.
	 * @throws DataNotFound: No data found.
	 */
	private final IomipBISHome getIomHome(BisContext aContext, Utility aUtility)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound {
		aUtility.log(LogEventId.DEBUG_LEVEL_2, "IomClient::getIomHome()");

		if (IomHome == null) {
			InitialContext initialContext = null;
			aUtility.log(
				LogEventId.DEBUG_LEVEL_2,
				"Get Iom Bean: host<"
					+ "[ provider url<"
					+ aProviderURL
					+ "> home<"
					+ IomHomeName
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
				new InitialContextFactory(
					aProviderURL,
					initialContextProperties);

			try {
				aUtility.log(
					LogEventId.DEBUG_LEVEL_2,
					"IomClient::initialContextProperties: "
						+ initialContextProperties.toString());
				initialContext =
					aContextFactory.getInitialContext(initialContextProperties);
			} catch (InitialContextFactoryException e) {
				aUtility.throwException(
					"IOMIPException",
					"InitialContextFactoryException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ IomHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}

			try {
				Object o = initialContext.lookup(IomHomeName);
				IomHome =
					(IomipBISHome) javax.rmi.PortableRemoteObject.narrow(
						o,
						IomipBISHome.class);
			} catch (NamingException e) {
				aUtility.throwException(
					"IOMIPException",
					"NamingException: "
						+ "[ provider url<"
						+ aProviderURL
						+ "> home<"
						+ IomHomeName
						+ "> ] "
						+ e.getMessage(),
					origination,
					Severity.UnRecoverable);
			}
		}
		return IomHome;
	}
	/**
	 * Throws Iom Exception.
	 * Creation date: (5/7/01 12:43:31 PM)
	 * @param aExceptionData
	 * @param aUtility
	 * @param aCode
	 * @param aDescription
	 * @throws InvalidData
	 * @throws AccessDenied
	 * @throws BusinessViolation
	 * @throws SystemFailure
	 * @throws NotImplemented
	 * @throws ObjectNotFound
	 * @throws DataNotFound
	 */
	public void throwIomException(
		ExceptionData aExceptionData,
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
		String aOrigination = null;
		Severity aSeverity = null;

		try {
			aOrigination = aExceptionData.aOrigination.theValue();
		} catch (Exception e) {
		}
		try {
			aSeverity = aExceptionData.aSeverity.theValue();
		} catch (Exception e) {
		}

		aUtility.throwException(
			aCode,
			aDescription
				+ ": "
				+ aExceptionData.aCode
				+ "|"
				+ aExceptionData.aDescription,
			aOrigination,
			aSeverity);
	}
}
