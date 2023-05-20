// $Id: Rc1scnQInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Rc1scnQInputMsg implements MMarshalObject { 
	public Rc1scnQInput value;

	public Rc1scnQInputMsg () {
	}
	public Rc1scnQInputMsg (Rc1scnQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnQInput();
value.ims_region = new String ();
value.fmt = new String ();
value.ac_input = new String ();
value.fmtid = new String ();
value.type_input = new String ();
value.asvc = new String ();
value.zts = new String ();
value.dgec = new String ();
value.tysg = new String ();
value.clo = new String ();
value.cust = new String ();
value.ownr_trt = new String ();
value.cabssysid = new String ();
value.crissysid = new String ();
value.family_typ = new String ();
value.ccna = new String ();
value.lata = new String ();
value.scantyp = new String ();
value.fc = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRc1scnQInput (ms, tag); 
	}
	static public Rc1scnQInput decodeRc1scnQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Rc1scnQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.number_of_pages = ms.decodeLong ("number_of_pages");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.ac_input = ms.decodeOctetString (9, "ac_input");
		value.fmtid = ms.decodeOctetString (48, "fmtid");
		value.type_input = ms.decodeOctetString (6, "type_input");
		value.asvc = ms.decodeOctetString (12, "asvc");
		value.zts = ms.decodeOctetString (12, "zts");
		value.dgec = ms.decodeOctetString (14, "dgec");
		value.tysg = ms.decodeOctetString (7, "tysg");
		value.clo = ms.decodeOctetString (13, "clo");
		value.cust = ms.decodeOctetString (21, "cust");
		value.ownr_trt = ms.decodeOctetString (6, "ownr_trt");
		value.cabssysid = ms.decodeOctetString (20, "cabssysid");
		value.crissysid = ms.decodeOctetString (20, "crissysid");
		value.family_typ = ms.decodeOctetString (2, "family_typ");
		value.ccna = ms.decodeOctetString (4, "ccna");
		value.lata = ms.decodeOctetString (6, "lata");
		value.scantyp = ms.decodeOctetString (2, "scantyp");
		value.fc = ms.decodeOctetString (2, "fc");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRc1scnQInput (ms, value, tag); 
	}
	static public void encodeRc1scnQInput (MMarshalStrategy ms, Rc1scnQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.number_of_pages, "number_of_pages");
	ms.encode (value.fmt, 2, "fmt");
ms.encode (value.ac_input, 9, "ac_input");
ms.encode (value.fmtid, 48, "fmtid");
ms.encode (value.type_input, 6, "type_input");
ms.encode (value.asvc, 12, "asvc");
ms.encode (value.zts, 12, "zts");
ms.encode (value.dgec, 14, "dgec");
ms.encode (value.tysg, 7, "tysg");
ms.encode (value.clo, 13, "clo");
ms.encode (value.cust, 21, "cust");
ms.encode (value.ownr_trt, 6, "ownr_trt");
ms.encode (value.cabssysid, 20, "cabssysid");
ms.encode (value.crissysid, 20, "crissysid");
ms.encode (value.family_typ, 2, "family_typ");
ms.encode (value.ccna, 4, "ccna");
ms.encode (value.lata, 6, "lata");
ms.encode (value.scantyp, 2, "scantyp");
ms.encode (value.fc, 2, "fc");
ms.endStruct (tag, true); 
}
public static Rc1scnQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRc1scnQInput (ms, "Rc1scnQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnQInputHelper.type(); 
}
public static byte [] toOctet (Rc1scnQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRc1scnQInput (ms, val, "Rc1scnQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
