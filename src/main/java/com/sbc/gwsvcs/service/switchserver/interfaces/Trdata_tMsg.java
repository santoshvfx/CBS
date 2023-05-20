package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Trdata_tMsg implements MMarshalObject { 
	public Trdata_t value;

	public Trdata_tMsg () {
	}
	public Trdata_tMsg (Trdata_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTrdata_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTrdata_t (ms, tag); 
	}
	static public Trdata_t decodeTrdata_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Trdata_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("TRNSLTN_TAG_CD", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("TRNSLTN_TAG_CD", false);
			{ 
				value.TRNSLTN_TAG_CD = new char[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.TRNSLTN_TAG_CD[__i0] = ms.decodeChar ("TRNSLTN_TAG_CD");
				} 
			}
			ms.endArray ("TRNSLTN_TAG_CD", false);
			ms.endSequence ("TRNSLTN_TAG_CD", false);
		}
		value.INACT_CD = ms.decodeOctetString (2, "INACT_CD");
		value.INVAL_ID = ms.decodeOctetString (13, "INVAL_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTrdata_t (MMarshalStrategy ms, Trdata_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("TRNSLTN_TAG_CD", true);
			ms.encode (value.TRNSLTN_TAG_CD.length, "_sequenceLength", true);
			ms.startArray ("TRNSLTN_TAG_CD", true);
			{ 
				for (int __i0 = 0; __i0 < value.TRNSLTN_TAG_CD.length; __i0++) { 
					ms.encode (value.TRNSLTN_TAG_CD[__i0], "TRNSLTN_TAG_CD");
				} 
			}
			ms.endArray ("TRNSLTN_TAG_CD", true);
			ms.endSequence ("TRNSLTN_TAG_CD", true);
		}
		ms.encode (value.INACT_CD, 2, "INACT_CD");
		ms.encode (value.INVAL_ID, 13, "INVAL_ID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_tHelper.type(); 
	}
	public static byte [] toOctet (Trdata_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTrdata_t (ms, val, "Trdata_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Trdata_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTrdata_t (ms, "Trdata_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t();
		int __seqLength = 0;
		value.TRNSLTN_TAG_CD = new char[__seqLength];
		value.INACT_CD = new String ();
		value.INVAL_ID = new String ();
		return value; 
	} 
}
