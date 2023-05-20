package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class GoodInfo_t implements org.omg.CORBA.portable.IDLEntity { 
	public String VANITY_CLS_CD;
	public String WORD_TX;

	public GoodInfo_t () {
	}
	public GoodInfo_t (String VANITY_CLS_CD, String WORD_TX) { 
		this.VANITY_CLS_CD = VANITY_CLS_CD;
		this.WORD_TX = WORD_TX;

	} 
}
