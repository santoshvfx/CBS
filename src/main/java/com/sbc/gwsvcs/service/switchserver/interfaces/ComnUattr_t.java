package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class ComnUattr_t implements org.omg.CORBA.portable.IDLEntity { 
    public String AIS_CD;
    public String ASGNM_CAPY_QTY;
    public com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] Asglim;
    public String ASGNM_USE_QTY;
    public String AVAIL_CAPY_IND;
    public String CALL_CT;
    public com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t DATETNONLIST;
    public String DISCT_ASGNM_CTGY_CD;
    public String DISCT_CO_TYPE_CD;
    public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t DUE_DT;
    public String INTRCPT_CD;
    public String ORD_3_NBR;
    public com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t NEGDATE;
    public String NEGSTATUS;
    public String NPUB_IND;
    public String REQCATG;
    public String RLS_DT_IND;
    public String TN_RLS_DT_TX;
    public String TN_RMK_TX;
    public String TN_SELT_IND;
    public String TN_TYPE_CD;

    public ComnUattr_t () {
    }
    public ComnUattr_t (String AIS_CD, String ASGNM_CAPY_QTY, com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] Asglim, String ASGNM_USE_QTY, String AVAIL_CAPY_IND, String CALL_CT, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t DATETNONLIST, String DISCT_ASGNM_CTGY_CD, String DISCT_CO_TYPE_CD, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t DUE_DT, String INTRCPT_CD, String ORD_3_NBR, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t NEGDATE, String NEGSTATUS, String NPUB_IND, String REQCATG, String RLS_DT_IND, String TN_RLS_DT_TX, String TN_RMK_TX, String TN_SELT_IND, String TN_TYPE_CD) { 
        this.AIS_CD = AIS_CD;
        this.ASGNM_CAPY_QTY = ASGNM_CAPY_QTY;
        this.Asglim = Asglim;
        this.ASGNM_USE_QTY = ASGNM_USE_QTY;
        this.AVAIL_CAPY_IND = AVAIL_CAPY_IND;
        this.CALL_CT = CALL_CT;
        this.DATETNONLIST = DATETNONLIST;
        this.DISCT_ASGNM_CTGY_CD = DISCT_ASGNM_CTGY_CD;
        this.DISCT_CO_TYPE_CD = DISCT_CO_TYPE_CD;
        this.DUE_DT = DUE_DT;
        this.INTRCPT_CD = INTRCPT_CD;
        this.ORD_3_NBR = ORD_3_NBR;
        this.NEGDATE = NEGDATE;
        this.NEGSTATUS = NEGSTATUS;
        this.NPUB_IND = NPUB_IND;
        this.REQCATG = REQCATG;
        this.RLS_DT_IND = RLS_DT_IND;
        this.TN_RLS_DT_TX = TN_RLS_DT_TX;
        this.TN_RMK_TX = TN_RMK_TX;
        this.TN_SELT_IND = TN_SELT_IND;
        this.TN_TYPE_CD = TN_TYPE_CD;

    } 
}
