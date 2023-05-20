package com.sbc.gwsvcs.service.toplistener.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TOPSendToHostReq_tMsg implements MMarshalObject { 
	public TOPSendToHostReq_t value;

	public TOPSendToHostReq_tMsg () {
	}
	public TOPSendToHostReq_tMsg (TOPSendToHostReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTOPSendToHostReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTOPSendToHostReq_t (ms, tag); 
	}
	static public TOPSendToHostReq_t decodeTOPSendToHostReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TOPSendToHostReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.toplistener.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		{
			ms.startSequence ("FCIFData", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.FCIFData = ms.decodeOctetString (__seqLength, "FCIFData");
			ms.endSequence ("FCIFData", false);
		}
		{
			ms.startSequence ("DtnName", false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value.DtnName = ms.decodeOctetString (__seqLength, "DtnName");
			ms.endSequence ("DtnName", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTOPSendToHostReq_t (MMarshalStrategy ms, TOPSendToHostReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.toplistener.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		{
			ms.startSequence ("FCIFData", true);
			int __seqLength = (value.FCIFData.endsWith("\0")) ? value.FCIFData.length() : value.FCIFData.length() + 1;
			ms.encode(__seqLength, "m_length", true);
			ms.encode (value.FCIFData, __seqLength, "FCIFData");
			ms.endSequence ("FCIFData", true);
		}
		{
			ms.startSequence ("DtnName", true);
			int __seqLength = (value.DtnName.endsWith("\0")) ? value.DtnName.length() : value.DtnName.length() + 1;
			ms.encode(__seqLength, "m_length", true);
			ms.encode (value.DtnName, __seqLength, "DtnName");
			ms.endSequence ("DtnName", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_tHelper.type(); 
	}
	public static byte [] toOctet (TOPSendToHostReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTOPSendToHostReq_t (ms, val, "TOPSendToHostReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TOPSendToHostReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTOPSendToHostReq_t (ms, "TOPSendToHostReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t create () { 
		com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t value = new com.sbc.gwsvcs.service.toplistener.interfaces.TOPSendToHostReq_t();
		value.Header = new com.sbc.gwsvcs.service.toplistener.interfaces.Header_t();
		value.FCIFData = new String ();
		value.DtnName = new String ();
		return value; 
	} 
}
