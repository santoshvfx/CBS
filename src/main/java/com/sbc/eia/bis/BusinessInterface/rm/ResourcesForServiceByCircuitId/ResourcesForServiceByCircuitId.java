// $Id: ResourcesForServiceByCircuitId.java,v 1.9 2011/04/07 02:11:27 rs278j Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ResourcesForServiceByCircuitId;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
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
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.AITTIRKS;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitId;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.CircuitInformation;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.INTIRKS;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.PBTIRKS;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.SNETTIRKS;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.SWBTTIRKS;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WAInput;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.WorkAuthorizationInformation;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.framework.logging.LogEventId;
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
import com.sbc.eia.idl.rm_types.ResourceRoleName;
import com.sbc.eia.idl.types.Severity;

/*
import com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.*;

import com.sbc.eia.idl.nam.*;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.rm_types.*;
import com.sbc.eia.idl.nam_types.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.rm.*;
import com.sbc.eia.idl.exception_types.*;


import com.sbc.eia.idl.types.*;
import com.sbc.eia.bis.BusinessInterface.*;

import com.sbc.bccs.idl.helpers.*;

import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.bis.BusinessInterface.rm.TIRKS.*;
import com.sbc.eia.bis.RmNam.utilities.BISPERFLogger;
import com.sbc.eia.bis.RmNam.utilities.TranBase;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.*;
*/


/**
 * Implements the ResourcesForServiceByCircuitIdIntf Business Interface which defines service resources functionality by 
 *	exchange company circuit id.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
#   History :
#   Date      | Author        | Version      | Notes
#   ----------------------------------------------------------------------------
#	04/02/02	Mark Liljequist	4.0.0			Added Interfaces for SNET and IN TIRKS.
#
#	04/08/02	Mark Liljequist 4.0.0			Add CILLI format for CFA and M type circuits
#   in retrieveResource
#
#	02/02/2004	Stevan Dunkin	7.10.2			RM 124625	
#	RM 124625	Removed null values in ResourceRoleInformation constructor because of the addition
#	of a second constructor that does not have the OCN data in it's parameter list. 
*/
 
public class ResourcesForServiceByCircuitId extends Host 
	implements ResourcesForServiceByCircuitIdIntf
{
	// The list of immediate children (classes that derive from this class)
	private final static String[] hostList = null;

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {
			ResourcesForServiceByCircuitIdInterfaceName
		};

	// The host list for TIRKS access
	private static final java.lang.String[] hostNodesList = new String[]
	{
			PBTIRKS.class.getName(),
			SWBTTIRKS.class.getName(),
			AITTIRKS.class.getName(),
			SNETTIRKS.class.getName(),
			INTIRKS.class.getName(),
	};
	
	// The company factory for the next level
	public com.sbc.eia.bis.BusinessInterface.CompanyFactory companyFactory = null;
	
/**
 * Class constructor accepting runtime control parameters.
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @param aProperties java.util.Hashtable
 */
public ResourcesForServiceByCircuitId(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
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
			ResourcesForServiceByCircuitId.ResourcesForServiceByCircuitIdInterfaceName, ResourcesForServiceByCircuitId.class.getName()),
		new Selector(new Company(Company.Company_PacificBell, State.getAnAnyState(), null, null),
			ResourcesForServiceByCircuitId.ResourcesForServiceByCircuitIdInterfaceName, ResourcesForServiceByCircuitId.class.getName()),
		new Selector(new Company(Company.Company_SouthWesternBell, State.getAnAnyState(), null, null),
			ResourcesForServiceByCircuitId.ResourcesForServiceByCircuitIdInterfaceName, ResourcesForServiceByCircuitId.class.getName()),
		new Selector(new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null),
			ResourcesForServiceByCircuitId.ResourcesForServiceByCircuitIdInterfaceName, ResourcesForServiceByCircuitId.class.getName())		
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
		new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null)
	};
}
/**
 * Retrieves the resources for a service by circuit id.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation[]
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aContextOPM com.sbc.bccs.idl.helpers.ObjectPropertyManager
 * @param aCircuitId com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.CircuitId
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public ResourceRoleInformation[] retrieveResources(BisContext aContext, ObjectPropertyManager aContextOPM,
	CircuitId aCircuitId)
		throws	AccessDenied, BusinessViolation, InvalidData,
				NotImplemented, ObjectNotFound, SystemFailure, DataNotFound
{
	String myMethodName = "ResourcesForServiceByCircuitId::retrieveResources()";
	
	utility.log(LogEventId.DEBUG_LEVEL_1, myMethodName);
	

	// Build the company factory
	if (companyFactory == null)
	{
		try {
			companyFactory = new CompanyFactory(hostNodesList, utility, getProperties());
		}
		catch (BusinessException e)
		{
			utility.throwException(ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
				"Internal Error: Business Interface Exception: " +
				e.getExceptionCode() + ":" + e.getMessage(),
				Business.BUSINESS_INTERFACE, Severity.UnRecoverable, e);
		}
	}

	// Find the host object for work authorization and call the getWAInformation method
	WorkAuthorizationInformation aWAInfo[] = null;
	WAInput[] waArray = new WAInput[] { new WAInput("", "", "", "", aCircuitId) };

	try
	{ 
		aWAInfo = ((CircuitInformation)companyFactory.
			getCompany(new ServiceCenter(aContextOPM.getValue(BisContextProperty.SERVICECENTER))).
			getBusinessInterface(CircuitInformation.CircuitInformationInterfaceName)).
			getWAInformation(aContext, waArray);
	}
	catch (InvalidServiceCenterException e)
	{
		utility.throwException(ExceptionCode.ERR_RM_INVALID_SERVICE_CENTER,
			TranBase.formatInvalidData(BisContext.class, "aContext.aProperties",
				aContextOPM.getValue(BisContextProperty.SERVICECENTER),
				"Invalid Service Center: " + e.getExceptionCode() +	":" + e.getMessage()),
			 Business.BUSINESS_INTERFACE, Severity.UnRecoverable);
	}
	catch (UnknownServiceCenterException e)
	{
		utility.throwException(ExceptionCode.ERR_RM_UNKNOWN_SERVICE_CENTER,
			TranBase.formatInvalidData(BisContext.class, "aContext.aProperties",
				aContextOPM.getValue(BisContextProperty.SERVICECENTER),
				"Unknown Service Center: " + e.getExceptionCode() +	":" + e.getMessage()),
			Business.BUSINESS_INTERFACE, Severity.UnRecoverable);
	}
	catch (NotImplementedException e)
	{
		utility.throwException(ExceptionCode.ERR_RM_OPERATION_NOT_IMPLEMENTED,
			"Functionality not implemented for service center: " + 
			aContextOPM.getValue(BisContextProperty.SERVICECENTER) + ". " +
			e.getExceptionCode() + ":" + e.getMessage(), Business.BUSINESS_INTERFACE, Severity.UnRecoverable);
	}
	catch (com.sbc.eia.bis.BusinessInterface.BusinessException e)
	{
		utility.throwException(ExceptionCode.ERR_RM_INTERNAL_BUSINESS_INTERFACE,
			"Internal Error: Business Interface Exception: " + e.getExceptionCode() +
			":" + e.getMessage(), Business.BUSINESS_INTERFACE, Severity.UnRecoverable, e);
	}

	if (aWAInfo == null || aWAInfo.length < 1 || aWAInfo[0] == null)
		utility.throwException(
			ExceptionCode.ERR_RM_SERVICE_NOT_FOUND,
			"Service not found.",
			"CircuitInformation", Severity.UnRecoverable);

	// Look for exceptions
	if (aWAInfo[0].getException() != null)
	{
		Exception e = aWAInfo[0].getException();
		if (e.getClass().equals(AccessDenied.class)) throw (AccessDenied)e;
		if (e.getClass().equals(BusinessViolation.class)) throw (BusinessViolation)e;
		if (e.getClass().equals(InvalidData.class)) throw (InvalidData)e;
		if (e.getClass().equals(NotImplemented.class)) throw (NotImplemented)e;
		if (e.getClass().equals(ObjectNotFound.class)) throw (ObjectNotFound)e;
		if (e.getClass().equals(DataNotFound.class)) throw (DataNotFound)e;
		if (e.getClass().equals(SystemFailure.class)) throw (SystemFailure)e;
		utility.throwException(ExceptionCode.ERR_RM_UNEXPECTED_EXCEPTION_FROM_CIRCUIT_INFORMATION,
			"Unknown exception <" + e.getClass() + "> received from getWAInformation(): " +
			e.getMessage(),
			CircuitInformation.CircuitInformationInterfaceName, Severity.UnRecoverable, e);
	}

	// Build and return the result
	// If the circuit is of type CFA or Message return the requested cllis from the input.

	String aLoc, zLoc;
	
	if (aCircuitId.getType() == CircuitId.CARRIER_FACILITY || aCircuitId.getType() == CircuitId.MESSAGE) 
	{
		aLoc = aCircuitId.getLocA();
		zLoc = aCircuitId.getLocZ();
	} 
	else 
	{
		aLoc = aWAInfo[0].getLoca();
		zLoc = aWAInfo[0].getLocz();
	}
	
	return new ResourceRoleInformation[] 
	{
		new ResourceRoleInformation(ResourceRoleName.ALOCATIONTERMINATION, aLoc,
			aWAInfo[0].getAcna(), aWAInfo[0].getCcna()),
		new ResourceRoleInformation(ResourceRoleName.ZLOCATIONTERMINATION, zLoc,
			aWAInfo[0].getAcna(), aWAInfo[0].getCcna())
	};
}
}
