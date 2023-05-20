package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchRptNtuResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg;
	public com.sbc.gwsvcs.service.switchserver.interfaces.PrtRec_t[] PrtRec;

	public SwitchRptNtuResp_t () {
	}
	public SwitchRptNtuResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[] Umsg, com.sbc.gwsvcs.service.switchserver.interfaces.PrtRec_t[] PrtRec) { 
		this.Header = Header;
		this.Umsg = Umsg;
		this.PrtRec = PrtRec;

	} 
}
