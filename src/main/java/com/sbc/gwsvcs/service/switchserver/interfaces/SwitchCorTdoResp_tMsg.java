package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchCorTdoResp_tMsg implements MMarshalObject { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t value;
	public SwitchCorTdoResp_tMsg() { }
	public SwitchCorTdoResp_tMsg (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchCorTdoResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeSwitchCorTdoResp_t (ms, tag); 
	}
	static public void encodeSwitchCorTdoResp_t (MMarshalStrategy ms, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t value, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_tMsg.encodeSwitchTdoResp_t (ms, value, tag);
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t decodeSwitchCorTdoResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t value;
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_tMsg.decodeSwitchTdoResp_t (ms, tag);
		return value; 
	}
	public TypeCode getType () {
		return SwitchCorTdoResp_tHelper.type();
	}
	public static byte[] toOctet (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchCorTdoResp_t(ms, val, "SwitchCorTdoResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t fromOctet (byte [] val) throws MMarshalException {
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeSwitchCorTdoResp_t (ms, "SwitchCorTdoResp_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_t create() {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoResp_tMsg.create();
		} 
	}
