package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CktRecSvc1_t implements org.omg.CORBA.portable.IDLEntity { 
	public String FND_SVC_IND;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] ExOld;

	public CktRecSvc1_t () {
	}
	public CktRecSvc1_t (String FND_SVC_IND, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] ExOld) { 
		this.FND_SVC_IND = FND_SVC_IND;
		this.ExOld = ExOld;

	} 
}
