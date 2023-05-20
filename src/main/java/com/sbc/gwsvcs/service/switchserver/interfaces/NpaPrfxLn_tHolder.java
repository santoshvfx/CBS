package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class NpaPrfxLn_tHolder implements org.omg.CORBA.portable.Streamable { 
	public NpaPrfxLn_t value;

	public NpaPrfxLn_tHolder () {
	}
	public NpaPrfxLn_tHolder (NpaPrfxLn_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tHelper.type();
	} 
}
