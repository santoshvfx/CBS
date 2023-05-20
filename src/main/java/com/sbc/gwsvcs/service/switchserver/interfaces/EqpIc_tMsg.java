package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpIc_tMsg implements MMarshalObject { 
	public EqpIc_t value;

	public EqpIc_tMsg () {
	}
	public EqpIc_tMsg (EqpIc_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpIc_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpIc_t (ms, tag); 
	}
	static public EqpIc_t decodeEqpIc_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpIc_t value = create();
		ms.startStruct (tag, false);
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ic");
		value.DLCT_CD = ms.decodeOctetString (5, "DLCT_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEqpIc_t (MMarshalStrategy ms, EqpIc_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ic, "Ic");
		ms.encode (value.DLCT_CD, 5, "DLCT_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tHelper.type(); 
	}
	public static byte [] toOctet (EqpIc_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEqpIc_t (ms, val, "EqpIc_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EqpIc_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEqpIc_t (ms, "EqpIc_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t();
		value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.DLCT_CD = new String ();
		return value; 
	} 
}
