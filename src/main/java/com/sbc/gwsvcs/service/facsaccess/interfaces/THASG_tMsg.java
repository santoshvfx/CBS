package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class THASG_tMsg implements MMarshalObject { 
	public THASG_t value;

	public THASG_tMsg () {
	}
	public THASG_tMsg (THASG_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeTHASG_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeTHASG_t (ms, tag); 
	}
	static public THASG_t decodeTHASG_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		THASG_t value = create();
		ms.startStruct (tag, false);
		value.TID = ms.decodeOctetString (7, "TID");
		value.WC = ms.decodeOctetString (9, "WC");
		value.PRIORI = ms.decodeOctetString (3, "PRIORI");
		value.EMP = ms.decodeOctetString (8, "EMP");
		value.EWO = ms.decodeOctetString (13, "EWO");
		value.TR = ms.decodeOctetString (8, "TR");
		value.PAGE = ms.decodeOctetString (4, "PAGE");
		value.FCA = ms.decodeOctetString (11, "FCA");
		value.FLPR = ms.decodeOctetString (5, "FLPR");
		value.FHPR = ms.decodeOctetString (5, "FHPR");
		value.TCA = ms.decodeOctetString (11, "TCA");
		value.TLPR = ms.decodeOctetString (5, "TLPR");
		value.THPR = ms.decodeOctetString (5, "THPR");
		value.FCA2 = ms.decodeOctetString (11, "FCA2");
		value.FLPR2 = ms.decodeOctetString (5, "FLPR2");
		value.FHPR2 = ms.decodeOctetString (5, "FHPR2");
		value.TCA2 = ms.decodeOctetString (11, "TCA2");
		value.TLPR2 = ms.decodeOctetString (5, "TLPR2");
		value.THPR2 = ms.decodeOctetString (5, "THPR2");
		value.FCA3 = ms.decodeOctetString (11, "FCA3");
		value.FLPR3 = ms.decodeOctetString (5, "FLPR3");
		value.FHPR3 = ms.decodeOctetString (5, "FHPR3");
		value.TCA3 = ms.decodeOctetString (11, "TCA3");
		value.TLPR3 = ms.decodeOctetString (5, "TLPR3");
		value.THPR3 = ms.decodeOctetString (5, "THPR3");
		value.FCA4 = ms.decodeOctetString (11, "FCA4");
		value.FLPR4 = ms.decodeOctetString (5, "FLPR4");
		value.FHPR4 = ms.decodeOctetString (5, "FHPR4");
		value.TCA4 = ms.decodeOctetString (11, "TCA4");
		value.TLPR4 = ms.decodeOctetString (5, "TLPR4");
		value.THPR4 = ms.decodeOctetString (5, "THPR4");
		value.FCA5 = ms.decodeOctetString (11, "FCA5");
		value.FLPR5 = ms.decodeOctetString (5, "FLPR5");
		value.FHPR5 = ms.decodeOctetString (5, "FHPR5");
		value.TCA5 = ms.decodeOctetString (11, "TCA5");
		value.TLPR5 = ms.decodeOctetString (5, "TLPR5");
		value.THPR5 = ms.decodeOctetString (5, "THPR5");
		value.FCA6 = ms.decodeOctetString (11, "FCA6");
		value.FLPR6 = ms.decodeOctetString (5, "FLPR6");
		value.FHPR6 = ms.decodeOctetString (5, "FHPR6");
		value.TCA6 = ms.decodeOctetString (11, "TCA6");
		value.TLPR6 = ms.decodeOctetString (5, "TLPR6");
		value.THPR6 = ms.decodeOctetString (5, "THPR6");
		value.DEST = ms.decodeOctetString (9, "DEST");
		value.INFO = ms.decodeOctetString (79, "INFO");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeTHASG_t (MMarshalStrategy ms, THASG_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TID, 7, "TID");
		ms.encode (value.WC, 9, "WC");
		ms.encode (value.PRIORI, 3, "PRIORI");
		ms.encode (value.EMP, 8, "EMP");
		ms.encode (value.EWO, 13, "EWO");
		ms.encode (value.TR, 8, "TR");
		ms.encode (value.PAGE, 4, "PAGE");
		ms.encode (value.FCA, 11, "FCA");
		ms.encode (value.FLPR, 5, "FLPR");
		ms.encode (value.FHPR, 5, "FHPR");
		ms.encode (value.TCA, 11, "TCA");
		ms.encode (value.TLPR, 5, "TLPR");
		ms.encode (value.THPR, 5, "THPR");
		ms.encode (value.FCA2, 11, "FCA2");
		ms.encode (value.FLPR2, 5, "FLPR2");
		ms.encode (value.FHPR2, 5, "FHPR2");
		ms.encode (value.TCA2, 11, "TCA2");
		ms.encode (value.TLPR2, 5, "TLPR2");
		ms.encode (value.THPR2, 5, "THPR2");
		ms.encode (value.FCA3, 11, "FCA3");
		ms.encode (value.FLPR3, 5, "FLPR3");
		ms.encode (value.FHPR3, 5, "FHPR3");
		ms.encode (value.TCA3, 11, "TCA3");
		ms.encode (value.TLPR3, 5, "TLPR3");
		ms.encode (value.THPR3, 5, "THPR3");
		ms.encode (value.FCA4, 11, "FCA4");
		ms.encode (value.FLPR4, 5, "FLPR4");
		ms.encode (value.FHPR4, 5, "FHPR4");
		ms.encode (value.TCA4, 11, "TCA4");
		ms.encode (value.TLPR4, 5, "TLPR4");
		ms.encode (value.THPR4, 5, "THPR4");
		ms.encode (value.FCA5, 11, "FCA5");
		ms.encode (value.FLPR5, 5, "FLPR5");
		ms.encode (value.FHPR5, 5, "FHPR5");
		ms.encode (value.TCA5, 11, "TCA5");
		ms.encode (value.TLPR5, 5, "TLPR5");
		ms.encode (value.THPR5, 5, "THPR5");
		ms.encode (value.FCA6, 11, "FCA6");
		ms.encode (value.FLPR6, 5, "FLPR6");
		ms.encode (value.FHPR6, 5, "FHPR6");
		ms.encode (value.TCA6, 11, "TCA6");
		ms.encode (value.TLPR6, 5, "TLPR6");
		ms.encode (value.THPR6, 5, "THPR6");
		ms.encode (value.DEST, 9, "DEST");
		ms.encode (value.INFO, 79, "INFO");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.THASG_tHelper.type(); 
	}
	public static byte [] toOctet (THASG_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeTHASG_t (ms, val, "THASG_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static THASG_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeTHASG_t (ms, "THASG_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.THASG_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.THASG_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.THASG_t();
		value.TID = new String ();
		value.WC = new String ();
		value.PRIORI = new String ();
		value.EMP = new String ();
		value.EWO = new String ();
		value.TR = new String ();
		value.PAGE = new String ();
		value.FCA = new String ();
		value.FLPR = new String ();
		value.FHPR = new String ();
		value.TCA = new String ();
		value.TLPR = new String ();
		value.THPR = new String ();
		value.FCA2 = new String ();
		value.FLPR2 = new String ();
		value.FHPR2 = new String ();
		value.TCA2 = new String ();
		value.TLPR2 = new String ();
		value.THPR2 = new String ();
		value.FCA3 = new String ();
		value.FLPR3 = new String ();
		value.FHPR3 = new String ();
		value.TCA3 = new String ();
		value.TLPR3 = new String ();
		value.THPR3 = new String ();
		value.FCA4 = new String ();
		value.FLPR4 = new String ();
		value.FHPR4 = new String ();
		value.TCA4 = new String ();
		value.TLPR4 = new String ();
		value.THPR4 = new String ();
		value.FCA5 = new String ();
		value.FLPR5 = new String ();
		value.FHPR5 = new String ();
		value.TCA5 = new String ();
		value.TLPR5 = new String ();
		value.THPR5 = new String ();
		value.FCA6 = new String ();
		value.FLPR6 = new String ();
		value.FHPR6 = new String ();
		value.TCA6 = new String ();
		value.TLPR6 = new String ();
		value.THPR6 = new String ();
		value.DEST = new String ();
		value.INFO = new String ();
		return value; 
	} 
}
