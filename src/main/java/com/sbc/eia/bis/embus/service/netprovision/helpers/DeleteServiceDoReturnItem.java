// $Id: DeleteServiceDoReturnItem.java,v 1.1 2004/10/04 21:29:25 biscvsid Exp $
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

public class DeleteServiceDoReturnItem extends ServiceItem {

    /**
     * Constructor
     * @param name String
     */
    public DeleteServiceDoReturnItem(String name) {
        super(name);
    }
    /**
     * Construction
     * @param name String
     * @param list List
     */
    public DeleteServiceDoReturnItem(String name, List list) {
        super(name, list);
    }

    /**
     * Returns the Object ID
     * @return String
     */
    public String getObjectId() {
        return (String) this.theHashElements.get(ElementName.OBJECT_ID);
    }

    /**
     * Returns the LS Circuit ID
     * @return String
     */
    public String getCustomerTransportId() {
        return (String) this.theHashElements.get(ElementName.LS_CIRCUIT_ID_2);
    }
}
