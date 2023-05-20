package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DsgnOldNew_tMsg implements MMarshalObject { 
	public DsgnOldNew_t value;

	public DsgnOldNew_tMsg () {
	}
	public DsgnOldNew_tMsg (DsgnOldNew_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDsgnOldNew_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDsgnOldNew_t (ms, tag); 
	}
	static public DsgnOldNew_t decodeDsgnOldNew_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DsgnOldNew_t value = create();
		ms.startStruct (tag, false);
		value.DsgnNew = com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_tMsg.decodeDsgnNew_t (ms, "DsgnNew");
		value.DsgnOld = com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_tMsg.decodeDsgnOld_t (ms, "DsgnOld");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeDsgnOldNew_t (MMarshalStrategy ms, DsgnOldNew_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_tMsg.encodeDsgnNew_t (ms, value.DsgnNew, "DsgnNew");
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_tMsg.encodeDsgnOld_t (ms, value.DsgnOld, "DsgnOld");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tHelper.type(); 
	}
	public static byte [] toOctet (DsgnOldNew_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeDsgnOldNew_t (ms, val, "DsgnOldNew_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static DsgnOldNew_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeDsgnOldNew_t (ms, "DsgnOldNew_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_t();
		value.DsgnNew = new com.sbc.gwsvcs.service.switchserver.interfaces.DsgnNew_t();
		value.DsgnOld = new com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOld_t();
		return value; 
	} 
}
