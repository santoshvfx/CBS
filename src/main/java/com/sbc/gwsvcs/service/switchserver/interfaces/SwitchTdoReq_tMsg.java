package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchTdoReq_tMsg implements MMarshalObject { 
	public SwitchTdoReq_t value;

	public SwitchTdoReq_tMsg () {
	}
	public SwitchTdoReq_tMsg (SwitchTdoReq_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchTdoReq_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchTdoReq_t (ms, tag); 
	}
	static public SwitchTdoReq_t decodeSwitchTdoReq_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchTdoReq_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.MSEG_CD = ms.decodeOctetString (2, "MSEG_CD");
		{ 
			ms.startSequence ("Car", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Car", false);
			{ 
				value.Car = new com.sbc.gwsvcs.service.switchserver.interfaces.Car_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Car[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Car_tMsg.decodeCar_t (ms, "Car");
				} 
			}
			ms.endArray ("Car", false);
			ms.endSequence ("Car", false);
		}
		{ 
			ms.startSequence ("Masg", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Masg", false);
			{ 
				value.Masg = new com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Masg[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Masg_tMsg.decodeMasg_t (ms, "Masg");
				} 
			}
			ms.endArray ("Masg", false);
			ms.endSequence ("Masg", false);
		}
		value.SWITCH_WC = ms.decodeOctetString (7, "SWITCH_WC");
		value.ORD_2_NBR = ms.decodeOctetString (13, "ORD_2_NBR");
		value.ORD_2_DDT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "ORD_2_DDT");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchTdoReq_t (MMarshalStrategy ms, SwitchTdoReq_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		ms.encode (value.MSEG_CD, 2, "MSEG_CD");
		{ 
			ms.startSequence ("Car", true);
			ms.encode (value.Car.length, "_sequenceLength", true);
			ms.startArray ("Car", true);
			{ 
				for (int __i0 = 0; __i0 < value.Car.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Car_tMsg.encodeCar_t (ms, value.Car[__i0], "Car");
				} 
			}
			ms.endArray ("Car", true);
			ms.endSequence ("Car", true);
		}
		{ 
			ms.startSequence ("Masg", true);
			ms.encode (value.Masg.length, "_sequenceLength", true);
			ms.startArray ("Masg", true);
			{ 
				for (int __i0 = 0; __i0 < value.Masg.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Masg_tMsg.encodeMasg_t (ms, value.Masg[__i0], "Masg");
				} 
			}
			ms.endArray ("Masg", true);
			ms.endSequence ("Masg", true);
		}
		ms.encode (value.SWITCH_WC, 7, "SWITCH_WC");
		ms.encode (value.ORD_2_NBR, 13, "ORD_2_NBR");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.ORD_2_DDT, "ORD_2_DDT");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchTdoReq_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchTdoReq_t (ms, val, "SwitchTdoReq_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchTdoReq_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchTdoReq_t (ms, "SwitchTdoReq_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchTdoReq_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.MSEG_CD = new String ();
		int __seqLength = 0;
		value.Car = new com.sbc.gwsvcs.service.switchserver.interfaces.Car_t[__seqLength];
		value.Masg = new com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t[__seqLength];
		value.SWITCH_WC = new String ();
		value.ORD_2_NBR = new String ();
		value.ORD_2_DDT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		return value; 
	} 
}
