package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class UpdAsmReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String SWITCH_TYPE_CD;
	public String TN_UPD_FCN_CD;
	public String USER_NM;
	public String SWITCH_ID_NM;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t AGE_DT;
	public String RET_IND;
	public String ACTN_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CompId_t[] CompId;

	public UpdAsmReq_t () {
	}
	public UpdAsmReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String SWITCH_TYPE_CD, String TN_UPD_FCN_CD, String USER_NM, String SWITCH_ID_NM, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t AGE_DT, String RET_IND, String ACTN_CD, com.sbc.gwsvcs.service.switchserver.interfaces.CompId_t[] CompId) { 
		this.Ex = Ex;
		this.SWITCH_TYPE_CD = SWITCH_TYPE_CD;
		this.TN_UPD_FCN_CD = TN_UPD_FCN_CD;
		this.USER_NM = USER_NM;
		this.SWITCH_ID_NM = SWITCH_ID_NM;
		this.AGE_DT = AGE_DT;
		this.RET_IND = RET_IND;
		this.ACTN_CD = ACTN_CD;
		this.CompId = CompId;

	} 
}
