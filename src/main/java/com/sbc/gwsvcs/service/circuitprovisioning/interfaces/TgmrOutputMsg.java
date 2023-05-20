// $Id: TgmrOutputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmrOutputMsg implements MMarshalObject { 
	public TgmrOutput value;

	public TgmrOutputMsg () {
	}
	public TgmrOutputMsg (TgmrOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.groupaccess = new String ();
value.adminarea = new String ();
value.TgmrGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDef[5];
for (int i0 = 0; i0 < 5; i0++) { 
value.TgmrGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDefMsg.create();
}
value.ineffect = new String ();
value.spare = new String ();
value.custswloc = new String ();
value.pendingdisc = new String ();
value.pendingconn = new String ();
value.pendingdet = new String ();
value.pendingatt = new String ();
value.cspc = new String ();
value.totalworking = new String ();
value.rmks = new String ();
value.mco = new String ();
value.cust = new String ();
value.custloc = new String ();
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmrOutput (ms, tag); 
	}
	static public TgmrOutput decodeTgmrOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmrOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.mask = ms.decodeOctetString (46, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.groupaccess = ms.decodeOctetString (9, "groupaccess");
		value.adminarea = ms.decodeOctetString (3, "adminarea");
		ms.startArray ("TgmrGrp", false);
		{ 
			value.TgmrGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDef[5];
			for (int __i0 = 0; __i0 < 5; __i0++) { 
				value.TgmrGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDefMsg.decodeTgmrGrpDef (ms, "TgmrGrp");
			} 
		}
		ms.endArray ("TgmrGrp", false);
		value.ineffect = ms.decodeOctetString (6, "ineffect");
		value.spare = ms.decodeOctetString (6, "spare");
		value.custswloc = ms.decodeOctetString (12, "custswloc");
		value.pendingdisc = ms.decodeOctetString (6, "pendingdisc");
		value.pendingconn = ms.decodeOctetString (6, "pendingconn");
		value.pendingdet = ms.decodeOctetString (6, "pendingdet");
		value.pendingatt = ms.decodeOctetString (6, "pendingatt");
		value.cspc = ms.decodeOctetString (12, "cspc");
		value.totalworking = ms.decodeOctetString (7, "totalworking");
		value.rmks = ms.decodeOctetString (21, "rmks");
		value.mco = ms.decodeOctetString (12, "mco");
		value.cust = ms.decodeOctetString (21, "cust");
		value.custloc = ms.decodeOctetString (12, "custloc");
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmrOutput (ms, value, tag); 
	}
	static public void encodeTgmrOutput (MMarshalStrategy ms, TgmrOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 9, "cmd");
	ms.encode (value.mask, 46, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.groupaccess, 9, "groupaccess");
ms.encode (value.adminarea, 3, "adminarea");
ms.startArray ("TgmrGrp", true);
{ 
for (int __i0 = 0; __i0 < 5; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrGrpDefMsg.encodeTgmrGrpDef (ms, value.TgmrGrp[__i0], "TgmrGrp");
} 
}
ms.endArray ("TgmrGrp", true);
ms.encode (value.ineffect, 6, "ineffect");
ms.encode (value.spare, 6, "spare");
ms.encode (value.custswloc, 12, "custswloc");
ms.encode (value.pendingdisc, 6, "pendingdisc");
ms.encode (value.pendingconn, 6, "pendingconn");
ms.encode (value.pendingdet, 6, "pendingdet");
ms.encode (value.pendingatt, 6, "pendingatt");
ms.encode (value.cspc, 12, "cspc");
ms.encode (value.totalworking, 7, "totalworking");
ms.encode (value.rmks, 21, "rmks");
ms.encode (value.mco, 12, "mco");
ms.encode (value.cust, 21, "cust");
ms.encode (value.custloc, 12, "custloc");
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static TgmrOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgmrOutput (ms, "TgmrOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrOutputHelper.type(); 
}
public static byte [] toOctet (TgmrOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgmrOutput (ms, val, "TgmrOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
