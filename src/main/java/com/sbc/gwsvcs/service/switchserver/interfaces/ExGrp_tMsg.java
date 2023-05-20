package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ExGrp_tMsg implements MMarshalObject { 
	public ExGrp_t value;

	public ExGrp_tMsg () {
	}
	public ExGrp_tMsg (ExGrp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeExGrp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeExGrp_t (ms, tag); 
	}
	static public ExGrp_t decodeExGrp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ExGrp_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.GRP_ID = ms.decodeOctetString (5, "GRP_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeExGrp_t (MMarshalStrategy ms, ExGrp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.GRP_ID, 5, "GRP_ID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_tHelper.type(); 
	}
	public static byte [] toOctet (ExGrp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeExGrp_t (ms, val, "ExGrp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ExGrp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeExGrp_t (ms, "ExGrp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.ExGrp_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.GRP_ID = new String ();
		return value; 
	} 
}
