package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqOrdResp_tMsg implements MMarshalObject { 
	public SwitchInqOrdResp_t value;

	public SwitchInqOrdResp_tMsg () {
	}
	public SwitchInqOrdResp_tMsg (SwitchInqOrdResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqOrdResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqOrdResp_t (ms, tag); 
	}
	static public SwitchInqOrdResp_t decodeSwitchInqOrdResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqOrdResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("InqOrdResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("InqOrdResp", false);
			{ 
				value.InqOrdResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.InqOrdResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_tMsg.decodeInqOrdResp_t (ms, "InqOrdResp");
				} 
			}
			ms.endArray ("InqOrdResp", false);
			ms.endSequence ("InqOrdResp", false);
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
	static public void encodeSwitchInqOrdResp_t (MMarshalStrategy ms, SwitchInqOrdResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("InqOrdResp", true);
			ms.encode (value.InqOrdResp.length, "_sequenceLength", true);
			ms.startArray ("InqOrdResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.InqOrdResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_tMsg.encodeInqOrdResp_t (ms, value.InqOrdResp[__i0], "InqOrdResp");
				} 
			}
			ms.endArray ("InqOrdResp", true);
			ms.endSequence ("InqOrdResp", true);
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
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqOrdResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqOrdResp_t (ms, val, "SwitchInqOrdResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqOrdResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqOrdResp_t (ms, "SwitchInqOrdResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.InqOrdResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdResp_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
