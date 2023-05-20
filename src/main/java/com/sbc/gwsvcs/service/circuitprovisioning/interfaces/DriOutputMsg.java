// $Id: DriOutputMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class DriOutputMsg implements MMarshalObject { 
	public DriOutput value;

	public DriOutputMsg () {
	}
	public DriOutputMsg (DriOutput initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutput();
value.cmd = new String ();
value.mask = new String ();
value.x_for = new String ();
value.ckt = new String ();
value.loca = new String ();
value.locz = new String ();
value.clo = new String ();
value.act = new String ();
value.stat = new String ();
value.segowner = new String ();
value.slc = new String ();
value.puls = new String ();
value.dv = new String ();
value.cac = new String ();
value.ord = new String ();
value.jt_msg = new String ();
value.ctx = new String ();
value.sig_op = new String ();
value.drc = new String ();
value.mic = new String ();
value.dd = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.ec_dsgcon = new String ();
value.ec_tel = new String ();
value.dlrd = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.ec_mco = new String ();
value.ec_oco = new String ();
value.cdlrd = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
value.ic = new String ();
value.pon = new String ();
value.ver = new String ();
value.ckr = new String ();
value.refnum = new String ();
value.ic_dsgcon = new String ();
value.ic_tel = new String ();
value.acccode = new String ();
value.dsg_addr = new String ();
value.nc = new String ();
value.pri_loc = new String ();
value.nci1 = new String ();
value.tlvt1 = new String ();
value.tlvr1 = new String ();
value.apot = new String ();
value.sec_loc = new String ();
value.nci2 = new String ();
value.tlvt2 = new String ();
value.tlvr2 = new String ();
value.spot = new String ();
value.other_loc = new String ();
value.cfa_a = new String ();
value.cfa_z = new String ();
value.remarks = new String ();
value.msgid = new String ();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeDriOutput (ms, tag); 
	}
	static public DriOutput decodeDriOutput (MMarshalStrategy ms, String tag) throws MMarshalException { 
		DriOutput value = create();
		ms.startStruct (tag, false);
		value.last = ms.decodeLong ("last");
		value.cmd = ms.decodeOctetString (8, "cmd");
		value.mask = ms.decodeOctetString (28, "mask");
		value.x_for = ms.decodeOctetString (9, "x_for");
		value.ckt = ms.decodeOctetString (48, "ckt");
		value.loca = ms.decodeOctetString (12, "loca");
		value.locz = ms.decodeOctetString (12, "locz");
		value.clo = ms.decodeOctetString (14, "clo");
		value.act = ms.decodeOctetString (4, "act");
		value.stat = ms.decodeOctetString (15, "stat");
		value.segowner = ms.decodeOctetString (2, "segowner");
		value.slc = ms.decodeOctetString (3, "slc");
		value.puls = ms.decodeOctetString (3, "puls");
		value.dv = ms.decodeOctetString (3, "dv");
		value.cac = ms.decodeOctetString (9, "cac");
		value.ord = ms.decodeOctetString (22, "ord");
		value.jt_msg = ms.decodeOctetString (2, "jt_msg");
		value.ctx = ms.decodeOctetString (3, "ctx");
		value.sig_op = ms.decodeOctetString (6, "sig_op");
		value.drc = ms.decodeOctetString (4, "drc");
		value.mic = ms.decodeOctetString (3, "mic");
		value.dd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "dd");
		value.ec_dsgcon = ms.decodeOctetString (16, "ec_dsgcon");
		value.ec_tel = ms.decodeOctetString (13, "ec_tel");
		value.dlrd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "dlrd");
		value.ec_mco = ms.decodeOctetString (13, "ec_mco");
		value.ec_oco = ms.decodeOctetString (13, "ec_oco");
		value.cdlrd = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "cdlrd");
		value.ic = ms.decodeOctetString (21, "ic");
		value.pon = ms.decodeOctetString (17, "pon");
		value.ver = ms.decodeOctetString (3, "ver");
		value.ckr = ms.decodeOctetString (54, "ckr");
		value.refnum = ms.decodeOctetString (5, "refnum");
		value.ic_dsgcon = ms.decodeOctetString (16, "ic_dsgcon");
		value.ic_tel = ms.decodeOctetString (18, "ic_tel");
		value.acccode = ms.decodeOctetString (7, "acccode");
		value.dsg_addr = ms.decodeOctetString (31, "dsg_addr");
		value.nc = ms.decodeOctetString (5, "nc");
		value.pri_loc = ms.decodeOctetString (33, "pri_loc");
		value.nci1 = ms.decodeOctetString (13, "nci1");
		value.tlvt1 = ms.decodeOctetString (6, "tlvt1");
		value.tlvr1 = ms.decodeOctetString (6, "tlvr1");
		value.apot = ms.decodeOctetString (33, "apot");
		value.sec_loc = ms.decodeOctetString (33, "sec_loc");
		value.nci2 = ms.decodeOctetString (13, "nci2");
		value.tlvt2 = ms.decodeOctetString (6, "tlvt2");
		value.tlvr2 = ms.decodeOctetString (6, "tlvr2");
		value.spot = ms.decodeOctetString (33, "spot");
		value.other_loc = ms.decodeOctetString (36, "other_loc");
		value.cfa_a = ms.decodeOctetString (48, "cfa_a");
		value.cfa_z = ms.decodeOctetString (48, "cfa_z");
		value.remarks = ms.decodeOctetString (232, "remarks");
		value.msgid = ms.decodeOctetString (81, "msgid");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeDriOutput (ms, value, tag); 
	}
	static public void encodeDriOutput (MMarshalStrategy ms, DriOutput value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.last, "last");
		ms.encode (value.cmd, 8, "cmd");
	ms.encode (value.mask, 28, "mask");
ms.encode (value.x_for, 9, "x_for");
ms.encode (value.ckt, 48, "ckt");
ms.encode (value.loca, 12, "loca");
ms.encode (value.locz, 12, "locz");
ms.encode (value.clo, 14, "clo");
ms.encode (value.act, 4, "act");
ms.encode (value.stat, 15, "stat");
ms.encode (value.segowner, 2, "segowner");
ms.encode (value.slc, 3, "slc");
ms.encode (value.puls, 3, "puls");
ms.encode (value.dv, 3, "dv");
ms.encode (value.cac, 9, "cac");
ms.encode (value.ord, 22, "ord");
ms.encode (value.jt_msg, 2, "jt_msg");
ms.encode (value.ctx, 3, "ctx");
ms.encode (value.sig_op, 6, "sig_op");
ms.encode (value.drc, 4, "drc");
ms.encode (value.mic, 3, "mic");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.dd, "dd");
ms.encode (value.ec_dsgcon, 16, "ec_dsgcon");
ms.encode (value.ec_tel, 13, "ec_tel");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.dlrd, "dlrd");
ms.encode (value.ec_mco, 13, "ec_mco");
ms.encode (value.ec_oco, 13, "ec_oco");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.cdlrd, "cdlrd");
ms.encode (value.ic, 21, "ic");
ms.encode (value.pon, 17, "pon");
ms.encode (value.ver, 3, "ver");
ms.encode (value.ckr, 54, "ckr");
ms.encode (value.refnum, 5, "refnum");
ms.encode (value.ic_dsgcon, 16, "ic_dsgcon");
ms.encode (value.ic_tel, 18, "ic_tel");
ms.encode (value.acccode, 7, "acccode");
ms.encode (value.dsg_addr, 31, "dsg_addr");
ms.encode (value.nc, 5, "nc");
ms.encode (value.pri_loc, 33, "pri_loc");
ms.encode (value.nci1, 13, "nci1");
ms.encode (value.tlvt1, 6, "tlvt1");
ms.encode (value.tlvr1, 6, "tlvr1");
ms.encode (value.apot, 33, "apot");
ms.encode (value.sec_loc, 33, "sec_loc");
ms.encode (value.nci2, 13, "nci2");
ms.encode (value.tlvt2, 6, "tlvt2");
ms.encode (value.tlvr2, 6, "tlvr2");
ms.encode (value.spot, 33, "spot");
ms.encode (value.other_loc, 36, "other_loc");
ms.encode (value.cfa_a, 48, "cfa_a");
ms.encode (value.cfa_z, 48, "cfa_z");
ms.encode (value.remarks, 232, "remarks");
ms.encode (value.msgid, 81, "msgid");
ms.endStruct (tag, true); 
}
public static DriOutput fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeDriOutput (ms, "DriOutput"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.DriOutputHelper.type(); 
}
public static byte [] toOctet (DriOutput val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeDriOutput (ms, val, "DriOutput");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
