// $Id: ErrorUtilMsg.java,v 1.1 2002/09/29 04:10:28 dm2328 Exp $

package com.sbc.gwsvcs.service.circuitprovisioning.interfaces;

  import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class ErrorUtilMsg implements MMarshalObject { 
	public ErrorUtil value;

	public ErrorUtilMsg () {
	}
	public ErrorUtilMsg (ErrorUtil initial) { 
		value = initial; 
	}
public static com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ErrorUtil create () { 
	com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ErrorUtil value = new com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ErrorUtil();
	value.ErrorMsg = new String ();
	return value; 
}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeErrorUtil (ms, tag); 
	}
	static public ErrorUtil decodeErrorUtil (MMarshalStrategy ms, String tag) throws MMarshalException { 
		ErrorUtil value = create();
		ms.startStruct (tag, false);
		value.OrigEvent = ms.decodeLong ("OrigEvent");
		value.ErrorMsg = ms.decodeOctetString (100, "ErrorMsg");
		ms.endStruct (tag, false);
		return value; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeErrorUtil (ms, value, tag); 
	}
	static public void encodeErrorUtil (MMarshalStrategy ms, ErrorUtil value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.OrigEvent, "OrigEvent");
		ms.encode (value.ErrorMsg, 100, "ErrorMsg");
	ms.endStruct (tag, true); 
}
public static ErrorUtil fromOctet (byte [] val) throws MMarshalException { 
	try { 
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(val), false);
		ms.setRemote (ms.decodeBoolean (null));
		return decodeErrorUtil (ms, "ErrorUtil"); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
public TypeCode getType () { 
	return com.sbc.gwsvcs.service.circuitprovisioning.interfaces.ErrorUtilHelper.type(); 
}
public static byte [] toOctet (ErrorUtil val) throws MMarshalException { 
	try {
		        com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
			          new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
		ms.init (new MBuffer(), true);
		ms.encode (false, null);
		encodeErrorUtil (ms, val, "ErrorUtil");
		MBuffer buf = ms.getBuffer();
		return buf.get (buf.getWritePosition()); 
	} catch (MBufferException e) { 
		throw new MMarshalException ("Buffer error", e); 
	} 
}
}
