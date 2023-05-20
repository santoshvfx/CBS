// $Id: TgnsumOutputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgnsumOutputMsg implements MMarshalObject { 
	public TgnsumOutput value;

	public TgnsumOutputMsg () {
	}
	public TgnsumOutputMsg (TgnsumOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.location1 = new String ();
value.fmt = new String ();
value.display = new String ();
value.starttgn = new String ();
value.lterm = new String ();
value.TgnsumGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef[18];
for (int i0 = 0; i0 < 18; i0++) { 
value.TgnsumGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgnsumOutput (ms, tag); 
	}
	static public TgnsumOutput decodeTgnsumOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgnsumOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.mask = ms.decodeOctetString (17, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.location1 = ms.decodeOctetString (12, "location1");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.display = ms.decodeOctetString (2, "display");
		value.starttgn = ms.decodeOctetString (5, "starttgn");
		value.lterm = ms.decodeOctetString (9, "lterm");
		ms.startArray ("TgnsumGrp", false);
		{ 
			value.TgnsumGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDef[18];
			for (int __i0 = 0; __i0 < 18; __i0++) { 
				value.TgnsumGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDefMsg.decodeTgnsumGrpDef (ms, "TgnsumGrp");
			} 
		}
		ms.endArray ("TgnsumGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgnsumOutput (ms, value, tag); 
	}
	static public void encodeTgnsumOutput (MMarshalStrategy ms, TgnsumOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 9, "cmd");
	ms.encode (value.mask, 17, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.location1, 12, "location1");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.display, 2, "display");
ms.encode (value.starttgn, 5, "starttgn");
ms.encode (value.lterm, 9, "lterm");
ms.startArray ("TgnsumGrp", true);
{ 
for (int __i0 = 0; __i0 < 18; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumGrpDefMsg.encodeTgnsumGrpDef (ms, value.TgnsumGrp[__i0], "TgnsumGrp");
} 
}
ms.endArray ("TgnsumGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static TgnsumOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgnsumOutput (ms, "TgnsumOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumOutputHelper.type(); 
}
public static byte [] toOctet (TgnsumOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgnsumOutput (ms, val, "TgnsumOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
