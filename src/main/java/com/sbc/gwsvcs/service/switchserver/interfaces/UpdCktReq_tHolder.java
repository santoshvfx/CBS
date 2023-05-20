package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class UpdCktReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public UpdCktReq_t value;

	public UpdCktReq_tHolder () {
	}
	public UpdCktReq_tHolder (UpdCktReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_tHelper.type();
	} 
}
