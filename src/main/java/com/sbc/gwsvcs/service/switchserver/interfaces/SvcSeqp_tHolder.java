package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SvcSeqp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public SvcSeqp_t value;

	public SvcSeqp_tHolder () {
	}
	public SvcSeqp_tHolder (SvcSeqp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tHelper.type();
	} 
}
