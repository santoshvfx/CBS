package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqAsmResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmResp_t[] InqAsmResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchInqAsmResp_t () {
	}
	public SwitchInqAsmResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmResp_t[] InqAsmResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.InqAsmResp = InqAsmResp;
		this.Umsg = Umsg;

	} 
}
