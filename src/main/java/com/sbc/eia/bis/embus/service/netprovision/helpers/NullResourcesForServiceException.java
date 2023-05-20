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
//# Date      | Author        		| Notes
//# ----------------------------------------------------------------------------
//# 9/27/2004   Mark Liljequist       Creation.
//# 8/10/2006   Mark Liljequist       Moved to helper package for rmim split.




package com.sbc.eia.bis.embus.service.netprovision.helpers;

/**
 * @author ml2917
 *
 */

public class NullResourcesForServiceException extends Exception {

	public NullResourcesForServiceException(String message) {

		super(message);
	}

}
