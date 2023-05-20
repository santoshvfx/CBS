package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Lst_tMsg implements MMarshalObject { 
	public Lst_t value;

	public Lst_tMsg () {
	}
	public Lst_tMsg (Lst_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeLst_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeLst_t (ms, tag); 
	}
	static public Lst_t decodeLst_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Lst_t value = create();
		ms.startStruct (tag, false);
		value.LST_ID = ms.decodeOctetString (21, "LST_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeLst_t (MMarshalStrategy ms, Lst_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.LST_ID, 21, "LST_ID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tHelper.type(); 
	}
	public static byte [] toOctet (Lst_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeLst_t (ms, val, "Lst_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Lst_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeLst_t (ms, "Lst_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t();
		value.LST_ID = new String ();
		return value; 
	} 
}
