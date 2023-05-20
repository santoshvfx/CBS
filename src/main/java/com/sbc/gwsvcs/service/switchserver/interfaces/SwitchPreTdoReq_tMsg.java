package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchPreTdoReq_tMsg implements MMarshalObject { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value;
	public SwitchPreTdoReq_tMsg() { }
	public SwitchPreTdoReq_tMsg (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchPreTdoReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeSwitchPreTdoReq_t (ms, tag); 
	}
	static public void encodeSwitchPreTdoReq_t (MMarshalStrategy ms, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tMsg.encodeSwitchTdoReq_t (ms, value, tag);
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t decodeSwitchPreTdoReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value;
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tMsg.decodeSwitchTdoReq_t (ms, tag);
		return value; 
	}
	public TypeCode getType () {
		return SwitchPreTdoReq_tHelper.type();
	}
	public static byte[] toOctet (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchPreTdoReq_t(ms, val, "SwitchPreTdoReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t fromOctet (byte [] val) throws MMarshalException {
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeSwitchPreTdoReq_t (ms, "SwitchPreTdoReq_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t create() {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tMsg.create();
		} 
	}
