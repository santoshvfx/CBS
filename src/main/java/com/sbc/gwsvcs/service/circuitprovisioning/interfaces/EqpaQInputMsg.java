// $Id: EqpaQInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpaQInputMsg implements MMarshalObject { 
	public EqpaQInput value;

	public EqpaQInputMsg () {
	}
	public EqpaQInputMsg (EqpaQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaQInput();
value.ims_region = new String ();
value.location = new String ();
value.equip_code = new String ();
value.level = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpaQInput (ms, tag); 
	}
	static public EqpaQInput decodeEqpaQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpaQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.location = ms.decodeOctetString (12, "location");
		value.equip_code = ms.decodeOctetString (15, "equip_code");
		value.level = ms.decodeOctetString (2, "level");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpaQInput (ms, value, tag); 
	}
	static public void encodeEqpaQInput (MMarshalStrategy ms, EqpaQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.location, 12, "location");
ms.encode (value.equip_code, 15, "equip_code");
ms.encode (value.level, 2, "level");
ms.endStruct (tag, true); 
}
public static EqpaQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpaQInput (ms, "EqpaQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaQInputHelper.type(); 
}
public static byte [] toOctet (EqpaQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpaQInput (ms, val, "EqpaQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
