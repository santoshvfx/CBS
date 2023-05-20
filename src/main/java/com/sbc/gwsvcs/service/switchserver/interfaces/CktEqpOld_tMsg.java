package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktEqpOld_tMsg implements MMarshalObject { 
	public CktEqpOld_t value;

	public CktEqpOld_tMsg () {
	}
	public CktEqpOld_tMsg (CktEqpOld_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktEqpOld_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktEqpOld_t (ms, tag); 
	}
	static public CktEqpOld_t decodeCktEqpOld_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktEqpOld_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID = ms.decodeOctetString (46, "SWITCH_ID");
		value.RT_ZONE_NBR = ms.decodeOctetString (4, "RT_ZONE_NBR");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktEqpOld_t (MMarshalStrategy ms, CktEqpOld_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID, 46, "SWITCH_ID");
		ms.encode (value.RT_ZONE_NBR, 4, "RT_ZONE_NBR");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_tHelper.type(); 
	}
	public static byte [] toOctet (CktEqpOld_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktEqpOld_t (ms, val, "CktEqpOld_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktEqpOld_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktEqpOld_t (ms, "CktEqpOld_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_t();
		value.SWITCH_ID = new String ();
		value.RT_ZONE_NBR = new String ();
		return value; 
	} 
}
