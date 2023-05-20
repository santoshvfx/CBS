package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SvcSattrOld_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SvcSattrOld_t value;

	public SvcSattrOld_tHolder () {
	}
	public SvcSattrOld_tHolder (SvcSattrOld_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tHelper.type();
	} 
}
