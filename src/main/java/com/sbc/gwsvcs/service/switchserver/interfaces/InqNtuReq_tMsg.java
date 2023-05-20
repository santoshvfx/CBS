package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class InqNtuReq_tMsg implements MMarshalObject { 
	public InqNtuReq_t value;

	public InqNtuReq_tMsg () {
	}
	public InqNtuReq_tMsg (InqNtuReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeInqNtuReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeInqNtuReq_t (ms, tag); 
	}
	static public InqNtuReq_t decodeInqNtuReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		InqNtuReq_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.HI_VALU = ms.decodeOctetString (25, "HI_VALU");
		value.CKT_OPTN_IND = ms.decodeOctetString (2, "CKT_OPTN_IND");
		value.VIEW_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "VIEW_DT");
		value.LAST_VIEW_IND = ms.decodeOctetString (2, "LAST_VIEW_IND");
		value.FOPTN = ms.decodeOctetString (2, "FOPTN");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeInqNtuReq_t (MMarshalStrategy ms, InqNtuReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.HI_VALU, 25, "HI_VALU");
		ms.encode (value.CKT_OPTN_IND, 2, "CKT_OPTN_IND");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.VIEW_DT, "VIEW_DT");
		ms.encode (value.LAST_VIEW_IND, 2, "LAST_VIEW_IND");
		ms.encode (value.FOPTN, 2, "FOPTN");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_tHelper.type(); 
	}
	public static byte [] toOctet (InqNtuReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeInqNtuReq_t (ms, val, "InqNtuReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static InqNtuReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeInqNtuReq_t (ms, "InqNtuReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqNtuReq_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.HI_VALU = new String ();
		value.CKT_OPTN_IND = new String ();
		value.VIEW_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.LAST_VIEW_IND = new String ();
		value.FOPTN = new String ();
		return value; 
	} 
}
