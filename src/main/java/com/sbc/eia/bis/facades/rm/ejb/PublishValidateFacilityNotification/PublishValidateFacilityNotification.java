//$Id: PublishValidateFacilityNotification.java,v 1.4 2009/01/21 19:16:39 hw7243 Exp $
//############################################################################
//#
//# Copyright Notice:
//#
//#     RESTRICTED - PROPRIETARY INFORMATION
//#      The information herein is for use only by authorized employees
//#      of AT&T Services, Inc. and authorized Affiliates of AT&T Services,
//#      Inc., and is not for general distribution within or outside the
//#      respective companies.
//#      Copying or reproduction without prior written approval is prohibited.
//#
//#      Copyright © 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 07/16/2007  Rene Duka             Creation.
//# 11/06/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.

package com.sbc.eia.bis.facades.rm.ejb.PublishValidateFacilityNotification;

import com.sbc.eia.bis.RmNam.utilities.Logger;

public interface PublishValidateFacilityNotification 
	extends javax.ejb.EJBLocalObject 
{

	/**
	 * publishValidateFacilityNotification
     * 
     * @param aMessage
	 * @param aLogger
	 * @throws Exception
	 */
	public void publishValidateFacilityNotification(
			javax.jms.Message  aMessage,
		Logger aLogger)
		throws
			Exception;
}