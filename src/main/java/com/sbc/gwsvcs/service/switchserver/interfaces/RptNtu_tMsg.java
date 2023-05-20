package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RptNtu_tMsg implements MMarshalObject { 
	public RptNtu_t value;

	public RptNtu_tMsg () {
	}
	public RptNtu_tMsg (RptNtu_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRptNtu_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRptNtu_t (ms, tag); 
	}
	static public RptNtu_t decodeRptNtu_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RptNtu_t value = create();
		ms.startStruct (tag, false);
		value.NU_TYPE_CD = ms.decodeOctetString (5, "NU_TYPE_CD");
		value.NU_LOW_ID = ms.decodeOctetString (25, "NU_LOW_ID");
		value.ExInport = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "ExInport");
		value.SELT_STATE_CD = ms.decodeOctetString (6, "SELT_STATE_CD");
		value.OPEN_SIDE_CD = ms.decodeOctetString (5, "OPEN_SIDE_CD");
		value.SWITCH_TN_REQ_QTY = ms.decodeOctetString (3, "SWITCH_TN_REQ_QTY");
		value.FOPTN = ms.decodeOctetString (2, "FOPTN");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeRptNtu_t (MMarshalStrategy ms, RptNtu_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.NU_TYPE_CD, 5, "NU_TYPE_CD");
		ms.encode (value.NU_LOW_ID, 25, "NU_LOW_ID");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.ExInport, "ExInport");
		ms.encode (value.SELT_STATE_CD, 6, "SELT_STATE_CD");
		ms.encode (value.OPEN_SIDE_CD, 5, "OPEN_SIDE_CD");
		ms.encode (value.SWITCH_TN_REQ_QTY, 3, "SWITCH_TN_REQ_QTY");
		ms.encode (value.FOPTN, 2, "FOPTN");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_tHelper.type(); 
	}
	public static byte [] toOctet (RptNtu_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeRptNtu_t (ms, val, "RptNtu_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static RptNtu_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeRptNtu_t (ms, "RptNtu_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_t();
		value.NU_TYPE_CD = new String ();
		value.NU_LOW_ID = new String ();
		value.ExInport = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.SELT_STATE_CD = new String ();
		value.OPEN_SIDE_CD = new String ();
		value.SWITCH_TN_REQ_QTY = new String ();
		value.FOPTN = new String ();
		return value; 
	} 
}
