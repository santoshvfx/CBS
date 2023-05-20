package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Ntu_tMsg implements MMarshalObject { 
	public Ntu_t value;

	public Ntu_tMsg () {
	}
	public Ntu_tMsg (Ntu_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeNtu_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeNtu_t (ms, tag); 
	}
	static public Ntu_t decodeNtu_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Ntu_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.TN_HI_RNGE_ID = ms.decodeOctetString (25, "TN_HI_RNGE_ID");
		value.TN_PARSE_CD = ms.decodeOctetString (5, "TN_PARSE_CD");
		value.Uattr = com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tMsg.decodeComnUattr_t (ms, "Uattr");
		value.AVDT_IND = ms.decodeOctetString (2, "AVDT_IND");
		value.RTE_IDX = ms.decodeOctetString (5, "RTE_IDX");
		value.Fctr = com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_tMsg.decodeFctr_t (ms, "Fctr");
		value.Memb = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Memb");
		{ 
			ms.startSequence ("Ic", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Ic", false);
			{ 
				value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Ic[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ic");
				} 
			}
			ms.endArray ("Ic", false);
			ms.endSequence ("Ic", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeNtu_t (MMarshalStrategy ms, Ntu_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.TN_HI_RNGE_ID, 25, "TN_HI_RNGE_ID");
		ms.encode (value.TN_PARSE_CD, 5, "TN_PARSE_CD");
		com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tMsg.encodeComnUattr_t (ms, value.Uattr, "Uattr");
		ms.encode (value.AVDT_IND, 2, "AVDT_IND");
		ms.encode (value.RTE_IDX, 5, "RTE_IDX");
		com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_tMsg.encodeFctr_t (ms, value.Fctr, "Fctr");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Memb, "Memb");
		{ 
			ms.startSequence ("Ic", true);
			ms.encode (value.Ic.length, "_sequenceLength", true);
			ms.startArray ("Ic", true);
			{ 
				for (int __i0 = 0; __i0 < value.Ic.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ic[__i0], "Ic");
				} 
			}
			ms.endArray ("Ic", true);
			ms.endSequence ("Ic", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tHelper.type(); 
	}
	public static byte [] toOctet (Ntu_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeNtu_t (ms, val, "Ntu_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Ntu_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeNtu_t (ms, "Ntu_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.TN_HI_RNGE_ID = new String ();
		value.TN_PARSE_CD = new String ();
		value.Uattr = new com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t();
		value.AVDT_IND = new String ();
		value.RTE_IDX = new String ();
		value.Fctr = new com.sbc.gwsvcs.service.switchserver.interfaces.Fctr_t();
		value.Memb = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		int __seqLength = 0;
		value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
		return value; 
	} 
}
