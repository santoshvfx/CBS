package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchPreMctResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchPreMctResp_t value;

	public SwitchPreMctResp_tHolder () {
	}
	public SwitchPreMctResp_tHolder (SwitchPreMctResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_tHelper.type();
	} 
}
