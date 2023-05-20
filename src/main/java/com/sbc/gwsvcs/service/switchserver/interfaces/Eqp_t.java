package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Eqp_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SWITCH_ID_NM;
	public com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t EqpOld;

	public Eqp_t () {
	}
	public Eqp_t (String SWITCH_ID_NM, com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t EqpOld) { 
		this.SWITCH_ID_NM = SWITCH_ID_NM;
		this.EqpOld = EqpOld;

	} 
}
