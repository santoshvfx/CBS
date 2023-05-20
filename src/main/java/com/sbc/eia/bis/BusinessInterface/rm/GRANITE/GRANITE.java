//$Id: GRANITE.java,v 1.8 2009/07/25 02:23:35 js7440 Exp $
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
//# 01/2009    Jon Costa              Creation.
//# 07/22/2009 Julius Sembrano        PR25176019: Granite flow, there is no loop info return when WTN is returned from Granite. 
//#									  Modified logic for setting GraniteUverseNotFound and SiteNotFound

package com.sbc.eia.bis.BusinessInterface.rm.GRANITE;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.embus.service.xng.helpers.SendRequestToXNG;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.bis_types.AccessDenied;
import com.sbc.eia.idl.bis_types.BusinessViolation;
import com.sbc.eia.idl.bis_types.DataNotFound;
import com.sbc.eia.idl.bis_types.InvalidData;
import com.sbc.eia.idl.bis_types.NotImplemented;
import com.sbc.eia.idl.bis_types.ObjectNotFound;
import com.sbc.eia.idl.bis_types.SystemFailure;
import com.sbc.eia.idl.exception_types.ExceptionCode;
import com.sbc.eia.idl.types.Severity;

public class GRANITE
{
    public static String HostName       = "GRANITE";
    public static String QNI_Method     = "QueryNetworkInventory";
    public static String DATE_FORMAT    = "MMddyy";
    public static String YES_VAL        = "Y";
    public static String NO_VAL         = "N";
    public static String ADSL_VAL       = "ADSL";
    public static String VDSL_VAL       = "VDSL";
    public static String GPON_VAL       = "GPON";
    public static String VOICE_VAL      = "VOICE";
    public static String VLAN_VAL       = "VLAN";
    public static String VOICE_CKID_TAG = "CKID";
    public static String VLAN_CKID_TAG  = "CKID1";
    public static String DBUVERSE_VAL   = "Uverse";
    public static String PENDING_STATUS = "PENDING";
    public static String IN_EFFECT_STATUS = "IE";

    protected Utility aUtility = null;
    protected Hashtable aProperties = null;
    protected String GRANITEVersion = null;
    protected String GRANITEEndPointAddress = null;
    protected String GRANITESiteType = null;
    protected String GRANITEQueryType = null;
    protected String GRANITERuleFile = null;

    /**
     * Constructor.
     */
    public GRANITE(Utility utility, Hashtable properties) throws InvalidData,
                                                         AccessDenied,
                                                         BusinessViolation,
                                                         SystemFailure,
                                                         NotImplemented,
                                                         ObjectNotFound,
                                                         DataNotFound
    {
        aUtility = utility;
        aProperties = properties;
        try
        {
            // Retrieve GRANITE specific properties and verify the property is populated
            verify(GRANITEVersion = (String) aProperties.get("GRANITE_VERSION"),           "GRANITE_VERSION");
            verify(GRANITEEndPointAddress = (String) aProperties.get("GRANITE_SOAP_URL"),  "GRANITE_SOAP_URL");
            verify(GRANITESiteType = (String) aProperties.get("GRANITE_SITE_TYPE_VALUE"),  "GRANITE_SITE_TYPE_VALUE");
            verify(GRANITEQueryType = (String) aProperties.get("GRANITE_QUERY_TYPE_VALUE"),"GRANITE_QUERY_TYPE_VALUE");
            GRANITERuleFile = (String) aProperties.get(SendRequestToXNG.XNG_EXCEPTION_RULE_FILE_TAG);
        }
        catch (Exception e)
        {
            aUtility.log(LogEventId.ERROR, "Missing required GRANITE property file data: " + e.getMessage());
            aUtility.throwException(ExceptionCode.ERR_RM_UNEXPECTED_NULL_FIELD,
                                    "Missing property value [" + e.getMessage() + "], unable to continue",
                                    (String) aProperties.get("BIS_NAME"),
                                    Severity.UnRecoverable);
        }
    }

    /**
     * @param inString
     * @throws Exception
     */
    private void verify(String inString, String property) throws Exception
    {
        if (inString != null && inString.trim().length() > 0) return;
        throw new Exception(property);
    }
}
