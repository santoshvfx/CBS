package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchRptNtuResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchRptNtuResp_t value;

	public SwitchRptNtuResp_tHolder () {
	}
	public SwitchRptNtuResp_tHolder (SwitchRptNtuResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuResp_tHelper.type();
	} 
}
