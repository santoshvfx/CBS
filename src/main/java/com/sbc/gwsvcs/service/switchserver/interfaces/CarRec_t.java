package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CarRec_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CTRL_CD;
	public String ASGNM_REQ_IND;
	public String FDT_TX;
	public String FRM_RMK_TX;
	public String CKT_TERMN_ID;
	public String ORD_TYPE_CD;
	public String CORR_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t[] CarAcl;

	public CarRec_t () {
	}
	public CarRec_t (String CTRL_CD, String ASGNM_REQ_IND, String FDT_TX, String FRM_RMK_TX, String CKT_TERMN_ID, String ORD_TYPE_CD, String CORR_CD, com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t[] CarAcl) { 
		this.CTRL_CD = CTRL_CD;
		this.ASGNM_REQ_IND = ASGNM_REQ_IND;
		this.FDT_TX = FDT_TX;
		this.FRM_RMK_TX = FRM_RMK_TX;
		this.CKT_TERMN_ID = CKT_TERMN_ID;
		this.ORD_TYPE_CD = ORD_TYPE_CD;
		this.CORR_CD = CORR_CD;
		this.CarAcl = CarAcl;

	} 
}
