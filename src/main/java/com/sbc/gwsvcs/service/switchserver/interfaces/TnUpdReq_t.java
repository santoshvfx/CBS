package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class TnUpdReq_t implements org.omg.CORBA.portable.IDLEntity { 
    public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
    public String TN_HI_RNGE_ID;
    public String TN_MASK_ID;
    public String TN_PARSE_CD;
    public String TN_UPD_FCN_CD;
    public String OPTNL_MSG_TX;
    public com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM;
    public String INVNTY_ORD_NBR;
    public String STEP_ID;
    public com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t Updopt;
    public String ACTN_CD;
    public String ACTN_CD_MEMB;
    public com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t Ntu;
    public String SOURCE;
    public String PORT_IND;

    public TnUpdReq_t () {
    }
    public TnUpdReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String TN_HI_RNGE_ID, String TN_MASK_ID, String TN_PARSE_CD, String TN_UPD_FCN_CD, String OPTNL_MSG_TX, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM, String INVNTY_ORD_NBR, String STEP_ID, com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t Updopt, String ACTN_CD, String ACTN_CD_MEMB, com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t Ntu, String SOURCE, String PORT_IND) { 
        this.Ex = Ex;
        this.TN_HI_RNGE_ID = TN_HI_RNGE_ID;
        this.TN_MASK_ID = TN_MASK_ID;
        this.TN_PARSE_CD = TN_PARSE_CD;
        this.TN_UPD_FCN_CD = TN_UPD_FCN_CD;
        this.OPTNL_MSG_TX = OPTNL_MSG_TX;
        this.INQ_CHNG_DT_TM = INQ_CHNG_DT_TM;
        this.INVNTY_ORD_NBR = INVNTY_ORD_NBR;
        this.STEP_ID = STEP_ID;
        this.Updopt = Updopt;
        this.ACTN_CD = ACTN_CD;
        this.ACTN_CD_MEMB = ACTN_CD_MEMB;
        this.Ntu = Ntu;
        this.SOURCE = SOURCE;
        this.PORT_IND = PORT_IND;

    } 
}
