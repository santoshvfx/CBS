package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class Umsg_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private Umsg_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t();
		{
			byte[] _bytes = new byte[9];
			i.read_octet_array (_bytes, 0, 9);
			int _j;
			for (_j = 0; _j < 9; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MSG_NBR = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.MSG_TYPE = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.MSG_TX = new char[__seqLength];
			i.read_char_array (value.MSG_TX, 0, __seqLength); 
		}
		value.Ex = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t value) { 
		{
			byte[] _bytes = new byte[9];
			byte[] _bytes_src = value.MSG_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 9 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 9);
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.MSG_TYPE.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
		{ 
			o.write_long (value.MSG_TX.length);
			o.write_char_array (value.MSG_TX, 0, value.MSG_TX.length); 
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.write (o, value.Ex);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.Umsg_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "MSG_NBR";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (9, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.MsgNbr_tHelper.id(), "MsgNbr_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "MSG_TYPE";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (2, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.MsgType_tHelper.id(), "MsgType_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "MSG_TX";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_char);
				members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
				members[3] = new StructMember();
				members[3].name = "Ex";
				members[3].type = com.sbc.gwsvcs.service.switchserver.interfaces.Ex_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "Umsg_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/Umsg_t:1.0"; } 
}
