package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchSelTneReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchSelTneReq_t value;

	public SwitchSelTneReq_tHolder () {
	}
	public SwitchSelTneReq_tHolder (SwitchSelTneReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_tHelper.type();
	} 
}
