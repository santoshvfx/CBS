// $Id: ZretsiOutputHolder.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZretsiOutputHolder implements org.omg.CORBA.portable.Streamable { 
	public ZretsiOutput value;

	public ZretsiOutputHolder () {
	}
	public ZretsiOutputHolder (ZretsiOutput initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiOutputHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiOutputHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiOutputHelper.write (o, value); 
	}
}
