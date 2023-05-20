// $Id: CktsrOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CktsrOutputMsg implements MMarshalObject { 
	public CktsrOutput value;

	public CktsrOutputMsg () {
	}
	public CktsrOutputMsg (CktsrOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.cac = new String ();
value.admin = new String ();
value.famdr = new String ();
value.CktsrGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDef[5];
for (int i0 = 0; i0 < 5; i0++) { 
value.CktsrGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDefMsg.create();
}
value.sscur_gac = new String ();
value.sscur_trknbr = new String ();
value.sscur_cktstat = new String ();
value.sscur_currspare = new String ();
value.sscur_fmt = new String ();
value.sscur_grpid = new String ();
value.sspend_gac = new String ();
value.sspend_trknbr = new String ();
value.sspend_clo = new String ();
value.sspend_pendact = new String ();
value.sspend_fmt = new String ();
value.sspend_grpid = new String ();
value.sspend_duedate = new String ();
value.relckt_cac = new String ();
value.relckt_fmt = new String ();
value.relckt_relid = new String ();
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCktsrOutput (ms, tag); 
	}
	static public CktsrOutput decodeCktsrOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CktsrOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (9, "cmd");
		value.mask = ms.decodeOctetString (47, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.cac = ms.decodeOctetString (8, "cac");
		value.admin = ms.decodeOctetString (3, "admin");
		value.famdr = ms.decodeOctetString (6, "famdr");
		ms.startArray ("CktsrGrp", false);
		{ 
			value.CktsrGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDef[5];
			for (int __i0 = 0; __i0 < 5; __i0++) { 
				value.CktsrGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDefMsg.decodeCktsrGrpDef (ms, "CktsrGrp");
			} 
		}
		ms.endArray ("CktsrGrp", false);
		value.sscur_gac = ms.decodeOctetString (9, "sscur_gac");
		value.sscur_trknbr = ms.decodeOctetString (5, "sscur_trknbr");
		value.sscur_cktstat = ms.decodeOctetString (2, "sscur_cktstat");
		value.sscur_currspare = ms.decodeOctetString (2, "sscur_currspare");
		value.sscur_fmt = ms.decodeOctetString (2, "sscur_fmt");
		value.sscur_grpid = ms.decodeOctetString (41, "sscur_grpid");
		value.sspend_gac = ms.decodeOctetString (9, "sspend_gac");
		value.sspend_trknbr = ms.decodeOctetString (5, "sspend_trknbr");
		value.sspend_clo = ms.decodeOctetString (13, "sspend_clo");
		value.sspend_pendact = ms.decodeOctetString (2, "sspend_pendact");
		value.sspend_fmt = ms.decodeOctetString (2, "sspend_fmt");
		value.sspend_grpid = ms.decodeOctetString (41, "sspend_grpid");
		value.sspend_duedate = ms.decodeOctetString (9, "sspend_duedate");
		value.relckt_cac = ms.decodeOctetString (8, "relckt_cac");
		value.relckt_fmt = ms.decodeOctetString (2, "relckt_fmt");
		value.relckt_relid = ms.decodeOctetString (46, "relckt_relid");
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCktsrOutput (ms, value, tag); 
	}
	static public void encodeCktsrOutput (MMarshalStrategy ms, CktsrOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 9, "cmd");
	ms.encode (value.mask, 47, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.cac, 8, "cac");
ms.encode (value.admin, 3, "admin");
ms.encode (value.famdr, 6, "famdr");
ms.startArray ("CktsrGrp", true);
{ 
for (int __i0 = 0; __i0 < 5; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrGrpDefMsg.encodeCktsrGrpDef (ms, value.CktsrGrp[__i0], "CktsrGrp");
} 
}
ms.endArray ("CktsrGrp", true);
ms.encode (value.sscur_gac, 9, "sscur_gac");
ms.encode (value.sscur_trknbr, 5, "sscur_trknbr");
ms.encode (value.sscur_cktstat, 2, "sscur_cktstat");
ms.encode (value.sscur_currspare, 2, "sscur_currspare");
ms.encode (value.sscur_fmt, 2, "sscur_fmt");
ms.encode (value.sscur_grpid, 41, "sscur_grpid");
ms.encode (value.sspend_gac, 9, "sspend_gac");
ms.encode (value.sspend_trknbr, 5, "sspend_trknbr");
ms.encode (value.sspend_clo, 13, "sspend_clo");
ms.encode (value.sspend_pendact, 2, "sspend_pendact");
ms.encode (value.sspend_fmt, 2, "sspend_fmt");
ms.encode (value.sspend_grpid, 41, "sspend_grpid");
ms.encode (value.sspend_duedate, 9, "sspend_duedate");
ms.encode (value.relckt_cac, 8, "relckt_cac");
ms.encode (value.relckt_fmt, 2, "relckt_fmt");
ms.encode (value.relckt_relid, 46, "relckt_relid");
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static CktsrOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCktsrOutput (ms, "CktsrOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CktsrOutputHelper.type(); 
}
public static byte [] toOctet (CktsrOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCktsrOutput (ms, val, "CktsrOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
