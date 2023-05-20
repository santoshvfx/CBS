package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TneSeltVarb_tMsg implements MMarshalObject { 
	public TneSeltVarb_t value;

	public TneSeltVarb_tMsg () {
	}
	public TneSeltVarb_tMsg (TneSeltVarb_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTneSeltVarb_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTneSeltVarb_t (ms, tag); 
	}
	static public TneSeltVarb_t decodeTneSeltVarb_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TneSeltVarb_t value = create();
		ms.startStruct (tag, false);
		value.QTY_CT = ms.decodeOctetString (7, "QTY_CT");
		value.NpaPrfx = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tMsg.decodeNpaPrfx_t (ms, "NpaPrfx");
		value.BDY_DT = ms.decodeOctetString (3, "BDY_DT");
		value.GoodInfo = com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_tMsg.decodeGoodInfo_t (ms, "GoodInfo");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTneSeltVarb_t (MMarshalStrategy ms, TneSeltVarb_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.QTY_CT, 7, "QTY_CT");
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_tMsg.encodeNpaPrfx_t (ms, value.NpaPrfx, "NpaPrfx");
		ms.encode (value.BDY_DT, 3, "BDY_DT");
		com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_tMsg.encodeGoodInfo_t (ms, value.GoodInfo, "GoodInfo");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_tHelper.type(); 
	}
	public static byte [] toOctet (TneSeltVarb_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTneSeltVarb_t (ms, val, "TneSeltVarb_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TneSeltVarb_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTneSeltVarb_t (ms, "TneSeltVarb_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TneSeltVarb_t();
		value.QTY_CT = new String ();
		value.NpaPrfx = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfx_t();
		value.BDY_DT = new String ();
		value.GoodInfo = new com.sbc.gwsvcs.service.switchserver.interfaces.GoodInfo_t();
		return value; 
	} 
}
