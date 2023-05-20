package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTnUpdReq_tMsg implements MMarshalObject { 
	public SwitchTnUpdReq_t value;

	public SwitchTnUpdReq_tMsg () {
	}
	public SwitchTnUpdReq_tMsg (SwitchTnUpdReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchTnUpdReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchTnUpdReq_t (ms, tag); 
	}
	static public SwitchTnUpdReq_t decodeSwitchTnUpdReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchTnUpdReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("TnUpdReq", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (99)) {
				throw new MMarshalException("Sequence TnUpdReq exceeds the bounded size of 99");
				}
			ms.startArray ("TnUpdReq", false);
			{ 
				value.TnUpdReq = new com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.TnUpdReq[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_tMsg.decodeTnUpdReq_t (ms, "TnUpdReq");
				} 
			}
			ms.endArray ("TnUpdReq", false);
			ms.endSequence ("TnUpdReq", false);
		}
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchTnUpdReq_t (MMarshalStrategy ms, SwitchTnUpdReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("TnUpdReq", true);
			if (value.TnUpdReq.length > (99)) {
				throw new MMarshalException("Sequence TnUpdReq exceeds the bounded size of 99");
				}
			ms.encode (value.TnUpdReq.length, "_sequenceLength", true);
			ms.startArray ("TnUpdReq", true);
			{ 
				for (int __i0 = 0; __i0 < value.TnUpdReq.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_tMsg.encodeTnUpdReq_t (ms, value.TnUpdReq[__i0], "TnUpdReq");
				} 
			}
			ms.endArray ("TnUpdReq", true);
			ms.endSequence ("TnUpdReq", true);
		}
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchTnUpdReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchTnUpdReq_t (ms, val, "SwitchTnUpdReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchTnUpdReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchTnUpdReq_t (ms, "SwitchTnUpdReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnUpdReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.TnUpdReq = new com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t[__seqLength];
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
