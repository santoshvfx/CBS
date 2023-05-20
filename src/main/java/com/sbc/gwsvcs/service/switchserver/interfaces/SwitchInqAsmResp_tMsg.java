package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqAsmResp_tMsg implements MMarshalObject { 
	public SwitchInqAsmResp_t value;

	public SwitchInqAsmResp_tMsg () {
	}
	public SwitchInqAsmResp_tMsg (SwitchInqAsmResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqAsmResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqAsmResp_t (ms, tag); 
	}
	static public SwitchInqAsmResp_t decodeSwitchInqAsmResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqAsmResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("InqAsmResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("InqAsmResp", false);
			{ 
				value.InqAsmResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.InqAsmResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmResp_tMsg.decodeInqAsmResp_t (ms, "InqAsmResp");
				} 
			}
			ms.endArray ("InqAsmResp", false);
			ms.endSequence ("InqAsmResp", false);
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
	static public void encodeSwitchInqAsmResp_t (MMarshalStrategy ms, SwitchInqAsmResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("InqAsmResp", true);
			ms.encode (value.InqAsmResp.length, "_sequenceLength", true);
			ms.startArray ("InqAsmResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.InqAsmResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmResp_tMsg.encodeInqAsmResp_t (ms, value.InqAsmResp[__i0], "InqAsmResp");
				} 
			}
			ms.endArray ("InqAsmResp", true);
			ms.endSequence ("InqAsmResp", true);
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
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqAsmResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqAsmResp_t (ms, val, "SwitchInqAsmResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqAsmResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqAsmResp_t (ms, "SwitchInqAsmResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.InqAsmResp = new com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmResp_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
