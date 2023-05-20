package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Seqp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t SeqpNew;
	public String SWITCH_ID_NM;

	public Seqp_t () {
	}
	public Seqp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t SeqpNew, String SWITCH_ID_NM) { 
		this.SeqpNew = SeqpNew;
		this.SWITCH_ID_NM = SWITCH_ID_NM;

	} 
}
