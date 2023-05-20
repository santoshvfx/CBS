package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdCktReq_tMsg implements MMarshalObject { 
	public SwitchUpdCktReq_t value;

	public SwitchUpdCktReq_tMsg () {
	}
	public SwitchUpdCktReq_tMsg (SwitchUpdCktReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchUpdCktReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchUpdCktReq_t (ms, tag); 
	}
	static public SwitchUpdCktReq_t decodeSwitchUpdCktReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchUpdCktReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("UpdCktReq", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("UpdCktReq", false);
			{ 
				value.UpdCktReq = new com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.UpdCktReq[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_tMsg.decodeUpdCktReq_t (ms, "UpdCktReq");
				} 
			}
			ms.endArray ("UpdCktReq", false);
			ms.endSequence ("UpdCktReq", false);
		}
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchUpdCktReq_t (MMarshalStrategy ms, SwitchUpdCktReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("UpdCktReq", true);
			ms.encode (value.UpdCktReq.length, "_sequenceLength", true);
			ms.startArray ("UpdCktReq", true);
			{ 
				for (int __i0 = 0; __i0 < value.UpdCktReq.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_tMsg.encodeUpdCktReq_t (ms, value.UpdCktReq[__i0], "UpdCktReq");
				} 
			}
			ms.endArray ("UpdCktReq", true);
			ms.endSequence ("UpdCktReq", true);
		}
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchUpdCktReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchUpdCktReq_t (ms, val, "SwitchUpdCktReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchUpdCktReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchUpdCktReq_t (ms, "SwitchUpdCktReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdCktReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.UpdCktReq = new com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_t[__seqLength];
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
