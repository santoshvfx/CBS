package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class MasgCkt_tMsg implements MMarshalObject { 
	public MasgCkt_t value;

	public MasgCkt_tMsg () {
	}
	public MasgCkt_tMsg (MasgCkt_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeMasgCkt_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeMasgCkt_t (ms, tag); 
	}
	static public MasgCkt_t decodeMasgCkt_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		MasgCkt_t value = create();
		ms.startStruct (tag, false);
		value.CktCktattr = com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tMsg.decodeCktCktattr_t (ms, "CktCktattr");
		{ 
			ms.startSequence ("CktEqp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("CktEqp", false);
			{ 
				value.CktEqp = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CktEqp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_tMsg.decodeCktEqp_t (ms, "CktEqp");
				} 
			}
			ms.endArray ("CktEqp", false);
			ms.endSequence ("CktEqp", false);
		}
		value.CktSvc = com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_tMsg.decodeCktSvc_t (ms, "CktSvc");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeMasgCkt_t (MMarshalStrategy ms, MasgCkt_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tMsg.encodeCktCktattr_t (ms, value.CktCktattr, "CktCktattr");
		{ 
			ms.startSequence ("CktEqp", true);
			ms.encode (value.CktEqp.length, "_sequenceLength", true);
			ms.startArray ("CktEqp", true);
			{ 
				for (int __i0 = 0; __i0 < value.CktEqp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_tMsg.encodeCktEqp_t (ms, value.CktEqp[__i0], "CktEqp");
				} 
			}
			ms.endArray ("CktEqp", true);
			ms.endSequence ("CktEqp", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_tMsg.encodeCktSvc_t (ms, value.CktSvc, "CktSvc");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_tHelper.type(); 
	}
	public static byte [] toOctet (MasgCkt_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeMasgCkt_t (ms, val, "MasgCkt_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static MasgCkt_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeMasgCkt_t (ms, "MasgCkt_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t();
		value.CktCktattr = new com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_t();
		int __seqLength = 0;
		value.CktEqp = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t[__seqLength];
		value.CktSvc = new com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t();
		return value; 
	} 
}
