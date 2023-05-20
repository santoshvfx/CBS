package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RtnTnReq_tMsg implements MMarshalObject { 
	public RtnTnReq_t value;

	public RtnTnReq_tMsg () {
	}
	public RtnTnReq_tMsg (RtnTnReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRtnTnReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRtnTnReq_t (ms, tag); 
	}
	static public RtnTnReq_t decodeRtnTnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RtnTnReq_t value = create();
		ms.startStruct (tag, false);
		value.LIST_TYPE = ms.decodeOctetString (2, "LIST_TYPE");
		value.TN_LN_CT = ms.decodeOctetString (4, "TN_LN_CT");
		{ 
			ms.startSequence ("AccpLst", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (5)) {
				throw new MMarshalException("Sequence AccpLst exceeds the bounded size of 5");
				}
			ms.startArray ("AccpLst", false);
			{ 
				value.AccpLst = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.AccpLst[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "AccpLst");
				} 
			}
			ms.endArray ("AccpLst", false);
			ms.endSequence ("AccpLst", false);
		}
		{ 
			ms.startSequence ("RtnLst", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (100)) {
				throw new MMarshalException("Sequence RtnLst exceeds the bounded size of 100");
				}
			ms.startArray ("RtnLst", false);
			{ 
				value.RtnLst = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.RtnLst[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "RtnLst");
				} 
			}
			ms.endArray ("RtnLst", false);
			ms.endSequence ("RtnLst", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeRtnTnReq_t (MMarshalStrategy ms, RtnTnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.LIST_TYPE, 2, "LIST_TYPE");
		ms.encode (value.TN_LN_CT, 4, "TN_LN_CT");
		{ 
			ms.startSequence ("AccpLst", true);
			if (value.AccpLst.length > (5)) {
				throw new MMarshalException("Sequence AccpLst exceeds the bounded size of 5");
				}
			ms.encode (value.AccpLst.length, "_sequenceLength", true);
			ms.startArray ("AccpLst", true);
			{ 
				for (int __i0 = 0; __i0 < value.AccpLst.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.AccpLst[__i0], "AccpLst");
				} 
			}
			ms.endArray ("AccpLst", true);
			ms.endSequence ("AccpLst", true);
		}
		{ 
			ms.startSequence ("RtnLst", true);
			if (value.RtnLst.length > (100)) {
				throw new MMarshalException("Sequence RtnLst exceeds the bounded size of 100");
				}
			ms.encode (value.RtnLst.length, "_sequenceLength", true);
			ms.startArray ("RtnLst", true);
			{ 
				for (int __i0 = 0; __i0 < value.RtnLst.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.RtnLst[__i0], "RtnLst");
				} 
			}
			ms.endArray ("RtnLst", true);
			ms.endSequence ("RtnLst", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_tHelper.type(); 
	}
	public static byte [] toOctet (RtnTnReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeRtnTnReq_t (ms, val, "RtnTnReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static RtnTnReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeRtnTnReq_t (ms, "RtnTnReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t();
		value.LIST_TYPE = new String ();
		value.TN_LN_CT = new String ();
		int __seqLength = 0;
		value.AccpLst = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[__seqLength];
		value.RtnLst = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t[__seqLength];
		return value; 
	} 
}
