package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiTnlResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public String WC_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;

	public SwitchWsiTnlResp_t () {
	}
	public SwitchWsiTnlResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, String WC_CD, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg) { 
		this.Header = Header;
		this.WC_CD = WC_CD;
		this.Umsg = Umsg;

	} 
}
