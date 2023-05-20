package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ComnUattr_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ComnUattr_t value;

	public ComnUattr_tHolder () {
	}
	public ComnUattr_tHolder (ComnUattr_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.type();
	} 
}
