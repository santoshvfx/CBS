// $Id: ServiceProvidersForResourceIntf.java,v 1.3 2005/04/05 15:45:30 kk8467 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;

import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.rm.*;
/**
 * Defines the business interface for ServiceProvidersForResource.
 * Creation date: (4/18/01 7:26:34 AM)
 * @author: Sam Lok
 */
public interface ServiceProvidersForResourceIntf extends com.sbc.eia.bis.BusinessInterface.Business {
	java.lang.String ServiceProvidersForResourceInterfaceName = ServiceProvidersForResourceIntf.class.getName() ;
/**
 * Supports the retrieveServiceProvidersForResource business transaction.
 * Creation date: (4/25/01 9:10:57 AM)
 * @return RetrieveServiceProvidersForResourceReturn
 * @param aContext BisContext
 * @param aResourceHandle ObjectHandle
 * @param aServiceTypeHandles ObjectHandle[]
 * @exception InvalidData: An input parameter contained invalid data.
 * @exception AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception BusinessViolation: The attempted action violates a business rule.
 * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception NotImplemented: The method has not been implemented.
 * @exception ObjectNotFound: The desired domain object could not be found.
 * @exception DataNotFound: No data found.
 */
RetrieveServiceProvidersForResourceReturn retrievePicByCLLI( BisContext aContext,
                                                             String aCLLI,
															 ObjectKey[] aServiceTypeHandles)
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound;
}
