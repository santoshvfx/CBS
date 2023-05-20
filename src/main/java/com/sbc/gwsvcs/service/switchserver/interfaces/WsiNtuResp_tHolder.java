package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiNtuResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public WsiNtuResp_t value;

	public WsiNtuResp_tHolder () {
	}
	public WsiNtuResp_tHolder (WsiNtuResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_tHelper.type();
	} 
}
