package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class InqCktReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String FOPTN;

	public InqCktReq_t () {
	}
	public InqCktReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String FOPTN) { 
		this.Ex = Ex;
		this.FOPTN = FOPTN;

	} 
}
