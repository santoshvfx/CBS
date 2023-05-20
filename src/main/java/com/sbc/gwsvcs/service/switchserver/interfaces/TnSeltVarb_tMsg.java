package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnSeltVarb_tMsg implements MMarshalObject { 
	public TnSeltVarb_t value;

	public TnSeltVarb_tMsg () {
	}
	public TnSeltVarb_tMsg (TnSeltVarb_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnSeltVarb_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnSeltVarb_t (ms, tag); 
	}
	static public TnSeltVarb_t decodeTnSeltVarb_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnSeltVarb_t value = create();
		ms.startStruct (tag, false);
		value.QTY_CT = ms.decodeOctetString (7, "QTY_CT");
		value.NpaPrfx = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tMsg.decodeNpaPrfx_t (ms, "NpaPrfx");
		value.BLG_DY = ms.decodeOctetString (3, "BLG_DY");
		value.SpcfcTn = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "SpcfcTn");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTnSeltVarb_t (MMarshalStrategy ms, TnSeltVarb_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.QTY_CT, 7, "QTY_CT");
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tMsg.encodeNpaPrfx_t (ms, value.NpaPrfx, "NpaPrfx");
		ms.encode (value.BLG_DY, 3, "BLG_DY");
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.SpcfcTn, "SpcfcTn");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_tHelper.type(); 
	}
	public static byte [] toOctet (TnSeltVarb_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTnSeltVarb_t (ms, val, "TnSeltVarb_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TnSeltVarb_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTnSeltVarb_t (ms, "TnSeltVarb_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TnSeltVarb_t();
		value.QTY_CT = new String ();
		value.NpaPrfx = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t();
		value.BLG_DY = new String ();
		value.SpcfcTn = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t();
		return value; 
	} 
}
