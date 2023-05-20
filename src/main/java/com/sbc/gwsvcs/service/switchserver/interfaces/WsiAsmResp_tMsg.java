package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiAsmResp_tMsg implements MMarshalObject { 
	public WsiAsmResp_t value;

	public WsiAsmResp_tMsg () {
	}
	public WsiAsmResp_tMsg (WsiAsmResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeWsiAsmResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeWsiAsmResp_t (ms, tag); 
	}
	static public WsiAsmResp_t decodeWsiAsmResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		WsiAsmResp_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeWsiAsmResp_t (MMarshalStrategy ms, WsiAsmResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_tHelper.type(); 
	}
	public static byte [] toOctet (WsiAsmResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeWsiAsmResp_t (ms, val, "WsiAsmResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static WsiAsmResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeWsiAsmResp_t (ms, "WsiAsmResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmResp_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		return value; 
	} 
}
