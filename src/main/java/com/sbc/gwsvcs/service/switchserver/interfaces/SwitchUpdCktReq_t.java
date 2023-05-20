package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchUpdCktReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_t[] UpdCktReq;
	public String SWITCH_WC;

	public SwitchUpdCktReq_t () {
	}
	public SwitchUpdCktReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_t[] UpdCktReq, String SWITCH_WC) { 
		this.Header = Header;
		this.UpdCktReq = UpdCktReq;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
