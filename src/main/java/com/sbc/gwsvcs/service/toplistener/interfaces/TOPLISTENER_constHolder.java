package com.sbc.gwsvcs.service.toplistener.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOPLISTENER_constHolder implements org.omg.CORBA.portable.Streamable { 
	public TOPLISTENER_const value;

	public TOPLISTENER_constHolder () { }
	public TOPLISTENER_constHolder (TOPLISTENER_const initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = TOPLISTENER_constHelper.read (i); 
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		TOPLISTENER_constHelper.write (o, value); 
	}
	public TypeCode _type () { return TOPLISTENER_constHelper.type(); } 
}
