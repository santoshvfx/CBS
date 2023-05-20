package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqCktResp_tMsg implements MMarshalObject { 
	public InqCktResp_t value;

	public InqCktResp_tMsg () {
	}
	public InqCktResp_tMsg (InqCktResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeInqCktResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeInqCktResp_t (ms, tag); 
	}
	static public InqCktResp_t decodeInqCktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		InqCktResp_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.OTPT_LN_QTY = ms.decodeOctetString (5, "OTPT_LN_QTY");
		{ 
			ms.startSequence ("OTPT_LN_DESC_TX", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("OTPT_LN_DESC_TX", false);
			{ 
				value.OTPT_LN_DESC_TX = new String[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.OTPT_LN_DESC_TX[__i0] = ms.decodeOctetString (81, "OTPT_LN_DESC_TX");
				} 
			}
			ms.endArray ("OTPT_LN_DESC_TX", false);
			ms.endSequence ("OTPT_LN_DESC_TX", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeInqCktResp_t (MMarshalStrategy ms, InqCktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.OTPT_LN_QTY, 5, "OTPT_LN_QTY");
		{ 
			ms.startSequence ("OTPT_LN_DESC_TX", true);
			ms.encode (value.OTPT_LN_DESC_TX.length, "_sequenceLength", true);
			ms.startArray ("OTPT_LN_DESC_TX", true);
			{ 
				for (int __i0 = 0; __i0 < value.OTPT_LN_DESC_TX.length; __i0++) { 
					ms.encode (value.OTPT_LN_DESC_TX[__i0], 81, "OTPT_LN_DESC_TX");
				} 
			}
			ms.endArray ("OTPT_LN_DESC_TX", true);
			ms.endSequence ("OTPT_LN_DESC_TX", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_tHelper.type(); 
	}
	public static byte [] toOctet (InqCktResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeInqCktResp_t (ms, val, "InqCktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static InqCktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeInqCktResp_t (ms, "InqCktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.OTPT_LN_QTY = new String ();
		int __seqLength = 0;
		value.OTPT_LN_DESC_TX = new String[__seqLength];
		return value; 
	} 
}
