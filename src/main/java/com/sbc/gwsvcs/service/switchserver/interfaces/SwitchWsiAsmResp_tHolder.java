package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiAsmResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchWsiAsmResp_t value;

	public SwitchWsiAsmResp_tHolder () {
	}
	public SwitchWsiAsmResp_tHolder (SwitchWsiAsmResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmResp_tHelper.type();
	} 
}
