// $Id: CblsOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CblsOutputMsg implements MMarshalObject { 
	public CblsOutput value;

	public CblsOutputMsg () {
	}
	public CblsOutputMsg (CblsOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.term_A = new String ();
value.term_Z = new String ();
value.cable = new String ();
value.from_unit = new String ();
value.last_unit = new String ();
value.qty_spare = new String ();
value.tot_qty = new String ();
value.cmpl_date = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.inv_stat = new String ();
value.x_2w_spares = new String ();
value.x_1w_spares = new String ();
value.fac_detail = new String ();
value.fac_use = new String ();
value.fac_group = new String ();
value.comp_id = new String ();
value.no_of_sprs = new String ();
value.no_of_wires = new String ();
value.shld_dir = new String ();
value.ckt_clo_scan = new String ();
value.alt_pri = new String ();
value.dr_class = new String ();
value.misuse = new String ();
value.mw = new String ();
value.termn_frames = new String ();
value.seq_nbr = new String ();
value.CblsGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDef[12];
for (int i0 = 0; i0 < 12; i0++) { 
value.CblsGrp[i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDefMsg.create();
}
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCblsOutput (ms, tag); 
	}
	static public CblsOutput decodeCblsOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CblsOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (8, "cmd");
		value.mask = ms.decodeOctetString (41, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.term_A = ms.decodeOctetString (12, "term_A");
		value.term_Z = ms.decodeOctetString (12, "term_Z");
		value.cable = ms.decodeOctetString (11, "cable");
		value.from_unit = ms.decodeOctetString (6, "from_unit");
		value.last_unit = ms.decodeOctetString (6, "last_unit");
		value.qty_spare = ms.decodeOctetString (6, "qty_spare");
		value.tot_qty = ms.decodeOctetString (6, "tot_qty");
		value.cmpl_date = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "cmpl_date");
		value.inv_stat = ms.decodeOctetString (3, "inv_stat");
		value.x_2w_spares = ms.decodeOctetString (5, "x_2w_spares");
		value.x_1w_spares = ms.decodeOctetString (5, "x_1w_spares");
		value.fac_detail = ms.decodeOctetString (15, "fac_detail");
		value.fac_use = ms.decodeOctetString (3, "fac_use");
		value.fac_group = ms.decodeOctetString (10, "fac_group");
		value.comp_id = ms.decodeOctetString (10, "comp_id");
		value.no_of_sprs = ms.decodeOctetString (5, "no_of_sprs");
		value.no_of_wires = ms.decodeOctetString (2, "no_of_wires");
		value.shld_dir = ms.decodeOctetString (2, "shld_dir");
		value.ckt_clo_scan = ms.decodeOctetString (2, "ckt_clo_scan");
		value.alt_pri = ms.decodeOctetString (2, "alt_pri");
		value.dr_class = ms.decodeOctetString (5, "dr_class");
		value.misuse = ms.decodeOctetString (3, "misuse");
		value.mw = ms.decodeOctetString (2, "mw");
		value.termn_frames = ms.decodeOctetString (3, "termn_frames");
		value.seq_nbr = ms.decodeOctetString (4, "seq_nbr");
		ms.startArray ("CblsGrp", false);
		{ 
			value.CblsGrp = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDef[12];
			for (int __i0 = 0; __i0 < 12; __i0++) { 
				value.CblsGrp[__i0] = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDefMsg.decodeCblsGrpDef (ms, "CblsGrp");
			} 
		}
		ms.endArray ("CblsGrp", false);
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCblsOutput (ms, value, tag); 
	}
	static public void encodeCblsOutput (MMarshalStrategy ms, CblsOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 8, "cmd");
	ms.encode (value.mask, 41, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.term_A, 12, "term_A");
ms.encode (value.term_Z, 12, "term_Z");
ms.encode (value.cable, 11, "cable");
ms.encode (value.from_unit, 6, "from_unit");
ms.encode (value.last_unit, 6, "last_unit");
ms.encode (value.qty_spare, 6, "qty_spare");
ms.encode (value.tot_qty, 6, "tot_qty");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.cmpl_date, "cmpl_date");
ms.encode (value.inv_stat, 3, "inv_stat");
ms.encode (value.x_2w_spares, 5, "x_2w_spares");
ms.encode (value.x_1w_spares, 5, "x_1w_spares");
ms.encode (value.fac_detail, 15, "fac_detail");
ms.encode (value.fac_use, 3, "fac_use");
ms.encode (value.fac_group, 10, "fac_group");
ms.encode (value.comp_id, 10, "comp_id");
ms.encode (value.no_of_sprs, 5, "no_of_sprs");
ms.encode (value.no_of_wires, 2, "no_of_wires");
ms.encode (value.shld_dir, 2, "shld_dir");
ms.encode (value.ckt_clo_scan, 2, "ckt_clo_scan");
ms.encode (value.alt_pri, 2, "alt_pri");
ms.encode (value.dr_class, 5, "dr_class");
ms.encode (value.misuse, 3, "misuse");
ms.encode (value.mw, 2, "mw");
ms.encode (value.termn_frames, 3, "termn_frames");
ms.encode (value.seq_nbr, 4, "seq_nbr");
ms.startArray ("CblsGrp", true);
{ 
for (int __i0 = 0; __i0 < 12; __i0++) { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDefMsg.encodeCblsGrpDef (ms, value.CblsGrp[__i0], "CblsGrp");
} 
}
ms.endArray ("CblsGrp", true);
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static CblsOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCblsOutput (ms, "CblsOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsOutputHelper.type(); 
}
public static byte [] toOctet (CblsOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCblsOutput (ms, val, "CblsOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
