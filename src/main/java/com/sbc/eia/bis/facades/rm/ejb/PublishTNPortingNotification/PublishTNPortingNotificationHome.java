//$Id: PublishTNPortingNotificationHome.java,v 1.1 2006/06/15 22:06:04 jp2854 Exp $
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
//# 06/05/2006  Jyothi Jasti         Creation for LS3.

package com.sbc.eia.bis.facades.rm.ejb.PublishTNPortingNotification;

public interface PublishTNPortingNotificationHome
	extends javax.ejb.EJBLocalHome {

	/**
	 * Creates a default instance of Session Bean
	 * @return the EJBLocalObject
	 * @throws javax.ejb.CreateException
	 */
	public com
		.sbc
		.eia
		.bis
		.facades
		.rm
		.ejb
		.PublishTNPortingNotification
		.PublishTNPortingNotification create()
		throws javax.ejb.CreateException;
}
