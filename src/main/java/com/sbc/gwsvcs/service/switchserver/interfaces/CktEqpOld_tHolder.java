package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktEqpOld_tHolder implements org.omg.CORBA.portable.Streamable { 
	public CktEqpOld_t value;

	public CktEqpOld_tHolder () {
	}
	public CktEqpOld_tHolder (CktEqpOld_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_tHelper.type();
	} 
}
