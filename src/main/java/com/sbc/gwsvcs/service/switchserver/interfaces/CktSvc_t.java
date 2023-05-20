package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CktSvc_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t[] SvcSeqp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_t SvcCtl;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExOld;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExNew;

	public CktSvc_t () {
	}
	public CktSvc_t (com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t[] SvcSeqp, com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_t SvcCtl, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExOld, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExNew) { 
		this.SvcSeqp = SvcSeqp;
		this.SvcCtl = SvcCtl;
		this.ExOld = ExOld;
		this.ExNew = ExNew;

	} 
}
