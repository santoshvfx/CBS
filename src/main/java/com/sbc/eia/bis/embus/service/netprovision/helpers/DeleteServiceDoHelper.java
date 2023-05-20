// $Id: DeleteServiceDoHelper.java,v 1.1 2004/10/04 21:29:25 biscvsid Exp $
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
//# 07/06/2005  Rene Duka             Modified SERVICE_REQUEST and SERVICE_OPERATION to have upper-case "D".

package com.sbc.eia.bis.embus.service.netprovision.helpers;

public class DeleteServiceDoHelper extends RequestItem {

    public static final String SERVICE_REQUEST = "Delete";
    public static final String SERVICE_OPERATION = "Delete";
    public static final String ACTION = "Do";
    public static final String NOTES = "";
    public static final String RESULT_POLICY = "";

    /**
     * constructor DeleteServiceDoHelper.
     * @param className
     * @param item
     */

    public DeleteServiceDoHelper(String className, ServiceItem item) {
        super(SERVICE_REQUEST, SERVICE_OPERATION, ACTION, NOTES, RESULT_POLICY, className, item);
    }
}

