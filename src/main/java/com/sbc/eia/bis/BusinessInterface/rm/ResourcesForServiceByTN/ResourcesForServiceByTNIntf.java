// $Id: ResourcesForServiceByTNIntf.java,v 1.4 2005/08/23 18:42:30 vc7563 Exp $

package com.sbc.eia.bis.BusinessInterface.rm.ResourcesForServiceByTN;

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
 * ResourcesForServiceByTNIntf is the Business Interface which defines service resources functionality by 
 *	working telephone number.
 * Creation date: (2/19/01 1:01:32 PM)
 * @author: Creighton Malet
 */
public interface ResourcesForServiceByTNIntf extends com.sbc.eia.bis.BusinessInterface.Business{
	static public final String ResourcesForServiceByTNInterfaceName = "ResourcesForServiceByTNIntf";
/**
 * Retrieves the resources for a service by working telephone number.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation[]
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aContextOPM com.sbc.bccs.idl.helpers.ObjectPropertyManager
 * @param aTN com.sbc.bccs.idl.helpers.TN
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public ResourceRoleInformation[] retrieveResources(BisContext aContext, ObjectPropertyManager aContextOPM,
	TN aTN, String anLRN)
		throws 	AccessDenied, BusinessViolation, InvalidData,
				NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
/**
 * Retrieves the resources for a service by 6-digit service handle.
 * @return com.sbc.eia.bis.BusinessInterface.rm.ResourcesForService.ResourceRoleInformation[]
 * @param aContext com.sbc.eia.idl.bis_types_2_0_0.BisContext
 * @param aContextOPM com.sbc.bccs.idl.helpers.ObjectPropertyManager
 * @param aNpaNxx java.lang.String
 * @exception com.sbc.eia.idl.bis_types_2_0_0.InvalidData: An input parameter contained invalid data.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.AccessDenied: Access to the specified domain object or information is not allowed.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.BusinessViolation: The attempted action violates a business rule.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.SystemFailure: The method could not be completed due to system level errors (e.g., out of memory, loss of network connectivity)n.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.NotImplemented: The method has not been implemented.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.ObjectNotFound: The desired domain object could not be found.
 * @exception com.sbc.eia.idl.bis_types_2_0_0.DataNotFound: No data found.
 */
public ResourceRoleInformation[] retrieveResources(BisContext aContext, ObjectPropertyManager aContextOPM,
	String aNpaNxx)
		throws 	AccessDenied, BusinessViolation, InvalidData,
				NotImplemented, ObjectNotFound, SystemFailure, DataNotFound;
}