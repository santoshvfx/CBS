// $Id: ZrgrpGrpDef1Msg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrgrpGrpDef1Msg implements MMarshalObject { 
	public ZrgrpGrpDef1 value;

	public ZrgrpGrpDef1Msg () {
	}
	public ZrgrpGrpDef1Msg (ZrgrpGrpDef1 initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1 create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1 value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1();
value.trkeqpttype = new String ();
value.asmtyp = new String ();
value.tcc = new String ();
value.prty = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrgrpGrpDef1 (ms, tag); 
	}
	static public ZrgrpGrpDef1 decodeZrgrpGrpDef1 (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrgrpGrpDef1 value = create();
		ms.startStruct (tag, false);
		value.trkeqpttype = ms.decodeOctetString (11, "trkeqpttype");
		value.asmtyp = ms.decodeOctetString (5, "asmtyp");
		value.tcc = ms.decodeOctetString (4, "tcc");
		value.prty = ms.decodeOctetString (2, "prty");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrgrpGrpDef1 (ms, value, tag); 
	}
	static public void encodeZrgrpGrpDef1 (MMarshalStrategy ms, ZrgrpGrpDef1 value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.trkeqpttype, 11, "trkeqpttype");
	ms.encode (value.asmtyp, 5, "asmtyp");
ms.encode (value.tcc, 4, "tcc");
ms.encode (value.prty, 2, "prty");
ms.endStruct (tag, true); 
}
public static ZrgrpGrpDef1 fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeZrgrpGrpDef1 (ms, "ZrgrpGrpDef1"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef1Helper.type(); 
}
public static byte [] toOctet (ZrgrpGrpDef1 val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeZrgrpGrpDef1 (ms, val, "ZrgrpGrpDef1");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
