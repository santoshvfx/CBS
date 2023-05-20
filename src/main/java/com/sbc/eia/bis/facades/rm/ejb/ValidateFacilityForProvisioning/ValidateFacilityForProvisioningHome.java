//$Id: ValidateFacilityForProvisioningHome.java,v 1.1 2009/01/07 22:26:53 hw7243 Exp $
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
//# 12/21/2007  Hongmei Parkin	     Creation
//# 01/23/2008	Deepti Nayar		Modified for LS7



package com.sbc.eia.bis.facades.rm.ejb.ValidateFacilityForProvisioning;

public interface ValidateFacilityForProvisioningHome
extends javax.ejb.EJBLocalHome {

/**
* Creates a default instance of Session Bean
*/

public com.sbc.eia.bis.facades.rm.ejb.ValidateFacilityForProvisioning.ValidateFacilityForProvisioning create()
throws javax.ejb.CreateException;
}
