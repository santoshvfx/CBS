// $Id: ZrxlocQInputMsg.java,v 1.1 2002/09/29 04:10:31 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrxlocQInputMsg implements MMarshalObject { 
	public ZrxlocQInput value;

	public ZrxlocQInputMsg () {
	}
	public ZrxlocQInputMsg (ZrxlocQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocQInput();
value.ims_region = new String ();
value.location = new String ();
value.pc_network = new String ();
value.pc_cluster = new String ();
value.pc_point = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrxlocQInput (ms, tag); 
	}
	static public ZrxlocQInput decodeZrxlocQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrxlocQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.location = ms.decodeOctetString (12, "location");
		value.pc_network = ms.decodeOctetString (4, "pc_network");
		value.pc_cluster = ms.decodeOctetString (4, "pc_cluster");
		value.pc_point = ms.decodeOctetString (4, "pc_point");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrxlocQInput (ms, value, tag); 
	}
	static public void encodeZrxlocQInput (MMarshalStrategy ms, ZrxlocQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.location, 12, "location");
ms.encode (value.pc_network, 4, "pc_network");
ms.encode (value.pc_cluster, 4, "pc_cluster");
ms.encode (value.pc_point, 4, "pc_point");
ms.endStruct (tag, true); 
}
public static ZrxlocQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrxlocQInput (ms, "ZrxlocQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocQInputHelper.type(); 
}
public static byte [] toOctet (ZrxlocQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrxlocQInput (ms, val, "ZrxlocQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
