package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ComnUattr_tMsg implements MMarshalObject { 
	public ComnUattr_t value;

	public ComnUattr_tMsg () {
	}
	public ComnUattr_tMsg (ComnUattr_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeComnUattr_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeComnUattr_t (ms, tag); 
	}
	static public ComnUattr_t decodeComnUattr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ComnUattr_t value = create();
		ms.startStruct (tag, false);
		value.AIS_CD = ms.decodeOctetString (2, "AIS_CD");
		value.ASGNM_CAPY_QTY = ms.decodeOctetString (3, "ASGNM_CAPY_QTY");
		{ 
			ms.startSequence ("Asglim", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (6)) {
				throw new MMarshalException("Sequence Asglim exceeds the bounded size of 6");
				}
			ms.startArray ("Asglim", false);
			{ 
				value.Asglim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Asglim[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tMsg.decodeAsglim_t (ms, "Asglim");
				} 
			}
			ms.endArray ("Asglim", false);
			ms.endSequence ("Asglim", false);
		}
		value.ASGNM_USE_QTY = ms.decodeOctetString (3, "ASGNM_USE_QTY");
		value.AVAIL_CAPY_IND = ms.decodeOctetString (2, "AVAIL_CAPY_IND");
		value.CALL_CT = ms.decodeOctetString (3, "CALL_CT");
		value.DATETNONLIST = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "DATETNONLIST");
		value.DISCT_ASGNM_CTGY_CD = ms.decodeOctetString (8, "DISCT_ASGNM_CTGY_CD");
		value.DISCT_CO_TYPE_CD = ms.decodeOctetString (6, "DISCT_CO_TYPE_CD");
		value.DUE_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "DUE_DT");
		value.INTRCPT_CD = ms.decodeOctetString (4, "INTRCPT_CD");
		value.ORD_3_NBR = ms.decodeOctetString (14, "ORD_3_NBR");
		value.NEGDATE = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "NEGDATE");
		value.NEGSTATUS = ms.decodeOctetString (2, "NEGSTATUS");
		value.NPUB_IND = ms.decodeOctetString (2, "NPUB_IND");
		value.REQCATG = ms.decodeOctetString (11, "REQCATG");
		value.RLS_DT_IND = ms.decodeOctetString (2, "RLS_DT_IND");
		value.TN_RLS_DT_TX = ms.decodeOctetString (9, "TN_RLS_DT_TX");
		value.TN_RMK_TX = ms.decodeOctetString (61, "TN_RMK_TX");
		value.TN_SELT_IND = ms.decodeOctetString (2, "TN_SELT_IND");
		value.TN_TYPE_CD = ms.decodeOctetString (2, "TN_TYPE_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeComnUattr_t (MMarshalStrategy ms, ComnUattr_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.AIS_CD, 2, "AIS_CD");
		ms.encode (value.ASGNM_CAPY_QTY, 3, "ASGNM_CAPY_QTY");
		{ 
			ms.startSequence ("Asglim", true);
			if (value.Asglim.length > (6)) {
				throw new MMarshalException("Sequence Asglim exceeds the bounded size of 6");
				}
			ms.encode (value.Asglim.length, "_sequenceLength", true);
			ms.startArray ("Asglim", true);
			{ 
				for (int __i0 = 0; __i0 < value.Asglim.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tMsg.encodeAsglim_t (ms, value.Asglim[__i0], "Asglim");
				} 
			}
			ms.endArray ("Asglim", true);
			ms.endSequence ("Asglim", true);
		}
		ms.encode (value.ASGNM_USE_QTY, 3, "ASGNM_USE_QTY");
		ms.encode (value.AVAIL_CAPY_IND, 2, "AVAIL_CAPY_IND");
		ms.encode (value.CALL_CT, 3, "CALL_CT");
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.DATETNONLIST, "DATETNONLIST");
		ms.encode (value.DISCT_ASGNM_CTGY_CD, 8, "DISCT_ASGNM_CTGY_CD");
		ms.encode (value.DISCT_CO_TYPE_CD, 6, "DISCT_CO_TYPE_CD");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.DUE_DT, "DUE_DT");
		ms.encode (value.INTRCPT_CD, 4, "INTRCPT_CD");
		ms.encode (value.ORD_3_NBR, 14, "ORD_3_NBR");
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.NEGDATE, "NEGDATE");
		ms.encode (value.NEGSTATUS, 2, "NEGSTATUS");
		ms.encode (value.NPUB_IND, 2, "NPUB_IND");
		ms.encode (value.REQCATG, 11, "REQCATG");
		ms.encode (value.RLS_DT_IND, 2, "RLS_DT_IND");
		ms.encode (value.TN_RLS_DT_TX, 9, "TN_RLS_DT_TX");
		ms.encode (value.TN_RMK_TX, 61, "TN_RMK_TX");
		ms.encode (value.TN_SELT_IND, 2, "TN_SELT_IND");
		ms.encode (value.TN_TYPE_CD, 2, "TN_TYPE_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tHelper.type(); 
	}
	public static byte [] toOctet (ComnUattr_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeComnUattr_t (ms, val, "ComnUattr_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ComnUattr_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeComnUattr_t (ms, "ComnUattr_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t();
		value.AIS_CD = new String ();
		value.ASGNM_CAPY_QTY = new String ();
		int __seqLength = 0;
		value.Asglim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
		value.ASGNM_USE_QTY = new String ();
		value.AVAIL_CAPY_IND = new String ();
		value.CALL_CT = new String ();
		value.DATETNONLIST = new com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t();
		value.DISCT_ASGNM_CTGY_CD = new String ();
		value.DISCT_CO_TYPE_CD = new String ();
		value.DUE_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.INTRCPT_CD = new String ();
		value.ORD_3_NBR = new String ();
		value.NEGDATE = new com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t();
		value.NEGSTATUS = new String ();
		value.NPUB_IND = new String ();
		value.REQCATG = new String ();
		value.RLS_DT_IND = new String ();
		value.TN_RLS_DT_TX = new String ();
		value.TN_RMK_TX = new String ();
		value.TN_SELT_IND = new String ();
		value.TN_TYPE_CD = new String ();
		return value; 
	} 
}
