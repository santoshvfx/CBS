package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class OrdAcl_tHolder implements org.omg.CORBA.portable.Streamable { 
	public OrdAcl_t value;

	public OrdAcl_tHolder () {
	}
	public OrdAcl_tHolder (OrdAcl_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tHelper.type();
	} 
}
