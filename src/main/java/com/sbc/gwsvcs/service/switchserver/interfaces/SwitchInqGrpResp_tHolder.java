package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqGrpResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchInqGrpResp_t value;

	public SwitchInqGrpResp_tHolder () {
	}
	public SwitchInqGrpResp_tHolder (SwitchInqGrpResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpResp_tHelper.type();
	} 
}
