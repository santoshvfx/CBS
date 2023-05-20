package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiAsmReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_t WsiAsmReq;
	public String SWITCH_WC;

	public SwitchWsiAsmReq_t () {
	}
	public SwitchWsiAsmReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_t WsiAsmReq, String SWITCH_WC) { 
		this.Header = Header;
		this.WsiAsmReq = WsiAsmReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
