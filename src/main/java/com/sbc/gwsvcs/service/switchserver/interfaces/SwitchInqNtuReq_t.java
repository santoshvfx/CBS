package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqNtuReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t InqNtuReq;
	public String SWITCH_WC;

	public SwitchInqNtuReq_t () {
	}
	public SwitchInqNtuReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t InqNtuReq, String SWITCH_WC) { 
		this.Header = Header;
		this.InqNtuReq = InqNtuReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
