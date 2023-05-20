package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiNtuResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_t WsiNtuResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchWsiNtuResp_t () {
	}
	public SwitchWsiNtuResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_t WsiNtuResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.WsiNtuResp = WsiNtuResp;
		this.Umsg = Umsg;

	} 
}
