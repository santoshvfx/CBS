package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktEqpNew_tMsg implements MMarshalObject { 
	public CktEqpNew_t value;

	public CktEqpNew_tMsg () {
	}
	public CktEqpNew_tMsg (CktEqpNew_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktEqpNew_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktEqpNew_t (ms, tag); 
	}
	static public CktEqpNew_t decodeCktEqpNew_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktEqpNew_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID = ms.decodeOctetString (46, "SWITCH_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktEqpNew_t (MMarshalStrategy ms, CktEqpNew_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID, 46, "SWITCH_ID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_tHelper.type(); 
	}
	public static byte [] toOctet (CktEqpNew_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktEqpNew_t (ms, val, "CktEqpNew_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktEqpNew_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktEqpNew_t (ms, "CktEqpNew_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_t();
		value.SWITCH_ID = new String ();
		return value; 
	} 
}
