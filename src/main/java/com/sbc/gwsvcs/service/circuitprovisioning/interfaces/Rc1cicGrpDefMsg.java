// $Id: Rc1cicGrpDefMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Rc1cicGrpDefMsg implements MMarshalObject { 
	public Rc1cicGrpDef value;

	public Rc1cicGrpDefMsg () {
	}
	public Rc1cicGrpDefMsg (Rc1cicGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDef();
value.sel = new String ();
value.bind = new String ();
value.p = new String ();
value.tcic = new String ();
value.access_code = new String ();
value.formatid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRc1cicGrpDef (ms, tag); 
	}
	static public Rc1cicGrpDef decodeRc1cicGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Rc1cicGrpDef value = create();
		ms.startStruct (tag, false);
		value.sel = ms.decodeOctetString (2, "sel");
		value.bind = ms.decodeOctetString (6, "bind");
		value.p = ms.decodeOctetString (2, "p");
		value.tcic = ms.decodeOctetString (4, "tcic");
		value.access_code = ms.decodeOctetString (29, "access_code");
		value.formatid = ms.decodeOctetString (34, "formatid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRc1cicGrpDef (ms, value, tag); 
	}
	static public void encodeRc1cicGrpDef (MMarshalStrategy ms, Rc1cicGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.sel, 2, "sel");
	ms.encode (value.bind, 6, "bind");
ms.encode (value.p, 2, "p");
ms.encode (value.tcic, 4, "tcic");
ms.encode (value.access_code, 29, "access_code");
ms.encode (value.formatid, 34, "formatid");
ms.endStruct (tag, true); 
}
public static Rc1cicGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRc1cicGrpDef (ms, "Rc1cicGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDefHelper.type(); 
}
public static byte [] toOctet (Rc1cicGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRc1cicGrpDef (ms, val, "Rc1cicGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
