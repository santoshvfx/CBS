package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class UpdAsmReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public UpdAsmReq_t value;

	public UpdAsmReq_tHolder () {
	}
	public UpdAsmReq_tHolder (UpdAsmReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_tHelper.type();
	} 
}
