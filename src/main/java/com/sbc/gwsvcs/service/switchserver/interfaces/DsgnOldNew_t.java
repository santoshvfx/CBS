package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class DsgnOldNew_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_t DsgnNew;
	public com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_t DsgnOld;

	public DsgnOldNew_t () {
	}
	public DsgnOldNew_t (com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_t DsgnNew, com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_t DsgnOld) { 
		this.DsgnNew = DsgnNew;
		this.DsgnOld = DsgnOld;

	} 
}
