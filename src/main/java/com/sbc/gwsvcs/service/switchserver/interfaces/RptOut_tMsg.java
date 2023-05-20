package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RptOut_tMsg implements MMarshalObject { 
	public RptOut_t value;

	public RptOut_tMsg () {
	}
	public RptOut_tMsg (RptOut_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRptOut_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRptOut_t (ms, tag); 
	}
	static public RptOut_t decodeRptOut_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RptOut_t value = create();
		ms.startStruct (tag, false);
		value.INPT_SOPTN_CD = ms.decodeOctetString (51, "INPT_SOPTN_CD");
		value.OTPT_SOPTN_CD = ms.decodeOctetString (51, "OTPT_SOPTN_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeRptOut_t (MMarshalStrategy ms, RptOut_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.INPT_SOPTN_CD, 51, "INPT_SOPTN_CD");
		ms.encode (value.OTPT_SOPTN_CD, 51, "OTPT_SOPTN_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_tHelper.type(); 
	}
	public static byte [] toOctet (RptOut_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeRptOut_t (ms, val, "RptOut_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static RptOut_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeRptOut_t (ms, "RptOut_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_t();
		value.INPT_SOPTN_CD = new String ();
		value.OTPT_SOPTN_CD = new String ();
		return value; 
	} 
}
