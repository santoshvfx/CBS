// $Id: GcintcQInputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GcintcQInputMsg implements MMarshalObject { 
	public GcintcQInput value;

	public GcintcQInputMsg () {
	}
	public GcintcQInputMsg (GcintcQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcQInput();
value.ims_region = new String ();
value.ord_class = new String ();
value.app = new String ();
value.dd = new String ();
value.t_input = new String ();
value.entry_input = new String ();
value.admin_area = new String ();
value.swc = new String ();
value.total_interval = new String ();
value.npa = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGcintcQInput (ms, tag); 
	}
	static public GcintcQInput decodeGcintcQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GcintcQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.T1_flag = ms.decodeLong ("T1_flag");
		value.ord_class = ms.decodeOctetString (2, "ord_class");
		value.app = ms.decodeOctetString (7, "app");
		value.dd = ms.decodeOctetString (7, "dd");
		value.t_input = ms.decodeOctetString (2, "t_input");
		value.entry_input = ms.decodeOctetString (6, "entry_input");
		value.admin_area = ms.decodeOctetString (3, "admin_area");
		value.swc = ms.decodeOctetString (7, "swc");
		value.total_interval = ms.decodeOctetString (4, "total_interval");
		value.npa = ms.decodeOctetString (25, "npa");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGcintcQInput (ms, value, tag); 
	}
	static public void encodeGcintcQInput (MMarshalStrategy ms, GcintcQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.T1_flag, "T1_flag");
	ms.encode (value.ord_class, 2, "ord_class");
ms.encode (value.app, 7, "app");
ms.encode (value.dd, 7, "dd");
ms.encode (value.t_input, 2, "t_input");
ms.encode (value.entry_input, 6, "entry_input");
ms.encode (value.admin_area, 3, "admin_area");
ms.encode (value.swc, 7, "swc");
ms.encode (value.total_interval, 4, "total_interval");
ms.encode (value.npa, 25, "npa");
ms.endStruct (tag, true); 
}
public static GcintcQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeGcintcQInput (ms, "GcintcQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcQInputHelper.type(); 
}
public static byte [] toOctet (GcintcQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeGcintcQInput (ms, val, "GcintcQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
