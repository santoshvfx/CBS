package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class WsiNtuReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String TN_UPD_FCN_CD;

	public WsiNtuReq_t () {
	}
	public WsiNtuReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String TN_UPD_FCN_CD) { 
		this.Ex = Ex;
		this.TN_UPD_FCN_CD = TN_UPD_FCN_CD;

	} 
}
