// $Id: RdlocQInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RdlocQInputMsg implements MMarshalObject { 
	public RdlocQInput value;

	public RdlocQInputMsg () {
	}
	public RdlocQInputMsg (RdlocQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQInput();
value.ims_region = new String ();
value.location = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRdlocQInput (ms, tag); 
	}
	static public RdlocQInput decodeRdlocQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RdlocQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.location = ms.decodeOctetString (12, "location");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRdlocQInput (ms, value, tag); 
	}
	static public void encodeRdlocQInput (MMarshalStrategy ms, RdlocQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.location, 12, "location");
ms.endStruct (tag, true); 
}
public static RdlocQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeRdlocQInput (ms, "RdlocQInput"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocQInputHelper.type(); 
}
public static byte [] toOctet (RdlocQInput val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeRdlocQInput (ms, val, "RdlocQInput");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
