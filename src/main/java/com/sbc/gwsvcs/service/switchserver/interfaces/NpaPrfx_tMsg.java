package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class NpaPrfx_tMsg implements MMarshalObject { 
	public NpaPrfx_t value;

	public NpaPrfx_tMsg () {
	}
	public NpaPrfx_tMsg (NpaPrfx_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeNpaPrfx_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeNpaPrfx_t (ms, tag); 
	}
	static public NpaPrfx_t decodeNpaPrfx_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		NpaPrfx_t value = create();
		ms.startStruct (tag, false);
		value.NPA = ms.decodeOctetString (4, "NPA");
		value.PRFX_CD = ms.decodeOctetString (4, "PRFX_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeNpaPrfx_t (MMarshalStrategy ms, NpaPrfx_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.NPA, 4, "NPA");
		ms.encode (value.PRFX_CD, 4, "PRFX_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tHelper.type(); 
	}
	public static byte [] toOctet (NpaPrfx_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeNpaPrfx_t (ms, val, "NpaPrfx_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static NpaPrfx_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeNpaPrfx_t (ms, "NpaPrfx_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t();
		value.NPA = new String ();
		value.PRFX_CD = new String ();
		return value; 
	} 
}
