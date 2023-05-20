package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class CktRecSvc_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] SvcExOld;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] SvcHuntOld;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] SvcAssocOld;
	public com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_t SvcSattrOld;

	public CktRecSvc_t () {
	}
	public CktRecSvc_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] SvcExOld, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] SvcHuntOld, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[] SvcAssocOld, com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_t SvcSattrOld) { 
		this.SvcExOld = SvcExOld;
		this.SvcHuntOld = SvcHuntOld;
		this.SvcAssocOld = SvcAssocOld;
		this.SvcSattrOld = SvcSattrOld;

	} 
}
