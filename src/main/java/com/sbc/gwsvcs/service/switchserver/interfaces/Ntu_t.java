package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Ntu_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String TN_HI_RNGE_ID;
	public String TN_PARSE_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t Uattr;
	public String AVDT_IND;
	public String RTE_IDX;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_t Fctr;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Memb;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] Ic;

	public Ntu_t () {
	}
	public Ntu_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String TN_HI_RNGE_ID, String TN_PARSE_CD, com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t Uattr, String AVDT_IND, String RTE_IDX, com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_t Fctr, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Memb, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] Ic) { 
		this.Ex = Ex;
		this.TN_HI_RNGE_ID = TN_HI_RNGE_ID;
		this.TN_PARSE_CD = TN_PARSE_CD;
		this.Uattr = Uattr;
		this.AVDT_IND = AVDT_IND;
		this.RTE_IDX = RTE_IDX;
		this.Fctr = Fctr;
		this.Memb = Memb;
		this.Ic = Ic;

	} 
}
