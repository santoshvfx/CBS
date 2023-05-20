package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchRtnTnReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t RtnTnReq;
	public String SWITCH_WC;

	public SwitchRtnTnReq_t () {
	}
	public SwitchRtnTnReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t RtnTnReq, String SWITCH_WC) { 
		this.Header = Header;
		this.RtnTnReq = RtnTnReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
