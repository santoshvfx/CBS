package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Ctl_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SYS_CONN;
	public String TN_UPD_FCN_CD;
	public String DIP_IND;

	public Ctl_t () {
	}
	public Ctl_t (String SYS_CONN, String TN_UPD_FCN_CD, String DIP_IND) { 
		this.SYS_CONN = SYS_CONN;
		this.TN_UPD_FCN_CD = TN_UPD_FCN_CD;
		this.DIP_IND = DIP_IND;

	} 
}
