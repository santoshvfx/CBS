package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ENGMNU_tMsg implements MMarshalObject { 
	public ENGMNU_t value;

	public ENGMNU_tMsg () {
	}
	public ENGMNU_tMsg (ENGMNU_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeENGMNU_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeENGMNU_t (ms, tag); 
	}
	static public ENGMNU_t decodeENGMNU_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ENGMNU_t value = create();
		ms.startStruct (tag, false);
		value.TID = ms.decodeOctetString (7, "TID");
		value.WC = ms.decodeOctetString (9, "WC");
		value.EWO = ms.decodeOctetString (13, "EWO");
		value.TR = ms.decodeOctetString (8, "TR");
		value.EMP = ms.decodeOctetString (8, "EMP");
		value.FUNC = ms.decodeOctetString (9, "FUNC");
		value.OBJ = ms.decodeOctetString (9, "OBJ");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeENGMNU_t (MMarshalStrategy ms, ENGMNU_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TID, 7, "TID");
		ms.encode (value.WC, 9, "WC");
		ms.encode (value.EWO, 13, "EWO");
		ms.encode (value.TR, 8, "TR");
		ms.encode (value.EMP, 8, "EMP");
		ms.encode (value.FUNC, 9, "FUNC");
		ms.encode (value.OBJ, 9, "OBJ");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_tHelper.type(); 
	}
	public static byte [] toOctet (ENGMNU_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeENGMNU_t (ms, val, "ENGMNU_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ENGMNU_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeENGMNU_t (ms, "ENGMNU_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.ENGMNU_t();
		value.TID = new String ();
		value.WC = new String ();
		value.EWO = new String ();
		value.TR = new String ();
		value.EMP = new String ();
		value.FUNC = new String ();
		value.OBJ = new String ();
		return value; 
	} 
}
