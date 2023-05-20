package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchUpdMscReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_t UpdMscReq;
	public String SWITCH_WC;

	public SwitchUpdMscReq_t () {
	}
	public SwitchUpdMscReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_t UpdMscReq, String SWITCH_WC) { 
		this.Header = Header;
		this.UpdMscReq = UpdMscReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
