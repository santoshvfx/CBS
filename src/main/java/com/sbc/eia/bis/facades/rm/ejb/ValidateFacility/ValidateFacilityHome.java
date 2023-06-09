//$Id: ValidateFacilityHome.java,v 1.2 2007/11/13 16:56:05 rd2842 Exp $
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
//#      Copyright � 2002-2005 AT&T Knowledge Ventures.
//#      All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------
//# 07/11/2007  Rene Duka             Creation.
//# 11/06/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.

package com.sbc.eia.bis.facades.rm.ejb.ValidateFacility;

public interface ValidateFacilityHome
  extends javax.ejb.EJBLocalHome {

/**
* Creates a default instance of Session Bean
*/

public com.sbc.eia.bis.facades.rm.ejb.ValidateFacility.ValidateFacility create()
  throws javax.ejb.CreateException;
}
