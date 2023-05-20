package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Loop_t implements org.omg.CORBA.portable.IDLEntity { 
	public String RT_ZONE_NBR;
	public String CBL_NBR;
	public String PR_NBR;

	public Loop_t () {
	}
	public Loop_t (String RT_ZONE_NBR, String CBL_NBR, String PR_NBR) { 
		this.RT_ZONE_NBR = RT_ZONE_NBR;
		this.CBL_NBR = CBL_NBR;
		this.PR_NBR = PR_NBR;

	} 
}
