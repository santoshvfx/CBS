// $Id: ZrgrpGrpDef2Msg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ZrgrpGrpDef2Msg implements MMarshalObject { 
	public ZrgrpGrpDef2 value;

	public ZrgrpGrpDef2Msg () {
	}
	public ZrgrpGrpDef2Msg (ZrgrpGrpDef2 initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2 create () { 
	com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2 value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2();
	value.popt = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeZrgrpGrpDef2 (ms, tag); 
	}
	static public ZrgrpGrpDef2 decodeZrgrpGrpDef2 (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ZrgrpGrpDef2 value = create();
		ms.startStruct (tag, false);
		value.popt = ms.decodeOctetString (5, "popt");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeZrgrpGrpDef2 (ms, value, tag); 
	}
	static public void encodeZrgrpGrpDef2 (MMarshalStrategy ms, ZrgrpGrpDef2 value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.popt, 5, "popt");
	ms.endStruct (tag, true); 
}
public static ZrgrpGrpDef2 fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeZrgrpGrpDef2 (ms, "ZrgrpGrpDef2"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ZrgrpGrpDef2Helper.type(); 
}
public static byte [] toOctet (ZrgrpGrpDef2 val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeZrgrpGrpDef2 (ms, val, "ZrgrpGrpDef2");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
