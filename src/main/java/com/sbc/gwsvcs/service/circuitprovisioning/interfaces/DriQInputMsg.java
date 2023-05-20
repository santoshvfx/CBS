// $Id: DriQInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DriQInputMsg implements MMarshalObject { 
	public DriQInput value;

	public DriQInputMsg () {
	}
	public DriQInputMsg (DriQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInput();
value.ims_region = new String ();
value.clo = new String ();
value.ckt = new String ();
value.cac = new String ();
value.ord = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDriQInput (ms, tag); 
	}
	static public DriQInput decodeDriQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DriQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.clo = ms.decodeOctetString (14, "clo");
		value.ckt = ms.decodeOctetString (48, "ckt");
		value.cac = ms.decodeOctetString (9, "cac");
		value.ord = ms.decodeOctetString (22, "ord");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDriQInput (ms, value, tag); 
	}
	static public void encodeDriQInput (MMarshalStrategy ms, DriQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.clo, 14, "clo");
ms.encode (value.ckt, 48, "ckt");
ms.encode (value.cac, 9, "cac");
ms.encode (value.ord, 22, "ord");
ms.endStruct (tag, true); 
}
public static DriQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeDriQInput (ms, "DriQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriQInputHelper.type(); 
}
public static byte [] toOctet (DriQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeDriQInput (ms, val, "DriQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
