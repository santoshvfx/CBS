// $Id: Rc1scnOutputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Rc1scnOutputMsg implements MMarshalObject { 
	public Rc1scnOutput value;

	public Rc1scnOutputMsg () {
	}
	public Rc1scnOutputMsg (Rc1scnOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.window = new String ();
value.of = new String ();
value.x_date = new String ();
value.time = new String ();
value.searchfmt = new String ();
value.ac_input = new String ();
value.fmtid = new String ();
value.type_input = new String ();
value.asvc = new String ();
value.zts = new String ();
value.dgec = new String ();
value.tysg = new String ();
value.clo = new String ();
value.cust = new String ();
value.acna = new String ();
value.ownr_trt = new String ();
value.cabssysid = new String ();
value.crissysid = new String ();
value.family_typ = new String ();
value.ccna = new String ();
value.lata = new String ();
value.scantyp = new String ();
value.fc = new String ();
value.Rc1scnGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDef[13];
for (int i0 = 0; i0 < 13; i0++) { 
value.Rc1scnGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRc1scnOutput (ms, tag); 
	}
	static public Rc1scnOutput decodeRc1scnOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Rc1scnOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.mask = ms.decodeOctetString (38, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.page = ms.decodeLong ("page");
		value.window = ms.decodeOctetString (2, "window");
		value.of = ms.decodeOctetString (2, "of");
		value.x_date = ms.decodeOctetString (9, "x_date");
		value.time = ms.decodeOctetString (6, "time");
		value.searchfmt = ms.decodeOctetString (2, "searchfmt");
		value.ac_input = ms.decodeOctetString (9, "ac_input");
		value.fmtid = ms.decodeOctetString (48, "fmtid");
		value.type_input = ms.decodeOctetString (6, "type_input");
		value.asvc = ms.decodeOctetString (12, "asvc");
		value.zts = ms.decodeOctetString (12, "zts");
		value.dgec = ms.decodeOctetString (14, "dgec");
		value.tysg = ms.decodeOctetString (7, "tysg");
		value.clo = ms.decodeOctetString (13, "clo");
		value.cust = ms.decodeOctetString (21, "cust");
		value.acna = ms.decodeOctetString (4, "acna");
		value.ownr_trt = ms.decodeOctetString (6, "ownr_trt");
		value.cabssysid = ms.decodeOctetString (20, "cabssysid");
		value.crissysid = ms.decodeOctetString (20, "crissysid");
		value.family_typ = ms.decodeOctetString (2, "family_typ");
		value.ccna = ms.decodeOctetString (4, "ccna");
		value.lata = ms.decodeOctetString (6, "lata");
		value.scantyp = ms.decodeOctetString (2, "scantyp");
		value.fc = ms.decodeOctetString (2, "fc");
		ms.startArray ("Rc1scnGrp", false);
		{ 
			value.Rc1scnGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDef[13];
			for (int __i0 = 0; __i0 < 13; __i0++) { 
				value.Rc1scnGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDefMsg.decodeRc1scnGrpDef (ms, "Rc1scnGrp");
			} 
		}
		ms.endArray ("Rc1scnGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRc1scnOutput (ms, value, tag); 
	}
	static public void encodeRc1scnOutput (MMarshalStrategy ms, Rc1scnOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 9, "cmd");
	ms.encode (value.mask, 38, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.page, "page");
ms.encode (value.window, 2, "window");
ms.encode (value.of, 2, "of");
ms.encode (value.x_date, 9, "x_date");
ms.encode (value.time, 6, "time");
ms.encode (value.searchfmt, 2, "searchfmt");
ms.encode (value.ac_input, 9, "ac_input");
ms.encode (value.fmtid, 48, "fmtid");
ms.encode (value.type_input, 6, "type_input");
ms.encode (value.asvc, 12, "asvc");
ms.encode (value.zts, 12, "zts");
ms.encode (value.dgec, 14, "dgec");
ms.encode (value.tysg, 7, "tysg");
ms.encode (value.clo, 13, "clo");
ms.encode (value.cust, 21, "cust");
ms.encode (value.acna, 4, "acna");
ms.encode (value.ownr_trt, 6, "ownr_trt");
ms.encode (value.cabssysid, 20, "cabssysid");
ms.encode (value.crissysid, 20, "crissysid");
ms.encode (value.family_typ, 2, "family_typ");
ms.encode (value.ccna, 4, "ccna");
ms.encode (value.lata, 6, "lata");
ms.encode (value.scantyp, 2, "scantyp");
ms.encode (value.fc, 2, "fc");
ms.startArray ("Rc1scnGrp", true);
{ 
for (int __i0 = 0; __i0 < 13; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnGrpDefMsg.encodeRc1scnGrpDef (ms, value.Rc1scnGrp[__i0], "Rc1scnGrp");
} 
}
ms.endArray ("Rc1scnGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static Rc1scnOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRc1scnOutput (ms, "Rc1scnOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Rc1scnOutputHelper.type(); 
}
public static byte [] toOctet (Rc1scnOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRc1scnOutput (ms, val, "Rc1scnOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
