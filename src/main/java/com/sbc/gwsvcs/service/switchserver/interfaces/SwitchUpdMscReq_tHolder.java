package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdMscReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchUpdMscReq_t value;

	public SwitchUpdMscReq_tHolder () {
	}
	public SwitchUpdMscReq_tHolder (SwitchUpdMscReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdMscReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdMscReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdMscReq_tHelper.type();
	} 
}
