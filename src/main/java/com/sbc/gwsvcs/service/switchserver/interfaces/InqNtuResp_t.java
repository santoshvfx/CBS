package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class InqNtuResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String OTPT_LN_QTY;
	public String[] OTPT_LN_DESC_TX;

	public InqNtuResp_t () {
	}
	public InqNtuResp_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String OTPT_LN_QTY, String[] OTPT_LN_DESC_TX) { 
		this.Ex = Ex;
		this.OTPT_LN_QTY = OTPT_LN_QTY;
		this.OTPT_LN_DESC_TX = OTPT_LN_DESC_TX;

	} 
}
