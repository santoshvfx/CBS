package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class UpdCktReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t Ctl;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t Svc;

	public UpdCktReq_t () {
	}
	public UpdCktReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t Ctl, com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t Svc) { 
		this.Ctl = Ctl;
		this.Svc = Svc;

	} 
}
