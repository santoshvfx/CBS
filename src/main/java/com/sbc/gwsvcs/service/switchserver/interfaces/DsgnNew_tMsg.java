package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DsgnNew_tMsg implements MMarshalObject { 
	public DsgnNew_t value;

	public DsgnNew_tMsg () {
	}
	public DsgnNew_tMsg (DsgnNew_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDsgnNew_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDsgnNew_t (ms, tag); 
	}
	static public DsgnNew_t decodeDsgnNew_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DsgnNew_t value = create();
		ms.startStruct (tag, false);
		value.CNDCTR_NBR = ms.decodeOctetString (2, "CNDCTR_NBR");
		value.CO_TERMN_CD = ms.decodeOctetString (2, "CO_TERMN_CD");
		value.CTGY_CD = ms.decodeOctetString (2, "CTGY_CD");
		value.SVC_GRAD = ms.decodeOctetString (2, "SVC_GRAD");
		value.SVC_TYPE_CD_2 = ms.decodeOctetString (2, "SVC_TYPE_CD_2");
		value.SVC_CLS_CD = ms.decodeOctetString (2, "SVC_CLS_CD");
		value.SIGG_CD = ms.decodeOctetString (2, "SIGG_CD");
		value.CO_ADMN_TYPE_CD = ms.decodeOctetString (6, "CO_ADMN_TYPE_CD");
		value.ASGN_USOC_CD = ms.decodeOctetString (6, "ASGN_USOC_CD");
		value.CLS_SVC_USOC_CD = ms.decodeOctetString (6, "CLS_SVC_USOC_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeDsgnNew_t (MMarshalStrategy ms, DsgnNew_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CNDCTR_NBR, 2, "CNDCTR_NBR");
		ms.encode (value.CO_TERMN_CD, 2, "CO_TERMN_CD");
		ms.encode (value.CTGY_CD, 2, "CTGY_CD");
		ms.encode (value.SVC_GRAD, 2, "SVC_GRAD");
		ms.encode (value.SVC_TYPE_CD_2, 2, "SVC_TYPE_CD_2");
		ms.encode (value.SVC_CLS_CD, 2, "SVC_CLS_CD");
		ms.encode (value.SIGG_CD, 2, "SIGG_CD");
		ms.encode (value.CO_ADMN_TYPE_CD, 6, "CO_ADMN_TYPE_CD");
		ms.encode (value.ASGN_USOC_CD, 6, "ASGN_USOC_CD");
		ms.encode (value.CLS_SVC_USOC_CD, 6, "CLS_SVC_USOC_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_tHelper.type(); 
	}
	public static byte [] toOctet (DsgnNew_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeDsgnNew_t (ms, val, "DsgnNew_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static DsgnNew_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeDsgnNew_t (ms, "DsgnNew_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_t();
		value.CNDCTR_NBR = new String ();
		value.CO_TERMN_CD = new String ();
		value.CTGY_CD = new String ();
		value.SVC_GRAD = new String ();
		value.SVC_TYPE_CD_2 = new String ();
		value.SVC_CLS_CD = new String ();
		value.SIGG_CD = new String ();
		value.CO_ADMN_TYPE_CD = new String ();
		value.ASGN_USOC_CD = new String ();
		value.CLS_SVC_USOC_CD = new String ();
		return value; 
	} 
}
