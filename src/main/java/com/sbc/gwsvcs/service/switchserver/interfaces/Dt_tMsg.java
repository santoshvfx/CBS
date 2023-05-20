package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Dt_tMsg implements MMarshalObject { 
	public Dt_t value;

	public Dt_tMsg () {
	}
	public Dt_tMsg (Dt_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDt_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDt_t (ms, tag); 
	}
	static public Dt_t decodeDt_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Dt_t value = create();
		ms.startStruct (tag, false);
		value.YR = ms.decodeOctetString (5, "YR");
		value.MO = ms.decodeOctetString (3, "MO");
		value.DY = ms.decodeOctetString (3, "DY");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeDt_t (MMarshalStrategy ms, Dt_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.YR, 5, "YR");
		ms.encode (value.MO, 3, "MO");
		ms.encode (value.DY, 3, "DY");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type(); 
	}
	public static byte [] toOctet (Dt_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeDt_t (ms, val, "Dt_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Dt_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeDt_t (ms, "Dt_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.YR = new String ();
		value.MO = new String ();
		value.DY = new String ();
		return value; 
	} 
}
