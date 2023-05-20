package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnInqReq_tMsg implements MMarshalObject { 
	public TnInqReq_t value;

	public TnInqReq_tMsg () {
	}
	public TnInqReq_tMsg (TnInqReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnInqReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnInqReq_t (ms, tag); 
	}
	static public TnInqReq_t decodeTnInqReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnInqReq_t value = create();
		ms.startStruct (tag, false);
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ic");
		{ 
			ms.startSequence ("NPA_PRFX_CD", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (10)) {
				throw new MMarshalException("Sequence NPA_PRFX_CD exceeds the bounded size of 10");
				}
			ms.startArray ("NPA_PRFX_CD", false);
			{ 
				value.NPA_PRFX_CD = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.NPA_PRFX_CD[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tMsg.decodeNpaPrfx_t (ms, "NPA_PRFX_CD");
				} 
			}
			ms.endArray ("NPA_PRFX_CD", false);
			ms.endSequence ("NPA_PRFX_CD", false);
		}
		value.TN_PATT_SELT_OPT = ms.decodeOctetString (5, "TN_PATT_SELT_OPT");
		value.SWITCH_TN_REQ_QTY = ms.decodeOctetString (3, "SWITCH_TN_REQ_QTY");
		value.TN_WORD_PATT = ms.decodeOctetString (8, "TN_WORD_PATT");
		value.EXCL_TN_IND = ms.decodeOctetString (2, "EXCL_TN_IND");
		value.TN_TYPE_CD = ms.decodeOctetString (2, "TN_TYPE_CD");
		value.TN_SPARE_IND = ms.decodeOctetString (2, "TN_SPARE_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTnInqReq_t (MMarshalStrategy ms, TnInqReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ic, "Ic");
		{ 
			ms.startSequence ("NPA_PRFX_CD", true);
			if (value.NPA_PRFX_CD.length > (10)) {
				throw new MMarshalException("Sequence NPA_PRFX_CD exceeds the bounded size of 10");
				}
			ms.encode (value.NPA_PRFX_CD.length, "_sequenceLength", true);
			ms.startArray ("NPA_PRFX_CD", true);
			{ 
				for (int __i0 = 0; __i0 < value.NPA_PRFX_CD.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tMsg.encodeNpaPrfx_t (ms, value.NPA_PRFX_CD[__i0], "NPA_PRFX_CD");
				} 
			}
			ms.endArray ("NPA_PRFX_CD", true);
			ms.endSequence ("NPA_PRFX_CD", true);
		}
		ms.encode (value.TN_PATT_SELT_OPT, 5, "TN_PATT_SELT_OPT");
		ms.encode (value.SWITCH_TN_REQ_QTY, 3, "SWITCH_TN_REQ_QTY");
		ms.encode (value.TN_WORD_PATT, 8, "TN_WORD_PATT");
		ms.encode (value.EXCL_TN_IND, 2, "EXCL_TN_IND");
		ms.encode (value.TN_TYPE_CD, 2, "TN_TYPE_CD");
		ms.encode (value.TN_SPARE_IND, 2, "TN_SPARE_IND");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_tHelper.type(); 
	}
	public static byte [] toOctet (TnInqReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTnInqReq_t (ms, val, "TnInqReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TnInqReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTnInqReq_t (ms, "TnInqReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_t();
		value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		int __seqLength = 0;
		value.NPA_PRFX_CD = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t[__seqLength];
		value.TN_PATT_SELT_OPT = new String ();
		value.SWITCH_TN_REQ_QTY = new String ();
		value.TN_WORD_PATT = new String ();
		value.EXCL_TN_IND = new String ();
		value.TN_TYPE_CD = new String ();
		value.TN_SPARE_IND = new String ();
		return value; 
	} 
}
