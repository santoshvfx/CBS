package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiAsmReq_tMsg implements MMarshalObject { 
	public SwitchWsiAsmReq_t value;

	public SwitchWsiAsmReq_tMsg () {
	}
	public SwitchWsiAsmReq_tMsg (SwitchWsiAsmReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchWsiAsmReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchWsiAsmReq_t (ms, tag); 
	}
	static public SwitchWsiAsmReq_t decodeSwitchWsiAsmReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchWsiAsmReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.WsiAsmReq = com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_tMsg.decodeWsiAsmReq_t (ms, "WsiAsmReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchWsiAsmReq_t (MMarshalStrategy ms, SwitchWsiAsmReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_tMsg.encodeWsiAsmReq_t (ms, value.WsiAsmReq, "WsiAsmReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchWsiAsmReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchWsiAsmReq_t (ms, val, "SwitchWsiAsmReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchWsiAsmReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchWsiAsmReq_t (ms, "SwitchWsiAsmReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiAsmReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.WsiAsmReq = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
