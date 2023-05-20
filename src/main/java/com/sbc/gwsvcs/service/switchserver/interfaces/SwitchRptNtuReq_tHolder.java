package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchRptNtuReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchRptNtuReq_t value;

	public SwitchRptNtuReq_tHolder () {
	}
	public SwitchRptNtuReq_tHolder (SwitchRptNtuReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuReq_tHelper.type();
	} 
}
