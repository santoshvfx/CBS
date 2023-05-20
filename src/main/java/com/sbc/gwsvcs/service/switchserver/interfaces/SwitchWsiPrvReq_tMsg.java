package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiPrvReq_tMsg implements MMarshalObject { 
	public SwitchWsiPrvReq_t value;

	public SwitchWsiPrvReq_tMsg () {
	}
	public SwitchWsiPrvReq_tMsg (SwitchWsiPrvReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchWsiPrvReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchWsiPrvReq_t (ms, tag); 
	}
	static public SwitchWsiPrvReq_t decodeSwitchWsiPrvReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchWsiPrvReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.InqEx = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "InqEx");
		value.DUAL_SVC_IND = ms.decodeChar ("DUAL_SVC_IND");
		value.ORD_TYPE_2_CD = ms.decodeOctetString (10, "ORD_TYPE_2_CD");
		value.ASGNM_ACTN_CD = ms.decodeOctetString (4, "ASGNM_ACTN_CD");
		value.VIEW_DT = ms.decodeOctetString (9, "VIEW_DT");
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		value.ORD_2_NBR = ms.decodeOctetString (13, "ORD_2_NBR");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchWsiPrvReq_t (MMarshalStrategy ms, SwitchWsiPrvReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.InqEx, "InqEx");
		ms.encode (value.DUAL_SVC_IND, "DUAL_SVC_IND");
		ms.encode (value.ORD_TYPE_2_CD, 10, "ORD_TYPE_2_CD");
		ms.encode (value.ASGNM_ACTN_CD, 4, "ASGNM_ACTN_CD");
		ms.encode (value.VIEW_DT, 9, "VIEW_DT");
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.encode (value.ORD_2_NBR, 13, "ORD_2_NBR");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchWsiPrvReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchWsiPrvReq_t (ms, val, "SwitchWsiPrvReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchWsiPrvReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchWsiPrvReq_t (ms, "SwitchWsiPrvReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.InqEx = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.ORD_TYPE_2_CD = new String ();
		value.ASGNM_ACTN_CD = new String ();
		value.VIEW_DT = new String ();
		value.SWITCH_WC = new String ();
		value.ORD_2_NBR = new String ();
		return value; 
	} 
}
