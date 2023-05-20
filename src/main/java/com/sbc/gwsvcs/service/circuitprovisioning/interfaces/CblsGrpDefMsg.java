// $Id: CblsGrpDefMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CblsGrpDefMsg implements MMarshalObject { 
	public CblsGrpDef value;

	public CblsGrpDefMsg () {
	}
	public CblsGrpDefMsg (CblsGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDef();
value.unit = new String ();
value.subd_f = new String ();
value.subd_t = new String ();
value.asgt_rstn = new String ();
value.activity_cur = new String ();
value.activity_pnd = new String ();
value.d = new String ();
value.f = new String ();
value.cktid_clo = new String ();
value.due_date = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCblsGrpDef (ms, tag); 
	}
	static public CblsGrpDef decodeCblsGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CblsGrpDef value = create();
		ms.startStruct (tag, false);
		value.unit = ms.decodeOctetString (6, "unit");
		value.subd_f = ms.decodeOctetString (2, "subd_f");
		value.subd_t = ms.decodeOctetString (2, "subd_t");
		value.asgt_rstn = ms.decodeOctetString (4, "asgt_rstn");
		value.activity_cur = ms.decodeOctetString (2, "activity_cur");
		value.activity_pnd = ms.decodeOctetString (2, "activity_pnd");
		value.d = ms.decodeOctetString (2, "d");
		value.f = ms.decodeOctetString (2, "f");
		value.cktid_clo = ms.decodeOctetString (46, "cktid_clo");
		value.due_date = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "due_date");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCblsGrpDef (ms, value, tag); 
	}
	static public void encodeCblsGrpDef (MMarshalStrategy ms, CblsGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.unit, 6, "unit");
	ms.encode (value.subd_f, 2, "subd_f");
ms.encode (value.subd_t, 2, "subd_t");
ms.encode (value.asgt_rstn, 4, "asgt_rstn");
ms.encode (value.activity_cur, 2, "activity_cur");
ms.encode (value.activity_pnd, 2, "activity_pnd");
ms.encode (value.d, 2, "d");
ms.encode (value.f, 2, "f");
ms.encode (value.cktid_clo, 46, "cktid_clo");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.due_date, "due_date");
ms.endStruct (tag, true); 
}
public static CblsGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCblsGrpDef (ms, "CblsGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CblsGrpDefHelper.type(); 
}
public static byte [] toOctet (CblsGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCblsGrpDef (ms, val, "CblsGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
