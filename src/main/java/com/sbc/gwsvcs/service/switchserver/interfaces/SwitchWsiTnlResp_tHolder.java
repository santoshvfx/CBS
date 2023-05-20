package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiTnlResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchWsiTnlResp_t value;

	public SwitchWsiTnlResp_tHolder () {
	}
	public SwitchWsiTnlResp_tHolder (SwitchWsiTnlResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlResp_tHelper.type();
	} 
}
