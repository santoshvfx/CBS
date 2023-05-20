package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchQueryCktResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchQueryCktResp_t value;

	public SwitchQueryCktResp_tHolder () {
	}
	public SwitchQueryCktResp_tHolder (SwitchQueryCktResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktResp_tHelper.type();
	} 
}
