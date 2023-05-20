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
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------
//# 04/12/2005  Rene Duka    Creation.
//# 05/18/2005  Rene Duka    Removed setter for Order Action Type.
//# 06/27/2005  Rene Duka    Corrected the formatting of aDSLAM.aId.
//# 12/15/2005  Jyothi Pentyala Added methods for LS2.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;

public class CreateReservationFacilityAssignmentDoItem extends ServiceItem {

    /**
     * Constructor
     */
    public CreateReservationFacilityAssignmentDoItem() {
        super();
    }

    /**
     * Constructor
     * @param name String
     */
    public CreateReservationFacilityAssignmentDoItem(String name) {
        super(name);
    }

    /**
     * Constructor
     * @param name String
     * @param list List
     */
    public CreateReservationFacilityAssignmentDoItem(String name, List list) {
        super(name, list);
    }

    /**
     * Sets the LS Circuit ID
     * @param input
     */
    public void setCustomerTransportId(String input) {
        try {
            this.theHashElements.put(ElementName.LS_CIRCUIT_ID_1, input.trim());
            this.theHashElements.put(ElementName.LS_CIRCUIT_ID_2, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the LS Order Number
     * @param input
     */
    public void setOrderActionNumber(String input) {
        try {
            this.theHashElements.put(ElementName.LS_ORDER_ACTION_NUMBER, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the LS Order Action ID
     * @param input
     */
    public void setOrderActionId(String input) {
        try {
            this.theHashElements.put(ElementName.LS_ORDER_ACTION_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the Site ID
     * @param input
     */
    public void setSiteId(String input) {
        try {
            this.theHashElements.put(ElementName.SITE_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the BAN
     * @param input
     */
    public void setBAN(String input) {
        try {
            this.theHashElements.put(ElementName.BAN, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the DSLAM Physical Port
     * @param input
     */
    public void setDSLAM_PhysicalPort(String input) {
        try {
            this.theHashElements.put(ElementName.DSLAM_PHYSICAL_PORT, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the DSLAM ID
     * @param input
     */
    public void setDSLAM_Id(String input) {
        try {
            this.theHashElements.put(ElementName.DSLAM_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the DSLAM A-Side VLAN ID
     * @param input
     */
    public void setDSLAM_VLANIdA(String input) {
        try {
            this.theHashElements.put(ElementName.DSLAM_VLAN_ID_A, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the DSLAM Z-Side VLAN ID
     * @param input
     */
    public void setDSLAM_VLANIdZ(String input) {
        try {
            this.theHashElements.put(ElementName.DSLAM_VLAN_ID_Z, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the DSLAM ONT ID
     * @param input
     */
    public void setDSLAM_ONTId(String input) {
        try {
            this.theHashElements.put(ElementName.DSLAM_ONT_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the DSLAM OLT ID
     * @param input
     */
    public void setDSLAM_OLTId(String input) {
        try {
            this.theHashElements.put(ElementName.DSLAM_OLT_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the DSLAM OLT Logical Port
     * @param input
     */
    public void setDSLAM_OLTLogicalPort(String input) {
        try {
            this.theHashElements.put(ElementName.DSLAM_OLT_LOGICAL_PORT, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the 7450 Logical Egress Port
     * @param input
     */
    public void set7450_LogicaEgresslPort(String input) {
        try {
            this.theHashElements.put(ElementName.NETWORK_7450_LOGICAL_EGRESS_PORT, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the 7450 Terminal ID
     * @param input
     */
    public void set7450_TerminalId(String input) {
        try {
            this.theHashElements.put(ElementName.NETWORK_7450_TERMINAL_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the Service Bundle ID
     * @param input
     */
    public void setServiceBundleId(String input) {
        try {
            this.theHashElements.put(ElementName.SERVICE_BUNDLE_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the Access Point Policy
     * @param input
     */
    public void setAccessPointPolicy(String input) {
        try {
            this.theHashElements.put(ElementName.ACCESS_POINT_POLICY, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the VPLS Domain
     * @param input
     */
    public void setVPLSDomain(String input) {
        try {
            this.theHashElements.put(ElementName.VPLS_DOMAIN, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the BisContext User Id
     * @param input
     */
    public void setBisContext_UserId(String input) {
        try {
            this.theHashElements.put(ElementName.BISCONTEXT_USERID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the BisContext Interface Name
     * @param input
     */
    public void setBisContext_InterfaceName(String input) {
        try {
            this.theHashElements.put(ElementName.BISCONTEXT_INTERFACE_NAME, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the BisContext Request Id
     * @param input
     */
    public void setBisContext_RequestId(String input) {
        try {
            this.theHashElements.put(ElementName.BISCONTEXT_REQUEST_ID, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

    /**
     * Sets the BisContext Logical Server
     * @param input
     */
    public void setBisContext_LogicalServer(String input) {
        try {
            this.theHashElements.put(ElementName.BISCONTEXT_LOGICAL_SERVER, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }

	/**
	 * Sets the NetworkType
	 * @param input
	 */
	public void setNetworkType(String input) {
		try {
			theHashElements.put(ElementName.NETWORK_TYPE, input.trim());
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Sets the OpticalLineTerminalTargetId
	 * @param input
	 */
	public void setOpticalLineTerminalTargetId(String input) {
		try {
			theHashElements.put(
				ElementName.OPTICAL_LINE_TERMINAL_TID,
				input.trim());
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Sets the OpticalNetworkTerminalAccessId
	 * @param input
	 */
	public void setOpticalNetworkTerminalAccessId(String input) {
		try {
			theHashElements.put(
				ElementName.OPTICAL_NETWORK_TERMINAL_AID,
				input.trim());
		} catch (NullPointerException e) {
		}
	}    
	
	public void setBillingTelephoneNumber(String input) {
		try {
			theHashElements.put(
				ElementName.BILLING_TELEPHONE_NUMBER,
				input.trim());
		} catch (NullPointerException e) {
		}
	}    
	
	public void setCustomerFirstName(String input) {
		try {
			theHashElements.put(
				ElementName.CUSTOMER_FIRST_NAME,
				input.trim());
		} catch (NullPointerException e) {
		}
	}    
	
	public void setCustomerLastName(String input) {
		try {
			theHashElements.put(
				ElementName.CUSTOMER_LAST_NAME,
				input.trim());
		} catch (NullPointerException e) {
		}
	}    
}
