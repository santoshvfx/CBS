package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpOld_tHolder implements org.omg.CORBA.portable.Streamable { 
	public EqpOld_t value;

	public EqpOld_tHolder () {
	}
	public EqpOld_tHolder (EqpOld_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_tHelper.type();
	} 
}
