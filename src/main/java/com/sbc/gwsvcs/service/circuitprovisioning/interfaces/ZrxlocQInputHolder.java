// $Id: ZrxlocQInputHolder.java,v 1.1 2002/09/29 04:10:31 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrxlocQInputHolder implements org.omg.CORBA.portable.Streamable { 
	public ZrxlocQInput value;

	public ZrxlocQInputHolder () {
	}
	public ZrxlocQInputHolder (ZrxlocQInput initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocQInputHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocQInputHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocQInputHelper.write (o, value); 
	}
}
