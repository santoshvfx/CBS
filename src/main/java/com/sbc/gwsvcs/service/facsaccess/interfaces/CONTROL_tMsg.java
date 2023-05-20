package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CONTROL_tMsg implements MMarshalObject { 
	public CONTROL_t value;

	public CONTROL_tMsg () {
	}
	public CONTROL_tMsg (CONTROL_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCONTROL_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCONTROL_t (ms, tag); 
	}
	static public CONTROL_t decodeCONTROL_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CONTROL_t value = create();
		ms.startStruct (tag, false);
		value.TID = ms.decodeOctetString (7, "TID");
		value.WC = ms.decodeOctetString (9, "WC");
		value.EWO = ms.decodeOctetString (13, "EWO");
		value.TR = ms.decodeOctetString (8, "TR");
		value.RCTR = ms.decodeOctetString (8, "RCTR");
		value.EMP = ms.decodeOctetString (8, "EMP");
		value.PRIORI = ms.decodeOctetString (3, "PRIORI");
		value.RCLST = ms.decodeOctetString (2, "RCLST");
		value.RCBCT = ms.decodeOctetString (2, "RCBCT");
		value.RCBCF = ms.decodeOctetString (2, "RCBCF");
		value.RCLNMV = ms.decodeOctetString (2, "RCLNMV");
		value.COSMOS = ms.decodeOctetString (2, "COSMOS");
		value.WORKSH = ms.decodeOctetString (2, "WORKSH");
		value.LSTWS = ms.decodeOctetString (2, "LSTWS");
		value.SORT = ms.decodeOctetString (2, "SORT");
		value.COPIES = ms.decodeOctetString (2, "COPIES");
		value.DESTIN = ms.decodeOctetString (9, "DESTIN");
		value.LSTCOP = ms.decodeOctetString (2, "LSTCOP");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCONTROL_t (MMarshalStrategy ms, CONTROL_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TID, 7, "TID");
		ms.encode (value.WC, 9, "WC");
		ms.encode (value.EWO, 13, "EWO");
		ms.encode (value.TR, 8, "TR");
		ms.encode (value.RCTR, 8, "RCTR");
		ms.encode (value.EMP, 8, "EMP");
		ms.encode (value.PRIORI, 3, "PRIORI");
		ms.encode (value.RCLST, 2, "RCLST");
		ms.encode (value.RCBCT, 2, "RCBCT");
		ms.encode (value.RCBCF, 2, "RCBCF");
		ms.encode (value.RCLNMV, 2, "RCLNMV");
		ms.encode (value.COSMOS, 2, "COSMOS");
		ms.encode (value.WORKSH, 2, "WORKSH");
		ms.encode (value.LSTWS, 2, "LSTWS");
		ms.encode (value.SORT, 2, "SORT");
		ms.encode (value.COPIES, 2, "COPIES");
		ms.encode (value.DESTIN, 9, "DESTIN");
		ms.encode (value.LSTCOP, 2, "LSTCOP");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.CONTROL_tHelper.type(); 
	}
	public static byte [] toOctet (CONTROL_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCONTROL_t (ms, val, "CONTROL_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static CONTROL_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCONTROL_t (ms, "CONTROL_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.CONTROL_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.CONTROL_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.CONTROL_t();
		value.TID = new String ();
		value.WC = new String ();
		value.EWO = new String ();
		value.TR = new String ();
		value.RCTR = new String ();
		value.EMP = new String ();
		value.PRIORI = new String ();
		value.RCLST = new String ();
		value.RCBCT = new String ();
		value.RCBCF = new String ();
		value.RCLNMV = new String ();
		value.COSMOS = new String ();
		value.WORKSH = new String ();
		value.LSTWS = new String ();
		value.SORT = new String ();
		value.COPIES = new String ();
		value.DESTIN = new String ();
		value.LSTCOP = new String ();
		return value; 
	} 
}
