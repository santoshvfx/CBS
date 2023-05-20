package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SvcSeqp_tMsg implements MMarshalObject { 
	public SvcSeqp_t value;

	public SvcSeqp_tMsg () {
	}
	public SvcSeqp_tMsg (SvcSeqp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSvcSeqp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSvcSeqp_t (ms, tag); 
	}
	static public SvcSeqp_t decodeSvcSeqp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SvcSeqp_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID_NM = ms.decodeOctetString (6, "SWITCH_ID_NM");
		value.SWITCH_ID = ms.decodeOctetString (46, "SWITCH_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSvcSeqp_t (MMarshalStrategy ms, SvcSeqp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID_NM, 6, "SWITCH_ID_NM");
		ms.encode (value.SWITCH_ID, 46, "SWITCH_ID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tHelper.type(); 
	}
	public static byte [] toOctet (SvcSeqp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSvcSeqp_t (ms, val, "SvcSeqp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SvcSeqp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSvcSeqp_t (ms, "SvcSeqp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t();
		value.SWITCH_ID_NM = new String ();
		value.SWITCH_ID = new String ();
		return value; 
	} 
}
