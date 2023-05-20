package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktRecSvc1_tHolder implements org.omg.CORBA.portable.Streamable { 
	public CktRecSvc1_t value;

	public CktRecSvc1_tHolder () {
	}
	public CktRecSvc1_tHolder (CktRecSvc1_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tHelper.type();
	} 
}
