// $Id: CktsrQInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktsrQInputMsg implements MMarshalObject { 
	public CktsrQInput value;

	public CktsrQInputMsg () {
	}
	public CktsrQInputMsg (CktsrQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrQInput();
value.ims_region = new String ();
value.fmt = new String ();
value.cktid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktsrQInput (ms, tag); 
	}
	static public CktsrQInput decodeCktsrQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktsrQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.number_of_pages = ms.decodeLong ("number_of_pages");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.cktid = ms.decodeOctetString (46, "cktid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktsrQInput (ms, value, tag); 
	}
	static public void encodeCktsrQInput (MMarshalStrategy ms, CktsrQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.number_of_pages, "number_of_pages");
	ms.encode (value.fmt, 2, "fmt");
ms.encode (value.cktid, 46, "cktid");
ms.endStruct (tag, true); 
}
public static CktsrQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCktsrQInput (ms, "CktsrQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrQInputHelper.type(); 
}
public static byte [] toOctet (CktsrQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCktsrQInput (ms, val, "CktsrQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
