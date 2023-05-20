package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchTnInqResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t[] TnInqResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchTnInqResp_t () {
	}
	public SwitchTnInqResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t[] TnInqResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.TnInqResp = TnInqResp;
		this.Umsg = Umsg;

	} 
}
