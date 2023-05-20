package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchSelTneReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_t SelTneReq;
	public String SWITCH_WC;

	public SwitchSelTneReq_t () {
	}
	public SwitchSelTneReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_t SelTneReq, String SWITCH_WC) { 
		this.Header = Header;
		this.SelTneReq = SelTneReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
