package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SvcSeqp_t implements org.omg.CORBA.portable.IDLEntity { 
	public String SWITCH_ID_NM;
	public String SWITCH_ID;

	public SvcSeqp_t () {
	}
	public SvcSeqp_t (String SWITCH_ID_NM, String SWITCH_ID) { 
		this.SWITCH_ID_NM = SWITCH_ID_NM;
		this.SWITCH_ID = SWITCH_ID;

	} 
}
