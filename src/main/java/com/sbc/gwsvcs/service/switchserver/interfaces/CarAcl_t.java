package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CarAcl_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CKT_TERMN_ID;
	public String ACTN_CD;
	public String SWITCH_ID;
	public char NO_REUSE_IND;
	public String TR_CALL_IND;
	public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t TEL_LN_ID;
	public String SSP_IND;
	public String FDT_TX;
	public String FRM_RMK_TX;
	public String CTRL_GRP_ID;
	public com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t AclCec;

	public CarAcl_t () {
	}
	public CarAcl_t (String CKT_TERMN_ID, String ACTN_CD, String SWITCH_ID, char NO_REUSE_IND, String TR_CALL_IND, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t TEL_LN_ID, String SSP_IND, String FDT_TX, String FRM_RMK_TX, String CTRL_GRP_ID, com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t AclCec) { 
		this.CKT_TERMN_ID = CKT_TERMN_ID;
		this.ACTN_CD = ACTN_CD;
		this.SWITCH_ID = SWITCH_ID;
		this.NO_REUSE_IND = NO_REUSE_IND;
		this.TR_CALL_IND = TR_CALL_IND;
		this.TEL_LN_ID = TEL_LN_ID;
		this.SSP_IND = SSP_IND;
		this.FDT_TX = FDT_TX;
		this.FRM_RMK_TX = FRM_RMK_TX;
		this.CTRL_GRP_ID = CTRL_GRP_ID;
		this.AclCec = AclCec;

	} 
}
