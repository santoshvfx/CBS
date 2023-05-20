//$Id: ItemName.java,v 1.7 2006/06/15 15:26:52 ck2932 Exp $
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
//# 04/15/2005  Jinmin Ni             Create
//# 04/19/2005  Rene Duka             Added items for 'reserveActivateFacilityAssignment'.
//# 05/25/2005  jp2854                Added MODIFY_MODIFY_DO item for ModifyPortAssignment
//# 06/07/2005  Manjula Goniguntla    Added elements for 'PublishAutoDiscoveryResults'. 
//# 06/15/2005  Rene Duka             Added item for 'disconnectService'.

package com.sbc.eia.bis.embus.service.netprovision.helpers;

/**
 * @author ml2917
 *
 */

public class ItemName {

    public static String RESERVE_ACTIVATE_FACILITY_ASSIGNMENT = "ReserveActivateFacilityAssignment";
    public static String DISCONNECT_SERVICE = "DisconnectService";
    public static String MODIFY_MODIFY_DO = "ModifyModifyDo";
    public static String PUBLISH_AUTO_DISCOVERY_RESULTS = "Modify";
    public static String RG = "RG";  
    public static String MANAGE = "Manage";   
}
