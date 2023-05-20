package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AsgnmCapyQty_tMsg implements MMarshalObject { 
	public String value;
	public AsgnmCapyQty_tMsg() { }
	public AsgnmCapyQty_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAsgnmCapyQty_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeAsgnmCapyQty_t (ms, tag); 
	}
	static public void encodeAsgnmCapyQty_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 3, tag);
	}
	public static String decodeAsgnmCapyQty_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (3, tag);
		return value; 
	}
	public TypeCode getType () {
		return AsgnmCapyQty_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAsgnmCapyQty_t(ms, val, "AsgnmCapyQty_t");
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
			return decodeAsgnmCapyQty_t (ms, "AsgnmCapyQty_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
