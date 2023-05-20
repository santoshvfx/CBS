package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiPrvReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchWsiPrvReq_t value;

	public SwitchWsiPrvReq_tHolder () {
	}
	public SwitchWsiPrvReq_tHolder (SwitchWsiPrvReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_tHelper.type();
	} 
}
