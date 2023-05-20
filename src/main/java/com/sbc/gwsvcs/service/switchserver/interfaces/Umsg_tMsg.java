package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Umsg_tMsg implements MMarshalObject { 
	public Umsg_t value;

	public Umsg_tMsg () {
	}
	public Umsg_tMsg (Umsg_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUmsg_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUmsg_t (ms, tag); 
	}
	static public Umsg_t decodeUmsg_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Umsg_t value = create();
		ms.startStruct (tag, false);
		value.MSG_NBR = ms.decodeOctetString (9, "MSG_NBR");
		value.MSG_TYPE = ms.decodeOctetString (2, "MSG_TYPE");
		{ 
			ms.startSequence ("MSG_TX", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("MSG_TX", false);
			{ 
				value.MSG_TX = new char[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.MSG_TX[__i0] = ms.decodeChar ("MSG_TX");
				} 
			}
			ms.endArray ("MSG_TX", false);
			ms.endSequence ("MSG_TX", false);
		}
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeUmsg_t (MMarshalStrategy ms, Umsg_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.MSG_NBR, 9, "MSG_NBR");
		ms.encode (value.MSG_TYPE, 2, "MSG_TYPE");
		{ 
			ms.startSequence ("MSG_TX", true);
			ms.encode (value.MSG_TX.length, "_sequenceLength", true);
			ms.startArray ("MSG_TX", true);
			{ 
				for (int __i0 = 0; __i0 < value.MSG_TX.length; __i0++) { 
					ms.encode (value.MSG_TX[__i0], "MSG_TX");
				} 
			}
			ms.endArray ("MSG_TX", true);
			ms.endSequence ("MSG_TX", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.type(); 
	}
	public static byte [] toOctet (Umsg_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUmsg_t (ms, val, "Umsg_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Umsg_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUmsg_t (ms, "Umsg_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t();
		value.MSG_NBR = new String ();
		value.MSG_TYPE = new String ();
		int __seqLength = 0;
		value.MSG_TX = new char[__seqLength];
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		return value; 
	} 
}
