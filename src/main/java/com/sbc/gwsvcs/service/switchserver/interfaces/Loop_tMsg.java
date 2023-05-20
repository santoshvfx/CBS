package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Loop_tMsg implements MMarshalObject { 
	public Loop_t value;

	public Loop_tMsg () {
	}
	public Loop_tMsg (Loop_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLoop_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLoop_t (ms, tag); 
	}
	static public Loop_t decodeLoop_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Loop_t value = create();
		ms.startStruct (tag, false);
		value.RT_ZONE_NBR = ms.decodeOctetString (4, "RT_ZONE_NBR");
		value.CBL_NBR = ms.decodeOctetString (11, "CBL_NBR");
		value.PR_NBR = ms.decodeOctetString (6, "PR_NBR");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeLoop_t (MMarshalStrategy ms, Loop_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.RT_ZONE_NBR, 4, "RT_ZONE_NBR");
		ms.encode (value.CBL_NBR, 11, "CBL_NBR");
		ms.encode (value.PR_NBR, 6, "PR_NBR");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Loop_tHelper.type(); 
	}
	public static byte [] toOctet (Loop_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeLoop_t (ms, val, "Loop_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Loop_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeLoop_t (ms, "Loop_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t();
		value.RT_ZONE_NBR = new String ();
		value.CBL_NBR = new String ();
		value.PR_NBR = new String ();
		return value; 
	} 
}
