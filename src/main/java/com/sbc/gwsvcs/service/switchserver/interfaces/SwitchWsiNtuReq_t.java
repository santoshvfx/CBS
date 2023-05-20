package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiNtuReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_t WsiNtuReq;
	public String SWITCH_WC;

	public SwitchWsiNtuReq_t () {
	}
	public SwitchWsiNtuReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_t WsiNtuReq, String SWITCH_WC) { 
		this.Header = Header;
		this.WsiNtuReq = WsiNtuReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
