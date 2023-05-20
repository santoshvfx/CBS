package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class OrdCtl_t implements org.omg.CORBA.portable.IDLEntity { 
	public String DATA_IND;
	public String CTRL_CD;
	public String CKT_2_ID;
	public String ASGNM_REQ_IND;
	public String FRM_RMK_TX;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t[] Trdata;

	public OrdCtl_t () {
	}
	public OrdCtl_t (String DATA_IND, String CTRL_CD, String CKT_2_ID, String ASGNM_REQ_IND, String FRM_RMK_TX, com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t[] Trdata) { 
		this.DATA_IND = DATA_IND;
		this.CTRL_CD = CTRL_CD;
		this.CKT_2_ID = CKT_2_ID;
		this.ASGNM_REQ_IND = ASGNM_REQ_IND;
		this.FRM_RMK_TX = FRM_RMK_TX;
		this.Trdata = Trdata;

	} 
}
