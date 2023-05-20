package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class EqpIc_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic;
	public String DLCT_CD;

	public EqpIc_t () {
	}
	public EqpIc_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic, String DLCT_CD) { 
		this.Ic = Ic;
		this.DLCT_CD = DLCT_CD;

	} 
}
