//$Id: INQTERMConstants.java,v 1.1 2009/02/04 02:35:00 js7440 Exp $
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
//# 01/30/2009  Julius Sembrano       Creation.

package com.sbc.eia.bis.BusinessInterface.rm.LFACS;

/**
 * Class      : INQTERMConstants
 * Description: Defines constants used by the INQTERM transaction.
 */
public interface INQTERMConstants {

    public static String RESULTS = "results";
    public static String INQTERM = "INQTERM";
    public static String RSP = "RSP";
    public static String TERM = "TERM";
    public static String INCNT = "INCNT";
    public static String SYSTP = "SYSTP";
    public static String ERRRSP = "ERRRSP";
    public static String ETYP = "ETYP";
    public static String ERRMSG = "ERRMSG";
    public static String STATUS_INFO = "statusInfo";
    public static String ERRORS= "errors";
    public static String ERROR_CODE= "errorCode";
    public static String ERROR_DESCRIPTION= "errorDescription";
    public static String ALLOWED_SYSTP1 = "73RMD";
    public static String ALLOWED_SYSTP2 = "73RMB";
}
