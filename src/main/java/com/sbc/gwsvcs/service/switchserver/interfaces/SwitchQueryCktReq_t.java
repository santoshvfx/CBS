package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchQueryCktReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public String SWITCH_WC;
	public com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktReq_t QueryCktReq;

	public SwitchQueryCktReq_t () {
	}
	public SwitchQueryCktReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, String SWITCH_WC, com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktReq_t QueryCktReq) { 
		this.Header = Header;
		this.SWITCH_WC = SWITCH_WC;
		this.QueryCktReq = QueryCktReq;

	} 
}
