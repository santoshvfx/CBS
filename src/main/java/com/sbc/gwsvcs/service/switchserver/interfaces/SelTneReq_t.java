package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SelTneReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public String REQ_CTGY_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_t SeltVarb;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t Lst;

	public SelTneReq_t () {
	}
	public SelTneReq_t (String REQ_CTGY_CD, com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_t SeltVarb, com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t Lst) { 
		this.REQ_CTGY_CD = REQ_CTGY_CD;
		this.SeltVarb = SeltVarb;
		this.Lst = Lst;

	} 
}
