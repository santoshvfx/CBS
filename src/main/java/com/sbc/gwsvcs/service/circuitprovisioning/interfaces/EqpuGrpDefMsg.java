// $Id: EqpuGrpDefMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpuGrpDefMsg implements MMarshalObject { 
	public EqpuGrpDef value;

	public EqpuGrpDefMsg () {
	}
	public EqpuGrpDefMsg (EqpuGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDef();
value.hecigs = new String ();
value.units = new String ();
value.sprs = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpuGrpDef (ms, tag); 
	}
	static public EqpuGrpDef decodeEqpuGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpuGrpDef value = create();
		ms.startStruct (tag, false);
		value.hecigs = ms.decodeOctetString (9, "hecigs");
		value.units = ms.decodeOctetString (6, "units");
		value.sprs = ms.decodeOctetString (5, "sprs");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpuGrpDef (ms, value, tag); 
	}
	static public void encodeEqpuGrpDef (MMarshalStrategy ms, EqpuGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.hecigs, 9, "hecigs");
	ms.encode (value.units, 6, "units");
ms.encode (value.sprs, 5, "sprs");
ms.endStruct (tag, true); 
}
public static EqpuGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpuGrpDef (ms, "EqpuGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpuGrpDefHelper.type(); 
}
public static byte [] toOctet (EqpuGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
	          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpuGrpDef (ms, val, "EqpuGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
