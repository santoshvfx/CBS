package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class OrdCarRec_tHolder implements org.omg.CORBA.portable.Streamable { 
	public OrdCarRec_t value;

	public OrdCarRec_tHolder () {
	}
	public OrdCarRec_tHolder (OrdCarRec_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tHelper.type();
	} 
}
