package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwitchInqOrdReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SwitchInqOrdReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t();
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.read (i);
		value.InqOrdReq = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tHelper.read (i);
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWITCH_WC = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.write (o, value.Header);
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tHelper.write (o, value.InqOrdReq);
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.SWITCH_WC.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchInqOrdReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "InqOrdReq";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_tHelper.type();
				members[2] = new StructMember();
				members[2].name = "SWITCH_WC";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (7, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWc_tHelper.id(), "SwitchWc_t", members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "SwitchInqOrdReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SwitchInqOrdReq_t:1.0"; } 
}
