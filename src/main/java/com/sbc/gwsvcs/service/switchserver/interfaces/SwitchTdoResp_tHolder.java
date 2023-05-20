package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTdoResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchTdoResp_t value;

	public SwitchTdoResp_tHolder () {
	}
	public SwitchTdoResp_tHolder (SwitchTdoResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_tHelper.type();
	} 
}
