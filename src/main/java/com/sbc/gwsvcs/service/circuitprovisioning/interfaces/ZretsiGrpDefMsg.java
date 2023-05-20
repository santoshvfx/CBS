// $Id: ZretsiGrpDefMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZretsiGrpDefMsg implements MMarshalObject { 
	public ZretsiGrpDef value;

	public ZretsiGrpDefMsg () {
	}
	public ZretsiGrpDefMsg (ZretsiGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDef();
value.trkequip = new String ();
value.tnn_tl = new String ();
value.tnn_f = new String ();
value.tnn_g = new String ();
value.tc_cd = new String ();
value.tnn_s = new String ();
value.tnn_l = new String ();
value.key = new String ();
value.isasia = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZretsiGrpDef (ms, tag); 
	}
	static public ZretsiGrpDef decodeZretsiGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZretsiGrpDef value = create();
		ms.startStruct (tag, false);
		value.trkequip = ms.decodeOctetString (11, "trkequip");
		value.tnn_tl = ms.decodeOctetString (3, "tnn_tl");
		value.tnn_f = ms.decodeOctetString (2, "tnn_f");
		value.tnn_g = ms.decodeOctetString (2, "tnn_g");
		value.tc_cd = ms.decodeOctetString (4, "tc_cd");
		value.tnn_s = ms.decodeOctetString (2, "tnn_s");
		value.tnn_l = ms.decodeOctetString (2, "tnn_l");
		value.key = ms.decodeOctetString (11, "key");
		value.isasia = ms.decodeOctetString (3, "isasia");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZretsiGrpDef (ms, value, tag); 
	}
	static public void encodeZretsiGrpDef (MMarshalStrategy ms, ZretsiGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.trkequip, 11, "trkequip");
	ms.encode (value.tnn_tl, 3, "tnn_tl");
ms.encode (value.tnn_f, 2, "tnn_f");
ms.encode (value.tnn_g, 2, "tnn_g");
ms.encode (value.tc_cd, 4, "tc_cd");
ms.encode (value.tnn_s, 2, "tnn_s");
ms.encode (value.tnn_l, 2, "tnn_l");
ms.encode (value.key, 11, "key");
ms.encode (value.isasia, 3, "isasia");
ms.endStruct (tag, true); 
}
public static ZretsiGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZretsiGrpDef (ms, "ZretsiGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZretsiGrpDefHelper.type(); 
}
public static byte [] toOctet (ZretsiGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZretsiGrpDef (ms, val, "ZretsiGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
