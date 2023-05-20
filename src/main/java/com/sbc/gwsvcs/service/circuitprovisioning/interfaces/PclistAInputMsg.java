// $Id: PclistAInputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PclistAInputMsg implements MMarshalObject { 
	public PclistAInput value;

	public PclistAInputMsg () {
	}
	public PclistAInputMsg (PclistAInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistAInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistAInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistAInput();
value.ims_region = new String ();
value.rro = new String ();
value.pos = new String ();
value.uac = new String ();
value.c = new String ();
value.fcn = new String ();
value.date = new String ();
value.clo = new String ();
value.seg = new String ();
value.ckl = new String ();
value.order = new String ();
value.cktid = new String ();
value.cust = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePclistAInput (ms, tag); 
	}
	static public PclistAInput decodePclistAInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PclistAInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.rro = ms.decodeOctetString (4, "rro");
		value.pos = ms.decodeOctetString (4, "pos");
		value.uac = ms.decodeOctetString (5, "uac");
		value.c = ms.decodeOctetString (2, "c");
		value.fcn = ms.decodeOctetString (6, "fcn");
		value.date = ms.decodeOctetString (7, "date");
		value.clo = ms.decodeOctetString (10, "clo");
		value.seg = ms.decodeOctetString (4, "seg");
		value.ckl = ms.decodeOctetString (5, "ckl");
		value.order = ms.decodeOctetString (19, "order");
		value.cktid = ms.decodeOctetString (46, "cktid");
		value.cust = ms.decodeOctetString (21, "cust");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePclistAInput (ms, value, tag); 
	}
	static public void encodePclistAInput (MMarshalStrategy ms, PclistAInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.rro, 4, "rro");
ms.encode (value.pos, 4, "pos");
ms.encode (value.uac, 5, "uac");
ms.encode (value.c, 2, "c");
ms.encode (value.fcn, 6, "fcn");
ms.encode (value.date, 7, "date");
ms.encode (value.clo, 10, "clo");
ms.encode (value.seg, 4, "seg");
ms.encode (value.ckl, 5, "ckl");
ms.encode (value.order, 19, "order");
ms.encode (value.cktid, 46, "cktid");
ms.encode (value.cust, 21, "cust");
ms.endStruct (tag, true); 
}
public static PclistAInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodePclistAInput (ms, "PclistAInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistAInputHelper.type(); 
}
public static byte [] toOctet (PclistAInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodePclistAInput (ms, val, "PclistAInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
