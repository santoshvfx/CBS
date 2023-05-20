// $Id: EqpuOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpuOutputMsg implements MMarshalObject { 
	public EqpuOutput value;

	public EqpuOutputMsg () {
	}
	public EqpuOutputMsg (EqpuOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuOutput();
value.mask = new String ();
value.x_for = new String ();
value.location = new String ();
value.hecig = new String ();
value.EqpuGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDef[80];
for (int i0 = 0; i0 < 80; i0++) { 
value.EqpuGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpuOutput (ms, tag); 
	}
	static public EqpuOutput decodeEqpuOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpuOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.mask = ms.decodeOctetString (30, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.location = ms.decodeOctetString (12, "location");
		value.hecig = ms.decodeOctetString (9, "hecig");
		ms.startArray ("EqpuGrp", false);
		{ 
			value.EqpuGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDef[80];
			for (int __i0 = 0; __i0 < 80; __i0++) { 
				value.EqpuGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDefMsg.decodeEqpuGrpDef (ms, "EqpuGrp");
			} 
		}
		ms.endArray ("EqpuGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpuOutput (ms, value, tag); 
	}
	static public void encodeEqpuOutput (MMarshalStrategy ms, EqpuOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.mask, 30, "mask");
	ms.encode (value.x_for, 9, "x_for");
ms.encode (value.location, 12, "location");
ms.encode (value.hecig, 9, "hecig");
ms.startArray ("EqpuGrp", true);
{ 
for (int __i0 = 0; __i0 < 80; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDefMsg.encodeEqpuGrpDef (ms, value.EqpuGrp[__i0], "EqpuGrp");
} 
}
ms.endArray ("EqpuGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static EqpuOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpuOutput (ms, "EqpuOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuOutputHelper.type(); 
}
public static byte [] toOctet (EqpuOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpuOutput (ms, val, "EqpuOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
