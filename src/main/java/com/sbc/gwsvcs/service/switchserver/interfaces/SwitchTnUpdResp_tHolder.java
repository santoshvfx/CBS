package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTnUpdResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchTnUpdResp_t value;

	public SwitchTnUpdResp_tHolder () {
	}
	public SwitchTnUpdResp_tHolder (SwitchTnUpdResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdResp_tHelper.type();
	} 
}
