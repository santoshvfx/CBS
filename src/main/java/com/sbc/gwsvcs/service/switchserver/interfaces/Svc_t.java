package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Svc_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t[] ExOldNew;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_t Sattr;
	public com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_t DsgnOldNew;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t[] Seqp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t Ctl;

	public Svc_t () {
	}
	public Svc_t (com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t[] ExOldNew, com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_t Sattr, com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_t DsgnOldNew, com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t[] Seqp, com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t Ctl) { 
		this.ExOldNew = ExOldNew;
		this.Sattr = Sattr;
		this.DsgnOldNew = DsgnOldNew;
		this.Seqp = Seqp;
		this.Ctl = Ctl;

	} 
}
