// $Id: TgnsumQInputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgnsumQInputMsg implements MMarshalObject { 
	public TgnsumQInput value;

	public TgnsumQInputMsg () {
	}
	public TgnsumQInputMsg (TgnsumQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumQInput();
value.ims_region = new String ();
value.location = new String ();
value.fmt = new String ();
value.display = new String ();
value.starttgn = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgnsumQInput (ms, tag); 
	}
	static public TgnsumQInput decodeTgnsumQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgnsumQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.start_page = ms.decodeLong ("start_page");
		value.end_page = ms.decodeLong ("end_page");
		value.location = ms.decodeOctetString (12, "location");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.display = ms.decodeOctetString (2, "display");
		value.starttgn = ms.decodeOctetString (5, "starttgn");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgnsumQInput (ms, value, tag); 
	}
	static public void encodeTgnsumQInput (MMarshalStrategy ms, TgnsumQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.start_page, "start_page");
	ms.encode (value.end_page, "end_page");
	ms.encode (value.location, 12, "location");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.display, 2, "display");
ms.encode (value.starttgn, 5, "starttgn");
ms.endStruct (tag, true); 
}
public static TgnsumQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgnsumQInput (ms, "TgnsumQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgnsumQInputHelper.type(); 
}
public static byte [] toOctet (TgnsumQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgnsumQInput (ms, val, "TgnsumQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
