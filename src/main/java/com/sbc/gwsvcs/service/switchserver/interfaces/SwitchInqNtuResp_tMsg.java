package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqNtuResp_tMsg implements MMarshalObject { 
	public SwitchInqNtuResp_t value;

	public SwitchInqNtuResp_tMsg () {
	}
	public SwitchInqNtuResp_tMsg (SwitchInqNtuResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqNtuResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqNtuResp_t (ms, tag); 
	}
	static public SwitchInqNtuResp_t decodeSwitchInqNtuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqNtuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("InqNtuResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("InqNtuResp", false);
			{ 
				value.InqNtuResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.InqNtuResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuResp_tMsg.decodeInqNtuResp_t (ms, "InqNtuResp");
				} 
			}
			ms.endArray ("InqNtuResp", false);
			ms.endSequence ("InqNtuResp", false);
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
	static public void encodeSwitchInqNtuResp_t (MMarshalStrategy ms, SwitchInqNtuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("InqNtuResp", true);
			ms.encode (value.InqNtuResp.length, "_sequenceLength", true);
			ms.startArray ("InqNtuResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.InqNtuResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuResp_tMsg.encodeInqNtuResp_t (ms, value.InqNtuResp[__i0], "InqNtuResp");
				} 
			}
			ms.endArray ("InqNtuResp", true);
			ms.endSequence ("InqNtuResp", true);
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
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqNtuResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqNtuResp_t (ms, val, "SwitchInqNtuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqNtuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqNtuResp_t (ms, "SwitchInqNtuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.InqNtuResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuResp_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
