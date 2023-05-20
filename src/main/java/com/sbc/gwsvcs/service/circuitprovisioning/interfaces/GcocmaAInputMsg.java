// $Id: GcocmaAInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GcocmaAInputMsg implements MMarshalObject { 
	public GcocmaAInput value;

	public GcocmaAInputMsg () {
	}
	public GcocmaAInputMsg (GcocmaAInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaAInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaAInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaAInput();
value.ims_region = new String ();
value.clo = new String ();
value.ord = new String ();
value.ordtype = new String ();
value.orig = new String ();
value.cust = new String ();
value.app = new String ();
value.dd = new String ();
value.mdfr = new String ();
value.wco = new String ();
value.oco = new String ();
value.cco = new String ();
value.doc = new String ();
value.relord = new String ();
value.ndnw = new String ();
value.id = new String ();
value.sid = new String ();
value.ckt = new String ();
value.cid = new String ();
value.fmt = new String ();
value.actn = new String ();
value.old_id = new String ();
value.old_id_fmt = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGcocmaAInput (ms, tag); 
	}
	static public GcocmaAInput decodeGcocmaAInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GcocmaAInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.clo = ms.decodeOctetString (11, "clo");
		value.ord = ms.decodeOctetString (19, "ord");
		value.ordtype = ms.decodeOctetString (2, "ordtype");
		value.orig = ms.decodeOctetString (9, "orig");
		value.cust = ms.decodeOctetString (21, "cust");
		value.app = ms.decodeOctetString (7, "app");
		value.dd = ms.decodeOctetString (7, "dd");
		value.mdfr = ms.decodeOctetString (4, "mdfr");
		value.wco = ms.decodeOctetString (4, "wco");
		value.oco = ms.decodeOctetString (4, "oco");
		value.cco = ms.decodeOctetString (4, "cco");
		value.doc = ms.decodeOctetString (5, "doc");
		value.relord = ms.decodeOctetString (21, "relord");
		value.ndnw = ms.decodeOctetString (2, "ndnw");
		value.id = ms.decodeOctetString (7, "id");
		value.sid = ms.decodeOctetString (7, "sid");
		value.ckt = ms.decodeOctetString (4, "ckt");
		value.cid = ms.decodeOctetString (46, "cid");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.actn = ms.decodeOctetString (3, "actn");
		value.old_id = ms.decodeOctetString (46, "old_id");
		value.old_id_fmt = ms.decodeOctetString (2, "old_id_fmt");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGcocmaAInput (ms, value, tag); 
	}
	static public void encodeGcocmaAInput (MMarshalStrategy ms, GcocmaAInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.clo, 11, "clo");
ms.encode (value.ord, 19, "ord");
ms.encode (value.ordtype, 2, "ordtype");
ms.encode (value.orig, 9, "orig");
ms.encode (value.cust, 21, "cust");
ms.encode (value.app, 7, "app");
ms.encode (value.dd, 7, "dd");
ms.encode (value.mdfr, 4, "mdfr");
ms.encode (value.wco, 4, "wco");
ms.encode (value.oco, 4, "oco");
ms.encode (value.cco, 4, "cco");
ms.encode (value.doc, 5, "doc");
ms.encode (value.relord, 21, "relord");
ms.encode (value.ndnw, 2, "ndnw");
ms.encode (value.id, 7, "id");
ms.encode (value.sid, 7, "sid");
ms.encode (value.ckt, 4, "ckt");
ms.encode (value.cid, 46, "cid");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.actn, 3, "actn");
ms.encode (value.old_id, 46, "old_id");
ms.encode (value.old_id_fmt, 2, "old_id_fmt");
ms.endStruct (tag, true); 
}
public static GcocmaAInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeGcocmaAInput (ms, "GcocmaAInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaAInputHelper.type(); 
}
public static byte [] toOctet (GcocmaAInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeGcocmaAInput (ms, val, "GcocmaAInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
