package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class ConnToId_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private ConnToId_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t();
		value.ConnTo = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.ConnTo);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.ConnToId_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "ConnTo";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "ConnToId_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/ConnToId_t:1.0"; } 
}
