package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktRec_tMsg implements MMarshalObject { 
	public CktRec_t value;

	public CktRec_tMsg () {
	}
	public CktRec_tMsg (CktRec_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktRec_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktRec_t (ms, tag); 
	}
	static public CktRec_t decodeCktRec_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktRec_t value = create();
		ms.startStruct (tag, false);
		value.CktRecSvc = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tMsg.decodeCktRecSvc1_t (ms, "CktRecSvc");
		value.CktRecCtl = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tMsg.decodeCktRecCtl_t (ms, "CktRecCtl");
		{ 
			ms.startSequence ("EqpIc", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("EqpIc", false);
			{ 
				value.EqpIc = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.EqpIc[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tMsg.decodeEqpIc_t (ms, "EqpIc");
				} 
			}
			ms.endArray ("EqpIc", false);
			ms.endSequence ("EqpIc", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktRec_t (MMarshalStrategy ms, CktRec_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tMsg.encodeCktRecSvc1_t (ms, value.CktRecSvc, "CktRecSvc");
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tMsg.encodeCktRecCtl_t (ms, value.CktRecCtl, "CktRecCtl");
		{ 
			ms.startSequence ("EqpIc", true);
			ms.encode (value.EqpIc.length, "_sequenceLength", true);
			ms.startArray ("EqpIc", true);
			{ 
				for (int __i0 = 0; __i0 < value.EqpIc.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tMsg.encodeEqpIc_t (ms, value.EqpIc[__i0], "EqpIc");
				} 
			}
			ms.endArray ("EqpIc", true);
			ms.endSequence ("EqpIc", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_tHelper.type(); 
	}
	public static byte [] toOctet (CktRec_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktRec_t (ms, val, "CktRec_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktRec_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktRec_t (ms, "CktRec_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t();
		value.CktRecSvc = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_t();
		value.CktRecCtl = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_t();
		int __seqLength = 0;
		value.EqpIc = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[__seqLength];
		return value; 
	} 
}
