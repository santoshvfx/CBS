// $Id: ServiceProvidersForServiceIntf.java,v 1.3 2005/11/08 00:04:21 sr1284 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;

import com.sbc.eia.idl.types.*;
import com.sbc.eia.idl.bis_types.*;
import com.sbc.eia.idl.rm.*;
import com.sbc.eia.bis.RmNam.utilities.Logger;
/**
 * Defines the business interface for ServiceProvidersForService.
 * Creation date: (5/22/01 3:07:26 PM)
 * @author: Sam Lok
 */
public interface ServiceProvidersForServiceIntf extends com.sbc.eia.bis.BusinessInterface.Business {
	java.lang.String ServiceProvidersForServiceInterfaceName = ServiceProvidersForServiceIntf.class.getName();
/**
 * Supports the RetrieveServiceProvidersForService business transaction..
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
RetrieveServiceProvidersForServiceReturn retrieveByService(	BisContext aContext,
															ObjectKey aServiceHandle,
															ObjectKey[] aServiceTypeHandles, 
															Logger aLogger)
throws InvalidData, AccessDenied, BusinessViolation, SystemFailure, NotImplemented, ObjectNotFound, DataNotFound;
}
