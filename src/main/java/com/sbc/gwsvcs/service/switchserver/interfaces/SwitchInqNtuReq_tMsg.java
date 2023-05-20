package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqNtuReq_tMsg implements MMarshalObject { 
	public SwitchInqNtuReq_t value;

	public SwitchInqNtuReq_tMsg () {
	}
	public SwitchInqNtuReq_tMsg (SwitchInqNtuReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqNtuReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqNtuReq_t (ms, tag); 
	}
	static public SwitchInqNtuReq_t decodeSwitchInqNtuReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqNtuReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.InqNtuReq = com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_tMsg.decodeInqNtuReq_t (ms, "InqNtuReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchInqNtuReq_t (MMarshalStrategy ms, SwitchInqNtuReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_tMsg.encodeInqNtuReq_t (ms, value.InqNtuReq, "InqNtuReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqNtuReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqNtuReq_t (ms, val, "SwitchInqNtuReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqNtuReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqNtuReq_t (ms, "SwitchInqNtuReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqNtuReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.InqNtuReq = new com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
