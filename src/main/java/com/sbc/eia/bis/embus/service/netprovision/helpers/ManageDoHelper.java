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

public class ManageDoHelper extends RequestItem {

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

    public ManageDoHelper(String className, ServiceItem item) {
        super(SERVICE_REQUEST, SERVICE_OPERATION, ACTION, NOTES, RESULT_POLICY, className, item);
    }
}

