
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
//#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author        | Notes
//# ----------------------------------------------------------------------------
//# 8/17/04     Jinmin Ni       RM141220: exception for OCN by clli inquiry

/**
 * @author jn1936
 * Created on Jul 26, 2004
 */

package com.sbc.eia.bis.BusinessInterface.rm.ServiceProviders;

public class ClliToSwitchOwnerNotFoundException extends Throwable
{

    /**
     * Constructor for ClliToSwitchOwnerNotFoundException.
     */
    public ClliToSwitchOwnerNotFoundException()
    {
        super();
    }
    
    /**
     * Constructor for ClliToSwitchOwnerNotFoundException
     */
    public ClliToSwitchOwnerNotFoundException ( String s ) {
        super ( s );
    }

}
