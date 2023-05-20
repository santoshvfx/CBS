//$Id: ServiceItemFactory.java,v 1.6 2006/08/11 23:07:47 ml2917 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of SBC Services Inc. and authorized Affiliates of SBC Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      © 2002-2005 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 9/27/2004   Mark Liljequist       Creation.
//# 10/25/2004  Stevan Dunkin         Added Query policy object to constructObject() method.
//# 10/26/2004  Jinmin Ni             Added CircuitLayoutRecordDesignElementItem object to
//#                                   constructObject() method
//# 10/28/2004  Jinmin Ni             Added CreateReservationPortsDoReturnItem and CreateDesignDoItem
//#                                   object to constructoOjbect() method
//# 10/28/2004  Vickie Ng             Added a PhysicalPortInformationItem
//#                                   object to constructoOjbect() method
//# 11/02/2004  Mark Liljequist       Added factory lookup for connection
//# 11/10/2003 Mark Liljequist        Change for Implement.
//# 12/28/2004  Jinmin Ni             Replace DeleteImplemetationDoReturnItem with
//#                                   DeleteImplementationDoReturnItem for DR122277
//# 01/21/2005  Jinmin Ni             Added factory lookup for channel object
//# 04/15/2005  Jinmin Ni             Copy form netprovision.utities
//#                                   Remove all the look up for INL transaction
//# 05/24/2005  Rene Duka             Added lookup for reserveActivateFacilityAssignment transaction.
//# 05/25/2005  jp2854                Added factory lookup for ModifyModifyDo return item
//# 06/07/2005  Manjula Goniguntla    Added factory lookup for PublishAutoDiscoveryResults return item
//# 06/15/2005  Rene Duka             Added factory lookup for disconnectService transaction.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;

public class ServiceItemFactory
{

    /**
     * Method getInstance.
     * @param name
     * @param list
     * @return ServiceItem
     * @throws NullResourcesForServiceException
     */

    public static ServiceItem getInstance(String name, List list)
        throws NullResourcesForServiceException
    {

        ServiceItem item = constructObject(name);
        item.setElements(list);
        return item;

    }

    /**
     * Method getInstance.
     * @param name
     * @return ServiceItem
     * @throws NullResourcesForServiceException
     */

    public static ServiceItem getInstance(String name)
        throws NullResourcesForServiceException
    {

        return constructObject(name);

    }

    /**
     * Method constructObject.
     * @param name
     * @return ServiceItem
     * @throws NullResourcesForServiceException
     */

    private static ServiceItem constructObject(String name)
        throws NullResourcesForServiceException
    {

        if (name.equalsIgnoreCase(ItemName.RESERVE_ACTIVATE_FACILITY_ASSIGNMENT))
            return new CreateReservationFacilityAssignmentDoReturnItem(name);
        
        if (name.equalsIgnoreCase(ItemName.DISCONNECT_SERVICE))
            return new DeleteServiceDoReturnItem(name);
        
        if (name.equalsIgnoreCase(ItemName.MODIFY_MODIFY_DO))
            return new ModifyModifyDoReturnItem(name);
            
        if (name.equalsIgnoreCase(ItemName.PUBLISH_AUTO_DISCOVERY_RESULTS))
            return new PublishAutoDiscoveryResultsDoReturnItem(name);            

        throw new NullResourcesForServiceException(
            "No object instantiated for Item: " + name + ".");
    }

}
