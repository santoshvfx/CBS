package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Indicator_tMsg implements MMarshalObject { 
	public char value;
	public Indicator_tMsg() { }
	public Indicator_tMsg (char init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeIndicator_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeIndicator_t (ms, tag); 
	}
	static public void encodeIndicator_t (MMarshalStrategy ms, char value, String tag) throws MMarshalException { 
		ms.encode (value, tag);
	}
	public static char decodeIndicator_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		char value;
		value = ms.decodeChar (tag);
		return value; 
	}
	public TypeCode getType () {
		return Indicator_tHelper.type();
	}
	public static byte[] toOctet (char val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeIndicator_t(ms, val, "Indicator_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static char fromOctet (byte [] val) throws MMarshalException {
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeIndicator_t (ms, "Indicator_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static char create() {
		char ret = (char) 0;
		return ret;
		} 
	}
