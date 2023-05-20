package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchTnUpdReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t[] TnUpdReq;
	public String SWITCH_WC;

	public SwitchTnUpdReq_t () {
	}
	public SwitchTnUpdReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t[] TnUpdReq, String SWITCH_WC) { 
		this.Header = Header;
		this.TnUpdReq = TnUpdReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
