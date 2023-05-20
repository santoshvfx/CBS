package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SelTnResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t[] Nbr;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t Lst;
	public String REQ_STS_2_CD;

	public SelTnResp_t () {
	}
	public SelTnResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t[] Nbr, com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t Lst, String REQ_STS_2_CD) { 
		this.Nbr = Nbr;
		this.Lst = Lst;
		this.REQ_STS_2_CD = REQ_STS_2_CD;

	} 
}
