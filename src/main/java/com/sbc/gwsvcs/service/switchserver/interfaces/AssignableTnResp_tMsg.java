package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AssignableTnResp_tMsg implements MMarshalObject { 
	public AssignableTnResp_t value;

	public AssignableTnResp_tMsg () {
	}
	public AssignableTnResp_tMsg (AssignableTnResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAssignableTnResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAssignableTnResp_t (ms, tag); 
	}
	static public AssignableTnResp_t decodeAssignableTnResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AssignableTnResp_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
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
		value.TN_RMK_TX = ms.decodeOctetString (61, "TN_RMK_TX");
		value.INQ_CHNG_DT_TM = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "INQ_CHNG_DT_TM");
		value.TN_TYPE_CD = ms.decodeOctetString (2, "TN_TYPE_CD");
		value.NPUB_IND = ms.decodeOctetString (2, "NPUB_IND");
		value.DISCT_ASGNM_CTGY_CD = ms.decodeOctetString (8, "DISCT_ASGNM_CTGY_CD");
		value.DISCT_CO_TYPE_CD = ms.decodeOctetString (6, "DISCT_CO_TYPE_CD");
		value.TN_RLS_DT_TX = ms.decodeOctetString (9, "TN_RLS_DT_TX");
		value.RT_ZONE = ms.decodeOctetString (4, "RT_ZONE");
		value.CTX_NM = ms.decodeOctetString (19, "CTX_NM");
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ic");
		value.DLCT_IND = ms.decodeOctetString (2, "DLCT_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeAssignableTnResp_t (MMarshalStrategy ms, AssignableTnResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
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
		ms.encode (value.TN_RMK_TX, 61, "TN_RMK_TX");
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.INQ_CHNG_DT_TM, "INQ_CHNG_DT_TM");
		ms.encode (value.TN_TYPE_CD, 2, "TN_TYPE_CD");
		ms.encode (value.NPUB_IND, 2, "NPUB_IND");
		ms.encode (value.DISCT_ASGNM_CTGY_CD, 8, "DISCT_ASGNM_CTGY_CD");
		ms.encode (value.DISCT_CO_TYPE_CD, 6, "DISCT_CO_TYPE_CD");
		ms.encode (value.TN_RLS_DT_TX, 9, "TN_RLS_DT_TX");
		ms.encode (value.RT_ZONE, 4, "RT_ZONE");
		ms.encode (value.CTX_NM, 19, "CTX_NM");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ic, "Ic");
		ms.encode (value.DLCT_IND, 2, "DLCT_IND");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_tHelper.type(); 
	}
	public static byte [] toOctet (AssignableTnResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAssignableTnResp_t (ms, val, "AssignableTnResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static AssignableTnResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAssignableTnResp_t (ms, "AssignableTnResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnResp_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		int __seqLength = 0;
		value.Asglim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
		value.TN_RMK_TX = new String ();
		value.INQ_CHNG_DT_TM = new com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t();
		value.TN_TYPE_CD = new String ();
		value.NPUB_IND = new String ();
		value.DISCT_ASGNM_CTGY_CD = new String ();
		value.DISCT_CO_TYPE_CD = new String ();
		value.TN_RLS_DT_TX = new String ();
		value.RT_ZONE = new String ();
		value.CTX_NM = new String ();
		value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.DLCT_IND = new String ();
		return value; 
	} 
}
