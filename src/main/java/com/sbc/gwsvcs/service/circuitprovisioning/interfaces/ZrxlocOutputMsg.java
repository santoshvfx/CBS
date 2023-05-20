// $Id: ZrxlocOutputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrxlocOutputMsg implements MMarshalObject { 
	public ZrxlocOutput value;

	public ZrxlocOutputMsg () {
	}
	public ZrxlocOutputMsg (ZrxlocOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocOutput();
value.mask = new String ();
value.x_for = new String ();
value.function = new String ();
value.location1 = new String ();
value.lpc1 = new String ();
value.pc_ncp1 = new String ();
value.pcbit = new String ();
value.ZrxlocGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDef[10];
for (int i0 = 0; i0 < 10; i0++) { 
value.ZrxlocGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrxlocOutput (ms, tag); 
	}
	static public ZrxlocOutput decodeZrxlocOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrxlocOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.mask = ms.decodeOctetString (53, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.function = ms.decodeOctetString (2, "function");
		value.location1 = ms.decodeOctetString (12, "location1");
		value.lpc1 = ms.decodeOctetString (7, "lpc1");
		value.pc_ncp1 = ms.decodeOctetString (12, "pc_ncp1");
		value.pcbit = ms.decodeOctetString (25, "pcbit");
		ms.startArray ("ZrxlocGrp", false);
		{ 
			value.ZrxlocGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDef[10];
			for (int __i0 = 0; __i0 < 10; __i0++) { 
				value.ZrxlocGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDefMsg.decodeZrxlocGrpDef (ms, "ZrxlocGrp");
			} 
		}
		ms.endArray ("ZrxlocGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrxlocOutput (ms, value, tag); 
	}
	static public void encodeZrxlocOutput (MMarshalStrategy ms, ZrxlocOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.mask, 53, "mask");
	ms.encode (value.x_for, 9, "x_for");
ms.encode (value.function, 2, "function");
ms.encode (value.location1, 12, "location1");
ms.encode (value.lpc1, 7, "lpc1");
ms.encode (value.pc_ncp1, 12, "pc_ncp1");
ms.encode (value.pcbit, 25, "pcbit");
ms.startArray ("ZrxlocGrp", true);
{ 
for (int __i0 = 0; __i0 < 10; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDefMsg.encodeZrxlocGrpDef (ms, value.ZrxlocGrp[__i0], "ZrxlocGrp");
} 
}
ms.endArray ("ZrxlocGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static ZrxlocOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrxlocOutput (ms, "ZrxlocOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocOutputHelper.type(); 
}
public static byte [] toOctet (ZrxlocOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrxlocOutput (ms, val, "ZrxlocOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
