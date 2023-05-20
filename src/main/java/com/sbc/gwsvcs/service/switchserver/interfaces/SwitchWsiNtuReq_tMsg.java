package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiNtuReq_tMsg implements MMarshalObject { 
	public SwitchWsiNtuReq_t value;

	public SwitchWsiNtuReq_tMsg () {
	}
	public SwitchWsiNtuReq_tMsg (SwitchWsiNtuReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchWsiNtuReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchWsiNtuReq_t (ms, tag); 
	}
	static public SwitchWsiNtuReq_t decodeSwitchWsiNtuReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchWsiNtuReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.WsiNtuReq = com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_tMsg.decodeWsiNtuReq_t (ms, "WsiNtuReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchWsiNtuReq_t (MMarshalStrategy ms, SwitchWsiNtuReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_tMsg.encodeWsiNtuReq_t (ms, value.WsiNtuReq, "WsiNtuReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchWsiNtuReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchWsiNtuReq_t (ms, val, "SwitchWsiNtuReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchWsiNtuReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchWsiNtuReq_t (ms, "SwitchWsiNtuReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiNtuReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.WsiNtuReq = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
