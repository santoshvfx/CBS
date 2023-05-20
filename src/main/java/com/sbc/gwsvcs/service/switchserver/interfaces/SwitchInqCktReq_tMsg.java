package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqCktReq_tMsg implements MMarshalObject { 
	public SwitchInqCktReq_t value;

	public SwitchInqCktReq_tMsg () {
	}
	public SwitchInqCktReq_tMsg (SwitchInqCktReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqCktReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqCktReq_t (ms, tag); 
	}
	static public SwitchInqCktReq_t decodeSwitchInqCktReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqCktReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.InqCktReq = com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_tMsg.decodeInqCktReq_t (ms, "InqCktReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchInqCktReq_t (MMarshalStrategy ms, SwitchInqCktReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_tMsg.encodeInqCktReq_t (ms, value.InqCktReq, "InqCktReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqCktReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqCktReq_t (ms, val, "SwitchInqCktReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqCktReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqCktReq_t (ms, "SwitchInqCktReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqCktReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.InqCktReq = new com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
