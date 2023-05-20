package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqOrdResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_t[] InqOrdResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchInqOrdResp_t () {
	}
	public SwitchInqOrdResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_t[] InqOrdResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.InqOrdResp = InqOrdResp;
		this.Umsg = Umsg;

	} 
}
