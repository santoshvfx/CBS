package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiAsmResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public WsiAsmResp_t value;

	public WsiAsmResp_tHolder () {
	}
	public WsiAsmResp_tHolder (WsiAsmResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_tHelper.type();
	} 
}
