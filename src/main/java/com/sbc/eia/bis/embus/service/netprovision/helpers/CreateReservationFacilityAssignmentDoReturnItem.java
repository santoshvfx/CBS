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
//#       (C) SBC Services, Inc 2005.  All Rights Reserved.
//#
//# History :
//# Date      | Author            | Notes
//# ----------------------------------------------------------------------------
//# 04/12/2005  Rene Duka          Creation.
//# 12/15/2005  Jyothi Pentyala    Added methods for LS2.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;

public class CreateReservationFacilityAssignmentDoReturnItem extends ServiceItem {

    /**
     * Constructor
     * @param name String
     */
    public CreateReservationFacilityAssignmentDoReturnItem(String name) {
        super(name);
    }
    /**
     * Construction
     * @param name String
     * @param list List
     */
    public CreateReservationFacilityAssignmentDoReturnItem(String name, List list) {
        super(name, list);
    }

    /**
     * Returns the Object ID
     * @return String
     */
    public String getObjectId() {
        return (String) theHashElements.get(ElementName.OBJECT_ID);
    }

    /**
     * Returns the Service Access Point
     * @return String
     */
    public String getServiceAccessPoint() {
        return (String) theHashElements.get(ElementName.SERVICE_ACCESS_POINT);
    }

	/**
	* Returns Virtual Path Indicator
	* @return String
	*/
	public String getVirtualPathIndicator() {
		return (String) theHashElements.get(ElementName.VIRTUAL_PATH_INDICATOR);
	}

	/**
	* Returns the Virtual Channel Indicator
	* @return String
	*/
	public String getVirtualChannelIndicator() {
		return (String) theHashElements.get(
			ElementName.VIRTUAL_CHANNEL_INDICATOR);
	}

	/**
	* Returns VLAN ID
	* @return String
	*/
	public String getVLANID() {
		return (String) theHashElements.get(ElementName.VLANID);
	}

}
