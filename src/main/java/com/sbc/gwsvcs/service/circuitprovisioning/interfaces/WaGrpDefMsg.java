// $Id: WaGrpDefMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class WaGrpDefMsg implements MMarshalObject { 
	public WaGrpDef value;

	public WaGrpDefMsg () {
	}
	public WaGrpDefMsg (WaGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDef();
value.clo_nbr1 = new String ();
value.due_date1 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.act1 = new String ();
value.te1 = new String ();
value.clo_nbr2 = new String ();
value.due_date2 = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.act2 = new String ();
value.te2 = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeWaGrpDef (ms, tag); 
	}
	static public WaGrpDef decodeWaGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		WaGrpDef value = create();
		ms.startStruct (tag, false);
		value.clo_nbr1 = ms.decodeOctetString (14, "clo_nbr1");
		value.due_date1 = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "due_date1");
		value.act1 = ms.decodeOctetString (3, "act1");
		value.te1 = ms.decodeOctetString (2, "te1");
		value.clo_nbr2 = ms.decodeOctetString (14, "clo_nbr2");
		value.due_date2 = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "due_date2");
		value.act2 = ms.decodeOctetString (3, "act2");
		value.te2 = ms.decodeOctetString (2, "te2");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeWaGrpDef (ms, value, tag); 
	}
	static public void encodeWaGrpDef (MMarshalStrategy ms, WaGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.clo_nbr1, 14, "clo_nbr1");
	com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.due_date1, "due_date1");
	ms.encode (value.act1, 3, "act1");
ms.encode (value.te1, 2, "te1");
ms.encode (value.clo_nbr2, 14, "clo_nbr2");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.due_date2, "due_date2");
ms.encode (value.act2, 3, "act2");
ms.encode (value.te2, 2, "te2");
ms.endStruct (tag, true); 
}
public static WaGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeWaGrpDef (ms, "WaGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDefHelper.type(); 
}
public static byte [] toOctet (WaGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeWaGrpDef (ms, val, "WaGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
