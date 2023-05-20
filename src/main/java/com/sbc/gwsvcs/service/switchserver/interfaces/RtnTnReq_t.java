package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class RtnTnReq_t implements org.omg.CORBA.portable.IDLEntity { 
    public String LIST_TYPE;
    public String TN_LN_CT;
    public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[] AccpLst;
    public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[] RtnLst;

    public RtnTnReq_t () {
    }
    public RtnTnReq_t (String LIST_TYPE, String TN_LN_CT, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[] AccpLst, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[] RtnLst) { 
        this.LIST_TYPE = LIST_TYPE;
        this.TN_LN_CT = TN_LN_CT;
        this.AccpLst = AccpLst;
        this.RtnLst = RtnLst;

    } 
}
