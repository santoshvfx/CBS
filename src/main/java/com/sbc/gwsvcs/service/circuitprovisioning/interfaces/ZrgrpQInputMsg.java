// $Id: ZrgrpQInputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrgrpQInputMsg implements MMarshalObject { 
	public ZrgrpQInput value;

	public ZrgrpQInputMsg () {
	}
	public ZrgrpQInputMsg (ZrgrpQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpQInput();
value.ims_region = new String ();
value.gac = new String ();
value.end = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrgrpQInput (ms, tag); 
	}
	static public ZrgrpQInput decodeZrgrpQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrgrpQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.gac = ms.decodeOctetString (9, "gac");
		value.end = ms.decodeOctetString (2, "end");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrgrpQInput (ms, value, tag); 
	}
	static public void encodeZrgrpQInput (MMarshalStrategy ms, ZrgrpQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.gac, 9, "gac");
ms.encode (value.end, 2, "end");
ms.endStruct (tag, true); 
}
public static ZrgrpQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrgrpQInput (ms, "ZrgrpQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpQInputHelper.type(); 
}
public static byte [] toOctet (ZrgrpQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrgrpQInput (ms, val, "ZrgrpQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
