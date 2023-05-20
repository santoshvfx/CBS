package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Trdata_t implements org.omg.CORBA.portable.IDLEntity { 
	public char[] TRNSLTN_TAG_CD;
	public String INACT_CD;
	public String INVAL_ID;

	public Trdata_t () {
	}
	public Trdata_t (char[] TRNSLTN_TAG_CD, String INACT_CD, String INVAL_ID) { 
		this.TRNSLTN_TAG_CD = TRNSLTN_TAG_CD;
		this.INACT_CD = INACT_CD;
		this.INVAL_ID = INVAL_ID;

	} 
}
