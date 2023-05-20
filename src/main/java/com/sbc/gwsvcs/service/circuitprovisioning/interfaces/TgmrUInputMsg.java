// $Id: TgmrUInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmrUInputMsg implements MMarshalObject { 
	public TgmrUInput value;

	public TgmrUInputMsg () {
	}
	public TgmrUInputMsg (TgmrUInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrUInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrUInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrUInput();
value.ims_region = new String ();
value.groupaccess = new String ();
value.fmt = new String ();
value.idtype = new String ();
value.groupid = new String ();
value.date = new String ();
value.act = new String ();
value.cspc = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmrUInput (ms, tag); 
	}
	static public TgmrUInput decodeTgmrUInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmrUInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.groupaccess = ms.decodeOctetString (9, "groupaccess");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.idtype = ms.decodeOctetString (6, "idtype");
		value.groupid = ms.decodeOctetString (46, "groupid");
		value.date = ms.decodeOctetString (7, "date");
		value.act = ms.decodeOctetString (3, "act");
		value.cspc = ms.decodeOctetString (12, "cspc");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmrUInput (ms, value, tag); 
	}
	static public void encodeTgmrUInput (MMarshalStrategy ms, TgmrUInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.groupaccess, 9, "groupaccess");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.idtype, 6, "idtype");
ms.encode (value.groupid, 46, "groupid");
ms.encode (value.date, 7, "date");
ms.encode (value.act, 3, "act");
ms.encode (value.cspc, 12, "cspc");
ms.endStruct (tag, true); 
}
public static TgmrUInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgmrUInput (ms, "TgmrUInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrUInputHelper.type(); 
}
public static byte [] toOctet (TgmrUInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgmrUInput (ms, val, "TgmrUInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
