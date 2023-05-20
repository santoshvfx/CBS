package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class InqOrdResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public String OTPT_LN_QTY;
	public String[] OTPT_LN_DESC_TX;

	public InqOrdResp_t () {
	}
	public InqOrdResp_t (String OTPT_LN_QTY, String[] OTPT_LN_DESC_TX) { 
		this.OTPT_LN_QTY = OTPT_LN_QTY;
		this.OTPT_LN_DESC_TX = OTPT_LN_DESC_TX;

	} 
}
