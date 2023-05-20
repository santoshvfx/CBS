package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Svc_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Svc_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t();
		{ 
			int __seqLength = i.read_long();
			value.ExOldNew = new com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.ExOldNew[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tHelper.read (i);
			} 
		}
		value.Sattr = com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_tHelper.read (i);
		value.DsgnOldNew = com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.Seqp = new com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Seqp[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_tHelper.read (i);
			} 
		}
		value.Ctl = com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t value) { 
		{ 
			o.write_long (value.ExOldNew.length);
			for (int __i = 0; __i < value.ExOldNew.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tHelper.write (o, value.ExOldNew[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_tHelper.write (o, value.Sattr);
		com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tHelper.write (o, value.DsgnOldNew);
		{ 
			o.write_long (value.Seqp.length);
			for (int __i = 0; __i < value.Seqp.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_tHelper.write (o, value.Seqp[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tHelper.write (o, value.Ctl);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.Svc_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
		return read(a.create_input_stream()); 
	}

	synchronized public static TypeCode type() { 
		if (myTc == null) {

			synchronized(org.omg.CORBA.TypeCode.class) {

				if (__active) {
					return org.omg.CORBA.ORB.init().create_recursive_tc(id());
					}
				__active = true;
				StructMember members[] = new StructMember[5];
				members[0] = new StructMember();
				members[0].name = "ExOldNew";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.ExOldNew_tHelper.type();
				members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
				members[1] = new StructMember();
				members[1].name = "Sattr";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Sattr_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "DsgnOldNew";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.DsgnOldNew_tHelper.type();
				members[3] = new StructMember();
				members[3].name = "Seqp";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.Seqp_tHelper.type();
				members[3].type = ORB.init().create_sequence_tc (0, members[3].type);
				members[4] = new StructMember();
				members[4].name = "Ctl";
				members[4].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ctl_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "Svc_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/Svc_t:1.0"; } 
}
