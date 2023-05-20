package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiTnlReq_tMsg implements MMarshalObject { 
	public SwitchWsiTnlReq_t value;

	public SwitchWsiTnlReq_tMsg () {
	}
	public SwitchWsiTnlReq_tMsg (SwitchWsiTnlReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchWsiTnlReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchWsiTnlReq_t (ms, tag); 
	}
	static public SwitchWsiTnlReq_t decodeSwitchWsiTnlReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchWsiTnlReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.InqEx = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "InqEx");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchWsiTnlReq_t (MMarshalStrategy ms, SwitchWsiTnlReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.InqEx, "InqEx");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchWsiTnlReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchWsiTnlReq_t (ms, val, "SwitchWsiTnlReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchWsiTnlReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchWsiTnlReq_t (ms, "SwitchWsiTnlReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiTnlReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.InqEx = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
