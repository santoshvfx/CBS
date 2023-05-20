package com.sbc.gwsvcs.service.toplistener.interfaces;
import com.sbc.vicunalite.api.*;

final public class TOPSendToHostResp_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.toplistener.interfaces.Header_t Header;
	public String FCIFData;

	public TOPSendToHostResp_t () {
	}
	public TOPSendToHostResp_t (com.sbc.gwsvcs.service.toplistener.interfaces.Header_t Header, String FCIFData) { 
		this.Header = Header;
		this.FCIFData = FCIFData;

	} 
}
