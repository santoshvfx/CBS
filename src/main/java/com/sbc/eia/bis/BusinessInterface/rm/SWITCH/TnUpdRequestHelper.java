//$Id: TnUpdRequestHelper.java,v 1.3 2009/09/16 21:54:34 sl7683 Exp $
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
//# 06/23/2006  Rene Duka             LS R3 enhancements. Integration Test changes.
//# 02/23/2007  Rene Duka             60665: Format SWITCH DueDate to YYYYMMDD.
//# 09/16/2009  Sheilla Lirio		  LS11 CR27048: SWITCHServer update to add TN_RLS_DT_TX to HIPCS TN porting UPDTRM

package com.sbc.eia.bis.BusinessInterface.rm.SWITCH;

import java.util.Hashtable;

import com.sbc.bccs.utilities.Utility;
import com.sbc.eia.bis.framework.logging.LogEventId;
import com.sbc.eia.idl.types.EiaDate;
import com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Header_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Tm_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t;
import com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t;

/**
 * Request helper for 5010 - SwitchTnUpdReq_t
 * @author: Rene Duka
 */

public class TnUpdRequestHelper {

    protected SwitchTnUpdReq_t aRequest = null;

    /**
     * Constructor for WsiNtuRequestHelper
     */
    public TnUpdRequestHelper() {

        aRequest = new SwitchTnUpdReq_t();
    }

    /**
     * Constructor for WsiNtuRequestHelper
     * @param Utility   aUtility
     * @param Hashtable aProperties
     * @param Header_t  aHeader
     * @param String    aWireCenter
     * @param String    aTN
     */
    public TnUpdRequestHelper(
        Utility aUtility, 
        Hashtable aProperties, 
        String aSWITCHActionType, 
        Header_t aHeader, 
        String aSOACServiceOrderNumber,
        String aWireCenter,
        EiaDate aDueDate,
        String aTN,
        String aTNList) {

        this();

        try {
            // format the temporary fields
            String source = "";
            Dt_t due_dt = new Dt_t();
            String ord_3_nbr = "";
            Asglim_t asglim_t = new Asglim_t();
            String tn_selt_ind = "";
            String tn_list_name = "";
            String tn_list = "";
            String negstatus = "";
            String reqcatg = "";
            String actn_cd_memb = "";
            String port_ind = "";
            String reldate = "";
            DtTm_t datetnonlist = new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), "");
            DtTm_t negdate = new DtTm_t(new Dt_t("@", "", ""), new Tm_t("", "", ""), "");
            
    
            if (aSWITCHActionType.equalsIgnoreCase("I")) {
                // Source = "UPDNTU"
                source = "UPDNTU";
                // aDueDate
                String aYYYY = "", aMO = "", aDY = "";
                // aDueDate
                due_dt.YR = aYYYY.valueOf(aDueDate.aYear);
                due_dt.MO = aDueDate.aMonth < 10 ? "0" + aMO.valueOf(aDueDate.aMonth) : aMO.valueOf(aDueDate.aMonth);
                due_dt.DY = aDueDate.aDay < 10 ? "0" + aDY.valueOf(aDueDate.aDay) : aDY.valueOf(aDueDate.aDay);
                // aSOACServiceOrderNumber
                ord_3_nbr = aSOACServiceOrderNumber;
                // asglim_t = "EXP" / "RST"
                asglim_t.TN_LIM_VALU_CD = "EXP";
                asglim_t.TN_LIM_TYPE_CD = "RST";
                // tn_selt_ind = "N"
                tn_selt_ind = "N";
                // aTNList
                tn_list_name = "TNL";
                // aTNList
                tn_list = aTNList;
                // negstatus = "@"
                negstatus = "@";
                // reqcatg = "@"
                reqcatg = "@";
                //reldate = "@"
                reldate = "@";
                // actn_cd_memb = "OUT"
                actn_cd_memb = "OUT";
                // port_ind = "Y";
                port_ind = "Y";
                // datetnonlist.YR = "@"
                datetnonlist= new DtTm_t(new Dt_t("@", "", ""), new Tm_t("", "", ""), "");
                // negdate.YR = "@"
                negdate = new DtTm_t(new Dt_t("@", "", ""), new Tm_t("", "", ""), "");
                
            }
    
            if (aSWITCHActionType.equalsIgnoreCase("O")) {
                // Source = "ADMNTU"
                source = "ADMNTU";
                // aDueDate = "@" / "" / ""
                due_dt.YR = "@";
                due_dt.MO = "";
                due_dt.DY = "";       
                // aSOACServiceOrderNumber ==> "@"
                ord_3_nbr = "@";
                // asglim_t = "@" / ""
                asglim_t.TN_LIM_VALU_CD = "@";
                asglim_t.TN_LIM_TYPE_CD = "";
                // tn_selt_ind = "Y"
                tn_selt_ind = "Y";
            }

            // format comnUattr_t ==> ntu_t.Uattr
            ComnUattr_t comnUattr_t = new ComnUattr_t();
    
            comnUattr_t.AIS_CD = "";
            comnUattr_t.ASGNM_CAPY_QTY = "";
            comnUattr_t.Asglim = new Asglim_t[] {asglim_t};
            comnUattr_t.ASGNM_USE_QTY = "";
            comnUattr_t.AVAIL_CAPY_IND = "";
            comnUattr_t.CALL_CT = "";
            comnUattr_t.DISCT_ASGNM_CTGY_CD = "";
            comnUattr_t.DISCT_CO_TYPE_CD = "";
            comnUattr_t.DUE_DT = due_dt;
            comnUattr_t.DATETNONLIST= datetnonlist;
            comnUattr_t.INTRCPT_CD = "";
            comnUattr_t.ORD_3_NBR = ord_3_nbr;
            comnUattr_t.NEGDATE = negdate;
            comnUattr_t.NEGSTATUS = negstatus;
            comnUattr_t.NPUB_IND = "";
            comnUattr_t.REQCATG = reqcatg;
            comnUattr_t.RLS_DT_IND = "";
            comnUattr_t.TN_RLS_DT_TX = reldate;
            comnUattr_t.TN_RMK_TX = "";
            comnUattr_t.TN_SELT_IND = tn_selt_ind;
            comnUattr_t.TN_TYPE_CD = "";
    
            // format ext_t ==> ntu_t.Memb
            Ex_t ex_t_Memb = new Ex_t();
    
            ex_t_Memb.SWITCH_ID = tn_list;
            ex_t_Memb.SWITCH_ID_NM = tn_list_name;
    
            // format the Ntu_t structure ==> tnUpdReq_t.Ntu
            Ntu_t ntu_t = new Ntu_t();
    
            ntu_t.Ex = new Ex_t("", "");
            ntu_t.TN_HI_RNGE_ID = "";
            ntu_t.TN_PARSE_CD = "";
            ntu_t.Uattr = comnUattr_t;
            ntu_t.AVDT_IND = "";
            ntu_t.RTE_IDX = "";
            ntu_t.Fctr = new Fctr_t(new Ex_t("", ""), "");
            ntu_t.Memb = ex_t_Memb;
            ntu_t.Ic = new Ex_t[] {};
    
            // format Ex_t structure ==> tnUpdReq_t.Ex
            Ex_t ex_t = new Ex_t();
            ex_t.SWITCH_ID = aTN;
            ex_t.SWITCH_ID_NM = "TN";
    
            // format DtTm_t structure ==> tnUpdReq_t.INQ_CHNG_DT_TM
            DtTm_t inq_chng_dt_tm = new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), "");

            // format the UpdOpt_t structure ==> tnUpdReq_t.Updopt
            Updopt_t updopt_t = new Updopt_t("", 
                                             "", 
                                             "", 
                                             "", 
                                             new ComnUattr_t("", "", new Asglim_t[] {}, "", "", "", new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), ""), "", "", new Dt_t("", "", ""), "", "", new DtTm_t(new Dt_t("", "", ""), new Tm_t("", "", ""), ""), "", "", "", "", "", "", "", ""),
                                             new String[] {}, 
                                             new Dt_t[] {}, 
                                             new Dt_t("", "", ""), 
                                             new Dt_t("", "", ""), 
                                             new Comp_t[] {},
                                             new Ex_t[] {}, 
                                             new Ex_t("", ""), 
                                             new Ex_t("", ""));
    
            // format tnUpdReq_t structure ==> SwitchTnUpdReq_t.TnUpdReq[]
            TnUpdReq_t tnUpdReq_t = new TnUpdReq_t();
            tnUpdReq_t.Ex = ex_t;
            tnUpdReq_t.TN_HI_RNGE_ID = "";
            tnUpdReq_t.TN_MASK_ID = "";
            tnUpdReq_t.TN_PARSE_CD = "";
            tnUpdReq_t.TN_UPD_FCN_CD = "CHG";
            tnUpdReq_t.OPTNL_MSG_TX = "";
            tnUpdReq_t.INQ_CHNG_DT_TM = inq_chng_dt_tm;
            tnUpdReq_t.INVNTY_ORD_NBR = "";
            tnUpdReq_t.STEP_ID = "";
            tnUpdReq_t.Updopt = updopt_t;
            tnUpdReq_t.ACTN_CD = "MOD";
            tnUpdReq_t.ACTN_CD_MEMB = actn_cd_memb;
            tnUpdReq_t.Ntu = ntu_t;
            tnUpdReq_t.SOURCE = source;
            tnUpdReq_t.PORT_IND = port_ind;
            
            // format SwitchTnUpdReq_t structure
            aRequest.Header = aHeader;
            aRequest.SWITCH_WC = aWireCenter;
            aRequest.TnUpdReq = new TnUpdReq_t[] {tnUpdReq_t};
        }
        catch (NullPointerException e) {
            String aExceptionMessage = "Error in building SwitchTnUpdReq_t" + " - "
                                        + e.getMessage() + " - "
                                        + "IGNORED: Ok to continue.";

            aUtility.log(LogEventId.DEBUG_LEVEL_2, aExceptionMessage);
        }
    }
    
    /**
     * Method: getRequest
     * @return SwitchTnUpdReq_t    aRequest
     */
    public SwitchTnUpdReq_t getRequest() {
        return aRequest;
    }
}
