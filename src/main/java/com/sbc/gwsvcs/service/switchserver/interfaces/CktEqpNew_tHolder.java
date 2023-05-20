package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktEqpNew_tHolder implements org.omg.CORBA.portable.Streamable { 
	public CktEqpNew_t value;

	public CktEqpNew_tHolder () {
	}
	public CktEqpNew_tHolder (CktEqpNew_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_tHelper.type();
	} 
}
