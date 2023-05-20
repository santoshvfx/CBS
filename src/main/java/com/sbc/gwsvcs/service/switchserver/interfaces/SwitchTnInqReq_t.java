package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchTnInqReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_t TnInqReq;
	public String SWITCH_WC;

	public SwitchTnInqReq_t () {
	}
	public SwitchTnInqReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_t TnInqReq, String SWITCH_WC) { 
		this.Header = Header;
		this.TnInqReq = TnInqReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
