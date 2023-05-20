package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class ExOldNew_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex_Old;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex_New;

	public ExOldNew_t () {
	}
	public ExOldNew_t (com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex_Old, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex_New) { 
		this.Ex_Old = Ex_Old;
		this.Ex_New = Ex_New;

	} 
}
