package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class RtnTnReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public RtnTnReq_t value;

	public RtnTnReq_tHolder () {
	}
	public RtnTnReq_tHolder (RtnTnReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_tHelper.type();
	} 
}
