package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchAssignableTnResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchAssignableTnResp_t value;

	public SwitchAssignableTnResp_tHolder () {
	}
	public SwitchAssignableTnResp_tHolder (SwitchAssignableTnResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnResp_tHelper.type();
	} 
}
