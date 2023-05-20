// $Id: ZrtdsoGrpDefHolder.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrtdsoGrpDefHolder implements org.omg.CORBA.portable.Streamable { 
	public ZrtdsoGrpDef value;

	public ZrtdsoGrpDefHolder () {
	}
	public ZrtdsoGrpDefHolder (ZrtdsoGrpDef initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDefHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDefHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDefHelper.write (o, value); 
	}
}
