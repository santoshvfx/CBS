// $Id: ZrtdsoAInputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrtdsoAInputMsg implements MMarshalObject { 
	public ZrtdsoAInput value;

	public ZrtdsoAInputMsg () {
	}
	public ZrtdsoAInputMsg (ZrtdsoAInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInput();
value.ims_region = new String ();
value.tablename = new String ();
value.fieldvalue = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrtdsoAInput (ms, tag); 
	}
	static public ZrtdsoAInput decodeZrtdsoAInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrtdsoAInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.tablename = ms.decodeOctetString (17, "tablename");
		value.fieldvalue = ms.decodeOctetString (61, "fieldvalue");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrtdsoAInput (ms, value, tag); 
	}
	static public void encodeZrtdsoAInput (MMarshalStrategy ms, ZrtdsoAInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.tablename, 17, "tablename");
ms.encode (value.fieldvalue, 61, "fieldvalue");
ms.endStruct (tag, true); 
}
public static ZrtdsoAInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrtdsoAInput (ms, "ZrtdsoAInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoAInputHelper.type(); 
}
public static byte [] toOctet (ZrtdsoAInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrtdsoAInput (ms, val, "ZrtdsoAInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
