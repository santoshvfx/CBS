package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktEqp_tMsg implements MMarshalObject { 
	public CktEqp_t value;

	public CktEqp_tMsg () {
	}
	public CktEqp_tMsg (CktEqp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktEqp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktEqp_t (ms, tag); 
	}
	static public CktEqp_t decodeCktEqp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktEqp_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID_NM = ms.decodeOctetString (6, "SWITCH_ID_NM");
		value.CktEqpOld = com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_tMsg.decodeCktEqpOld_t (ms, "CktEqpOld");
		value.CktEqpNew = com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_tMsg.decodeCktEqpNew_t (ms, "CktEqpNew");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktEqp_t (MMarshalStrategy ms, CktEqp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID_NM, 6, "SWITCH_ID_NM");
		com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_tMsg.encodeCktEqpOld_t (ms, value.CktEqpOld, "CktEqpOld");
		com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_tMsg.encodeCktEqpNew_t (ms, value.CktEqpNew, "CktEqpNew");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_tHelper.type(); 
	}
	public static byte [] toOctet (CktEqp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktEqp_t (ms, val, "CktEqp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktEqp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktEqp_t (ms, "CktEqp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t();
		value.SWITCH_ID_NM = new String ();
		value.CktEqpOld = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpOld_t();
		value.CktEqpNew = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqpNew_t();
		return value; 
	} 
}
