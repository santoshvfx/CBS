// $Id: TastgnUInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TastgnUInputMsg implements MMarshalObject { 
	public TastgnUInput value;

	public TastgnUInputMsg () {
	}
	public TastgnUInputMsg (TastgnUInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnUInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnUInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnUInput();
value.ims_region = new String ();
value.cmd = new String ();
value.key = new String ();
value.end = new String ();
value.prev_endtrunk = new String ();
value.tgn = new String ();
value.starttmn = new String ();
value.starttrunkno = new String ();
value.endtrunk = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTastgnUInput (ms, tag); 
	}
	static public TastgnUInput decodeTastgnUInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TastgnUInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.idx = ms.decodeLong ("idx");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.key = ms.decodeOctetString (33, "key");
		value.end = ms.decodeOctetString (2, "end");
		value.prev_endtrunk = ms.decodeOctetString (5, "prev_endtrunk");
		value.tgn = ms.decodeOctetString (5, "tgn");
		value.starttmn = ms.decodeOctetString (5, "starttmn");
		value.starttrunkno = ms.decodeOctetString (5, "starttrunkno");
		value.endtrunk = ms.decodeOctetString (5, "endtrunk");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTastgnUInput (ms, value, tag); 
	}
	static public void encodeTastgnUInput (MMarshalStrategy ms, TastgnUInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.idx, "idx");
	ms.encode (value.cmd, 9, "cmd");
ms.encode (value.key, 33, "key");
ms.encode (value.end, 2, "end");
ms.encode (value.prev_endtrunk, 5, "prev_endtrunk");
ms.encode (value.tgn, 5, "tgn");
ms.encode (value.starttmn, 5, "starttmn");
ms.encode (value.starttrunkno, 5, "starttrunkno");
ms.encode (value.endtrunk, 5, "endtrunk");
ms.endStruct (tag, true); 
}
public static TastgnUInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTastgnUInput (ms, "TastgnUInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnUInputHelper.type(); 
}
public static byte [] toOctet (TastgnUInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTastgnUInput (ms, val, "TastgnUInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
