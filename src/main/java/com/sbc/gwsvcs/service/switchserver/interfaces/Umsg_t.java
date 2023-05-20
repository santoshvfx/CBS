package com.sbc.gwsvcs.service.switchserver.interfaces;


final public class Umsg_t implements org.omg.CORBA.portable.IDLEntity { 
	public String MSG_NBR;
	public String MSG_TYPE;
	public char[] MSG_TX;
	public com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex;

	public Umsg_t () {
	}
	public Umsg_t (String MSG_NBR, String MSG_TYPE, char[] MSG_TX, com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t Ex) { 
		this.MSG_NBR = MSG_NBR;
		this.MSG_TYPE = MSG_TYPE;
		this.MSG_TX = MSG_TX;
		this.Ex = Ex;

	} 
}
