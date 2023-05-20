package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ExOldNew_tMsg implements MMarshalObject { 
	public ExOldNew_t value;

	public ExOldNew_tMsg () {
	}
	public ExOldNew_tMsg (ExOldNew_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeExOldNew_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeExOldNew_t (ms, tag); 
	}
	static public ExOldNew_t decodeExOldNew_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ExOldNew_t value = create();
		ms.startStruct (tag, false);
		value.Ex_Old = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex_Old");
		value.Ex_New = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex_New");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeExOldNew_t (MMarshalStrategy ms, ExOldNew_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex_Old, "Ex_Old");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex_New, "Ex_New");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tHelper.type(); 
	}
	public static byte [] toOctet (ExOldNew_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeExOldNew_t (ms, val, "ExOldNew_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static ExOldNew_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeExOldNew_t (ms, "ExOldNew_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t();
		value.Ex_Old = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.Ex_New = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		return value; 
	} 
}
