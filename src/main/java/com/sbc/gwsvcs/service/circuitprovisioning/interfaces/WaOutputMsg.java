// $Id: WaOutputMsg.java,v 1.1 2002/09/29 04:10:30 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class WaOutputMsg implements MMarshalObject { 
	public WaOutput value;

	public WaOutputMsg () {
	}
	public WaOutputMsg (WaOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.ckt = new String ();
value.loca = new String ();
value.locz = new String ();
value.clo = new String ();
value.from = new String ();
value.to = new String ();
value.act = new String ();
value.prq = new String ();
value.tsp = new String ();
value.msc = new String ();
value.mco = new String ();
value.cac = new String ();
value.ord = new String ();
value.sup = new String ();
value.tp = new String ();
value.stat = new String ();
value.lease = new String ();
value.dpi = new String ();
value.pg = new String ();
value.cust = new String ();
value.tgac = new String ();
value.puls = new String ();
value.dv = new String ();
value.dd = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.iad = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.ccon = new String ();
value.tel = new String ();
value.ptd = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.swc = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.acna = new String ();
value.ccna = new String ();
value.imp = new String ();
value.imc = new String ();
value.fcd = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.wot = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.btn = new String ();
value.cus = new String ();
value.aor = new String ();
value.rri = new String ();
value.lata = new String ();
value.dva = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.rid = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.sa = new String ();
value.jobid = new String ();
value.ex1 = new String ();
value.rclo = new String ();
value.proj = new String ();
value.ex2 = new String ();
value.ro = new String ();
value.cco = new String ();
value.ex3 = new String ();
value.cro = new String ();
value.oco = new String ();
value.ex4 = new String ();
value.origr = new String ();
value.tel2 = new String ();
value.dggnr_group = new String ();
value.tel3 = new String ();
value.ver = new String ();
value.pre_ck = new String ();
value.pon = new String ();
value.ckr = new String ();
value.mcn = new String ();
value.WaGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDef[3];
for (int i0 = 0; i0 < 3; i0++) { 
value.WaGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDefMsg.create();
}
value.notes = new String ();
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeWaOutput (ms, tag); 
	}
	static public WaOutput decodeWaOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		WaOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (8, "cmd");
		value.mask = ms.decodeOctetString (20, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.ckt = ms.decodeOctetString (48, "ckt");
		value.loca = ms.decodeOctetString (12, "loca");
		value.locz = ms.decodeOctetString (12, "locz");
		value.clo = ms.decodeOctetString (14, "clo");
		value.from = ms.decodeOctetString (4, "from");
		value.to = ms.decodeOctetString (4, "to");
		value.act = ms.decodeOctetString (4, "act");
		value.prq = ms.decodeOctetString (4, "prq");
		value.tsp = ms.decodeOctetString (3, "tsp");
		value.msc = ms.decodeOctetString (2, "msc");
		value.mco = ms.decodeOctetString (13, "mco");
		value.cac = ms.decodeOctetString (9, "cac");
		value.ord = ms.decodeOctetString (19, "ord");
		value.sup = ms.decodeOctetString (3, "sup");
		value.tp = ms.decodeOctetString (2, "tp");
		value.stat = ms.decodeOctetString (3, "stat");
		value.lease = ms.decodeOctetString (2, "lease");
		value.dpi = ms.decodeOctetString (2, "dpi");
		value.pg = ms.decodeOctetString (7, "pg");
		value.cust = ms.decodeOctetString (20, "cust");
		value.tgac = ms.decodeOctetString (9, "tgac");
		value.puls = ms.decodeOctetString (3, "puls");
		value.dv = ms.decodeOctetString (3, "dv");
		value.dd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "dd");
		value.iad = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "iad");
		value.ccon = ms.decodeOctetString (25, "ccon");
		value.tel = ms.decodeOctetString (13, "tel");
		value.ptd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "ptd");
		value.swc = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "swc");
		value.acna = ms.decodeOctetString (4, "acna");
		value.ccna = ms.decodeOctetString (4, "ccna");
		value.imp = ms.decodeOctetString (13, "imp");
		value.imc = ms.decodeOctetString (13, "imc");
		value.fcd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "fcd");
		value.wot = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "wot");
		value.btn = ms.decodeOctetString (13, "btn");
		value.cus = ms.decodeOctetString (4, "cus");
		value.aor = ms.decodeOctetString (7, "aor");
		value.rri = ms.decodeOctetString (5, "rri");
		value.lata = ms.decodeOctetString (6, "lata");
		value.dva = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "dva");
		value.rid = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "rid");
		value.sa = ms.decodeOctetString (43, "sa");
		value.jobid = ms.decodeOctetString (15, "jobid");
		value.ex1 = ms.decodeOctetString (7, "ex1");
		value.rclo = ms.decodeOctetString (41, "rclo");
		value.proj = ms.decodeOctetString (16, "proj");
		value.ex2 = ms.decodeOctetString (7, "ex2");
		value.ro = ms.decodeOctetString (43, "ro");
		value.cco = ms.decodeOctetString (16, "cco");
		value.ex3 = ms.decodeOctetString (7, "ex3");
		value.cro = ms.decodeOctetString (42, "cro");
		value.oco = ms.decodeOctetString (16, "oco");
		value.ex4 = ms.decodeOctetString (7, "ex4");
		value.origr = ms.decodeOctetString (9, "origr");
		value.tel2 = ms.decodeOctetString (13, "tel2");
		value.dggnr_group = ms.decodeOctetString (4, "dggnr_group");
		value.tel3 = ms.decodeOctetString (13, "tel3");
		value.ver = ms.decodeOctetString (3, "ver");
		value.pre_ck = ms.decodeOctetString (45, "pre_ck");
		value.pon = ms.decodeOctetString (17, "pon");
		value.ckr = ms.decodeOctetString (54, "ckr");
		value.mcn = ms.decodeOctetString (12, "mcn");
		ms.startArray ("WaGrp", false);
		{ 
			value.WaGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDef[3];
			for (int __i0 = 0; __i0 < 3; __i0++) { 
				value.WaGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDefMsg.decodeWaGrpDef (ms, "WaGrp");
			} 
		}
		ms.endArray ("WaGrp", false);
		value.notes = ms.decodeOctetString (313, "notes");
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeWaOutput (ms, value, tag); 
	}
	static public void encodeWaOutput (MMarshalStrategy ms, WaOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 8, "cmd");
	ms.encode (value.mask, 20, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.ckt, 48, "ckt");
ms.encode (value.loca, 12, "loca");
ms.encode (value.locz, 12, "locz");
ms.encode (value.clo, 14, "clo");
ms.encode (value.from, 4, "from");
ms.encode (value.to, 4, "to");
ms.encode (value.act, 4, "act");
ms.encode (value.prq, 4, "prq");
ms.encode (value.tsp, 3, "tsp");
ms.encode (value.msc, 2, "msc");
ms.encode (value.mco, 13, "mco");
ms.encode (value.cac, 9, "cac");
ms.encode (value.ord, 19, "ord");
ms.encode (value.sup, 3, "sup");
ms.encode (value.tp, 2, "tp");
ms.encode (value.stat, 3, "stat");
ms.encode (value.lease, 2, "lease");
ms.encode (value.dpi, 2, "dpi");
ms.encode (value.pg, 7, "pg");
ms.encode (value.cust, 20, "cust");
ms.encode (value.tgac, 9, "tgac");
ms.encode (value.puls, 3, "puls");
ms.encode (value.dv, 3, "dv");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.dd, "dd");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.iad, "iad");
ms.encode (value.ccon, 25, "ccon");
ms.encode (value.tel, 13, "tel");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.ptd, "ptd");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.swc, "swc");
ms.encode (value.acna, 4, "acna");
ms.encode (value.ccna, 4, "ccna");
ms.encode (value.imp, 13, "imp");
ms.encode (value.imc, 13, "imc");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.fcd, "fcd");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.wot, "wot");
ms.encode (value.btn, 13, "btn");
ms.encode (value.cus, 4, "cus");
ms.encode (value.aor, 7, "aor");
ms.encode (value.rri, 5, "rri");
ms.encode (value.lata, 6, "lata");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.dva, "dva");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.rid, "rid");
ms.encode (value.sa, 43, "sa");
ms.encode (value.jobid, 15, "jobid");
ms.encode (value.ex1, 7, "ex1");
ms.encode (value.rclo, 41, "rclo");
ms.encode (value.proj, 16, "proj");
ms.encode (value.ex2, 7, "ex2");
ms.encode (value.ro, 43, "ro");
ms.encode (value.cco, 16, "cco");
ms.encode (value.ex3, 7, "ex3");
ms.encode (value.cro, 42, "cro");
ms.encode (value.oco, 16, "oco");
ms.encode (value.ex4, 7, "ex4");
ms.encode (value.origr, 9, "origr");
ms.encode (value.tel2, 13, "tel2");
ms.encode (value.dggnr_group, 4, "dggnr_group");
ms.encode (value.tel3, 13, "tel3");
ms.encode (value.ver, 3, "ver");
ms.encode (value.pre_ck, 45, "pre_ck");
ms.encode (value.pon, 17, "pon");
ms.encode (value.ckr, 54, "ckr");
ms.encode (value.mcn, 12, "mcn");
ms.startArray ("WaGrp", true);
{ 
for (int __i0 = 0; __i0 < 3; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaGrpDefMsg.encodeWaGrpDef (ms, value.WaGrp[__i0], "WaGrp");
} 
}
ms.endArray ("WaGrp", true);
ms.encode (value.notes, 313, "notes");
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static WaOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeWaOutput (ms, "WaOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.WaOutputHelper.type(); 
}
public static byte [] toOctet (WaOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeWaOutput (ms, val, "WaOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
