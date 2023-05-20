package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqOrdEx_tHolder implements org.omg.CORBA.portable.Streamable { 
	public InqOrdEx_t value;

	public InqOrdEx_tHolder () {
	}
	public InqOrdEx_tHolder (InqOrdEx_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tHelper.type();
	} 
}
