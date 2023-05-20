package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SelTnReq_tMsg implements MMarshalObject { 
	public SelTnReq_t value;

	public SelTnReq_tMsg () {
	}
	public SelTnReq_tMsg (SelTnReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSelTnReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSelTnReq_t (ms, tag); 
	}
	static public SelTnReq_t decodeSelTnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SelTnReq_t value = create();
		ms.startStruct (tag, false);
		value.REQ_CTGY_CD = ms.decodeOctetString (11, "REQ_CTGY_CD");
		value.SeltVarb = com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_tMsg.decodeTnSeltVarb_t (ms, "SeltVarb");
		value.Lst = com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tMsg.decodeLst_t (ms, "Lst");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSelTnReq_t (MMarshalStrategy ms, SelTnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.REQ_CTGY_CD, 11, "REQ_CTGY_CD");
		com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_tMsg.encodeTnSeltVarb_t (ms, value.SeltVarb, "SeltVarb");
		com.sbc.gwsvcs.service.switchserver.interfaces.Lst_tMsg.encodeLst_t (ms, value.Lst, "Lst");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_tHelper.type(); 
	}
	public static byte [] toOctet (SelTnReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSelTnReq_t (ms, val, "SelTnReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SelTnReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSelTnReq_t (ms, "SelTnReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SelTnReq_t();
		value.REQ_CTGY_CD = new String ();
		value.SeltVarb = new com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t();
		value.Lst = new com.sbc.gwsvcs.service.switchserver.interfaces.Lst_t();
		return value; 
	} 
}
