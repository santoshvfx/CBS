package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Pkt_tMsg implements MMarshalObject { 
	public Pkt_t value;

	public Pkt_tMsg () {
	}
	public Pkt_tMsg (Pkt_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePkt_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePkt_t (ms, tag); 
	}
	static public Pkt_t decodePkt_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Pkt_t value = create();
		ms.startStruct (tag, false);
		value.PROCSG_MODE_IND = ms.decodeOctetString (4, "PROCSG_MODE_IND");
		value.ORD_WRK_TASK_IND = ms.decodeOctetString (2, "ORD_WRK_TASK_IND");
		value.MSEG_CD = ms.decodeOctetString (2, "MSEG_CD");
		value.STS_3_CD = ms.decodeOctetString (4, "STS_3_CD");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodePkt_t (MMarshalStrategy ms, Pkt_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.PROCSG_MODE_IND, 4, "PROCSG_MODE_IND");
		ms.encode (value.ORD_WRK_TASK_IND, 2, "ORD_WRK_TASK_IND");
		ms.encode (value.MSEG_CD, 2, "MSEG_CD");
		ms.encode (value.STS_3_CD, 4, "STS_3_CD");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_tHelper.type(); 
	}
	public static byte [] toOctet (Pkt_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodePkt_t (ms, val, "Pkt_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Pkt_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodePkt_t (ms, "Pkt_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t();
		value.PROCSG_MODE_IND = new String ();
		value.ORD_WRK_TASK_IND = new String ();
		value.MSEG_CD = new String ();
		value.STS_3_CD = new String ();
		return value; 
	} 
}
