// $Id: ResourcesForServiceByTN.java,v 1.12 2005/08/23 18:42:29 vc7563 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ResourcesForServiceByTN;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.BusinessInterface.Business;
import com.sbc.eia.bis.BusinessInterface.BusinessException;
import com.sbc.eia.bis.BusinessInterface.Company;
import com.sbc.eia.bis.BusinessInterface.CompanyFactory;
import com.sbc.eia.bis.BusinessInterface.Host;
import com.sbc.eia.bis.BusinessInterface.InvalidCompanyException;
import com.sbc.eia.bis.BusinessInterface.InvalidServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.InvalidStateException;
import com.sbc.eia.bis.BusinessInterface.NotImplementedException;
import com.sbc.eia.bis.BusinessInterface.NullDataException;
import com.sbc.eia.bis.BusinessInterface.Selector;
import com.sbc.eia.bis.BusinessInterface.ServiceCenter;
import com.sbc.eia.bis.BusinessInterface.State;
import com.sbc.eia.bis.BusinessInterface.UnknownServiceCenterException;
import com.sbc.eia.bis.BusinessInterface.rm.LERG.LERG;
import com.sbc.eia.bis.BusinessInterface.rm.LERG.PortabilityStatusIntf;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.rm.components.NamClientGetNetworkAddress;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BisContextProperty;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;
import com.sbc.gwsvcs.service.hostlookup.HOSTLOOKUPHelper;
/**
 * Implements the ResourcesForServiceByTNIntf Business Interface which defines service resources functionality by 
 *	working telephone number.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 
#   History :
#   Date      	| Author        | Version	| Notes
#   ----------------------------------------------------------------------------
# 	03/04/2002	Mark Liljequist 2.0			Update for exception parser in retrieveResources.
#	09/16/2002	Mark Liljequist	6.0.0		Added the RM GNA components.
#	06/28/2003	Sumana Roy		7.0.2		Added retrieveResources() for 6-digit service handle.

# 	07/13/2004	Stevan Dunkin		9.0.0
#	RM 138478   Added logic to log a 7-digit NPA/NXX/X	
#
#	04/22/2005  Roland Pates	11.0.2		Added logic to the getCLLI call at retrieveResources() 
#											for ported and non-ported TN
*/
 
public class ResourcesForServiceByTN extends Host 
	implements ResourcesForServiceByTNIntf
{
	// Helper class to get the state code using the phone number.
	
	private HOSTLOOKUPHelper hostHelper= null;
	
	// The list of immediate children (classes that derive from this class)
	private final static String[] hostList = null;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			ResourcesForServiceByTNInterfaceName
		};

	// The host list for LERG access
	private static final java.lang.String[] hostNodesList = new String[]
	{
		LERG.class.getName(),
	};

	// The company factory for the next level
	public com.sbc.eia.bis.BusinessInterface.CompanyFactory companyFactory = null;
	
	private NamClientGetNetworkAddress theGNA = null;
	
/**
 * Class constructor accepting runtime control parameters.
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @param aProperties java.util.Hashtable
 */
public ResourcesForServiceByTN(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
{
	super(aCompany, aUtility, aProperties);
}
/**
 * Called by the Host Factory to preload entries in the Host cache.
 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getCacheEntries method.
 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Selector[] getCacheEntries(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ResourcesForService::getCacheEntries()");

	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] {
		new Selector(new Company(Company.Company_Ameritech, State.getAnAnyState(), null, null),
			ResourcesForServiceByTN.ResourcesForServiceByTNInterfaceName, ResourcesForServiceByTN.class.getName()),
		new Selector(new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null),
			ResourcesForServiceByTN.ResourcesForServiceByTNInterfaceName, ResourcesForServiceByTN.class.getName()),
		new Selector(new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null),
			ResourcesForServiceByTN.ResourcesForServiceByTNInterfaceName, ResourcesForServiceByTN.class.getName()),
		new Selector(new Company(Company.Company_SBCTelecom, State.getAnAnyState(), null, null),
			ResourcesForServiceByTN.ResourcesForServiceByTNInterfaceName, ResourcesForServiceByTN.class.getName()),
		new Selector(new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null),
			ResourcesForServiceByTN.ResourcesForServiceByTNInterfaceName, ResourcesForServiceByTN.class.getName())
	};
		
}
/**
 * Called by the Host Factory to obtain a list of hosts that are the immediate
 * 	children of the called class.
 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getHostList method.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getHostList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ResourcesForService::getHostList()");

	// Return the list of immediate children (classes that derive from this class)
	return hostList;
}
/**
 * Called by the Host Factory to obtain a list of the interfaces 
 *	that are the supported by the called class.
 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getInterfaceList method.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getInterfaceList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ResourcesForService::getInterfaceList()");

	// Return the list of interfaces supported by this host
	return interfaceList;
}
/**
 * Called by the Host Factory to obtain a list of the companies 
 *	that are the supported by the called class.
 * 	This method is an override of the com.sbc.eia.bis.BusinessInterface.getSupportedCompanies method.
 * @return com.sbc.eia.bis.BusinessInterface.Company[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ResourcesForService::getSupportedCompanies()");

	// Return the company/state combination supported by this host
	return new Company[] {
		new Company(Company.Company_Ameritech, State.getAnAnyState(), null, null),
		new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null),
		new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null),
		new Company(Company.Company_SBCTelecom, State.getAnAnyState(), null, null),
		new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null)
	};
}
/**
 * Retrieves the resources for a service by working telephone number.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation[]
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aContextOPM com.sbc.bccs.idl.helpers.ObjectPropertyManager
 * @param aTN com.sbc.bccs.idl.helpers.TN
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */
public ResourceRoleInformation[] retrieveResources(
	BisContext aContext,
	ObjectPropertyManager aContextOPM,
	TN aTN,
	String anLRN)
	throws
		AccessDenied,
		BusinessViolation,
		InvalidData,
		NotImplemented,
		ObjectNotFound,
		SystemFailure,
		DataNotFound {
	String myMethodName = "ResourcesForServiceByTN::retrieveResources()";
	utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
			

	TN aWorkTN = aTN;

	// Build the company factory
	if (companyFactory == null) {
		try {
			companyFactory = new CompanyFactory(hostNodesList, utility, getProperties());
		} catch (BusinessException e) {
			utility.throwException(
				ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
				"Internal Error: Business Interface Exception: "
					+ e.getExceptionCode()
					+ ":"
					+ e.getMessage(),
				Business.BUSINESS_INTERFACE,
				Severity.UnRecoverable,
				e);
		}
	}

	// Find the host object and call the getCLLI method
	ResourceRoleInformation aCLLIInfo = null;
	try {
		utility.log(LogEventId.DEBUG_LEVEL_1, "Calling getCLLI() for 10-digit TN ");
		aCLLIInfo =
			(
				(PortabilityStatusIntf) companyFactory
					.getCompany(
						new ServiceCenter(aContextOPM.getValue(BisContextProperty.SERVICECENTER)))
					.getBusinessInterface(PortabilityStatusIntf.PortabilityStatusInterfaceName))	
					.getCLLI(
				aContext,
				aWorkTN,
				anLRN == null ? anLRN : anLRN.substring(0, 6));					
	} catch (InvalidServiceCenterException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_INVALID_SERVICE_CENTER,
			TranBase.formatInvalidData(
				BisContext.class,
				"aContext.aProperties",
				aContextOPM.getValue(BisContextProperty.SERVICECENTER),
				"Invalid Service Center: " + e.getExceptionCode() + ":" + e.getMessage()),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable);
	} catch (UnknownServiceCenterException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_UNKNOWN_SERVICE_CENTER,
			TranBase.formatInvalidData(
				BisContext.class,
				"aContext.aProperties",
				aContextOPM.getValue(BisContextProperty.SERVICECENTER),
				"Unknown Service Center: " + e.getExceptionCode() + ":" + e.getMessage()),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable);
	} catch (NotImplementedException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
			"Functionality not implemented for service center: "
				+ aContextOPM.getValue(BisContextProperty.SERVICECENTER)
				+ ". "
				+ e.getExceptionCode()
				+ ":"
				+ e.getMessage(),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable);
	} catch (com.sbc.eia.bis.BusinessInterface.BusinessException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
			"Internal Error: Business Interface Exception: "
				+ e.getExceptionCode()
				+ ":"
				+ e.getMessage(),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable,
			e);
	}

	// Build and return the result.
	
	return new ResourceRoleInformation[] {	
			aCLLIInfo
		};
}

/**
 * Retrieves the resources for a service by working 6 digit service handle.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation[]
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aContextOPM com.sbc.bccs.idl.helpers.ObjectPropertyManager
 * @param strNpaNxx java.lang.String
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */
// RM:96232 This method is to handle the 6 digit Service Handle that is recieved from EAO through OBF.

public ResourceRoleInformation[] retrieveResources(
	BisContext aContext,
	ObjectPropertyManager aContextOPM,
	String srtNpaNxx)
	throws
		AccessDenied,
		BusinessViolation,
		InvalidData,
		NotImplemented,
		ObjectNotFound,
		SystemFailure,
		DataNotFound {
	String myMethodName = "ResourcesForServiceByTN::retrieveResources()";
	utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
	
		String TXN001 = "TESTCASEXN001=RetrieveResourcesForService" + 
						"|BisContext" +
						"|ServiceCenter|" + aContextOPM.getValue(BisContextProperty.SERVICECENTER) +
		 				"|Application|rmbis|CustomerName|CLLI|end" +
						"|ServiceHandle|" + srtNpaNxx + "|com.sbc.eia.bis.TelephoneNumber" +
						"|ResourceRoleNames|ServingSwitch|end|";
		
		utility.log(LogEventId.INFO_LEVEL_2, TXN001);
				
// Skiping the call to NAM for EAO
	
	// Build the company factory
		
	if (companyFactory == null) {
		try {
			companyFactory = new CompanyFactory(hostNodesList, utility, getProperties());
			utility.log(LogEventId.DEBUG_LEVEL_2, "Company Factory Object::" + companyFactory);	
			
		} catch (BusinessException e) {
			utility.throwException(
				ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
				"Internal Error: Business Interface Exception: "
					+ e.getExceptionCode()
					+ ":"
					+ e.getMessage(),
				Business.BUSINESS_INTERFACE,
				Severity.UnRecoverable,
				e);
		}
	}

	// Find the host object and call the getCLLI method
	ResourceRoleInformation aCLLIInfo = null;
	try {
		utility.log(LogEventId.DEBUG_LEVEL_1, (srtNpaNxx.length() == 6                          
												? "Calling getCLLI() for 6-digit Npa/Nxx"       
												: "Calling getCLLI() for 7-digit Npa/Nxx/X"));	
		aCLLIInfo =	( (PortabilityStatusIntf) companyFactory
						.getCompany(
							new ServiceCenter(aContextOPM.getValue(BisContextProperty.SERVICECENTER)))
						.getBusinessInterface(PortabilityStatusIntf.PortabilityStatusInterfaceName))
						.getCLLI(aContext,srtNpaNxx);
					
	} catch (InvalidServiceCenterException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_INVALID_SERVICE_CENTER,
			TranBase.formatInvalidData(
				BisContext.class,
				"aContext.aProperties",
				aContextOPM.getValue(BisContextProperty.SERVICECENTER),
				"Invalid Service Center: " + e.getExceptionCode() + ":" + e.getMessage()),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable);
	} catch (UnknownServiceCenterException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_UNKNOWN_SERVICE_CENTER,
			TranBase.formatInvalidData(
				BisContext.class,
				"aContext.aProperties",
				aContextOPM.getValue(BisContextProperty.SERVICECENTER),
				"Unknown Service Center: " + e.getExceptionCode() + ":" + e.getMessage()),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable);
	} catch (NotImplementedException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
			"Functionality not implemented for service center: "
				+ aContextOPM.getValue(BisContextProperty.SERVICECENTER)
				+ ". "
				+ e.getExceptionCode()
				+ ":"
				+ e.getMessage(),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable);
	} catch (com.sbc.eia.bis.BusinessInterface.BusinessException e) {
		utility.throwException(
			ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
			"Internal Error: Business Interface Exception: "
				+ e.getExceptionCode()
				+ ":"
				+ e.getMessage(),
			Business.BUSINESS_INTERFACE,
			Severity.UnRecoverable,
			e);
	}

	// Build and return the result.
	
	return new ResourceRoleInformation[] {	
			aCLLIInfo
		};
}

}
