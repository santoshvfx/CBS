package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ENGMNU_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ENGMNU_t value;

	public ENGMNU_tHolder () {
	}
	public ENGMNU_tHolder (ENGMNU_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_tHelper.type();
	} 
}
