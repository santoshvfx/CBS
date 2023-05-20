// $Id: EqpscOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpscOutputMsg implements MMarshalObject { 
	public EqpscOutput value;

	public EqpscOutputMsg () {
	}
	public EqpscOutputMsg (EqpscOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscOutput();
value.mask = new String ();
value.x_for = new String ();
value.location = new String ();
value.equip_code = new String ();
value.level = new String ();
value.relayrack = new String ();
value.EqpscGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDef[19];
for (int i0 = 0; i0 < 19; i0++) { 
value.EqpscGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpscOutput (ms, tag); 
	}
	static public EqpscOutput decodeEqpscOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpscOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.mask = ms.decodeOctetString (15, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.location = ms.decodeOctetString (12, "location");
		value.equip_code = ms.decodeOctetString (15, "equip_code");
		value.level = ms.decodeOctetString (2, "level");
		value.relayrack = ms.decodeOctetString (11, "relayrack");
		ms.startArray ("EqpscGrp", false);
		{ 
			value.EqpscGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDef[19];
			for (int __i0 = 0; __i0 < 19; __i0++) { 
				value.EqpscGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDefMsg.decodeEqpscGrpDef (ms, "EqpscGrp");
			} 
		}
		ms.endArray ("EqpscGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpscOutput (ms, value, tag); 
	}
	static public void encodeEqpscOutput (MMarshalStrategy ms, EqpscOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.mask, 15, "mask");
	ms.encode (value.x_for, 9, "x_for");
ms.encode (value.location, 12, "location");
ms.encode (value.equip_code, 15, "equip_code");
ms.encode (value.level, 2, "level");
ms.encode (value.relayrack, 11, "relayrack");
ms.startArray ("EqpscGrp", true);
{ 
for (int __i0 = 0; __i0 < 19; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDefMsg.encodeEqpscGrpDef (ms, value.EqpscGrp[__i0], "EqpscGrp");
} 
}
ms.endArray ("EqpscGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static EqpscOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpscOutput (ms, "EqpscOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscOutputHelper.type(); 
}
public static byte [] toOctet (EqpscOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpscOutput (ms, val, "EqpscOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
