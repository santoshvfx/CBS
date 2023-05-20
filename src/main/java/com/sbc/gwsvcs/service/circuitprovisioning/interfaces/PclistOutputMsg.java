// $Id: PclistOutputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class PclistOutputMsg implements MMarshalObject { 
	public PclistOutput value;

	public PclistOutputMsg () {
	}
	public PclistOutputMsg (PclistOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistOutput();
value.x_for = new String ();
value.mask = new String ();
value.rro = new String ();
value.wk_pos = new String ();
value.uac = new String ();
value.start = new String ();
value.end_item = new String ();
value.rro_wkp1 = new String ();
value.rro_wkp2 = new String ();
value.PclistGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDef[8];
for (int i0 = 0; i0 < 8; i0++) { 
value.PclistGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodePclistOutput (ms, tag); 
	}
	static public PclistOutput decodePclistOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		PclistOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.mask = ms.decodeOctetString (36, "mask");
		value.rro = ms.decodeOctetString (4, "rro");
		value.wk_pos = ms.decodeOctetString (4, "wk_pos");
		value.uac = ms.decodeOctetString (5, "uac");
		value.start = ms.decodeOctetString (6, "start");
		value.end_item = ms.decodeOctetString (6, "end_item");
		value.rro_wkp1 = ms.decodeOctetString (4, "rro_wkp1");
		value.rro_wkp2 = ms.decodeOctetString (4, "rro_wkp2");
		ms.startArray ("PclistGrp", false);
		{ 
			value.PclistGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDef[8];
			for (int __i0 = 0; __i0 < 8; __i0++) { 
				value.PclistGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDefMsg.decodePclistGrpDef (ms, "PclistGrp");
			} 
		}
		ms.endArray ("PclistGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodePclistOutput (ms, value, tag); 
	}
	static public void encodePclistOutput (MMarshalStrategy ms, PclistOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.x_for, 9, "x_for");
	ms.encode (value.mask, 36, "mask");
ms.encode (value.rro, 4, "rro");
ms.encode (value.wk_pos, 4, "wk_pos");
ms.encode (value.uac, 5, "uac");
ms.encode (value.start, 6, "start");
ms.encode (value.end_item, 6, "end_item");
ms.encode (value.rro_wkp1, 4, "rro_wkp1");
ms.encode (value.rro_wkp2, 4, "rro_wkp2");
ms.startArray ("PclistGrp", true);
{ 
for (int __i0 = 0; __i0 < 8; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistGrpDefMsg.encodePclistGrpDef (ms, value.PclistGrp[__i0], "PclistGrp");
} 
}
ms.endArray ("PclistGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static PclistOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodePclistOutput (ms, "PclistOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.PclistOutputHelper.type(); 
}
public static byte [] toOctet (PclistOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodePclistOutput (ms, val, "PclistOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
