package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class NpaPrfxLn_t implements org.omg.CORBA.portable.IDLEntity { 
	public String NPA;
	public String PRFX_CD;
	public String LN;

	public NpaPrfxLn_t () {
	}
	public NpaPrfxLn_t (String NPA, String PRFX_CD, String LN) { 
		this.NPA = NPA;
		this.PRFX_CD = PRFX_CD;
		this.LN = LN;

	} 
}
