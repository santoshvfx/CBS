package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class AssignableTnReq_tMsg implements MMarshalObject { 
	public AssignableTnReq_t value;

	public AssignableTnReq_tMsg () {
	}
	public AssignableTnReq_tMsg (AssignableTnReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeAssignableTnReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeAssignableTnReq_t (ms, tag); 
	}
	static public AssignableTnReq_t decodeAssignableTnReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		AssignableTnReq_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.TN_HI_RNGE_ID = ms.decodeOctetString (25, "TN_HI_RNGE_ID");
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ic");
		value.SWITCH_TN_REQ_QTY = ms.decodeOctetString (3, "SWITCH_TN_REQ_QTY");
		value.TN_TYPE_CD = ms.decodeOctetString (2, "TN_TYPE_CD");
		value.RT_ZONE = ms.decodeOctetString (4, "RT_ZONE");
		value.CTX_NM = ms.decodeOctetString (19, "CTX_NM");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeAssignableTnReq_t (MMarshalStrategy ms, AssignableTnReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.TN_HI_RNGE_ID, 25, "TN_HI_RNGE_ID");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ic, "Ic");
		ms.encode (value.SWITCH_TN_REQ_QTY, 3, "SWITCH_TN_REQ_QTY");
		ms.encode (value.TN_TYPE_CD, 2, "TN_TYPE_CD");
		ms.encode (value.RT_ZONE, 4, "RT_ZONE");
		ms.encode (value.CTX_NM, 19, "CTX_NM");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_tHelper.type(); 
	}
	public static byte [] toOctet (AssignableTnReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeAssignableTnReq_t (ms, val, "AssignableTnReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static AssignableTnReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeAssignableTnReq_t (ms, "AssignableTnReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.AssignableTnReq_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.TN_HI_RNGE_ID = new String ();
		value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.SWITCH_TN_REQ_QTY = new String ();
		value.TN_TYPE_CD = new String ();
		value.RT_ZONE = new String ();
		value.CTX_NM = new String ();
		return value; 
	} 
}
