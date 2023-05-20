package com.sbc.gwsvcs.service.toplistener.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOPSendToHostAckResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TOPSendToHostAckResp_t value;

	public TOPSendToHostAckResp_tHolder () {
	}
	public TOPSendToHostAckResp_tHolder (TOPSendToHostAckResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_tHelper.type();
	} 
}
