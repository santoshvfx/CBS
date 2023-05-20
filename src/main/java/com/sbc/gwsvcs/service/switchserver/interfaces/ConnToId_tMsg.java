package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ConnToId_tMsg implements MMarshalObject { 
	public ConnToId_t value;

	public ConnToId_tMsg () {
	}
	public ConnToId_tMsg (ConnToId_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeConnToId_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeConnToId_t (ms, tag); 
	}
	static public ConnToId_t decodeConnToId_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ConnToId_t value = create();
		ms.startStruct (tag, false);
		value.ConnTo = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "ConnTo");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeConnToId_t (MMarshalStrategy ms, ConnToId_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.ConnTo, "ConnTo");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_tHelper.type(); 
	}
	public static byte [] toOctet (ConnToId_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeConnToId_t (ms, val, "ConnToId_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ConnToId_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeConnToId_t (ms, "ConnToId_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t();
		value.ConnTo = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		return value; 
	} 
}
