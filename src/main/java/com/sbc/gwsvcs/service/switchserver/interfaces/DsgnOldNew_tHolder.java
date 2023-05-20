package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class DsgnOldNew_tHolder implements org.omg.CORBA.portable.Streamable { 
	public DsgnOldNew_t value;

	public DsgnOldNew_tHolder () {
	}
	public DsgnOldNew_tHolder (DsgnOldNew_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tHelper.type();
	} 
}
