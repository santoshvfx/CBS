package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class CktSvc_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private CktSvc_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t();
		{ 
			int __seqLength = i.read_long();
			value.SvcSeqp = new com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SvcSeqp[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tHelper.read (i);
			} 
		}
		value.SvcCtl = com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_tHelper.read (i);
		value.ExOld = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		value.ExNew = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t value) { 
		{ 
			o.write_long (value.SvcSeqp.length);
			for (int __i = 0; __i < value.SvcSeqp.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tHelper.write (o, value.SvcSeqp[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_tHelper.write (o, value.SvcCtl);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.ExOld);
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.ExNew);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktSvc_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[4];
				members[0] = new StructMember();
				members[0].name = "SvcSeqp";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSeqp_tHelper.type();
				members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
				members[1] = new StructMember();
				members[1].name = "SvcCtl";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.SvcCtl_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "ExOld";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[3] = new StructMember();
				members[3].name = "ExNew";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "CktSvc_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/CktSvc_t:1.0"; } 
}
