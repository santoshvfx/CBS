// $Id: EqpaOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class EqpaOutputMsg implements MMarshalObject { 
	public EqpaOutput value;

	public EqpaOutputMsg () {
	}
	public EqpaOutputMsg (EqpaOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaOutput();
value.mask = new String ();
value.x_for = new String ();
value.location = new String ();
value.equip_code = new String ();
value.level = new String ();
value.relayrack = new String ();
value.nmrunits = new String ();
value.nbrspares = new String ();
value.invauth = new String ();
value.asgnauth = new String ();
value.nbrdv = new String ();
value.note = new String ();
value.unit = new String ();
value.unitstatus = new String ();
value.asgnstatus = new String ();
value.spare = new String ();
value.count = new String ();
value.swfw = new String ();
value.invctl = new String ();
value.faultid = new String ();
value.userdata = new String ();
value.pli = new String ();
value.scid = new String ();
value.eqpuse = new String ();
value.edsx = new String ();
value.eqp_ordernbr = new String ();
value.eqp_item = new String ();
value.eqp_date = new String ();
value.eqp_type = new String ();
value.relloc = new String ();
value.supl_ordernbr = new String ();
value.supl_item = new String ();
value.supl_date = new String ();
value.supl_type = new String ();
value.cpypct_a = new String ();
value.cpypct_ty = new String ();
value.cpypct_fr = new String ();
value.cpypct_addr1 = new String ();
value.cpypct_addr2 = new String ();
value.cpypct_addr3 = new String ();
value.cpypct_addr4 = new String ();
value.cpypct_leads = new String ();
value.leasenbr_a = new String ();
value.leasenbr_ty = new String ();
value.leasenbr_fr = new String ();
value.leasenbr_addr1 = new String ();
value.leasenbr_addr2 = new String ();
value.leasenbr_addr3 = new String ();
value.leasenbr_addr4 = new String ();
value.leasenbr_leads = new String ();
value.subdivision = new String ();
value.assign_note = new String ();
value.EqpaGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDef[3];
for (int i0 = 0; i0 < 3; i0++) { 
value.EqpaGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeEqpaOutput (ms, tag); 
	}
	static public EqpaOutput decodeEqpaOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		EqpaOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.mask = ms.decodeOctetString (35, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.location = ms.decodeOctetString (12, "location");
		value.equip_code = ms.decodeOctetString (15, "equip_code");
		value.level = ms.decodeOctetString (2, "level");
		value.relayrack = ms.decodeOctetString (11, "relayrack");
		value.nmrunits = ms.decodeOctetString (5, "nmrunits");
		value.nbrspares = ms.decodeOctetString (5, "nbrspares");
		value.invauth = ms.decodeOctetString (3, "invauth");
		value.asgnauth = ms.decodeOctetString (3, "asgnauth");
		value.nbrdv = ms.decodeOctetString (5, "nbrdv");
		value.note = ms.decodeOctetString (71, "note");
		value.unit = ms.decodeOctetString (7, "unit");
		value.unitstatus = ms.decodeOctetString (3, "unitstatus");
		value.asgnstatus = ms.decodeOctetString (2, "asgnstatus");
		value.spare = ms.decodeOctetString (4, "spare");
		value.count = ms.decodeOctetString (4, "count");
		value.swfw = ms.decodeOctetString (9, "swfw");
		value.invctl = ms.decodeOctetString (3, "invctl");
		value.faultid = ms.decodeOctetString (2, "faultid");
		value.userdata = ms.decodeOctetString (11, "userdata");
		value.pli = ms.decodeOctetString (2, "pli");
		value.scid = ms.decodeOctetString (7, "scid");
		value.eqpuse = ms.decodeOctetString (5, "eqpuse");
		value.edsx = ms.decodeOctetString (3, "edsx");
		value.eqp_ordernbr = ms.decodeOctetString (11, "eqp_ordernbr");
		value.eqp_item = ms.decodeOctetString (4, "eqp_item");
		value.eqp_date = ms.decodeOctetString (3, "eqp_date");
		value.eqp_type = ms.decodeOctetString (2, "eqp_type");
		value.relloc = ms.decodeOctetString (45, "relloc");
		value.supl_ordernbr = ms.decodeOctetString (11, "supl_ordernbr");
		value.supl_item = ms.decodeOctetString (4, "supl_item");
		value.supl_date = ms.decodeOctetString (3, "supl_date");
		value.supl_type = ms.decodeOctetString (2, "supl_type");
		value.cpypct_a = ms.decodeOctetString (2, "cpypct_a");
		value.cpypct_ty = ms.decodeOctetString (2, "cpypct_ty");
		value.cpypct_fr = ms.decodeOctetString (3, "cpypct_fr");
		value.cpypct_addr1 = ms.decodeOctetString (6, "cpypct_addr1");
		value.cpypct_addr2 = ms.decodeOctetString (6, "cpypct_addr2");
		value.cpypct_addr3 = ms.decodeOctetString (6, "cpypct_addr3");
		value.cpypct_addr4 = ms.decodeOctetString (6, "cpypct_addr4");
		value.cpypct_leads = ms.decodeOctetString (8, "cpypct_leads");
		value.leasenbr_a = ms.decodeOctetString (2, "leasenbr_a");
		value.leasenbr_ty = ms.decodeOctetString (2, "leasenbr_ty");
		value.leasenbr_fr = ms.decodeOctetString (3, "leasenbr_fr");
		value.leasenbr_addr1 = ms.decodeOctetString (6, "leasenbr_addr1");
		value.leasenbr_addr2 = ms.decodeOctetString (6, "leasenbr_addr2");
		value.leasenbr_addr3 = ms.decodeOctetString (6, "leasenbr_addr3");
		value.leasenbr_addr4 = ms.decodeOctetString (6, "leasenbr_addr4");
		value.leasenbr_leads = ms.decodeOctetString (8, "leasenbr_leads");
		value.subdivision = ms.decodeOctetString (6, "subdivision");
		value.assign_note = ms.decodeOctetString (46, "assign_note");
		ms.startArray ("EqpaGrp", false);
		{ 
			value.EqpaGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDef[3];
			for (int __i0 = 0; __i0 < 3; __i0++) { 
				value.EqpaGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDefMsg.decodeEqpaGrpDef (ms, "EqpaGrp");
			} 
		}
		ms.endArray ("EqpaGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeEqpaOutput (ms, value, tag); 
	}
	static public void encodeEqpaOutput (MMarshalStrategy ms, EqpaOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.mask, 35, "mask");
	ms.encode (value.x_for, 9, "x_for");
ms.encode (value.location, 12, "location");
ms.encode (value.equip_code, 15, "equip_code");
ms.encode (value.level, 2, "level");
ms.encode (value.relayrack, 11, "relayrack");
ms.encode (value.nmrunits, 5, "nmrunits");
ms.encode (value.nbrspares, 5, "nbrspares");
ms.encode (value.invauth, 3, "invauth");
ms.encode (value.asgnauth, 3, "asgnauth");
ms.encode (value.nbrdv, 5, "nbrdv");
ms.encode (value.note, 71, "note");
ms.encode (value.unit, 7, "unit");
ms.encode (value.unitstatus, 3, "unitstatus");
ms.encode (value.asgnstatus, 2, "asgnstatus");
ms.encode (value.spare, 4, "spare");
ms.encode (value.count, 4, "count");
ms.encode (value.swfw, 9, "swfw");
ms.encode (value.invctl, 3, "invctl");
ms.encode (value.faultid, 2, "faultid");
ms.encode (value.userdata, 11, "userdata");
ms.encode (value.pli, 2, "pli");
ms.encode (value.scid, 7, "scid");
ms.encode (value.eqpuse, 5, "eqpuse");
ms.encode (value.edsx, 3, "edsx");
ms.encode (value.eqp_ordernbr, 11, "eqp_ordernbr");
ms.encode (value.eqp_item, 4, "eqp_item");
ms.encode (value.eqp_date, 3, "eqp_date");
ms.encode (value.eqp_type, 2, "eqp_type");
ms.encode (value.relloc, 45, "relloc");
ms.encode (value.supl_ordernbr, 11, "supl_ordernbr");
ms.encode (value.supl_item, 4, "supl_item");
ms.encode (value.supl_date, 3, "supl_date");
ms.encode (value.supl_type, 2, "supl_type");
ms.encode (value.cpypct_a, 2, "cpypct_a");
ms.encode (value.cpypct_ty, 2, "cpypct_ty");
ms.encode (value.cpypct_fr, 3, "cpypct_fr");
ms.encode (value.cpypct_addr1, 6, "cpypct_addr1");
ms.encode (value.cpypct_addr2, 6, "cpypct_addr2");
ms.encode (value.cpypct_addr3, 6, "cpypct_addr3");
ms.encode (value.cpypct_addr4, 6, "cpypct_addr4");
ms.encode (value.cpypct_leads, 8, "cpypct_leads");
ms.encode (value.leasenbr_a, 2, "leasenbr_a");
ms.encode (value.leasenbr_ty, 2, "leasenbr_ty");
ms.encode (value.leasenbr_fr, 3, "leasenbr_fr");
ms.encode (value.leasenbr_addr1, 6, "leasenbr_addr1");
ms.encode (value.leasenbr_addr2, 6, "leasenbr_addr2");
ms.encode (value.leasenbr_addr3, 6, "leasenbr_addr3");
ms.encode (value.leasenbr_addr4, 6, "leasenbr_addr4");
ms.encode (value.leasenbr_leads, 8, "leasenbr_leads");
ms.encode (value.subdivision, 6, "subdivision");
ms.encode (value.assign_note, 46, "assign_note");
ms.startArray ("EqpaGrp", true);
{ 
for (int __i0 = 0; __i0 < 3; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaGrpDefMsg.encodeEqpaGrpDef (ms, value.EqpaGrp[__i0], "EqpaGrp");
} 
}
ms.endArray ("EqpaGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static EqpaOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeEqpaOutput (ms, "EqpaOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.EqpaOutputHelper.type(); 
}
public static byte [] toOctet (EqpaOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeEqpaOutput (ms, val, "EqpaOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
