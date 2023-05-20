package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiNtuResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchWsiNtuResp_t value;

	public SwitchWsiNtuResp_tHolder () {
	}
	public SwitchWsiNtuResp_tHolder (SwitchWsiNtuResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuResp_tHelper.type();
	} 
}
