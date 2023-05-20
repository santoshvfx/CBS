package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class TnInqReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic;
	public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t[] NPA_PRFX_CD;
	public String TN_PATT_SELT_OPT;
	public String SWITCH_TN_REQ_QTY;
	public String TN_WORD_PATT;
	public String EXCL_TN_IND;
	public String TN_TYPE_CD;
	public String TN_SPARE_IND;

	public TnInqReq_t () {
	}
	public TnInqReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t[] NPA_PRFX_CD, String TN_PATT_SELT_OPT, String SWITCH_TN_REQ_QTY, String TN_WORD_PATT, String EXCL_TN_IND, String TN_TYPE_CD, String TN_SPARE_IND) { 
		this.Ic = Ic;
		this.NPA_PRFX_CD = NPA_PRFX_CD;
		this.TN_PATT_SELT_OPT = TN_PATT_SELT_OPT;
		this.SWITCH_TN_REQ_QTY = SWITCH_TN_REQ_QTY;
		this.TN_WORD_PATT = TN_WORD_PATT;
		this.EXCL_TN_IND = EXCL_TN_IND;
		this.TN_TYPE_CD = TN_TYPE_CD;
		this.TN_SPARE_IND = TN_SPARE_IND;

	} 
}
