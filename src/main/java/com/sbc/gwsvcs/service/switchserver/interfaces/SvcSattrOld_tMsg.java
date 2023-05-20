package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SvcSattrOld_tMsg implements MMarshalObject { 
	public SvcSattrOld_t value;

	public SvcSattrOld_tMsg () {
	}
	public SvcSattrOld_tMsg (SvcSattrOld_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSvcSattrOld_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSvcSattrOld_t (ms, tag); 
	}
	static public SvcSattrOld_t decodeSvcSattrOld_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SvcSattrOld_t value = create();
		ms.startStruct (tag, false);
		value.DENY_NPYMT_IND = ms.decodeOctetString (3, "DENY_NPYMT_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSvcSattrOld_t (MMarshalStrategy ms, SvcSattrOld_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.DENY_NPYMT_IND, 3, "DENY_NPYMT_IND");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tHelper.type(); 
	}
	public static byte [] toOctet (SvcSattrOld_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSvcSattrOld_t (ms, val, "SvcSattrOld_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SvcSattrOld_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSvcSattrOld_t (ms, "SvcSattrOld_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_t();
		value.DENY_NPYMT_IND = new String ();
		return value; 
	} 
}
