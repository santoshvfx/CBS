package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchQueryCktReq_tMsg implements MMarshalObject { 
	public SwitchQueryCktReq_t value;

	public SwitchQueryCktReq_tMsg () {
	}
	public SwitchQueryCktReq_tMsg (SwitchQueryCktReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchQueryCktReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchQueryCktReq_t (ms, tag); 
	}
	static public SwitchQueryCktReq_t decodeSwitchQueryCktReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchQueryCktReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		value.QueryCktReq = com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktReq_tMsg.decodeQueryCktReq_t (ms, "QueryCktReq");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchQueryCktReq_t (MMarshalStrategy ms, SwitchQueryCktReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktReq_tMsg.encodeQueryCktReq_t (ms, value.QueryCktReq, "QueryCktReq");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchQueryCktReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchQueryCktReq_t (ms, val, "SwitchQueryCktReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchQueryCktReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchQueryCktReq_t (ms, "SwitchQueryCktReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchQueryCktReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.SWITCH_WC = new String ();
		value.QueryCktReq = new com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktReq_t();
		return value; 
	} 
}
