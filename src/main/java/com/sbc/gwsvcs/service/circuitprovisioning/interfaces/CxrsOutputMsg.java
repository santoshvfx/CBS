// $Id: CxrsOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CxrsOutputMsg implements MMarshalObject { 
	public CxrsOutput value;

	public CxrsOutputMsg () {
	}
	public CxrsOutputMsg (CxrsOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsOutput();
value.term_A = new String ();
value.term_Z = new String ();
value.fac_des = new String ();
value.fac_typ = new String ();
value.cac = new String ();
value.clo = new String ();
value.due_date_query = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.cxr_type = new String ();
value.subpath = new String ();
value.fac_grp = new String ();
value.dds = new String ();
value.f_a = new String ();
value.trans_rate = new String ();
value.avail = new String ();
value.currscan = new String ();
value.timing_pri_sec = new String ();
value.CxrsGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDef[14];
for (int i0 = 0; i0 < 14; i0++) { 
value.CxrsGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCxrsOutput (ms, tag); 
	}
	static public CxrsOutput decodeCxrsOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CxrsOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.term_A = ms.decodeOctetString (12, "term_A");
		value.term_Z = ms.decodeOctetString (12, "term_Z");
		value.fac_des = ms.decodeOctetString (6, "fac_des");
		value.fac_typ = ms.decodeOctetString (7, "fac_typ");
		value.cac = ms.decodeOctetString (9, "cac");
		value.clo = ms.decodeOctetString (17, "clo");
		value.due_date_query = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "due_date_query");
		value.cxr_type = ms.decodeOctetString (7, "cxr_type");
		value.subpath = ms.decodeOctetString (7, "subpath");
		value.fac_grp = ms.decodeOctetString (8, "fac_grp");
		value.dds = ms.decodeOctetString (2, "dds");
		value.f_a = ms.decodeOctetString (2, "f_a");
		value.trans_rate = ms.decodeOctetString (4, "trans_rate");
		value.avail = ms.decodeOctetString (2, "avail");
		value.currscan = ms.decodeOctetString (2, "currscan");
		value.timing_pri_sec = ms.decodeOctetString (2, "timing_pri_sec");
		ms.startArray ("CxrsGrp", false);
		{ 
			value.CxrsGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDef[14];
			for (int __i0 = 0; __i0 < 14; __i0++) { 
				value.CxrsGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDefMsg.decodeCxrsGrpDef (ms, "CxrsGrp");
			} 
		}
		ms.endArray ("CxrsGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCxrsOutput (ms, value, tag); 
	}
	static public void encodeCxrsOutput (MMarshalStrategy ms, CxrsOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.term_A, 12, "term_A");
	ms.encode (value.term_Z, 12, "term_Z");
ms.encode (value.fac_des, 6, "fac_des");
ms.encode (value.fac_typ, 7, "fac_typ");
ms.encode (value.cac, 9, "cac");
ms.encode (value.clo, 17, "clo");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.due_date_query, "due_date_query");
ms.encode (value.cxr_type, 7, "cxr_type");
ms.encode (value.subpath, 7, "subpath");
ms.encode (value.fac_grp, 8, "fac_grp");
ms.encode (value.dds, 2, "dds");
ms.encode (value.f_a, 2, "f_a");
ms.encode (value.trans_rate, 4, "trans_rate");
ms.encode (value.avail, 2, "avail");
ms.encode (value.currscan, 2, "currscan");
ms.encode (value.timing_pri_sec, 2, "timing_pri_sec");
ms.startArray ("CxrsGrp", true);
{ 
for (int __i0 = 0; __i0 < 14; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDefMsg.encodeCxrsGrpDef (ms, value.CxrsGrp[__i0], "CxrsGrp");
} 
}
ms.endArray ("CxrsGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static CxrsOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCxrsOutput (ms, "CxrsOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsOutputHelper.type(); 
}
public static byte [] toOctet (CxrsOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCxrsOutput (ms, val, "CxrsOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
