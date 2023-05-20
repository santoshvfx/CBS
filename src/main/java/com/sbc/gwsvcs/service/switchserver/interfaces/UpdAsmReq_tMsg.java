package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UpdAsmReq_tMsg implements MMarshalObject { 
	public UpdAsmReq_t value;

	public UpdAsmReq_tMsg () {
	}
	public UpdAsmReq_tMsg (UpdAsmReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUpdAsmReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUpdAsmReq_t (ms, tag); 
	}
	static public UpdAsmReq_t decodeUpdAsmReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UpdAsmReq_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.SWITCH_TYPE_CD = ms.decodeOctetString (6, "SWITCH_TYPE_CD");
		value.TN_UPD_FCN_CD = ms.decodeOctetString (4, "TN_UPD_FCN_CD");
		value.USER_NM = ms.decodeOctetString (9, "USER_NM");
		value.SWITCH_ID_NM = ms.decodeOctetString (6, "SWITCH_ID_NM");
		value.AGE_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "AGE_DT");
		value.RET_IND = ms.decodeOctetString (2, "RET_IND");
		value.ACTN_CD = ms.decodeOctetString (4, "ACTN_CD");
		{ 
			ms.startSequence ("CompId", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("CompId", false);
			{ 
				value.CompId = new com.sbc.gwsvcs.service.switchserver.interfaces.CompId_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CompId[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.CompId_tMsg.decodeCompId_t (ms, "CompId");
				} 
			}
			ms.endArray ("CompId", false);
			ms.endSequence ("CompId", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeUpdAsmReq_t (MMarshalStrategy ms, UpdAsmReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.SWITCH_TYPE_CD, 6, "SWITCH_TYPE_CD");
		ms.encode (value.TN_UPD_FCN_CD, 4, "TN_UPD_FCN_CD");
		ms.encode (value.USER_NM, 9, "USER_NM");
		ms.encode (value.SWITCH_ID_NM, 6, "SWITCH_ID_NM");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.AGE_DT, "AGE_DT");
		ms.encode (value.RET_IND, 2, "RET_IND");
		ms.encode (value.ACTN_CD, 4, "ACTN_CD");
		{ 
			ms.startSequence ("CompId", true);
			ms.encode (value.CompId.length, "_sequenceLength", true);
			ms.startArray ("CompId", true);
			{ 
				for (int __i0 = 0; __i0 < value.CompId.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.CompId_tMsg.encodeCompId_t (ms, value.CompId[__i0], "CompId");
				} 
			}
			ms.endArray ("CompId", true);
			ms.endSequence ("CompId", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_tHelper.type(); 
	}
	public static byte [] toOctet (UpdAsmReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUpdAsmReq_t (ms, val, "UpdAsmReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static UpdAsmReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUpdAsmReq_t (ms, "UpdAsmReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.UpdAsmReq_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.SWITCH_TYPE_CD = new String ();
		value.TN_UPD_FCN_CD = new String ();
		value.USER_NM = new String ();
		value.SWITCH_ID_NM = new String ();
		value.AGE_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.RET_IND = new String ();
		value.ACTN_CD = new String ();
		int __seqLength = 0;
		value.CompId = new com.sbc.gwsvcs.service.switchserver.interfaces.CompId_t[__seqLength];
		return value; 
	} 
}
