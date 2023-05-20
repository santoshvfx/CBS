package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SelTnResp_tMsg implements MMarshalObject { 
	public SelTnResp_t value;

	public SelTnResp_tMsg () {
	}
	public SelTnResp_tMsg (SelTnResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSelTnResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSelTnResp_t (ms, tag); 
	}
	static public SelTnResp_t decodeSelTnResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SelTnResp_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("Nbr", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (5)) {
				throw new MMarshalException("Sequence Nbr exceeds the bounded size of 5");
				}
			ms.startArray ("Nbr", false);
			{ 
				value.Nbr = new com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Nbr[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_tMsg.decodeNbr_t (ms, "Nbr");
				} 
			}
			ms.endArray ("Nbr", false);
			ms.endSequence ("Nbr", false);
		}
		value.Lst = com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tMsg.decodeLst_t (ms, "Lst");
		value.REQ_STS_2_CD = ms.decodeOctetString (2, "REQ_STS_2_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSelTnResp_t (MMarshalStrategy ms, SelTnResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("Nbr", true);
			if (value.Nbr.length > (5)) {
				throw new MMarshalException("Sequence Nbr exceeds the bounded size of 5");
				}
			ms.encode (value.Nbr.length, "_sequenceLength", true);
			ms.startArray ("Nbr", true);
			{ 
				for (int __i0 = 0; __i0 < value.Nbr.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_tMsg.encodeNbr_t (ms, value.Nbr[__i0], "Nbr");
				} 
			}
			ms.endArray ("Nbr", true);
			ms.endSequence ("Nbr", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tMsg.encodeLst_t (ms, value.Lst, "Lst");
		ms.encode (value.REQ_STS_2_CD, 2, "REQ_STS_2_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_tHelper.type(); 
	}
	public static byte [] toOctet (SelTnResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSelTnResp_t (ms, val, "SelTnResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SelTnResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSelTnResp_t (ms, "SelTnResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SelTnResp_t();
		int __seqLength = 0;
		value.Nbr = new com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t[__seqLength];
		value.Lst = new com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t();
		value.REQ_STS_2_CD = new String ();
		return value; 
	} 
}
