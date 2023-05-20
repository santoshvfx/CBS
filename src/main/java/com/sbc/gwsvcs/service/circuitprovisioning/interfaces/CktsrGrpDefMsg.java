// $Id: CktsrGrpDefMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktsrGrpDefMsg implements MMarshalObject { 
	public CktsrGrpDef value;

	public CktsrGrpDefMsg () {
	}
	public CktsrGrpDefMsg (CktsrGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDef();
value.s = new String ();
value.fmt = new String ();
value.cktid = new String ();
value.idtype = new String ();
value.clo = new String ();
value.dd = new String ();
value.act = new String ();
value.puls = new String ();
value.pdac = new String ();
value.loca = new String ();
value.locz = new String ();
value.dr = new String ();
value.piu = new String ();
value.eac = new String ();
value.te = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktsrGrpDef (ms, tag); 
	}
	static public CktsrGrpDef decodeCktsrGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktsrGrpDef value = create();
		ms.startStruct (tag, false);
		value.s = ms.decodeOctetString (2, "s");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.cktid = ms.decodeOctetString (46, "cktid");
		value.idtype = ms.decodeOctetString (6, "idtype");
		value.clo = ms.decodeOctetString (13, "clo");
		value.dd = ms.decodeOctetString (7, "dd");
		value.act = ms.decodeOctetString (3, "act");
		value.puls = ms.decodeOctetString (3, "puls");
		value.pdac = ms.decodeOctetString (9, "pdac");
		value.loca = ms.decodeOctetString (12, "loca");
		value.locz = ms.decodeOctetString (12, "locz");
		value.dr = ms.decodeOctetString (6, "dr");
		value.piu = ms.decodeOctetString (4, "piu");
		value.eac = ms.decodeOctetString (2, "eac");
		value.te = ms.decodeOctetString (2, "te");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktsrGrpDef (ms, value, tag); 
	}
	static public void encodeCktsrGrpDef (MMarshalStrategy ms, CktsrGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.s, 2, "s");
	ms.encode (value.fmt, 2, "fmt");
ms.encode (value.cktid, 46, "cktid");
ms.encode (value.idtype, 6, "idtype");
ms.encode (value.clo, 13, "clo");
ms.encode (value.dd, 7, "dd");
ms.encode (value.act, 3, "act");
ms.encode (value.puls, 3, "puls");
ms.encode (value.pdac, 9, "pdac");
ms.encode (value.loca, 12, "loca");
ms.encode (value.locz, 12, "locz");
ms.encode (value.dr, 6, "dr");
ms.encode (value.piu, 4, "piu");
ms.encode (value.eac, 2, "eac");
ms.encode (value.te, 2, "te");
ms.endStruct (tag, true); 
}
public static CktsrGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCktsrGrpDef (ms, "CktsrGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDefHelper.type(); 
}
public static byte [] toOctet (CktsrGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCktsrGrpDef (ms, val, "CktsrGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
