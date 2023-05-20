package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqAsmReq_tMsg implements MMarshalObject { 
	public SwitchInqAsmReq_t value;

	public SwitchInqAsmReq_tMsg () {
	}
	public SwitchInqAsmReq_tMsg (SwitchInqAsmReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqAsmReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqAsmReq_t (ms, tag); 
	}
	static public SwitchInqAsmReq_t decodeSwitchInqAsmReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqAsmReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.InqAsmReq = com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_tMsg.decodeInqAsmReq_t (ms, "InqAsmReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchInqAsmReq_t (MMarshalStrategy ms, SwitchInqAsmReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_tMsg.encodeInqAsmReq_t (ms, value.InqAsmReq, "InqAsmReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqAsmReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqAsmReq_t (ms, val, "SwitchInqAsmReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqAsmReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqAsmReq_t (ms, "SwitchInqAsmReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqAsmReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.InqAsmReq = new com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
