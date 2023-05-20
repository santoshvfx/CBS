package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwitchWsiPrvResp_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SwitchWsiPrvResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t();
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.read (i);
		value.Pkt = com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.CktRec = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.CktRec[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_tHelper.read (i);
			} 
		}
		value.OrdCtl = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.OrdCarRec = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.OrdCarRec[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.OrdAcl = new com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.OrdAcl[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Umsg[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.write (o, value.Header);
		com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_tHelper.write (o, value.Pkt);
		{ 
			o.write_long (value.CktRec.length);
			for (int __i = 0; __i < value.CktRec.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_tHelper.write (o, value.CktRec[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tHelper.write (o, value.OrdCtl);
		{ 
			o.write_long (value.OrdCarRec.length);
			for (int __i = 0; __i < value.OrdCarRec.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tHelper.write (o, value.OrdCarRec[__i]);
			} 
		}
		{ 
			o.write_long (value.OrdAcl.length);
			for (int __i = 0; __i < value.OrdAcl.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tHelper.write (o, value.OrdAcl[__i]);
			} 
		}
		{ 
			o.write_long (value.Umsg.length);
			for (int __i = 0; __i < value.Umsg.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.write (o, value.Umsg[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWsiPrvResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[7];
				members[0] = new StructMember();
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "Pkt";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Pkt_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "CktRec";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
				members[3] = new StructMember();
				members[3].name = "OrdCtl";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCtl_tHelper.type();
				members[4] = new StructMember();
				members[4].name = "OrdCarRec";
				members[4].type = com.sbc.gwsvcs.service.switchserver.interfaces.OrdCarRec_tHelper.type();
				members[4].type = ORB.init().create_sequence_tc (0, members[4].type);
				members[5] = new StructMember();
				members[5].name = "OrdAcl";
				members[5].type = com.sbc.gwsvcs.service.switchserver.interfaces.OrdAcl_tHelper.type();
				members[5].type = ORB.init().create_sequence_tc (0, members[5].type);
				members[6] = new StructMember();
				members[6].name = "Umsg";
				members[6].type = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.type();
				members[6].type = ORB.init().create_sequence_tc (0, members[6].type);
				myTc = ORB.init().create_struct_tc (id(), "SwitchWsiPrvResp_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SwitchWsiPrvResp_t:1.0"; } 
}
