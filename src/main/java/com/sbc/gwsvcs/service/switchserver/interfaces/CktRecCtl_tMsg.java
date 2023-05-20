package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktRecCtl_tMsg implements MMarshalObject { 
	public CktRecCtl_t value;

	public CktRecCtl_tMsg () {
	}
	public CktRecCtl_tMsg (CktRecCtl_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktRecCtl_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktRecCtl_t (ms, tag); 
	}
	static public CktRecCtl_t decodeCktRecCtl_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktRecCtl_t value = create();
		ms.startStruct (tag, false);
		value.FND_CKT_IND = ms.decodeOctetString (2, "FND_CKT_IND");
		value.CKT_VIEW_CD = ms.decodeOctetString (9, "CKT_VIEW_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktRecCtl_t (MMarshalStrategy ms, CktRecCtl_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.FND_CKT_IND, 2, "FND_CKT_IND");
		ms.encode (value.CKT_VIEW_CD, 9, "CKT_VIEW_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tHelper.type(); 
	}
	public static byte [] toOctet (CktRecCtl_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktRecCtl_t (ms, val, "CktRecCtl_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktRecCtl_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktRecCtl_t (ms, "CktRecCtl_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_t();
		value.FND_CKT_IND = new String ();
		value.CKT_VIEW_CD = new String ();
		return value; 
	} 
}
