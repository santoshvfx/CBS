package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqGrpReq_tMsg implements MMarshalObject { 
	public InqGrpReq_t value;

	public InqGrpReq_tMsg () {
	}
	public InqGrpReq_tMsg (InqGrpReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeInqGrpReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeInqGrpReq_t (ms, tag); 
	}
	static public InqGrpReq_t decodeInqGrpReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		InqGrpReq_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("ExGrp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (10)) {
				throw new MMarshalException("Sequence ExGrp exceeds the bounded size of 10");
				}
			ms.startArray ("ExGrp", false);
			{ 
				value.ExGrp = new com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ExGrp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_tMsg.decodeExGrp_t (ms, "ExGrp");
				} 
			}
			ms.endArray ("ExGrp", false);
			ms.endSequence ("ExGrp", false);
		}
		value.FOPTN = ms.decodeOctetString (2, "FOPTN");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeInqGrpReq_t (MMarshalStrategy ms, InqGrpReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("ExGrp", true);
			if (value.ExGrp.length > (10)) {
				throw new MMarshalException("Sequence ExGrp exceeds the bounded size of 10");
				}
			ms.encode (value.ExGrp.length, "_sequenceLength", true);
			ms.startArray ("ExGrp", true);
			{ 
				for (int __i0 = 0; __i0 < value.ExGrp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_tMsg.encodeExGrp_t (ms, value.ExGrp[__i0], "ExGrp");
				} 
			}
			ms.endArray ("ExGrp", true);
			ms.endSequence ("ExGrp", true);
		}
		ms.encode (value.FOPTN, 2, "FOPTN");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_tHelper.type(); 
	}
	public static byte [] toOctet (InqGrpReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeInqGrpReq_t (ms, val, "InqGrpReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static InqGrpReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeInqGrpReq_t (ms, "InqGrpReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_t();
		int __seqLength = 0;
		value.ExGrp = new com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_t[__seqLength];
		value.FOPTN = new String ();
		return value; 
	} 
}
