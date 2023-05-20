package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTnInqResp_tMsg implements MMarshalObject { 
	public SwitchTnInqResp_t value;

	public SwitchTnInqResp_tMsg () {
	}
	public SwitchTnInqResp_tMsg (SwitchTnInqResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchTnInqResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchTnInqResp_t (ms, tag); 
	}
	static public SwitchTnInqResp_t decodeSwitchTnInqResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchTnInqResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("TnInqResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("TnInqResp", false);
			{ 
				value.TnInqResp = new com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.TnInqResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_tMsg.decodeTnInqResp_t (ms, "TnInqResp");
				} 
			}
			ms.endArray ("TnInqResp", false);
			ms.endSequence ("TnInqResp", false);
		}
		{ 
			ms.startSequence ("Umsg", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Umsg", false);
			{ 
				value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Umsg[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tMsg.decodeUmsg_t (ms, "Umsg");
				} 
			}
			ms.endArray ("Umsg", false);
			ms.endSequence ("Umsg", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchTnInqResp_t (MMarshalStrategy ms, SwitchTnInqResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("TnInqResp", true);
			ms.encode (value.TnInqResp.length, "_sequenceLength", true);
			ms.startArray ("TnInqResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.TnInqResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_tMsg.encodeTnInqResp_t (ms, value.TnInqResp[__i0], "TnInqResp");
				} 
			}
			ms.endArray ("TnInqResp", true);
			ms.endSequence ("TnInqResp", true);
		}
		{ 
			ms.startSequence ("Umsg", true);
			ms.encode (value.Umsg.length, "_sequenceLength", true);
			ms.startArray ("Umsg", true);
			{ 
				for (int __i0 = 0; __i0 < value.Umsg.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tMsg.encodeUmsg_t (ms, value.Umsg[__i0], "Umsg");
				} 
			}
			ms.endArray ("Umsg", true);
			ms.endSequence ("Umsg", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchTnInqResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchTnInqResp_t (ms, val, "SwitchTnInqResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchTnInqResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchTnInqResp_t (ms, "SwitchTnInqResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.TnInqResp = new com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
