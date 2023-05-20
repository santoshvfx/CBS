package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CarRec_tMsg implements MMarshalObject { 
	public CarRec_t value;

	public CarRec_tMsg () {
	}
	public CarRec_tMsg (CarRec_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCarRec_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCarRec_t (ms, tag); 
	}
	static public CarRec_t decodeCarRec_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CarRec_t value = create();
		ms.startStruct (tag, false);
		value.CTRL_CD = ms.decodeOctetString (2, "CTRL_CD");
		value.ASGNM_REQ_IND = ms.decodeOctetString (2, "ASGNM_REQ_IND");
		value.FDT_TX = ms.decodeOctetString (6, "FDT_TX");
		value.FRM_RMK_TX = ms.decodeOctetString (29, "FRM_RMK_TX");
		value.CKT_TERMN_ID = ms.decodeOctetString (52, "CKT_TERMN_ID");
		value.ORD_TYPE_CD = ms.decodeOctetString (2, "ORD_TYPE_CD");
		value.CORR_CD = ms.decodeOctetString (2, "CORR_CD");
		{ 
			ms.startSequence ("CarAcl", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("CarAcl", false);
			{ 
				value.CarAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CarAcl[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_tMsg.decodeCarAcl_t (ms, "CarAcl");
				} 
			}
			ms.endArray ("CarAcl", false);
			ms.endSequence ("CarAcl", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCarRec_t (MMarshalStrategy ms, CarRec_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CTRL_CD, 2, "CTRL_CD");
		ms.encode (value.ASGNM_REQ_IND, 2, "ASGNM_REQ_IND");
		ms.encode (value.FDT_TX, 6, "FDT_TX");
		ms.encode (value.FRM_RMK_TX, 29, "FRM_RMK_TX");
		ms.encode (value.CKT_TERMN_ID, 52, "CKT_TERMN_ID");
		ms.encode (value.ORD_TYPE_CD, 2, "ORD_TYPE_CD");
		ms.encode (value.CORR_CD, 2, "CORR_CD");
		{ 
			ms.startSequence ("CarAcl", true);
			ms.encode (value.CarAcl.length, "_sequenceLength", true);
			ms.startArray ("CarAcl", true);
			{ 
				for (int __i0 = 0; __i0 < value.CarAcl.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_tMsg.encodeCarAcl_t (ms, value.CarAcl[__i0], "CarAcl");
				} 
			}
			ms.endArray ("CarAcl", true);
			ms.endSequence ("CarAcl", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_tHelper.type(); 
	}
	public static byte [] toOctet (CarRec_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCarRec_t (ms, val, "CarRec_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CarRec_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCarRec_t (ms, "CarRec_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t();
		value.CTRL_CD = new String ();
		value.ASGNM_REQ_IND = new String ();
		value.FDT_TX = new String ();
		value.FRM_RMK_TX = new String ();
		value.CKT_TERMN_ID = new String ();
		value.ORD_TYPE_CD = new String ();
		value.CORR_CD = new String ();
		int __seqLength = 0;
		value.CarAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t[__seqLength];
		return value; 
	} 
}
