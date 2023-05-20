package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwitchPreMctResp_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SwitchPreMctResp_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_t();
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.Umsg = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Umsg[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.write (o, value.Header);
		{ 
			o.write_long (value.Umsg.length);
			for (int __i = 0; __i < value.Umsg.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.write (o, value.Umsg[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctResp_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "Umsg";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				myTc = ORB.init().create_struct_tc (id(), "SwitchPreMctResp_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SwitchPreMctResp_t:1.0"; } 
}
