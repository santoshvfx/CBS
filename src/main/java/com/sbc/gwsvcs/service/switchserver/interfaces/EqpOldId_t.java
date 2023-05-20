package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class EqpOldId_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[] EqpIc;

	public EqpOldId_t () {
	}
	public EqpOldId_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[] EqpIc) { 
		this.Ex = Ex;
		this.EqpIc = EqpIc;

	} 
}
