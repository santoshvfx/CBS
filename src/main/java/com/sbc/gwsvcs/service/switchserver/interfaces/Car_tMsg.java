package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Car_tMsg implements MMarshalObject { 
	public Car_t value;

	public Car_tMsg () {
	}
	public Car_tMsg (Car_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeCar_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeCar_t (ms, tag); 
	}
	static public Car_t decodeCar_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Car_t value = create();
		ms.startStruct (tag, false);
		{ 
			ms.startSequence ("CarRec", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("CarRec", false);
			{ 
				value.CarRec = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CarRec[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_tMsg.decodeCarRec_t (ms, "CarRec");
				} 
			}
			ms.endArray ("CarRec", false);
			ms.endSequence ("CarRec", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeCar_t (MMarshalStrategy ms, Car_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		{ 
			ms.startSequence ("CarRec", true);
			ms.encode (value.CarRec.length, "_sequenceLength", true);
			ms.startArray ("CarRec", true);
			{ 
				for (int __i0 = 0; __i0 < value.CarRec.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_tMsg.encodeCarRec_t (ms, value.CarRec[__i0], "CarRec");
				} 
			}
			ms.endArray ("CarRec", true);
			ms.endSequence ("CarRec", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Car_tHelper.type(); 
	}
	public static byte [] toOctet (Car_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeCar_t (ms, val, "Car_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Car_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeCar_t (ms, "Car_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Car_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Car_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Car_t();
		int __seqLength = 0;
		value.CarRec = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRec_t[__seqLength];
		return value; 
	} 
}
