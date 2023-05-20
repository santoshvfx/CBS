package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DsgnOld_tMsg implements MMarshalObject { 
	public DsgnOld_t value;

	public DsgnOld_tMsg () {
	}
	public DsgnOld_tMsg (DsgnOld_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDsgnOld_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDsgnOld_t (ms, tag); 
	}
	static public DsgnOld_t decodeDsgnOld_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DsgnOld_t value = create();
		ms.startStruct (tag, false);
		value.CTGY_CD = ms.decodeOctetString (2, "CTGY_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeDsgnOld_t (MMarshalStrategy ms, DsgnOld_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CTGY_CD, 2, "CTGY_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_tHelper.type(); 
	}
	public static byte [] toOctet (DsgnOld_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeDsgnOld_t (ms, val, "DsgnOld_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static DsgnOld_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeDsgnOld_t (ms, "DsgnOld_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_t();
		value.CTGY_CD = new String ();
		return value; 
	} 
}
