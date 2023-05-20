package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqOrdResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqOrdResp_t value;

	public SwitchInqOrdResp_tHolder () {
	}
	public SwitchInqOrdResp_tHolder (SwitchInqOrdResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdResp_tHelper.type();
	} 
}
