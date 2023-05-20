package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CarRecAcl_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CKT_TERMN_ID;
	public String ACTN_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t[] Loop;

	public CarRecAcl_t () {
	}
	public CarRecAcl_t (String CKT_TERMN_ID, String ACTN_CD, com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t[] Loop) { 
		this.CKT_TERMN_ID = CKT_TERMN_ID;
		this.ACTN_CD = ACTN_CD;
		this.Loop = Loop;

	} 
}
