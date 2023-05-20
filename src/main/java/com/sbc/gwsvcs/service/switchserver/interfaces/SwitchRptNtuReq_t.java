package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchRptNtuReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_t RptNtu;
	public com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_t RptOut;
	public String SWITCH_WC;

	public SwitchRptNtuReq_t () {
	}
	public SwitchRptNtuReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_t RptNtu, com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_t RptOut, String SWITCH_WC) { 
		this.Header = Header;
		this.RptNtu = RptNtu;
		this.RptOut = RptOut;
		this.SWITCH_WC = SWITCH_WC;

	} 
}
