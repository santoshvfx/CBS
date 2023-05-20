// $Id: ZrxlocGrpDefMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrxlocGrpDefMsg implements MMarshalObject { 
	public ZrxlocGrpDef value;

	public ZrxlocGrpDefMsg () {
	}
	public ZrxlocGrpDefMsg (ZrxlocGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDef();
value.sel = new String ();
value.location = new String ();
value.lpc = new String ();
value.pc_ncp = new String ();
value.level = new String ();
value.hr = new String ();
value.type = new String ();
value.stat = new String ();
value.ciccomp = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrxlocGrpDef (ms, tag); 
	}
	static public ZrxlocGrpDef decodeZrxlocGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrxlocGrpDef value = create();
		ms.startStruct (tag, false);
		value.sel = ms.decodeOctetString (2, "sel");
		value.location = ms.decodeOctetString (12, "location");
		value.lpc = ms.decodeOctetString (7, "lpc");
		value.pc_ncp = ms.decodeOctetString (12, "pc_ncp");
		value.level = ms.decodeOctetString (2, "level");
		value.hr = ms.decodeOctetString (2, "hr");
		value.type = ms.decodeOctetString (6, "type");
		value.stat = ms.decodeOctetString (3, "stat");
		value.ciccomp = ms.decodeOctetString (6, "ciccomp");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrxlocGrpDef (ms, value, tag); 
	}
	static public void encodeZrxlocGrpDef (MMarshalStrategy ms, ZrxlocGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.sel, 2, "sel");
	ms.encode (value.location, 12, "location");
ms.encode (value.lpc, 7, "lpc");
ms.encode (value.pc_ncp, 12, "pc_ncp");
ms.encode (value.level, 2, "level");
ms.encode (value.hr, 2, "hr");
ms.encode (value.type, 6, "type");
ms.encode (value.stat, 3, "stat");
ms.encode (value.ciccomp, 6, "ciccomp");
ms.endStruct (tag, true); 
}
public static ZrxlocGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrxlocGrpDef (ms, "ZrxlocGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrxlocGrpDefHelper.type(); 
}
public static byte [] toOctet (ZrxlocGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrxlocGrpDef (ms, val, "ZrxlocGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
