package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnInqResp_tMsg implements MMarshalObject { 
	public TnInqResp_t value;

	public TnInqResp_tMsg () {
	}
	public TnInqResp_tMsg (TnInqResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnInqResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnInqResp_t (ms, tag); 
	}
	static public TnInqResp_t decodeTnInqResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnInqResp_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.DISCT_ASGNM_CTGY_CD = ms.decodeOctetString (8, "DISCT_ASGNM_CTGY_CD");
		value.DISCT_CO_TYPE_CD = ms.decodeOctetString (6, "DISCT_CO_TYPE_CD");
		value.TN_RLS_DT_TX = ms.decodeOctetString (9, "TN_RLS_DT_TX");
		value.TN_RMK_TX = ms.decodeOctetString (61, "TN_RMK_TX");
		value.INQ_CHNG_DT_TM = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "INQ_CHNG_DT_TM");
		value.TN_TYPE_CD = ms.decodeOctetString (2, "TN_TYPE_CD");
		value.TN_LIST_NBR = ms.decodeOctetString (4, "TN_LIST_NBR");
		value.TN_LIM_VALU_CD = ms.decodeOctetString (4, "TN_LIM_VALU_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTnInqResp_t (MMarshalStrategy ms, TnInqResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.DISCT_ASGNM_CTGY_CD, 8, "DISCT_ASGNM_CTGY_CD");
		ms.encode (value.DISCT_CO_TYPE_CD, 6, "DISCT_CO_TYPE_CD");
		ms.encode (value.TN_RLS_DT_TX, 9, "TN_RLS_DT_TX");
		ms.encode (value.TN_RMK_TX, 61, "TN_RMK_TX");
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.INQ_CHNG_DT_TM, "INQ_CHNG_DT_TM");
		ms.encode (value.TN_TYPE_CD, 2, "TN_TYPE_CD");
		ms.encode (value.TN_LIST_NBR, 4, "TN_LIST_NBR");
		ms.encode (value.TN_LIM_VALU_CD, 4, "TN_LIM_VALU_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_tHelper.type(); 
	}
	public static byte [] toOctet (TnInqResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTnInqResp_t (ms, val, "TnInqResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TnInqResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTnInqResp_t (ms, "TnInqResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TnInqResp_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.DISCT_ASGNM_CTGY_CD = new String ();
		value.DISCT_CO_TYPE_CD = new String ();
		value.TN_RLS_DT_TX = new String ();
		value.TN_RMK_TX = new String ();
		value.INQ_CHNG_DT_TM = new com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t();
		value.TN_TYPE_CD = new String ();
		value.TN_LIST_NBR = new String ();
		value.TN_LIM_VALU_CD = new String ();
		return value; 
	} 
}
