package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class QueryCktResp_tMsg implements MMarshalObject { 
	public QueryCktResp_t value;

	public QueryCktResp_tMsg () {
	}
	public QueryCktResp_tMsg (QueryCktResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeQueryCktResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeQueryCktResp_t (ms, tag); 
	}
	static public QueryCktResp_t decodeQueryCktResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		QueryCktResp_t value = create();
		ms.startStruct (tag, false);
		value.PTY_CKT = ms.decodeOctetString (2, "PTY_CKT");
		value.INQ_CHNG_DT_TM = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "INQ_CHNG_DT_TM");
		value.ASGN_USOC_CD = ms.decodeOctetString (6, "ASGN_USOC_CD");
		value.CO_ASGNM_TYPE = ms.decodeOctetString (7, "CO_ASGNM_TYPE");
		value.CLS_SVC_USOC_CD = ms.decodeOctetString (6, "CLS_SVC_USOC_CD");
		value.CTGY_CD = ms.decodeOctetString (2, "CTGY_CD");
		{ 
			ms.startSequence ("EqpOldId", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("EqpOldId", false);
			{ 
				value.EqpOldId = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.EqpOldId[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tMsg.decodeEqpOldId_t (ms, "EqpOldId");
				} 
			}
			ms.endArray ("EqpOldId", false);
			ms.endSequence ("EqpOldId", false);
		}
		value.CktRecSvc = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tMsg.decodeCktRecSvc_t (ms, "CktRecSvc");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeQueryCktResp_t (MMarshalStrategy ms, QueryCktResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PTY_CKT, 2, "PTY_CKT");
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.INQ_CHNG_DT_TM, "INQ_CHNG_DT_TM");
		ms.encode (value.ASGN_USOC_CD, 6, "ASGN_USOC_CD");
		ms.encode (value.CO_ASGNM_TYPE, 7, "CO_ASGNM_TYPE");
		ms.encode (value.CLS_SVC_USOC_CD, 6, "CLS_SVC_USOC_CD");
		ms.encode (value.CTGY_CD, 2, "CTGY_CD");
		{ 
			ms.startSequence ("EqpOldId", true);
			ms.encode (value.EqpOldId.length, "_sequenceLength", true);
			ms.startArray ("EqpOldId", true);
			{ 
				for (int __i0 = 0; __i0 < value.EqpOldId.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tMsg.encodeEqpOldId_t (ms, value.EqpOldId[__i0], "EqpOldId");
				} 
			}
			ms.endArray ("EqpOldId", true);
			ms.endSequence ("EqpOldId", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tMsg.encodeCktRecSvc_t (ms, value.CktRecSvc, "CktRecSvc");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_tHelper.type(); 
	}
	public static byte [] toOctet (QueryCktResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeQueryCktResp_t (ms, val, "QueryCktResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static QueryCktResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeQueryCktResp_t (ms, "QueryCktResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.QueryCktResp_t();
		value.PTY_CKT = new String ();
		value.INQ_CHNG_DT_TM = new com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t();
		value.ASGN_USOC_CD = new String ();
		value.CO_ASGNM_TYPE = new String ();
		value.CLS_SVC_USOC_CD = new String ();
		value.CTGY_CD = new String ();
		int __seqLength = 0;
		value.EqpOldId = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t[__seqLength];
		value.CktRecSvc = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t();
		return value; 
	} 
}
