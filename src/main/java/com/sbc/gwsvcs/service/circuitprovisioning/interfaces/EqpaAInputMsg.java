// $Id: EqpaAInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpaAInputMsg implements MMarshalObject { 
	public EqpaAInput value;

	public EqpaAInputMsg () {
	}
	public EqpaAInputMsg (EqpaAInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaAInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaAInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaAInput();
value.ims_region = new String ();
value.location = new String ();
value.equip_code = new String ();
value.level = new String ();
value.activity = new String ();
value.clo1 = new String ();
value.clo2 = new String ();
value.clo3 = new String ();
value.ddmon = new String ();
value.ddday = new String ();
value.ddyr = new String ();
value.cktid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpaAInput (ms, tag); 
	}
	static public EqpaAInput decodeEqpaAInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpaAInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.location = ms.decodeOctetString (12, "location");
		value.equip_code = ms.decodeOctetString (15, "equip_code");
		value.level = ms.decodeOctetString (2, "level");
		value.activity = ms.decodeOctetString (2, "activity");
		value.clo1 = ms.decodeOctetString (10, "clo1");
		value.clo2 = ms.decodeOctetString (4, "clo2");
		value.clo3 = ms.decodeOctetString (2, "clo3");
		value.ddmon = ms.decodeOctetString (3, "ddmon");
		value.ddday = ms.decodeOctetString (3, "ddday");
		value.ddyr = ms.decodeOctetString (3, "ddyr");
		value.cktid = ms.decodeOctetString (53, "cktid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpaAInput (ms, value, tag); 
	}
	static public void encodeEqpaAInput (MMarshalStrategy ms, EqpaAInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.location, 12, "location");
ms.encode (value.equip_code, 15, "equip_code");
ms.encode (value.level, 2, "level");
ms.encode (value.activity, 2, "activity");
ms.encode (value.clo1, 10, "clo1");
ms.encode (value.clo2, 4, "clo2");
ms.encode (value.clo3, 2, "clo3");
ms.encode (value.ddmon, 3, "ddmon");
ms.encode (value.ddday, 3, "ddday");
ms.encode (value.ddyr, 3, "ddyr");
ms.encode (value.cktid, 53, "cktid");
ms.endStruct (tag, true); 
}
public static EqpaAInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpaAInput (ms, "EqpaAInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaAInputHelper.type(); 
}
public static byte [] toOctet (EqpaAInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpaAInput (ms, val, "EqpaAInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
