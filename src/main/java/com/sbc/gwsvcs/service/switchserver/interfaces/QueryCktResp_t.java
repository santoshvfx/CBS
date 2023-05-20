package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class QueryCktResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public String PTY_CKT;
	public com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM;
	public String ASGN_USOC_CD;
	public String CO_ASGNM_TYPE;
	public String CLS_SVC_USOC_CD;
	public String CTGY_CD;
	public com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t[] EqpOldId;
	public com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t CktRecSvc;

	public QueryCktResp_t () {
	}
	public QueryCktResp_t (String PTY_CKT, com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t INQ_CHNG_DT_TM, String ASGN_USOC_CD, String CO_ASGNM_TYPE, String CLS_SVC_USOC_CD, String CTGY_CD, com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t[] EqpOldId, com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t CktRecSvc) { 
		this.PTY_CKT = PTY_CKT;
		this.INQ_CHNG_DT_TM = INQ_CHNG_DT_TM;
		this.ASGN_USOC_CD = ASGN_USOC_CD;
		this.CO_ASGNM_TYPE = CO_ASGNM_TYPE;
		this.CLS_SVC_USOC_CD = CLS_SVC_USOC_CD;
		this.CTGY_CD = CTGY_CD;
		this.EqpOldId = EqpOldId;
		this.CktRecSvc = CktRecSvc;

	} 
}
