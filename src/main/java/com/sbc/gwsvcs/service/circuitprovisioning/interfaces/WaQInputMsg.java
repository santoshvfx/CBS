// $Id: WaQInputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class WaQInputMsg implements MMarshalObject { 
	public WaQInput value;

	public WaQInputMsg () {
	}
	public WaQInputMsg (WaQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInput();
value.ims_region = new String ();
value.clo = new String ();
value.cac = new String ();
value.ckt = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeWaQInput (ms, tag); 
	}
	static public WaQInput decodeWaQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		WaQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.clo = ms.decodeOctetString (14, "clo");
		value.cac = ms.decodeOctetString (9, "cac");
		value.ckt = ms.decodeOctetString (48, "ckt");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeWaQInput (ms, value, tag); 
	}
	static public void encodeWaQInput (MMarshalStrategy ms, WaQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.clo, 14, "clo");
ms.encode (value.cac, 9, "cac");
ms.encode (value.ckt, 48, "ckt");
ms.endStruct (tag, true); 
}
public static WaQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeWaQInput (ms, "WaQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaQInputHelper.type(); 
}
public static byte [] toOctet (WaQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeWaQInput (ms, val, "WaQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
