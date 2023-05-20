package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class SwitchWsiPrvReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t InqEx;
	public char DUAL_SVC_IND;
	public String ORD_TYPE_2_CD;
	public String ASGNM_ACTN_CD;
	public String VIEW_DT;
	public String SWITCH_WC;
	public String ORD_2_NBR;

	public SwitchWsiPrvReq_t () {
	}
	public SwitchWsiPrvReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Header_t Header, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t InqEx, char DUAL_SVC_IND, String ORD_TYPE_2_CD, String ASGNM_ACTN_CD, String VIEW_DT, String SWITCH_WC, String ORD_2_NBR) { 
		this.Header = Header;
		this.InqEx = InqEx;
		this.DUAL_SVC_IND = DUAL_SVC_IND;
		this.ORD_TYPE_2_CD = ORD_TYPE_2_CD;
		this.ASGNM_ACTN_CD = ASGNM_ACTN_CD;
		this.VIEW_DT = VIEW_DT;
		this.SWITCH_WC = SWITCH_WC;
		this.ORD_2_NBR = ORD_2_NBR;

	} 
}
