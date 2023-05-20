// $Id: Rc1scnGrpDefMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Rc1scnGrpDefMsg implements MMarshalObject { 
	public Rc1scnGrpDef value;

	public Rc1scnGrpDefMsg () {
	}
	public Rc1scnGrpDefMsg (Rc1scnGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDef();
value.select = new String ();
value.ac = new String ();
value.fmt = new String ();
value.cktid = new String ();
value.type = new String ();
value.stat = new String ();
value.date = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRc1scnGrpDef (ms, tag); 
	}
	static public Rc1scnGrpDef decodeRc1scnGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Rc1scnGrpDef value = create();
		ms.startStruct (tag, false);
		value.select = ms.decodeOctetString (2, "select");
		value.ac = ms.decodeOctetString (9, "ac");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.cktid = ms.decodeOctetString (46, "cktid");
		value.type = ms.decodeOctetString (6, "type");
		value.stat = ms.decodeOctetString (3, "stat");
		value.date = ms.decodeOctetString (9, "date");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRc1scnGrpDef (ms, value, tag); 
	}
	static public void encodeRc1scnGrpDef (MMarshalStrategy ms, Rc1scnGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.select, 2, "select");
	ms.encode (value.ac, 9, "ac");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.cktid, 46, "cktid");
ms.encode (value.type, 6, "type");
ms.encode (value.stat, 3, "stat");
ms.encode (value.date, 9, "date");
ms.endStruct (tag, true); 
}
public static Rc1scnGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRc1scnGrpDef (ms, "Rc1scnGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDefHelper.type(); 
}
public static byte [] toOctet (Rc1scnGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRc1scnGrpDef (ms, val, "Rc1scnGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
