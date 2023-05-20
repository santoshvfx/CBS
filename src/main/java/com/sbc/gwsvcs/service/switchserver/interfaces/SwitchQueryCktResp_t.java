package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchQueryCktResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t[] QueryCktResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchQueryCktResp_t () {
	}
	public SwitchQueryCktResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t[] QueryCktResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.QueryCktResp = QueryCktResp;
		this.Umsg = Umsg;

	} 
}
