package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class InqOrdReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private InqOrdReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t();
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORD_2_NBR = new String (_bytes, 0, _j);
		}
		{ 
			int __seqLength = i.read_long();
			value.InqOrdEx = new com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.InqOrdEx[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tHelper.read (i);
			} 
		}
		{
			byte[] _bytes = new byte[2];
			i.read_octet_array (_bytes, 0, 2);
			int _j;
			for (_j = 0; _j < 2; _j++)
				if (_bytes[_j] == 0)
					break;
			value.FOPTN = new String (_bytes, 0, _j);
		}
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t value) { 
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ORD_2_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		{ 
			o.write_long (value.InqOrdEx.length);
			for (int __i = 0; __i < value.InqOrdEx.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tHelper.write (o, value.InqOrdEx[__i]);
			} 
		}
		{
			byte[] _bytes = new byte[2];
			byte[] _bytes_src = value.FOPTN.getBytes();
			int _j;
			for (_j = 0; _j < 2 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 2);
		}
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "ORD_2_NBR";
				members[0].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[0].type = ORB.init().create_array_tc (13, members[0].type);
				members[0].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Ord2Nbr_tHelper.id(), "Ord2Nbr_t", members[0].type);
				members[1] = new StructMember();
				members[1].name = "InqOrdEx";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.InqOrdEx_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				members[2] = new StructMember();
				members[2].name = "FOPTN";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (2, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Foptn_tHelper.id(), "Foptn_t", members[2].type);
				myTc = ORB.init().create_struct_tc (id(), "InqOrdReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/InqOrdReq_t:1.0"; } 
}
