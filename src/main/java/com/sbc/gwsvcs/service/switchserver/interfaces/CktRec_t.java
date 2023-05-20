package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CktRec_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_t CktRecSvc;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_t CktRecCtl;
	public com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[] EqpIc;

	public CktRec_t () {
	}
	public CktRec_t (com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_t CktRecSvc, com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_t CktRecCtl, com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[] EqpIc) { 
		this.CktRecSvc = CktRecSvc;
		this.CktRecCtl = CktRecCtl;
		this.EqpIc = EqpIc;

	} 
}
