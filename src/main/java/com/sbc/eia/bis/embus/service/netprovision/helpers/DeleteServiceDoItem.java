// $Id: DeleteServiceDoItem.java,v 1.1 2004/10/04 21:29:25 biscvsid Exp $
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
//# 06/15/2005  Rene Duka             Creation.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;

public class DeleteServiceDoItem extends ServiceItem {

    /**
     * Constructor
     */
    public DeleteServiceDoItem() {
        super();
    }

    /**
     * Constructor
     * @param name String
     */
    public DeleteServiceDoItem(String name) {
        super(name);
    }

    /**
     * Constructor
     * @param name String
     * @param list List
     */
    public DeleteServiceDoItem(String name, List list) {
        super(name, list);
    }

    /**
     * Sets the LS Circuit ID
     * @param input
     */
    public void setCustomerTransportId(String input) {
        try {
            this.theHashElements.put(ElementName.LS_CIRCUIT_ID_2, input.trim());
        }
        catch (NullPointerException e)  {
        }
    }
}
