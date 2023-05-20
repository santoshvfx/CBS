package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class ChgAsgReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public ChgAsgReq_t value;

	public ChgAsgReq_tHolder () {
	}
	public ChgAsgReq_tHolder (ChgAsgReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_tHelper.type();
	} 
}
