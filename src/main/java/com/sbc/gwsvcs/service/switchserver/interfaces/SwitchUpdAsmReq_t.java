package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchUpdAsmReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_t UpdAsmReq;
	public String SWITCH_WC;

	public SwitchUpdAsmReq_t () {
	}
	public SwitchUpdAsmReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_t UpdAsmReq, String SWITCH_WC) { 
		this.Header = Header;
		this.UpdAsmReq = UpdAsmReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
