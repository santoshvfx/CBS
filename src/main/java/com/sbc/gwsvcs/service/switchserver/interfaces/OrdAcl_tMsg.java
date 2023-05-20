package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class OrdAcl_tMsg implements MMarshalObject { 
	public OrdAcl_t value;

	public OrdAcl_tMsg () {
	}
	public OrdAcl_tMsg (OrdAcl_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeOrdAcl_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeOrdAcl_t (ms, tag); 
	}
	static public OrdAcl_t decodeOrdAcl_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		OrdAcl_t value = create();
		ms.startStruct (tag, false);
		value.ACTN_CD = ms.decodeOctetString (4, "ACTN_CD");
		value.AclCec = com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tMsg.decodeAclCec_t (ms, "AclCec");
		value.ASGN_USOC_CD = ms.decodeOctetString (6, "ASGN_USOC_CD");
		value.CLS_SVC_USOC_CD = ms.decodeOctetString (6, "CLS_SVC_USOC_CD");
		value.USOC = ms.decodeOctetString (6, "USOC");
		value.TEL_LN_ID = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "TEL_LN_ID");
		value.FRM_RMK_TX = ms.decodeOctetString (29, "FRM_RMK_TX");
		value.CKT_2_ID = ms.decodeOctetString (52, "CKT_2_ID");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeOrdAcl_t (MMarshalStrategy ms, OrdAcl_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ACTN_CD, 4, "ACTN_CD");
		com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tMsg.encodeAclCec_t (ms, value.AclCec, "AclCec");
		ms.encode (value.ASGN_USOC_CD, 6, "ASGN_USOC_CD");
		ms.encode (value.CLS_SVC_USOC_CD, 6, "CLS_SVC_USOC_CD");
		ms.encode (value.USOC, 6, "USOC");
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.TEL_LN_ID, "TEL_LN_ID");
		ms.encode (value.FRM_RMK_TX, 29, "FRM_RMK_TX");
		ms.encode (value.CKT_2_ID, 52, "CKT_2_ID");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tHelper.type(); 
	}
	public static byte [] toOctet (OrdAcl_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeOrdAcl_t (ms, val, "OrdAcl_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static OrdAcl_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeOrdAcl_t (ms, "OrdAcl_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t();
		value.ACTN_CD = new String ();
		value.AclCec = new com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t();
		value.ASGN_USOC_CD = new String ();
		value.CLS_SVC_USOC_CD = new String ();
		value.USOC = new String ();
		value.TEL_LN_ID = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t();
		value.FRM_RMK_TX = new String ();
		value.CKT_2_ID = new String ();
		return value; 
	} 
}
