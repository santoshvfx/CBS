package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchSelTneReq_tMsg implements MMarshalObject { 
	public SwitchSelTneReq_t value;

	public SwitchSelTneReq_tMsg () {
	}
	public SwitchSelTneReq_tMsg (SwitchSelTneReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchSelTneReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchSelTneReq_t (ms, tag); 
	}
	static public SwitchSelTneReq_t decodeSwitchSelTneReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchSelTneReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.SelTneReq = com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_tMsg.decodeSelTneReq_t (ms, "SelTneReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchSelTneReq_t (MMarshalStrategy ms, SwitchSelTneReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_tMsg.encodeSelTneReq_t (ms, value.SelTneReq, "SelTneReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchSelTneReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchSelTneReq_t (ms, val, "SwitchSelTneReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchSelTneReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchSelTneReq_t (ms, "SwitchSelTneReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTneReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.SelTneReq = new com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
