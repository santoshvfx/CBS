package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchChgAsgReq_tMsg implements MMarshalObject { 
	public SwitchChgAsgReq_t value;

	public SwitchChgAsgReq_tMsg () {
	}
	public SwitchChgAsgReq_tMsg (SwitchChgAsgReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchChgAsgReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchChgAsgReq_t (ms, tag); 
	}
	static public SwitchChgAsgReq_t decodeSwitchChgAsgReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchChgAsgReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("ChgAsgReq", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ChgAsgReq", false);
			{ 
				value.ChgAsgReq = new com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ChgAsgReq[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_tMsg.decodeChgAsgReq_t (ms, "ChgAsgReq");
				} 
			}
			ms.endArray ("ChgAsgReq", false);
			ms.endSequence ("ChgAsgReq", false);
		}
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		value.ORD_2_NBR = ms.decodeOctetString (13, "ORD_2_NBR");
		value.USER_MODE_IND = ms.decodeOctetString (2, "USER_MODE_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchChgAsgReq_t (MMarshalStrategy ms, SwitchChgAsgReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("ChgAsgReq", true);
			ms.encode (value.ChgAsgReq.length, "_sequenceLength", true);
			ms.startArray ("ChgAsgReq", true);
			{ 
				for (int __i0 = 0; __i0 < value.ChgAsgReq.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_tMsg.encodeChgAsgReq_t (ms, value.ChgAsgReq[__i0], "ChgAsgReq");
				} 
			}
			ms.endArray ("ChgAsgReq", true);
			ms.endSequence ("ChgAsgReq", true);
		}
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.encode (value.ORD_2_NBR, 13, "ORD_2_NBR");
		ms.encode (value.USER_MODE_IND, 2, "USER_MODE_IND");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchChgAsgReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchChgAsgReq_t (ms, val, "SwitchChgAsgReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchChgAsgReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchChgAsgReq_t (ms, "SwitchChgAsgReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchChgAsgReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.ChgAsgReq = new com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_t[__seqLength];
		value.SWITCH_WC = new String ();
		value.ORD_2_NBR = new String ();
		value.USER_MODE_IND = new String ();
		return value; 
	} 
}
