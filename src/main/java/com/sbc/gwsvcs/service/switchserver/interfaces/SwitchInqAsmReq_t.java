package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqAsmReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_t InqAsmReq;
	public String SWITCH_WC;

	public SwitchInqAsmReq_t () {
	}
	public SwitchInqAsmReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_t InqAsmReq, String SWITCH_WC) { 
		this.Header = Header;
		this.InqAsmReq = InqAsmReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
