package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchAssignableTnResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t[] AssignableTnResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchAssignableTnResp_t () {
	}
	public SwitchAssignableTnResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t[] AssignableTnResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.AssignableTnResp = AssignableTnResp;
		this.Umsg = Umsg;

	} 
}
