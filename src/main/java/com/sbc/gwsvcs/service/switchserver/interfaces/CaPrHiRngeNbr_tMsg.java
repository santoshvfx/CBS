package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CaPrHiRngeNbr_tMsg implements MMarshalObject { 
	public String value;
	public CaPrHiRngeNbr_tMsg() { }
	public CaPrHiRngeNbr_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCaPrHiRngeNbr_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeCaPrHiRngeNbr_t (ms, tag); 
	}
	static public void encodeCaPrHiRngeNbr_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 16, tag);
	}
	public static String decodeCaPrHiRngeNbr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (16, tag);
		return value; 
	}
	public TypeCode getType () {
		return CaPrHiRngeNbr_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCaPrHiRngeNbr_t(ms, val, "CaPrHiRngeNbr_t");
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
			return decodeCaPrHiRngeNbr_t (ms, "CaPrHiRngeNbr_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
