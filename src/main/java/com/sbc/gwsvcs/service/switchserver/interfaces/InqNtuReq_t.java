package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class InqNtuReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String HI_VALU;
	public String CKT_OPTN_IND;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t VIEW_DT;
	public String LAST_VIEW_IND;
	public String FOPTN;

	public InqNtuReq_t () {
	}
	public InqNtuReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String HI_VALU, String CKT_OPTN_IND, com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t VIEW_DT, String LAST_VIEW_IND, String FOPTN) { 
		this.Ex = Ex;
		this.HI_VALU = HI_VALU;
		this.CKT_OPTN_IND = CKT_OPTN_IND;
		this.VIEW_DT = VIEW_DT;
		this.LAST_VIEW_IND = LAST_VIEW_IND;
		this.FOPTN = FOPTN;

	} 
}
