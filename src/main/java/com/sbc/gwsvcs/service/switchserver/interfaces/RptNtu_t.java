package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class RptNtu_t implements org.omg.CORBA.portable.IDLEntity { 
	public String NU_TYPE_CD;
	public String NU_LOW_ID;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExInport;
	public String SELT_STATE_CD;
	public String OPEN_SIDE_CD;
	public String SWITCH_TN_REQ_QTY;
	public String FOPTN;

	public RptNtu_t () {
	}
	public RptNtu_t (String NU_TYPE_CD, String NU_LOW_ID, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t ExInport, String SELT_STATE_CD, String OPEN_SIDE_CD, String SWITCH_TN_REQ_QTY, String FOPTN) { 
		this.NU_TYPE_CD = NU_TYPE_CD;
		this.NU_LOW_ID = NU_LOW_ID;
		this.ExInport = ExInport;
		this.SELT_STATE_CD = SELT_STATE_CD;
		this.OPEN_SIDE_CD = OPEN_SIDE_CD;
		this.SWITCH_TN_REQ_QTY = SWITCH_TN_REQ_QTY;
		this.FOPTN = FOPTN;

	} 
}
