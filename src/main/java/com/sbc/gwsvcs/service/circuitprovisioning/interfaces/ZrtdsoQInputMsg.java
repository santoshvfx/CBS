// $Id: ZrtdsoQInputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrtdsoQInputMsg implements MMarshalObject { 
	public ZrtdsoQInput value;

	public ZrtdsoQInputMsg () {
	}
	public ZrtdsoQInputMsg (ZrtdsoQInput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoQInput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoQInput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoQInput();
value.ims_region = new String ();
value.tablename = new String ();
value.tablekey = new String ();
value.adminarea = new String ();
value.tablerecord = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrtdsoQInput (ms, tag); 
	}
	static public ZrtdsoQInput decodeZrtdsoQInput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrtdsoQInput value = create();
		ms.startStruct (tag, false);
		value.ims_region = ms.decodeOctetString (9, "ims_region");
		value.tablename = ms.decodeOctetString (17, "tablename");
		value.tablekey = ms.decodeOctetString (16, "tablekey");
		value.adminarea = ms.decodeOctetString (3, "adminarea");
		value.tablerecord = ms.decodeOctetString (25, "tablerecord");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrtdsoQInput (ms, value, tag); 
	}
	static public void encodeZrtdsoQInput (MMarshalStrategy ms, ZrtdsoQInput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.ims_region, 9, "ims_region");
	ms.encode (value.tablename, 17, "tablename");
ms.encode (value.tablekey, 16, "tablekey");
ms.encode (value.adminarea, 3, "adminarea");
ms.encode (value.tablerecord, 25, "tablerecord");
ms.endStruct (tag, true); 
}
public static ZrtdsoQInput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrtdsoQInput (ms, "ZrtdsoQInput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoQInputHelper.type(); 
}
public static byte [] toOctet (ZrtdsoQInput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrtdsoQInput (ms, val, "ZrtdsoQInput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
