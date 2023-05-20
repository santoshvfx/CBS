package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AclCec_tMsg implements MMarshalObject { 
	public AclCec_t value;

	public AclCec_tMsg () {
	}
	public AclCec_tMsg (AclCec_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAclCec_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAclCec_t (ms, tag); 
	}
	static public AclCec_t decodeAclCec_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AclCec_t value = create();
		ms.startStruct (tag, false);
		value.ASGN_USOC_CD = ms.decodeOctetString (6, "ASGN_USOC_CD");
		value.CO_ADMN_TYPE_CD = ms.decodeOctetString (6, "CO_ADMN_TYPE_CD");
		value.EST_HUND_CLG_LOAD_NBR = ms.decodeOctetString (2, "EST_HUND_CLG_LOAD_NBR");
		value.ESNL_SVC_LN_IND = ms.decodeOctetString (2, "ESNL_SVC_LN_IND");
		value.SVC_CLS_CD = ms.decodeOctetString (2, "SVC_CLS_CD");
		value.CNDCTR_NBR = ms.decodeOctetString (2, "CNDCTR_NBR");
		value.CO_TERMN_CD = ms.decodeOctetString (2, "CO_TERMN_CD");
		value.CLS_SVC_USOC_CD = ms.decodeOctetString (6, "CLS_SVC_USOC_CD");
		value.CTGY_CD = ms.decodeOctetString (2, "CTGY_CD");
		value.SVC_GRAD = ms.decodeOctetString (2, "SVC_GRAD");
		value.PLSG_IND = ms.decodeOctetString (2, "PLSG_IND");
		value.TRAN_QUAL_IND = ms.decodeOctetString (2, "TRAN_QUAL_IND");
		value.SIGG_CD = ms.decodeOctetString (2, "SIGG_CD");
		value.SVC_TYPE_CD_2 = ms.decodeOctetString (2, "SVC_TYPE_CD_2");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeAclCec_t (MMarshalStrategy ms, AclCec_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ASGN_USOC_CD, 6, "ASGN_USOC_CD");
		ms.encode (value.CO_ADMN_TYPE_CD, 6, "CO_ADMN_TYPE_CD");
		ms.encode (value.EST_HUND_CLG_LOAD_NBR, 2, "EST_HUND_CLG_LOAD_NBR");
		ms.encode (value.ESNL_SVC_LN_IND, 2, "ESNL_SVC_LN_IND");
		ms.encode (value.SVC_CLS_CD, 2, "SVC_CLS_CD");
		ms.encode (value.CNDCTR_NBR, 2, "CNDCTR_NBR");
		ms.encode (value.CO_TERMN_CD, 2, "CO_TERMN_CD");
		ms.encode (value.CLS_SVC_USOC_CD, 6, "CLS_SVC_USOC_CD");
		ms.encode (value.CTGY_CD, 2, "CTGY_CD");
		ms.encode (value.SVC_GRAD, 2, "SVC_GRAD");
		ms.encode (value.PLSG_IND, 2, "PLSG_IND");
		ms.encode (value.TRAN_QUAL_IND, 2, "TRAN_QUAL_IND");
		ms.encode (value.SIGG_CD, 2, "SIGG_CD");
		ms.encode (value.SVC_TYPE_CD_2, 2, "SVC_TYPE_CD_2");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tHelper.type(); 
	}
	public static byte [] toOctet (AclCec_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAclCec_t (ms, val, "AclCec_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static AclCec_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAclCec_t (ms, "AclCec_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t();
		value.ASGN_USOC_CD = new String ();
		value.CO_ADMN_TYPE_CD = new String ();
		value.EST_HUND_CLG_LOAD_NBR = new String ();
		value.ESNL_SVC_LN_IND = new String ();
		value.SVC_CLS_CD = new String ();
		value.CNDCTR_NBR = new String ();
		value.CO_TERMN_CD = new String ();
		value.CLS_SVC_USOC_CD = new String ();
		value.CTGY_CD = new String ();
		value.SVC_GRAD = new String ();
		value.PLSG_IND = new String ();
		value.TRAN_QUAL_IND = new String ();
		value.SIGG_CD = new String ();
		value.SVC_TYPE_CD_2 = new String ();
		return value; 
	} 
}
