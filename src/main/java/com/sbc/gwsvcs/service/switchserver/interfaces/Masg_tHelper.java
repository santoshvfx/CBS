package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Masg_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Masg_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t();
		{ 
			int __seqLength = i.read_long();
			value.MasgCkt = new com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.MasgCkt[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t value) { 
		{ 
			o.write_long (value.MasgCkt.length);
			for (int __i = 0; __i < value.MasgCkt.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_tHelper.write (o, value.MasgCkt[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.Masg_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[1];
				members[0] = new StructMember();
				members[0].name = "MasgCkt";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_tHelper.type();
				members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
				myTc = ORB.init().create_struct_tc (id(), "Masg_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/Masg_t:1.0"; } 
}
