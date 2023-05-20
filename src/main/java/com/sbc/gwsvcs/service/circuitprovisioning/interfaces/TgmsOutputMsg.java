// $Id: TgmsOutputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmsOutputMsg implements MMarshalObject { 
	public TgmsOutput value;

	public TgmsOutputMsg () {
	}
	public TgmsOutputMsg (TgmsOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.groupaccess = new String ();
value.trknbr = new String ();
value.totaltirks = new String ();
value.fmt1 = new String ();
value.groupid1 = new String ();
value.status1 = new String ();
value.fmt2 = new String ();
value.groupid2 = new String ();
value.status2 = new String ();
value.tgmsdisplay = new String ();
value.TgmsGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDef[15];
for (int i0 = 0; i0 < 15; i0++) { 
value.TgmsGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmsOutput (ms, tag); 
	}
	static public TgmsOutput decodeTgmsOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmsOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.mask = ms.decodeOctetString (47, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.groupaccess = ms.decodeOctetString (9, "groupaccess");
		value.trknbr = ms.decodeOctetString (5, "trknbr");
		value.totaltirks = ms.decodeOctetString (5, "totaltirks");
		value.fmt1 = ms.decodeOctetString (2, "fmt1");
		value.groupid1 = ms.decodeOctetString (41, "groupid1");
		value.status1 = ms.decodeOctetString (6, "status1");
		value.fmt2 = ms.decodeOctetString (2, "fmt2");
		value.groupid2 = ms.decodeOctetString (41, "groupid2");
		value.status2 = ms.decodeOctetString (6, "status2");
		value.tgmsdisplay = ms.decodeOctetString (2, "tgmsdisplay");
		ms.startArray ("TgmsGrp", false);
		{ 
			value.TgmsGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDef[15];
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				value.TgmsGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDefMsg.decodeTgmsGrpDef (ms, "TgmsGrp");
			} 
		}
		ms.endArray ("TgmsGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmsOutput (ms, value, tag); 
	}
	static public void encodeTgmsOutput (MMarshalStrategy ms, TgmsOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 9, "cmd");
	ms.encode (value.mask, 47, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.groupaccess, 9, "groupaccess");
ms.encode (value.trknbr, 5, "trknbr");
ms.encode (value.totaltirks, 5, "totaltirks");
ms.encode (value.fmt1, 2, "fmt1");
ms.encode (value.groupid1, 41, "groupid1");
ms.encode (value.status1, 6, "status1");
ms.encode (value.fmt2, 2, "fmt2");
ms.encode (value.groupid2, 41, "groupid2");
ms.encode (value.status2, 6, "status2");
ms.encode (value.tgmsdisplay, 2, "tgmsdisplay");
ms.startArray ("TgmsGrp", true);
{ 
for (int __i0 = 0; __i0 < 15; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsGrpDefMsg.encodeTgmsGrpDef (ms, value.TgmsGrp[__i0], "TgmsGrp");
} 
}
ms.endArray ("TgmsGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static TgmsOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgmsOutput (ms, "TgmsOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsOutputHelper.type(); 
}
public static byte [] toOctet (TgmsOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgmsOutput (ms, val, "TgmsOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
