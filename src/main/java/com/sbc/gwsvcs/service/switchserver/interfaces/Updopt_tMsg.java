package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class Updopt_tMsg implements MMarshalObject { 
	public Updopt_t value;

	public Updopt_tMsg () {
	}
	public Updopt_tMsg (Updopt_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeUpdopt_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeUpdopt_t (ms, tag); 
	}
	static public Updopt_t decodeUpdopt_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		Updopt_t value = create();
		ms.startStruct (tag, false);
		value.TN_LN_CT = ms.decodeOctetString (4, "TN_LN_CT");
		value.PNDG_IND = ms.decodeOctetString (2, "PNDG_IND");
		value.SELT_STATE_CD = ms.decodeOctetString (6, "SELT_STATE_CD");
		value.TN_LIST_NBR = ms.decodeOctetString (4, "TN_LIST_NBR");
		value.Uattr = com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tMsg.decodeComnUattr_t (ms, "Uattr");
		{ 
			ms.startSequence ("EMP_ID", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (3)) {
				throw new MMarshalException("Sequence EMP_ID exceeds the bounded size of 3");
				}
			ms.startArray ("EMP_ID", false);
			{ 
				value.EMP_ID = new String[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.EMP_ID[__i0] = ms.decodeOctetString (9, "EMP_ID");
				} 
			}
			ms.endArray ("EMP_ID", false);
			ms.endSequence ("EMP_ID", false);
		}
		{ 
			ms.startSequence ("CHG_DT", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			if (__seqLength > (2)) {
				throw new MMarshalException("Sequence CHG_DT exceeds the bounded size of 2");
				}
			ms.startArray ("CHG_DT", false);
			{ 
				value.CHG_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CHG_DT[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "CHG_DT");
				} 
			}
			ms.endArray ("CHG_DT", false);
			ms.endSequence ("CHG_DT", false);
		}
		value.FROM_AVAIL_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "FROM_AVAIL_DT");
		value.TO_AVAIL_DT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.decodeDt_t (ms, "TO_AVAIL_DT");
		{ 
			ms.startSequence ("Comp", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Comp", false);
			{ 
				value.Comp = new com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Comp[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Comp_tMsg.decodeComp_t (ms, "Comp");
				} 
			}
			ms.endArray ("Comp", false);
			ms.endSequence ("Comp", false);
		}
		{ 
			ms.startSequence ("Memb", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Memb", false);
			{ 
				value.Memb = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Memb[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Memb");
				} 
			}
			ms.endArray ("Memb", false);
			ms.endSequence ("Memb", false);
		}
		value.Tric = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Tric");
		value.Ic = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.decodeEx_t (ms, "Ic");
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeUpdopt_t (MMarshalStrategy ms, Updopt_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		ms.encode (value.TN_LN_CT, 4, "TN_LN_CT");
		ms.encode (value.PNDG_IND, 2, "PNDG_IND");
		ms.encode (value.SELT_STATE_CD, 6, "SELT_STATE_CD");
		ms.encode (value.TN_LIST_NBR, 4, "TN_LIST_NBR");
		com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_tMsg.encodeComnUattr_t (ms, value.Uattr, "Uattr");
		{ 
			ms.startSequence ("EMP_ID", true);
			if (value.EMP_ID.length > (3)) {
				throw new MMarshalException("Sequence EMP_ID exceeds the bounded size of 3");
				}
			ms.encode (value.EMP_ID.length, "_sequenceLength", true);
			ms.startArray ("EMP_ID", true);
			{ 
				for (int __i0 = 0; __i0 < value.EMP_ID.length; __i0++) { 
					ms.encode (value.EMP_ID[__i0], 9, "EMP_ID");
				} 
			}
			ms.endArray ("EMP_ID", true);
			ms.endSequence ("EMP_ID", true);
		}
		{ 
			ms.startSequence ("CHG_DT", true);
			if (value.CHG_DT.length > (2)) {
				throw new MMarshalException("Sequence CHG_DT exceeds the bounded size of 2");
				}
			ms.encode (value.CHG_DT.length, "_sequenceLength", true);
			ms.startArray ("CHG_DT", true);
			{ 
				for (int __i0 = 0; __i0 < value.CHG_DT.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.CHG_DT[__i0], "CHG_DT");
				} 
			}
			ms.endArray ("CHG_DT", true);
			ms.endSequence ("CHG_DT", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.FROM_AVAIL_DT, "FROM_AVAIL_DT");
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tMsg.encodeDt_t (ms, value.TO_AVAIL_DT, "TO_AVAIL_DT");
		{ 
			ms.startSequence ("Comp", true);
			ms.encode (value.Comp.length, "_sequenceLength", true);
			ms.startArray ("Comp", true);
			{ 
				for (int __i0 = 0; __i0 < value.Comp.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Comp_tMsg.encodeComp_t (ms, value.Comp[__i0], "Comp");
				} 
			}
			ms.endArray ("Comp", true);
			ms.endSequence ("Comp", true);
		}
		{ 
			ms.startSequence ("Memb", true);
			ms.encode (value.Memb.length, "_sequenceLength", true);
			ms.startArray ("Memb", true);
			{ 
				for (int __i0 = 0; __i0 < value.Memb.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Memb[__i0], "Memb");
				} 
			}
			ms.endArray ("Memb", true);
			ms.endSequence ("Memb", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Tric, "Tric");
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tMsg.encodeEx_t (ms, value.Ic, "Ic");
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_tHelper.type(); 
	}
	public static byte [] toOctet (Updopt_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeUpdopt_t (ms, val, "Updopt_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static Updopt_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeUpdopt_t (ms, "Updopt_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Updopt_t();
		value.TN_LN_CT = new String ();
		value.PNDG_IND = new String ();
		value.SELT_STATE_CD = new String ();
		value.TN_LIST_NBR = new String ();
		value.Uattr = new com.sbc.gwsvcs.service.switchserver.interfaces.ComnUattr_t();
		int __seqLength = 0;
		value.EMP_ID = new String[__seqLength];
		value.CHG_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t[__seqLength];
		value.FROM_AVAIL_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.TO_AVAIL_DT = new com.sbc.gwsvcs.service.switchserver.interfaces.Dt_t();
		value.Comp = new com.sbc.gwsvcs.service.switchserver.interfaces.Comp_t[__seqLength];
		value.Memb = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
		value.Tric = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		value.Ic = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t();
		return value; 
	} 
}
