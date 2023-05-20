package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class QueryCktResp_tHolder implements org.omg.CORBA.portable.Streamable { 
	public QueryCktResp_t value;

	public QueryCktResp_tHolder () {
	}
	public QueryCktResp_tHolder (QueryCktResp_t initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_tHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_tHelper.write (o, value); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_tHelper.type();
	} 
}
