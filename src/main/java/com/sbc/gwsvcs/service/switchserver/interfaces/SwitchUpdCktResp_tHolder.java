package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdCktResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchUpdCktResp_t value;

	public SwitchUpdCktResp_tHolder () {
	}
	public SwitchUpdCktResp_tHolder (SwitchUpdCktResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktResp_tHelper.type();
	} 
}
