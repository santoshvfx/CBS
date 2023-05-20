package com.sbc.gwsvcs.service.toplistener.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOPSendToHostResp_tMsg implements MMarshalObject { 
	public TOPSendToHostResp_t value;

	public TOPSendToHostResp_tMsg () {
	}
	public TOPSendToHostResp_tMsg (TOPSendToHostResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTOPSendToHostResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTOPSendToHostResp_t (ms, tag); 
	}
	static public TOPSendToHostResp_t decodeTOPSendToHostResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TOPSendToHostResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.toplistener.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{
			ms.startSequence ("FCIFData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.FCIFData = ms.decodeOctetString (__seqLength, "FCIFData");
			ms.endSequence ("FCIFData", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTOPSendToHostResp_t (MMarshalStrategy ms, TOPSendToHostResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.toplistener.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{
			ms.startSequence ("FCIFData", true);
			int __seqLength = (value.FCIFData.endsWith("\0")) ? value.FCIFData.length() : value.FCIFData.length() + 1;
			ms.encode(__seqLength, "m_length", true);
			ms.encode (value.FCIFData, __seqLength, "FCIFData");
			ms.endSequence ("FCIFData", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_tHelper.type(); 
	}
	public static byte [] toOctet (TOPSendToHostResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTOPSendToHostResp_t (ms, val, "TOPSendToHostResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TOPSendToHostResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTOPSendToHostResp_t (ms, "TOPSendToHostResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_t create () { 
		com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_t value = new com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostResp_t();
		value.Header = new com.sbc.gwsvcs.service.toplistener.interfaces.Header_t();
		value.FCIFData = new String ();
		return value; 
	} 
}
