package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqGrpReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_t InqGrpReq;
	public String SWITCH_WC;

	public SwitchInqGrpReq_t () {
	}
	public SwitchInqGrpReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_t InqGrpReq, String SWITCH_WC) { 
		this.Header = Header;
		this.InqGrpReq = InqGrpReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
