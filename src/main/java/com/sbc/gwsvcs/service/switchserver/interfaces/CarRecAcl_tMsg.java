package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CarRecAcl_tMsg implements MMarshalObject { 
	public CarRecAcl_t value;

	public CarRecAcl_tMsg () {
	}
	public CarRecAcl_tMsg (CarRecAcl_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCarRecAcl_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCarRecAcl_t (ms, tag); 
	}
	static public CarRecAcl_t decodeCarRecAcl_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CarRecAcl_t value = create();
		ms.startStruct (tag, false);
		value.CKT_TERMN_ID = ms.decodeOctetString (52, "CKT_TERMN_ID");
		value.ACTN_CD = ms.decodeOctetString (4, "ACTN_CD");
		{ 
			ms.startSequence ("Loop", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Loop", false);
			{ 
				value.Loop = new com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Loop[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Loop_tMsg.decodeLoop_t (ms, "Loop");
				} 
			}
			ms.endArray ("Loop", false);
			ms.endSequence ("Loop", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCarRecAcl_t (MMarshalStrategy ms, CarRecAcl_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CKT_TERMN_ID, 52, "CKT_TERMN_ID");
		ms.encode (value.ACTN_CD, 4, "ACTN_CD");
		{ 
			ms.startSequence ("Loop", true);
			ms.encode (value.Loop.length, "_sequenceLength", true);
			ms.startArray ("Loop", true);
			{ 
				for (int __i0 = 0; __i0 < value.Loop.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Loop_tMsg.encodeLoop_t (ms, value.Loop[__i0], "Loop");
				} 
			}
			ms.endArray ("Loop", true);
			ms.endSequence ("Loop", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_tHelper.type(); 
	}
	public static byte [] toOctet (CarRecAcl_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCarRecAcl_t (ms, val, "CarRecAcl_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CarRecAcl_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCarRecAcl_t (ms, "CarRecAcl_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t();
		value.CKT_TERMN_ID = new String ();
		value.ACTN_CD = new String ();
		int __seqLength = 0;
		value.Loop = new com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t[__seqLength];
		return value; 
	} 
}
