package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Ex_tMsg implements MMarshalObject { 
	public Ex_t value;

	public Ex_tMsg () {
	}
	public Ex_tMsg (Ex_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEx_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEx_t (ms, tag); 
	}
	static public Ex_t decodeEx_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Ex_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID_NM = ms.decodeOctetString (6, "SWITCH_ID_NM");
		value.SWITCH_ID = ms.decodeOctetString (46, "SWITCH_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEx_t (MMarshalStrategy ms, Ex_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID_NM, 6, "SWITCH_ID_NM");
		ms.encode (value.SWITCH_ID, 46, "SWITCH_ID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type(); 
	}
	public static byte [] toOctet (Ex_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEx_t (ms, val, "Ex_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Ex_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEx_t (ms, "Ex_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.SWITCH_ID_NM = new String ();
		value.SWITCH_ID = new String ();
		return value; 
	} 
}
