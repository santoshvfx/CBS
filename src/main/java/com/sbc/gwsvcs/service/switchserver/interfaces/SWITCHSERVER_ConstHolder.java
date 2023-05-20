package com.sbc.gwsvcs.service.switchserver.interfaces;

import java.util.*;
import org.omg.CORBA.TypeCode;

public class SWITCHSERVER_ConstHolder implements org.omg.CORBA.portable.Streamable { 
	public SWITCHSERVER_Const value;

	public SWITCHSERVER_ConstHolder () { }
	public SWITCHSERVER_ConstHolder (SWITCHSERVER_Const initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = SWITCHSERVER_ConstHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		SWITCHSERVER_ConstHelper.write (o, value); 
	}
	public TypeCode _type () { return SWITCHSERVER_ConstHelper.type(); } 
}
