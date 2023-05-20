// $Id: CxrsQInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CxrsQInputMsg implements MMarshalObject { 
	public CxrsQInput value;

	public CxrsQInputMsg () {
	}
	public CxrsQInputMsg (CxrsQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQInput();
value.ims_region = new String ();
value.term_A = new String ();
value.term_Z = new String ();
value.fac_des = new String ();
value.fac_typ = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCxrsQInput (ms, tag); 
	}
	static public CxrsQInput decodeCxrsQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CxrsQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.term_A = ms.decodeOctetString (12, "term_A");
		value.term_Z = ms.decodeOctetString (12, "term_Z");
		value.fac_des = ms.decodeOctetString (6, "fac_des");
		value.fac_typ = ms.decodeOctetString (7, "fac_typ");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCxrsQInput (ms, value, tag); 
	}
	static public void encodeCxrsQInput (MMarshalStrategy ms, CxrsQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.term_A, 12, "term_A");
ms.encode (value.term_Z, 12, "term_Z");
ms.encode (value.fac_des, 6, "fac_des");
ms.encode (value.fac_typ, 7, "fac_typ");
ms.endStruct (tag, true); 
}
public static CxrsQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCxrsQInput (ms, "CxrsQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsQInputHelper.type(); 
}
public static byte [] toOctet (CxrsQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCxrsQInput (ms, val, "CxrsQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
