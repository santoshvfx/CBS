// $Id: Rc1cicAInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Rc1cicAInputMsg implements MMarshalObject { 
	public Rc1cicAInput value;

	public Rc1cicAInputMsg () {
	}
	public Rc1cicAInputMsg (Rc1cicAInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicAInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicAInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicAInput();
value.ims_region = new String ();
value.cmd = new String ();
value.loca = new String ();
value.locz = new String ();
value.autopost = new String ();
value.tcic = new String ();
value.start = new String ();
value.end = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRc1cicAInput (ms, tag); 
	}
	static public Rc1cicAInput decodeRc1cicAInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Rc1cicAInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.loca = ms.decodeOctetString (12, "loca");
		value.locz = ms.decodeOctetString (12, "locz");
		value.autopost = ms.decodeOctetString (2, "autopost");
		value.tcic = ms.decodeOctetString (6, "tcic");
		value.start = ms.decodeOctetString (6, "start");
		value.end = ms.decodeOctetString (6, "end");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRc1cicAInput (ms, value, tag); 
	}
	static public void encodeRc1cicAInput (MMarshalStrategy ms, Rc1cicAInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.cmd, 9, "cmd");
ms.encode (value.loca, 12, "loca");
ms.encode (value.locz, 12, "locz");
ms.encode (value.autopost, 2, "autopost");
ms.encode (value.tcic, 6, "tcic");
ms.encode (value.start, 6, "start");
ms.encode (value.end, 6, "end");
ms.endStruct (tag, true); 
}
public static Rc1cicAInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRc1cicAInput (ms, "Rc1cicAInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1cicAInputHelper.type(); 
}
public static byte [] toOctet (Rc1cicAInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRc1cicAInput (ms, val, "Rc1cicAInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
