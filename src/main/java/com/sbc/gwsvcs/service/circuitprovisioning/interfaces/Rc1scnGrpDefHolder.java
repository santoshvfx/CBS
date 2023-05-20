// $Id: Rc1scnGrpDefHolder.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Rc1scnGrpDefHolder implements org.omg.CORBA.portable.Streamable { 
	public Rc1scnGrpDef value;

	public Rc1scnGrpDefHolder () {
	}
	public Rc1scnGrpDefHolder (Rc1scnGrpDef initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDefHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDefHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDefHelper.write (o, value); 
	}
}
