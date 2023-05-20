package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class InqGrpReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_t[] ExGrp;
	public String FOPTN;

	public InqGrpReq_t () {
	}
	public InqGrpReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_t[] ExGrp, String FOPTN) { 
		this.ExGrp = ExGrp;
		this.FOPTN = FOPTN;

	} 
}
