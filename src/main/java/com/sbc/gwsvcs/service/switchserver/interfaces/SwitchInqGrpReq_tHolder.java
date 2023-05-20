package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqGrpReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqGrpReq_t value;

	public SwitchInqGrpReq_tHolder () {
	}
	public SwitchInqGrpReq_tHolder (SwitchInqGrpReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpReq_tHelper.type();
	} 
}
