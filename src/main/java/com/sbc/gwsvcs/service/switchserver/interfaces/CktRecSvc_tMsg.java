package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktRecSvc_tMsg implements MMarshalObject { 
	public CktRecSvc_t value;

	public CktRecSvc_tMsg () {
	}
	public CktRecSvc_tMsg (CktRecSvc_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktRecSvc_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktRecSvc_t (ms, tag); 
	}
	static public CktRecSvc_t decodeCktRecSvc_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktRecSvc_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("SvcExOld", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SvcExOld", false);
			{ 
				value.SvcExOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SvcExOld[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "SvcExOld");
				} 
			}
			ms.endArray ("SvcExOld", false);
			ms.endSequence ("SvcExOld", false);
		}
		{ 
			ms.startSequence ("SvcHuntOld", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SvcHuntOld", false);
			{ 
				value.SvcHuntOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SvcHuntOld[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "SvcHuntOld");
				} 
			}
			ms.endArray ("SvcHuntOld", false);
			ms.endSequence ("SvcHuntOld", false);
		}
		{ 
			ms.startSequence ("SvcAssocOld", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("SvcAssocOld", false);
			{ 
				value.SvcAssocOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.SvcAssocOld[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "SvcAssocOld");
				} 
			}
			ms.endArray ("SvcAssocOld", false);
			ms.endSequence ("SvcAssocOld", false);
		}
		value.SvcSattrOld = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tMsg.decodeSvcSattrOld_t (ms, "SvcSattrOld");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktRecSvc_t (MMarshalStrategy ms, CktRecSvc_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("SvcExOld", true);
			ms.encode (value.SvcExOld.length, "_sequenceLength", true);
			ms.startArray ("SvcExOld", true);
			{ 
				for (int __i0 = 0; __i0 < value.SvcExOld.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.SvcExOld[__i0], "SvcExOld");
				} 
			}
			ms.endArray ("SvcExOld", true);
			ms.endSequence ("SvcExOld", true);
		}
		{ 
			ms.startSequence ("SvcHuntOld", true);
			ms.encode (value.SvcHuntOld.length, "_sequenceLength", true);
			ms.startArray ("SvcHuntOld", true);
			{ 
				for (int __i0 = 0; __i0 < value.SvcHuntOld.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.SvcHuntOld[__i0], "SvcHuntOld");
				} 
			}
			ms.endArray ("SvcHuntOld", true);
			ms.endSequence ("SvcHuntOld", true);
		}
		{ 
			ms.startSequence ("SvcAssocOld", true);
			ms.encode (value.SvcAssocOld.length, "_sequenceLength", true);
			ms.startArray ("SvcAssocOld", true);
			{ 
				for (int __i0 = 0; __i0 < value.SvcAssocOld.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.SvcAssocOld[__i0], "SvcAssocOld");
				} 
			}
			ms.endArray ("SvcAssocOld", true);
			ms.endSequence ("SvcAssocOld", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tMsg.encodeSvcSattrOld_t (ms, value.SvcSattrOld, "SvcSattrOld");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_tHelper.type(); 
	}
	public static byte [] toOctet (CktRecSvc_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktRecSvc_t (ms, val, "CktRecSvc_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktRecSvc_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktRecSvc_t (ms, "CktRecSvc_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t();
		int __seqLength = 0;
		value.SvcExOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
		value.SvcHuntOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
		value.SvcAssocOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
		value.SvcSattrOld = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_t();
		return value; 
	} 
}
