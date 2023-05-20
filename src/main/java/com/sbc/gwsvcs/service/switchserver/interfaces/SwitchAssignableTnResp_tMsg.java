package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchAssignableTnResp_tMsg implements MMarshalObject { 
	public SwitchAssignableTnResp_t value;

	public SwitchAssignableTnResp_tMsg () {
	}
	public SwitchAssignableTnResp_tMsg (SwitchAssignableTnResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchAssignableTnResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchAssignableTnResp_t (ms, tag); 
	}
	static public SwitchAssignableTnResp_t decodeSwitchAssignableTnResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchAssignableTnResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{ 
			ms.startSequence ("AssignableTnResp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("AssignableTnResp", false);
			{ 
				value.AssignableTnResp = new com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.AssignableTnResp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_tMsg.decodeAssignableTnResp_t (ms, "AssignableTnResp");
				} 
			}
			ms.endArray ("AssignableTnResp", false);
			ms.endSequence ("AssignableTnResp", false);
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
	static public void encodeSwitchAssignableTnResp_t (MMarshalStrategy ms, SwitchAssignableTnResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{ 
			ms.startSequence ("AssignableTnResp", true);
			ms.encode (value.AssignableTnResp.length, "_sequenceLength", true);
			ms.startArray ("AssignableTnResp", true);
			{ 
				for (int __i0 = 0; __i0 < value.AssignableTnResp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_tMsg.encodeAssignableTnResp_t (ms, value.AssignableTnResp[__i0], "AssignableTnResp");
				} 
			}
			ms.endArray ("AssignableTnResp", true);
			ms.endSequence ("AssignableTnResp", true);
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
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchAssignableTnResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchAssignableTnResp_t (ms, val, "SwitchAssignableTnResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchAssignableTnResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchAssignableTnResp_t (ms, "SwitchAssignableTnResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		int __seqLength = 0;
		value.AssignableTnResp = new com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
