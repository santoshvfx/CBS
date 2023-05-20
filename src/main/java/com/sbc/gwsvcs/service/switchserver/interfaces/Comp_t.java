package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Comp_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SWITCH_ID_NM;
	public String SPCFC_FUNCLT_CD;

	public Comp_t () {
	}
	public Comp_t (String SWITCH_ID_NM, String SPCFC_FUNCLT_CD) { 
		this.SWITCH_ID_NM = SWITCH_ID_NM;
		this.SPCFC_FUNCLT_CD = SPCFC_FUNCLT_CD;

	} 
}
