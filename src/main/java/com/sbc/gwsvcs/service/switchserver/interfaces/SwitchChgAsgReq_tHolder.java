package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchChgAsgReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchChgAsgReq_t value;

	public SwitchChgAsgReq_tHolder () {
	}
	public SwitchChgAsgReq_tHolder (SwitchChgAsgReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgReq_tHelper.type();
	} 
}
