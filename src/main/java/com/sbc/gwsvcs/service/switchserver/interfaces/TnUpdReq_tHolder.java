package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnUpdReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public TnUpdReq_t value;

	public TnUpdReq_tHolder () {
	}
	public TnUpdReq_tHolder (TnUpdReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_tHelper.type();
	} 
}
