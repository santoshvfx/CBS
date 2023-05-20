// $Id: ZrgrpGrpDef1Holder.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrgrpGrpDef1Holder implements org.omg.CORBA.portable.Streamable { 
	public ZrgrpGrpDef1 value;

	public ZrgrpGrpDef1Holder () {
	}
	public ZrgrpGrpDef1Holder (ZrgrpGrpDef1 initial) { 
		value = initial; 
	}
	public void _read (org.omg.CORBA.portable.InputStream i) { 
		value = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Helper.read (i); 
	}
	public TypeCode _type () {
		return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Helper.type();
	}
	public void _write (org.omg.CORBA.portable.OutputStream o) { 
		com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Helper.write (o, value); 
	}
}
