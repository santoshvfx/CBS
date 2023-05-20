// $Id: ZrgrpUInputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrgrpUInputMsg implements MMarshalObject { 
	public ZrgrpUInput value;

	public ZrgrpUInputMsg () {
	}
	public ZrgrpUInputMsg (ZrgrpUInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpUInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpUInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpUInput();
value.ims_region = new String ();
value.gac = new String ();
value.end = new String ();
value.trkeqpttype = new String ();
value.asmtyp = new String ();
value.tcc = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrgrpUInput (ms, tag); 
	}
	static public ZrgrpUInput decodeZrgrpUInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrgrpUInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.gac = ms.decodeOctetString (9, "gac");
		value.end = ms.decodeOctetString (2, "end");
		value.trkeqpttype = ms.decodeOctetString (11, "trkeqpttype");
		value.asmtyp = ms.decodeOctetString (5, "asmtyp");
		value.tcc = ms.decodeOctetString (4, "tcc");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrgrpUInput (ms, value, tag); 
	}
	static public void encodeZrgrpUInput (MMarshalStrategy ms, ZrgrpUInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.gac, 9, "gac");
ms.encode (value.end, 2, "end");
ms.encode (value.trkeqpttype, 11, "trkeqpttype");
ms.encode (value.asmtyp, 5, "asmtyp");
ms.encode (value.tcc, 4, "tcc");
ms.endStruct (tag, true); 
}
public static ZrgrpUInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrgrpUInput (ms, "ZrgrpUInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpUInputHelper.type(); 
}
public static byte [] toOctet (ZrgrpUInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrgrpUInput (ms, val, "ZrgrpUInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
