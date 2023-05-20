package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class TneSeltVarb_t implements org.omg.CORBA.portable.IDLEntity { 
	public String QTY_CT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t NpaPrfx;
	public String BDY_DT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_t GoodInfo;

	public TneSeltVarb_t () {
	}
	public TneSeltVarb_t (String QTY_CT, com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t NpaPrfx, String BDY_DT, com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_t GoodInfo) { 
		this.QTY_CT = QTY_CT;
		this.NpaPrfx = NpaPrfx;
		this.BDY_DT = BDY_DT;
		this.GoodInfo = GoodInfo;

	} 
}
