package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiTnlReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t InqEx;
	public String SWITCH_WC;

	public SwitchWsiTnlReq_t () {
	}
	public SwitchWsiTnlReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t InqEx, String SWITCH_WC) { 
		this.Header = Header;
		this.InqEx = InqEx;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
