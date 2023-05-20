package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqOrdReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public InqOrdReq_t value;

	public InqOrdReq_tHolder () {
	}
	public InqOrdReq_tHolder (InqOrdReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tHelper.type();
	} 
}
