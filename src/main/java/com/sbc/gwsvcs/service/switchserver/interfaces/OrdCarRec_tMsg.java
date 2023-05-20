package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class OrdCarRec_tMsg implements MMarshalObject { 
	public OrdCarRec_t value;

	public OrdCarRec_tMsg () {
	}
	public OrdCarRec_tMsg (OrdCarRec_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeOrdCarRec_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeOrdCarRec_t (ms, tag); 
	}
	static public OrdCarRec_t decodeOrdCarRec_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		OrdCarRec_t value = create();
		ms.startStruct (tag, false);
		value.CKT_TERMN_ID = ms.decodeOctetString (52, "CKT_TERMN_ID");
		value.CTRL_CD = ms.decodeOctetString (2, "CTRL_CD");
		{ 
			ms.startSequence ("CarRecAcl", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("CarRecAcl", false);
			{ 
				value.CarRecAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CarRecAcl[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_tMsg.decodeCarRecAcl_t (ms, "CarRecAcl");
				} 
			}
			ms.endArray ("CarRecAcl", false);
			ms.endSequence ("CarRecAcl", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeOrdCarRec_t (MMarshalStrategy ms, OrdCarRec_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CKT_TERMN_ID, 52, "CKT_TERMN_ID");
		ms.encode (value.CTRL_CD, 2, "CTRL_CD");
		{ 
			ms.startSequence ("CarRecAcl", true);
			ms.encode (value.CarRecAcl.length, "_sequenceLength", true);
			ms.startArray ("CarRecAcl", true);
			{ 
				for (int __i0 = 0; __i0 < value.CarRecAcl.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_tMsg.encodeCarRecAcl_t (ms, value.CarRecAcl[__i0], "CarRecAcl");
				} 
			}
			ms.endArray ("CarRecAcl", true);
			ms.endSequence ("CarRecAcl", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tHelper.type(); 
	}
	public static byte [] toOctet (OrdCarRec_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeOrdCarRec_t (ms, val, "OrdCarRec_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static OrdCarRec_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeOrdCarRec_t (ms, "OrdCarRec_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t();
		value.CKT_TERMN_ID = new String ();
		value.CTRL_CD = new String ();
		int __seqLength = 0;
		value.CarRecAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t[__seqLength];
		return value; 
	} 
}
