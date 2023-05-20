package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class NpaPrfx_tHolder implements org.omg.CORBA.portable.Streamable { 
	public NpaPrfx_t value;

	public NpaPrfx_tHolder () {
	}
	public NpaPrfx_tHolder (NpaPrfx_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tHelper.type();
	} 
}
