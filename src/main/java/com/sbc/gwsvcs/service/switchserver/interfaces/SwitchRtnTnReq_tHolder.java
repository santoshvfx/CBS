package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchRtnTnReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchRtnTnReq_t value;

	public SwitchRtnTnReq_tHolder () {
	}
	public SwitchRtnTnReq_tHolder (SwitchRtnTnReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_tHelper.type();
	} 
}
