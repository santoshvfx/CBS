// $Id: PortabilityStatusIntf.java,v 1.5 2003/07/22 22:06:48 sr1284 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.LERG;

import com.sbc.bccs.idl.helpers.ObjectPropertyManager;
import com.sbc.bccs.idl.helpers.TN;
import com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BisContext;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;

/**
 * PortabilityStatus is the Business Interface which defines portability status functionality.
 * Creation date: (2/19/01 1:01:32 PM)
 * @author: Creighton Malet
 */
public interface PortabilityStatusIntf extends com.sbc.eia.bis.BusinessInterface.Business{
	static public final String PortabilityStatusInterfaceName = "PortabilityStatusIntf";
/**
 * Returns the CLLI for the LRN/TN.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param aTN com.sbc.bccs.idl.helpers.TN
 * @param anLRN java.lang.String
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */
ResourceRoleInformation getCLLI(BisContext aContext, TN aTN, String anLRN)
		throws AccessDenied, BusinessViolation, InvalidData,NotImplemented, ObjectNotFound,
		SystemFailure, DataNotFound;
/**
 * Returns the CLLI for the 6-digitNpa/Nxx.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation
 * @param aContext com.sbc.eia.idl.bis_types.BisContext
 * @param	anNpaNxx java.lang.String
 * @exception com.sbc.eia.idl.bis_types.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types.DataNotFound: No data found.
 */			
ResourceRoleInformation getCLLI(BisContext aContext,String anNpaNxx)
		throws AccessDenied, BusinessViolation, InvalidData,NotImplemented, ObjectNotFound, 
		SystemFailure, DataNotFound;
}
