package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class ChgAsgReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExOld;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExNew;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t[] Eqp;

	public ChgAsgReq_t () {
	}
	public ChgAsgReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExOld, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExNew, com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t[] Eqp) { 
		this.ExOld = ExOld;
		this.ExNew = ExNew;
		this.Eqp = Eqp;

	} 
}
