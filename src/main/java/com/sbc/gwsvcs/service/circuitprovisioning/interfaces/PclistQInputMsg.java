// $Id: PclistQInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PclistQInputMsg implements MMarshalObject { 
	public PclistQInput value;

	public PclistQInputMsg () {
	}
	public PclistQInputMsg (PclistQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistQInput();
value.ims_region = new String ();
value.rro = new String ();
value.pos = new String ();
value.uac = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePclistQInput (ms, tag); 
	}
	static public PclistQInput decodePclistQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PclistQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.rro = ms.decodeOctetString (4, "rro");
		value.pos = ms.decodeOctetString (4, "pos");
		value.uac = ms.decodeOctetString (5, "uac");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePclistQInput (ms, value, tag); 
	}
	static public void encodePclistQInput (MMarshalStrategy ms, PclistQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.rro, 4, "rro");
ms.encode (value.pos, 4, "pos");
ms.encode (value.uac, 5, "uac");
ms.endStruct (tag, true); 
}
public static PclistQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodePclistQInput (ms, "PclistQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistQInputHelper.type(); 
}
public static byte [] toOctet (PclistQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodePclistQInput (ms, val, "PclistQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
