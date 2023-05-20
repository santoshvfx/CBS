package com.sbc.gwsvcs.service.toplistener.interfaces;
import com.sbc.vicunalite.api.*;

final public class TOPSendToHostReq_t implements org.omg.CORBA.portable.IDLEntity { 
	public com.sbc.gwsvcs.service.toplistener.interfaces.Header_t Header;
	public String FCIFData;
	public String DtnName;

	public TOPSendToHostReq_t () {
	}
	public TOPSendToHostReq_t (com.sbc.gwsvcs.service.toplistener.interfaces.Header_t Header, String FCIFData, String DtnName) { 
		this.Header = Header;
		this.FCIFData = FCIFData;
		this.DtnName = DtnName;

	} 
}
