// $Id: EqpscGrpDefMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpscGrpDefMsg implements MMarshalObject { 
	public EqpscGrpDef value;

	public EqpscGrpDefMsg () {
	}
	public EqpscGrpDefMsg (EqpscGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDef();
value.unit = new String ();
value.invstat = new String ();
value.subdiv = new String ();
value.asntyp = new String ();
value.dvcd = new String ();
value.curact = new String ();
value.circuitid = new String ();
value.pendact = new String ();
value.duedate = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpscGrpDef (ms, tag); 
	}
	static public EqpscGrpDef decodeEqpscGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpscGrpDef value = create();
		ms.startStruct (tag, false);
		value.unit = ms.decodeOctetString (7, "unit");
		value.invstat = ms.decodeOctetString (3, "invstat");
		value.subdiv = ms.decodeOctetString (6, "subdiv");
		value.asntyp = ms.decodeOctetString (2, "asntyp");
		value.dvcd = ms.decodeOctetString (3, "dvcd");
		value.curact = ms.decodeOctetString (2, "curact");
		value.circuitid = ms.decodeOctetString (46, "circuitid");
		value.pendact = ms.decodeOctetString (2, "pendact");
		value.duedate = ms.decodeOctetString (7, "duedate");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpscGrpDef (ms, value, tag); 
	}
	static public void encodeEqpscGrpDef (MMarshalStrategy ms, EqpscGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.unit, 7, "unit");
	ms.encode (value.invstat, 3, "invstat");
ms.encode (value.subdiv, 6, "subdiv");
ms.encode (value.asntyp, 2, "asntyp");
ms.encode (value.dvcd, 3, "dvcd");
ms.encode (value.curact, 2, "curact");
ms.encode (value.circuitid, 46, "circuitid");
ms.encode (value.pendact, 2, "pendact");
ms.encode (value.duedate, 7, "duedate");
ms.endStruct (tag, true); 
}
public static EqpscGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpscGrpDef (ms, "EqpscGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpscGrpDefHelper.type(); 
}
public static byte [] toOctet (EqpscGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpscGrpDef (ms, val, "EqpscGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
