package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdAsmResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchUpdAsmResp_t value;

	public SwitchUpdAsmResp_tHolder () {
	}
	public SwitchUpdAsmResp_tHolder (SwitchUpdAsmResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmResp_tHelper.type();
	} 
}
