package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchQueryCktResp_tMsg implements MMarshalObject { 
	public SwitchQueryCktResp_t value;

	public SwitchQueryCktResp_tMsg () {
	}
	public SwitchQueryCktResp_tMsg (SwitchQueryCktResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchQueryCktResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchQueryCktResp_t (ms, tag); 
	}
	static public SwitchQueryCktResp_t decodeSwitchQueryCktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchQueryCktResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("QueryCktResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("QueryCktResp", false);
			{ 
				value.QueryCktResp = new com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.QueryCktResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_tMsg.decodeQueryCktResp_t (ms, "QueryCktResp");
				} 
			}
			ms.endArray ("QueryCktResp", false);
			ms.endSequence ("QueryCktResp", false);
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
	static public void encodeSwitchQueryCktResp_t (MMarshalStrategy ms, SwitchQueryCktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("QueryCktResp", true);
			ms.encode (value.QueryCktResp.length, "_sequenceLength", true);
			ms.startArray ("QueryCktResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.QueryCktResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_tMsg.encodeQueryCktResp_t (ms, value.QueryCktResp[__i0], "QueryCktResp");
				} 
			}
			ms.endArray ("QueryCktResp", true);
			ms.endSequence ("QueryCktResp", true);
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
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchQueryCktResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchQueryCktResp_t (ms, val, "SwitchQueryCktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchQueryCktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchQueryCktResp_t (ms, "SwitchQueryCktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.QueryCktResp = new com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
