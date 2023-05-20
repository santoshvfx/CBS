package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiAsmResp_tMsg implements MMarshalObject { 
	public SwitchWsiAsmResp_t value;

	public SwitchWsiAsmResp_tMsg () {
	}
	public SwitchWsiAsmResp_tMsg (SwitchWsiAsmResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchWsiAsmResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchWsiAsmResp_t (ms, tag); 
	}
	static public SwitchWsiAsmResp_t decodeSwitchWsiAsmResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchWsiAsmResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("WsiAsmResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("WsiAsmResp", false);
			{ 
				value.WsiAsmResp = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.WsiAsmResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_tMsg.decodeWsiAsmResp_t (ms, "WsiAsmResp");
				} 
			}
			ms.endArray ("WsiAsmResp", false);
			ms.endSequence ("WsiAsmResp", false);
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
	static public void encodeSwitchWsiAsmResp_t (MMarshalStrategy ms, SwitchWsiAsmResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("WsiAsmResp", true);
			ms.encode (value.WsiAsmResp.length, "_sequenceLength", true);
			ms.startArray ("WsiAsmResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.WsiAsmResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_tMsg.encodeWsiAsmResp_t (ms, value.WsiAsmResp[__i0], "WsiAsmResp");
				} 
			}
			ms.endArray ("WsiAsmResp", true);
			ms.endSequence ("WsiAsmResp", true);
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
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchWsiAsmResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchWsiAsmResp_t (ms, val, "SwitchWsiAsmResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchWsiAsmResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchWsiAsmResp_t (ms, "SwitchWsiAsmResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.WsiAsmResp = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
