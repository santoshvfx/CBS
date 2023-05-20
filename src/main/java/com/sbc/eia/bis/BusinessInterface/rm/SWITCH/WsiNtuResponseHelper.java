//$Id: WsiNtuResponseHelper.java,v 1.1 2006/08/15 20:31:27 jo8461 Exp $
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
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuResp_t;

/**
 * @author: Rene Duka
 */

public class WsiNtuResponseHelper {
    
    private Utility aUtility = null;
    private Hashtable aProperties = null;
     
    private String aREQCATG = null;
    private String aNEGSTATUS = null;
    private String aORD_3_NBR = null;
    private String aDUE_DT = null;
    private String aTN_LIM_VALU_CD = null;
    private String aTN_LIM_TYPE_CD = null;
    private String aTN_SELT_IND = null;
    private String aMemb_EXNM = null;
    private String aMemb_EXID = null;
    private String aUmsg_MSG_NBR = null;
    private String aUmsg_MSG_TYPE = null;
    private String aUmsg_MSG_TX = null;
    private boolean aIsError = false;

    /**
     * Constructor for WsiNtuResponseHelper
     * @param Utility utility
     * @param Hashtable properties
     */
    public WsiNtuResponseHelper(Utility utility, Hashtable properties) {
        aProperties = properties;
        aUtility = utility;
    }
    
    /**
     * Method: parseResponse
     * @param SwitchWsiNtuResp_t    aResponse
     * @param String                aTN
     */
    public void parseResponse(SwitchWsiNtuResp_t aResponse) {

        String myMethodName = "SWITCHWsiNtuResponseHelper: parseResponse()";
        String aFieldName = null;
        aUtility.log(LogEventId.DEBUG_LEVEL_1, ">" + myMethodName);
        
        try {

            if (aResponse.Umsg.length > 0) {
                for (int i = 0; i < aResponse.Umsg.length; i++) {
                    if (aResponse.Umsg[i].MSG_TYPE.equalsIgnoreCase("E")
                        || aResponse.Umsg[i].MSG_TYPE.equalsIgnoreCase("")) {
                        aUmsg_MSG_NBR = aResponse.Umsg[i].MSG_NBR;
                        aUmsg_MSG_TYPE = aResponse.Umsg[i].MSG_TYPE;
                        aUmsg_MSG_TX = new String(aResponse.Umsg[i].MSG_TX);
                        aIsError = true;
                        break;
                    }
                }
            }
            
            if (!aIsError) {
                // set Requestor Category
                aFieldName = "REQCATG";
                aREQCATG = aResponse.WsiNtuResp.Ntu.Uattr.REQCATG;
    
                // set Negotiation Status
                aFieldName = "NEGSTATUS";
                aNEGSTATUS = aResponse.WsiNtuResp.Ntu.Uattr.NEGSTATUS;
    
                // set Order Number
                aFieldName = "ORD_3_NBR";
                aORD_3_NBR = aResponse.WsiNtuResp.Ntu.Uattr.ORD_3_NBR;
    
                // set Due Date
                aFieldName = "DUE_DT";
                aDUE_DT = aResponse.WsiNtuResp.Ntu.Uattr.DUE_DT.YR 
                          + aResponse.WsiNtuResp.Ntu.Uattr.DUE_DT.MO
                          + aResponse.WsiNtuResp.Ntu.Uattr.DUE_DT.DY;
    
                if (aResponse.WsiNtuResp.Ntu.Uattr.Asglim.length > 0) {
                    // set TN Limitation Value Code
                    aFieldName = "TN_LIM_VALU_CD";
                    aTN_LIM_VALU_CD = aResponse.WsiNtuResp.Ntu.Uattr.Asglim[0].TN_LIM_VALU_CD;
                                    
                    // set TN Limitation Type Code
                    aFieldName = "TN_LIM_TYPE_CD";
                    aTN_LIM_TYPE_CD = aResponse.WsiNtuResp.Ntu.Uattr.Asglim[0].TN_LIM_TYPE_CD;
                }
    
                // set TN Selectability Indicator
                aFieldName = "TN_SELT_IND";
                aTN_SELT_IND = aResponse.WsiNtuResp.Ntu.Uattr.TN_SELT_IND;
    
                // set SWITCH_ID_NM
                aFieldName = "SWITCH_ID_NM";
                aMemb_EXNM = aResponse.WsiNtuResp.Ntu.Memb.SWITCH_ID_NM;
    
                // set SWITCH_ID
                aFieldName = "SWITCH_ID";
                aMemb_EXID = aResponse.WsiNtuResp.Ntu.Memb.SWITCH_ID;
            }
        }
        catch (NullPointerException e) {
            aUtility.log(LogEventId.EXCEPTION, aFieldName + "<NullPointerException>");
        }
        catch (Exception e) {
            aUtility.log(LogEventId.EXCEPTION, aFieldName + "<Exception>");
        }
        finally {
            aUtility.log(LogEventId.DEBUG_LEVEL_1, "<" + myMethodName);
        }
        return;
    }
       
    /**
     * Method: getREQCATG
     * @return aREQCATG
     */
    public String getREQCATG() {
        return aREQCATG;
    }
    
    /**
     * Method: getNEGSTATUS
     * @return aNEGSTATUS
     */
    public String getNEGSTATUS() {
        return aNEGSTATUS;
    }

    /**
     * Method: getORD_3_NBR
     * @return aORD_3_NBR
     */
    public String getORD_3_NBR() {
        return aORD_3_NBR;
    }

    /**
     * Method: getDUE_DT
     * @return aDUE_DT
     */
    public String getDUE_DT() {
        return aDUE_DT;
    }

    /**
     * Method: getTN_LIM_VALU_CD
     * @return aTN_LIM_VALU_CD
     */
    public String getTN_LIM_VALU_CD() {
        return aTN_LIM_VALU_CD;
    }

    /**
     * Method: getTN_LIM_TYPE_CD
     * @return aTN_LIM_TYPE_CD
     */
    public String getTN_LIM_TYPE_CD() {
        return aTN_LIM_TYPE_CD;
    }

    /**
     * Method: getTN_SELT_IND
     * @return aTN_SELT_IND
     */
    public String getTN_SELT_IND() {
        return aTN_SELT_IND;
    }

    /**
     * Method: getMemb_EXNM
     * @return aMemb_EXNM
     */
    public String getMemb_EXNM() {
        return aMemb_EXNM;
    }

    /**
     * Method: getMemb_EXID
     * @return aMemb_EXID
     */
    public String getMemb_EXID() {
        return aMemb_EXID;
    }

    /**
     * Method: getUmsg_MSG_NBR
     * @return aUmsg_MSG_NBR
     */
    public String getUmsg_MSG_NBR() {
        return aUmsg_MSG_NBR;
    }

    /**
     * Method: getUmsg_MSG_TYPE
     * @return aUmsg_MSG_TYPE
     */
    public String getUmsg_MSG_TYPE() {
        return aUmsg_MSG_TYPE;
    }

    /**
     * Method: getUmsg_MSG_TX
     * @return aUmsg_MSG_TX
     */
    public String getUmsg_MSG_TX() {
        return aUmsg_MSG_TX;
    }

    /**
     * Method: getIsError
     * @return aIsError
     */
    public boolean getIsError() {
        return aIsError;
    }
}
