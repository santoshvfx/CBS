package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchAssignableTnReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t AssignableTnReq;
	public String SWITCH_WC;

	public SwitchAssignableTnReq_t () {
	}
	public SwitchAssignableTnReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t AssignableTnReq, String SWITCH_WC) { 
		this.Header = Header;
		this.AssignableTnReq = AssignableTnReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
