package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Exception_t implements org.omg.CORBA.portable.IDLEntity { 
	public int HtErrCd;
	public String HostCnvrstnGrp;
	public String HtErrLogTx;
	public String HtErrTx;

	public Exception_t () {
	}
	public Exception_t (int HtErrCd, String HostCnvrstnGrp, String HtErrLogTx, String HtErrTx) { 
		this.HtErrCd = HtErrCd;
		this.HostCnvrstnGrp = HostCnvrstnGrp;
		this.HtErrLogTx = HtErrLogTx;
		this.HtErrTx = HtErrTx;

	} 
}
