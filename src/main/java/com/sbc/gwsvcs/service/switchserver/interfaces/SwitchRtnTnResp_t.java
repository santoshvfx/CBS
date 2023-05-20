package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchRtnTnResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public String REQ_STS_2_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchRtnTnResp_t () {
	}
	public SwitchRtnTnResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, String REQ_STS_2_CD, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.REQ_STS_2_CD = REQ_STS_2_CD;
		this.Umsg = Umsg;

	} 
}
