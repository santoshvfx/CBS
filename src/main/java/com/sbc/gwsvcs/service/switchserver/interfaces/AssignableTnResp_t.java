package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class AssignableTnResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] Asglim;
	public String TN_RMK_TX;
	public com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM;
	public String TN_TYPE_CD;
	public String NPUB_IND;
	public String DISCT_ASGNM_CTGY_CD;
	public String DISCT_CO_TYPE_CD;
	public String TN_RLS_DT_TX;
	public String RT_ZONE;
	public String CTX_NM;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic;
	public String DLCT_IND;

	public AssignableTnResp_t () {
	}
	public AssignableTnResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[] Asglim, String TN_RMK_TX, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM, String TN_TYPE_CD, String NPUB_IND, String DISCT_ASGNM_CTGY_CD, String DISCT_CO_TYPE_CD, String TN_RLS_DT_TX, String RT_ZONE, String CTX_NM, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic, String DLCT_IND) { 
		this.Ex = Ex;
		this.Asglim = Asglim;
		this.TN_RMK_TX = TN_RMK_TX;
		this.INQ_CHNG_DT_TM = INQ_CHNG_DT_TM;
		this.TN_TYPE_CD = TN_TYPE_CD;
		this.NPUB_IND = NPUB_IND;
		this.DISCT_ASGNM_CTGY_CD = DISCT_ASGNM_CTGY_CD;
		this.DISCT_CO_TYPE_CD = DISCT_CO_TYPE_CD;
		this.TN_RLS_DT_TX = TN_RLS_DT_TX;
		this.RT_ZONE = RT_ZONE;
		this.CTX_NM = CTX_NM;
		this.Ic = Ic;
		this.DLCT_IND = DLCT_IND;

	} 
}
