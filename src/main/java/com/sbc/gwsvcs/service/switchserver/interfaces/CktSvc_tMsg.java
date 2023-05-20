package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktSvc_tMsg implements MMarshalObject { 
	public CktSvc_t value;

	public CktSvc_tMsg () {
	}
	public CktSvc_tMsg (CktSvc_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktSvc_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktSvc_t (ms, tag); 
	}
	static public CktSvc_t decodeCktSvc_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktSvc_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("SvcSeqp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SvcSeqp", false);
			{ 
				value.SvcSeqp = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SvcSeqp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tMsg.decodeSvcSeqp_t (ms, "SvcSeqp");
				} 
			}
			ms.endArray ("SvcSeqp", false);
			ms.endSequence ("SvcSeqp", false);
		}
		value.SvcCtl = com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_tMsg.decodeSvcCtl_t (ms, "SvcCtl");
		value.ExOld = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "ExOld");
		value.ExNew = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "ExNew");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktSvc_t (MMarshalStrategy ms, CktSvc_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("SvcSeqp", true);
			ms.encode (value.SvcSeqp.length, "_sequenceLength", true);
			ms.startArray ("SvcSeqp", true);
			{ 
				for (int __i0 = 0; __i0 < value.SvcSeqp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tMsg.encodeSvcSeqp_t (ms, value.SvcSeqp[__i0], "SvcSeqp");
				} 
			}
			ms.endArray ("SvcSeqp", true);
			ms.endSequence ("SvcSeqp", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_tMsg.encodeSvcCtl_t (ms, value.SvcCtl, "SvcCtl");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.ExOld, "ExOld");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.ExNew, "ExNew");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_tHelper.type(); 
	}
	public static byte [] toOctet (CktSvc_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktSvc_t (ms, val, "CktSvc_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktSvc_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktSvc_t (ms, "CktSvc_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t();
		int __seqLength = 0;
		value.SvcSeqp = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t[__seqLength];
		value.SvcCtl = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_t();
		value.ExOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.ExNew = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		return value; 
	} 
}
