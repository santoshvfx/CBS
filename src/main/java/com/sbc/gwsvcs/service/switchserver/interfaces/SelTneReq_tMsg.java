package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SelTneReq_tMsg implements MMarshalObject { 
	public SelTneReq_t value;

	public SelTneReq_tMsg () {
	}
	public SelTneReq_tMsg (SelTneReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSelTneReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSelTneReq_t (ms, tag); 
	}
	static public SelTneReq_t decodeSelTneReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SelTneReq_t value = create();
		ms.startStruct (tag, false);
		value.REQ_CTGY_CD = ms.decodeOctetString (11, "REQ_CTGY_CD");
		value.SeltVarb = com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_tMsg.decodeTneSeltVarb_t (ms, "SeltVarb");
		value.Lst = com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tMsg.decodeLst_t (ms, "Lst");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSelTneReq_t (MMarshalStrategy ms, SelTneReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.REQ_CTGY_CD, 11, "REQ_CTGY_CD");
		com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_tMsg.encodeTneSeltVarb_t (ms, value.SeltVarb, "SeltVarb");
		com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tMsg.encodeLst_t (ms, value.Lst, "Lst");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_tHelper.type(); 
	}
	public static byte [] toOctet (SelTneReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSelTneReq_t (ms, val, "SelTneReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SelTneReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSelTneReq_t (ms, "SelTneReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SelTneReq_t();
		value.REQ_CTGY_CD = new String ();
		value.SeltVarb = new com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_t();
		value.Lst = new com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t();
		return value; 
	} 
}
