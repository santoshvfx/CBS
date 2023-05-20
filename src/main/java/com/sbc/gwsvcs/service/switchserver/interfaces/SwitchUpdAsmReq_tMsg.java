package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdAsmReq_tMsg implements MMarshalObject { 
	public SwitchUpdAsmReq_t value;

	public SwitchUpdAsmReq_tMsg () {
	}
	public SwitchUpdAsmReq_tMsg (SwitchUpdAsmReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchUpdAsmReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchUpdAsmReq_t (ms, tag); 
	}
	static public SwitchUpdAsmReq_t decodeSwitchUpdAsmReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchUpdAsmReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.UpdAsmReq = com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_tMsg.decodeUpdAsmReq_t (ms, "UpdAsmReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchUpdAsmReq_t (MMarshalStrategy ms, SwitchUpdAsmReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_tMsg.encodeUpdAsmReq_t (ms, value.UpdAsmReq, "UpdAsmReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchUpdAsmReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchUpdAsmReq_t (ms, val, "SwitchUpdAsmReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchUpdAsmReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchUpdAsmReq_t (ms, "SwitchUpdAsmReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdAsmReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.UpdAsmReq = new com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
