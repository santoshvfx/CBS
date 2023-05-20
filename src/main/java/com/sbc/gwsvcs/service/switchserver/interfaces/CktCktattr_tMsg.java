package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktCktattr_tMsg implements MMarshalObject { 
	public CktCktattr_t value;

	public CktCktattr_tMsg () {
	}
	public CktCktattr_tMsg (CktCktattr_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktCktattr_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktCktattr_t (ms, tag); 
	}
	static public CktCktattr_t decodeCktCktattr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktCktattr_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("TN_RMK_TX", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("TN_RMK_TX", false);
			{ 
				value.TN_RMK_TX = new String[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.TN_RMK_TX[__i0] = ms.decodeOctetString (61, "TN_RMK_TX");
				} 
			}
			ms.endArray ("TN_RMK_TX", false);
			ms.endSequence ("TN_RMK_TX", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktCktattr_t (MMarshalStrategy ms, CktCktattr_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("TN_RMK_TX", true);
			ms.encode (value.TN_RMK_TX.length, "_sequenceLength", true);
			ms.startArray ("TN_RMK_TX", true);
			{ 
				for (int __i0 = 0; __i0 < value.TN_RMK_TX.length; __i0++) { 
					ms.encode (value.TN_RMK_TX[__i0], 61, "TN_RMK_TX");
				} 
			}
			ms.endArray ("TN_RMK_TX", true);
			ms.endSequence ("TN_RMK_TX", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tHelper.type(); 
	}
	public static byte [] toOctet (CktCktattr_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktCktattr_t (ms, val, "CktCktattr_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktCktattr_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktCktattr_t (ms, "CktCktattr_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_t();
		int __seqLength = 0;
		value.TN_RMK_TX = new String[__seqLength];
		return value; 
	} 
}
