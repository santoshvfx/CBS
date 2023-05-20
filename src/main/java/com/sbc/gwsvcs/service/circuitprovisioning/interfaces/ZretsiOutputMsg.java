// $Id: ZretsiOutputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZretsiOutputMsg implements MMarshalObject { 
	public ZretsiOutput value;

	public ZretsiOutputMsg () {
	}
	public ZretsiOutputMsg (ZretsiOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.lterm = new String ();
value.location = new String ();
value.isopt = new String ();
value.asopt = new String ();
value.zretsidate = new String ();
value.clo = new String ();
value.item = new String ();
value.itemend = new String ();
value.equiptype = new String ();
value.lvl = new String ();
value.ZretsiGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDef[18];
for (int i0 = 0; i0 < 18; i0++) { 
value.ZretsiGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZretsiOutput (ms, tag); 
	}
	static public ZretsiOutput decodeZretsiOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZretsiOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (7, "cmd");
		value.mask = ms.decodeOctetString (16, "mask");
		value.x_for = ms.decodeOctetString (8, "x_for");
		value.lterm = ms.decodeOctetString (9, "lterm");
		value.location = ms.decodeOctetString (12, "location");
		value.isopt = ms.decodeOctetString (4, "isopt");
		value.asopt = ms.decodeOctetString (4, "asopt");
		value.zretsidate = ms.decodeOctetString (7, "zretsidate");
		value.clo = ms.decodeOctetString (10, "clo");
		value.item = ms.decodeOctetString (4, "item");
		value.itemend = ms.decodeOctetString (4, "itemend");
		value.equiptype = ms.decodeOctetString (15, "equiptype");
		value.lvl = ms.decodeOctetString (2, "lvl");
		ms.startArray ("ZretsiGrp", false);
		{ 
			value.ZretsiGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDef[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.ZretsiGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDefMsg.decodeZretsiGrpDef (ms, "ZretsiGrp");
			} 
		}
		ms.endArray ("ZretsiGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZretsiOutput (ms, value, tag); 
	}
	static public void encodeZretsiOutput (MMarshalStrategy ms, ZretsiOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 7, "cmd");
	ms.encode (value.mask, 16, "mask");
ms.encode (value.x_for, 8, "x_for");
ms.encode (value.lterm, 9, "lterm");
ms.encode (value.location, 12, "location");
ms.encode (value.isopt, 4, "isopt");
ms.encode (value.asopt, 4, "asopt");
ms.encode (value.zretsidate, 7, "zretsidate");
ms.encode (value.clo, 10, "clo");
ms.encode (value.item, 4, "item");
ms.encode (value.itemend, 4, "itemend");
ms.encode (value.equiptype, 15, "equiptype");
ms.encode (value.lvl, 2, "lvl");
ms.startArray ("ZretsiGrp", true);
{ 
for (int __i0 = 0; __i0 < 18; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDefMsg.encodeZretsiGrpDef (ms, value.ZretsiGrp[__i0], "ZretsiGrp");
} 
}
ms.endArray ("ZretsiGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static ZretsiOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZretsiOutput (ms, "ZretsiOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiOutputHelper.type(); 
}
public static byte [] toOctet (ZretsiOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZretsiOutput (ms, val, "ZretsiOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
