// $Id: DriQInputHolder.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DriQInputHolder implements org.omg.CORBA.portable.Streamable { 
	public DriQInput value;

	public DriQInputHolder () {
	}
	public DriQInputHolder (DriQInput initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInputHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInputHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInputHelper.write (o, value); 
	}
}
