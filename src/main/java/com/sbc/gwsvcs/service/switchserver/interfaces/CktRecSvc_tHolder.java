package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktRecSvc_tHolder implements org.omg.CORBA.portable.Streamable { 
	public CktRecSvc_t value;

	public CktRecSvc_tHolder () {
	}
	public CktRecSvc_tHolder (CktRecSvc_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tHelper.type();
	} 
}
