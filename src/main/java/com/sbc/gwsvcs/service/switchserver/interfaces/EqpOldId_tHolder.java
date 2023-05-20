package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpOldId_tHolder implements org.omg.CORBA.portable.Streamable { 
	public EqpOldId_t value;

	public EqpOldId_tHolder () {
	}
	public EqpOldId_tHolder (EqpOldId_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tHelper.type();
	} 
}
