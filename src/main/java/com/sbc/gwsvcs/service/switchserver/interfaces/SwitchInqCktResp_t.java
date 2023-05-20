package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqCktResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t[] InqCktResp;
	public String ExchKeyId;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchInqCktResp_t () {
	}
	public SwitchInqCktResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t[] InqCktResp, String ExchKeyId, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.InqCktResp = InqCktResp;
		this.ExchKeyId = ExchKeyId;
		this.Umsg = Umsg;

	} 
}
