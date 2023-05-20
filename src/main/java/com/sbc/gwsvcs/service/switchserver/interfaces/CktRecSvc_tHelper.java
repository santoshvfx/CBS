package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class CktRecSvc_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private CktRecSvc_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t();
		{ 
			int __seqLength = i.read_long();
			value.SvcExOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SvcExOld[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.SvcHuntOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SvcHuntOld[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
			} 
		}
		{ 
			int __seqLength = i.read_long();
			value.SvcAssocOld = new com.sbc.gwsvcs.service.switchserver.interfaces.Ex_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.SvcAssocOld[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
			} 
		}
		value.SvcSattrOld = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t value) { 
		{ 
			o.write_long (value.SvcExOld.length);
			for (int __i = 0; __i < value.SvcExOld.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.SvcExOld[__i]);
			} 
		}
		{ 
			o.write_long (value.SvcHuntOld.length);
			for (int __i = 0; __i < value.SvcHuntOld.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.SvcHuntOld[__i]);
			} 
		}
		{ 
			o.write_long (value.SvcAssocOld.length);
			for (int __i = 0; __i < value.SvcAssocOld.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.SvcAssocOld[__i]);
			} 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tHelper.write (o, value.SvcSattrOld);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.CktRecSvc_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "SvcExOld";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[0].type = ORB.init().create_sequence_tc (0, members[0].type);
				members[1] = new StructMember();
				members[1].name = "SvcHuntOld";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				members[2] = new StructMember();
				members[2].name = "SvcAssocOld";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
				members[3] = new StructMember();
				members[3].name = "SvcSattrOld";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.SvcSattrOld_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "CktRecSvc_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/CktRecSvc_t:1.0"; } 
}
