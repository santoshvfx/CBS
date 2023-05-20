// $Id: TgmsGrpDefMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmsGrpDefMsg implements MMarshalObject { 
	public TgmsGrpDef value;

	public TgmsGrpDefMsg () {
	}
	public TgmsGrpDefMsg (TgmsGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDef();
value.s = new String ();
value.trk = new String ();
value.cac = new String ();
value.owner = new String ();
value.stat = new String ();
value.clonbr = new String ();
value.dd = new String ();
value.act = new String ();
value.pdtk = new String ();
value.pdgac = new String ();
value.pddd = new String ();
value.pdact = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmsGrpDef (ms, tag); 
	}
	static public TgmsGrpDef decodeTgmsGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmsGrpDef value = create();
		ms.startStruct (tag, false);
		value.s = ms.decodeOctetString (2, "s");
		value.trk = ms.decodeOctetString (5, "trk");
		value.cac = ms.decodeOctetString (9, "cac");
		value.owner = ms.decodeOctetString (5, "owner");
		value.stat = ms.decodeOctetString (3, "stat");
		value.clonbr = ms.decodeOctetString (14, "clonbr");
		value.dd = ms.decodeOctetString (9, "dd");
		value.act = ms.decodeOctetString (3, "act");
		value.pdtk = ms.decodeOctetString (5, "pdtk");
		value.pdgac = ms.decodeOctetString (9, "pdgac");
		value.pddd = ms.decodeOctetString (9, "pddd");
		value.pdact = ms.decodeOctetString (2, "pdact");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmsGrpDef (ms, value, tag); 
	}
	static public void encodeTgmsGrpDef (MMarshalStrategy ms, TgmsGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.s, 2, "s");
	ms.encode (value.trk, 5, "trk");
ms.encode (value.cac, 9, "cac");
ms.encode (value.owner, 5, "owner");
ms.encode (value.stat, 3, "stat");
ms.encode (value.clonbr, 14, "clonbr");
ms.encode (value.dd, 9, "dd");
ms.encode (value.act, 3, "act");
ms.encode (value.pdtk, 5, "pdtk");
ms.encode (value.pdgac, 9, "pdgac");
ms.encode (value.pddd, 9, "pddd");
ms.encode (value.pdact, 2, "pdact");
ms.endStruct (tag, true); 
}
public static TgmsGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgmsGrpDef (ms, "TgmsGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDefHelper.type(); 
}
public static byte [] toOctet (TgmsGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgmsGrpDef (ms, val, "TgmsGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
