package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqCktResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqCktResp_t value;

	public SwitchInqCktResp_tHolder () {
	}
	public SwitchInqCktResp_tHolder (SwitchInqCktResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_tHelper.type();
	} 
}
