// $Id: ServiceProvidersBusLayer.java,v 1.5 2005/11/07 23:52:34 sr1284 Exp $
//###############################################################################
//#
//#   Copyright Notice:
//#
//#       This software/documentation is the proprietary trade secret and
//#       property of SBC Services, Inc. Receipt or possession of it does not
//#       convey any rights to divulge, reproduce, use or allow others to
//#       use it without the specific written authorization of SBC Services, Inc.
//#       Use must conform strictly to the license agreement between user and
//#       SBC Services, Inc.
//#
//#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//#	2001		Sam Lok			Creation.
//#	1/13/2004	Sam Lok			RM127209: Log level changes.
//# 11/1/2005   Sumana Roy		Added logger object to retrieveByService() for the execute method calls.

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;

import com.sbc.eia.bis.facades.rm.transactions.RetrieveServiceProvidersForResource.*;
import com.sbc.eia.bis.facades.rm.transactions.RetrieveResourcesForService.*;
import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.rm.*;
import com.sbc.eia.idl.rm_types.*;
import com.sbc.eia.idl.exception_types.*;
import com.sbc.eia.bis.BusinessInterface.*;
import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.bis.framework.logging.LogEventId;

/**
 * This class implements the retrieve() method for PB and SWBT.  This is not really a Host
 * class, but rather a intemediary class that invoke the retrieveResourceForService and
 * retrieveServiceProvidersForResource methods.
 * Creation date: (10/19/00 3:02:10 PM)
 * @author: Creighton Malet
 */
public class ServiceProvidersBusLayer extends Host 
	implements ServiceProvidersForServiceIntf
{

	// The list of supported interfaces
	private final static java.lang.String[] interfaceList =
		new String[] {	ServiceProvidersForServiceInterfaceName /*, AnotherInterface */ };
		
	private RetrieveResourcesForService myRR4Service = null;
	private RetrieveServiceProvidersForResource myRSP4Resource = null;
								
	// This belongs here - the list of immediate children (classes that derive from this class)
//		new String[] {
//			// Use this normally: HOSTTemplateChild1.class.getName()
//			null
//		};
	private static final String hostList[] = null ;
/**
 * Class constructor accepting company, utility and properties.
 * Creation date: (12/19/00 11:29:49 AM)
 * @param aCompany com.sbc.eia.bis.BusinessInterface.Company
 * @param aUtility com.sbc.bccs.utilities.Utility aUtility
 * @param aProperties java.util.Hashtable
 */
public ServiceProvidersBusLayer(Company aCompany, com.sbc.bccs.utilities.Utility aUtility, java.util.Hashtable aProperties)
{
	super(aCompany, aUtility, aProperties);
}
/**
 * Called by the Host Factory to get entries for preloading into the Host cache.
 * 	This method must be overridden.
 * @return com.sbc.eia.bis.BusinessInterface.Selector[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Selector[] getCacheEntries(com.sbc.bccs.utilities.Utility aUtility)
	throws NullDataException, InvalidCompanyException, InvalidStateException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ServiceProvidersBusLayer::getCacheEntries()");

	// Add entries to the HostFactory cache at start time to avoid long searches
	return new Selector[] { 
		new Selector(new Company(Company.Company_PacificBell,		  State.getAnAnyState(), null, null),
					 ServiceProvidersForServiceInterfaceName, ServiceProvidersBusLayer.class.getName()),
		new Selector(new Company(Company.Company_SouthWesternBell,	  State.getAnAnyState(), null, null),
					 ServiceProvidersForServiceInterfaceName, ServiceProvidersBusLayer.class.getName()),
		new Selector(new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null),
					 ServiceProvidersForServiceInterfaceName, ServiceProvidersBusLayer.class.getName())
	};
}
/**
 * Called by the Host Factory to obtain a list of hosts that are the immediate
 * 	children of the called class.
 *	This method must be overridden.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getHostList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ServiceProvidersBusLayer::getHostList()");

	// Return the list of immediate children (classes that derive from this class)
	return hostList; /* null if no children */
}
/**
 * Called by the Host Factory to obtain a list of the interfaces 
 *	that are the supported by the called class.
 * 	This method must be overridden.
 * @return java.lang.String[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 */
public static String[] getInterfaceList(com.sbc.bccs.utilities.Utility aUtility)
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ServiceProvidersBusLayer::getInterfaceList()");

	// Return the list of interfaces supported by this host
	return interfaceList;
}
/**
 * Called by the Host Factory to obtain a list of the companies 
 *	that are the supported by the called class.
 * 	This method must be overridden.
 * @return com.sbc.eia.bis.BusinessInterface.Company[]
 * @param aUtility com.sbc.bccs.utilities.Utility
 * @exception com.sbc.eia.bis.BusinessInterface.NullDataException: A required input parameter was null.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidCompanyException: An attempt was made to create an invalid company.
 * @exception com.sbc.eia.bis.BusinessInterface.InvalidStateException: An attempt was made to create an invalid state.
 */
public static Company[] getSupportedCompanies(com.sbc.bccs.utilities.Utility aUtility)
	throws InvalidCompanyException, InvalidStateException, NullDataException
{
	aUtility.log(LogEventId.DEBUG_LEVEL_1, "ServiceProvidersBusLayer::getSupportedCompanies()");

	// Return the company/state combination supported by this host
	return new Company[] { new Company(Company.Company_PacificBell, 		State.getAnAnyState(), null, null),
						   new Company(Company.Company_SouthWesternBell,	State.getAnAnyState(), null, null),
						   new Company(Company.Company_SouthernNETelephone, State.getAnAnyState(), null, null)
						};
}
/**
 * Supports the RetrieveServiceProvidersForService business transaction by utilizing the retrieveResourceForService
 * and retrieveServiceProvidersForResource transactions' execute() methods.
 * Note that try/catch exceptions are noticebly missing, because we simply pass any exception through.
 * Creation date: (5/22/01 3:16:31 PM)
 * @return RetrieveServiceProvidersForServiceReturn
 * @param aContext BisContext
 * @param aServiceHandle ObjectHandle
 * @param aServiceTypeHandle ObjectHandle[]
 * @exception InvalidData: An input parameter contained invalid data.
 * @exception AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception BusinessViolation: The attempted action violates a business rule.
 * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception NotImplemented: The method has not been implemented.
 * @exception ObjectNotFound: The desired domain object could not be found.
 * @exception DataNotFound: No data found.
 */
public
RetrieveServiceProvidersForServiceReturn retrieveByService(	BisContext aContext,
															ObjectKey aServiceHandle,
															ObjectKey[] aServiceTypeHandles,
															Logger alogger)
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound
{
	String myMethodName = "ServiceProvidersBusLayer::retrieveByService()" ;
	utility.log( LogEventId.DEBUG_LEVEL_1, myMethodName ) ;

	// first get the CLLI by TN
	utility.log( LogEventId.INFO_LEVEL_1, "Looking up Resource for Service..." ) ;

	if(myRR4Service == null)
		myRR4Service = new RetrieveResourcesForService( getProperties() ) ;
	
	RetrieveResourcesForServiceReturn aResourceResult =
			myRR4Service.execute( aContext, aServiceHandle,
											new String[]{ ResourceRoleName.SERVINGSWITCH }, alogger ) ;

	// then we lookup PIC/LPIC by CLLI
	if ( aResourceResult != null && aResourceResult.aResourceRoles != null )
	{
		utility.log( LogEventId.INFO_LEVEL_1, "Got "+aResourceResult.aResourceRoles.length+" Resources." ) ;
		for ( int i=0 ; i<aResourceResult.aResourceRoles.length ; i++ )
		{
			if ( aResourceResult.aResourceRoles[i].aRoleName == ResourceRoleName.SERVINGSWITCH )
			{
				utility.log( LogEventId.INFO_LEVEL_1, "Looking up Service Providers for Resource "+i+"..." ) ;

				if(myRSP4Resource == null)
					myRSP4Resource = new RetrieveServiceProvidersForResource(getProperties());

				RetrieveServiceProvidersForResourceReturn result = null ;
				try
				{
					result = myRSP4Resource.execute( aContext,
													 aResourceResult.aResourceRoles[i].aResourceHandle.aValue,
													 aServiceTypeHandles, alogger ) ;
				}
				catch ( ObjectNotFound oe )
				{
					if ( oe.aExceptionData.aCode.equals( ExceptionCode.ERR_RM_RESOURCE_NOT_FOUND ) ) break ;
				}

				// found what we need, just return and no need to continue the loop	
				return new RetrieveServiceProvidersForServiceReturn( aContext,
																	 result.aServiceProvidersForServiceType ) ;
			}
		}
	}
	// we didn't find anything if we gets here
	utility.throwException(	ExceptionCode.ERR_RM_SERVICE_NOT_FOUND,
							"Service not found.",
							(String)getProperties().get( "BIS_NAME" ),
							Severity.UnRecoverable ) ;
	return null ;  // will never get here.  this return is just to keep the compiler from complaining.
}
}
