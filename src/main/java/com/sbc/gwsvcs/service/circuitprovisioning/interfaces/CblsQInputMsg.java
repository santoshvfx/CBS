// $Id: CblsQInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CblsQInputMsg implements MMarshalObject { 
	public CblsQInput value;

	public CblsQInputMsg () {
	}
	public CblsQInputMsg (CblsQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQInput();
value.ims_region = new String ();
value.term_A = new String ();
value.term_Z = new String ();
value.cable = new String ();
value.from_unit = new String ();
value.last_unit = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCblsQInput (ms, tag); 
	}
	static public CblsQInput decodeCblsQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CblsQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.term_A = ms.decodeOctetString (12, "term_A");
		value.term_Z = ms.decodeOctetString (12, "term_Z");
		value.cable = ms.decodeOctetString (11, "cable");
		value.from_unit = ms.decodeOctetString (6, "from_unit");
		value.last_unit = ms.decodeOctetString (6, "last_unit");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCblsQInput (ms, value, tag); 
	}
	static public void encodeCblsQInput (MMarshalStrategy ms, CblsQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.term_A, 12, "term_A");
ms.encode (value.term_Z, 12, "term_Z");
ms.encode (value.cable, 11, "cable");
ms.encode (value.from_unit, 6, "from_unit");
ms.encode (value.last_unit, 6, "last_unit");
ms.endStruct (tag, true); 
}
public static CblsQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCblsQInput (ms, "CblsQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsQInputHelper.type(); 
}
public static byte [] toOctet (CblsQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCblsQInput (ms, val, "CblsQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
