//$Id: ClientHelperConstants.java,v 1.7 2008/10/30 20:34:47 jc1421 Exp $
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
//# 09/07/2007  Rene Duka             Creation.
//# 09/10/2007  Rene Duka             Modified for LS6.
//# 11/13/2007  Rene Duka             CR 15842: Added mobilityCSI as a client.
//# 10/30/2008  Jon Costa             DR111198: removed RM_VERSION definition, using BIS_NAME instead.

package com.sbc.eia.bis.embus.service.client.access;

/**
* @author rd2842
*
*/
public class ClientHelperConstants 
{
    public final static String OMS_SERVICE_NAME = "OMS";
    public final static String OMS_REQUEST = "OMSRequest";
    public static final String OMS_VERSION = "1.0";

    public final static String AMSS_SERVICE_NAME = "AMSS";
    public final static String AMSS_REQUEST = "AMSSRequest";
    public static final String AMSS_VERSION = "1.0";

    public final static String FIRST_SERVICE_NAME = "FIRST";
    public final static String FIRST_REQUEST = "FIRSTRequest";
    public static final String FIRST_VERSION = "1.0";

    public final static String BBNMS_SERVICE_NAME = "BBNMS";
    public final static String BBNMS_REQUEST = "BBNMSRequest";
    public static final String BBNMS_VERSION = "1.0";

    public final static String MOBILITY_CSI_SERVICE_NAME = "mobilityCSI";
    public final static String MOBILITY_CSI_REQUEST = "mobilityCSIRequest";
    public static final String MOBILITY_CSI_VERSION = "1.0";

    public final static String RM_SERVICE_NAME = "RM";
    public final static String RM_REQUEST = "RMRequest";
    //RM_VERSION not needed, using BIS_NAME in rm.properties.*
    
    //pre-defined valid JMS/EMBUS vs. bisContext tag mapping  
  
    //EMBUS_MESSAGE_TAG in bisContext
    public static final String EMBUS_MESSAGE_TAG = "embusMessageTag";

    //JMS_CORRELATION_ID in bisContext
    public static final String JMS_CORRELATION_ID = "JMSCorrelationID";

    // JMS_REPLY_TO_QUEUE in bisContext x500 format
    public static final String JMS_DESTINATION_NAME = "destinationName";
}
