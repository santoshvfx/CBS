package com.sbc.gwsvcs.service.toplistener.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOPSendToHostAckResp_tMsg implements MMarshalObject { 
	public TOPSendToHostAckResp_t value;

	public TOPSendToHostAckResp_tMsg () {
	}
	public TOPSendToHostAckResp_tMsg (TOPSendToHostAckResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTOPSendToHostAckResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTOPSendToHostAckResp_t (ms, tag); 
	}
	static public TOPSendToHostAckResp_t decodeTOPSendToHostAckResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TOPSendToHostAckResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.toplistener.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTOPSendToHostAckResp_t (MMarshalStrategy ms, TOPSendToHostAckResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.toplistener.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_tHelper.type(); 
	}
	public static byte [] toOctet (TOPSendToHostAckResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTOPSendToHostAckResp_t (ms, val, "TOPSendToHostAckResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TOPSendToHostAckResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTOPSendToHostAckResp_t (ms, "TOPSendToHostAckResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_t create () { 
		com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_t value = new com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostAckResp_t();
		value.Header = new com.sbc.gwsvcs.service.toplistener.interfaces.Header_t();
		return value; 
	} 
}
