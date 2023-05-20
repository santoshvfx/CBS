package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqOrdEx_tMsg implements MMarshalObject { 
	public InqOrdEx_t value;

	public InqOrdEx_tMsg () {
	}
	public InqOrdEx_tMsg (InqOrdEx_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeInqOrdEx_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeInqOrdEx_t (ms, tag); 
	}
	static public InqOrdEx_t decodeInqOrdEx_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		InqOrdEx_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeInqOrdEx_t (MMarshalStrategy ms, InqOrdEx_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tHelper.type(); 
	}
	public static byte [] toOctet (InqOrdEx_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeInqOrdEx_t (ms, val, "InqOrdEx_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static InqOrdEx_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeInqOrdEx_t (ms, "InqOrdEx_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		return value; 
	} 
}
