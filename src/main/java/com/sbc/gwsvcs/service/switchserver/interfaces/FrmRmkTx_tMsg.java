package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class FrmRmkTx_tMsg implements MMarshalObject { 
	public String value;
	public FrmRmkTx_tMsg() { }
	public FrmRmkTx_tMsg (String init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeFrmRmkTx_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeFrmRmkTx_t (ms, tag); 
	}
	static public void encodeFrmRmkTx_t (MMarshalStrategy ms, String value, String tag) throws MMarshalException { 
		ms.encode (value, 29, tag);
	}
	public static String decodeFrmRmkTx_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		String value;
		value = ms.decodeOctetString (29, tag);
		return value; 
	}
	public TypeCode getType () {
		return FrmRmkTx_tHelper.type();
	}
	public static byte[] toOctet (String val) throws MMarshalException { 
		try {
			 com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeFrmRmkTx_t(ms, val, "FrmRmkTx_t");
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
			return decodeFrmRmkTx_t (ms, "FrmRmkTx_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static String create() {
		return new String();
		} 
	}
