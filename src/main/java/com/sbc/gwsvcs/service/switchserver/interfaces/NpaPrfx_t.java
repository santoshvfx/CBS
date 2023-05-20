package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class NpaPrfx_t implements org.omg.CORBA.portable.IDLEntity { 
	public String NPA;
	public String PRFX_CD;

	public NpaPrfx_t () {
	}
	public NpaPrfx_t (String NPA, String PRFX_CD) { 
		this.NPA = NPA;
		this.PRFX_CD = PRFX_CD;

	} 
}
