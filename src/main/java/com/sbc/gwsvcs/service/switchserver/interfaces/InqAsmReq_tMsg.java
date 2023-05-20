package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqAsmReq_tMsg implements MMarshalObject { 
	public InqAsmReq_t value;

	public InqAsmReq_tMsg () {
	}
	public InqAsmReq_tMsg (InqAsmReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeInqAsmReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeInqAsmReq_t (ms, tag); 
	}
	static public InqAsmReq_t decodeInqAsmReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		InqAsmReq_t value = create();
		ms.startStruct (tag, false);
		value.NTU = ms.decodeOctetString (3, "NTU");
		value.LO = ms.decodeOctetString (16, "LO");
		value.HI = ms.decodeOctetString (16, "HI");
		value.FOPTN = ms.decodeOctetString (2, "FOPTN");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeInqAsmReq_t (MMarshalStrategy ms, InqAsmReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.NTU, 3, "NTU");
		ms.encode (value.LO, 16, "LO");
		ms.encode (value.HI, 16, "HI");
		ms.encode (value.FOPTN, 2, "FOPTN");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_tHelper.type(); 
	}
	public static byte [] toOctet (InqAsmReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeInqAsmReq_t (ms, val, "InqAsmReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static InqAsmReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeInqAsmReq_t (ms, "InqAsmReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqAsmReq_t();
		value.NTU = new String ();
		value.LO = new String ();
		value.HI = new String ();
		value.FOPTN = new String ();
		return value; 
	} 
}
