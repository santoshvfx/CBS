package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiAsmReq_tMsg implements MMarshalObject { 
	public WsiAsmReq_t value;

	public WsiAsmReq_tMsg () {
	}
	public WsiAsmReq_tMsg (WsiAsmReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeWsiAsmReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeWsiAsmReq_t (ms, tag); 
	}
	static public WsiAsmReq_t decodeWsiAsmReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		WsiAsmReq_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.SWITCH_TYPE_CD = ms.decodeOctetString (6, "SWITCH_TYPE_CD");
		value.TN_UPD_FCN_CD = ms.decodeOctetString (4, "TN_UPD_FCN_CD");
		value.SELT_CD = ms.decodeOctetString (2, "SELT_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeWsiAsmReq_t (MMarshalStrategy ms, WsiAsmReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.SWITCH_TYPE_CD, 6, "SWITCH_TYPE_CD");
		ms.encode (value.TN_UPD_FCN_CD, 4, "TN_UPD_FCN_CD");
		ms.encode (value.SELT_CD, 2, "SELT_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_tHelper.type(); 
	}
	public static byte [] toOctet (WsiAsmReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeWsiAsmReq_t (ms, val, "WsiAsmReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static WsiAsmReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeWsiAsmReq_t (ms, "WsiAsmReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiAsmReq_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.SWITCH_TYPE_CD = new String ();
		value.TN_UPD_FCN_CD = new String ();
		value.SELT_CD = new String ();
		return value; 
	} 
}
