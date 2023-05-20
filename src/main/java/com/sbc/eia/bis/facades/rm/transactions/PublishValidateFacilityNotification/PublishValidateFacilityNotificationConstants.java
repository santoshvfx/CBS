//$Id: PublishValidateFacilityNotificationConstants.java,v 1.15 2009/04/24 03:31:30 js7440 Exp $
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
//# --------------------------------------------------------------------------
//# 11/01/2007  Rene Duka             Creation.
//# 11/06/2007  Rene Duka             RM 410745: Project Lightspeed - Release 6.0.
//# 12/11/2007  Rene Duka             CR 16563: Additional requirements for Project Lightspeed - Release 6.0.
//# 05/22/2008  Sriram Chevuturu	  LS 7:PR 22162695: JMSSelector for MobilityCSI
//# 10/02/2008  Vickie Ng			  LS 9: added region constants
//# 01/08/2009  Julius Sembrano       LS 10: added LS IPDSLAM and HYBRID IPDSLAM
//# 02/10/2009  Lira Galsim			  LS 10: Added constants for services "Other" and "POTS".
//# 04/20/2009  Julius Sembrano       CR24678: RM BIS not to treat UNE-P/LWC as conflict

package com.sbc.eia.bis.facades.rm.transactions.PublishValidateFacilityNotification;

/**
 * Class      : PublishValidateFacilityNotificationConstants
 * Description: Defines constants used by the pVFN transaction.
 */
public class PublishValidateFacilityNotificationConstants
{
    public static int WTN_Format1_LENGTH = 8;           // e.g. 968-0096, 968 0096
    public static int WTN_Format2_LENGTH = 12;          // e.g. 925 968-0096, 925-968-0096
    public static int WTN_Format3_LENGTH = 10;          // e.g. 9259280096
    
    public static String SERVICE_NAME_CIRCUIT_ID = "CircuitID";
    public static String SERVICE_NAME_UVERSE = "U-Verse";
    public static String SERVICE_NAME_DSL = "DSL";
    public static String SERVICE_NAME_LS_IPDSLAM = "LS-IP-DSL";
    public static String SERVICE_NAME_HYBRID_IPDSLAM = "HY-IP-DSL";
    public static String SERVICE_NAME_GPON = "GPON";
    public static String SERVICE_NAME_BPON = "BPON";
    public static String SERVICE_NAME_OTHER = "Other";
    public static String SERVICE_NAME_POTS = "POTS";
    public static String SERVICE_NAME_DRYLOOP = "DRYLOOP";
    public static String SERVICE_NAME_UNE_P = "UNE-P";
    public static String SERVICE_NAME_LWC = "LWC";
    
    
    public static String SERVICE_ITEM_PROPERTY_LOOP_CIRCUIT_1 = "LOOP_CIRCUIT_ID_1";
    public static String SERVICE_ITEM_PROPERTY_LOOP_CIRCUIT_2 = "LOOP_CIRCUIT_ID_2";
    public static String SERVICE_ITEM_PROPERTY_LOOP_CIRCUIT_3 = "LOOP_CIRCUIT_ID_3";
    
    public static String DSL_SERVICE_PROVIDER_ASI = "ASI";
    public static String INTERNET_SERVICE_PROVIDER_ATTIS = "ATTIS";            
    public static final String DSL_SERVICE_PROVIDER_INDICATOR = "Non-ASI";
    public static final String INTERNET_SERVICE_PROVIDER_INDICATOR = "Non-ATTIS";

    //JMS Selector
    public static final String JMS_SELECTOR_TAG = "JMSSelector";
    
    public static final String REGION_EAST = "E";
    public static final String REGION_WEST = "W";
    public static final String REGION_MIDWEST = "M";
    public static final String REGION_SOUTHWEST = "S";
    public static final String REGION_SOUTHEAST = "B";
    public static final String PRODUCT_SBC_DIALUP = "SBC_DIALUP"; 
    
    // USOC
    public static final String DISH_SERVICE_USOC = "KST";
    public static final String NONPUBLISHED_USOC_NPU = "NPU";
    public static final String NONPUBLISHED_USOC_NP3 = "NP3";
    
    //FLOATED_FID
    public static final String DSL_SERVICE_PROVIDER_FID_CODE  = "UNN1";
    public static final String FID_ISP_SERVICE_PROVIDER_MIDWEST  = "DNPC";
    public static final String HANDICAP_FID_SOUTHEAST = "DPD";
    
    //LEFT-HANDED FID
    public static final String HANDICAP_FID_WEST = "TTY";
    public static final String CLASS_OF_SERVICE_FID = "CLSV";
    public static final String FID_NP = "NP"; 
    public static final String FID_NPU = "NPU";
    
    public static final String FID_VALUE_NS = "NS";
    public static final String FID_VALUE_NP_NSL = "NP-NSL";
    public static final String FID_VALUE_NP_OSL = "NP-OSL";  
}
