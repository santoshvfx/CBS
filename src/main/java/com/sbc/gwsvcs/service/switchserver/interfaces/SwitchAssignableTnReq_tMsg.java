package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchAssignableTnReq_tMsg implements MMarshalObject { 
	public SwitchAssignableTnReq_t value;

	public SwitchAssignableTnReq_tMsg () {
	}
	public SwitchAssignableTnReq_tMsg (SwitchAssignableTnReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchAssignableTnReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchAssignableTnReq_t (ms, tag); 
	}
	static public SwitchAssignableTnReq_t decodeSwitchAssignableTnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchAssignableTnReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.AssignableTnReq = com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_tMsg.decodeAssignableTnReq_t (ms, "AssignableTnReq");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchAssignableTnReq_t (MMarshalStrategy ms, SwitchAssignableTnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_tMsg.encodeAssignableTnReq_t (ms, value.AssignableTnReq, "AssignableTnReq");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchAssignableTnReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchAssignableTnReq_t (ms, val, "SwitchAssignableTnReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchAssignableTnReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchAssignableTnReq_t (ms, "SwitchAssignableTnReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchAssignableTnReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.AssignableTnReq = new com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
