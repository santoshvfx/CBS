package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Sattr_t implements org.omg.CORBA.portable.IDLEntity { 
	public String ORD_3_NBR;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t DUE_DT;
	public String TN_RMK_TX;

	public Sattr_t () {
	}
	public Sattr_t (String ORD_3_NBR, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t DUE_DT, String TN_RMK_TX) { 
		this.ORD_3_NBR = ORD_3_NBR;
		this.DUE_DT = DUE_DT;
		this.TN_RMK_TX = TN_RMK_TX;

	} 
}
