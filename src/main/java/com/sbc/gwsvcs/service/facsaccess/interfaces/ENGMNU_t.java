package com.sbc.gwsvcs.service.facsaccess.interfaces;


final public class ENGMNU_t implements org.omg.CORBA.portable.IDLEntity { 
	public String TID;
	public String WC;
	public String EWO;
	public String TR;
	public String EMP;
	public String FUNC;
	public String OBJ;

	public ENGMNU_t () {
	}
	public ENGMNU_t (String TID, String WC, String EWO, String TR, String EMP, String FUNC, String OBJ) { 
		this.TID = TID;
		this.WC = WC;
		this.EWO = EWO;
		this.TR = TR;
		this.EMP = EMP;
		this.FUNC = FUNC;
		this.OBJ = OBJ;

	} 
}
