package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Comp_tMsg implements MMarshalObject { 
	public Comp_t value;

	public Comp_tMsg () {
	}
	public Comp_tMsg (Comp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeComp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeComp_t (ms, tag); 
	}
	static public Comp_t decodeComp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Comp_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID_NM = ms.decodeOctetString (6, "SWITCH_ID_NM");
		value.SPCFC_FUNCLT_CD = ms.decodeOctetString (6, "SPCFC_FUNCLT_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeComp_t (MMarshalStrategy ms, Comp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID_NM, 6, "SWITCH_ID_NM");
		ms.encode (value.SPCFC_FUNCLT_CD, 6, "SPCFC_FUNCLT_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Comp_tHelper.type(); 
	}
	public static byte [] toOctet (Comp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeComp_t (ms, val, "Comp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Comp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeComp_t (ms, "Comp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t();
		value.SWITCH_ID_NM = new String ();
		value.SPCFC_FUNCLT_CD = new String ();
		return value; 
	} 
}
