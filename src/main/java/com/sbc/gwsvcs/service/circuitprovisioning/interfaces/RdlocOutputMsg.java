// $Id: RdlocOutputMsg.java,v 1.1 2002/09/29 04:10:29 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class RdlocOutputMsg implements MMarshalObject { 
	public RdlocOutput value;

	public RdlocOutputMsg () {
	}
	public RdlocOutputMsg (RdlocOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocOutput();
value.cmd = new String ();
value.x_for = new String ();
value.mask = new String ();
value.RdlocGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDef[4];
for (int i0 = 0; i0 < 4; i0++) { 
value.RdlocGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDefMsg.create();
}
value.mstr = new String ();
value.mstrloc = new String ();
value.hr = new String ();
value.hostloc = new String ();
value.pointcode = new String ();
value.tciccomp = new String ();
value.description = new String ();
value.adminarea = new String ();
value.locname = new String ();
value.mcotel = new String ();
value.drarea = new String ();
value.street = new String ();
value.switchsys = new String ();
value.paa1 = new String ();
value.town = new String ();
value.tasuniv = new String ();
value.owner = new String ();
value.cnty = new String ();
value.state = new String ();
value.statsw = new String ();
value.lata = new String ();
value.zip = new String ();
value.phone = new String ();
value.ext = new String ();
value.loss = new String ();
value.db = new String ();
value.pop = new String ();
value.title = new String ();
value.range = new String ();
value.company = new String ();
value.impedance = new String ();
value.bse = new String ();
value.ctl = new String ();
value.tasoptions = new String ();
value.cgnbr = new String ();
value.exchange = new String ();
value.corridor = new String ();
value.officeclass = new String ();
value.sic = new String ();
value.locatetype = new String ();
value.swc = new String ();
value.wats = new String ();
value.remarks = new String ();
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeRdlocOutput (ms, tag); 
	}
	static public RdlocOutput decodeRdlocOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		RdlocOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (7, "cmd");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.mask = ms.decodeOctetString (30, "mask");
		ms.startArray ("RdlocGrp", false);
		{ 
			value.RdlocGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDef[4];
			for (int __i0 = 0; __i0 < 4; __i0++) { 
				value.RdlocGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDefMsg.decodeRdlocGrpDef (ms, "RdlocGrp");
			} 
		}
		ms.endArray ("RdlocGrp", false);
		value.mstr = ms.decodeOctetString (2, "mstr");
		value.mstrloc = ms.decodeOctetString (12, "mstrloc");
		value.hr = ms.decodeOctetString (2, "hr");
		value.hostloc = ms.decodeOctetString (12, "hostloc");
		value.pointcode = ms.decodeOctetString (12, "pointcode");
		value.tciccomp = ms.decodeOctetString (6, "tciccomp");
		value.description = ms.decodeOctetString (47, "description");
		value.adminarea = ms.decodeOctetString (3, "adminarea");
		value.locname = ms.decodeOctetString (20, "locname");
		value.mcotel = ms.decodeOctetString (13, "mcotel");
		value.drarea = ms.decodeOctetString (6, "drarea");
		value.street = ms.decodeOctetString (28, "street");
		value.switchsys = ms.decodeOctetString (4, "switchsys");
		value.paa1 = ms.decodeOctetString (6, "paa1");
		value.town = ms.decodeOctetString (31, "town");
		value.tasuniv = ms.decodeOctetString (4, "tasuniv");
		value.owner = ms.decodeOctetString (5, "owner");
		value.cnty = ms.decodeOctetString (15, "cnty");
		value.state = ms.decodeOctetString (16, "state");
		value.statsw = ms.decodeOctetString (8, "statsw");
		value.lata = ms.decodeOctetString (6, "lata");
		value.zip = ms.decodeOctetString (6, "zip");
		value.phone = ms.decodeOctetString (13, "phone");
		value.ext = ms.decodeOctetString (5, "ext");
		value.loss = ms.decodeOctetString (4, "loss");
		value.db = ms.decodeOctetString (3, "db");
		value.pop = ms.decodeOctetString (2, "pop");
		value.title = ms.decodeOctetString (26, "title");
		value.range = ms.decodeOctetString (5, "range");
		value.company = ms.decodeOctetString (4, "company");
		value.impedance = ms.decodeOctetString (4, "impedance");
		value.bse = ms.decodeOctetString (5, "bse");
		value.ctl = ms.decodeOctetString (3, "ctl");
		value.tasoptions = ms.decodeOctetString (4, "tasoptions");
		value.cgnbr = ms.decodeOctetString (4, "cgnbr");
		value.exchange = ms.decodeOctetString (6, "exchange");
		value.corridor = ms.decodeOctetString (2, "corridor");
		value.officeclass = ms.decodeOctetString (4, "officeclass");
		value.sic = ms.decodeOctetString (3, "sic");
		value.locatetype = ms.decodeOctetString (2, "locatetype");
		value.swc = ms.decodeOctetString (12, "swc");
		value.wats = ms.decodeOctetString (2, "wats");
		value.remarks = ms.decodeOctetString (75, "remarks");
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeRdlocOutput (ms, value, tag); 
	}
	static public void encodeRdlocOutput (MMarshalStrategy ms, RdlocOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 7, "cmd");
	ms.encode (value.x_for, 9, "x_for");
ms.encode (value.mask, 30, "mask");
ms.startArray ("RdlocGrp", true);
{ 
for (int __i0 = 0; __i0 < 4; __i0++) { 
	com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocGrpDefMsg.encodeRdlocGrpDef (ms, value.RdlocGrp[__i0], "RdlocGrp");
} 
}
ms.endArray ("RdlocGrp", true);
ms.encode (value.mstr, 2, "mstr");
ms.encode (value.mstrloc, 12, "mstrloc");
ms.encode (value.hr, 2, "hr");
ms.encode (value.hostloc, 12, "hostloc");
ms.encode (value.pointcode, 12, "pointcode");
ms.encode (value.tciccomp, 6, "tciccomp");
ms.encode (value.description, 47, "description");
ms.encode (value.adminarea, 3, "adminarea");
ms.encode (value.locname, 20, "locname");
ms.encode (value.mcotel, 13, "mcotel");
ms.encode (value.drarea, 6, "drarea");
ms.encode (value.street, 28, "street");
ms.encode (value.switchsys, 4, "switchsys");
ms.encode (value.paa1, 6, "paa1");
ms.encode (value.town, 31, "town");
ms.encode (value.tasuniv, 4, "tasuniv");
ms.encode (value.owner, 5, "owner");
ms.encode (value.cnty, 15, "cnty");
ms.encode (value.state, 16, "state");
ms.encode (value.statsw, 8, "statsw");
ms.encode (value.lata, 6, "lata");
ms.encode (value.zip, 6, "zip");
ms.encode (value.phone, 13, "phone");
ms.encode (value.ext, 5, "ext");
ms.encode (value.loss, 4, "loss");
ms.encode (value.db, 3, "db");
ms.encode (value.pop, 2, "pop");
ms.encode (value.title, 26, "title");
ms.encode (value.range, 5, "range");
ms.encode (value.company, 4, "company");
ms.encode (value.impedance, 4, "impedance");
ms.encode (value.bse, 5, "bse");
ms.encode (value.ctl, 3, "ctl");
ms.encode (value.tasoptions, 4, "tasoptions");
ms.encode (value.cgnbr, 4, "cgnbr");
ms.encode (value.exchange, 6, "exchange");
ms.encode (value.corridor, 2, "corridor");
ms.encode (value.officeclass, 4, "officeclass");
ms.encode (value.sic, 3, "sic");
ms.encode (value.locatetype, 2, "locatetype");
ms.encode (value.swc, 12, "swc");
ms.encode (value.wats, 2, "wats");
ms.encode (value.remarks, 75, "remarks");
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static RdlocOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeRdlocOutput (ms, "RdlocOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.RdlocOutputHelper.type(); 
}
public static byte [] toOctet (RdlocOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeRdlocOutput (ms, val, "RdlocOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
