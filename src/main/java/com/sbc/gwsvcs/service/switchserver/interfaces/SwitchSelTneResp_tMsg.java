package com.sbc.gwsvcs.service.switchserver.interfaces;
import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchSelTneResp_tMsg implements MMarshalObject { 
	public com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t value;
	public SwitchSelTneResp_tMsg() { }
	public SwitchSelTneResp_tMsg (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t init) { value = init; }
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchSelTneResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 

		value = decodeSwitchSelTneResp_t (ms, tag); 
	}
	static public void encodeSwitchSelTneResp_t (MMarshalStrategy ms, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t value, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_tMsg.encodeSwitchSelTnResp_t (ms, value, tag);
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t decodeSwitchSelTneResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t value;
		value = com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_tMsg.decodeSwitchSelTnResp_t (ms, tag);
		return value; 
	}
	public TypeCode getType () {
		return SwitchSelTneResp_tHelper.type();
	}
	public static byte[] toOctet (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchSelTneResp_t(ms, val, "SwitchSelTneResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition());
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t fromOctet (byte [] val) throws MMarshalException {
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy();
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean(null));
			return decodeSwitchSelTneResp_t (ms, "SwitchSelTneResp_t");
		} catch (MBufferException e) {
			throw new MMarshalException ("Buffer error", e);
		}
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_t create() {
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnResp_tMsg.create();
		} 
	}
