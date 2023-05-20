package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchSelTnResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t SelTnResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchSelTnResp_t () {
	}
	public SwitchSelTnResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t SelTnResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.SelTnResp = SelTnResp;
		this.Umsg = Umsg;

	} 
}
