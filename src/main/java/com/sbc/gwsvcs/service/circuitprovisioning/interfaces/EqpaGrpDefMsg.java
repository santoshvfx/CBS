// $Id: EqpaGrpDefMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpaGrpDefMsg implements MMarshalObject { 
	public EqpaGrpDef value;

	public EqpaGrpDefMsg () {
	}
	public EqpaGrpDefMsg (EqpaGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDef();
value.activity = new String ();
value.clo = new String ();
value.ddmon = new String ();
value.ddday = new String ();
value.ddyr = new String ();
value.ap = new String ();
value.conf = new String ();
value.mc = new String ();
value.fmt = new String ();
value.cktid = new String ();
value.drclass = new String ();
value.misuse = new String ();
value.tsp = new String ();
value.mcc = new String ();
value.funct = new String ();
value.dv = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpaGrpDef (ms, tag); 
	}
	static public EqpaGrpDef decodeEqpaGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpaGrpDef value = create();
		ms.startStruct (tag, false);
		value.activity = ms.decodeOctetString (2, "activity");
		value.clo = ms.decodeOctetString (16, "clo");
		value.ddmon = ms.decodeOctetString (3, "ddmon");
		value.ddday = ms.decodeOctetString (3, "ddday");
		value.ddyr = ms.decodeOctetString (3, "ddyr");
		value.ap = ms.decodeOctetString (2, "ap");
		value.conf = ms.decodeOctetString (7, "conf");
		value.mc = ms.decodeOctetString (7, "mc");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.cktid = ms.decodeOctetString (53, "cktid");
		value.drclass = ms.decodeOctetString (5, "drclass");
		value.misuse = ms.decodeOctetString (3, "misuse");
		value.tsp = ms.decodeOctetString (3, "tsp");
		value.mcc = ms.decodeOctetString (2, "mcc");
		value.funct = ms.decodeOctetString (15, "funct");
		value.dv = ms.decodeOctetString (3, "dv");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpaGrpDef (ms, value, tag); 
	}
	static public void encodeEqpaGrpDef (MMarshalStrategy ms, EqpaGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.activity, 2, "activity");
	ms.encode (value.clo, 16, "clo");
ms.encode (value.ddmon, 3, "ddmon");
ms.encode (value.ddday, 3, "ddday");
ms.encode (value.ddyr, 3, "ddyr");
ms.encode (value.ap, 2, "ap");
ms.encode (value.conf, 7, "conf");
ms.encode (value.mc, 7, "mc");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.cktid, 53, "cktid");
ms.encode (value.drclass, 5, "drclass");
ms.encode (value.misuse, 3, "misuse");
ms.encode (value.tsp, 3, "tsp");
ms.encode (value.mcc, 2, "mcc");
ms.encode (value.funct, 15, "funct");
ms.encode (value.dv, 3, "dv");
ms.endStruct (tag, true); 
}
public static EqpaGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpaGrpDef (ms, "EqpaGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDefHelper.type(); 
}
public static byte [] toOctet (EqpaGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpaGrpDef (ms, val, "EqpaGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
