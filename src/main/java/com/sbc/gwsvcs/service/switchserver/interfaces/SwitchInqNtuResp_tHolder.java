package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqNtuResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqNtuResp_t value;

	public SwitchInqNtuResp_tHolder () {
	}
	public SwitchInqNtuResp_tHolder (SwitchInqNtuResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_tHelper.type();
	} 
}
