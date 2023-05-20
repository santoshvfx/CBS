package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class GoodInfo_tHolder implements org.omg.CORBA.portable.Streamable { 
	public GoodInfo_t value;

	public GoodInfo_tHolder () {
	}
	public GoodInfo_tHolder (GoodInfo_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_tHelper.type();
	} 
}
