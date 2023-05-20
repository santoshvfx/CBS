package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqCktReq_tMsg implements MMarshalObject { 
	public InqCktReq_t value;

	public InqCktReq_tMsg () {
	}
	public InqCktReq_tMsg (InqCktReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeInqCktReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeInqCktReq_t (ms, tag); 
	}
	static public InqCktReq_t decodeInqCktReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		InqCktReq_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.FOPTN = ms.decodeOctetString (2, "FOPTN");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeInqCktReq_t (MMarshalStrategy ms, InqCktReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.FOPTN, 2, "FOPTN");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_tHelper.type(); 
	}
	public static byte [] toOctet (InqCktReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeInqCktReq_t (ms, val, "InqCktReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static InqCktReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeInqCktReq_t (ms, "InqCktReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqCktReq_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.FOPTN = new String ();
		return value; 
	} 
}
