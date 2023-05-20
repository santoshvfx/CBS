package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchAssignableTnReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchAssignableTnReq_t value;

	public SwitchAssignableTnReq_tHolder () {
	}
	public SwitchAssignableTnReq_tHolder (SwitchAssignableTnReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnReq_tHelper.type();
	} 
}
