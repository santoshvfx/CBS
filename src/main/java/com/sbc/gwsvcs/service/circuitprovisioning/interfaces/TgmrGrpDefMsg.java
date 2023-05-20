// $Id: TgmrGrpDefMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmrGrpDefMsg implements MMarshalObject { 
	public TgmrGrpDef value;

	public TgmrGrpDefMsg () {
	}
	public TgmrGrpDefMsg (TgmrGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDef();
value.s = new String ();
value.fmt = new String ();
value.groupid = new String ();
value.idtype = new String ();
value.clonbr = new String ();
value.date = new String ();
value.act = new String ();
value.pcapcz = new String ();
value.drtype = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmrGrpDef (ms, tag); 
	}
	static public TgmrGrpDef decodeTgmrGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmrGrpDef value = create();
		ms.startStruct (tag, false);
		value.s = ms.decodeOctetString (2, "s");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.groupid = ms.decodeOctetString (46, "groupid");
		value.idtype = ms.decodeOctetString (6, "idtype");
		value.clonbr = ms.decodeOctetString (13, "clonbr");
		value.date = ms.decodeOctetString (7, "date");
		value.act = ms.decodeOctetString (3, "act");
		value.pcapcz = ms.decodeOctetString (37, "pcapcz");
		value.drtype = ms.decodeOctetString (6, "drtype");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmrGrpDef (ms, value, tag); 
	}
	static public void encodeTgmrGrpDef (MMarshalStrategy ms, TgmrGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.s, 2, "s");
	ms.encode (value.fmt, 2, "fmt");
ms.encode (value.groupid, 46, "groupid");
ms.encode (value.idtype, 6, "idtype");
ms.encode (value.clonbr, 13, "clonbr");
ms.encode (value.date, 7, "date");
ms.encode (value.act, 3, "act");
ms.encode (value.pcapcz, 37, "pcapcz");
ms.encode (value.drtype, 6, "drtype");
ms.endStruct (tag, true); 
}
public static TgmrGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgmrGrpDef (ms, "TgmrGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDefHelper.type(); 
}
public static byte [] toOctet (TgmrGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgmrGrpDef (ms, val, "TgmrGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
