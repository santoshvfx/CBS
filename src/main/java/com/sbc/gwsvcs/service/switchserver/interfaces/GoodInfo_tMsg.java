package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GoodInfo_tMsg implements MMarshalObject { 
	public GoodInfo_t value;

	public GoodInfo_tMsg () {
	}
	public GoodInfo_tMsg (GoodInfo_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGoodInfo_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGoodInfo_t (ms, tag); 
	}
	static public GoodInfo_t decodeGoodInfo_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GoodInfo_t value = create();
		ms.startStruct (tag, false);
		value.VANITY_CLS_CD = ms.decodeOctetString (3, "VANITY_CLS_CD");
		value.WORD_TX = ms.decodeOctetString (8, "WORD_TX");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeGoodInfo_t (MMarshalStrategy ms, GoodInfo_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.VANITY_CLS_CD, 3, "VANITY_CLS_CD");
		ms.encode (value.WORD_TX, 8, "WORD_TX");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_tHelper.type(); 
	}
	public static byte [] toOctet (GoodInfo_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeGoodInfo_t (ms, val, "GoodInfo_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static GoodInfo_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeGoodInfo_t (ms, "GoodInfo_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_t();
		value.VANITY_CLS_CD = new String ();
		value.WORD_TX = new String ();
		return value; 
	} 
}
