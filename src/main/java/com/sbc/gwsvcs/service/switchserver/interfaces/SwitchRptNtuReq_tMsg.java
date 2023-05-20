package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchRptNtuReq_tMsg implements MMarshalObject { 
	public SwitchRptNtuReq_t value;

	public SwitchRptNtuReq_tMsg () {
	}
	public SwitchRptNtuReq_tMsg (SwitchRptNtuReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchRptNtuReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchRptNtuReq_t (ms, tag); 
	}
	static public SwitchRptNtuReq_t decodeSwitchRptNtuReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchRptNtuReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.RptNtu = com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_tMsg.decodeRptNtu_t (ms, "RptNtu");
		value.RptOut = com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_tMsg.decodeRptOut_t (ms, "RptOut");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchRptNtuReq_t (MMarshalStrategy ms, SwitchRptNtuReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_tMsg.encodeRptNtu_t (ms, value.RptNtu, "RptNtu");
		com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_tMsg.encodeRptOut_t (ms, value.RptOut, "RptOut");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchRptNtuReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchRptNtuReq_t (ms, val, "SwitchRptNtuReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchRptNtuReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchRptNtuReq_t (ms, "SwitchRptNtuReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchRptNtuReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.RptNtu = new com.sbc.gwsvcs.service.switchserver.interfaces.RptNtu_t();
		value.RptOut = new com.sbc.gwsvcs.service.switchserver.interfaces.RptOut_t();
		value.SWITCH_WC = new String ();
		return value; 
	} 
}
