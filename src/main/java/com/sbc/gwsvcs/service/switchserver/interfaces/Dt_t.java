package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Dt_t implements org.omg.CORBA.portable.IDLEntity { 
	public String YR;
	public String MO;
	public String DY;

	public Dt_t () {
	}
	public Dt_t (String YR, String MO, String DY) { 
		this.YR = YR;
		this.MO = MO;
		this.DY = DY;

	} 
}
