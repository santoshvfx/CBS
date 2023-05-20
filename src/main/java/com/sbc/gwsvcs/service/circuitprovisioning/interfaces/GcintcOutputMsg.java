// $Id: GcintcOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class GcintcOutputMsg implements MMarshalObject { 
	public GcintcOutput value;

	public GcintcOutputMsg () {
	}
	public GcintcOutputMsg (GcintcOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcOutput();
value.x_for = new String ();
value.mask = new String ();
value.ord_class = new String ();
value.admin_area = new String ();
value.total_interval = new String ();
value.app1 = new String ();
value.dd1 = new String ();
value.swc1 = new String ();
value.npa = new String ();
value.t = new String ();
value.entry = new String ();
value.area = new String ();
value.app2 = new String ();
value.sid1 = new String ();
value.lam1 = new String ();
value.eird1 = new String ();
value.trid1 = new String ();
value.ad1 = new String ();
value.dlrd1 = new String ();
value.cdlrd1 = new String ();
value.rid1 = new String ();
value.dva1 = new String ();
value.tot = new String ();
value.app3 = new String ();
value.sid2 = new String ();
value.lam2 = new String ();
value.eird2 = new String ();
value.trid2 = new String ();
value.ad2 = new String ();
value.dlrd2 = new String ();
value.cdlrd2 = new String ();
value.rid2 = new String ();
value.dva2 = new String ();
value.wot = new String ();
value.fcd = new String ();
value.ptd = new String ();
value.swc2 = new String ();
value.dd2 = new String ();
value.iad = new String ();
value.dateseq = new String ();
value.order = new String ();
value.relatedorder = new String ();
value.proj = new String ();
value.access = new String ();
value.nonaccess = new String ();
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeGcintcOutput (ms, tag); 
	}
	static public GcintcOutput decodeGcintcOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		GcintcOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.mask = ms.decodeOctetString (42, "mask");
		value.ord_class = ms.decodeOctetString (2, "ord_class");
		value.admin_area = ms.decodeOctetString (3, "admin_area");
		value.total_interval = ms.decodeOctetString (4, "total_interval");
		value.app1 = ms.decodeOctetString (7, "app1");
		value.dd1 = ms.decodeOctetString (7, "dd1");
		value.swc1 = ms.decodeOctetString (7, "swc1");
		value.npa = ms.decodeOctetString (25, "npa");
		value.t = ms.decodeOctetString (2, "t");
		value.entry = ms.decodeOctetString (6, "entry");
		value.area = ms.decodeOctetString (5, "area");
		value.app2 = ms.decodeOctetString (4, "app2");
		value.sid1 = ms.decodeOctetString (4, "sid1");
		value.lam1 = ms.decodeOctetString (4, "lam1");
		value.eird1 = ms.decodeOctetString (4, "eird1");
		value.trid1 = ms.decodeOctetString (4, "trid1");
		value.ad1 = ms.decodeOctetString (4, "ad1");
		value.dlrd1 = ms.decodeOctetString (4, "dlrd1");
		value.cdlrd1 = ms.decodeOctetString (4, "cdlrd1");
		value.rid1 = ms.decodeOctetString (4, "rid1");
		value.dva1 = ms.decodeOctetString (4, "dva1");
		value.tot = ms.decodeOctetString (4, "tot");
		value.app3 = ms.decodeOctetString (7, "app3");
		value.sid2 = ms.decodeOctetString (7, "sid2");
		value.lam2 = ms.decodeOctetString (7, "lam2");
		value.eird2 = ms.decodeOctetString (7, "eird2");
		value.trid2 = ms.decodeOctetString (7, "trid2");
		value.ad2 = ms.decodeOctetString (7, "ad2");
		value.dlrd2 = ms.decodeOctetString (7, "dlrd2");
		value.cdlrd2 = ms.decodeOctetString (7, "cdlrd2");
		value.rid2 = ms.decodeOctetString (7, "rid2");
		value.dva2 = ms.decodeOctetString (7, "dva2");
		value.wot = ms.decodeOctetString (7, "wot");
		value.fcd = ms.decodeOctetString (7, "fcd");
		value.ptd = ms.decodeOctetString (7, "ptd");
		value.swc2 = ms.decodeOctetString (7, "swc2");
		value.dd2 = ms.decodeOctetString (7, "dd2");
		value.iad = ms.decodeOctetString (7, "iad");
		value.dateseq = ms.decodeOctetString (4, "dateseq");
		value.order = ms.decodeOctetString (19, "order");
		value.relatedorder = ms.decodeOctetString (21, "relatedorder");
		value.proj = ms.decodeOctetString (17, "proj");
		value.access = ms.decodeOctetString (2, "access");
		value.nonaccess = ms.decodeOctetString (21, "nonaccess");
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeGcintcOutput (ms, value, tag); 
	}
	static public void encodeGcintcOutput (MMarshalStrategy ms, GcintcOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.x_for, 9, "x_for");
	ms.encode (value.mask, 42, "mask");
ms.encode (value.ord_class, 2, "ord_class");
ms.encode (value.admin_area, 3, "admin_area");
ms.encode (value.total_interval, 4, "total_interval");
ms.encode (value.app1, 7, "app1");
ms.encode (value.dd1, 7, "dd1");
ms.encode (value.swc1, 7, "swc1");
ms.encode (value.npa, 25, "npa");
ms.encode (value.t, 2, "t");
ms.encode (value.entry, 6, "entry");
ms.encode (value.area, 5, "area");
ms.encode (value.app2, 4, "app2");
ms.encode (value.sid1, 4, "sid1");
ms.encode (value.lam1, 4, "lam1");
ms.encode (value.eird1, 4, "eird1");
ms.encode (value.trid1, 4, "trid1");
ms.encode (value.ad1, 4, "ad1");
ms.encode (value.dlrd1, 4, "dlrd1");
ms.encode (value.cdlrd1, 4, "cdlrd1");
ms.encode (value.rid1, 4, "rid1");
ms.encode (value.dva1, 4, "dva1");
ms.encode (value.tot, 4, "tot");
ms.encode (value.app3, 7, "app3");
ms.encode (value.sid2, 7, "sid2");
ms.encode (value.lam2, 7, "lam2");
ms.encode (value.eird2, 7, "eird2");
ms.encode (value.trid2, 7, "trid2");
ms.encode (value.ad2, 7, "ad2");
ms.encode (value.dlrd2, 7, "dlrd2");
ms.encode (value.cdlrd2, 7, "cdlrd2");
ms.encode (value.rid2, 7, "rid2");
ms.encode (value.dva2, 7, "dva2");
ms.encode (value.wot, 7, "wot");
ms.encode (value.fcd, 7, "fcd");
ms.encode (value.ptd, 7, "ptd");
ms.encode (value.swc2, 7, "swc2");
ms.encode (value.dd2, 7, "dd2");
ms.encode (value.iad, 7, "iad");
ms.encode (value.dateseq, 4, "dateseq");
ms.encode (value.order, 19, "order");
ms.encode (value.relatedorder, 21, "relatedorder");
ms.encode (value.proj, 17, "proj");
ms.encode (value.access, 2, "access");
ms.encode (value.nonaccess, 21, "nonaccess");
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static GcintcOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeGcintcOutput (ms, "GcintcOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.GcintcOutputHelper.type(); 
}
public static byte [] toOctet (GcintcOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeGcintcOutput (ms, val, "GcintcOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
