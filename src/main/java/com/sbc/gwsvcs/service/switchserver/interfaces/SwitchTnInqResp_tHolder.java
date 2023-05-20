package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTnInqResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchTnInqResp_t value;

	public SwitchTnInqResp_tHolder () {
	}
	public SwitchTnInqResp_tHolder (SwitchTnInqResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqResp_tHelper.type();
	} 
}
