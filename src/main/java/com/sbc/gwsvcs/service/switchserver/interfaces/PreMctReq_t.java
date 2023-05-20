package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class PreMctReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExOld;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExNew;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t[] Eqp;

	public PreMctReq_t () {
	}
	public PreMctReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExOld, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExNew, com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t[] Eqp) { 
		this.ExOld = ExOld;
		this.ExNew = ExNew;
		this.Eqp = Eqp;

	} 
}
