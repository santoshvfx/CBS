package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqCktReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_t InqCktReq;
	public String SWITCH_WC;

	public SwitchInqCktReq_t () {
	}
	public SwitchInqCktReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_t InqCktReq, String SWITCH_WC) { 
		this.Header = Header;
		this.InqCktReq = InqCktReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
