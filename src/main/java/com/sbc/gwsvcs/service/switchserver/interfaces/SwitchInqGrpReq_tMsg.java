package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchInqGrpReq_tMsg implements MMarshalObject { 
	public SwitchInqGrpReq_t value;

	public SwitchInqGrpReq_tMsg () {
	}
	public SwitchInqGrpReq_tMsg (SwitchInqGrpReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchInqGrpReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchInqGrpReq_t (ms, tag); 
	}
	static public SwitchInqGrpReq_t decodeSwitchInqGrpReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchInqGrpReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.InqGrpReq = com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_tMsg.decodeInqGrpReq_t (ms, "InqGrpReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchInqGrpReq_t (MMarshalStrategy ms, SwitchInqGrpReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_tMsg.encodeInqGrpReq_t (ms, value.InqGrpReq, "InqGrpReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchInqGrpReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchInqGrpReq_t (ms, val, "SwitchInqGrpReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchInqGrpReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchInqGrpReq_t (ms, "SwitchInqGrpReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqGrpReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.InqGrpReq = new com.sbc.gwsvcs.service.switchserver.interfaces.InqGrpReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
