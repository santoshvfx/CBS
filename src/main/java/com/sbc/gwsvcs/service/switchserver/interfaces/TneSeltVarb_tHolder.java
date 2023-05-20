package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class TneSeltVarb_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TneSeltVarb_t value;

	public TneSeltVarb_tHolder () {
	}
	public TneSeltVarb_tHolder (TneSeltVarb_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_tHelper.type();
	} 
}
