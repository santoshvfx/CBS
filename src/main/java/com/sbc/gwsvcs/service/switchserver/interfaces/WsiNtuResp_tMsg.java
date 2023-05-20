package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiNtuResp_tMsg implements MMarshalObject { 
	public WsiNtuResp_t value;

	public WsiNtuResp_tMsg () {
	}
	public WsiNtuResp_tMsg (WsiNtuResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeWsiNtuResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeWsiNtuResp_t (ms, tag); 
	}
	static public WsiNtuResp_t decodeWsiNtuResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		WsiNtuResp_t value = create();
		ms.startStruct (tag, false);
		value.INTRCPT_CD = ms.decodeOctetString (4, "INTRCPT_CD");
		value.Ntu = com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tMsg.decodeNtu_t (ms, "Ntu");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeWsiNtuResp_t (MMarshalStrategy ms, WsiNtuResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.INTRCPT_CD, 4, "INTRCPT_CD");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tMsg.encodeNtu_t (ms, value.Ntu, "Ntu");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_tHelper.type(); 
	}
	public static byte [] toOctet (WsiNtuResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeWsiNtuResp_t (ms, val, "WsiNtuResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static WsiNtuResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeWsiNtuResp_t (ms, "WsiNtuResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuResp_t();
		value.INTRCPT_CD = new String ();
		value.Ntu = new com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t();
		return value; 
	} 
}
