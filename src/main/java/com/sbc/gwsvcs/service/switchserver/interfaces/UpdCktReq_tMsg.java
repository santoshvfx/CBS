package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class UpdCktReq_tMsg implements MMarshalObject { 
	public UpdCktReq_t value;

	public UpdCktReq_tMsg () {
	}
	public UpdCktReq_tMsg (UpdCktReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUpdCktReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUpdCktReq_t (ms, tag); 
	}
	static public UpdCktReq_t decodeUpdCktReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		UpdCktReq_t value = create();
		ms.startStruct (tag, false);
		value.Ctl = com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tMsg.decodeCtl_t (ms, "Ctl");
		value.Svc = com.sbc.gwsvcs.service.switchserver.interfaces.Svc_tMsg.decodeSvc_t (ms, "Svc");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeUpdCktReq_t (MMarshalStrategy ms, UpdCktReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tMsg.encodeCtl_t (ms, value.Ctl, "Ctl");
		com.sbc.gwsvcs.service.switchserver.interfaces.Svc_tMsg.encodeSvc_t (ms, value.Svc, "Svc");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_tHelper.type(); 
	}
	public static byte [] toOctet (UpdCktReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUpdCktReq_t (ms, val, "UpdCktReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static UpdCktReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUpdCktReq_t (ms, "UpdCktReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.UpdCktReq_t();
		value.Ctl = new com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_t();
		value.Svc = new com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t();
		return value; 
	} 
}
