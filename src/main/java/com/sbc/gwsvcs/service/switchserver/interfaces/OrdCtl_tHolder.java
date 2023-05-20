package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class OrdCtl_tHolder implements org.omg.CORBA.portable.Streamable { 
	public OrdCtl_t value;

	public OrdCtl_tHolder () {
	}
	public OrdCtl_tHolder (OrdCtl_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tHelper.type();
	} 
}
