package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Sattr_tMsg implements MMarshalObject { 
	public Sattr_t value;

	public Sattr_tMsg () {
	}
	public Sattr_tMsg (Sattr_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSattr_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSattr_t (ms, tag); 
	}
	static public Sattr_t decodeSattr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Sattr_t value = create();
		ms.startStruct (tag, false);
		value.ORD_3_NBR = ms.decodeOctetString (14, "ORD_3_NBR");
		value.DUE_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "DUE_DT");
		value.TN_RMK_TX = ms.decodeOctetString (61, "TN_RMK_TX");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSattr_t (MMarshalStrategy ms, Sattr_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ORD_3_NBR, 14, "ORD_3_NBR");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.DUE_DT, "DUE_DT");
		ms.encode (value.TN_RMK_TX, 61, "TN_RMK_TX");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_tHelper.type(); 
	}
	public static byte [] toOctet (Sattr_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSattr_t (ms, val, "Sattr_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Sattr_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSattr_t (ms, "Sattr_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_t();
		value.ORD_3_NBR = new String ();
		value.DUE_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.TN_RMK_TX = new String ();
		return value; 
	} 
}
