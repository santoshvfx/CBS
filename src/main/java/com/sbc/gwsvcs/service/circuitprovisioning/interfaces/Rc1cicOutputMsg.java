// $Id: Rc1cicOutputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Rc1cicOutputMsg implements MMarshalObject { 
	public Rc1cicOutput value;

	public Rc1cicOutputMsg () {
	}
	public Rc1cicOutputMsg (Rc1cicOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.loca = new String ();
value.locz = new String ();
value.pca = new String ();
value.pcz = new String ();
value.tcicoption = new String ();
value.blocksize = new String ();
value.autopost = new String ();
value.start = new String ();
value.end = new String ();
value.accesscode = new String ();
value.fmt = new String ();
value.id = new String ();
value.starttcic = new String ();
value.display = new String ();
value.Rc1cicGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDef[13];
for (int i0 = 0; i0 < 13; i0++) { 
value.Rc1cicGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRc1cicOutput (ms, tag); 
	}
	static public Rc1cicOutput decodeRc1cicOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Rc1cicOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.mask = ms.decodeOctetString (45, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.loca = ms.decodeOctetString (12, "loca");
		value.locz = ms.decodeOctetString (12, "locz");
		value.pca = ms.decodeOctetString (12, "pca");
		value.pcz = ms.decodeOctetString (12, "pcz");
		value.tcicoption = ms.decodeOctetString (2, "tcicoption");
		value.blocksize = ms.decodeOctetString (6, "blocksize");
		value.autopost = ms.decodeOctetString (2, "autopost");
		value.start = ms.decodeOctetString (6, "start");
		value.end = ms.decodeOctetString (6, "end");
		value.accesscode = ms.decodeOctetString (9, "accesscode");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.id = ms.decodeOctetString (46, "id");
		value.starttcic = ms.decodeOctetString (6, "starttcic");
		value.display = ms.decodeOctetString (2, "display");
		ms.startArray ("Rc1cicGrp", false);
		{ 
			value.Rc1cicGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDef[13];
			for (int __i0 = 0; __i0 < 13; __i0++) { 
				value.Rc1cicGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDefMsg.decodeRc1cicGrpDef (ms, "Rc1cicGrp");
			} 
		}
		ms.endArray ("Rc1cicGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRc1cicOutput (ms, value, tag); 
	}
	static public void encodeRc1cicOutput (MMarshalStrategy ms, Rc1cicOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 9, "cmd");
	ms.encode (value.mask, 45, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.loca, 12, "loca");
ms.encode (value.locz, 12, "locz");
ms.encode (value.pca, 12, "pca");
ms.encode (value.pcz, 12, "pcz");
ms.encode (value.tcicoption, 2, "tcicoption");
ms.encode (value.blocksize, 6, "blocksize");
ms.encode (value.autopost, 2, "autopost");
ms.encode (value.start, 6, "start");
ms.encode (value.end, 6, "end");
ms.encode (value.accesscode, 9, "accesscode");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.id, 46, "id");
ms.encode (value.starttcic, 6, "starttcic");
ms.encode (value.display, 2, "display");
ms.startArray ("Rc1cicGrp", true);
{ 
for (int __i0 = 0; __i0 < 13; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicGrpDefMsg.encodeRc1cicGrpDef (ms, value.Rc1cicGrp[__i0], "Rc1cicGrp");
} 
}
ms.endArray ("Rc1cicGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static Rc1cicOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRc1cicOutput (ms, "Rc1cicOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicOutputHelper.type(); 
}
public static byte [] toOctet (Rc1cicOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRc1cicOutput (ms, val, "Rc1cicOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
