// $Id: ZrtdsoGrpDefMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrtdsoGrpDefMsg implements MMarshalObject { 
	public ZrtdsoGrpDef value;

	public ZrtdsoGrpDefMsg () {
	}
	public ZrtdsoGrpDefMsg (ZrtdsoGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDef();
value.fieldname = new String ();
value.fieldvalue = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrtdsoGrpDef (ms, tag); 
	}
	static public ZrtdsoGrpDef decodeZrtdsoGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrtdsoGrpDef value = create();
		ms.startStruct (tag, false);
		value.fieldname = ms.decodeOctetString (9, "fieldname");
		value.fieldvalue = ms.decodeOctetString (61, "fieldvalue");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrtdsoGrpDef (ms, value, tag); 
	}
	static public void encodeZrtdsoGrpDef (MMarshalStrategy ms, ZrtdsoGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.fieldname, 9, "fieldname");
	ms.encode (value.fieldvalue, 61, "fieldvalue");
ms.endStruct (tag, true); 
}
public static ZrtdsoGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(val), false);
	ms.setRemote (ms.decodeBoolean (null));
	return decodeZrtdsoGrpDef (ms, "ZrtdsoGrpDef"); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrtdsoGrpDefHelper.type(); 
}
public static byte [] toOctet (ZrtdsoGrpDef val) throws MMarshalException { 
try {
	        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
		          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
	ms.init (new MBuffer(), true);
	ms.encode (false, null);
	encodeZrtdsoGrpDef (ms, val, "ZrtdsoGrpDef");
	MBuffer buf = ms.getBuffer();
	return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
	throw new MMarshalException ("Buffer error", e); 
} 
}
}
