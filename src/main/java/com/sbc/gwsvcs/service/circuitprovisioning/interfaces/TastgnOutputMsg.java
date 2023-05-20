// $Id: TastgnOutputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TastgnOutputMsg implements MMarshalObject { 
	public TastgnOutput value;

	public TastgnOutputMsg () {
	}
	public TastgnOutputMsg (TastgnOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.ckt = new String ();
value.a = new String ();
value.z = new String ();
value.clo = new String ();
value.action = new String ();
value.duedate = new String ();
value.end = new String ();
value.view = new String ();
value.gac = new String ();
value.key = new String ();
value.type = new String ();
value.trk = new String ();
value.lastupdt = new String ();
value.TastgnGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDef[18];
for (int i0 = 0; i0 < 18; i0++) { 
value.TastgnGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTastgnOutput (ms, tag); 
	}
	static public TastgnOutput decodeTastgnOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TastgnOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.mask = ms.decodeOctetString (24, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.ckt = ms.decodeOctetString (46, "ckt");
		value.a = ms.decodeOctetString (12, "a");
		value.z = ms.decodeOctetString (12, "z");
		value.clo = ms.decodeOctetString (14, "clo");
		value.action = ms.decodeOctetString (3, "action");
		value.duedate = ms.decodeOctetString (7, "duedate");
		value.end = ms.decodeOctetString (2, "end");
		value.view = ms.decodeOctetString (9, "view");
		value.gac = ms.decodeOctetString (9, "gac");
		value.key = ms.decodeOctetString (33, "key");
		value.type = ms.decodeOctetString (2, "type");
		value.trk = ms.decodeOctetString (5, "trk");
		value.lastupdt = ms.decodeOctetString (7, "lastupdt");
		ms.startArray ("TastgnGrp", false);
		{ 
			value.TastgnGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDef[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.TastgnGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDefMsg.decodeTastgnGrpDef (ms, "TastgnGrp");
			} 
		}
		ms.endArray ("TastgnGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTastgnOutput (ms, value, tag); 
	}
	static public void encodeTastgnOutput (MMarshalStrategy ms, TastgnOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 9, "cmd");
	ms.encode (value.mask, 24, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.ckt, 46, "ckt");
ms.encode (value.a, 12, "a");
ms.encode (value.z, 12, "z");
ms.encode (value.clo, 14, "clo");
ms.encode (value.action, 3, "action");
ms.encode (value.duedate, 7, "duedate");
ms.encode (value.end, 2, "end");
ms.encode (value.view, 9, "view");
ms.encode (value.gac, 9, "gac");
ms.encode (value.key, 33, "key");
ms.encode (value.type, 2, "type");
ms.encode (value.trk, 5, "trk");
ms.encode (value.lastupdt, 7, "lastupdt");
ms.startArray ("TastgnGrp", true);
{ 
for (int __i0 = 0; __i0 < 18; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnGrpDefMsg.encodeTastgnGrpDef (ms, value.TastgnGrp[__i0], "TastgnGrp");
} 
}
ms.endArray ("TastgnGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static TastgnOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTastgnOutput (ms, "TastgnOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnOutputHelper.type(); 
}
public static byte [] toOctet (TastgnOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTastgnOutput (ms, val, "TastgnOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
