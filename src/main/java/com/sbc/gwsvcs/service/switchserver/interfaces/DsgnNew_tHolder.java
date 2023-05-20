package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class DsgnNew_tHolder implements org.omg.CORBA.portable.Streamable { 
	public DsgnNew_t value;

	public DsgnNew_tHolder () {
	}
	public DsgnNew_tHolder (DsgnNew_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_tHelper.type();
	} 
}
