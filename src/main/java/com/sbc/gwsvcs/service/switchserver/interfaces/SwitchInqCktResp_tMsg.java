package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqCktResp_tMsg implements MMarshalObject { 
	public SwitchInqCktResp_t value;

	public SwitchInqCktResp_tMsg () {
	}
	public SwitchInqCktResp_tMsg (SwitchInqCktResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqCktResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqCktResp_t (ms, tag); 
	}
	static public SwitchInqCktResp_t decodeSwitchInqCktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqCktResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("InqCktResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("InqCktResp", false);
			{ 
				value.InqCktResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.InqCktResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_tMsg.decodeInqCktResp_t (ms, "InqCktResp");
				} 
			}
			ms.endArray ("InqCktResp", false);
			ms.endSequence ("InqCktResp", false);
		}
		value.ExchKeyId = ms.decodeOctetString (7, "ExchKeyId");
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
	static public void encodeSwitchInqCktResp_t (MMarshalStrategy ms, SwitchInqCktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("InqCktResp", true);
			ms.encode (value.InqCktResp.length, "_sequenceLength", true);
			ms.startArray ("InqCktResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.InqCktResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_tMsg.encodeInqCktResp_t (ms, value.InqCktResp[__i0], "InqCktResp");
				} 
			}
			ms.endArray ("InqCktResp", true);
			ms.endSequence ("InqCktResp", true);
		}
		ms.encode (value.ExchKeyId, 7, "ExchKeyId");
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
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqCktResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqCktResp_t (ms, val, "SwitchInqCktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqCktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqCktResp_t (ms, "SwitchInqCktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.InqCktResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqCktResp_t[__seqLength];
		value.ExchKeyId = new String ();
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
