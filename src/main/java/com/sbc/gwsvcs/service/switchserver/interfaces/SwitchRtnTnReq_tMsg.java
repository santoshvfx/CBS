package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchRtnTnReq_tMsg implements MMarshalObject { 
	public SwitchRtnTnReq_t value;

	public SwitchRtnTnReq_tMsg () {
	}
	public SwitchRtnTnReq_tMsg (SwitchRtnTnReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchRtnTnReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchRtnTnReq_t (ms, tag); 
	}
	static public SwitchRtnTnReq_t decodeSwitchRtnTnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchRtnTnReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.RtnTnReq = com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_tMsg.decodeRtnTnReq_t (ms, "RtnTnReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchRtnTnReq_t (MMarshalStrategy ms, SwitchRtnTnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_tMsg.encodeRtnTnReq_t (ms, value.RtnTnReq, "RtnTnReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchRtnTnReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchRtnTnReq_t (ms, val, "SwitchRtnTnReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchRtnTnReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchRtnTnReq_t (ms, "SwitchRtnTnReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRtnTnReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.RtnTnReq = new com.sbc.gwsvcs.service.switchserver.interfaces.RtnTnReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
