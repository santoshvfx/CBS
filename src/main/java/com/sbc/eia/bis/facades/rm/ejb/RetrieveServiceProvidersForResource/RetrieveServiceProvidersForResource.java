/*
 * Created on Feb 24, 2005
 *
 */
package com.sbc.eia.bis.facades.rm.ejb.RetrieveServiceProvidersForResource;

import com.sbc.eia.bis.RmNam.utilities.Logger;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.types.StringOpt;
import com.sbc.eia.idl.types.ObjectKey;
import com.sbc.eia.idl.rm.RetrieveServiceProvidersForResourceReturn;

/**
 * @author: va6483
 * @creation date:02/24/05
 * This is an Enterprise Java Bean Local Interface
 */
public interface RetrieveServiceProvidersForResource
	extends javax.ejb.EJBLocalObject {
		
		/**
			 * @param aContext 
			 * @param aResourceHandle
			 * @param aServiceTypeHandles
			 * @return RetrieveServiceProvidersForResourceReturn
			 * @exception InvalidData: An input parameter contained invalid data.
			 * @exception AccessDenied: Access to the specified domain object or information is not allowed.
			 * @exception BusinessViolation: The attempted action violates a business rule.
			 * @exception SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
			 * @exception NotImplemented: The method has not been implemented.
			 * @exception ObjectNotFound: The desired domain object could not be found.
			 * @exception DataNotFound: No data found.
			 */
		
	public RetrieveServiceProvidersForResourceReturn retrieveServiceProvidersForResource(
			BisContext aContext, 
			String aResourceHandle, 
			ObjectKey[] aServiceTypeHandles,
			Logger aLogger)
		throws
			InvalidData,
			AccessDenied,
			BusinessViolation,
			SystemFailure,
			NotImplemented,
			ObjectNotFound,
			DataNotFound,
			java.rmi.RemoteException;
}
