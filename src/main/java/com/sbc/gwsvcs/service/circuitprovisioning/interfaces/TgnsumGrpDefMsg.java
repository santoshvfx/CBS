// $Id: TgnsumGrpDefMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgnsumGrpDefMsg implements MMarshalObject { 
	public TgnsumGrpDef value;

	public TgnsumGrpDefMsg () {
	}
	public TgnsumGrpDefMsg (TgnsumGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef();
value.s = new String ();
value.tgn = new String ();
value.r = new String ();
value.gac = new String ();
value.end = new String ();
value.desc = new String ();
value.location = new String ();
value.cllitrkname = new String ();
value.remarks = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgnsumGrpDef (ms, tag); 
	}
	static public TgnsumGrpDef decodeTgnsumGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgnsumGrpDef value = create();
		ms.startStruct (tag, false);
		value.s = ms.decodeOctetString (2, "s");
		value.tgn = ms.decodeOctetString (5, "tgn");
		value.r = ms.decodeOctetString (2, "r");
		value.gac = ms.decodeOctetString (9, "gac");
		value.end = ms.decodeOctetString (2, "end");
		value.desc = ms.decodeOctetString (14, "desc");
		value.location = ms.decodeOctetString (12, "location");
		value.cllitrkname = ms.decodeOctetString (17, "cllitrkname");
		value.remarks = ms.decodeOctetString (15, "remarks");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgnsumGrpDef (ms, value, tag); 
	}
	static public void encodeTgnsumGrpDef (MMarshalStrategy ms, TgnsumGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.s, 2, "s");
	ms.encode (value.tgn, 5, "tgn");
ms.encode (value.r, 2, "r");
ms.encode (value.gac, 9, "gac");
ms.encode (value.end, 2, "end");
ms.encode (value.desc, 14, "desc");
ms.encode (value.location, 12, "location");
ms.encode (value.cllitrkname, 17, "cllitrkname");
ms.encode (value.remarks, 15, "remarks");
ms.endStruct (tag, true); 
}
public static TgnsumGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgnsumGrpDef (ms, "TgnsumGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDefHelper.type(); 
}
public static byte [] toOctet (TgnsumGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgnsumGrpDef (ms, val, "TgnsumGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
