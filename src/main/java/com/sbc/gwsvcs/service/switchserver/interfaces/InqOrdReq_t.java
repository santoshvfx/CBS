package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class InqOrdReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public String ORD_2_NBR;
	public com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t[] InqOrdEx;
	public String FOPTN;

	public InqOrdReq_t () {
	}
	public InqOrdReq_t (String ORD_2_NBR, com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t[] InqOrdEx, String FOPTN) { 
		this.ORD_2_NBR = ORD_2_NBR;
		this.InqOrdEx = InqOrdEx;
		this.FOPTN = FOPTN;

	} 
}
