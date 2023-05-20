// $Id: TastgnGrpDefMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TastgnGrpDefMsg implements MMarshalObject { 
	public TastgnGrpDef value;

	public TastgnGrpDefMsg () {
	}
	public TastgnGrpDefMsg (TastgnGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDef();
value.tgn = new String ();
value.starttmn = new String ();
value.starttrunkno = new String ();
value.endtrunk = new String ();
value.cmd = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTastgnGrpDef (ms, tag); 
	}
	static public TastgnGrpDef decodeTastgnGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TastgnGrpDef value = create();
		ms.startStruct (tag, false);
		value.tgn = ms.decodeOctetString (5, "tgn");
		value.starttmn = ms.decodeOctetString (5, "starttmn");
		value.starttrunkno = ms.decodeOctetString (5, "starttrunkno");
		value.endtrunk = ms.decodeOctetString (5, "endtrunk");
		value.cmd = ms.decodeOctetString (9, "cmd");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTastgnGrpDef (ms, value, tag); 
	}
	static public void encodeTastgnGrpDef (MMarshalStrategy ms, TastgnGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.tgn, 5, "tgn");
	ms.encode (value.starttmn, 5, "starttmn");
ms.encode (value.starttrunkno, 5, "starttrunkno");
ms.encode (value.endtrunk, 5, "endtrunk");
ms.encode (value.cmd, 9, "cmd");
ms.endStruct (tag, true); 
}
public static TastgnGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTastgnGrpDef (ms, "TastgnGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDefHelper.type(); 
}
public static byte [] toOctet (TastgnGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTastgnGrpDef (ms, val, "TastgnGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
