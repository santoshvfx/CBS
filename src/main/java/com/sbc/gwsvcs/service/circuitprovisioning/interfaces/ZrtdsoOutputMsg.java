// $Id: ZrtdsoOutputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrtdsoOutputMsg implements MMarshalObject { 
	public ZrtdsoOutput value;

	public ZrtdsoOutputMsg () {
	}
	public ZrtdsoOutputMsg (ZrtdsoOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.tablename = new String ();
value.tablekey = new String ();
value.adminarea = new String ();
value.tablerecord = new String ();
value.norecords = new String ();
value.note = new String ();
value.rellev = new String ();
value.mod = new String ();
value.ZrtdsoGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDef[15];
for (int i0 = 0; i0 < 15; i0++) { 
value.ZrtdsoGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrtdsoOutput (ms, tag); 
	}
	static public ZrtdsoOutput decodeZrtdsoOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrtdsoOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (7, "cmd");
		value.mask = ms.decodeOctetString (32, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.tablename = ms.decodeOctetString (17, "tablename");
		value.tablekey = ms.decodeOctetString (16, "tablekey");
		value.adminarea = ms.decodeOctetString (3, "adminarea");
		value.tablerecord = ms.decodeOctetString (25, "tablerecord");
		value.norecords = ms.decodeOctetString (6, "norecords");
		value.note = ms.decodeOctetString (41, "note");
		value.rellev = ms.decodeOctetString (10, "rellev");
		value.mod = ms.decodeOctetString (2, "mod");
		ms.startArray ("ZrtdsoGrp", false);
		{ 
			value.ZrtdsoGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDef[15];
			for (int __i0 = 0; __i0 < 15; __i0++) { 
				value.ZrtdsoGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDefMsg.decodeZrtdsoGrpDef (ms, "ZrtdsoGrp");
			} 
		}
		ms.endArray ("ZrtdsoGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrtdsoOutput (ms, value, tag); 
	}
	static public void encodeZrtdsoOutput (MMarshalStrategy ms, ZrtdsoOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 7, "cmd");
	ms.encode (value.mask, 32, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.tablename, 17, "tablename");
ms.encode (value.tablekey, 16, "tablekey");
ms.encode (value.adminarea, 3, "adminarea");
ms.encode (value.tablerecord, 25, "tablerecord");
ms.encode (value.norecords, 6, "norecords");
ms.encode (value.note, 41, "note");
ms.encode (value.rellev, 10, "rellev");
ms.encode (value.mod, 2, "mod");
ms.startArray ("ZrtdsoGrp", true);
{ 
for (int __i0 = 0; __i0 < 15; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDefMsg.encodeZrtdsoGrpDef (ms, value.ZrtdsoGrp[__i0], "ZrtdsoGrp");
} 
}
ms.endArray ("ZrtdsoGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static ZrtdsoOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrtdsoOutput (ms, "ZrtdsoOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoOutputHelper.type(); 
}
public static byte [] toOctet (ZrtdsoOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrtdsoOutput (ms, val, "ZrtdsoOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
