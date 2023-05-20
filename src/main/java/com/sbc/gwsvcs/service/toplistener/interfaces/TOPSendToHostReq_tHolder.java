package com.sbc.gwsvcs.service.toplistener.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOPSendToHostReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TOPSendToHostReq_t value;

	public TOPSendToHostReq_tHolder () {
	}
	public TOPSendToHostReq_tHolder (TOPSendToHostReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_tHelper.type();
	} 
}
