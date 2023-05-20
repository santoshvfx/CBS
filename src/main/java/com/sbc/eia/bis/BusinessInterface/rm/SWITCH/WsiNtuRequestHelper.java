//$Id: WsiNtuRequestHelper.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $
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
//# 06/09/2006  Rene Duka             Creation.
//# 06/23/2006  Rene Duka             LS R3 enhancements. Code WT changes.

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_t;

/**
 * Request helper for 5170 - SwitchWsiNtuReq_t
 * @author: Rene Duka
 */

public class WsiNtuRequestHelper {

    protected SwitchWsiNtuReq_t aRequest;

    /**
     * Constructor for WsiNtuRequestHelper
     */
    public WsiNtuRequestHelper() {
        aRequest = new SwitchWsiNtuReq_t();
    }

    /**
     * Constructor for WsiNtuRequestHelper
     * @param Utility   aUtility
     * @param Hashtable aProperties
     * @param Header_t  aHeader
     * @param String    aWireCenter
     * @param String    aTN
     */
    public WsiNtuRequestHelper(
        Utility aUtility, 
        Hashtable aProperties, 
        Header_t aHeader, 
        String aWireCenter, 
        String aTN) {
        
        this();

        try {
            // format Ex_t structure ==> wsiNtuReq_t.Ex
            Ex_t ex_t = new Ex_t();
    
            ex_t.SWITCH_ID = aTN;
            ex_t.SWITCH_ID_NM = "TN";
            
            // format wsiNtuReq_t structure ==> switchWsiNtuReq.WsiNtuReq
            WsiNtuReq_t wsiNtuReq_t = new WsiNtuReq_t();
    
            wsiNtuReq_t.TN_UPD_FCN_CD = "CHG";
            wsiNtuReq_t.Ex = ex_t;
            
            // format SwitchWsiNtuReq_t structure
            //SwitchWsiNtuReq_t switchWsiNtuReq = new SwitchWsiNtuReq_t();
            //aRequest = new SwitchWsiNtuReq_t();
    
            aRequest.Header = aHeader;
            aRequest.SWITCH_WC = aWireCenter;
            aRequest.WsiNtuReq = wsiNtuReq_t;
        }
        catch (Exception e) {
            String aExceptionMessage = "Error in building SwitchWsiNtuReq_t" + " - "
                                        + e.getMessage() + " - "
                                        + "IGNORED: Ok to continue.";

            aUtility.log(LogEventId.DEBUG_LEVEL_2, aExceptionMessage);
        }
    }
    
    /**
     * Method: getRequest
     * @return SwitchWsiNtuReq_t    aRequest
     */
    public SwitchWsiNtuReq_t getRequest() {
        return aRequest;
    }
}
