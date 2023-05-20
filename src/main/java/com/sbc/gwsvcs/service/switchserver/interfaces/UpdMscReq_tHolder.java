package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class UpdMscReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public UpdMscReq_t value;

	public UpdMscReq_tHolder () {
	}
	public UpdMscReq_tHolder (UpdMscReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_tHelper.type();
	} 
}
