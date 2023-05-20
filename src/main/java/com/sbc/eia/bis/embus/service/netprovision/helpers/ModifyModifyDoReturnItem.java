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
//# 05/16/2005  va6483    Creation.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

import java.util.List;

public class ModifyModifyDoReturnItem extends ServiceItem {

    /**
     * Constructor
     * @param name String
     */
    public ModifyModifyDoReturnItem(String name) {
        super(name);
    }
    /**
     * Construction
     * @param name String
     * @param list List
     */
    public ModifyModifyDoReturnItem(String name, List list) {
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
     * Returns the Circuit Id
     * @return String
     */
    public String getCircuitId() {
        return (String) this.theHashElements.get(ElementName.LS_CIRCUIT_ID_2);
    }
}
