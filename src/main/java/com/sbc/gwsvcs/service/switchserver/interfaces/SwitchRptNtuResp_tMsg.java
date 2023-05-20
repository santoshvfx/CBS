package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchRptNtuResp_tMsg implements MMarshalObject { 
	public SwitchRptNtuResp_t value;

	public SwitchRptNtuResp_tMsg () {
	}
	public SwitchRptNtuResp_tMsg (SwitchRptNtuResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchRptNtuResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchRptNtuResp_t (ms, tag); 
	}
	static public SwitchRptNtuResp_t decodeSwitchRptNtuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchRptNtuResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
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
		{ 
			ms.startSequence ("PrtRec", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("PrtRec", false);
			{ 
				value.PrtRec = new com.sbc.gwsvcs.service.switchserver.interfaces.PrtRec_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.PrtRec[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.PrtRec_tMsg.decodePrtRec_t (ms, "PrtRec");
				} 
			}
			ms.endArray ("PrtRec", false);
			ms.endSequence ("PrtRec", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchRptNtuResp_t (MMarshalStrategy ms, SwitchRptNtuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
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
		{ 
			ms.startSequence ("PrtRec", true);
			ms.encode (value.PrtRec.length, "_sequenceLength", true);
			ms.startArray ("PrtRec", true);
			{ 
				for (int __i0 = 0; __i0 < value.PrtRec.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.PrtRec_tMsg.encodePrtRec_t (ms, value.PrtRec[__i0], "PrtRec");
				} 
			}
			ms.endArray ("PrtRec", true);
			ms.endSequence ("PrtRec", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchRptNtuResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchRptNtuResp_t (ms, val, "SwitchRptNtuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchRptNtuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchRptNtuResp_t (ms, "SwitchRptNtuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		value.PrtRec = new com.sbc.gwsvcs.service.switchserver.interfaces.PrtRec_t[__seqLength];
		return value; 
	} 
}
