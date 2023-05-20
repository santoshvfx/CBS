package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class BLOB_tMsg implements MMarshalObject { 
	public BLOB_t value;

	public BLOB_tMsg () {
	}
	public BLOB_tMsg (BLOB_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeBLOB_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeBLOB_t (ms, tag); 
	}
	static public BLOB_t decodeBLOB_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		BLOB_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("FCIF", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("FCIF", false);
			value.FCIF = new byte[__seqLength];
			ms.decode (value.FCIF, __seqLength, "FCIF");
			ms.endArray ("FCIF", false);
			ms.endSequence ("FCIF", false);
		}
		value.TRUNCATED = ms.decodeOctetString (2, "TRUNCATED");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeBLOB_t (MMarshalStrategy ms, BLOB_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("FCIF", true);
			ms.encode (value.FCIF.length, "_sequenceLength", true);
			ms.startArray ("FCIF", true);
			ms.encode (value.FCIF, value.FCIF.length, "FCIF");
			ms.endArray ("FCIF", true);
			ms.endSequence ("FCIF", true);
		}
		ms.encode (value.TRUNCATED, 2, "TRUNCATED");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.BLOB_tHelper.type(); 
	}
	public static byte [] toOctet (BLOB_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeBLOB_t (ms, val, "BLOB_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static BLOB_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeBLOB_t (ms, "BLOB_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.BLOB_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.BLOB_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.BLOB_t();
		int __seqLength = 0;
		value.FCIF = new byte[__seqLength];
		value.TRUNCATED = new String ();
		return value; 
	} 
}
