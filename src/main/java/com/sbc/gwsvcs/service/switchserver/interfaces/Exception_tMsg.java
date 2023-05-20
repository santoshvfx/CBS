package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Exception_tMsg implements MMarshalObject { 
	public Exception_t value;

	public Exception_tMsg () {
	}
	public Exception_tMsg (Exception_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeException_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeException_t (ms, tag); 
	}
	static public Exception_t decodeException_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Exception_t value = create();
		ms.startStruct (tag, false);
		value.HtErrCd = ms.decodeLong ("HtErrCd");
		value.HostCnvrstnGrp = ms.decodeOctetString (121, "HostCnvrstnGrp");
		value.HtErrLogTx = ms.decodeOctetString (121, "HtErrLogTx");
		value.HtErrTx = ms.decodeOctetString (121, "HtErrTx");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeException_t (MMarshalStrategy ms, Exception_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.HtErrCd, "HtErrCd");
		ms.encode (value.HostCnvrstnGrp, 121, "HostCnvrstnGrp");
		ms.encode (value.HtErrLogTx, 121, "HtErrLogTx");
		ms.encode (value.HtErrTx, 121, "HtErrTx");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Exception_tHelper.type(); 
	}
	public static byte [] toOctet (Exception_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeException_t (ms, val, "Exception_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Exception_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeException_t (ms, "Exception_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Exception_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Exception_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Exception_t();
		value.HostCnvrstnGrp = new String ();
		value.HtErrLogTx = new String ();
		value.HtErrTx = new String ();
		return value; 
	} 
}
