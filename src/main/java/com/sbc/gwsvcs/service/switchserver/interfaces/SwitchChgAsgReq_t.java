package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchChgAsgReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_t[] ChgAsgReq;
	public String SWITCH_WC;
	public String ORD_2_NBR;
	public String USER_MODE_IND;

	public SwitchChgAsgReq_t () {
	}
	public SwitchChgAsgReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_t[] ChgAsgReq, String SWITCH_WC, String ORD_2_NBR, String USER_MODE_IND) { 
		this.Header = Header;
		this.ChgAsgReq = ChgAsgReq;
		this.SWITCH_WC = SWITCH_WC;
		this.ORD_2_NBR = ORD_2_NBR;
		this.USER_MODE_IND = USER_MODE_IND;

	} 
}
