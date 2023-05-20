package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktRecCtl_tHolder implements org.omg.CORBA.portable.Streamable { 
	public CktRecCtl_t value;

	public CktRecCtl_tHolder () {
	}
	public CktRecCtl_tHolder (CktRecCtl_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tHelper.type();
	} 
}
