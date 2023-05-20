package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Updopt_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TN_LN_CT;
	public String PNDG_IND;
	public String SELT_STATE_CD;
	public String TN_LIST_NBR;
	public com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t Uattr;
	public String[] EMP_ID;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t[] CHG_DT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t FROM_AVAIL_DT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t TO_AVAIL_DT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t[] Comp;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] Memb;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Tric;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic;

	public Updopt_t () {
	}
	public Updopt_t (String TN_LN_CT, String PNDG_IND, String SELT_STATE_CD, String TN_LIST_NBR, com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t Uattr, String[] EMP_ID, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t[] CHG_DT, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t FROM_AVAIL_DT, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t TO_AVAIL_DT, com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t[] Comp, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] Memb, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Tric, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic) { 
		this.TN_LN_CT = TN_LN_CT;
		this.PNDG_IND = PNDG_IND;
		this.SELT_STATE_CD = SELT_STATE_CD;
		this.TN_LIST_NBR = TN_LIST_NBR;
		this.Uattr = Uattr;
		this.EMP_ID = EMP_ID;
		this.CHG_DT = CHG_DT;
		this.FROM_AVAIL_DT = FROM_AVAIL_DT;
		this.TO_AVAIL_DT = TO_AVAIL_DT;
		this.Comp = Comp;
		this.Memb = Memb;
		this.Tric = Tric;
		this.Ic = Ic;

	} 
}
