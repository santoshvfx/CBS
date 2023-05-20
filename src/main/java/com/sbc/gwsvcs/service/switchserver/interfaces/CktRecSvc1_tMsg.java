package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktRecSvc1_tMsg implements MMarshalObject { 
	public CktRecSvc1_t value;

	public CktRecSvc1_tMsg () {
	}
	public CktRecSvc1_tMsg (CktRecSvc1_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktRecSvc1_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktRecSvc1_t (ms, tag); 
	}
	static public CktRecSvc1_t decodeCktRecSvc1_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktRecSvc1_t value = create();
		ms.startStruct (tag, false);
		value.FND_SVC_IND = ms.decodeOctetString (2, "FND_SVC_IND");
		{ 
			ms.startSequence ("ExOld", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("ExOld", false);
			{ 
				value.ExOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.ExOld[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "ExOld");
				} 
			}
			ms.endArray ("ExOld", false);
			ms.endSequence ("ExOld", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCktRecSvc1_t (MMarshalStrategy ms, CktRecSvc1_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.FND_SVC_IND, 2, "FND_SVC_IND");
		{ 
			ms.startSequence ("ExOld", true);
			ms.encode (value.ExOld.length, "_sequenceLength", true);
			ms.startArray ("ExOld", true);
			{ 
				for (int __i0 = 0; __i0 < value.ExOld.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.ExOld[__i0], "ExOld");
				} 
			}
			ms.endArray ("ExOld", true);
			ms.endSequence ("ExOld", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tHelper.type(); 
	}
	public static byte [] toOctet (CktRecSvc1_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCktRecSvc1_t (ms, val, "CktRecSvc1_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CktRecSvc1_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCktRecSvc1_t (ms, "CktRecSvc1_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_t();
		value.FND_SVC_IND = new String ();
		int __seqLength = 0;
		value.ExOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
		return value; 
	} 
}
