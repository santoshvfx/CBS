package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Fctr_tMsg implements MMarshalObject { 
	public Fctr_t value;

	public Fctr_tMsg () {
	}
	public Fctr_tMsg (Fctr_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeFctr_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeFctr_t (ms, tag); 
	}
	static public Fctr_t decodeFctr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Fctr_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.RT_ZONE = ms.decodeOctetString (4, "RT_ZONE");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeFctr_t (MMarshalStrategy ms, Fctr_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.RT_ZONE, 4, "RT_ZONE");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_tHelper.type(); 
	}
	public static byte [] toOctet (Fctr_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeFctr_t (ms, val, "Fctr_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Fctr_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeFctr_t (ms, "Fctr_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.RT_ZONE = new String ();
		return value; 
	} 
}
