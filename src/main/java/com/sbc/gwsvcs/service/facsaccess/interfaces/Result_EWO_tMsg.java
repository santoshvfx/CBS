package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Result_EWO_tMsg implements MMarshalObject { 
	public Result_EWO_t value;

	public Result_EWO_tMsg () {
	}
	public Result_EWO_tMsg (Result_EWO_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeResult_EWO_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeResult_EWO_t (ms, tag); 
	}
	static public Result_EWO_t decodeResult_EWO_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Result_EWO_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.C1 = com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.decodeC1_Section_t (ms, "C1");
		value.CTL = com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.decodeCTL_Section_t (ms, "CTL");
		{ 
			ms.startSequence ("RESP", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("RESP", false);
			{ 
				value.RESP = new com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.RESP[__i0] = com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tMsg.decodeRESP_t (ms, "RESP");
				} 
			}
			ms.endArray ("RESP", false);
			ms.endSequence ("RESP", false);
		}
		value.EWODATA = com.sbc.gwsvcs.service.facsaccess.interfaces.BLOB_tMsg.decodeBLOB_t (ms, "EWODATA");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeResult_EWO_t (MMarshalStrategy ms, Result_EWO_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.facsaccess.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_tMsg.encodeC1_Section_t (ms, value.C1, "C1");
		com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_tMsg.encodeCTL_Section_t (ms, value.CTL, "CTL");
		{ 
			ms.startSequence ("RESP", true);
			ms.encode (value.RESP.length, "_sequenceLength", true);
			ms.startArray ("RESP", true);
			{ 
				for (int __i0 = 0; __i0 < value.RESP.length; __i0++) { 
					com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_tMsg.encodeRESP_t (ms, value.RESP[__i0], "RESP");
				} 
			}
			ms.endArray ("RESP", true);
			ms.endSequence ("RESP", true);
		}
		com.sbc.gwsvcs.service.facsaccess.interfaces.BLOB_tMsg.encodeBLOB_t (ms, value.EWODATA, "EWODATA");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.Result_EWO_tHelper.type(); 
	}
	public static byte [] toOctet (Result_EWO_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeResult_EWO_t (ms, val, "Result_EWO_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Result_EWO_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeResult_EWO_t (ms, "Result_EWO_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.Result_EWO_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.Result_EWO_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.Result_EWO_t();
		value.Header = new com.sbc.gwsvcs.service.facsaccess.interfaces.Header_t();
		value.C1 = new com.sbc.gwsvcs.service.facsaccess.interfaces.C1_Section_t();
		value.CTL = new com.sbc.gwsvcs.service.facsaccess.interfaces.CTL_Section_t();
		int __seqLength = 0;
		value.RESP = new com.sbc.gwsvcs.service.facsaccess.interfaces.RESP_t[__seqLength];
		value.EWODATA = new com.sbc.gwsvcs.service.facsaccess.interfaces.BLOB_t();
		return value; 
	} 
}
