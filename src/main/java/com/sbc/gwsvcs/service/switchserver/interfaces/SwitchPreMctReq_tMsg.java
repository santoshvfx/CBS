package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchPreMctReq_tMsg implements MMarshalObject { 
	public SwitchPreMctReq_t value;

	public SwitchPreMctReq_tMsg () {
	}
	public SwitchPreMctReq_tMsg (SwitchPreMctReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchPreMctReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchPreMctReq_t (ms, tag); 
	}
	static public SwitchPreMctReq_t decodeSwitchPreMctReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchPreMctReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("PreMctReq", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("PreMctReq", false);
			{ 
				value.PreMctReq = new com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.PreMctReq[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_tMsg.decodePreMctReq_t (ms, "PreMctReq");
				} 
			}
			ms.endArray ("PreMctReq", false);
			ms.endSequence ("PreMctReq", false);
		}
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		value.ORD_2_NBR = ms.decodeOctetString (13, "ORD_2_NBR");
		value.ORD_2_DDT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "ORD_2_DDT");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchPreMctReq_t (MMarshalStrategy ms, SwitchPreMctReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("PreMctReq", true);
			ms.encode (value.PreMctReq.length, "_sequenceLength", true);
			ms.startArray ("PreMctReq", true);
			{ 
				for (int __i0 = 0; __i0 < value.PreMctReq.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_tMsg.encodePreMctReq_t (ms, value.PreMctReq[__i0], "PreMctReq");
				} 
			}
			ms.endArray ("PreMctReq", true);
			ms.endSequence ("PreMctReq", true);
		}
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.encode (value.ORD_2_NBR, 13, "ORD_2_NBR");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.ORD_2_DDT, "ORD_2_DDT");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchPreMctReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchPreMctReq_t (ms, val, "SwitchPreMctReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchPreMctReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchPreMctReq_t (ms, "SwitchPreMctReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.PreMctReq = new com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_t[__seqLength];
		value.SWITCH_WC = new String ();
		value.ORD_2_NBR = new String ();
		value.ORD_2_DDT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		return value; 
	} 
}
