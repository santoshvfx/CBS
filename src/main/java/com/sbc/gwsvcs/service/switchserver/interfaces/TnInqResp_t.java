package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class TnInqResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String DISCT_ASGNM_CTGY_CD;
	public String DISCT_CO_TYPE_CD;
	public String TN_RLS_DT_TX;
	public String TN_RMK_TX;
	public com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM;
	public String TN_TYPE_CD;
	public String TN_LIST_NBR;
	public String TN_LIM_VALU_CD;

	public TnInqResp_t () {
	}
	public TnInqResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String DISCT_ASGNM_CTGY_CD, String DISCT_CO_TYPE_CD, String TN_RLS_DT_TX, String TN_RMK_TX, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM, String TN_TYPE_CD, String TN_LIST_NBR, String TN_LIM_VALU_CD) { 
		this.Ex = Ex;
		this.DISCT_ASGNM_CTGY_CD = DISCT_ASGNM_CTGY_CD;
		this.DISCT_CO_TYPE_CD = DISCT_CO_TYPE_CD;
		this.TN_RLS_DT_TX = TN_RLS_DT_TX;
		this.TN_RMK_TX = TN_RMK_TX;
		this.INQ_CHNG_DT_TM = INQ_CHNG_DT_TM;
		this.TN_TYPE_CD = TN_TYPE_CD;
		this.TN_LIST_NBR = TN_LIST_NBR;
		this.TN_LIM_VALU_CD = TN_LIM_VALU_CD;

	} 
}
