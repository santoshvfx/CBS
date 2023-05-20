package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CompId_tMsg implements MMarshalObject { 
	public CompId_t value;

	public CompId_tMsg () {
	}
	public CompId_tMsg (CompId_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCompId_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCompId_t (ms, tag); 
	}
	static public CompId_t decodeCompId_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CompId_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		{ 
			ms.startSequence ("ConnToId", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ConnToId", false);
			{ 
				value.ConnToId = new com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ConnToId[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_tMsg.decodeConnToId_t (ms, "ConnToId");
				} 
			}
			ms.endArray ("ConnToId", false);
			ms.endSequence ("ConnToId", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCompId_t (MMarshalStrategy ms, CompId_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		{ 
			ms.startSequence ("ConnToId", true);
			ms.encode (value.ConnToId.length, "_sequenceLength", true);
			ms.startArray ("ConnToId", true);
			{ 
				for (int __i0 = 0; __i0 < value.ConnToId.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_tMsg.encodeConnToId_t (ms, value.ConnToId[__i0], "ConnToId");
				} 
			}
			ms.endArray ("ConnToId", true);
			ms.endSequence ("ConnToId", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CompId_tHelper.type(); 
	}
	public static byte [] toOctet (CompId_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCompId_t (ms, val, "CompId_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CompId_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCompId_t (ms, "CompId_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CompId_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CompId_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CompId_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		int __seqLength = 0;
		value.ConnToId = new com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t[__seqLength];
		return value; 
	} 
}
