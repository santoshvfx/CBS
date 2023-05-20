package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UpdMscReq_tMsg implements MMarshalObject { 
	public UpdMscReq_t value;

	public UpdMscReq_tMsg () {
	}
	public UpdMscReq_tMsg (UpdMscReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUpdMscReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUpdMscReq_t (ms, tag); 
	}
	static public UpdMscReq_t decodeUpdMscReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UpdMscReq_t value = create();
		ms.startStruct (tag, false);
		value.TN_UPD_FCN_CD = ms.decodeOctetString (4, "TN_UPD_FCN_CD");
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		{ 
			ms.startSequence ("AsgLim", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (6)) {
				throw new MMarshalException("Sequence AsgLim exceeds the bounded size of 6");
				}
			ms.startArray ("AsgLim", false);
			{ 
				value.AsgLim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.AsgLim[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tMsg.decodeAsglim_t (ms, "AsgLim");
				} 
			}
			ms.endArray ("AsgLim", false);
			ms.endSequence ("AsgLim", false);
		}
		value.ACTN_CD = ms.decodeOctetString (4, "ACTN_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeUpdMscReq_t (MMarshalStrategy ms, UpdMscReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TN_UPD_FCN_CD, 4, "TN_UPD_FCN_CD");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		{ 
			ms.startSequence ("AsgLim", true);
			if (value.AsgLim.length > (6)) {
				throw new MMarshalException("Sequence AsgLim exceeds the bounded size of 6");
				}
			ms.encode (value.AsgLim.length, "_sequenceLength", true);
			ms.startArray ("AsgLim", true);
			{ 
				for (int __i0 = 0; __i0 < value.AsgLim.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_tMsg.encodeAsglim_t (ms, value.AsgLim[__i0], "AsgLim");
				} 
			}
			ms.endArray ("AsgLim", true);
			ms.endSequence ("AsgLim", true);
		}
		ms.encode (value.ACTN_CD, 4, "ACTN_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_tHelper.type(); 
	}
	public static byte [] toOctet (UpdMscReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUpdMscReq_t (ms, val, "UpdMscReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static UpdMscReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUpdMscReq_t (ms, "UpdMscReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.UpdMscReq_t();
		value.TN_UPD_FCN_CD = new String ();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		int __seqLength = 0;
		value.AsgLim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
		value.ACTN_CD = new String ();
		return value; 
	} 
}
