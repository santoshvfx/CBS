package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiAsmResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_t[] WsiAsmResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchWsiAsmResp_t () {
	}
	public SwitchWsiAsmResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_t[] WsiAsmResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.WsiAsmResp = WsiAsmResp;
		this.Umsg = Umsg;

	} 
}
