package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CktRecCtl_t implements org.omg.CORBA.portable.IDLEntity { 
	public String FND_CKT_IND;
	public String CKT_VIEW_CD;

	public CktRecCtl_t () {
	}
	public CktRecCtl_t (String FND_CKT_IND, String CKT_VIEW_CD) { 
		this.FND_CKT_IND = FND_CKT_IND;
		this.CKT_VIEW_CD = CKT_VIEW_CD;

	} 
}
