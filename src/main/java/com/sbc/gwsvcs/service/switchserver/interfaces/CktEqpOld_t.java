package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CktEqpOld_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SWITCH_ID;
	public String RT_ZONE_NBR;

	public CktEqpOld_t () {
	}
	public CktEqpOld_t (String SWITCH_ID, String RT_ZONE_NBR) { 
		this.SWITCH_ID = SWITCH_ID;
		this.RT_ZONE_NBR = RT_ZONE_NBR;

	} 
}
