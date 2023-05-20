package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdAsmReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchUpdAsmReq_t value;

	public SwitchUpdAsmReq_tHolder () {
	}
	public SwitchUpdAsmReq_tHolder (SwitchUpdAsmReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmReq_tHelper.type();
	} 
}
