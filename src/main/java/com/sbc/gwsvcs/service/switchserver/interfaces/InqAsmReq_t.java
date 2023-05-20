package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class InqAsmReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public String NTU;
	public String LO;
	public String HI;
	public String FOPTN;

	public InqAsmReq_t () {
	}
	public InqAsmReq_t (String NTU, String LO, String HI, String FOPTN) { 
		this.NTU = NTU;
		this.LO = LO;
		this.HI = HI;
		this.FOPTN = FOPTN;

	} 
}
