package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ExOldNew_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ExOldNew_t value;

	public ExOldNew_tHolder () {
	}
	public ExOldNew_tHolder (ExOldNew_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tHelper.type();
	} 
}
