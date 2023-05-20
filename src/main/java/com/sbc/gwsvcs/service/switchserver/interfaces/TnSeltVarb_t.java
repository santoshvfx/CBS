package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class TnSeltVarb_t implements org.omg.CORBA.portable.IDLEntity { 
	public String QTY_CT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t NpaPrfx;
	public String BLG_DY;
	public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t SpcfcTn;

	public TnSeltVarb_t () {
	}
	public TnSeltVarb_t (String QTY_CT, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t NpaPrfx, String BLG_DY, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t SpcfcTn) { 
		this.QTY_CT = QTY_CT;
		this.NpaPrfx = NpaPrfx;
		this.BLG_DY = BLG_DY;
		this.SpcfcTn = SpcfcTn;

	} 
}
