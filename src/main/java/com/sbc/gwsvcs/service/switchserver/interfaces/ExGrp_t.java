package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class ExGrp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String GRP_ID;

	public ExGrp_t () {
	}
	public ExGrp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String GRP_ID) { 
		this.Ex = Ex;
		this.GRP_ID = GRP_ID;

	} 
}
