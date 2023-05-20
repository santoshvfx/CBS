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
//# 05/18/2005  Rene Duka    Set SERVICE_REQUEST to "" and RESULT_POLICY to "ReturnSAP".
//# 06/10/2005  Rene Duka    Remove null as one of the parameters.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

public class CreateReservationFacilityAssignmentDoHelper extends RequestItem {

    public static final String SERVICE_REQUEST = "Create";
    public static final String SERVICE_OPERATION = "Create";
    public static final String ACTION = "Do";
    public static final String NOTES = "";
    public static final String RESULT_POLICY = "ReturnSAP";

    /**
     * constructor CreateReservationFacilityAssignmentDoHelper.
     * @param className
     * @param item
     */

    public CreateReservationFacilityAssignmentDoHelper(String className, ServiceItem item) {
        super(SERVICE_REQUEST, SERVICE_OPERATION, ACTION, NOTES, RESULT_POLICY, className, item);
    }
}

