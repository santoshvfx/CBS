package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchChgAsgResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchChgAsgResp_t value;

	public SwitchChgAsgResp_tHolder () {
	}
	public SwitchChgAsgResp_tHolder (SwitchChgAsgResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgResp_tHelper.type();
	} 
}
