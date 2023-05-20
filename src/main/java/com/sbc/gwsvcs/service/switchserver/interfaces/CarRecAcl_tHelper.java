package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class CarRecAcl_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private CarRecAcl_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t();
		{
			byte[] _bytes = new byte[52];
			i.read_octet_array (_bytes, 0, 52);
			int _j;
			for (_j = 0; _j < 52; _j++)
				if (_bytes[_j] == 0)
					break;
			value.CKT_TERMN_ID = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[4];
			i.read_octet_array (_bytes, 0, 4);
			int _j;
			for (_j = 0; _j < 4; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ACTN_CD = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.Loop = new com.sbc.gwsvcs.service.switchserver.interfaces.Loop_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.Loop[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.Loop_tHelper.read (i);
			} 
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t value) { 
		{
			byte[] _bytes = new byte[52];
			byte[] _bytes_src = value.CKT_TERMN_ID.getBytes();
			int _j;
			for (_j = 0; _j < 52 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 52);
		}
		{
			byte[] _bytes = new byte[4];
			byte[] _bytes_src = value.ACTN_CD.getBytes();
			int _j;
			for (_j = 0; _j < 4 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 4);
		}
		{ 
			o.write_long (value.Loop.length);
			for (int __i = 0; __i < value.Loop.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.Loop_tHelper.write (o, value.Loop[__i]);
			} 
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.CarRecAcl_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "CKT_TERMN_ID";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (52, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.CktTermnId_tHelper.id(), "CktTermnId_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "ACTN_CD";
				members[1].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[1].type = ORB.init().create_array_tc (4, members[1].type);
				members[1].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.ActnCd_tHelper.id(), "ActnCd_t", members[1].type);
				members[2] = new StructMember();
				members[2].name = "Loop";
				members[2].type = com.sbc.gwsvcs.service.switchserver.interfaces.Loop_tHelper.type();
				members[2].type = ORB.init().create_sequence_tc (0, members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "CarRecAcl_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/CarRecAcl_t:1.0"; } 
}
