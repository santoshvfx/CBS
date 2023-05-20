package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchRtnTnResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchRtnTnResp_t value;

	public SwitchRtnTnResp_tHolder () {
	}
	public SwitchRtnTnResp_tHolder (SwitchRtnTnResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnResp_tHelper.type();
	} 
}
