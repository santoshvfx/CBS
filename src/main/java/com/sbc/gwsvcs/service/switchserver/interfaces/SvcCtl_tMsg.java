package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SvcCtl_tMsg implements MMarshalObject { 
	public SvcCtl_t value;

	public SvcCtl_tMsg () {
	}
	public SvcCtl_tMsg (SvcCtl_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSvcCtl_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSvcCtl_t (ms, tag); 
	}
	static public SvcCtl_t decodeSvcCtl_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SvcCtl_t value = create();
		ms.startStruct (tag, false);
		value.TN_UPD_FCN_CD = ms.decodeOctetString (4, "TN_UPD_FCN_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSvcCtl_t (MMarshalStrategy ms, SvcCtl_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TN_UPD_FCN_CD, 4, "TN_UPD_FCN_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_tHelper.type(); 
	}
	public static byte [] toOctet (SvcCtl_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSvcCtl_t (ms, val, "SvcCtl_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SvcCtl_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSvcCtl_t (ms, "SvcCtl_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_t();
		value.TN_UPD_FCN_CD = new String ();
		return value; 
	} 
}
