package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class WsiAsmReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String SWITCH_TYPE_CD;
	public String TN_UPD_FCN_CD;
	public String SELT_CD;

	public WsiAsmReq_t () {
	}
	public WsiAsmReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String SWITCH_TYPE_CD, String TN_UPD_FCN_CD, String SELT_CD) { 
		this.Ex = Ex;
		this.SWITCH_TYPE_CD = SWITCH_TYPE_CD;
		this.TN_UPD_FCN_CD = TN_UPD_FCN_CD;
		this.SELT_CD = SELT_CD;

	} 
}
