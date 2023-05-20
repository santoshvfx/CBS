package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqOrdResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public InqOrdResp_t value;

	public InqOrdResp_tHolder () {
	}
	public InqOrdResp_tHolder (InqOrdResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_tHelper.type();
	} 
}
