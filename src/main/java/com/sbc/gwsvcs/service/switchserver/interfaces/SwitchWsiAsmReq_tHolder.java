package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiAsmReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchWsiAsmReq_t value;

	public SwitchWsiAsmReq_tHolder () {
	}
	public SwitchWsiAsmReq_tHolder (SwitchWsiAsmReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmReq_tHelper.type();
	} 
}
