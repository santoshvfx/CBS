// $Id: CxrsGrpDefMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class CxrsGrpDefMsg implements MMarshalObject { 
	public CxrsGrpDef value;

	public CxrsGrpDefMsg () {
	}
	public CxrsGrpDefMsg (CxrsGrpDef initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDef create () { 
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDef value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDef();
value.channel_pair = new String ();
value.asgt_rstn = new String ();
value.cur_act = new String ();
value.inv_stat = new String ();
value.d = new String ();
value.circuit_id = new String ();
value.pend_act = new String ();
value.due_date_data = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_t();
return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCxrsGrpDef (ms, tag); 
	}
	static public CxrsGrpDef decodeCxrsGrpDef (MMarshalStrategy ms, String tag) throws MMarshalException { 
		CxrsGrpDef value = create();
		ms.startStruct (tag, false);
		value.channel_pair = ms.decodeOctetString (6, "channel_pair");
		value.asgt_rstn = ms.decodeOctetString (4, "asgt_rstn");
		value.cur_act = ms.decodeOctetString (2, "cur_act");
		value.inv_stat = ms.decodeOctetString (3, "inv_stat");
		value.d = ms.decodeOctetString (2, "d");
		value.circuit_id = ms.decodeOctetString (46, "circuit_id");
		value.pend_act = ms.decodeOctetString (2, "pend_act");
		value.due_date_data = com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.decodeDt_t (ms, "due_date_data");
		value.ws_flag = ms.decodeLong ("ws_flag");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCxrsGrpDef (ms, value, tag); 
	}
	static public void encodeCxrsGrpDef (MMarshalStrategy ms, CxrsGrpDef value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.channel_pair, 6, "channel_pair");
	ms.encode (value.asgt_rstn, 4, "asgt_rstn");
ms.encode (value.cur_act, 2, "cur_act");
ms.encode (value.inv_stat, 3, "inv_stat");
ms.encode (value.d, 2, "d");
ms.encode (value.circuit_id, 46, "circuit_id");
ms.encode (value.pend_act, 2, "pend_act");
com.sbc.gwsvcs.service.circuitprovisioning.interfaces.Dt_tMsg.encodeDt_t (ms, value.due_date_data, "due_date_data");
ms.encode (value.ws_flag, "ws_flag");
ms.endStruct (tag, true); 
}
public static CxrsGrpDef fromOctet (byte [] val) throws MMarshalException { 
try { 
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(val), false);
ms.setRemote (ms.decodeBoolean (null));
return decodeCxrsGrpDef (ms, "CxrsGrpDef"); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
public TypeCode getType () { 
return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.CxrsGrpDefHelper.type(); 
}
public static byte [] toOctet (CxrsGrpDef val) throws MMarshalException { 
try {
        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
ms.init (new MBuffer(), true);
ms.encode (false, null);
encodeCxrsGrpDef (ms, val, "CxrsGrpDef");
MBuffer buf = ms.getBuffer();
return buf.get (buf.getWritePosition()); 
} catch (MBufferException e) { 
throw new MMarshalException ("Buffer error", e); 
} 
}
}
