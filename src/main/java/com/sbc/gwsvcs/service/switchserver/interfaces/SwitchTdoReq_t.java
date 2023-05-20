package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchTdoReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public String MSEG_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Car_t[] Car;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t[] Masg;
	public String SWITCH_WC;
	public String ORD_2_NBR;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t ORD_2_DDT;

	public SwitchTdoReq_t () {
	}
	public SwitchTdoReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, String MSEG_CD, com.sbc.gwsvcs.service.switchserver.interfaces.Car_t[] Car, com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t[] Masg, String SWITCH_WC, String ORD_2_NBR, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t ORD_2_DDT) { 
		this.Header = Header;
		this.MSEG_CD = MSEG_CD;
		this.Car = Car;
		this.Masg = Masg;
		this.SWITCH_WC = SWITCH_WC;
		this.ORD_2_NBR = ORD_2_NBR;
		this.ORD_2_DDT = ORD_2_DDT;

	} 
}
