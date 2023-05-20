package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchPreMctReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_t[] PreMctReq;
	public String SWITCH_WC;
	public String ORD_2_NBR;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t ORD_2_DDT;

	public SwitchPreMctReq_t () {
	}
	public SwitchPreMctReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_t[] PreMctReq, String SWITCH_WC, String ORD_2_NBR, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t ORD_2_DDT) { 
		this.Header = Header;
		this.PreMctReq = PreMctReq;
		this.SWITCH_WC = SWITCH_WC;
		this.ORD_2_NBR = ORD_2_NBR;
		this.ORD_2_DDT = ORD_2_DDT;

	} 
}
