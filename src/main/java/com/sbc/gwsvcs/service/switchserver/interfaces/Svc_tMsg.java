package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Svc_tMsg implements MMarshalObject { 
	public Svc_t value;

	public Svc_tMsg () {
	}
	public Svc_tMsg (Svc_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSvc_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSvc_t (ms, tag); 
	}
	static public Svc_t decodeSvc_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Svc_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("ExOldNew", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ExOldNew", false);
			{ 
				value.ExOldNew = new com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ExOldNew[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tMsg.decodeExOldNew_t (ms, "ExOldNew");
				} 
			}
			ms.endArray ("ExOldNew", false);
			ms.endSequence ("ExOldNew", false);
		}
		value.Sattr = com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_tMsg.decodeSattr_t (ms, "Sattr");
		value.DsgnOldNew = com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tMsg.decodeDsgnOldNew_t (ms, "DsgnOldNew");
		{ 
			ms.startSequence ("Seqp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Seqp", false);
			{ 
				value.Seqp = new com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Seqp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_tMsg.decodeSeqp_t (ms, "Seqp");
				} 
			}
			ms.endArray ("Seqp", false);
			ms.endSequence ("Seqp", false);
		}
		value.Ctl = com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tMsg.decodeCtl_t (ms, "Ctl");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSvc_t (MMarshalStrategy ms, Svc_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("ExOldNew", true);
			ms.encode (value.ExOldNew.length, "_sequenceLength", true);
			ms.startArray ("ExOldNew", true);
			{ 
				for (int __i0 = 0; __i0 < value.ExOldNew.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tMsg.encodeExOldNew_t (ms, value.ExOldNew[__i0], "ExOldNew");
				} 
			}
			ms.endArray ("ExOldNew", true);
			ms.endSequence ("ExOldNew", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_tMsg.encodeSattr_t (ms, value.Sattr, "Sattr");
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tMsg.encodeDsgnOldNew_t (ms, value.DsgnOldNew, "DsgnOldNew");
		{ 
			ms.startSequence ("Seqp", true);
			ms.encode (value.Seqp.length, "_sequenceLength", true);
			ms.startArray ("Seqp", true);
			{ 
				for (int __i0 = 0; __i0 < value.Seqp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_tMsg.encodeSeqp_t (ms, value.Seqp[__i0], "Seqp");
				} 
			}
			ms.endArray ("Seqp", true);
			ms.endSequence ("Seqp", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tMsg.encodeCtl_t (ms, value.Ctl, "Ctl");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Svc_tHelper.type(); 
	}
	public static byte [] toOctet (Svc_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSvc_t (ms, val, "Svc_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Svc_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSvc_t (ms, "Svc_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t();
		int __seqLength = 0;
		value.ExOldNew = new com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t[__seqLength];
		value.Sattr = new com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_t();
		value.DsgnOldNew = new com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_t();
		value.Seqp = new com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t[__seqLength];
		value.Ctl = new com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t();
		return value; 
	} 
}
