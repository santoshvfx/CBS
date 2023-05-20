package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ECCABLE_Section_t implements org.omg.CORBA.portable.IDLEntity { 
	public String PRT;
	public String STEP;
	public String DREC;
	public String CA;
	public String RMK;
	public String RULES;
	public String CNST;
	public String ORIG;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t[] CARNG;
	public com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t[] RTARNG;

	public ECCABLE_Section_t () {
	}
	public ECCABLE_Section_t (String PRT, String STEP, String DREC, String CA, String RMK, String RULES, String CNST, String ORIG, com.sbc.gwsvcs.service.facsaccess.interfaces.CARNG_t[] CARNG, com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t[] RTARNG) { 
		this.PRT = PRT;
		this.STEP = STEP;
		this.DREC = DREC;
		this.CA = CA;
		this.RMK = RMK;
		this.RULES = RULES;
		this.CNST = CNST;
		this.ORIG = ORIG;
		this.CARNG = CARNG;
		this.RTARNG = RTARNG;

	} 
}
