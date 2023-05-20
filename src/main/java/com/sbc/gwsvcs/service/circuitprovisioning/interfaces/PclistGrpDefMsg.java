// $Id: PclistGrpDefMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PclistGrpDefMsg implements MMarshalObject { 
	public PclistGrpDef value;

	public PclistGrpDefMsg () {
	}
	public PclistGrpDefMsg (PclistGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDef();
value.c = new String ();
value.to_1 = new String ();
value.to_2 = new String ();
value.fcn = new String ();
value.date = new String ();
value.clo = new String ();
value.seg = new String ();
value.ckl = new String ();
value.order = new String ();
value.jep = new String ();
value.er = new String ();
value.from_rro = new String ();
value.from_pos = new String ();
value.bcnt = new String ();
value.act = new String ();
value.st = new String ();
value.cktid = new String ();
value.cust = new String ();
value.s = new String ();
value.d = new String ();
value.p = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePclistGrpDef (ms, tag); 
	}
	static public PclistGrpDef decodePclistGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PclistGrpDef value = create();
		ms.startStruct (tag, false);
		value.c = ms.decodeOctetString (2, "c");
		value.to_1 = ms.decodeOctetString (4, "to_1");
		value.to_2 = ms.decodeOctetString (4, "to_2");
		value.fcn = ms.decodeOctetString (6, "fcn");
		value.date = ms.decodeOctetString (7, "date");
		value.clo = ms.decodeOctetString (10, "clo");
		value.seg = ms.decodeOctetString (4, "seg");
		value.ckl = ms.decodeOctetString (5, "ckl");
		value.order = ms.decodeOctetString (19, "order");
		value.jep = ms.decodeOctetString (4, "jep");
		value.er = ms.decodeOctetString (3, "er");
		value.from_rro = ms.decodeOctetString (4, "from_rro");
		value.from_pos = ms.decodeOctetString (4, "from_pos");
		value.bcnt = ms.decodeOctetString (5, "bcnt");
		value.act = ms.decodeOctetString (4, "act");
		value.st = ms.decodeOctetString (3, "st");
		value.cktid = ms.decodeOctetString (46, "cktid");
		value.cust = ms.decodeOctetString (21, "cust");
		value.s = ms.decodeOctetString (2, "s");
		value.d = ms.decodeOctetString (2, "d");
		value.p = ms.decodeOctetString (2, "p");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePclistGrpDef (ms, value, tag); 
	}
	static public void encodePclistGrpDef (MMarshalStrategy ms, PclistGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.c, 2, "c");
	ms.encode (value.to_1, 4, "to_1");
ms.encode (value.to_2, 4, "to_2");
ms.encode (value.fcn, 6, "fcn");
ms.encode (value.date, 7, "date");
ms.encode (value.clo, 10, "clo");
ms.encode (value.seg, 4, "seg");
ms.encode (value.ckl, 5, "ckl");
ms.encode (value.order, 19, "order");
ms.encode (value.jep, 4, "jep");
ms.encode (value.er, 3, "er");
ms.encode (value.from_rro, 4, "from_rro");
ms.encode (value.from_pos, 4, "from_pos");
ms.encode (value.bcnt, 5, "bcnt");
ms.encode (value.act, 4, "act");
ms.encode (value.st, 3, "st");
ms.encode (value.cktid, 46, "cktid");
ms.encode (value.cust, 21, "cust");
ms.encode (value.s, 2, "s");
ms.encode (value.d, 2, "d");
ms.encode (value.p, 2, "p");
ms.endStruct (tag, true); 
}
public static PclistGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodePclistGrpDef (ms, "PclistGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDefHelper.type(); 
}
public static byte [] toOctet (PclistGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodePclistGrpDef (ms, val, "PclistGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
