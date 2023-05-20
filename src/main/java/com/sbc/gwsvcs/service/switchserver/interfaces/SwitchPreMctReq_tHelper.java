package com.sbc.gwsvcs.service.switchserver.interfaces;

import org.omg.CORBA.TypeCodePackage.*;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.StructMember;
import org.omg.CORBA.ORB;

public class SwitchPreMctReq_tHelper { 
	private static TypeCode myTc = null;
	private static boolean __active = false;

	private SwitchPreMctReq_tHelper () {
	}
	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t read (org.omg.CORBA.portable.InputStream i) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t value = new com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t();
		value.Header = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.read (i);
		{ 
			int __seqLength = i.read_long();
			value.PreMctReq = new com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_t[__seqLength];
			for (int __i = 0; __i < __seqLength; __i++) { 
				value.PreMctReq[__i] = com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_tHelper.read (i);
			} 
		}
		{
			byte[] _bytes = new byte[7];
			i.read_octet_array (_bytes, 0, 7);
			int _j;
			for (_j = 0; _j < 7; _j++)
				if (_bytes[_j] == 0)
					break;
			value.SWITCH_WC = new String (_bytes, 0, _j);
		}
		{
			byte[] _bytes = new byte[13];
			i.read_octet_array (_bytes, 0, 13);
			int _j;
			for (_j = 0; _j < 13; _j++)
				if (_bytes[_j] == 0)
					break;
			value.ORD_2_NBR = new String (_bytes, 0, _j);
		}
		value.ORD_2_DDT = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.read (i);
		return value; 
	}

	public static void write (org.omg.CORBA.portable.OutputStream o, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t value) { 
		com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.write (o, value.Header);
		{ 
			o.write_long (value.PreMctReq.length);
			for (int __i = 0; __i < value.PreMctReq.length; __i++) { 
				com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_tHelper.write (o, value.PreMctReq[__i]);
			} 
		}
		{
			byte[] _bytes = new byte[7];
			byte[] _bytes_src = value.SWITCH_WC.getBytes();
			int _j;
			for (_j = 0; _j < 7 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 7);
		}
		{
			byte[] _bytes = new byte[13];
			byte[] _bytes_src = value.ORD_2_NBR.getBytes();
			int _j;
			for (_j = 0; _j < 13 - 1; _j++)
				_bytes[_j] = _bytes_src[_j];
			_bytes[_j] = 0;
			o.write_octet_array (_bytes, 0, 13);
		}
		com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.write (o, value.ORD_2_DDT);
	}

	public static void insert (org.omg.CORBA.Any a, com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t t) { 
		org.omg.CORBA.portable.OutputStream o = a.create_output_stream();
		write (o, t);
		a.read_value (o.create_input_stream(), type()); 
	}

	public static com.sbc.gwsvcs.service.switchserver.interfaces.SwitchPreMctReq_t extract (org.omg.CORBA.Any a) throws BAD_OPERATION { 
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
				members[0].name = "Header";
				members[0].type = com.sbc.gwsvcs.service.switchserver.interfaces.Header_tHelper.type();
				members[1] = new StructMember();
				members[1].name = "PreMctReq";
				members[1].type = com.sbc.gwsvcs.service.switchserver.interfaces.PreMctReq_tHelper.type();
				members[1].type = ORB.init().create_sequence_tc (0, members[1].type);
				members[2] = new StructMember();
				members[2].name = "SWITCH_WC";
				members[2].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[2].type = ORB.init().create_array_tc (7, members[2].type);
				members[2].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.SwitchWc_tHelper.id(), "SwitchWc_t", members[2].type);
				members[3] = new StructMember();
				members[3].name = "ORD_2_NBR";
				members[3].type = ORB.init().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
				members[3].type = ORB.init().create_array_tc (13, members[3].type);
				members[3].type = ORB.init ().create_alias_tc (com.sbc.gwsvcs.service.switchserver.interfaces.Ord2Nbr_tHelper.id(), "Ord2Nbr_t", members[3].type);
				members[4] = new StructMember();
				members[4].name = "ORD_2_DDT";
				members[4].type = com.sbc.gwsvcs.service.switchserver.interfaces.Dt_tHelper.type();
				myTc = ORB.init().create_struct_tc (id(), "SwitchPreMctReq_t", members);
				__active = false;
			}
		}
		return myTc;
	}

	public static String id() { return "IDL:com/sbc/gwsvcs/service/switchserver/interfaces/SwitchPreMctReq_t:1.0"; } 
}
