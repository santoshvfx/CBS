// $Id: CreateFacilityAssignmentHome.java,v 1.1 2005/03/30 20:06:08 va6483 Exp $
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
//#       (C) SBC Services, Inc 2004.  All Rights Reserved.
//#
//# History :
//# Date      | Author               | Notes
//# ----------------------------------------------------------------------------
//# 03/30/2005	Vani Sree		       Creation.

package com.sbc.eia.bis.facades.rm.ejb.CreateFacilityAssignment;

/**
 * @author va6483
 * @creation date:03/29/05
 * Local Home Interface for CreateFacilityAssignment Enterprise Bean
 * CreateFacilityAssignmentHome
 */

public interface CreateFacilityAssignmentHome
	extends javax.ejb.EJBLocalHome {

	/**
	 * Creates a default instance of Session Bean
	 */

	public com
		.sbc
		.eia
		.bis
		.facades
		.rm
		.ejb
		.CreateFacilityAssignment
		.CreateFacilityAssignment create()
		throws javax.ejb.CreateException;
}
