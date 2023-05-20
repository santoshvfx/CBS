package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class WsiNtuReq_tMsg implements MMarshalObject { 
	public WsiNtuReq_t value;

	public WsiNtuReq_tMsg () {
	}
	public WsiNtuReq_tMsg (WsiNtuReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeWsiNtuReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeWsiNtuReq_t (ms, tag); 
	}
	static public WsiNtuReq_t decodeWsiNtuReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		WsiNtuReq_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.TN_UPD_FCN_CD = ms.decodeOctetString (4, "TN_UPD_FCN_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeWsiNtuReq_t (MMarshalStrategy ms, WsiNtuReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.TN_UPD_FCN_CD, 4, "TN_UPD_FCN_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_tHelper.type(); 
	}
	public static byte [] toOctet (WsiNtuReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeWsiNtuReq_t (ms, val, "WsiNtuReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static WsiNtuReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeWsiNtuReq_t (ms, "WsiNtuReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.WsiNtuReq_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.TN_UPD_FCN_CD = new String ();
		return value; 
	} 
}
