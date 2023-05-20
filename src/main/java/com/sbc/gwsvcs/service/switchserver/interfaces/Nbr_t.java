package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Nbr_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t TN;
	public String TRMNTG_TRAF_AREA_CD;
	public String BDY_DT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t Rls2Dt;
	public String MTCH_IND;

	public Nbr_t () {
	}
	public Nbr_t (com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t TN, String TRMNTG_TRAF_AREA_CD, String BDY_DT, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t Rls2Dt, String MTCH_IND) { 
		this.TN = TN;
		this.TRMNTG_TRAF_AREA_CD = TRMNTG_TRAF_AREA_CD;
		this.BDY_DT = BDY_DT;
		this.Rls2Dt = Rls2Dt;
		this.MTCH_IND = MTCH_IND;

	} 
}
