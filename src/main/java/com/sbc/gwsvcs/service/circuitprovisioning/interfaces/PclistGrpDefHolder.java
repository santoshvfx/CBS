// $Id: PclistGrpDefHolder.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PclistGrpDefHolder implements org.omg.CORBA.portable.Streamable { 
	public PclistGrpDef value;

	public PclistGrpDefHolder () {
	}
	public PclistGrpDefHolder (PclistGrpDef initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDefHelper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDefHelper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDefHelper.write (o, value); 
	}
}
