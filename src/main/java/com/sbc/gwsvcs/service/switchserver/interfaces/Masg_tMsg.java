package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Masg_tMsg implements MMarshalObject { 
	public Masg_t value;

	public Masg_tMsg () {
	}
	public Masg_tMsg (Masg_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeMasg_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeMasg_t (ms, tag); 
	}
	static public Masg_t decodeMasg_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Masg_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("MasgCkt", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("MasgCkt", false);
			{ 
				value.MasgCkt = new com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.MasgCkt[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_tMsg.decodeMasgCkt_t (ms, "MasgCkt");
				} 
			}
			ms.endArray ("MasgCkt", false);
			ms.endSequence ("MasgCkt", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeMasg_t (MMarshalStrategy ms, Masg_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("MasgCkt", true);
			ms.encode (value.MasgCkt.length, "_sequenceLength", true);
			ms.startArray ("MasgCkt", true);
			{ 
				for (int __i0 = 0; __i0 < value.MasgCkt.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_tMsg.encodeMasgCkt_t (ms, value.MasgCkt[__i0], "MasgCkt");
				} 
			}
			ms.endArray ("MasgCkt", true);
			ms.endSequence ("MasgCkt", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Masg_tHelper.type(); 
	}
	public static byte [] toOctet (Masg_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeMasg_t (ms, val, "Masg_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Masg_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeMasg_t (ms, "Masg_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t();
		int __seqLength = 0;
		value.MasgCkt = new com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t[__seqLength];
		return value; 
	} 
}
