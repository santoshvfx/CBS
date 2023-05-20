package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqOrdReq_tMsg implements MMarshalObject { 
	public InqOrdReq_t value;

	public InqOrdReq_tMsg () {
	}
	public InqOrdReq_tMsg (InqOrdReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeInqOrdReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeInqOrdReq_t (ms, tag); 
	}
	static public InqOrdReq_t decodeInqOrdReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		InqOrdReq_t value = create();
		ms.startStruct (tag, false);
		value.ORD_2_NBR = ms.decodeOctetString (13, "ORD_2_NBR");
		{ 
			ms.startSequence ("InqOrdEx", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("InqOrdEx", false);
			{ 
				value.InqOrdEx = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.InqOrdEx[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tMsg.decodeInqOrdEx_t (ms, "InqOrdEx");
				} 
			}
			ms.endArray ("InqOrdEx", false);
			ms.endSequence ("InqOrdEx", false);
		}
		value.FOPTN = ms.decodeOctetString (2, "FOPTN");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeInqOrdReq_t (MMarshalStrategy ms, InqOrdReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ORD_2_NBR, 13, "ORD_2_NBR");
		{ 
			ms.startSequence ("InqOrdEx", true);
			ms.encode (value.InqOrdEx.length, "_sequenceLength", true);
			ms.startArray ("InqOrdEx", true);
			{ 
				for (int __i0 = 0; __i0 < value.InqOrdEx.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tMsg.encodeInqOrdEx_t (ms, value.InqOrdEx[__i0], "InqOrdEx");
				} 
			}
			ms.endArray ("InqOrdEx", true);
			ms.endSequence ("InqOrdEx", true);
		}
		ms.encode (value.FOPTN, 2, "FOPTN");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tHelper.type(); 
	}
	public static byte [] toOctet (InqOrdReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeInqOrdReq_t (ms, val, "InqOrdReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static InqOrdReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeInqOrdReq_t (ms, "InqOrdReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t();
		value.ORD_2_NBR = new String ();
		int __seqLength = 0;
		value.InqOrdEx = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t[__seqLength];
		value.FOPTN = new String ();
		return value; 
	} 
}
