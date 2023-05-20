package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Asglim_tMsg implements MMarshalObject { 
	public Asglim_t value;

	public Asglim_tMsg () {
	}
	public Asglim_tMsg (Asglim_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAsglim_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAsglim_t (ms, tag); 
	}
	static public Asglim_t decodeAsglim_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Asglim_t value = create();
		ms.startStruct (tag, false);
		value.TN_LIM_VALU_CD = ms.decodeOctetString (4, "TN_LIM_VALU_CD");
		value.TN_LIM_TYPE_CD = ms.decodeOctetString (4, "TN_LIM_TYPE_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeAsglim_t (MMarshalStrategy ms, Asglim_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TN_LIM_VALU_CD, 4, "TN_LIM_VALU_CD");
		ms.encode (value.TN_LIM_TYPE_CD, 4, "TN_LIM_TYPE_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tHelper.type(); 
	}
	public static byte [] toOctet (Asglim_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAsglim_t (ms, val, "Asglim_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Asglim_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAsglim_t (ms, "Asglim_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t();
		value.TN_LIM_VALU_CD = new String ();
		value.TN_LIM_TYPE_CD = new String ();
		return value; 
	} 
}
