package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqOrdReq_tMsg implements MMarshalObject { 
	public SwitchInqOrdReq_t value;

	public SwitchInqOrdReq_tMsg () {
	}
	public SwitchInqOrdReq_tMsg (SwitchInqOrdReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqOrdReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqOrdReq_t (ms, tag); 
	}
	static public SwitchInqOrdReq_t decodeSwitchInqOrdReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqOrdReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.InqOrdReq = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tMsg.decodeInqOrdReq_t (ms, "InqOrdReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchInqOrdReq_t (MMarshalStrategy ms, SwitchInqOrdReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tMsg.encodeInqOrdReq_t (ms, value.InqOrdReq, "InqOrdReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqOrdReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqOrdReq_t (ms, val, "SwitchInqOrdReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqOrdReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqOrdReq_t (ms, "SwitchInqOrdReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.InqOrdReq = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
