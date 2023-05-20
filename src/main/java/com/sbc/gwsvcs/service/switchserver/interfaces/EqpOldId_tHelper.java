package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class EqpOldId_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private EqpOldId_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t();
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.EqpIc = new com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.EqpIc[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ex);
		{ 
			o.write_long (value.EqpIc.length);
			for (int __i = 0; __i < value.EqpIc.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tHelper.write (o, value.EqpIc[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.EqpOldId_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[2];
				members[0] = new StructMember();
				members[0].name = "Ex";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "EqpIc";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.EqpIc_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				myTc = ORB.init().create_struct_tc (id(), "EqpOldId_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/EqpOldId_t:1.0"; } 
}
