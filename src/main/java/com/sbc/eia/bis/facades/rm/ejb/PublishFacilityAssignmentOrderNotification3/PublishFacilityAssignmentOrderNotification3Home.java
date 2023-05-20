//$Id: PublishFacilityAssignmentOrderNotification3Home.java,v 1.2 2009/02/03 23:36:00 ch1463 Exp $
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
//# 02/21/2008  Doris Sunga           Creation.

package com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification3;

public interface PublishFacilityAssignmentOrderNotification3Home
	extends javax.ejb.EJBLocalHome {

	/**
	* Creates a default instance of Session Bean
	*/
	public com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification3.PublishFacilityAssignmentOrderNotification3 create()
		throws javax.ejb.CreateException;
	}
