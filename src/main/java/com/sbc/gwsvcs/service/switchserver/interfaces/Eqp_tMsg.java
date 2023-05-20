package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Eqp_tMsg implements MMarshalObject { 
	public Eqp_t value;

	public Eqp_tMsg () {
	}
	public Eqp_tMsg (Eqp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqp_t (ms, tag); 
	}
	static public Eqp_t decodeEqp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Eqp_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID_NM = ms.decodeOctetString (6, "SWITCH_ID_NM");
		value.EqpOld = com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_tMsg.decodeEqpOld_t (ms, "EqpOld");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEqp_t (MMarshalStrategy ms, Eqp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID_NM, 6, "SWITCH_ID_NM");
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_tMsg.encodeEqpOld_t (ms, value.EqpOld, "EqpOld");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_tHelper.type(); 
	}
	public static byte [] toOctet (Eqp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEqp_t (ms, val, "Eqp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Eqp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEqp_t (ms, "Eqp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t();
		value.SWITCH_ID_NM = new String ();
		value.EqpOld = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t();
		return value; 
	} 
}
