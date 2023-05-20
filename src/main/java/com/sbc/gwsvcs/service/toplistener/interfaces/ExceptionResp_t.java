package com.sbc.gwsvcs.service.toplistener.interfaces;
import com.sbc.vicunalite.api.*;

final public class ExceptionResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.toplistener.interfaces.Header_t Header;
	public com.sbc.gwsvcs.service.toplistener.interfaces.GWException_t OutExcp;

	public ExceptionResp_t () {
	}
	public ExceptionResp_t (com.sbc.gwsvcs.service.toplistener.interfaces.Header_t Header, com.sbc.gwsvcs.service.toplistener.interfaces.GWException_t OutExcp) { 
		this.Header = Header;
		this.OutExcp = OutExcp;

	} 
}
