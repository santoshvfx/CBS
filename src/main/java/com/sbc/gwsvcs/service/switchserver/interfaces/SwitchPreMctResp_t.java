package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchPreMctResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchPreMctResp_t () {
	}
	public SwitchPreMctResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.Umsg = Umsg;

	} 
}
