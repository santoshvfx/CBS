package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CktEqp_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SWITCH_ID_NM;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_t CktEqpOld;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_t CktEqpNew;

	public CktEqp_t () {
	}
	public CktEqp_t (String SWITCH_ID_NM, com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_t CktEqpOld, com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_t CktEqpNew) { 
		this.SWITCH_ID_NM = SWITCH_ID_NM;
		this.CktEqpOld = CktEqpOld;
		this.CktEqpNew = CktEqpNew;

	} 
}
