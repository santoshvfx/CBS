// $Id: EqpscQInputMsg.java,v 1.2 2002/09/29 04:13:00 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpscQInputMsg implements MMarshalObject { 
	public EqpscQInput value;

	public EqpscQInputMsg () {
	}
	public EqpscQInputMsg (EqpscQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQInput();
value.ims_region = new String ();
value.location = new String ();
value.equip_code = new String ();
value.level = new String ();
value.relayrack = new String ();
value.unit = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpscQInput (ms, tag); 
	}
	static public EqpscQInput decodeEqpscQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpscQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.location = ms.decodeOctetString (12, "location");
		value.equip_code = ms.decodeOctetString (15, "equip_code");
		value.level = ms.decodeOctetString (2, "level");
		value.relayrack = ms.decodeOctetString (11, "relayrack");
		value.unit = ms.decodeOctetString (7, "unit");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpscQInput (ms, value, tag); 
	}
	static public void encodeEqpscQInput (MMarshalStrategy ms, EqpscQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.location, 12, "location");
ms.encode (value.equip_code, 15, "equip_code");
ms.encode (value.level, 2, "level");
ms.encode (value.relayrack, 11, "relayrack");
ms.encode (value.unit, 7, "unit");
ms.endStruct (tag, true); 
}
public static EqpscQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpscQInput (ms, "EqpscQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscQInputHelper.type(); 
}
public static byte [] toOctet (EqpscQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpscQInput (ms, val, "EqpscQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
