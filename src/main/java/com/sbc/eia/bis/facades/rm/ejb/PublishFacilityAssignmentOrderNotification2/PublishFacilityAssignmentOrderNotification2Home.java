//$Id: PublishFacilityAssignmentOrderNotification2Home.java,v 1.4 2008/02/23 03:35:54 ds4987 Exp $
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

package com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification2;

public interface PublishFacilityAssignmentOrderNotification2Home
	extends javax.ejb.EJBLocalHome {

	/**
	* Creates a default instance of Session Bean
	*/
	public com.sbc.eia.bis.facades.rm.ejb.PublishFacilityAssignmentOrderNotification2.PublishFacilityAssignmentOrderNotification2 create()
		throws javax.ejb.CreateException;
	}
