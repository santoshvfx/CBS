package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiNtuReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public WsiNtuReq_t value;

	public WsiNtuReq_tHolder () {
	}
	public WsiNtuReq_tHolder (WsiNtuReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_tHelper.type();
	} 
}
