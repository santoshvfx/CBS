package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpOld_tMsg implements MMarshalObject { 
	public EqpOld_t value;

	public EqpOld_tMsg () {
	}
	public EqpOld_tMsg (EqpOld_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpOld_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpOld_t (ms, tag); 
	}
	static public EqpOld_t decodeEqpOld_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpOld_t value = create();
		ms.startStruct (tag, false);
		value.SWITCH_ID = ms.decodeOctetString (46, "SWITCH_ID");
		value.TN_RMK_TX = ms.decodeOctetString (61, "TN_RMK_TX");
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
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeEqpOld_t (MMarshalStrategy ms, EqpOld_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.SWITCH_ID, 46, "SWITCH_ID");
		ms.encode (value.TN_RMK_TX, 61, "TN_RMK_TX");
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
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_tHelper.type(); 
	}
	public static byte [] toOctet (EqpOld_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeEqpOld_t (ms, val, "EqpOld_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static EqpOld_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeEqpOld_t (ms, "EqpOld_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOld_t();
		value.SWITCH_ID = new String ();
		value.TN_RMK_TX = new String ();
		int __seqLength = 0;
		value.AsgLim = new com.sbc.gwsvcs.service.switchserver.interfaces.Asglim_t[__seqLength];
		return value; 
	} 
}
