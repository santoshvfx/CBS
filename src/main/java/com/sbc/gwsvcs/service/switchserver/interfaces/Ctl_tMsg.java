package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Ctl_tMsg implements MMarshalObject { 
	public Ctl_t value;

	public Ctl_tMsg () {
	}
	public Ctl_tMsg (Ctl_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCtl_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCtl_t (ms, tag); 
	}
	static public Ctl_t decodeCtl_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Ctl_t value = create();
		ms.startStruct (tag, false);
		value.SYS_CONN = ms.decodeOctetString (2, "SYS_CONN");
		value.TN_UPD_FCN_CD = ms.decodeOctetString (4, "TN_UPD_FCN_CD");
		value.DIP_IND = ms.decodeOctetString (2, "DIP_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCtl_t (MMarshalStrategy ms, Ctl_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SYS_CONN, 2, "SYS_CONN");
		ms.encode (value.TN_UPD_FCN_CD, 4, "TN_UPD_FCN_CD");
		ms.encode (value.DIP_IND, 2, "DIP_IND");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tHelper.type(); 
	}
	public static byte [] toOctet (Ctl_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCtl_t (ms, val, "Ctl_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Ctl_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCtl_t (ms, "Ctl_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t();
		value.SYS_CONN = new String ();
		value.TN_UPD_FCN_CD = new String ();
		value.DIP_IND = new String ();
		return value; 
	} 
}
