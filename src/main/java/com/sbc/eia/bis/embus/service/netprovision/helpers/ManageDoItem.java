//###############################################################################
//#
//#   Copyright Notice:
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
//# Date      | Author      | Notes
//# ----------------------------------------------------------------------------
//# 06/12/05      ck2932       Creation.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;
/**
 * @author CK2932
 */

public class ManageDoItem extends ServiceItem  {
	
    /**
     * Constructor
     */
    public ManageDoItem() {
        super();
    }

    /**
     * Constructor
     * @param name String
     */
    public ManageDoItem(String name) {
        super(name);
    }

    /**
     * Constructor
     * @param name String
     * @param list List
     */
    public ManageDoItem(String name, List list) {
        super(name, list);
    }	
    
    /**
     * Sets the Circuit Id
     * @param input
     */
    public void setCircuitId(String input) {
        try {
            this.theHashElements.put(ElementName.LS_CIRCUIT_ID_2, input.trim());
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
      
}