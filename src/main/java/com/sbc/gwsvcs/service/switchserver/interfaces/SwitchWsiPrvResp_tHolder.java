package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiPrvResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchWsiPrvResp_t value;

	public SwitchWsiPrvResp_tHolder () {
	}
	public SwitchWsiPrvResp_tHolder (SwitchWsiPrvResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_tHelper.type();
	} 
}
