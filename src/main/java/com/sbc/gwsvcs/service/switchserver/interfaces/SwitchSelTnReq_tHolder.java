package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchSelTnReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchSelTnReq_t value;

	public SwitchSelTnReq_tHolder () {
	}
	public SwitchSelTnReq_tHolder (SwitchSelTnReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_tHelper.type();
	} 
}
