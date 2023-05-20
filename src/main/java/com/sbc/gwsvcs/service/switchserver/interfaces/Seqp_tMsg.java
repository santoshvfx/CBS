package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Seqp_tMsg implements MMarshalObject { 
	public Seqp_t value;

	public Seqp_tMsg () {
	}
	public Seqp_tMsg (Seqp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSeqp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSeqp_t (ms, tag); 
	}
	static public Seqp_t decodeSeqp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Seqp_t value = create();
		ms.startStruct (tag, false);
		value.SeqpNew = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "SeqpNew");
		value.SWITCH_ID_NM = ms.decodeOctetString (6, "SWITCH_ID_NM");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSeqp_t (MMarshalStrategy ms, Seqp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.SeqpNew, "SeqpNew");
		ms.encode (value.SWITCH_ID_NM, 6, "SWITCH_ID_NM");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_tHelper.type(); 
	}
	public static byte [] toOctet (Seqp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSeqp_t (ms, val, "Seqp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Seqp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSeqp_t (ms, "Seqp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t();
		value.SeqpNew = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.SWITCH_ID_NM = new String ();
		return value; 
	} 
}
