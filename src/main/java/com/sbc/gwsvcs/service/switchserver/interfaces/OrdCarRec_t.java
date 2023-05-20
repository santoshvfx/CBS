package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class OrdCarRec_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CKT_TERMN_ID;
	public String CTRL_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t[] CarRecAcl;

	public OrdCarRec_t () {
	}
	public OrdCarRec_t (String CKT_TERMN_ID, String CTRL_CD, com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t[] CarRecAcl) { 
		this.CKT_TERMN_ID = CKT_TERMN_ID;
		this.CTRL_CD = CTRL_CD;
		this.CarRecAcl = CarRecAcl;

	} 
}
