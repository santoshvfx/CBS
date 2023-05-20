package com.sbc.gwsvcs.service.switchserver.interfaces;

import com.sbc.vicunalite.api.*;
import java.util.*;
import org.omg.CORBA.TypeCode;

public class SwitchWsiPrvResp_tMsg implements MMarshalObject { 
	public SwitchWsiPrvResp_t value;

	public SwitchWsiPrvResp_tMsg () {
	}
	public SwitchWsiPrvResp_tMsg (SwitchWsiPrvResp_t initial) { 
		value = initial; 
	}
	public void encode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		encodeSwitchWsiPrvResp_t (ms, value, tag); 
	}
	public void decode (MMarshalStrategy ms, String tag) throws MMarshalException { 
		value = decodeSwitchWsiPrvResp_t (ms, tag); 
	}
	static public SwitchWsiPrvResp_t decodeSwitchWsiPrvResp_t (MMarshalStrategy ms, String tag) throws MMarshalException { 
		SwitchWsiPrvResp_t value = create();
		ms.startStruct (tag, false);
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.decodeHeader_t (ms, "Header");
		value.Pkt = com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_tMsg.decodePkt_t (ms, "Pkt");
		{ 
			ms.startSequence ("CktRec", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("CktRec", false);
			{ 
				value.CktRec = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.CktRec[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_tMsg.decodeCktRec_t (ms, "CktRec");
				} 
			}
			ms.endArray ("CktRec", false);
			ms.endSequence ("CktRec", false);
		}
		value.OrdCtl = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tMsg.decodeOrdCtl_t (ms, "OrdCtl");
		{ 
			ms.startSequence ("OrdCarRec", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("OrdCarRec", false);
			{ 
				value.OrdCarRec = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.OrdCarRec[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tMsg.decodeOrdCarRec_t (ms, "OrdCarRec");
				} 
			}
			ms.endArray ("OrdCarRec", false);
			ms.endSequence ("OrdCarRec", false);
		}
		{ 
			ms.startSequence ("OrdAcl", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("OrdAcl", false);
			{ 
				value.OrdAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.OrdAcl[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tMsg.decodeOrdAcl_t (ms, "OrdAcl");
				} 
			}
			ms.endArray ("OrdAcl", false);
			ms.endSequence ("OrdAcl", false);
		}
		{ 
			ms.startSequence ("Umsg", false);
			int __seqLength = ms.decodeULong ("_sequenceLength", true);
			ms.startArray ("Umsg", false);
			{ 
				value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
				for (int __i0 = 0; __i0 < __seqLength; __i0++) { 
					value.Umsg[__i0] = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tMsg.decodeUmsg_t (ms, "Umsg");
				} 
			}
			ms.endArray ("Umsg", false);
			ms.endSequence ("Umsg", false);
		}
		ms.endStruct (tag, false);
		return value; 
	}
	static public void encodeSwitchWsiPrvResp_t (MMarshalStrategy ms, SwitchWsiPrvResp_t value, String tag) throws MMarshalException { 
		ms.startStruct (tag, true);
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tMsg.encodeHeader_t (ms, value.Header, "Header");
		com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_tMsg.encodePkt_t (ms, value.Pkt, "Pkt");
		{ 
			ms.startSequence ("CktRec", true);
			ms.encode (value.CktRec.length, "_sequenceLength", true);
			ms.startArray ("CktRec", true);
			{ 
				for (int __i0 = 0; __i0 < value.CktRec.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_tMsg.encodeCktRec_t (ms, value.CktRec[__i0], "CktRec");
				} 
			}
			ms.endArray ("CktRec", true);
			ms.endSequence ("CktRec", true);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tMsg.encodeOrdCtl_t (ms, value.OrdCtl, "OrdCtl");
		{ 
			ms.startSequence ("OrdCarRec", true);
			ms.encode (value.OrdCarRec.length, "_sequenceLength", true);
			ms.startArray ("OrdCarRec", true);
			{ 
				for (int __i0 = 0; __i0 < value.OrdCarRec.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tMsg.encodeOrdCarRec_t (ms, value.OrdCarRec[__i0], "OrdCarRec");
				} 
			}
			ms.endArray ("OrdCarRec", true);
			ms.endSequence ("OrdCarRec", true);
		}
		{ 
			ms.startSequence ("OrdAcl", true);
			ms.encode (value.OrdAcl.length, "_sequenceLength", true);
			ms.startArray ("OrdAcl", true);
			{ 
				for (int __i0 = 0; __i0 < value.OrdAcl.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tMsg.encodeOrdAcl_t (ms, value.OrdAcl[__i0], "OrdAcl");
				} 
			}
			ms.endArray ("OrdAcl", true);
			ms.endSequence ("OrdAcl", true);
		}
		{ 
			ms.startSequence ("Umsg", true);
			ms.encode (value.Umsg.length, "_sequenceLength", true);
			ms.startArray ("Umsg", true);
			{ 
				for (int __i0 = 0; __i0 < value.Umsg.length; __i0++) { 
					com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tMsg.encodeUmsg_t (ms, value.Umsg[__i0], "Umsg");
				} 
			}
			ms.endArray ("Umsg", true);
			ms.endSequence ("Umsg", true);
		}
		ms.endStruct (tag, true); 
	}
	public TypeCode getType () { 
		return com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_tHelper.type(); 
	}
	public static byte [] toOctet (SwitchWsiPrvResp_t val) throws MMarshalException { 
		try {
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(), true);
			ms.encode (false, null);
			encodeSwitchWsiPrvResp_t (ms, val, "SwitchWsiPrvResp_t");
			MBuffer buf = ms.getBuffer();
			return buf.get (buf.getWritePosition()); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static SwitchWsiPrvResp_t fromOctet (byte [] val) throws MMarshalException { 
		try { 
			com.sbc.vicunalite.api.marshal.MCDRStrategy ms = 
				new com.sbc.vicunalite.api.marshal.MCDRStrategy(); 
			ms.init (new MBuffer(val), false);
			ms.setRemote (ms.decodeBoolean (null));
			return decodeSwitchWsiPrvResp_t (ms, "SwitchWsiPrvResp_t"); 
		} catch (MBufferException e) { 
			throw new MMarshalException ("Buffer error", e); 
		} 
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t create () { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t();
		value.Header = new com.sbc.gwsvcs.service.switchserver.interfaces.Header_t();
		value.Pkt = new com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_t();
		int __seqLength = 0;
		value.CktRec = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t[__seqLength];
		value.OrdCtl = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_t();
		value.OrdCarRec = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t[__seqLength];
		value.OrdAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t[__seqLength];
		value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
		return value; 
	} 
}
