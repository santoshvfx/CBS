// $Id: RdlocGrpDefMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RdlocGrpDefMsg implements MMarshalObject { 
	public RdlocGrpDef value;

	public RdlocGrpDefMsg () {
	}
	public RdlocGrpDefMsg (RdlocGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDef();
value.locationcode = new String ();
value.fmt = new String ();
value.idtype = new String ();
value.stat = new String ();
value.effdate = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRdlocGrpDef (ms, tag); 
	}
	static public RdlocGrpDef decodeRdlocGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RdlocGrpDef value = create();
		ms.startStruct (tag, false);
		value.locationcode = ms.decodeOctetString (12, "locationcode");
		value.fmt = ms.decodeOctetString (5, "fmt");
		value.idtype = ms.decodeOctetString (6, "idtype");
		value.stat = ms.decodeOctetString (3, "stat");
		value.effdate = ms.decodeOctetString (9, "effdate");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRdlocGrpDef (ms, value, tag); 
	}
	static public void encodeRdlocGrpDef (MMarshalStrategy ms, RdlocGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.locationcode, 12, "locationcode");
	ms.encode (value.fmt, 5, "fmt");
ms.encode (value.idtype, 6, "idtype");
ms.encode (value.stat, 3, "stat");
ms.encode (value.effdate, 9, "effdate");
ms.endStruct (tag, true); 
}
public static RdlocGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRdlocGrpDef (ms, "RdlocGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDefHelper.type(); 
}
public static byte [] toOctet (RdlocGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRdlocGrpDef (ms, val, "RdlocGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
