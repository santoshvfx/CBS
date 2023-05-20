package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqNtuReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqNtuReq_t value;

	public SwitchInqNtuReq_tHolder () {
	}
	public SwitchInqNtuReq_tHolder (SwitchInqNtuReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_tHelper.type();
	} 
}
