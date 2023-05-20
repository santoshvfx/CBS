package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchSelTnResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchSelTnResp_t value;

	public SwitchSelTnResp_tHolder () {
	}
	public SwitchSelTnResp_tHolder (SwitchSelTnResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_tHelper.type();
	} 
}
