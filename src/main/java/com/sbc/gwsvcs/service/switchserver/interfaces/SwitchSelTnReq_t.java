package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchSelTnReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_t SelTnReq;
	public String SWITCH_WC;

	public SwitchSelTnReq_t () {
	}
	public SwitchSelTnReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_t SelTnReq, String SWITCH_WC) { 
		this.Header = Header;
		this.SelTnReq = SelTnReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
