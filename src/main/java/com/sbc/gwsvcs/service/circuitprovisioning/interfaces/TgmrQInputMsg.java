// $Id: TgmrQInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TgmrQInputMsg implements MMarshalObject { 
	public TgmrQInput value;

	public TgmrQInputMsg () {
	}
	public TgmrQInputMsg (TgmrQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrQInput();
value.ims_region = new String ();
value.groupaccess = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTgmrQInput (ms, tag); 
	}
	static public TgmrQInput decodeTgmrQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TgmrQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.groupaccess = ms.decodeOctetString (9, "groupaccess");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTgmrQInput (ms, value, tag); 
	}
	static public void encodeTgmrQInput (MMarshalStrategy ms, TgmrQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.groupaccess, 9, "groupaccess");
ms.endStruct (tag, true); 
}
public static TgmrQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeTgmrQInput (ms, "TgmrQInput"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.TgmrQInputHelper.type(); 
}
public static byte [] toOctet (TgmrQInput val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeTgmrQInput (ms, val, "TgmrQInput");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
