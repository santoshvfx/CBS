package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class OrdAcl_t implements org.omg.CORBA.portable.IDLEntity { 
	public String ACTN_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t AclCec;
	public String ASGN_USOC_CD;
	public String CLS_SVC_USOC_CD;
	public String USOC;
	public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t TEL_LN_ID;
	public String FRM_RMK_TX;
	public String CKT_2_ID;

	public OrdAcl_t () {
	}
	public OrdAcl_t (String ACTN_CD, com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t AclCec, String ASGN_USOC_CD, String CLS_SVC_USOC_CD, String USOC, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t TEL_LN_ID, String FRM_RMK_TX, String CKT_2_ID) { 
		this.ACTN_CD = ACTN_CD;
		this.AclCec = AclCec;
		this.ASGN_USOC_CD = ASGN_USOC_CD;
		this.CLS_SVC_USOC_CD = CLS_SVC_USOC_CD;
		this.USOC = USOC;
		this.TEL_LN_ID = TEL_LN_ID;
		this.FRM_RMK_TX = FRM_RMK_TX;
		this.CKT_2_ID = CKT_2_ID;

	} 
}
