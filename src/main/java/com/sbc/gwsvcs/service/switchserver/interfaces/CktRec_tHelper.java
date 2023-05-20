package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class CktRec_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private CktRec_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t();
		value.CktRecSvc = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tHelper.read (i);
		value.CktRecCtl = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.EqpIc = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.EqpIc[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tHelper.write (o, value.CktRecSvc);
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tHelper.write (o, value.CktRecCtl);
		{ 
			o.write_long (value.EqpIc.length);
			for (int __i = 0; __i < value.EqpIc.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tHelper.write (o, value.EqpIc[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRec_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[3];
				members[0] = new StructMember();
				members[0].name = "CktRecSvc";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc1_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "CktRecCtl";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.CktRecCtl_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "EqpIc";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "CktRec_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/CktRec_t:1.0"; } 
}
