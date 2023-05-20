package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AsgnmReqInd_tMsg implements MMarshalObject { 
	public String value;
	public AsgnmReqInd_tMsg() { }
	public AsgnmReqInd_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAsgnmReqInd_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeAsgnmReqInd_t (ms, tag); 
	}
	static public void encodeAsgnmReqInd_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 2, tag);
	}
	public static String decodeAsgnmReqInd_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (2, tag);
		return value; 
	}
	public TypeCode getType () {
		return AsgnmReqInd_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAsgnmReqInd_t(ms, val, "AsgnmReqInd_t");
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
			return decodeAsgnmReqInd_t (ms, "AsgnmReqInd_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
