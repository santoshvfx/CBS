// $Id: ZrgrpOutputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrgrpOutputMsg implements MMarshalObject { 
	public ZrgrpOutput value;

	public ZrgrpOutputMsg () {
	}
	public ZrgrpOutputMsg (ZrgrpOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.gac = new String ();
value.end = new String ();
value.ccstrunk = new String ();
value.last1 = new String ();
value.update = new String ();
value.lterm = new String ();
value.location = new String ();
value.f = new String ();
value.groupid = new String ();
value.ZrgrpGrp1 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1[8];
for (int i0 = 0; i0 < 8; i0++) { 
value.ZrgrpGrp1[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Msg.create();
}
value.ZrgrpGrp2 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2[4];
for (int i0 = 0; i0 < 4; i0++) { 
value.ZrgrpGrp2[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2Msg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrgrpOutput (ms, tag); 
	}
	static public ZrgrpOutput decodeZrgrpOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrgrpOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (7, "cmd");
		value.mask = ms.decodeOctetString (19, "mask");
		value.x_for = ms.decodeOctetString (8, "x_for");
		value.gac = ms.decodeOctetString (9, "gac");
		value.end = ms.decodeOctetString (2, "end");
		value.ccstrunk = ms.decodeOctetString (33, "ccstrunk");
		value.last1 = ms.decodeOctetString (7, "last1");
		value.update = ms.decodeOctetString (9, "update");
		value.lterm = ms.decodeOctetString (9, "lterm");
		value.location = ms.decodeOctetString (12, "location");
		value.f = ms.decodeOctetString (2, "f");
		value.groupid = ms.decodeOctetString (41, "groupid");
		ms.startArray ("ZrgrpGrp1", false);
		{ 
			value.ZrgrpGrp1 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1[8];
			for (int __i0 = 0; __i0 < 8; __i0++) { 
				value.ZrgrpGrp1[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Msg.decodeZrgrpGrpDef1 (ms, "ZrgrpGrp1");
			} 
		}
		ms.endArray ("ZrgrpGrp1", false);
		ms.startArray ("ZrgrpGrp2", false);
		{ 
			value.ZrgrpGrp2 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2[4];
			for (int __i0 = 0; __i0 < 4; __i0++) { 
				value.ZrgrpGrp2[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2Msg.decodeZrgrpGrpDef2 (ms, "ZrgrpGrp2");
			} 
		}
		ms.endArray ("ZrgrpGrp2", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrgrpOutput (ms, value, tag); 
	}
	static public void encodeZrgrpOutput (MMarshalStrategy ms, ZrgrpOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 7, "cmd");
	ms.encode (value.mask, 19, "mask");
ms.encode (value.x_for, 8, "x_for");
ms.encode (value.gac, 9, "gac");
ms.encode (value.end, 2, "end");
ms.encode (value.ccstrunk, 33, "ccstrunk");
ms.encode (value.last1, 7, "last1");
ms.encode (value.update, 9, "update");
ms.encode (value.lterm, 9, "lterm");
ms.encode (value.location, 12, "location");
ms.encode (value.f, 2, "f");
ms.encode (value.groupid, 41, "groupid");
ms.startArray ("ZrgrpGrp1", true);
{ 
for (int __i0 = 0; __i0 < 8; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Msg.encodeZrgrpGrpDef1 (ms, value.ZrgrpGrp1[__i0], "ZrgrpGrp1");
} 
}
ms.endArray ("ZrgrpGrp1", true);
ms.startArray ("ZrgrpGrp2", true);
{ 
for (int __i0 = 0; __i0 < 4; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2Msg.encodeZrgrpGrpDef2 (ms, value.ZrgrpGrp2[__i0], "ZrgrpGrp2");
} 
}
ms.endArray ("ZrgrpGrp2", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static ZrgrpOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrgrpOutput (ms, "ZrgrpOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpOutputHelper.type(); 
}
public static byte [] toOctet (ZrgrpOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrgrpOutput (ms, val, "ZrgrpOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
