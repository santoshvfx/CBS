package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqOrdReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t InqOrdReq;
	public String SWITCH_WC;

	public SwitchInqOrdReq_t () {
	}
	public SwitchInqOrdReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t InqOrdReq, String SWITCH_WC) { 
		this.Header = Header;
		this.InqOrdReq = InqOrdReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
