package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class MasgCkt_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private MasgCkt_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t();
		value.CktCktattr = com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.CktEqp = new com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.CktEqp[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_tHelper.read (i);
			} 
		}
		value.CktSvc = com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tHelper.write (o, value.CktCktattr);
		{ 
			o.write_long (value.CktEqp.length);
			for (int __i = 0; __i < value.CktEqp.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_tHelper.write (o, value.CktEqp[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_tHelper.write (o, value.CktSvc);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.MasgCkt_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "CktCktattr";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.CktCktattr_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "CktEqp";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.CktEqp_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				members[2] = new StructMember();
				members[2].name = "CktSvc";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "MasgCkt_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/MasgCkt_t:1.0"; } 
}
