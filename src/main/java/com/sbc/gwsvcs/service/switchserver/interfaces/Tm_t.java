package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Tm_t implements org.omg.CORBA.portable.IDLEntity { 
	public String HR;
	public String MTE;
	public String SECS;

	public Tm_t () {
	}
	public Tm_t (String HR, String MTE, String SECS) { 
		this.HR = HR;
		this.MTE = MTE;
		this.SECS = SECS;

	} 
}
