package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqNtuReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public InqNtuReq_t value;

	public InqNtuReq_tHolder () {
	}
	public InqNtuReq_tHolder (InqNtuReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_tHelper.type();
	} 
}
