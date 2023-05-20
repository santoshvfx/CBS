//$Id: ModifyFacilityInfoHome.java,v 1.3 2008/02/07 22:13:08 ds4987 Exp $
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
//# 09/28/2007  Doris Sunga           Creation.

package com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo;

public interface ModifyFacilityInfoHome
	extends javax.ejb.EJBLocalHome {

	/**
	* Creates a default instance of Session Bean
	*/
	public com.sbc.eia.bis.facades.rm.ejb.ModifyFacilityInfo.ModifyFacilityInfo create()
		throws javax.ejb.CreateException;
	}
