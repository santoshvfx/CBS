// $Id: GcocmaOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GcocmaOutputMsg implements MMarshalObject { 
	public GcocmaOutput value;

	public GcocmaOutputMsg () {
	}
	public GcocmaOutputMsg (GcocmaOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaOutput();
value.x_for = new String ();
value.mask = new String ();
value.clo = new String ();
value.ord = new String ();
value.ckt = new String ();
value.oiy = new String ();
value.assocord = new String ();
value.suppid = new String ();
value.actn = new String ();
value.orig = new String ();
value.ordtype = new String ();
value.orig2 = new String ();
value.cust = new String ();
value.rac = new String ();
value.pcf = new String ();
value.app = new String ();
value.dd = new String ();
value.mdfr = new String ();
value.swc = new String ();
value.intvl = new String ();
value.npa = new String ();
value.dop = new String ();
value.wco = new String ();
value.eco = new String ();
value.tro = new String ();
value.oco = new String ();
value.cco = new String ();
value.mro1 = new String ();
value.mro2 = new String ();
value.mro3 = new String ();
value.ic = new String ();
value.doc = new String ();
value.relord = new String ();
value.te = new String ();
value.wkgrp = new String ();
value.ndnw = new String ();
value.lata = new String ();
value.acna = new String ();
value.proj = new String ();
value.ccna = new String ();
value.exactid = new String ();
value.pon = new String ();
value.ver = new String ();
value.jobid = new String ();
value.sidjep = new String ();
value.bit = new String ();
value.id = new String ();
value.sid = new String ();
value.lam = new String ();
value.eird = new String ();
value.trid = new String ();
value.ad = new String ();
value.drld = new String ();
value.cdlrd = new String ();
value.rid = new String ();
value.dva = new String ();
value.wot = new String ();
value.fcd = new String ();
value.ptd = new String ();
value.iad = new String ();
value.tgac = new String ();
value.mcs = new String ();
value.rtopt = new String ();
value.exsys = new String ();
value.ri = new String ();
value.remark = new String ();
value.ostat = new String ();
value.ckt2 = new String ();
value.cid = new String ();
value.fmt = new String ();
value.actn2 = new String ();
value.old_id = new String ();
value.old_id_fmt = new String ();
value.dv = new String ();
value.qty = new String ();
value.dr = new String ();
value.cac = new String ();
value.dib = new String ();
value.assoc_ord = new String ();
value.inv = new String ();
value.exsys2 = new String ();
value.remark2 = new String ();
value.cstat = new String ();
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGcocmaOutput (ms, tag); 
	}
	static public GcocmaOutput decodeGcocmaOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GcocmaOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.mask = ms.decodeOctetString (43, "mask");
		value.clo = ms.decodeOctetString (11, "clo");
		value.ord = ms.decodeOctetString (19, "ord");
		value.ckt = ms.decodeOctetString (4, "ckt");
		value.oiy = ms.decodeOctetString (2, "oiy");
		value.assocord = ms.decodeOctetString (37, "assocord");
		value.suppid = ms.decodeOctetString (3, "suppid");
		value.actn = ms.decodeOctetString (42, "actn");
		value.orig = ms.decodeOctetString (9, "orig");
		value.ordtype = ms.decodeOctetString (2, "ordtype");
		value.orig2 = ms.decodeOctetString (9, "orig2");
		value.cust = ms.decodeOctetString (21, "cust");
		value.rac = ms.decodeOctetString (9, "rac");
		value.pcf = ms.decodeOctetString (2, "pcf");
		value.app = ms.decodeOctetString (7, "app");
		value.dd = ms.decodeOctetString (7, "dd");
		value.mdfr = ms.decodeOctetString (4, "mdfr");
		value.swc = ms.decodeOctetString (7, "swc");
		value.intvl = ms.decodeOctetString (8, "intvl");
		value.npa = ms.decodeOctetString (4, "npa");
		value.dop = ms.decodeOctetString (2, "dop");
		value.wco = ms.decodeOctetString (4, "wco");
		value.eco = ms.decodeOctetString (4, "eco");
		value.tro = ms.decodeOctetString (4, "tro");
		value.oco = ms.decodeOctetString (4, "oco");
		value.cco = ms.decodeOctetString (4, "cco");
		value.mro1 = ms.decodeOctetString (4, "mro1");
		value.mro2 = ms.decodeOctetString (4, "mro2");
		value.mro3 = ms.decodeOctetString (4, "mro3");
		value.ic = ms.decodeOctetString (2, "ic");
		value.doc = ms.decodeOctetString (5, "doc");
		value.relord = ms.decodeOctetString (21, "relord");
		value.te = ms.decodeOctetString (2, "te");
		value.wkgrp = ms.decodeOctetString (5, "wkgrp");
		value.ndnw = ms.decodeOctetString (2, "ndnw");
		value.lata = ms.decodeOctetString (6, "lata");
		value.acna = ms.decodeOctetString (4, "acna");
		value.proj = ms.decodeOctetString (17, "proj");
		value.ccna = ms.decodeOctetString (4, "ccna");
		value.exactid = ms.decodeOctetString (12, "exactid");
		value.pon = ms.decodeOctetString (21, "pon");
		value.ver = ms.decodeOctetString (3, "ver");
		value.jobid = ms.decodeOctetString (14, "jobid");
		value.sidjep = ms.decodeOctetString (4, "sidjep");
		value.bit = ms.decodeOctetString (2, "bit");
		value.id = ms.decodeOctetString (7, "id");
		value.sid = ms.decodeOctetString (7, "sid");
		value.lam = ms.decodeOctetString (7, "lam");
		value.eird = ms.decodeOctetString (7, "eird");
		value.trid = ms.decodeOctetString (7, "trid");
		value.ad = ms.decodeOctetString (7, "ad");
		value.drld = ms.decodeOctetString (7, "drld");
		value.cdlrd = ms.decodeOctetString (7, "cdlrd");
		value.rid = ms.decodeOctetString (7, "rid");
		value.dva = ms.decodeOctetString (7, "dva");
		value.wot = ms.decodeOctetString (7, "wot");
		value.fcd = ms.decodeOctetString (7, "fcd");
		value.ptd = ms.decodeOctetString (7, "ptd");
		value.iad = ms.decodeOctetString (7, "iad");
		value.tgac = ms.decodeOctetString (8, "tgac");
		value.mcs = ms.decodeOctetString (2, "mcs");
		value.rtopt = ms.decodeOctetString (2, "rtopt");
		value.exsys = ms.decodeOctetString (5, "exsys");
		value.ri = ms.decodeOctetString (3, "ri");
		value.remark = ms.decodeOctetString (61, "remark");
		value.ostat = ms.decodeOctetString (2, "ostat");
		value.ckt2 = ms.decodeOctetString (4, "ckt2");
		value.cid = ms.decodeOctetString (46, "cid");
		value.fmt = ms.decodeOctetString (2, "fmt");
		value.actn2 = ms.decodeOctetString (3, "actn2");
		value.old_id = ms.decodeOctetString (46, "old_id");
		value.old_id_fmt = ms.decodeOctetString (2, "old_id_fmt");
		value.dv = ms.decodeOctetString (3, "dv");
		value.qty = ms.decodeOctetString (4, "qty");
		value.dr = ms.decodeOctetString (6, "dr");
		value.cac = ms.decodeOctetString (9, "cac");
		value.dib = ms.decodeOctetString (7, "dib");
		value.assoc_ord = ms.decodeOctetString (19, "assoc_ord");
		value.inv = ms.decodeOctetString (2, "inv");
		value.exsys2 = ms.decodeOctetString (5, "exsys2");
		value.remark2 = ms.decodeOctetString (61, "remark2");
		value.cstat = ms.decodeOctetString (2, "cstat");
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGcocmaOutput (ms, value, tag); 
	}
	static public void encodeGcocmaOutput (MMarshalStrategy ms, GcocmaOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.x_for, 9, "x_for");
	ms.encode (value.mask, 43, "mask");
ms.encode (value.clo, 11, "clo");
ms.encode (value.ord, 19, "ord");
ms.encode (value.ckt, 4, "ckt");
ms.encode (value.oiy, 2, "oiy");
ms.encode (value.assocord, 37, "assocord");
ms.encode (value.suppid, 3, "suppid");
ms.encode (value.actn, 42, "actn");
ms.encode (value.orig, 9, "orig");
ms.encode (value.ordtype, 2, "ordtype");
ms.encode (value.orig2, 9, "orig2");
ms.encode (value.cust, 21, "cust");
ms.encode (value.rac, 9, "rac");
ms.encode (value.pcf, 2, "pcf");
ms.encode (value.app, 7, "app");
ms.encode (value.dd, 7, "dd");
ms.encode (value.mdfr, 4, "mdfr");
ms.encode (value.swc, 7, "swc");
ms.encode (value.intvl, 8, "intvl");
ms.encode (value.npa, 4, "npa");
ms.encode (value.dop, 2, "dop");
ms.encode (value.wco, 4, "wco");
ms.encode (value.eco, 4, "eco");
ms.encode (value.tro, 4, "tro");
ms.encode (value.oco, 4, "oco");
ms.encode (value.cco, 4, "cco");
ms.encode (value.mro1, 4, "mro1");
ms.encode (value.mro2, 4, "mro2");
ms.encode (value.mro3, 4, "mro3");
ms.encode (value.ic, 2, "ic");
ms.encode (value.doc, 5, "doc");
ms.encode (value.relord, 21, "relord");
ms.encode (value.te, 2, "te");
ms.encode (value.wkgrp, 5, "wkgrp");
ms.encode (value.ndnw, 2, "ndnw");
ms.encode (value.lata, 6, "lata");
ms.encode (value.acna, 4, "acna");
ms.encode (value.proj, 17, "proj");
ms.encode (value.ccna, 4, "ccna");
ms.encode (value.exactid, 12, "exactid");
ms.encode (value.pon, 21, "pon");
ms.encode (value.ver, 3, "ver");
ms.encode (value.jobid, 14, "jobid");
ms.encode (value.sidjep, 4, "sidjep");
ms.encode (value.bit, 2, "bit");
ms.encode (value.id, 7, "id");
ms.encode (value.sid, 7, "sid");
ms.encode (value.lam, 7, "lam");
ms.encode (value.eird, 7, "eird");
ms.encode (value.trid, 7, "trid");
ms.encode (value.ad, 7, "ad");
ms.encode (value.drld, 7, "drld");
ms.encode (value.cdlrd, 7, "cdlrd");
ms.encode (value.rid, 7, "rid");
ms.encode (value.dva, 7, "dva");
ms.encode (value.wot, 7, "wot");
ms.encode (value.fcd, 7, "fcd");
ms.encode (value.ptd, 7, "ptd");
ms.encode (value.iad, 7, "iad");
ms.encode (value.tgac, 8, "tgac");
ms.encode (value.mcs, 2, "mcs");
ms.encode (value.rtopt, 2, "rtopt");
ms.encode (value.exsys, 5, "exsys");
ms.encode (value.ri, 3, "ri");
ms.encode (value.remark, 61, "remark");
ms.encode (value.ostat, 2, "ostat");
ms.encode (value.ckt2, 4, "ckt2");
ms.encode (value.cid, 46, "cid");
ms.encode (value.fmt, 2, "fmt");
ms.encode (value.actn2, 3, "actn2");
ms.encode (value.old_id, 46, "old_id");
ms.encode (value.old_id_fmt, 2, "old_id_fmt");
ms.encode (value.dv, 3, "dv");
ms.encode (value.qty, 4, "qty");
ms.encode (value.dr, 6, "dr");
ms.encode (value.cac, 9, "cac");
ms.encode (value.dib, 7, "dib");
ms.encode (value.assoc_ord, 19, "assoc_ord");
ms.encode (value.inv, 2, "inv");
ms.encode (value.exsys2, 5, "exsys2");
ms.encode (value.remark2, 61, "remark2");
ms.encode (value.cstat, 2, "cstat");
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static GcocmaOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeGcocmaOutput (ms, "GcocmaOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcocmaOutputHelper.type(); 
}
public static byte [] toOctet (GcocmaOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeGcocmaOutput (ms, val, "GcocmaOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
