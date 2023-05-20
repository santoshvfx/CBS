package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiTnlReq_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SwitchWsiTnlReq_t value;

	public SwitchWsiTnlReq_tHolder () {
	}
	public SwitchWsiTnlReq_tHolder (SwitchWsiTnlReq_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlReq_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlReq_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlReq_tHelper.type();
	} 
}
