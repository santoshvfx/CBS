package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CompId_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t[] ConnToId;

	public CompId_t () {
	}
	public CompId_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t[] ConnToId) { 
		this.Ex = Ex;
		this.ConnToId = ConnToId;

	} 
}
