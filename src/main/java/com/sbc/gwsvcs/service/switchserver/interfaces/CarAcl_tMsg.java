package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CarAcl_tMsg implements MMarshalObject { 
	public CarAcl_t value;

	public CarAcl_tMsg () {
	}
	public CarAcl_tMsg (CarAcl_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCarAcl_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCarAcl_t (ms, tag); 
	}
	static public CarAcl_t decodeCarAcl_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CarAcl_t value = create();
		ms.startStruct (tag, false);
		value.CKT_TERMN_ID = ms.decodeOctetString (52, "CKT_TERMN_ID");
		value.ACTN_CD = ms.decodeOctetString (4, "ACTN_CD");
		value.SWITCH_ID = ms.decodeOctetString (46, "SWITCH_ID");
		value.NO_REUSE_IND = ms.decodeChar ("NO_REUSE_IND");
		value.TR_CALL_IND = ms.decodeOctetString (2, "TR_CALL_IND");
		value.TEL_LN_ID = com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.decodeNpaPrfxLn_t (ms, "TEL_LN_ID");
		value.SSP_IND = ms.decodeOctetString (2, "SSP_IND");
		value.FDT_TX = ms.decodeOctetString (6, "FDT_TX");
		value.FRM_RMK_TX = ms.decodeOctetString (29, "FRM_RMK_TX");
		value.CTRL_GRP_ID = ms.decodeOctetString (8, "CTRL_GRP_ID");
		value.AclCec = com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tMsg.decodeAclCec_t (ms, "AclCec");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCarAcl_t (MMarshalStrategy ms, CarAcl_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.CKT_TERMN_ID, 52, "CKT_TERMN_ID");
		ms.encode (value.ACTN_CD, 4, "ACTN_CD");
		ms.encode (value.SWITCH_ID, 46, "SWITCH_ID");
		ms.encode (value.NO_REUSE_IND, "NO_REUSE_IND");
		ms.encode (value.TR_CALL_IND, 2, "TR_CALL_IND");
		com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_tMsg.encodeNpaPrfxLn_t (ms, value.TEL_LN_ID, "TEL_LN_ID");
		ms.encode (value.SSP_IND, 2, "SSP_IND");
		ms.encode (value.FDT_TX, 6, "FDT_TX");
		ms.encode (value.FRM_RMK_TX, 29, "FRM_RMK_TX");
		ms.encode (value.CTRL_GRP_ID, 8, "CTRL_GRP_ID");
		com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_tMsg.encodeAclCec_t (ms, value.AclCec, "AclCec");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_tHelper.type(); 
	}
	public static byte [] toOctet (CarAcl_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCarAcl_t (ms, val, "CarAcl_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CarAcl_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCarAcl_t (ms, "CarAcl_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CarAcl_t();
		value.CKT_TERMN_ID = new String ();
		value.ACTN_CD = new String ();
		value.SWITCH_ID = new String ();
		value.TR_CALL_IND = new String ();
		value.TEL_LN_ID = new com.sbc.gwsvcs.service.switchserver.interfaces.NpaPrfxLn_t();
		value.SSP_IND = new String ();
		value.FDT_TX = new String ();
		value.FRM_RMK_TX = new String ();
		value.CTRL_GRP_ID = new String ();
		value.AclCec = new com.sbc.gwsvcs.service.switchserver.interfaces.AclCec_t();
		return value; 
	} 
}
