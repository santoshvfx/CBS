package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTdoReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchTdoReq_t value;

	public SwitchTdoReq_tHolder () {
	}
	public SwitchTdoReq_tHolder (SwitchTdoReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.type();
	} 
}
