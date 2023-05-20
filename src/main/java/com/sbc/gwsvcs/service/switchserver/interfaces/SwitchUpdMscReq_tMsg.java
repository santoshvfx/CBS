package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchUpdMscReq_tMsg implements MMarshalObject { 
	public SwitchUpdMscReq_t value;

	public SwitchUpdMscReq_tMsg () {
	}
	public SwitchUpdMscReq_tMsg (SwitchUpdMscReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchUpdMscReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchUpdMscReq_t (ms, tag); 
	}
	static public SwitchUpdMscReq_t decodeSwitchUpdMscReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchUpdMscReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.UpdMscReq = com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_tMsg.decodeUpdMscReq_t (ms, "UpdMscReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchUpdMscReq_t (MMarshalStrategy ms, SwitchUpdMscReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_tMsg.encodeUpdMscReq_t (ms, value.UpdMscReq, "UpdMscReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdMscReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchUpdMscReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchUpdMscReq_t (ms, val, "SwitchUpdMscReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchUpdMscReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchUpdMscReq_t (ms, "SwitchUpdMscReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdMscReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdMscReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchUpdMscReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.UpdMscReq = new com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
