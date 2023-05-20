package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class OrdCtl_tMsg implements MMarshalObject { 
	public OrdCtl_t value;

	public OrdCtl_tMsg () {
	}
	public OrdCtl_tMsg (OrdCtl_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeOrdCtl_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeOrdCtl_t (ms, tag); 
	}
	static public OrdCtl_t decodeOrdCtl_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		OrdCtl_t value = create();
		ms.startStruct (tag, false);
		value.DATA_IND = ms.decodeOctetString (2, "DATA_IND");
		value.CTRL_CD = ms.decodeOctetString (2, "CTRL_CD");
		value.CKT_2_ID = ms.decodeOctetString (52, "CKT_2_ID");
		value.ASGNM_REQ_IND = ms.decodeOctetString (2, "ASGNM_REQ_IND");
		value.FRM_RMK_TX = ms.decodeOctetString (29, "FRM_RMK_TX");
		{ 
			ms.startSequence ("Trdata", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Trdata", false);
			{ 
				value.Trdata = new com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Trdata[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_tMsg.decodeTrdata_t (ms, "Trdata");
				} 
			}
			ms.endArray ("Trdata", false);
			ms.endSequence ("Trdata", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeOrdCtl_t (MMarshalStrategy ms, OrdCtl_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.DATA_IND, 2, "DATA_IND");
		ms.encode (value.CTRL_CD, 2, "CTRL_CD");
		ms.encode (value.CKT_2_ID, 52, "CKT_2_ID");
		ms.encode (value.ASGNM_REQ_IND, 2, "ASGNM_REQ_IND");
		ms.encode (value.FRM_RMK_TX, 29, "FRM_RMK_TX");
		{ 
			ms.startSequence ("Trdata", true);
			ms.encode (value.Trdata.length, "_sequenceLength", true);
			ms.startArray ("Trdata", true);
			{ 
				for (int __i0 = 0; __i0 < value.Trdata.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_tMsg.encodeTrdata_t (ms, value.Trdata[__i0], "Trdata");
				} 
			}
			ms.endArray ("Trdata", true);
			ms.endSequence ("Trdata", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tHelper.type(); 
	}
	public static byte [] toOctet (OrdCtl_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeOrdCtl_t (ms, val, "OrdCtl_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static OrdCtl_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeOrdCtl_t (ms, "OrdCtl_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t();
		value.DATA_IND = new String ();
		value.CTRL_CD = new String ();
		value.CKT_2_ID = new String ();
		value.ASGNM_REQ_IND = new String ();
		value.FRM_RMK_TX = new String ();
		int __seqLength = 0;
		value.Trdata = new com.sbc.gwsvcs.service.switchserver.interfaces.Trdata_t[__seqLength];
		return value; 
	} 
}
