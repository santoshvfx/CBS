package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqOrdReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqOrdReq_t value;

	public SwitchInqOrdReq_tHolder () {
	}
	public SwitchInqOrdReq_tHolder (SwitchInqOrdReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_tHelper.type();
	} 
}
