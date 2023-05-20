package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RTARNG_tMsg implements MMarshalObject { 
	public RTARNG_t value;

	public RTARNG_tMsg () {
	}
	public RTARNG_tMsg (RTARNG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRTARNG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRTARNG_t (ms, tag); 
	}
	static public RTARNG_t decodeRTARNG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RTARNG_t value = create();
		ms.startStruct (tag, false);
		value.RTANO = ms.decodeOctetString (3, "RTANO");
		value.RTA = ms.decodeOctetString (51, "RTA");
		value.RLC = ms.decodeOctetString (12, "RLC");
		value.RMK = ms.decodeOctetString (51, "RMK");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeRTARNG_t (MMarshalStrategy ms, RTARNG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RTANO, 3, "RTANO");
		ms.encode (value.RTA, 51, "RTA");
		ms.encode (value.RLC, 12, "RLC");
		ms.encode (value.RMK, 51, "RMK");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_tHelper.type(); 
	}
	public static byte [] toOctet (RTARNG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeRTARNG_t (ms, val, "RTARNG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static RTARNG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeRTARNG_t (ms, "RTARNG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.RTARNG_t();
		value.RTANO = new String ();
		value.RTA = new String ();
		value.RLC = new String ();
		value.RMK = new String ();
		return value; 
	} 
}
