// $Id: ZretsiQInputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZretsiQInputMsg implements MMarshalObject { 
	public ZretsiQInput value;

	public ZretsiQInputMsg () {
	}
	public ZretsiQInputMsg (ZretsiQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiQInput();
value.ims_region = new String ();
value.location = new String ();
value.isopt = new String ();
value.asopt = new String ();
value.equiptype = new String ();
value.lvl = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZretsiQInput (ms, tag); 
	}
	static public ZretsiQInput decodeZretsiQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZretsiQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.location = ms.decodeOctetString (12, "location");
		value.isopt = ms.decodeOctetString (4, "isopt");
		value.asopt = ms.decodeOctetString (4, "asopt");
		value.equiptype = ms.decodeOctetString (15, "equiptype");
		value.lvl = ms.decodeOctetString (2, "lvl");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZretsiQInput (ms, value, tag); 
	}
	static public void encodeZretsiQInput (MMarshalStrategy ms, ZretsiQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.location, 12, "location");
ms.encode (value.isopt, 4, "isopt");
ms.encode (value.asopt, 4, "asopt");
ms.encode (value.equiptype, 15, "equiptype");
ms.encode (value.lvl, 2, "lvl");
ms.endStruct (tag, true); 
}
public static ZretsiQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZretsiQInput (ms, "ZretsiQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiQInputHelper.type(); 
}
public static byte [] toOctet (ZretsiQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZretsiQInput (ms, val, "ZretsiQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
