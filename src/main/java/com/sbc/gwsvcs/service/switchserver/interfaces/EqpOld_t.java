package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class EqpOld_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SWITCH_ID;
	public String TN_RMK_TX;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] AsgLim;

	public EqpOld_t () {
	}
	public EqpOld_t (String SWITCH_ID, String TN_RMK_TX, com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] AsgLim) { 
		this.SWITCH_ID = SWITCH_ID;
		this.TN_RMK_TX = TN_RMK_TX;
		this.AsgLim = AsgLim;

	} 
}
