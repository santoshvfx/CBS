//$Id: TnUpdResponseHelper.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $
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
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdResp_t;

/**
 * @author: Rene Duka
 */

public class TnUpdResponseHelper {
    private Utility aUtility = null;
    private Hashtable aProperties = null;
     
    private String aMSG_NBR = null;
    private String aMSG_TYPE = null;
    private String aMSG_TEXT = null;


    /**
     * Constructor for TnUpdResponseHelper
     * @param Utility utility
     * @param Hashtable properties
     */
    public TnUpdResponseHelper(Utility utility, Hashtable properties) {
        aProperties = properties;
        aUtility = utility;
    }
    
    /**
     * Method: parseResponse
     * @param SwitchTnUpdResp_t     aResponse
     */
    public void parseResponse(SwitchTnUpdResp_t aResponse) {

        String myMethodName = "TnUpdResponseHelper: parseResponse()";
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);

        for (int i = 0; i < aResponse.Umsg.length; i++)  {
            if (aResponse.Umsg[i].MSG_TYPE.equalsIgnoreCase("A")) {
                aMSG_NBR = aResponse.Umsg[i].MSG_NBR;
                aMSG_TYPE =  aResponse.Umsg[i].MSG_TYPE;
                aMSG_TEXT = new String(aResponse.Umsg[i].MSG_TX);
                break;
            }
            
            if (aResponse.Umsg[i].MSG_TYPE.equalsIgnoreCase("E")) {
                aMSG_NBR = aResponse.Umsg[i].MSG_NBR;
                aMSG_TYPE =  aResponse.Umsg[i].MSG_TYPE;
                aMSG_TEXT = new String(aResponse.Umsg[i].MSG_TX);
                break;
            }
        }
        aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        return;
    }

    /**
     * Method: getMSG_NBR
     * @return aMSG_NBR
     */
    public String getMSG_NBR() {
        return aMSG_NBR;
    }
    
    /**
     * Method: getMSG_TYPE
     * @return aMSG_TYPE
     */
    public String getMSG_TYPE() {
        return aMSG_TYPE;
    }

    /**
     * Method: getMSG_TEXT
     * @return aMSG_TEXT
     */
    public String getMSG_TEXT() {
        return aMSG_TEXT;
    }
}       

