package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class WsiNtuResp_t implements org.omg.CORBA.portable.IDLEntity { 
    public String INTRCPT_CD;
    public com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t Ntu;

    public WsiNtuResp_t () {
    }
    public WsiNtuResp_t (String INTRCPT_CD, com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t Ntu) { 
        this.INTRCPT_CD = INTRCPT_CD;
        this.Ntu = Ntu;

    } 
}
