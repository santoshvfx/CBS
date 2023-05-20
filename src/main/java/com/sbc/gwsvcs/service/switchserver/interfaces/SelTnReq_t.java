package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SelTnReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public String REQ_CTGY_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t SeltVarb;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t Lst;

	public SelTnReq_t () {
	}
	public SelTnReq_t (String REQ_CTGY_CD, com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t SeltVarb, com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t Lst) { 
		this.REQ_CTGY_CD = REQ_CTGY_CD;
		this.SeltVarb = SeltVarb;
		this.Lst = Lst;

	} 
}
