package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class AssignableTnReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String TN_HI_RNGE_ID;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic;
	public String SWITCH_TN_REQ_QTY;
	public String TN_TYPE_CD;
	public String RT_ZONE;
	public String CTX_NM;

	public AssignableTnReq_t () {
	}
	public AssignableTnReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String TN_HI_RNGE_ID, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ic, String SWITCH_TN_REQ_QTY, String TN_TYPE_CD, String RT_ZONE, String CTX_NM) { 
		this.Ex = Ex;
		this.TN_HI_RNGE_ID = TN_HI_RNGE_ID;
		this.Ic = Ic;
		this.SWITCH_TN_REQ_QTY = SWITCH_TN_REQ_QTY;
		this.TN_TYPE_CD = TN_TYPE_CD;
		this.RT_ZONE = RT_ZONE;
		this.CTX_NM = CTX_NM;

	} 
}
