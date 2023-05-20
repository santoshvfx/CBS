package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktCktattr_tHolder implements org.omg.CORBA.portable.Streamable { 
	public CktCktattr_t value;

	public CktCktattr_tHolder () {
	}
	public CktCktattr_tHolder (CktCktattr_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tHelper.type();
	} 
}
