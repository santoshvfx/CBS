// $Id: EqpuQInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpuQInputMsg implements MMarshalObject { 
	public EqpuQInput value;

	public EqpuQInputMsg () {
	}
	public EqpuQInputMsg (EqpuQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuQInput();
value.ims_region = new String ();
value.location = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpuQInput (ms, tag); 
	}
	static public EqpuQInput decodeEqpuQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpuQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.location = ms.decodeOctetString (12, "location");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpuQInput (ms, value, tag); 
	}
	static public void encodeEqpuQInput (MMarshalStrategy ms, EqpuQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.location, 12, "location");
ms.endStruct (tag, true); 
}
public static EqpuQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeEqpuQInput (ms, "EqpuQInput"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuQInputHelper.type(); 
}
public static byte [] toOctet (EqpuQInput val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeEqpuQInput (ms, val, "EqpuQInput");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
