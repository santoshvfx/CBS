package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchPreMctReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchPreMctReq_t value;

	public SwitchPreMctReq_tHolder () {
	}
	public SwitchPreMctReq_tHolder (SwitchPreMctReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_tHelper.type();
	} 
}
