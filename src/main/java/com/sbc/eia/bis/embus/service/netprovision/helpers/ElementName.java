    // $Id: ElementName.java,v 1.13 2006/09/12 18:08:34 ck2932 Exp $
    //###############################################################################
    //#
    //#   Copyright Notice:
    //#
    //#       This software/documentation is the proprietary trade secret and
    //#       property of SBC Services, Inc. Receipt or possession of it does not
    //#       convey any rights to divulge, reproduce, use or allow others to
    //#       use it without the specific written authorization of SBC Services, Inc.
    //#       Use must conform strictly to the license agreement between user and
    //#       SBC Services, Inc.
    //#
    //#       (C) SBC Services, Inc 2001-2004.  All Rights Reserved.
    //#
    //# History :
    //# Date      | Author              | Notes
    //# ----------------------------------------------------------------------------
    //# 04/15/2005  Jinmin Ni             Create
    //# 04/19/2005  Rene Duka             Added elements for 'reserveActivateFacilityAssignment'.
    //# 05/05/2005  Rene Duka             Added SERVICE_ACCESS_POINT.
    //# 05/17/2005  Rene Duka             Changed defintions to all lower-case per NetP.
    //# 05/24/2005  Rene Duka             Changed sy_objectID to sy_objectid.
    //# 06/06/2005  Rene Duka             Changed z_transo.vlanid to z_transo.vci.
    //# 06/07/2005  Manjula Goniguntla    Added elements for 'PublishAutoDiscoveryResults'
    //# 06/16/2005  Rene Duka             Changed a_tpp.cpe.whdlslamid to a_tpp.cpe.whdslamid.
    //# 06/27/2005  Rene Duka             Changed DSLAM_TERMINAL_ID to DSLAM_ID.
    //# 08/23/2005  Rene Duka             DR 142839: Changed "z_transo.vlatmconn.srname" to "z_transo.vlatmconn.asobjfullname".
    //# 12/15/2005  Jyothi Pentyala       Added variables for LS2 - RAFA.
    //# 01/19/2006  Jyothi Pentyala       Modified NetP mappings for RAFA - VCI and VPI.

    package com.sbc.eia.bis.embus.service.netprovision.helpers;

    /**
     * @author ml2917
     *
     */

    public interface ElementName {

        public static final String LS_CIRCUIT_ID_1           = "a_tpp.cpe.whcircuitid";
        public static final String LS_CIRCUIT_ID_2           = "name";
        public static final String LS_CIRCUIT_ID_3           = "srname";           
        public static final String LS_ORDER_ACTION_NUMBER    = "a_tpp.cpe.whordernumber";
        public static final String LS_ORDER_ACTION_ID        = "a_tpp.cpe.whorderactionid";
        public static final String SITE_ID                   = "a_tpp.cpe.whsiteid";
        public static final String BAN                       = "a_tpp.cpe.whcustomerid";
        public static final String DSLAM_PHYSICAL_PORT       = "a_tpp.subscriberport";
        public static final String DSLAM_ID                  = "a_tpp.cpe.whdslamid";
        public static final String DSLAM_VLAN_ID_A           = "a_vlanso.vlanid";
        public static final String DSLAM_VLAN_ID_Z           = "z_transo.vci";
        public static final String DSLAM_ONT_ID              = "rfu3";
        public static final String DSLAM_OLT_ID              = "rfu4";
        public static final String DSLAM_OLT_LOGICAL_PORT    = "rfu5";
        public static final String NETWORK_7450_LOGICAL_EGRESS_PORT = "z_tpwan";
        public static final String NETWORK_7450_TERMINAL_ID  = "rfu2";
        public static final String SERVICE_BUNDLE_ID         = "srprofile.sy_profname";
        public static final String ACCESS_POINT_POLICY       = "a_tpp.srprofile.sy_profname";
        public static final String VPLS_DOMAIN               = "z_vlanso.name";
        public static final String BISCONTEXT_USERID         = "a_tpp.cpe.whuserid";
        public static final String BISCONTEXT_INTERFACE_NAME = "a_tpp.cpe.whifcname";
        public static final String BISCONTEXT_REQUEST_ID     = "a_tpp.cpe.whrequestid";
        public static final String BISCONTEXT_LOGICAL_SERVER = "a_tpp.cpe.whlogicalserver";

        public static final String OBJECT_ID                 = "sy_objectid";
        //public static final String SERVICE_ACCESS_POINT      = "z_transo.vlatmconn.srname";
        public static final String SERVICE_ACCESS_POINT      = "z_transo.vlatmconn.asobjfullname";
        public static final String MODEL_NUMBER              = "whmodel";
        public static final String SERIAL_NUMBER             = "whserialnumber";
        
		public static final String NETWORK_TYPE               = "rfu3";
		public static final String OPTICAL_LINE_TERMINAL_TID  = "a_tpp.cpe.whdslamid";
		public static final String OPTICAL_NETWORK_TERMINAL_AID = "a_tpp.subscriberport";
		public static final String VLANID                     = "a_vlanso.vlanid";
		public static final String VIRTUAL_PATH_INDICATOR     = "a_epm.vlatmconn.raz_vpi";
		public static final String VIRTUAL_CHANNEL_INDICATOR  = "a_epm.vlatmconn.raz_vci";
		
        public static final String CUSTOMER_FIRST_NAME = "a_tpp.cpe.whfirstname";
        public static final String CUSTOMER_LAST_NAME = "a_tpp.cpe.whlastname";
        public static final String BILLING_TELEPHONE_NUMBER = "a_tpp.cpe.whbillingphoneid";
        
    }
