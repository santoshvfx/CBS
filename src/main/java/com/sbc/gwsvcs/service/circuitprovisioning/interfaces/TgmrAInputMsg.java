// $Id: TgmrAInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmrAInputMsg implements MMarshalObject { 
	public TgmrAInput value;

	public TgmrAInputMsg () {
	}
	public TgmrAInputMsg (TgmrAInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrAInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrAInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrAInput();
value.ims_region = new String ();
value.cmd = new String ();
value.adminarea = new String ();
value.fmt = new String ();
value.idtype = new String ();
value.custswloc = new String ();
value.cspc = new String ();
value.mco = new String ();
value.groupid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmrAInput (ms, tag); 
	}
	static public TgmrAInput decodeTgmrAInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmrAInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.adminarea = ms.decodeOctetString (3, "adminarea");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.idtype = ms.decodeOctetString (6, "idtype");
		value.custswloc = ms.decodeOctetString (12, "custswloc");
		value.cspc = ms.decodeOctetString (12, "cspc");
		value.mco = ms.decodeOctetString (12, "mco");
		value.groupid = ms.decodeOctetString (46, "groupid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmrAInput (ms, value, tag); 
	}
	static public void encodeTgmrAInput (MMarshalStrategy ms, TgmrAInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.cmd, 9, "cmd");
ms.encode (value.adminarea, 3, "adminarea");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.idtype, 6, "idtype");
ms.encode (value.custswloc, 12, "custswloc");
ms.encode (value.cspc, 12, "cspc");
ms.encode (value.mco, 12, "mco");
ms.encode (value.groupid, 46, "groupid");
ms.endStruct (tag, true); 
}
public static TgmrAInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeTgmrAInput (ms, "TgmrAInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrAInputHelper.type(); 
}
public static byte [] toOctet (TgmrAInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeTgmrAInput (ms, val, "TgmrAInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
