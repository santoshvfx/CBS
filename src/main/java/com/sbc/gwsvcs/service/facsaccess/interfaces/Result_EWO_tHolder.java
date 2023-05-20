package com.sbc.gwsvcs.service.facsaccess.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class Result_EWO_tHolder implements org.omg.CORBA.portable.Streamable { 
	public Result_EWO_t value;

	public Result_EWO_tHolder () {
	}
	public Result_EWO_tHolder (Result_EWO_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.facsaccess.interfaces.Result_EWO_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Result_EWO_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Result_EWO_tHelper.type();
	} 
}
