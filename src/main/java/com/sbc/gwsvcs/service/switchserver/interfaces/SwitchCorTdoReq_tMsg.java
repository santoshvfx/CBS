package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchCorTdoReq_tMsg implements MMarshalObject { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value;
	public SwitchCorTdoReq_tMsg() { }
	public SwitchCorTdoReq_tMsg (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchCorTdoReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeSwitchCorTdoReq_t (ms, tag); 
	}
	static public void encodeSwitchCorTdoReq_t (MMarshalStrategy ms, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tMsg.encodeSwitchTdoReq_t (ms, value, tag);
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t decodeSwitchCorTdoReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value;
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tMsg.decodeSwitchTdoReq_t (ms, tag);
		return value; 
	}
	public TypeCode getType () {
		return SwitchCorTdoReq_tHelper.type();
	}
	public static byte[] toOctet (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchCorTdoReq_t(ms, val, "SwitchCorTdoReq_t");
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
			return decodeSwitchCorTdoReq_t (ms, "SwitchCorTdoReq_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t create() {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tMsg.create();
		} 
	}
