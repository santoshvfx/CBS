package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchInqGrpResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpResp_t InqGrpResp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchInqGrpResp_t () {
	}
	public SwitchInqGrpResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpResp_t InqGrpResp, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.InqGrpResp = InqGrpResp;
		this.Umsg = Umsg;

	} 
}
