package com.sbc.gwsvcs.service.toplistener.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOPSendToHostResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TOPSendToHostResp_t value;

	public TOPSendToHostResp_tHolder () {
	}
	public TOPSendToHostResp_tHolder (TOPSendToHostResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_tHelper.type();
	} 
}
