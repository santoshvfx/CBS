package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ChgAsgReq_tMsg implements MMarshalObject { 
	public ChgAsgReq_t value;

	public ChgAsgReq_tMsg () {
	}
	public ChgAsgReq_tMsg (ChgAsgReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeChgAsgReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeChgAsgReq_t (ms, tag); 
	}
	static public ChgAsgReq_t decodeChgAsgReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ChgAsgReq_t value = create();
		ms.startStruct (tag, false);
		value.ExOld = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "ExOld");
		value.ExNew = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "ExNew");
		{ 
			ms.startSequence ("Eqp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Eqp", false);
			{ 
				value.Eqp = new com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Eqp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_tMsg.decodeEqp_t (ms, "Eqp");
				} 
			}
			ms.endArray ("Eqp", false);
			ms.endSequence ("Eqp", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeChgAsgReq_t (MMarshalStrategy ms, ChgAsgReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.ExOld, "ExOld");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.ExNew, "ExNew");
		{ 
			ms.startSequence ("Eqp", true);
			ms.encode (value.Eqp.length, "_sequenceLength", true);
			ms.startArray ("Eqp", true);
			{ 
				for (int __i0 = 0; __i0 < value.Eqp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_tMsg.encodeEqp_t (ms, value.Eqp[__i0], "Eqp");
				} 
			}
			ms.endArray ("Eqp", true);
			ms.endSequence ("Eqp", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_tHelper.type(); 
	}
	public static byte [] toOctet (ChgAsgReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeChgAsgReq_t (ms, val, "ChgAsgReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ChgAsgReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeChgAsgReq_t (ms, "ChgAsgReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.ChgAsgReq_t();
		value.ExOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.ExNew = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		int __seqLength = 0;
		value.Eqp = new com.sbc.gwsvcs.service.switchserver.interfaces.Eqp_t[__seqLength];
		return value; 
	} 
}
