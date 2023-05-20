// $Id: TastgnQInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TastgnQInputMsg implements MMarshalObject { 
	public TastgnQInput value;

	public TastgnQInputMsg () {
	}
	public TastgnQInputMsg (TastgnQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnQInput();
value.ims_region = new String ();
value.ckt = new String ();
value.clo = new String ();
value.end = new String ();
value.key = new String ();
value.type = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTastgnQInput (ms, tag); 
	}
	static public TastgnQInput decodeTastgnQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TastgnQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.ckt = ms.decodeOctetString (46, "ckt");
		value.clo = ms.decodeOctetString (14, "clo");
		value.end = ms.decodeOctetString (2, "end");
		value.key = ms.decodeOctetString (33, "key");
		value.type = ms.decodeOctetString (2, "type");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTastgnQInput (ms, value, tag); 
	}
	static public void encodeTastgnQInput (MMarshalStrategy ms, TastgnQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.ckt, 46, "ckt");
ms.encode (value.clo, 14, "clo");
ms.encode (value.end, 2, "end");
ms.encode (value.key, 33, "key");
ms.encode (value.type, 2, "type");
ms.endStruct (tag, true); 
}
public static TastgnQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTastgnQInput (ms, "TastgnQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TastgnQInputHelper.type(); 
}
public static byte [] toOctet (TastgnQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTastgnQInput (ms, val, "TastgnQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
