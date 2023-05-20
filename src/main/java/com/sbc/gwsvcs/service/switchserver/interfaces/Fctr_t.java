package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Fctr_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String RT_ZONE;

	public Fctr_t () {
	}
	public Fctr_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String RT_ZONE) { 
		this.Ex = Ex;
		this.RT_ZONE = RT_ZONE;

	} 
}
