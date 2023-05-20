package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqNtuResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuResp_t[] InqNtuResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchInqNtuResp_t () {
	}
	public SwitchInqNtuResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuResp_t[] InqNtuResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.InqNtuResp = InqNtuResp;
		this.Umsg = Umsg;

	} 
}
