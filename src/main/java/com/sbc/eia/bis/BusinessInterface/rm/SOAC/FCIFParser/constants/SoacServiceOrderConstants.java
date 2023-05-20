/*
 * Created on May 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
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
//#      © 2008-2020 SBC Knowledge Ventures, L.P. All rights reserved.
//#
//# History :
//# Date      | Author              | Notes
//# --------------------------------------------------------------------------
//# 05/25/2005  SRM Team			Creation
//# 02/04/2008  Ott Phannavong		LS7: added constants for network types etc...
//# 06/06/2008  Ott Phannavong		LS8: CRC18775 remove /SSM constant
//############################################################################
package com.sbc.eia.bis.BusinessInterface.rm.SOAC.FCIFParser.constants;

import com.sbc.eia.idl.rm_ls_types.NetworkType2Values;

public class SoacServiceOrderConstants extends ParserConstants
{
    public static final String SECTION_BEGIN = "*";
    public static final String SECTION_END = ";%";
    public static final String NEWLINE = "\n";
    public static final String SLASH_R_SLASH_N = "\r\n";
    public static final String REQ_LINE_CONTINUATION = "      ";
    public static final String RESP_LINE_CONTINUATION = "     ";
    
    public static final String CONTROL_HEADER_SECTION_TAG = "C1= ";
    public static final String FUNCTION_TYPE = "FUNCTION_TYPE";
    public static final String TRANSACTION_TYPE = "SO ";
    public static final String ACTION_INDICATOR = "ACTION_INDICATOR";    
    public static final String SOAC_SERVICE_ORDER_NUM = "SOAC_SERVICE_ORDER_NUM";
    public static final String CORRECTION_SUFFIX = "CORRECTION_SUFFIX";
    public static final String WIRE_CENTER_OR_NPANXX = "WIRE_CENTER";
    public static final String ORIGINATING_HOST_CONSTANT = "SOP";
    public static final String ORIGINATING_HOST_NAME = "ORIGINATING_HOST_NAME";
    public static final String RECEIVING_HOST_NAME = "RECEIVING_HOST_NAME";
    public static final String ORIGINATING_SYSTEM_NAME = "ORIGINATING_SYSTEM_NAME";
    public static final String RECEIVING_SYSTEM_NAME_CONSTANT = "SOAC";
    public static final String ENTITY_PLATFORM = "ENTITY_PLATFORM";
    public static final String ENTITY = "ENTITY";
    public static final String DUE_DATE = "DUE_DATE";
    public static final String CONTROL_HEADER_SECTION_END_TAG = "0;%";
	public static final String APPLICATION_INDICATOR = "APPLICATION_INDICATOR";

    public static final String SPECIAL_CONDITION_SECTION_TAG = "SC=";

    public static final String ECHO_SECTION_TAG = "EC=";
    public static final String OMS_ORDER_NUM = "OMS_ORDER_NUM";
    public static final String OMS_ORDER_ACTION_NUM = "OMS_ORDER_ACTION_NUM";
    public static final String REGION_INDICATOR = "REGION_INDICATOR";
    public static final String RESEND_INDICATOR = "RESEND_INDICATOR";
	public static final String CVOIP_INDICATOR = "VOIP";
    public static final String TN_OR_NPANXX = "TN_OR_NPANXX";
    public static final String CLASS_OF_SERVICE = "CLASS_OF_SERVICE";
    public static final String APPLICATION_DATE = "APPLICATION_DATE";
    public static final String ORIGINAL_DUE_DATE = "ORIGINAL_DUE_DATE";
    public static final String RELATED_ORDER_FID = "RO    ";    
    public static final String RELATED_SERVICE_ORDER = "RELATED_SERVICE_ORDER";
    public static final String SUBSEQ_DUE_DATE_FID = "ZD    ";
    public static final String SD_FLOATED_FID = "/SD ";    
    public static final String SUBSEQ_DUE_DATE = "SUBSEQ_DUE_DATE";
//	public static final String CVOIP_CLASS_OF_SERVICE = "XR7FA";
    
    public static final String LSTG_SECTION_TAG = "---LSTG";
    public static final String LIST_SECTION_TAG = "---LIST";
    public static final String LISTING_ADDRESS_FID = "LA    ";
    public static final String BASIC_ADDRESS = "BASIC_ADDRESS";
    public static final String EXTENDED_BASIC_ADDRESS = "EXTENDED_BASIC_ADDRESS";
    public static final String ASSIGNED_HOUSE_NUM_FID = "AHN   ";
    public static final String ASSIGNED_HOUSE_NUM = "ASSIGNED_HOUSE_NUM";
    public static final String LOCATION_FID = "LOC   ";    
    public static final String SUPPLEMENTAL_ADDRESS_INFO = "SUPPLEMENTAL_ADDRESS_INFO";
    
    public static final String REMARKS_SECTION_TAG = "---RMKS";
    public static final String REMARKS_FID = "RMK   ";
    public static final String LIGHTSPEED_REMARK = "LIGHTSPEED - ";
    public static final String SEPARATOR_CONSTANT = " - ";
    public static final String CONTINUATION = " NPA NXX-xxxx";
    public static final String DSL_DISCONNECT = "DSL DISCONNECT INVOLVED - ";    
    public static final String DSL_DISCONNECT_TN = "TN ";    
    
    public static final String SandE_SECTION_TAG = "---S&E";
    public static final String NEW_CONNECT_DISCONNECT = "NEW_CONNECT_DISCONNECT";    
    public static final String CLASS_OF_SERVICE_FLOATED_FID = "/CLS ";    
    public static final String CIRCUIT = "CIRCUIT";

    public static final String LOCAL_SERVING_OFFICE_FLOATED_FID = "/LSO ";
    public static final String FACS_WIRE_CENTER = "FACS_WIRE_CENTER";
    public static final String RELATED_CIRCUIT_FID = "/RTID ";    
    public static final String RELATED_TDMTN = "RELATED_TDMTN";    
    public static final String FIBER_CUSTOMER_INDICATOR_FID = "/FCI ";
    public static final String NETWORK_TYPE = "NETWORK_TYPE";
    public static final String ADDITIONAL_LINE_FLAG = "ADDITIONAL_LINE_FLAG";    
    public static final String ADDITIONAL_LINE_FID = "/ADL "; 
	public static final String BROADBAND_ROUTING_FID = "/BRTG ";
	public static final String FTTP_INDICATOR = "FTIP";
	public static final String TN_FLOATING_FID = "/TN  ";
	public static final String OLD_PROVIDER_FLOATING_FID = "/OLDP ";
	public static final String NEW_PROVIDER_FLOATING_FID = "/NEWP ";
	public static final String LRN_PROVIDER_FID = "/LRN ";
	public static final String RETAINED_PORTABILITY_FID = "/INVU";
	public static final String RETURN_TO_NATIVE_FID = "/RTN";
	public static final String PORT_OUT_FID = "/POUT";
	
        
    public static final String STATUS_SECTION_TAG = "SN=";
    public static final String STATUS_CODE = "STATUS_CODE";
    public static final String MAIN_ACCOUNT_TN = "MAIN_ACCOUNT_TN";

    public static final String ERROR_SECTION_TAG = "---ERR";
    public static final String ERR_MSG = "ERROR_MSG";

    public static final String ASSIGNMENT_SECTION_TAG = "---ASGM";
    public static final String ASSIGNMENT_SECTION_VERSION = "ASSIGNMENT_SECTION_VERSION";
    public static final String FID_NAME = "FID_NAME";
    public static final String FID_DATA = "FID_DATA";    
    
    //public static final String DISCONNECT_FLAG = "DISCONNECT_FLAG";
    public static final String REMARKS_DISCONNECT_TN  = "REMARKS_DISCONNECT_TN";
    
    public static final String  SECONDARY_CIRCUIT_ID = "SECONDARY_CIRCUIT_ID";
    public static final String  BILLING_ACCOUNT_NUMBER = "BILLING_ACCOUNT_NUMBER";
    public static final String FTTN_INDICATOR = NetworkType2Values.FTTN;
    public static final String FTTNBP_INDICATOR = NetworkType2Values.FTTNBP;
    
    public static final String NO_REGION = " ";
    public static final String OLD_NETWORK_TYPE = "OLD_NETWORK_TYPE";
    
   
    public static final String BONDED_CIRCUIT_FLOATED_FID = "/BCCR ";
    public static final String CHANGE_ACTION_CODE = "C";
    public static final String TO_ACTION_CODE = "T";
    public static final String IN_ACTION_CODE = "I";
    public static final String OUT_ACTION_CODE = "O";
    public static final String SECONDARY_RELATED_TDMTN = "/RTID ";
    public static final String OMS_BAN_LABEL = "OMS BAN - ";
    public static final String FTTN_AS3NE = "AS3NE";
    public static final String FTTP_AS3NF = "AS3NF";
    public static final String FTTP_NETWORK_INDICATOR = "FTTP";
    public static final String RGPN_NETWORK_INDICATOR = "RGPN";
    public static final String GPON_NETWORK_INDICATOR = "GPON";
    public static final String E_REGION_INDICATOR = "E";
    public static final String M_REGION_INDICATOR = "M";
    public static final String B_REGION_INDICATOR = "B";
    public static final String W_REGION_INDICATOR = "W";
    public static final String S_REGION_INDICATOR = "S";
    public static final String PRE_FUNCTION_TYPE = "PRE";
    public static final String ORDER_ACTION_TYPE = "ORDER_ACTION_TYPE";
    public static final String ORDER_SUBACTION_TYPE = "ORDER_SUBACTION_TYPE";
    public static final String CEASE_ORDER_ACTION_TYPE = "Cease";
    public static final String NEWCONNECT_ORDER_ACTION_TYPE = "Provide";
    
    //added changes from LS10
    public static final String EXCEPTION_ROUTING_INDICATOR = "EXCEPTION_ROUTING_INDICATOR";
    public static final String EXCEPTION_ROUTING_INDICATOR_FID = "PRM";
    public static final String DRYLOOP_RELATED_CIRCUIT_FID = "/DTID "; 
    public static final String GENERAL_FACILITIES_FID = "/GF ADSL, MMOD";
    public static final String GENERAL_FACILITIES_FID_SW = "/GF ADSL,MMOD";
    public static final String DSL_PROVISIONING_FID = "/PROV IP";
    public static final String FIBER_CUSTOMER_INDICATOR = "/FCI FBB";
    public static final String INTERCEPT_REDIRECT_FID = "/RCMR";
    public static final String INTERCEPT_REDIRECT_INDICATOR = "INTERCEPT_REDIRECT_INDICATOR";
    /**
     * 
     */
    public SoacServiceOrderConstants()
    {
        super();
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.srm.serviceorder.constants.ParserConstants#assignSectionNameValues()
     */
    public void assignSectionNameValues()
    {
        this.LIST = "---LIST";
        this.CTL = "---CTL";
        this.TRAFFIC = "---TFC";
        this.SE = "---S&E";
        this.BILL = "---BILL";
        this.RMKS = "---RMKS";
        this.STAT = "---STAT";
        this.ASGM = "---ASGM";
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.srm.serviceorder.constants.ParserConstants#translateActionCode(char)
     */
    public int translateActionCode(char argChar)
    {
        return 0;
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.srm.serviceorder.constants.ParserConstants#translateDefaultActionCode()
     */
    public int translateDefaultActionCode()
    {
        return 0;
    }

    /* (non-Javadoc)
     * @see com.sbc.eia.bis.srm.serviceorder.constants.ParserConstants#translateSectionActionCode(char, int)
     */
    public int translateSectionActionCode(
        char firstLetterInLine,
        int sectionCode)
    {
        return 0;
    }

}
