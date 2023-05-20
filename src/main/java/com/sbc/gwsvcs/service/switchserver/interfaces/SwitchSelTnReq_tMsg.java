package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchSelTnReq_tMsg implements MMarshalObject { 
	public SwitchSelTnReq_t value;

	public SwitchSelTnReq_tMsg () {
	}
	public SwitchSelTnReq_tMsg (SwitchSelTnReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchSelTnReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchSelTnReq_t (ms, tag); 
	}
	static public SwitchSelTnReq_t decodeSwitchSelTnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchSelTnReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.SelTnReq = com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_tMsg.decodeSelTnReq_t (ms, "SelTnReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchSelTnReq_t (MMarshalStrategy ms, SwitchSelTnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_tMsg.encodeSelTnReq_t (ms, value.SelTnReq, "SelTnReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchSelTnReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchSelTnReq_t (ms, val, "SwitchSelTnReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchSelTnReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchSelTnReq_t (ms, "SwitchSelTnReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchSelTnReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.SelTnReq = new com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
