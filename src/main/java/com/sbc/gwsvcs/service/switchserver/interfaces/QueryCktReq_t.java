package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class QueryCktReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;
	public String TN_UPD_FCN_CD;

	public QueryCktReq_t () {
	}
	public QueryCktReq_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex, String TN_UPD_FCN_CD) { 
		this.Ex = Ex;
		this.TN_UPD_FCN_CD = TN_UPD_FCN_CD;

	} 
}
