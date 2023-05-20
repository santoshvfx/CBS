package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class DtTm_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t DT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Tm_t TM;
	public String TM_OST;

	public DtTm_t () {
	}
	public DtTm_t (com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t DT, com.sbc.gwsvcs.service.switchserver.interfaces.Tm_t TM, String TM_OST) { 
		this.DT = DT;
		this.TM = TM;
		this.TM_OST = TM_OST;

	} 
}
