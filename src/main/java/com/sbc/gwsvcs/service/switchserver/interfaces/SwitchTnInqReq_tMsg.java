package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTnInqReq_tMsg implements MMarshalObject { 
	public SwitchTnInqReq_t value;

	public SwitchTnInqReq_tMsg () {
	}
	public SwitchTnInqReq_tMsg (SwitchTnInqReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchTnInqReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchTnInqReq_t (ms, tag); 
	}
	static public SwitchTnInqReq_t decodeSwitchTnInqReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchTnInqReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.TnInqReq = com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_tMsg.decodeTnInqReq_t (ms, "TnInqReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchTnInqReq_t (MMarshalStrategy ms, SwitchTnInqReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_tMsg.encodeTnInqReq_t (ms, value.TnInqReq, "TnInqReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchTnInqReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchTnInqReq_t (ms, val, "SwitchTnInqReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchTnInqReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchTnInqReq_t (ms, "SwitchTnInqReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTnInqReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.TnInqReq = new com.sbc.gwsvcs.service.switchserver.interfaces.TnInqReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
