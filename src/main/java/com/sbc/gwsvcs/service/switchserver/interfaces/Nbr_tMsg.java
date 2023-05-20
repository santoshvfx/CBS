package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Nbr_tMsg implements MMarshalObject { 
	public Nbr_t value;

	public Nbr_tMsg () {
	}
	public Nbr_tMsg (Nbr_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeNbr_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeNbr_t (ms, tag); 
	}
	static public Nbr_t decodeNbr_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Nbr_t value = create();
		ms.startStruct (tag, false);
		value.TN = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "TN");
		value.TRMNTG_TRAF_AREA_CD = ms.decodeOctetString (7, "TRMNTG_TRAF_AREA_CD");
		value.BDY_DT = ms.decodeOctetString (3, "BDY_DT");
		value.Rls2Dt = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "Rls2Dt");
		value.MTCH_IND = ms.decodeOctetString (2, "MTCH_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeNbr_t (MMarshalStrategy ms, Nbr_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.TN, "TN");
		ms.encode (value.TRMNTG_TRAF_AREA_CD, 7, "TRMNTG_TRAF_AREA_CD");
		ms.encode (value.BDY_DT, 3, "BDY_DT");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.Rls2Dt, "Rls2Dt");
		ms.encode (value.MTCH_IND, 2, "MTCH_IND");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_tHelper.type(); 
	}
	public static byte [] toOctet (Nbr_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeNbr_t (ms, val, "Nbr_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Nbr_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeNbr_t (ms, "Nbr_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Nbr_t();
		value.TN = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t();
		value.TRMNTG_TRAF_AREA_CD = new String ();
		value.BDY_DT = new String ();
		value.Rls2Dt = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.MTCH_IND = new String ();
		return value; 
	} 
}
