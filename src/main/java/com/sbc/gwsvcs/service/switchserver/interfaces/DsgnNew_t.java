package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class DsgnNew_t implements org.omg.CORBA.portable.IDLEntity { 
	public String CNDCTR_NBR;
	public String CO_TERMN_CD;
	public String CTGY_CD;
	public String SVC_GRAD;
	public String SVC_TYPE_CD_2;
	public String SVC_CLS_CD;
	public String SIGG_CD;
	public String CO_ADMN_TYPE_CD;
	public String ASGN_USOC_CD;
	public String CLS_SVC_USOC_CD;

	public DsgnNew_t () {
	}
	public DsgnNew_t (String CNDCTR_NBR, String CO_TERMN_CD, String CTGY_CD, String SVC_GRAD, String SVC_TYPE_CD_2, String SVC_CLS_CD, String SIGG_CD, String CO_ADMN_TYPE_CD, String ASGN_USOC_CD, String CLS_SVC_USOC_CD) { 
		this.CNDCTR_NBR = CNDCTR_NBR;
		this.CO_TERMN_CD = CO_TERMN_CD;
		this.CTGY_CD = CTGY_CD;
		this.SVC_GRAD = SVC_GRAD;
		this.SVC_TYPE_CD_2 = SVC_TYPE_CD_2;
		this.SVC_CLS_CD = SVC_CLS_CD;
		this.SIGG_CD = SIGG_CD;
		this.CO_ADMN_TYPE_CD = CO_ADMN_TYPE_CD;
		this.ASGN_USOC_CD = ASGN_USOC_CD;
		this.CLS_SVC_USOC_CD = CLS_SVC_USOC_CD;

	} 
}
