package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpOldId_tMsg implements MMarshalObject { 
	public EqpOldId_t value;

	public EqpOldId_tMsg () {
	}
	public EqpOldId_tMsg (EqpOldId_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpOldId_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpOldId_t (ms, tag); 
	}
	static public EqpOldId_t decodeEqpOldId_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpOldId_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		{ 
			ms.startSequence ("EqpIc", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("EqpIc", false);
			{ 
				value.EqpIc = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.EqpIc[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tMsg.decodeEqpIc_t (ms, "EqpIc");
				} 
			}
			ms.endArray ("EqpIc", false);
			ms.endSequence ("EqpIc", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEqpOldId_t (MMarshalStrategy ms, EqpOldId_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		{ 
			ms.startSequence ("EqpIc", true);
			ms.encode (value.EqpIc.length, "_sequenceLength", true);
			ms.startArray ("EqpIc", true);
			{ 
				for (int __i0 = 0; __i0 < value.EqpIc.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tMsg.encodeEqpIc_t (ms, value.EqpIc[__i0], "EqpIc");
				} 
			}
			ms.endArray ("EqpIc", true);
			ms.endSequence ("EqpIc", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_tHelper.type(); 
	}
	public static byte [] toOctet (EqpOldId_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEqpOldId_t (ms, val, "EqpOldId_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EqpOldId_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEqpOldId_t (ms, "EqpOldId_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		int __seqLength = 0;
		value.EqpIc = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[__seqLength];
		return value; 
	} 
}
