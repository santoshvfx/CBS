package com.sbc.gwsvcs.service.facsaccess.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SETUP_tMsg implements MMarshalObject { 
	public SETUP_t value;

	public SETUP_tMsg () {
	}
	public SETUP_tMsg (SETUP_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSETUP_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSETUP_t (ms, tag); 
	}
	static public SETUP_t decodeSETUP_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SETUP_t value = create();
		ms.startStruct (tag, false);
		value.TID = ms.decodeOctetString (7, "TID");
		value.WC = ms.decodeOctetString (9, "WC");
		value.PRIORI = ms.decodeOctetString (3, "PRIORI");
		value.EMP = ms.decodeOctetString (8, "EMP");
		value.EWO = ms.decodeOctetString (13, "EWO");
		value.TR = ms.decodeOctetString (8, "TR");
		value.DD = ms.decodeOctetString (9, "DD");
		value.PAGE = ms.decodeOctetString (4, "PAGE");
		value.FRCA = ms.decodeOctetString (11, "FRCA");
		value.FRLPR = ms.decodeOctetString (5, "FRLPR");
		value.FRHPR = ms.decodeOctetString (5, "FRHPR");
		value.TOCA = ms.decodeOctetString (11, "TOCA");
		value.TOLPR = ms.decodeOctetString (5, "TOLPR");
		value.TOHPR = ms.decodeOctetString (5, "TOHPR");
		value.FRCA2 = ms.decodeOctetString (11, "FRCA2");
		value.FRLPR2 = ms.decodeOctetString (5, "FRLPR2");
		value.FRHPR2 = ms.decodeOctetString (5, "FRHPR2");
		value.TOCA2 = ms.decodeOctetString (11, "TOCA2");
		value.TOLPR2 = ms.decodeOctetString (5, "TOLPR2");
		value.TOHPR2 = ms.decodeOctetString (5, "TOHPR2");
		value.FRCA3 = ms.decodeOctetString (11, "FRCA3");
		value.FRLPR3 = ms.decodeOctetString (5, "FRLPR3");
		value.FRHPR3 = ms.decodeOctetString (5, "FRHPR3");
		value.TOCA3 = ms.decodeOctetString (11, "TOCA3");
		value.TOLPR3 = ms.decodeOctetString (5, "TOLPR3");
		value.TOHPR3 = ms.decodeOctetString (5, "TOHPR3");
		value.FRCA4 = ms.decodeOctetString (11, "FRCA4");
		value.FRLPR4 = ms.decodeOctetString (5, "FRLPR4");
		value.FRHPR4 = ms.decodeOctetString (5, "FRHPR4");
		value.TOCA4 = ms.decodeOctetString (11, "TOCA4");
		value.TOLPR4 = ms.decodeOctetString (5, "TOLPR4");
		value.TOHPR4 = ms.decodeOctetString (5, "TOHPR4");
		value.FRCA5 = ms.decodeOctetString (11, "FRCA5");
		value.FRLPR5 = ms.decodeOctetString (5, "FRLPR5");
		value.FRHPR5 = ms.decodeOctetString (5, "FRHPR5");
		value.TOCA5 = ms.decodeOctetString (11, "TOCA5");
		value.TOLPR5 = ms.decodeOctetString (5, "TOLPR5");
		value.TOHPR5 = ms.decodeOctetString (5, "TOHPR5");
		value.FRCA6 = ms.decodeOctetString (11, "FRCA6");
		value.FRLPR6 = ms.decodeOctetString (5, "FRLPR6");
		value.FRHPR6 = ms.decodeOctetString (5, "FRHPR6");
		value.TOCA6 = ms.decodeOctetString (11, "TOCA6");
		value.TOLPR6 = ms.decodeOctetString (5, "TOLPR6");
		value.TOHPR6 = ms.decodeOctetString (5, "TOHPR6");
		value.FRCA7 = ms.decodeOctetString (11, "FRCA7");
		value.FRLPR7 = ms.decodeOctetString (5, "FRLPR7");
		value.FRHPR7 = ms.decodeOctetString (5, "FRHPR7");
		value.TOCA7 = ms.decodeOctetString (11, "TOCA7");
		value.TOLPR7 = ms.decodeOctetString (5, "TOLPR7");
		value.TOHPR7 = ms.decodeOctetString (5, "TOHPR7");
		value.FRCA8 = ms.decodeOctetString (11, "FRCA8");
		value.FRLPR8 = ms.decodeOctetString (5, "FRLPR8");
		value.FRHPR8 = ms.decodeOctetString (5, "FRHPR8");
		value.TOCA8 = ms.decodeOctetString (11, "TOCA8");
		value.TOLPR8 = ms.decodeOctetString (5, "TOLPR8");
		value.TOHPR8 = ms.decodeOctetString (5, "TOHPR8");
		value.FRCA9 = ms.decodeOctetString (11, "FRCA9");
		value.FRLPR9 = ms.decodeOctetString (5, "FRLPR9");
		value.FRHPR9 = ms.decodeOctetString (5, "FRHPR9");
		value.TOCA9 = ms.decodeOctetString (11, "TOCA9");
		value.TOLPR9 = ms.decodeOctetString (5, "TOLPR9");
		value.TOHPR9 = ms.decodeOctetString (5, "TOHPR9");
		value.FRCA10 = ms.decodeOctetString (11, "FRCA10");
		value.FRLPR10 = ms.decodeOctetString (5, "FRLPR10");
		value.FRHPR10 = ms.decodeOctetString (5, "FRHPR10");
		value.TOCA10 = ms.decodeOctetString (11, "TOCA10");
		value.TOLPR10 = ms.decodeOctetString (5, "TOLPR10");
		value.TOHPR10 = ms.decodeOctetString (5, "TOHPR10");
		value.FRCA11 = ms.decodeOctetString (11, "FRCA11");
		value.FRLPR11 = ms.decodeOctetString (5, "FRLPR11");
		value.FRHPR11 = ms.decodeOctetString (5, "FRHPR11");
		value.TOCA11 = ms.decodeOctetString (11, "TOCA11");
		value.TOLPR11 = ms.decodeOctetString (5, "TOLPR11");
		value.TOHPR11 = ms.decodeOctetString (5, "TOHPR11");
		value.FRCA12 = ms.decodeOctetString (11, "FRCA12");
		value.FRLPR12 = ms.decodeOctetString (5, "FRLPR12");
		value.FRHPR12 = ms.decodeOctetString (5, "FRHPR12");
		value.TOCA12 = ms.decodeOctetString (11, "TOCA12");
		value.TOLPR12 = ms.decodeOctetString (5, "TOLPR12");
		value.TOHPR12 = ms.decodeOctetString (5, "TOHPR12");
		value.FRCA13 = ms.decodeOctetString (11, "FRCA13");
		value.FRLPR13 = ms.decodeOctetString (5, "FRLPR13");
		value.FRHPR13 = ms.decodeOctetString (5, "FRHPR13");
		value.TOCA13 = ms.decodeOctetString (11, "TOCA13");
		value.TOLPR13 = ms.decodeOctetString (5, "TOLPR13");
		value.TOHPR13 = ms.decodeOctetString (5, "TOHPR13");
		value.FRCA14 = ms.decodeOctetString (11, "FRCA14");
		value.FRLPR14 = ms.decodeOctetString (5, "FRLPR14");
		value.FRHPR14 = ms.decodeOctetString (5, "FRHPR14");
		value.TOCA14 = ms.decodeOctetString (11, "TOCA14");
		value.TOLPR14 = ms.decodeOctetString (5, "TOLPR14");
		value.TOHPR14 = ms.decodeOctetString (5, "TOHPR14");
		value.FRCA15 = ms.decodeOctetString (11, "FRCA15");
		value.FRLPR15 = ms.decodeOctetString (5, "FRLPR15");
		value.FRHPR15 = ms.decodeOctetString (5, "FRHPR15");
		value.TOCA15 = ms.decodeOctetString (11, "TOCA15");
		value.TOLPR15 = ms.decodeOctetString (5, "TOLPR15");
		value.TOHPR15 = ms.decodeOctetString (5, "TOHPR15");
		value.PRINT = ms.decodeOctetString (2, "PRINT");
		value.DESTIN = ms.decodeOctetString (9, "DESTIN");
		value.INFO = ms.decodeOctetString (79, "INFO");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSETUP_t (MMarshalStrategy ms, SETUP_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TID, 7, "TID");
		ms.encode (value.WC, 9, "WC");
		ms.encode (value.PRIORI, 3, "PRIORI");
		ms.encode (value.EMP, 8, "EMP");
		ms.encode (value.EWO, 13, "EWO");
		ms.encode (value.TR, 8, "TR");
		ms.encode (value.DD, 9, "DD");
		ms.encode (value.PAGE, 4, "PAGE");
		ms.encode (value.FRCA, 11, "FRCA");
		ms.encode (value.FRLPR, 5, "FRLPR");
		ms.encode (value.FRHPR, 5, "FRHPR");
		ms.encode (value.TOCA, 11, "TOCA");
		ms.encode (value.TOLPR, 5, "TOLPR");
		ms.encode (value.TOHPR, 5, "TOHPR");
		ms.encode (value.FRCA2, 11, "FRCA2");
		ms.encode (value.FRLPR2, 5, "FRLPR2");
		ms.encode (value.FRHPR2, 5, "FRHPR2");
		ms.encode (value.TOCA2, 11, "TOCA2");
		ms.encode (value.TOLPR2, 5, "TOLPR2");
		ms.encode (value.TOHPR2, 5, "TOHPR2");
		ms.encode (value.FRCA3, 11, "FRCA3");
		ms.encode (value.FRLPR3, 5, "FRLPR3");
		ms.encode (value.FRHPR3, 5, "FRHPR3");
		ms.encode (value.TOCA3, 11, "TOCA3");
		ms.encode (value.TOLPR3, 5, "TOLPR3");
		ms.encode (value.TOHPR3, 5, "TOHPR3");
		ms.encode (value.FRCA4, 11, "FRCA4");
		ms.encode (value.FRLPR4, 5, "FRLPR4");
		ms.encode (value.FRHPR4, 5, "FRHPR4");
		ms.encode (value.TOCA4, 11, "TOCA4");
		ms.encode (value.TOLPR4, 5, "TOLPR4");
		ms.encode (value.TOHPR4, 5, "TOHPR4");
		ms.encode (value.FRCA5, 11, "FRCA5");
		ms.encode (value.FRLPR5, 5, "FRLPR5");
		ms.encode (value.FRHPR5, 5, "FRHPR5");
		ms.encode (value.TOCA5, 11, "TOCA5");
		ms.encode (value.TOLPR5, 5, "TOLPR5");
		ms.encode (value.TOHPR5, 5, "TOHPR5");
		ms.encode (value.FRCA6, 11, "FRCA6");
		ms.encode (value.FRLPR6, 5, "FRLPR6");
		ms.encode (value.FRHPR6, 5, "FRHPR6");
		ms.encode (value.TOCA6, 11, "TOCA6");
		ms.encode (value.TOLPR6, 5, "TOLPR6");
		ms.encode (value.TOHPR6, 5, "TOHPR6");
		ms.encode (value.FRCA7, 11, "FRCA7");
		ms.encode (value.FRLPR7, 5, "FRLPR7");
		ms.encode (value.FRHPR7, 5, "FRHPR7");
		ms.encode (value.TOCA7, 11, "TOCA7");
		ms.encode (value.TOLPR7, 5, "TOLPR7");
		ms.encode (value.TOHPR7, 5, "TOHPR7");
		ms.encode (value.FRCA8, 11, "FRCA8");
		ms.encode (value.FRLPR8, 5, "FRLPR8");
		ms.encode (value.FRHPR8, 5, "FRHPR8");
		ms.encode (value.TOCA8, 11, "TOCA8");
		ms.encode (value.TOLPR8, 5, "TOLPR8");
		ms.encode (value.TOHPR8, 5, "TOHPR8");
		ms.encode (value.FRCA9, 11, "FRCA9");
		ms.encode (value.FRLPR9, 5, "FRLPR9");
		ms.encode (value.FRHPR9, 5, "FRHPR9");
		ms.encode (value.TOCA9, 11, "TOCA9");
		ms.encode (value.TOLPR9, 5, "TOLPR9");
		ms.encode (value.TOHPR9, 5, "TOHPR9");
		ms.encode (value.FRCA10, 11, "FRCA10");
		ms.encode (value.FRLPR10, 5, "FRLPR10");
		ms.encode (value.FRHPR10, 5, "FRHPR10");
		ms.encode (value.TOCA10, 11, "TOCA10");
		ms.encode (value.TOLPR10, 5, "TOLPR10");
		ms.encode (value.TOHPR10, 5, "TOHPR10");
		ms.encode (value.FRCA11, 11, "FRCA11");
		ms.encode (value.FRLPR11, 5, "FRLPR11");
		ms.encode (value.FRHPR11, 5, "FRHPR11");
		ms.encode (value.TOCA11, 11, "TOCA11");
		ms.encode (value.TOLPR11, 5, "TOLPR11");
		ms.encode (value.TOHPR11, 5, "TOHPR11");
		ms.encode (value.FRCA12, 11, "FRCA12");
		ms.encode (value.FRLPR12, 5, "FRLPR12");
		ms.encode (value.FRHPR12, 5, "FRHPR12");
		ms.encode (value.TOCA12, 11, "TOCA12");
		ms.encode (value.TOLPR12, 5, "TOLPR12");
		ms.encode (value.TOHPR12, 5, "TOHPR12");
		ms.encode (value.FRCA13, 11, "FRCA13");
		ms.encode (value.FRLPR13, 5, "FRLPR13");
		ms.encode (value.FRHPR13, 5, "FRHPR13");
		ms.encode (value.TOCA13, 11, "TOCA13");
		ms.encode (value.TOLPR13, 5, "TOLPR13");
		ms.encode (value.TOHPR13, 5, "TOHPR13");
		ms.encode (value.FRCA14, 11, "FRCA14");
		ms.encode (value.FRLPR14, 5, "FRLPR14");
		ms.encode (value.FRHPR14, 5, "FRHPR14");
		ms.encode (value.TOCA14, 11, "TOCA14");
		ms.encode (value.TOLPR14, 5, "TOLPR14");
		ms.encode (value.TOHPR14, 5, "TOHPR14");
		ms.encode (value.FRCA15, 11, "FRCA15");
		ms.encode (value.FRLPR15, 5, "FRLPR15");
		ms.encode (value.FRHPR15, 5, "FRHPR15");
		ms.encode (value.TOCA15, 11, "TOCA15");
		ms.encode (value.TOLPR15, 5, "TOLPR15");
		ms.encode (value.TOHPR15, 5, "TOHPR15");
		ms.encode (value.PRINT, 2, "PRINT");
		ms.encode (value.DESTIN, 9, "DESTIN");
		ms.encode (value.INFO, 79, "INFO");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_tHelper.type(); 
	}
	public static byte [] toOctet (SETUP_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSETUP_t (ms, val, "SETUP_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SETUP_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSETUP_t (ms, "SETUP_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t create () { 
		com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t value = new com.sbc.gwsvcs.service.facsaccess.interfaces.SETUP_t();
		value.TID = new String ();
		value.WC = new String ();
		value.PRIORI = new String ();
		value.EMP = new String ();
		value.EWO = new String ();
		value.TR = new String ();
		value.DD = new String ();
		value.PAGE = new String ();
		value.FRCA = new String ();
		value.FRLPR = new String ();
		value.FRHPR = new String ();
		value.TOCA = new String ();
		value.TOLPR = new String ();
		value.TOHPR = new String ();
		value.FRCA2 = new String ();
		value.FRLPR2 = new String ();
		value.FRHPR2 = new String ();
		value.TOCA2 = new String ();
		value.TOLPR2 = new String ();
		value.TOHPR2 = new String ();
		value.FRCA3 = new String ();
		value.FRLPR3 = new String ();
		value.FRHPR3 = new String ();
		value.TOCA3 = new String ();
		value.TOLPR3 = new String ();
		value.TOHPR3 = new String ();
		value.FRCA4 = new String ();
		value.FRLPR4 = new String ();
		value.FRHPR4 = new String ();
		value.TOCA4 = new String ();
		value.TOLPR4 = new String ();
		value.TOHPR4 = new String ();
		value.FRCA5 = new String ();
		value.FRLPR5 = new String ();
		value.FRHPR5 = new String ();
		value.TOCA5 = new String ();
		value.TOLPR5 = new String ();
		value.TOHPR5 = new String ();
		value.FRCA6 = new String ();
		value.FRLPR6 = new String ();
		value.FRHPR6 = new String ();
		value.TOCA6 = new String ();
		value.TOLPR6 = new String ();
		value.TOHPR6 = new String ();
		value.FRCA7 = new String ();
		value.FRLPR7 = new String ();
		value.FRHPR7 = new String ();
		value.TOCA7 = new String ();
		value.TOLPR7 = new String ();
		value.TOHPR7 = new String ();
		value.FRCA8 = new String ();
		value.FRLPR8 = new String ();
		value.FRHPR8 = new String ();
		value.TOCA8 = new String ();
		value.TOLPR8 = new String ();
		value.TOHPR8 = new String ();
		value.FRCA9 = new String ();
		value.FRLPR9 = new String ();
		value.FRHPR9 = new String ();
		value.TOCA9 = new String ();
		value.TOLPR9 = new String ();
		value.TOHPR9 = new String ();
		value.FRCA10 = new String ();
		value.FRLPR10 = new String ();
		value.FRHPR10 = new String ();
		value.TOCA10 = new String ();
		value.TOLPR10 = new String ();
		value.TOHPR10 = new String ();
		value.FRCA11 = new String ();
		value.FRLPR11 = new String ();
		value.FRHPR11 = new String ();
		value.TOCA11 = new String ();
		value.TOLPR11 = new String ();
		value.TOHPR11 = new String ();
		value.FRCA12 = new String ();
		value.FRLPR12 = new String ();
		value.FRHPR12 = new String ();
		value.TOCA12 = new String ();
		value.TOLPR12 = new String ();
		value.TOHPR12 = new String ();
		value.FRCA13 = new String ();
		value.FRLPR13 = new String ();
		value.FRHPR13 = new String ();
		value.TOCA13 = new String ();
		value.TOLPR13 = new String ();
		value.TOHPR13 = new String ();
		value.FRCA14 = new String ();
		value.FRLPR14 = new String ();
		value.FRHPR14 = new String ();
		value.TOCA14 = new String ();
		value.TOLPR14 = new String ();
		value.TOHPR14 = new String ();
		value.FRCA15 = new String ();
		value.FRLPR15 = new String ();
		value.FRHPR15 = new String ();
		value.TOCA15 = new String ();
		value.TOLPR15 = new String ();
		value.TOHPR15 = new String ();
		value.PRINT = new String ();
		value.DESTIN = new String ();
		value.INFO = new String ();
		return value; 
	} 
}
