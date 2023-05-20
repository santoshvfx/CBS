package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqAsmReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqAsmReq_t value;

	public SwitchInqAsmReq_tHolder () {
	}
	public SwitchInqAsmReq_tHolder (SwitchInqAsmReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmReq_tHelper.type();
	} 
}
