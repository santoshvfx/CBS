package com.sbc.gwsvcs.service.toplistener.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class FCIFData_tMsg implements MMarshalObject { 
	public String value;
	public FCIFData_tMsg() { }
	public FCIFData_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeFCIFData_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeFCIFData_t (ms, tag); 
	}
	static public void encodeFCIFData_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		{
			ms.startSequence (tag, true);
			int __seqLength = (value.endsWith("\0")) ? value.length() : value.length() + 1;
			ms.encode(__seqLength, "m_length", true);
			ms.encode (value, __seqLength, tag);
			ms.endSequence (tag, true);
		}
	}
	public static String decodeFCIFData_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		{
			ms.startSequence (tag, false);
			int __seqLength = ms.decodeULong ("m_length", true);
			value = ms.decodeOctetString (__seqLength, tag);
			ms.endSequence (tag, false);
		}
		return value; 
	}
	public TypeCode getType () {
		return FCIFData_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeFCIFData_t(ms, val, "FCIFData_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String fromOctet (byte [] val) throws MMarshalException {
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeFCIFData_t (ms, "FCIFData_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
