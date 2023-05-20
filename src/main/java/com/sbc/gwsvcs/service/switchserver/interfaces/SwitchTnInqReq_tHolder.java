package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTnInqReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchTnInqReq_t value;

	public SwitchTnInqReq_tHolder () {
	}
	public SwitchTnInqReq_tHolder (SwitchTnInqReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_tHelper.type();
	} 
}
