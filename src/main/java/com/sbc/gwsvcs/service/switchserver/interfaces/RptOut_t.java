package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class RptOut_t implements org.omg.CORBA.portable.IDLEntity { 
	public String INPT_SOPTN_CD;
	public String OTPT_SOPTN_CD;

	public RptOut_t () {
	}
	public RptOut_t (String INPT_SOPTN_CD, String OTPT_SOPTN_CD) { 
		this.INPT_SOPTN_CD = INPT_SOPTN_CD;
		this.OTPT_SOPTN_CD = OTPT_SOPTN_CD;

	} 
}
