package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqAsmResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqAsmResp_t value;

	public SwitchInqAsmResp_tHolder () {
	}
	public SwitchInqAsmResp_tHolder (SwitchInqAsmResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmResp_tHelper.type();
	} 
}
