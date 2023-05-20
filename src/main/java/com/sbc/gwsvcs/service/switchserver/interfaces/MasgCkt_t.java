package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class MasgCkt_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_t CktCktattr;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t[] CktEqp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t CktSvc;

	public MasgCkt_t () {
	}
	public MasgCkt_t (com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_t CktCktattr, com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t[] CktEqp, com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t CktSvc) { 
		this.CktCktattr = CktCktattr;
		this.CktEqp = CktEqp;
		this.CktSvc = CktSvc;

	} 
}
