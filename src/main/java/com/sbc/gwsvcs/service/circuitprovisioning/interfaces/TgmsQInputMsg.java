// $Id: TgmsQInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmsQInputMsg implements MMarshalObject { 
	public TgmsQInput value;

	public TgmsQInputMsg () {
	}
	public TgmsQInputMsg (TgmsQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsQInput();
value.ims_region = new String ();
value.groupaccess = new String ();
value.groupid = new String ();
value.trknbr = new String ();
value.fmt = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmsQInput (ms, tag); 
	}
	static public TgmsQInput decodeTgmsQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmsQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.groupaccess = ms.decodeOctetString (9, "groupaccess");
		value.groupid = ms.decodeOctetString (41, "groupid");
		value.trknbr = ms.decodeOctetString (5, "trknbr");
		value.fmt = ms.decodeOctetString (2, "fmt");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmsQInput (ms, value, tag); 
	}
	static public void encodeTgmsQInput (MMarshalStrategy ms, TgmsQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.groupaccess, 9, "groupaccess");
ms.encode (value.groupid, 41, "groupid");
ms.encode (value.trknbr, 5, "trknbr");
ms.encode (value.fmt, 2, "fmt");
ms.endStruct (tag, true); 
}
public static TgmsQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgmsQInput (ms, "TgmsQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmsQInputHelper.type(); 
}
public static byte [] toOctet (TgmsQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgmsQInput (ms, val, "TgmsQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
