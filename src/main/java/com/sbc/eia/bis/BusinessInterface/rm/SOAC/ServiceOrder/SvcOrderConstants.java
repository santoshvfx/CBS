//$Id: SvcOrderConstants.java,v 1.13 2010/09/17 22:37:29 js7440 Exp $
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
//#      © 2002-2006 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 07/2006		Sriram Chevuturu      Creation
//# 10/14/2006	Doris Sunga			  LS R4: adding GPON network type
//# 10/21/2007  Doris Sunga			  LS R6: adding OMS & BBNMS Application id & indicator
//# 10/25/2007  Doris Sunga			  LS R6 CR 14149:  added PFAO_TRANSACTION & MFI_TRANSACTION constants
//# 01/29/2008  Doris Sunga			  LS R7: added PFAO2 & MFI2 constants
//# 09/16/2010  Julius Sembrano       Added UBCO and IBRT Network Types
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.ServiceOrder;

import com.sbc.eia.idl.rm_ls_types.NetworkType2Values;
import com.sbc.eia.idl.rm_ls_types.NetworkTypeValues;
import com.sbc.eia.idl.rm_ls_types.NetworkType3Values;

/**
 * @author sc8468
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class SvcOrderConstants
{

    protected final static String NEW_CONNECT_DISCONNECT_ACTION_TYPE = "PRE";

    protected final static String NEW_CONNECT_ACTION_INDICATOR = "I";
    protected final static String DISCONNECT_ACTION_INDICATOR = "O";
    protected final static String CHANGE_ACTION_INDICATOR = "C";

    protected final static String DUE_DATE_SUB_ACTION_TYPE = "COR";
    protected final static String CANCELLATION_SUB_ACTION_TYPE = "CAN";
    protected final static String COMPLETION_SUB_ACTION_TYPE = "PCN";

    public static final String F1TYPE = "F1";
    public static final String F2TYPE = "F2";

    //protected static final String NETWORK_TYPE = null;
    public static final String FTTP_NETWORK = NetworkTypeValues.FTTP; //map to FTTP
    public static final String FTTPIP_NETWORK = NetworkType2Values.FTTP; //map to FTTPIP	
    public static final String FTTNBP_NETWORK = NetworkType2Values.FTTNBP;
    public static final String BOND_NETWORK_INDICATOR = "BOND";
    public static final String FTTN_NETWORK = NetworkTypeValues.FTTN;
    public static final String RGPON_NETWORK = NetworkTypeValues.RGPON;
    public static final String RGPN_NETWORK = "RGPN";
    public static final String VOIP_NETWORK = "VOIP";
    public static final String GPON_NETWORK = "GPON";

    public static final String OMS_APPLICATIONID = "OMS";
    public static final String OMS_APPLICATIONINDICATOR = "O";
    public static final String BBNMS_APPLICATIONID = "BBNMS";
    public static final String BBNMS_APPLICATIONINDICATOR = "B";

    public static final String PFAO_TRANSACTION = "PublishFacilityAssignmentOrderNotification";
    public static final String MFI_TRANSACTION = "ModifyFacilityInfo";

    public static final String AMEND_SUB_ACTION_TYPE = "Amend";
    public static final String CHANGE_ORDER_ACTION_TYPE = "Change";
    public static final String PFAO2_TRANSACTION = "PublishFacilityAssignmentOrderNotification2";
    public static final String MFI2_TRANSACTION = "ModifyFacilityInfo2";

    // values for SFAO3/PFAO3 Transactions
    public static final String PFAO3_TRANSACTION = "PublishFacilityAssignmentOrderNotification3";
    public static final String IPCO_NETWORK = NetworkType3Values.IPCO;
    public static final String IPCO = "IPCO";
    public static final String IPRT_NETWORK = NetworkType3Values.IPRT;
    public static final String IPRT = "IPRT";
    
    public static final String IPCOBP_NETWORK = "IBCO";
    public static final String IPRTBP_NETWORK = "IBRT";
}