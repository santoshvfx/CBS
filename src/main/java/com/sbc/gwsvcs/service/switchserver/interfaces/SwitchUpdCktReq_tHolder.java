package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdCktReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchUpdCktReq_t value;

	public SwitchUpdCktReq_tHolder () {
	}
	public SwitchUpdCktReq_tHolder (SwitchUpdCktReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktReq_tHelper.type();
	} 
}
