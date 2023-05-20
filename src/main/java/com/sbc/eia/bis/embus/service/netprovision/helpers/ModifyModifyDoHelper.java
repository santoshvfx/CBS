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
//# 05/16/05      va6483       Creation.
//# 05/25/05      jp2854       Updated SERVICE_REQUEST and SERVICE_OPERATION fields

package com.sbc.eia.bis.embus.service.netprovision.helpers;

public class ModifyModifyDoHelper extends RequestItem {

    public static final String SERVICE_REQUEST = "Modify";
    public static final String SERVICE_OPERATION = "Modify";
    public static final String ACTION = "Do";
    public static final String NOTES = "";
    public static final String RESULT_POLICY = "";

    /**
     * constructor ModifyModifyDoHelper.
     * @param className
     * @param item
     */

    public ModifyModifyDoHelper(String className, ServiceItem item) {
        super(SERVICE_REQUEST, SERVICE_OPERATION, ACTION, NOTES, RESULT_POLICY, className, item);
    }
}

