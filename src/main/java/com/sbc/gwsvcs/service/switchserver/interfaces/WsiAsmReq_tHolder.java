package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiAsmReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public WsiAsmReq_t value;

	public WsiAsmReq_tHolder () {
	}
	public WsiAsmReq_tHolder (WsiAsmReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_tHelper.type();
	} 
}
