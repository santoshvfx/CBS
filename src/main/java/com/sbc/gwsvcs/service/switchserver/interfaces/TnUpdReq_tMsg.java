package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class TnUpdReq_tMsg implements MMarshalObject { 
	public TnUpdReq_t value;

	public TnUpdReq_tMsg () {
	}
	public TnUpdReq_tMsg (TnUpdReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTnUpdReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTnUpdReq_t (ms, tag); 
	}
	static public TnUpdReq_t decodeTnUpdReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		TnUpdReq_t value = create();
		ms.startStruct (tag, false);
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ex");
		value.TN_HI_RNGE_ID = ms.decodeOctetString (25, "TN_HI_RNGE_ID");
		value.TN_MASK_ID = ms.decodeOctetString (25, "TN_MASK_ID");
		value.TN_PARSE_CD = ms.decodeOctetString (5, "TN_PARSE_CD");
		value.TN_UPD_FCN_CD = ms.decodeOctetString (4, "TN_UPD_FCN_CD");
		value.OPTNL_MSG_TX = ms.decodeOctetString (10, "OPTNL_MSG_TX");
		value.INQ_CHNG_DT_TM = com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.decodeDtTm_t (ms, "INQ_CHNG_DT_TM");
		value.INVNTY_ORD_NBR = ms.decodeOctetString (14, "INVNTY_ORD_NBR");
		value.STEP_ID = ms.decodeOctetString (4, "STEP_ID");
		value.Updopt = com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_tMsg.decodeUpdopt_t (ms, "Updopt");
		value.ACTN_CD = ms.decodeOctetString (4, "ACTN_CD");
		value.ACTN_CD_MEMB = ms.decodeOctetString (4, "ACTN_CD_MEMB");
		value.Ntu = com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tMsg.decodeNtu_t (ms, "Ntu");
		value.SOURCE = ms.decodeOctetString (7, "SOURCE");
		value.PORT_IND = ms.decodeOctetString (2, "PORT_IND");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTnUpdReq_t (MMarshalStrategy ms, TnUpdReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ex, "Ex");
		ms.encode (value.TN_HI_RNGE_ID, 25, "TN_HI_RNGE_ID");
		ms.encode (value.TN_MASK_ID, 25, "TN_MASK_ID");
		ms.encode (value.TN_PARSE_CD, 5, "TN_PARSE_CD");
		ms.encode (value.TN_UPD_FCN_CD, 4, "TN_UPD_FCN_CD");
		ms.encode (value.OPTNL_MSG_TX, 10, "OPTNL_MSG_TX");
		com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_tMsg.encodeDtTm_t (ms, value.INQ_CHNG_DT_TM, "INQ_CHNG_DT_TM");
		ms.encode (value.INVNTY_ORD_NBR, 14, "INVNTY_ORD_NBR");
		ms.encode (value.STEP_ID, 4, "STEP_ID");
		com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_tMsg.encodeUpdopt_t (ms, value.Updopt, "Updopt");
		ms.encode (value.ACTN_CD, 4, "ACTN_CD");
		ms.encode (value.ACTN_CD_MEMB, 4, "ACTN_CD_MEMB");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_tMsg.encodeNtu_t (ms, value.Ntu, "Ntu");
		ms.encode (value.SOURCE, 7, "SOURCE");
		ms.encode (value.PORT_IND, 2, "PORT_IND");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_tHelper.type(); 
	}
	public static byte [] toOctet (TnUpdReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTnUpdReq_t (ms, val, "TnUpdReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static TnUpdReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTnUpdReq_t (ms, "TnUpdReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.TnUpdReq_t();
		value.Ex = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.TN_HI_RNGE_ID = new String ();
		value.TN_MASK_ID = new String ();
		value.TN_PARSE_CD = new String ();
		value.TN_UPD_FCN_CD = new String ();
		value.OPTNL_MSG_TX = new String ();
		value.INQ_CHNG_DT_TM = new com.sbc.gwsvcs.service.switchserver.interfaces.DtTm_t();
		value.INVNTY_ORD_NBR = new String ();
		value.STEP_ID = new String ();
		value.Updopt = new com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t();
		value.ACTN_CD = new String ();
		value.ACTN_CD_MEMB = new String ();
		value.Ntu = new com.sbc.gwsvcs.service.switchserver.interfaces.Ntu_t();
		value.SOURCE = new String ();
		value.PORT_IND = new String ();
		return value; 
	} 
}
