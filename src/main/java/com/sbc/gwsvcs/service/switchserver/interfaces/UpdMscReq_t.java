package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class UpdMscReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TN_UPD_FCN_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] AsgLim;
	public String ACTN_CD;

	public UpdMscReq_t () {
	}
	public UpdMscReq_t (String TN_UPD_FCN_CD, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] AsgLim, String ACTN_CD) { 
		this.TN_UPD_FCN_CD = TN_UPD_FCN_CD;
		this.Ex = Ex;
		this.AsgLim = AsgLim;
		this.ACTN_CD = ACTN_CD;

	} 
}
